CREATE TABLE public.drivers (
    driver_id bigint NOT NULL,
    driver_name varchar(250) DEFAULT NULL,
    CONSTRAINT drivers_pk PRIMARY KEY (driver_id)
);