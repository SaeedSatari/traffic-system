SET search_path TO traffic_schema;

-- Create the roles table
CREATE TABLE IF NOT EXISTS roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

INSERT INTO roles (name) VALUES ('ROLE_SUPER_ADMIN'), ('ROLE_OWNER'), ('ROLE_ADMIN'), ('ROLE_CONTRIBUTOR');

CREATE TABLE users (
    id VARCHAR(36) PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(120) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    verification_token VARCHAR(255),
    is_verified BOOLEAN NOT NULL,
    address VARCHAR(255),
    apartment VARCHAR(255),
    country VARCHAR(255),
    phone VARCHAR(20),
    state VARCHAR(255),
    city VARCHAR(255),
    postcode VARCHAR(20)
);

CREATE TABLE user_roles (
    user_id VARCHAR(36),
    role_id INT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);