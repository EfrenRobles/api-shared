package api.carreras.events.application;

import org.springframework.http.ResponseEntity;

import api.carreras.events.domain.Event;
import api.carreras.events.domain.EventRepository;
import api.carreras.events.domain.request.AddEventRequest;
import api.carreras.events.domain.request.UpdateEventRequest;

public class EventServiceImp implements EventService {

    private EventRepository eventRepository;

    public EventServiceImp(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public static EventServiceImp build(EventRepository eventRepository) {
        return new EventServiceImp(eventRepository);
    }

    @Override
    public ResponseEntity<?> getEventById(Long eventId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<?> getEventByLocation(String eventLocation) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<?> getEventList() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<?> addEvent(AddEventRequest data) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<?> updateEvent(Long eventId, UpdateEventRequest data) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<?> deleteEvent(Long eventId) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <E> E mapToEventDto(Event events, E eventDto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <E> Event mapToEvents(E eventDto, Event events) {
        // TODO Auto-generated method stub
        return null;
    }

}
