package api.carreras.events.domain;

import java.util.List;

public interface EventRepository {

    public List<Event> findAll();

    public Event findById(Long eventId);

    public Event findByLocation(String eventLocation);

    public Event save(Event event) throws Exception;

    public void delete(Event event) throws Exception;
}
