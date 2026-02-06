CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE draft_books(
	
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	author_id UUID NOT NULL,
	catalog_book_id UUID,
	title VARCHAR(255) NOT NULL,
	description VARCHAR(2000) NOT NULL,
	isbn VARCHAR(15) NOT NULL,
	price NUMERIC(10,2) NOT NULL,
	free_preview BOOLEAN NOT NULL DEFAULT TRUE,
    status VARCHAR(50) NOT NULL DEFAULT 'ONGOING',
	created_at TIMESTAMPTZ  NOT NULL DEFAULT now(),
	updated_at TIMESTAMPTZ  NOT NULL DEFAULT now()
	
);


CREATE TABLE draft_chapters(
	
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	draft_book_id UUID NOT NULL,
	title VARCHAR(255) NOT NULL,
	chapter_order INT NOT NULL,
	price NUMERIC(10,2) NOT NULL,
	free_preview BOOLEAN NOT NULL,
	content TEXT NOT NULL,

    created_at TIMESTAMPTZ  NOT NULL DEFAULT now(),
	updated_at TIMESTAMPTZ  NOT NULL DEFAULT now(),
    status VARCHAR(50) NOT NULL DEFAULT 'HIDDEN',
    
    
	    CONSTRAINT fk_draft_chapter_book
	    FOREIGN KEY (draft_book_id)
	    REFERENCES draft_books(id)
	    ON DELETE CASCADE
);






