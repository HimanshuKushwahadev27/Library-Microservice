package com.emi.Authoring_service.ServiceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.emi.Authoring_service.Repository.DraftBookRepo;
import com.emi.Authoring_service.Repository.DraftChapterRepo;
import com.emi.Authoring_service.RequestDtos.PublishDraftBookRequest;
import com.emi.Authoring_service.RequestDtos.RequestBookCreateDto;
import com.emi.Authoring_service.RequestDtos.RequestUpdateDraftBookDto;
import com.emi.Authoring_service.ResponseDtos.ResponseDraftBookDto;
import com.emi.Authoring_service.clients.CatalogService;
import com.emi.Authoring_service.entity.AuthorDraftBook;
import com.emi.Authoring_service.entity.AuthorDraftChapter;
import com.emi.Authoring_service.enums.BookVisibilityStatus;
import com.emi.Authoring_service.exceptions.BookAlreadyExistsException;
import com.emi.Authoring_service.exceptions.DraftNotFoundException;
import com.emi.Authoring_service.exceptions.NotAuthorizedException;
import com.emi.Authoring_service.mapper.BookDraftMapper;
import com.emi.Authoring_service.service.DraftBookService;
import com.emi.Authoring_service.service.DraftChapterService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookDraftServiceImpl implements DraftBookService {

	private final DraftChapterRepo chapterDraftRepo;
	private final DraftChapterService draftChapterService;
	private final BookDraftMapper bookDraftMapper;
	private final DraftBookRepo bookDraftRepo;
	private final CatalogService catalogService;

//	@PreAuthorize("hasRole('AUTHOR')")
	@Transactional
	@Override
	public ResponseDraftBookDto createBookDraft(RequestBookCreateDto request) {
		
		if(bookDraftRepo.existsByIsbn(request.isbn())) {
			throw new BookAlreadyExistsException("Book with the isbn "+ request.isbn()+" already exists");
		}
		
		var draftBook = bookDraftMapper.toEntity(request);
		bookDraftRepo.save(draftBook);
		
		return bookDraftMapper.toDto(draftBook);
	}

//	@PreAuthorize("hasRole('AUTHOR')")
	@Transactional
	@Override
	public ResponseDraftBookDto updateBookDraft(RequestUpdateDraftBookDto request) {
		
		AuthorDraftBook bookDraft = bookDraftRepo
				      .findById(request.id())
				      .orElseThrow(
				    		  () -> new DraftNotFoundException("Book Draft for the id " + request.id() + "not found")
				    		  );
		
		if(!bookDraft.getAuthorId().equals(request.authorId())) {
			throw new NotAuthorizedException("You are not permitted to access the book draft with id " + request.id());
		}
		
		if(bookDraft.getStatusVisible().equals( BookVisibilityStatus.PUBLIC) && bookDraft.getCatalogBookId()!=null) {
			this.updatePublishedBook(request);
		}
		
		bookDraft = bookDraftMapper.updateDraft( request, bookDraft);
		bookDraftRepo.save(bookDraft);
		
		return bookDraftMapper.toDto(bookDraft);
	}

//	@PreAuthorize("hasRole('AUTHOR')")
	@Override
	public List<ResponseDraftBookDto> getMyDraftBooks(UUID authorId) {
		
		List<AuthorDraftBook> bookDrafts = bookDraftRepo
				         		.findAllByAuthorId(authorId)
				         		.orElseThrow(() -> new DraftNotFoundException("No drafted books for authorId " + authorId));
		
		if(bookDrafts.isEmpty()){
			throw new DraftNotFoundException("No drafted books for authorId " + authorId);
		}
		return bookDrafts
				.stream()
				.map(bookDraftMapper::toDto)
				.toList();
		
	}

//	@PreAuthorize("hasRole('AUTHOR')")
	@Override
	public ResponseDraftBookDto getMyDraftBooksById(UUID authorId, UUID draftBookId) {
		
		AuthorDraftBook bookDraft = bookDraftRepo
			      .findByAuthorIdAndId(authorId, draftBookId)
			      .orElseThrow(
			    		  () -> new DraftNotFoundException("Book Draft for the id " + draftBookId + "not found")
			    		  );
		
		if(!bookDraft.getAuthorId().equals(authorId)) {
			throw new NotAuthorizedException("You are not permitted to access the book draft with id " + authorId);
		}
		
		return bookDraftMapper.toDto(bookDraft);
	}

//	@PreAuthorize("hasRole('AUTHOR')")
	@Transactional
	@Override
	public String deleteDraftBookById(UUID bookId, UUID authorId) {
		AuthorDraftBook bookDraft = bookDraftRepo
			      .findByAuthorIdAndId(authorId, bookId)
			      .orElseThrow(
			    		  () -> new DraftNotFoundException("Book Draft for the id " + bookId + "not found")
			    		  );

		draftChapterService.deleteDraftChaptersByIds(chapterDraftRepo
				.findByDraftBookId(bookId)
				.orElseThrow( 
						() -> new DraftNotFoundException("Chapter Drafts not found for the book id " + bookId )
						)
				.stream()
				.map(AuthorDraftChapter::getId)
				.toList()   
				, authorId);
		
		bookDraftRepo.deleteById(bookDraft.getId());
		
		return "Book draft with id " + bookId + "of author " +authorId+ "is deleted !!";
	}

//	@PreAuthorize("hasRole('AUTHOR')")
	@Transactional
	@Override
	public UUID publishDraftedBook(PublishDraftBookRequest request, UUID authorId) {
		AuthorDraftBook bookDraft = bookDraftRepo
			      .findByAuthorIdAndId(authorId, request.draftBookId())
			      .orElseThrow(
			    		  () -> new DraftNotFoundException("Book Draft for the id " + request.draftBookId() + "not found")
			    		  );
		
		if(bookDraft.getStatusVisible().equals( BookVisibilityStatus.PUBLIC)) {
			throw new NotAuthorizedException("Book with id " + request.draftBookId() + "is already published");
		}
		
		if(!bookDraft.getAuthorId().equals(authorId)) {
			throw new NotAuthorizedException("You are not permitted to access the book draft with id " + authorId);
		}
		
		bookDraft.setStatusVisible(BookVisibilityStatus.PUBLIC);
		UUID bookCatalogId=catalogService
			.createBook(
					bookDraftMapper
					.toPublish(bookDraft, request)
					);
		bookDraft.setCatalogBookId(bookCatalogId);
		bookDraftRepo.save(bookDraft);
		
		return bookCatalogId;

	}

//	@PreAuthorize("hasRole('AUTHOR')")
	@Transactional
	@Override
	public void updatePublishedBook(RequestUpdateDraftBookDto request) {
		catalogService.updateBook(bookDraftMapper.toUpdatePublished(request), request.authorId());
	}

}
