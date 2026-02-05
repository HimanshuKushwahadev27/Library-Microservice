package com.emi.Catalog_Service.mapper;

import org.springframework.stereotype.Component;

import com.emi.Catalog_Service.Entity.Book_Content;
import com.emi.Catalog_Service.RequestDtos.RequestCreateContentDto;
import com.emi.Catalog_Service.ResponseDtos.ResponseContentDto;

@Component
public class ContentMapper {

	public Book_Content toEntity(RequestCreateContentDto createContentDto) {
		
		Book_Content content = new Book_Content();
		content.setContent(createContentDto.content());
		content.setTitle(createContentDto.title());
		content.setBookId(createContentDto.bookId());
		content.setChapterNumber(createContentDto.chapterNumber());
		content.setPrice(createContentDto.price());
		content.setFreePreview(createContentDto.freePreview());
		content.setStatus(com.emi.Catalog_Service.enums.BookChapter_Status.PUBLISHED);
		content.setCreatedAt(java.time.Instant.now());
		content.setUpdatedAt(java.time.Instant.now());
		
		return content;
	}
	   
	public ResponseContentDto toDto(Book_Content content) {
		return new ResponseContentDto(
				content.getId(),
				content.getBookId(),
				content.getTitle(),
				content.getChapterNumber(),
				content.getPrice(),
				content.getContent(),
				content.getFreePreview(),
				content.getCreatedAt(),
				content.getUpdatedAt(),
				content.getStatus().toString()
		);
	}

}
