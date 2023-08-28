CREATE TABLE customers(

                          customer_id			BIGSERIAL NOT NULL,
                          first_name			VARCHAR(30) not null,
                          last_name			    VARCHAR(30)	not null,
                          age					BIGSERIAL not null,
                          gender				VARCHAR(10),
                          transaction_date	    date default CURRENT_DATE,
                          car_id				BIGINT NOT NULL

);

ALTER TABLE customers ADD CONSTRAINT customer_id_pk PRIMARY KEY (customer_id);

ALTER TABLE customers
    ADD CONSTRAINT customer_car_fk FOREIGN KEY (car_id)
        REFERENCES cars (id);