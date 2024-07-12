CREATE TABLE traffic_schema.applicants (
    id VARCHAR(36) PRIMARY KEY,
    user_id VARCHAR(36) NOT NULL,
    dob DATE,
    remarks TEXT,
    CONSTRAINT fk_user
        FOREIGN KEY(user_id) 
            REFERENCES traffic_schema.users(id)
            ON DELETE CASCADE
);

CREATE TABLE traffic_schema.applications (
    id VARCHAR(36) PRIMARY KEY,
    applicant_id VARCHAR(36) NOT NULL,
    license_type VARCHAR(255),
    status VARCHAR(255),
    application_date DATE,
    remarks TEXT,
    CONSTRAINT fk_applicant
        FOREIGN KEY(applicant_id) 
            REFERENCES traffic_schema.applicants(id)
            ON DELETE CASCADE
);

CREATE TABLE traffic_schema.eye_test_results (
    id VARCHAR(36) PRIMARY KEY,
    applicant_id VARCHAR(36) NOT NULL,
    test_date DATE,
    result VARCHAR(255),
    notes TEXT,
    CONSTRAINT fk_applicant
        FOREIGN KEY(applicant_id) 
            REFERENCES traffic_schema.applicants(id)
            ON DELETE CASCADE
);

CREATE TABLE traffic_schema.supervisors (
    id VARCHAR(36) PRIMARY KEY,
    user_id VARCHAR(36) NOT NULL,
    department VARCHAR(255),
    designation VARCHAR(255),
    CONSTRAINT fk_user
        FOREIGN KEY(user_id) 
            REFERENCES traffic_schema.users(id)
            ON DELETE CASCADE
);

UPDATE traffic_schema.roles
	SET name='ROLE_USER'
	WHERE name='ROLE_ADMIN';

UPDATE traffic_schema.roles
	SET name='ROLE_MODERATOR'
	WHERE name='ROLE_OWNER';

UPDATE traffic_schema.roles
	SET name='ROLE_ADMIN'
	WHERE name='ROLE_SUPER_ADMIN';

DELETE FROM traffic_schema.roles
    WHERE name = 'ROLE_CONTRIBUTOR'
