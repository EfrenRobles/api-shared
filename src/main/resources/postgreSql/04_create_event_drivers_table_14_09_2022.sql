CREATE TABLE public.event_drivers (
    event_id bigint NOT NULL,
    driver_id bigint NOT NULL,
    driver_point int DEFAULT '0',
    CONSTRAINT event_drivers_FK FOREIGN KEY (driver_id) REFERENCES drivers(driver_id) ON DELETE cascade,
    CONSTRAINT event_drivers_FK_1 FOREIGN KEY (event_id) REFERENCES events(event_id) ON DELETE cascade,
    CONSTRAINT event_drivers_pk PRIMARY KEY (event_id,driver_id)
);