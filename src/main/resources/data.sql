INSERT INTO devtest.client_credentials(id, api_key, user_id)
VALUES (1, '5c8d56a2-88b2-48a7-aec8-c9a8ac7af99a', 1) ON CONFLICT ON CONSTRAINT client_credentials_pkey DO NOTHING;;