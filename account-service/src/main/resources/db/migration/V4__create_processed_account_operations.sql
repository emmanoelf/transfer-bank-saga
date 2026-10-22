CREATE TABLE processed_account_operations (
    id UUID PRIMARY KEY,
    transfer_id UUID NOT NULL,
    operation_type VARCHAR(10) NOT NULL,
    agency VARCHAR(10) NOT NULL,
    account_number VARCHAR(30) NOT NULL,
    amount NUMERIC(19, 2) NOT NULL,
    processed_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_account_operation_type
        CHECK (operation_type IN ('DEBIT', 'CREDIT')),

    CONSTRAINT chk_account_operation_amount
        CHECK (amount > 0),

    CONSTRAINT uk_account_operation_transfer_type
        UNIQUE (transfer_id, operation_type)
);