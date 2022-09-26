package api.carreras.events.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import api.carreras.events.domain.response.EventResponse;

public interface EventRepository {

    public Page<Event> findAll(Pageable pageable, Event request);

    public Event findByEventId(Long eventId);

    public Event save(Event event) throws Exception;

    public void delete(Event event) throws Exception;
}
