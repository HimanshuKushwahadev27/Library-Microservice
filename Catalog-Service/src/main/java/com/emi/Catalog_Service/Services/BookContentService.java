package com.emi.Catalog_Service.Services;

import java.util.List;
import java.util.UUID;

import com.emi.Catalog_Service.RequestDtos.RequestCreateContentDto;
import com.emi.Catalog_Service.ResponseDtos.ResponseContentDto;


public interface BookContentService {

	public ResponseContentDto createBookContent(RequestCreateContentDto createContentDto);
	
	public List<ResponseContentDto> createMultipleBookContents(List<RequestCreateContentDto> createContentDto);

	public ResponseContentDto getBookContentByContentId(UUID contentId);
	
	public List<ResponseContentDto> getBookContentsByContentIds(List<UUID> contentIds);
	
	public List<ResponseContentDto> getBookContentByBookId(UUID bookId);

    public String deleteBookContentByContentId(UUID contentId, UUID authorId);
    
    public String deleteBookContentsByContentIds(List<UUID> contentIds, UUID authorId);
    
    public String deleteBookContentByBookId(UUID bookId, UUID authorId);

}
