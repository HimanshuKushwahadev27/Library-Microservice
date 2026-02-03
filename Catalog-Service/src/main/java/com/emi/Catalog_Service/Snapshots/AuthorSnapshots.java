package com.emi.Catalog_Service.Snapshots;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@Embeddable
@NoArgsConstructor
public class AuthorSnapshots {

	@Column(name="author_id" , nullable = false)
	private UUID id;
	
	@Column(name="author_name" , nullable = false)
	private String name;
	
}
