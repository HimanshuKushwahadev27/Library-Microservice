package com.emi.Catalog_Service.mapper;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.emi.Catalog_Service.Entity.Book;
import com.emi.Catalog_Service.Snapshots.GenreSnapshot;

@Component
public class GenreSnapshotMapper {

	public GenreSnapshot giveSnapshotFromGenre(UUID id, String name) {
		return GenreSnapshot.builder()
				.id(id)
				.name(name)
				.build();
	}
	
	public void setGenreSnapshot(Book book , Map<UUID,String> genreInfo) {
		book.setGenreIds(genreInfo.entrySet().stream()
				.map(entry -> giveSnapshotFromGenre(entry.getKey(), entry.getValue()))
				.collect(Collectors.toSet())
				);
	}

}
