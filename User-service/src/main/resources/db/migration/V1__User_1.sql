-- Enable UUID generation
CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE user_profiles (

    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    keycloak_id UUID NOT NULL UNIQUE,
    display_name VARCHAR(255),
    profile_image_url VARCHAR(500),
    bio VARCHAR(1000),
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);


CREATE TABLE user_library (

    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    keycloak_id UUID NOT NULL,
    book_id UUID NOT NULL,
    purchased_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT uq_user_library UNIQUE (keycloak_id, book_id)
);

CREATE TABLE reviews (

    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    keycloak_id UUID NOT NULL,
    user_id UUID NOT NULL,
    book_id UUID NOT NULL,
    rating INTEGER NOT NULL CHECK (rating BETWEEN 1 AND 5),
    comment VARCHAR(2000),
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT uq_reviews UNIQUE (keycloak_id, book_id)
);


CREATE TABLE bookmarks (

    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    keycloak_id UUID NOT NULL,
    book_id UUID NOT NULL,
    chapter_id UUID NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT uq_bookmarks UNIQUE (keycloak_id, book_id, chapter_id)
);


