package com.emi.Catalog_Service.Services;

import java.util.List;
import java.util.UUID;


import com.emi.Catalog_Service.RequestDtos.RequestBookCreationDto;
import com.emi.Catalog_Service.RequestDtos.RequsestBookUpdateDto;
import com.emi.Catalog_Service.ResponseDtos.ResponseBookDto;
import com.emi.Catalog_Service.ResponseDtos.ResponseFullBookDto;

public interface BookService {

	public UUID createBook(RequestBookCreationDto requestDto); 
	public ResponseFullBookDto getBookById(UUID bookId);
	public List<ResponseFullBookDto> getBookByIds(List<UUID> bookIds);
	public String deleteBook(UUID bookId, UUID authorId);
	ResponseBookDto updateBook(RequsestBookUpdateDto requestDto, UUID authorId);
	
}
