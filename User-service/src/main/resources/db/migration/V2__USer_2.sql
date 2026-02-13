--User_lib
CREATE INDEX idx_user_library_keycloak_id ON user_library(keycloak_id);
CREATE INDEX idx_user_library_book_id ON user_library(book_id);

--reviews
CREATE INDEX idx_reviews_book_id ON reviews(book_id);
CREATE INDEX idx_reviews_keycloak_id ON reviews(keycloak_id);
CREATE INDEX idx_reviews_user_id ON reviews(user_id);


--bookmark
CREATE INDEX idx_bookmarks_keycloak_id ON bookmarks(keycloak_id);
CREATE INDEX idx_bookmarks_book_id ON bookmarks(book_id);
CREATE INDEX idx_bookmarks_chapter_id ON bookmarks(chapter_id);

--user
CREATE INDEX idx_user_profiles_keycloak_id ON user_profiles(keycloak_id);
CREATE INDEX idx_user_profiles_is_deleted ON user_profiles(is_deleted);