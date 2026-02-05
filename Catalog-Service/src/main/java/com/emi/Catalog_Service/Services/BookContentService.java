package com.emi.Catalog_Service.Services;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.emi.Catalog_Service.RequestDtos.RequestCreateContentDto;
import com.emi.Catalog_Service.ResponseDtos.ResponseContentDto;


public interface BookContentService {

	public ResponseContentDto createBookContent(RequestCreateContentDto createContentDto);
	
	public List<ResponseContentDto> createMultipleBookContents(List<RequestCreateContentDto> createContentDto);

	public ResponseContentDto getBookContentByContentId(UUID contentId);
	
	public List<ResponseContentDto> getBookContentsByContentIds(List<UUID> contentIds);
	
	public List<ResponseContentDto> getBookContentByBookId(UUID bookId);

    public ResponseEntity<?> deleteBookContentByContentId(UUID contentId);
    
    public ResponseEntity<?> deleteBookContentsByContentIds(List<UUID> contentIds);
    
    public ResponseEntity<?> deleteBookContentByBookId(UUID bookId);

}
