CREATE TABLE accounts (
    id UUID PRIMARY KEY,
    agency VARCHAR(255) NOT NULL,
    account_number VARCHAR(255) NOT NULL,
    balance NUMERIC(38, 2) NOT NULL,
    status VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);