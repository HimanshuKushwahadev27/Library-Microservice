CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE authors (

    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    keycloak_id UUID NOT NULL UNIQUE,

    pen_name VARCHAR(255) NOT NULL,

    bio VARCHAR(2000),

    profile_image_url VARCHAR(500),

    verified BOOLEAN NOT NULL DEFAULT FALSE,

    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);