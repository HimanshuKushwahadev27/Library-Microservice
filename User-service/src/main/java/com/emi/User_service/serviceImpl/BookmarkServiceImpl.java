package com.emi.User_service.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.emi.User_service.entity.Bookmark;
import com.emi.User_service.exception.BookExistsInLibraryException;
import com.emi.User_service.exception.BookmarkException;
import com.emi.User_service.mapper.BookmarkMapper;
import com.emi.User_service.repository.BookmarkRepo;
import com.emi.User_service.requestDto.RequestBookmarkDto;
import com.emi.User_service.responseDto.ResponseBookmarkDto;
import com.emi.User_service.service.BookMarkService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookMarkService {

	private final BookmarkMapper bookmarkMapper;
	private final BookmarkRepo bookmarkRepo;
	
	@Override
	public ResponseBookmarkDto create(RequestBookmarkDto request, UUID keycloakId) {
		
		if (bookmarkRepo.existsByChapterIdAndKeycloakId(request.chapterId(), keycloakId)) {
			throw new BookmarkException(
					"bookmark with chapter id " + request.chapterId() + " already exists in user's library");
		}

		Bookmark mark = bookmarkMapper.toEntity(request, keycloakId);
		bookmarkRepo.save(mark);
		return bookmarkMapper.toDto(mark);
	}

	@Override
	public List<ResponseBookmarkDto> getAll(UUID keycloakId) {

		if (!bookmarkRepo.existsByKeycloakId(keycloakId)) {
			throw new BookExistsInLibraryException("you havent purchased any book yet");
		}

		List<Bookmark> marks = bookmarkRepo.findByKeycloakId(keycloakId);

		return marks.stream().map(bookmarkMapper::toDto).toList();
	}

	@Override
	public String delete(UUID id, UUID keycloakId) {
		
		if (bookmarkRepo.existsByIdAndKeycloakId(id, keycloakId)) {
			throw new BookExistsInLibraryException(
					"bookmark with id " + id + "is not found for the given user");
		}
		
		bookmarkRepo.deleteById(id);
		
		return "Bookmark in the user's library with the id " +id+ " is removed";
		
	}

}
