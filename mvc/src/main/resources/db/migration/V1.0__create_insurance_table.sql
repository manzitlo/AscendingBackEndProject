CREATE TABLE insurances(
                   id					BIGSERIAL NOT NULL,
                   company_name		    VARCHAR(20) not null,
                   sepcifications		VARCHAR(30) not null
);

ALTER TABLE insurances ADD CONSTRAINT insurance_pk PRIMARY KEY (id);