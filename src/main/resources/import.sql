INSERT INTO roles (name) VALUES ('ROLE_ADMIN')
INSERT INTO roles (name) VALUES ('ROLE_USER')

INSERT INTO users (create_at, is_active, last_login, modified_at, email, name, password) VALUES (CURRENT_DATE, true, CURRENT_DATE, NULL, 'admin@correo.cl','admin', '$2a$12$g4dOwrQ8YUFGiv76URNGoufNQGGy1Od8BA74orgsI21kBHmU08LZ2')

INSERT INTO users_roles (ROLE_ID, USER_ID) VALUES (1,1)
INSERT INTO users_roles (ROLE_ID, USER_ID) VALUES (2,1)

INSERT INTO phones (city_code, country_code, number) VALUES (1,1,1)
INSERT INTO users_phone_list (phone_list_id, user_id) VALUES (1,1)












