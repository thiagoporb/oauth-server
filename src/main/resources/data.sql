-- Credential
INSERT INTO credential (username, email, password, activated)
SELECT * FROM (SELECT 'admin', 'admin@admin.com', '$2a$10$r0RFDmpneBVryx.ihHK9gu6FFJQi4nTxQUqzdSTvrPpaKZMxigqpy', true) AS tmp
WHERE NOT EXISTS (
    SELECT username FROM credential WHERE username = 'admin'
) LIMIT 1;

-- Authority
INSERT INTO authority (name)
SELECT * FROM (SELECT 'ROLE_USER') AS tmp
WHERE NOT EXISTS (
    SELECT name FROM authority WHERE name = 'ROLE_USER'
) LIMIT 1;
INSERT INTO authority (name)
SELECT * FROM (SELECT 'ROLE_ADMIN') AS tmp
WHERE NOT EXISTS (
    SELECT name FROM authority WHERE name = 'ROLE_ADMIN'
) LIMIT 1;

-- Credential Authority
INSERT INTO credential_authority (username, authority)
SELECT * FROM (SELECT 'admin', 'ROLE_USER') AS tmp
WHERE NOT EXISTS (
    SELECT username, authority FROM credential_authority WHERE username = 'admin' and authority = 'ROLE_USER'
) LIMIT 1;
INSERT INTO credential_authority (username, authority)
SELECT * FROM (SELECT 'admin', 'ROLE_ADMIN') AS tmp
WHERE NOT EXISTS (
    SELECT username, authority FROM credential_authority WHERE username = 'admin' and authority = 'ROLE_ADMIN'
) LIMIT 1;

-- Client Details
--INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
--SELECT * FROM (SELECT 'exemplo-app', 'resources', '{bcrypt}$2y$12$3K99s54P0eJHO.OIByNoA.eiLwK73DWINFG4TYbemcJfaj8zFgLne', 'read,write', 'password,refresh_token,client_credentials', '', 'ROLE_USER,ROLE_ADMIN', 1800, null, '{}', '') AS tmp
--WHERE NOT EXISTS (
--    SELECT client_id FROM oauth_client_details WHERE client_id = 'exemplo-app'
--) LIMIT 1;