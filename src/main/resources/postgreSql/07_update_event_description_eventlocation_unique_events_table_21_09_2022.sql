ALTER TABLE events ADD CONSTRAINT events_un UNIQUE (event_description,event_location);