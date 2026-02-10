package com.emi.Authoring_service.ServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.emi.Authoring_service.Repository.DraftBookRepo;
import com.emi.Authoring_service.Repository.DraftChapterRepo;
import com.emi.Authoring_service.RequestDtos.RequestChapterCreateDto;
import com.emi.Authoring_service.RequestDtos.RequestUpdateDraftChapterDto;
import com.emi.Authoring_service.ResponseDtos.ResponseDraftChapterDto;
import com.emi.Authoring_service.clients.CatalogService;
import com.emi.Authoring_service.entity.AuthorDraftBook;
import com.emi.Authoring_service.entity.AuthorDraftChapter;
import com.emi.Authoring_service.enums.ChapterStatus;
import com.emi.Authoring_service.exceptions.DeletedException;
import com.emi.Authoring_service.exceptions.DraftNotFoundException;
import com.emi.Authoring_service.exceptions.NotAuthorizedException;
import com.emi.Authoring_service.mapper.ChapterDraftMapper;
import com.emi.Authoring_service.service.DraftChapterService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChapterDraftServiceImpl implements DraftChapterService {


	private final ChapterDraftMapper chapterDraftMapper;
	private final DraftChapterRepo chapterDraftRepo;
	private final DraftBookRepo bookDraftRepo;
	private final CatalogService catalogService;
	
	
	@Transactional
	@Override
	public ResponseDraftChapterDto createChapterDraft(RequestChapterCreateDto request) {
		 
		AuthorDraftBook draftBook = bookDraftRepo
				.findById(request.draftBookId())
				.orElseThrow(
						() -> new DraftNotFoundException("Draft for the book for the id " + request.draftBookId() + "is not found")
						);
		
		if(draftBook.getIsDeleted()) {
			throw new DeletedException("Draft for the book with id " + request.draftBookId());
		}
		
		AuthorDraftChapter draftChapter = chapterDraftMapper.toEntity(request);
		chapterDraftRepo.save(draftChapter);
		
		return chapterDraftMapper.toDto(draftChapter);
	}

	@Override
	public ResponseDraftChapterDto updateChapterDraft(RequestUpdateDraftChapterDto request, UUID authorId) {
		
		AuthorDraftChapter chapterDraft = chapterDraftRepo
				.findById(request.id())
				.orElseThrow(
						() -> new DraftNotFoundException("Chapter draft with the id " +request.id()+ " is not found")
						);
		
		AuthorDraftBook draftBook = bookDraftRepo
				.findById(chapterDraft.getDraftBookId())
				.orElseThrow(
						() -> new DraftNotFoundException("Draft for the book for the id " + chapterDraft.getDraftBookId())
						);
		
		if(draftBook.getIsDeleted()) {
			throw new DeletedException("Draft for the book with id " + chapterDraft.getIsDeleted());
		}
		
		if(draftBook.getAuthorId()!=authorId) {
			throw new NotAuthorizedException("You are not authorized to make changes in the chapter draft with id " + request.id());
		}
		
		chapterDraftMapper.toUpdate(request, chapterDraft);
		chapterDraftRepo.save(chapterDraft);
		
		return chapterDraftMapper.toDto(chapterDraft);
		
	}

	@Override
	public List<ResponseDraftChapterDto> getMyDraftChaptersByBookId(UUID authorId, UUID bookId) {
		
		List<AuthorDraftChapter> chapters = chapterDraftRepo
				.findByDraftBookId(bookId)
				.orElseThrow(
						() -> new DraftNotFoundException("No drafts for the book with id "+ bookId)
						);
		
		AuthorDraftBook draftBook = bookDraftRepo
				.findById(bookId)
				.orElseThrow(
						() -> new DraftNotFoundException("Draft for the book for the id " + bookId)
						);
		
		if(draftBook.getAuthorId()!=authorId) {
			throw new NotAuthorizedException("You are not authorized to view the book draft with id " + bookId );
		}
		
		if(draftBook.getIsDeleted()) {
			throw new DeletedException("Book with the id " + bookId + " is deleted");
		}
		
		return chapters.stream().map(chapterDraftMapper::toDto).toList();
	}

	@Override
	public List<ResponseDraftChapterDto> getMyDraftChaptersByChapterIds(UUID authorId, Set<UUID> chapterIds){
		return chapterIds.stream().map(id -> this.getMyDraftChapterById(authorId, id)).toList();
	}
	
	
	@Override
	public ResponseDraftChapterDto getMyDraftChapterById(UUID authorId, UUID draftChapterId) {
		AuthorDraftChapter chapterDraft = chapterDraftRepo
				.findById(draftChapterId)
				.orElseThrow(
						() -> new DraftNotFoundException("Chapter draft with the id " +draftChapterId+ " is not found")
						);
		
		AuthorDraftBook draftBook = bookDraftRepo
				.findById(chapterDraft.getDraftBookId())
				.orElseThrow(
						() -> new DraftNotFoundException("book for the chapter of bookId " + chapterDraft.getDraftBookId() + "is not found")
						);
		
		
		if(draftBook.getIsDeleted()) {
			throw new DeletedException("Draft for the book with id " + draftBook.getId() +" is deleted");
		}
		
		if(draftBook.getAuthorId()!=authorId) {
			throw new NotAuthorizedException("You are not authorized to view the book draft with id " + draftBook.getId());
		}
		
		return chapterDraftMapper.toDto(chapterDraft);

	}

	@Override
	public String deleteDraftChaptersByIds(List<UUID> chapterId, UUID authorId) {
		
		List<AuthorDraftChapter> chaptersDraft = chapterId.stream()
								.map(
										id -> chapterDraftRepo
										.findById(id)
										.orElseThrow(
												() -> new DraftNotFoundException("Chapter draft with the id " +id+ " is not found")
												))
							.toList();
		Set<UUID> bookDraftIds = chaptersDraft
								.stream()
								.map(AuthorDraftChapter::getDraftBookId)
								.collect(Collectors.toSet());
		
		Map<UUID, AuthorDraftBook> draftBookMaps = bookDraftRepo
				.findAllById(bookDraftIds)
				.stream()
				.collect(Collectors
						.toMap(
								AuthorDraftBook::getId, Function.identity()
								)
						);
		
		chaptersDraft.forEach(c -> {
			AuthorDraftBook draftBook = draftBookMaps.get(c.getDraftBookId());
			
			if(draftBook==null) {
			     throw new DraftNotFoundException(
			                "Draft book " + c.getDraftBookId() + " not found"
			        );
			}
			
			if(draftBook.getIsDeleted()) {
				throw new DeletedException("Book is already deleted for the draft with id " + c.getId());
			}
			

			if(c.getIsDeleted()) {
				throw new DeletedException("Draft for the chapter with id " + c.getId() +" is deleted");
			}
			
			if(!draftBook.getAuthorId().equals(authorId)){
			      throw new NotAuthorizedException(
			                "Chapter " + c.getId() +
			                " does not belong to author " + authorId
			        );
			}
		});
		
		chaptersDraft.stream().forEach(c -> {
			c.setIsDeleted(true);
			c.setStatus(ChapterStatus.DELETED);
			chapterDraftRepo.save(c);
		});
		
		return "The following chaters are deleted successfully";
	}

	
	
	@Override
	public ResponseDraftChapterDto publishDraftedChapters(UUID draftChapterId, UUID authorId) {
		
		AuthorDraftChapter chapterDraft = chapterDraftRepo
				.findById(draftChapterId)
				.orElseThrow(
						() -> new DraftNotFoundException("Chapter draft with the id " +draftChapterId+ " is not found")
						);
		

		if(chapterDraft.getIsDeleted()) {
			throw new DeletedException("Draft for the book with id " + chapterDraft.getId() +" is deleted");
		}
		
		
		AuthorDraftBook draftBook = bookDraftRepo
				.findById(chapterDraft.getDraftBookId())
				.orElseThrow(
						() -> new DraftNotFoundException("book for the chapter of bookId " + chapterDraft.getDraftBookId() + "is not found")
						);
		
		
		if(draftBook.getIsDeleted()) {
			throw new DeletedException("Draft for the book with id " + draftBook.getId() +" is deleted");
		}
		
		if(draftBook.getAuthorId()!=authorId) {
			throw new NotAuthorizedException("You are not authorized to view the book draft with id " + draftBook.getId());
		}
		
		catalogService.createBookContent(null);
	}

}
