package api.carreras.events.application;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import api.carreras.events.domain.Builder;
import api.carreras.events.domain.Event;
import api.carreras.events.domain.EventRepository;
import api.carreras.events.domain.request.AddEventRequest;
import api.carreras.events.domain.request.UpdateEventRequest;
import api.carreras.events.domain.response.EventResponse;
import api.carreras.shared.domain.Logger;
import api.carreras.shared.domain.exception.ServiceException;
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

        PaginationResponse result = Builder.set(PaginationResponse.class)
            .with(p -> p.setData(content))
            .with(p -> p.setPage((short) events.getNumber()))
            .with(p -> p.setLimit((byte) events.getSize()))
            .with(p -> p.setTotalItems((short) events.getTotalElements()))
            .with(p -> p.setTotalPages((short) events.getTotalPages()))
            .with(p -> p.setLast(events.isLast()))
            .build();

        return OnResponse.onSuccessPagination(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addEvent(AddEventRequest data) throws Exception {

        Event event = Builder.set(Event.class)
            .with(p -> p.setEventDescription(data.getEventDescription()))
            .with(p -> p.setEventLocation(data.getEventLocation()))
            .with(p -> p.setEventDate(data.getEventDate()))
            .build();

        event = eventRepository.save(event);
        
        if (event == null) {
            throw new ServiceException("The event location and description are already registered");
        }
        
        return OnResponse.onSuccess(event, HttpStatus.CREATED);
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

        return Builder.set(EventResponse.class)
            .with(e -> e.setEventId(event.getEventId()))
            .with(e -> e.setEventDescription(event.getEventDescription()))
            .with(e -> e.setEventLocation(event.getEventLocation()))
            .with(e -> e.setEventDate(event.getEventDate()))
            .build();
    }

    private Event mapToEvent(EventResponse eventDto) {

        return Builder.set(Event.class)
            .with(p -> p.setEventDescription(eventDto.getEventDescription()))
            .with(p -> p.setEventLocation(eventDto.getEventLocation()))
            .with(p -> p.setEventDate(eventDto.getEventDate()))
            .build();
    }

}
