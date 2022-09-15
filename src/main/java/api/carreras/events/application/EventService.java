package api.carreras.events.application;

import org.springframework.http.ResponseEntity;

import api.carreras.events.domain.Event;
import api.carreras.events.domain.EventRepository;
import api.carreras.events.domain.request.AddEventRequest;
import api.carreras.events.domain.request.UpdateEventRequest;

public interface EventService {

    public ResponseEntity<?> getEventById(Long eventId);

    public ResponseEntity<?> getEventByLocation(String eventLocation);

    public ResponseEntity<?> getEventList();

    public ResponseEntity<?> addEvent(AddEventRequest data) throws Exception;

    public ResponseEntity<?> updateEvent(Long eventId, UpdateEventRequest data) throws Exception;

    public ResponseEntity<?> deleteEvent(Long eventId) throws Exception;

    <E> E mapToEventDto(Event events, E eventDto);

    <E> Event mapToEvents(E eventDto, Event events);
}
