CREATE TABLE public.event_teams (
    event_id bigint NOT NULL,
    team_id bigint NOT NULL,
    team_point int DEFAULT '0',
    CONSTRAINT event_teams_FK FOREIGN KEY (team_id) REFERENCES teams(team_id) ON DELETE cascade,
    CONSTRAINT event_teams_FK_1 FOREIGN KEY (event_id) REFERENCES events(event_id) ON DELETE cascade,
    CONSTRAINT event_teams_pk PRIMARY KEY (event_id,team_id)
);