CREATE TABLE IF NOT EXISTS credential
(
    username character varying(255) NOT NULL,
    activated boolean NOT NULL,
    activationkey character varying(100),
    email character varying(50),
    password character varying(500),
    resetpasswordkey character varying(100),
    CONSTRAINT credential_pkey PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS authority
(
    name character varying(50) NOT NULL,
    CONSTRAINT authority_pkey PRIMARY KEY (name)
);

CREATE TABLE IF NOT EXISTS credential_authority
(
    username character varying(255) NOT NULL,
    authority character varying(255) NOT NULL,
    CONSTRAINT credential_authority_pkey PRIMARY KEY (username, authority),
    CONSTRAINT fk3odsunh03vw9546adkrvdfp79 FOREIGN KEY (authority)
        REFERENCES authority (name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk9f17q8dfno1haay7o6k0516r FOREIGN KEY (username)
        REFERENCES credential (username) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS oauth_access_token (
  token_id VARCHAR(256) DEFAULT NULL,
  token BYTEA,
  authentication_id VARCHAR(256) DEFAULT NULL,
  user_name VARCHAR(256) DEFAULT NULL,
  client_id VARCHAR(256) DEFAULT NULL,
  authentication BYTEA,
  refresh_token VARCHAR(256) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS oauth_refresh_token (
  token_id VARCHAR(256) DEFAULT NULL,
  token BYTEA,
  authentication BYTEA
);

CREATE TABLE IF NOT EXISTS  oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);

