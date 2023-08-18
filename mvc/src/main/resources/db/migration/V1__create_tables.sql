CREATE TABLE insurances(
                   id					BIGSERIAL NOT NULL,
                   company_name		    VARCHAR(20) not null,
                   sepcifications		VARCHAR(30) not null
);

ALTER TABLE insurances ADD CONSTRAINT insurance_pk PRIMARY KEY (id);

CREATE TABLE cars(
                     id					BIGSERIAL NOT NULL,
                     brand				VARCHAR(20) not null,
                     year				BIGSERIAL not null,
                     model				VARCHAR(10) not null,
                     color				VARCHAR(10),
                     insurance_id 		BIGSERIAL NOT NULL
);

ALTER TABLE cars ADD CONSTRAINT car_pk PRIMARY KEY (id);

ALTER TABLE cars
    ADD CONSTRAINT cars_insurances_fk FOREIGN KEY (insurance_id)
        REFERENCES insurances (id);

CREATE TABLE customers(

                      customer_id			BIGSERIAL NOT NULL,
                      first_name			VARCHAR(30) not null,
                      last_name			    VARCHAR(30)	not null,
                      age					BIGSERIAL not null,
                      gender				VARCHAR(10),
                      transaction_date	    date default CURRENT_DATE,
                      car_id				BIGSERIAL NOT NULL

);

ALTER TABLE customers ADD CONSTRAINT customer_id_pk PRIMARY KEY (customer_id);

ALTER TABLE customers
    ADD CONSTRAINT customer_car_fk FOREIGN KEY (car_id)
        REFERENCES cars (id);