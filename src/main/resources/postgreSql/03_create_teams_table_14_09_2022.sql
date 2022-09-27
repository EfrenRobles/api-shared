CREATE TABLE public.teams (
    team_id bigint NOT NULL,
    team_name varchar(250) DEFAULT NULL,
    CONSTRAINT teams_pk PRIMARY KEY (team_id)
);