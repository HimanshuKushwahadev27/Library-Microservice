package com.emi.Authoring_service.ServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.emi.Authoring_service.Repository.DraftBookRepo;
import com.emi.Authoring_service.Repository.DraftChapterRepo;
import com.emi.Authoring_service.RequestDtos.RequestChapterCreateDto;
import com.emi.Authoring_service.RequestDtos.RequestUpdateDraftChapterDto;
import com.emi.Authoring_service.ResponseDtos.ResponseDraftChapterDto;
import com.emi.Authoring_service.clients.CatalogService;
import com.emi.Authoring_service.entity.AuthorDraftBook;
import com.emi.Authoring_service.entity.AuthorDraftChapter;
import com.emi.Authoring_service.enums.BookVisibilityStatus;
import com.emi.Authoring_service.enums.ChapterStatus;
import com.emi.Authoring_service.exceptions.ChapterDraftExistsException;
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
	
	
//	@PreAuthorize("hasRole('AUTHOR')")
	@Transactional
	@Override
	public ResponseDraftChapterDto createChapterDraft(RequestChapterCreateDto request) {
		 
		if(!bookDraftRepo.existsById(request.draftBookId())) {
		throw new  DraftNotFoundException("Book draft with the id " +request.draftBookId()+ " is not found");	
		}
		
		if(chapterDraftRepo.existsByDraftBookIdAndTitle(request.draftBookId(), request.title())) {
			throw new ChapterDraftExistsException("Draft exists with the title "+request.title()+" for the book id "+request.draftBookId());
		}
		
		AuthorDraftChapter draftChapter = chapterDraftMapper.toEntity(request);
		chapterDraftRepo.save(draftChapter);
		
		return chapterDraftMapper.toDto(draftChapter);
	}

//	@PreAuthorize("hasRole('AUTHOR')")
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
		
		if(!draftBook.getAuthorId().equals(authorId)){
			throw new NotAuthorizedException("You are not authorized to make changes in the chapter draft with id " + request.id());
		}
		
		if(chapterDraft.getStatus() == ChapterStatus.PUBLISHED) {
			throw new NotAuthorizedException("you cant update the published chapter of id " + chapterDraft.getId());
		}
		
		chapterDraftMapper.toUpdate(request, chapterDraft);
		chapterDraftRepo.save(chapterDraft);
		
		return chapterDraftMapper.toDto(chapterDraft);
		
	}

//	@PreAuthorize("hasRole('AUTHOR')")
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
		
		if(!draftBook.getAuthorId().equals(authorId)) {
			throw new NotAuthorizedException("You are not authorized to view the book draft with id " + bookId );
		}
		
		return chapters.stream().map(chapterDraftMapper::toDto).toList();
	}

//	@PreAuthorize("hasRole('AUTHOR')")
	@Override
	public List<ResponseDraftChapterDto> getMyDraftChaptersByChapterIds(UUID authorId, Set<UUID> chapterIds){
		return chapterIds.stream().map(id -> this.getMyDraftChapterById(authorId, id)).toList();
	}
	
//	@PreAuthorize("hasRole('AUTHOR')")
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
		
		
		if(!draftBook.getAuthorId().equals(authorId)) {
			throw new NotAuthorizedException("You are not authorized to view the book draft with id " + draftBook.getId());
		}
		
		return chapterDraftMapper.toDto(chapterDraft);

	}

//	@PreAuthorize("hasRole('AUTHOR')")
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
			
			if(c.getStatus() == ChapterStatus.PUBLISHED) {
				throw new NotAuthorizedException("you cant delete the published chapter of id " + c.getId());
			}
			
			if(!draftBook.getAuthorId().equals(authorId)){
			      throw new NotAuthorizedException(
			                "Chapter " + c.getId() +
			                " does not belong to author " + authorId
			        );
			}
		});
		
		chaptersDraft.stream().forEach(c -> {
			chapterDraftRepo.deleteById(c.getId());
		});
		
		return "The following chapters are deleted successfully";
	}

	
//	@PreAuthorize("hasRole('AUTHOR')")
	@Override
	public void publishDraftedChapters(Set<UUID> draftChapterIds, UUID authorId) {
		
		List<AuthorDraftChapter> chapterDrafts =
		        draftChapterIds.stream()
		                .map(id -> chapterDraftRepo.findById(id)
		                        .orElseThrow(() ->
		                                new DraftNotFoundException(
		                                        "Draft chapter with id " + id + " not found"
		                                )))
		                .toList();
		

		
       chapterDrafts.stream().forEach(t -> {
    	 
    	   t.setStatus(ChapterStatus.PUBLISHED);
    	   chapterDraftRepo.save(t);
       });		
		
       Set<UUID> bookId =  chapterDrafts.stream()
               .map(AuthorDraftChapter::getDraftBookId)
               .collect(Collectors.toSet());
		
		if (bookId.size() != 1) {
		   throw new IllegalStateException(
		           "All chapters must belong to the same draft book"
		   );
		}
		
		UUID draftBookId = bookId.iterator().next();
		
			
		AuthorDraftBook  draftBook = bookDraftRepo.findById(draftBookId)
					                .orElseThrow(() ->
					                new DraftNotFoundException(
					                        "Draft book with id " + draftBookId + " not found"
					                ));
		
		if(!draftBook.getAuthorId().equals(authorId)) {
			throw new NotAuthorizedException("You are not authorized to view the book draft with id " + draftBook.getId());
		}
		
		if(!draftBook.getStatusVisible().equals( BookVisibilityStatus.PUBLIC)) {
			throw new NotAuthorizedException("Please first publish the book with id " +draftBook.getId() +" then publish the chapters");
		}
		
		catalogService.createMultipleBookContents(chapterDraftMapper.toPublishChapters(chapterDrafts));
	}

}
