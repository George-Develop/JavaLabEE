CREATE TABLE roles (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255),
                       can_edit_movies BOOLEAN
);
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       first_name VARCHAR(255),
                       last_name VARCHAR(255),
                       phone VARCHAR(20),
                       email VARCHAR(255),
                       role_id BIGINT,
                       FOREIGN KEY (role_id) REFERENCES roles(id)
);
CREATE TABLE movies (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        title VARCHAR(255),
                        genre VARCHAR(255),
                        release_year INT,
                        description VARCHAR(1000),
                        added_by BIGINT,
                        FOREIGN KEY (added_by) REFERENCES users(id)
);
