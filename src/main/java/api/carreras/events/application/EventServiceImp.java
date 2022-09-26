package api.carreras.events.application;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import api.carreras.events.domain.Event;
import api.carreras.events.domain.EventRepository;
import api.carreras.events.domain.request.AddEventRequest;
import api.carreras.events.domain.request.UpdateEventRequest;
import api.carreras.events.domain.response.EventResponse;
import api.carreras.shared.domain.Builder;
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
    public ResponseEntity<?> getEventByEventId(Long eventId) throws Exception {

        Event event = eventRepository.findByEventId(eventId);

        if (event == null) {
            throw new ServiceException("Event not found");
        }

        return OnResponse.onSuccess(mapToEventDto(event), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getEventList(
        Short page,
        Byte limit,
        String sortBy,
        String sortDir,
        EventResponse request
    ) {

        Event event = Builder.set(Event.class)
            .with(e -> e.setEventId(request.getEventId()))
            .with(e -> e.setEventLocation(request.getEventLocation()))
            .with(e -> e.setEventDescription(request.getEventDescription()))
            .with(e -> e.setEventDate(request.getEventDate()))
            .build();

        Sort sort = Sort.by(sortBy);

        if (sortDir.equalsIgnoreCase("DESC")) {
            sort = Sort.by(Direction.DESC, sortBy);
        }

        Pageable pageable = PageRequest.of(page, limit, sort);
        Page<Event> events = eventRepository.findAll(pageable, event);

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

        return OnResponse.onSuccess(mapToEventDto(event), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> updateEvent(UpdateEventRequest data) throws Exception {

        Event event = eventRepository.findByEventId(data.getEventId());
        Boolean needUpdate = false;

        if (event == null) {
            throw new ServiceException("Event not found");
        }

        if (data.getEventLocation() != null && !data.getEventLocation().equalsIgnoreCase(event.getEventLocation())) {
            event.setEventLocation(data.getEventLocation());
            needUpdate = true;
        }

        if (data.getEventDescription() != null &&
            !data.getEventDescription().equalsIgnoreCase(event.getEventDescription())) {
            event.setEventDescription(data.getEventDescription());
            needUpdate = true;
        }

        if (data.getEventDate() != null && !data.getEventDate().equals(event.getEventDate())) {

            event.setEventDate(data.getEventDate());
            needUpdate = true;
        }

        if (!needUpdate) {
            return OnResponse.onSuccess(mapToEventDto(event), HttpStatus.OK);
        }

        event = eventRepository.save(event);

        if (event == null) {
            throw new ServiceException("The event location and description are already registered");
        }

        return OnResponse.onSuccess(mapToEventDto(event), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteEvent(Long eventId) throws Exception {

        Event event = eventRepository.findByEventId(eventId);

        if (event == null) {
            throw new ServiceException("Event not found");
        }

        eventRepository.delete(event);

        return OnResponse.onSuccess(eventId, HttpStatus.NO_CONTENT);
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
