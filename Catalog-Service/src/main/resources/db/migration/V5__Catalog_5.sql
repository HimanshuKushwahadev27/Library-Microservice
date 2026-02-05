ALTER TABLE book_contents 
	DROP COLUMN free_access,
	ADD COLUMN is_deleted BOOLEAN;