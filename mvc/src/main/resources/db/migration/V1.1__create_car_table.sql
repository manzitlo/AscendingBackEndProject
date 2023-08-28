CREATE TABLE cars(
                     id					BIGSERIAL NOT NULL,
                     brand				VARCHAR(20) not null,
                     year				BIGSERIAL not null,
                     model				VARCHAR(10) not null,
                     color				VARCHAR(10),
                     insurance_id 		BIGINT NOT NULL
);

ALTER TABLE cars ADD CONSTRAINT car_pk PRIMARY KEY (id);

ALTER TABLE cars
    ADD CONSTRAINT cars_insurances_fk FOREIGN KEY (insurance_id)
        REFERENCES insurances (id);