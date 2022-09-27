package api.carreras.events.application;

import org.springframework.http.ResponseEntity;

import api.carreras.events.domain.Event;
import api.carreras.events.domain.EventRepository;
import api.carreras.events.domain.request.AddEventRequest;
import api.carreras.events.domain.request.UpdateEventRequest;
import api.carreras.events.domain.response.EventResponse;

public interface EventService {

    public ResponseEntity<?> getEventByEventId(Long eventId) throws Exception;

    public ResponseEntity<?> getEventList(Short page, Byte limit, String sortBy, String sortDir, EventResponse request);

    public ResponseEntity<?> addEvent(AddEventRequest data) throws Exception;

    public ResponseEntity<?> updateEvent(UpdateEventRequest data) throws Exception;

    public ResponseEntity<?> deleteEvent(Long eventId) throws Exception;
}
