package api.carreras.events.infrastructure.persistence.postgresql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import api.carreras.events.domain.Event;
import api.carreras.events.infrastructure.DomainPersistence;
import api.carreras.shared.domain.Logger;
import api.carreras.shared.domain.exception.RepositoryException;

@Component
public class EventReposirotyImpl implements DomainPersistence {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Page<Event> findAll(Pageable pageable) {

        return eventRepository.findAll(pageable);
    }

    @Override
    public Event findById(Long eventId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Event findByLocation(String eventLocation) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Event save(Event event) throws Exception {
        try {

            return eventRepository.save(event);
        } catch (Exception e) {
            Logger.log(e.getMessage());

            if (e.getMessage().contains("constraint [events_un]")) {
                return null;
            }

            throw new RepositoryException("Internal Server Error");
        }
    }

    @Override
    public void delete(Event event) throws Exception {
        // TODO Auto-generated method stub

    }

}
