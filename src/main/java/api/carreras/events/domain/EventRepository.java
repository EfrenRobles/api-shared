package api.carreras.events.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventRepository {

    public Page<Event> findAll(Pageable pageable);

    public Event findById(Long eventId);

    public Event findByLocation(String eventLocation);

    public Event save(Event event) throws Exception;

    public void delete(Event event) throws Exception;
}
