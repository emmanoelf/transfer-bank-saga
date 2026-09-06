ALTER TABLE accounts
ADD CONSTRAINT uk_accounts_agency_account_number
UNIQUE (agency, account_number);