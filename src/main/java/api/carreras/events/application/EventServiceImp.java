package api.carreras.events.application;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import api.carreras.events.domain.Event;
import api.carreras.events.domain.EventRepository;
import api.carreras.events.domain.request.AddEventRequest;
import api.carreras.events.domain.request.UpdateEventRequest;
import api.carreras.events.domain.response.EventResponse;
import api.carreras.shared.domain.response.OnResponse;
import api.carreras.shared.domain.response.PaginationResponse;

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
    public ResponseEntity<?> getEventList(Short page, Byte limit, String sortBy, String sortDir) {

        Sort sort = Sort.by(sortBy);
        sort = (sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())) ? sort.ascending() : sort.descending();

        Pageable pageable = PageRequest.of(page, limit, sort);

        Page<Event> events = eventRepository.findAll(pageable);

        List<Event> eventList = events.getContent();
        List<EventResponse> content = eventList
            .stream()
            .map(x -> mapToEventDto(x))
            .toList();

        PaginationResponse<?> result = PaginationResponse.build(
            content,
            (short) events.getNumber(),
            (byte) events.getSize(),
            (short) events.getTotalElements(),
            (short) events.getTotalPages(),
            events.isLast()
        );

        return OnResponse.onSuccess(result, HttpStatus.OK);
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

    private EventResponse mapToEventDto(Event event) {

        return EventResponse.build(
            event.getEventId(),
            event.getEventDescription(),
            event.getEventLocation(),
            event.getEventDate()
        );
    }

    private Event mapToEvent(EventResponse eventDto) {

        return Event.build(
            null,
            eventDto.getEventDescription(),
            eventDto.getEventLocation(),
            eventDto.getEventDate()
        );
    }

}
