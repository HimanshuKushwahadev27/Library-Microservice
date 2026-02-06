--Index(Draft_books)
CREATE INDEX idx_draft_books_author_id
ON draft_books(author_id);

CREATE INDEX idx_draft_books_catalog_book_id
ON draft_books(catalog_book_id);

CREATE UNIQUE INDEX uq_draft_books_isbn
ON draft_books(isbn);



--Index(Deaft_chapters)
CREATE INDEX idx_draft_chapters_draft_book_id
ON draft_chapters(draft_book_id);

CREATE INDEX idx_draft_chapters_book_order
ON draft_chapters(draft_book_id, chapter_order);

CREATE INDEX idx_draft_chapters_status
ON draft_chapters(status);