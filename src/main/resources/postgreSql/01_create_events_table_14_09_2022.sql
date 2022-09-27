CREATE TABLE public.events (
    event_id bigint NOT NULL,
    event_description varchar(250) NOT NULL,
    event_location varchar(250) NOT NULL,
    event_date timestamp NOT NULL,
    CONSTRAINT events_pk PRIMARY KEY (event_id)
);