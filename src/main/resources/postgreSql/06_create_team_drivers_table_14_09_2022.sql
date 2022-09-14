CREATE TABLE public.team_drivers (
    team_id bigint NOT NULL,
    driver_id bigint NOT NULL,
    CONSTRAINT team_drivers_pk PRIMARY KEY (driver_id,team_id),
    CONSTRAINT team_drivers_FK FOREIGN KEY (driver_id) REFERENCES drivers(driver_id) ON DELETE CASCADE,
    CONSTRAINT team_drivers_FK_1 FOREIGN KEY (team_id) REFERENCES teams(team_id) ON DELETE CASCADE
);