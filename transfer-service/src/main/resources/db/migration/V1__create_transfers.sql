CREATE TABLE transfers (
    id UUID PRIMARY KEY,
    sender_agency VARCHAR(255) NOT NULL,
    sender_account_number VARCHAR(255) NOT NULL,
    receiver_agency VARCHAR(255) NOT NULL,
    receiver_account_number VARCHAR(255) NOT NULL,
    amount NUMERIC(38, 2) NOT NULL,
    status VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);