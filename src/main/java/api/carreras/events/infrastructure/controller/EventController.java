package api.carreras.events.infrastructure.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.carreras.events.application.EventService;
import api.carreras.events.domain.request.AddEventRequest;
import api.carreras.events.domain.request.UpdateEventRequest;
import api.carreras.events.domain.response.EventResponse;
import api.carreras.shared.domain.Builder;
import api.carreras.shared.infrastructure.PaginationConstant;

@RestController
@RequestMapping("/api/v1/events")
@Validated
public class EventController {

    @Autowired
    private EventService eventService;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public Object getEventList(
        @RequestParam(value = "page", defaultValue = PaginationConstant.PAGE_DEFAULT, required = false) Short page,
        @RequestParam(value = "limit", defaultValue = PaginationConstant.LIMIT_DEFAULT, required = false) Byte limit,
        @RequestParam(value = "sortBy", defaultValue = "eventId", required = false) String sortBy,
        @RequestParam(value = "sortDir", defaultValue = PaginationConstant.SORT_ASC, required = false) String sortDir,
        @RequestParam(value = "eventId", required = false) Long eventId,
        @RequestParam(value = "eventLocation", required = false) String eventLocation,
        @RequestParam(value = "eventDescription", required = false) String eventDescription,
        @RequestParam(value = "eventDate", required = false)
        @DateTimeFormat(iso = ISO.DATE) LocalDate eventDate

    ) {
        EventResponse request = Builder.set(EventResponse.class)
            .with(e -> e.setEventId(eventId))
            .with(e -> e.setEventLocation(eventLocation))
            .with(e -> e.setEventDescription(eventDescription))
            .with(e -> e.setEventDate(eventDate))
            .build();

        return eventService.getEventList(page, limit, sortBy, sortDir, request);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(params = "eventId")
    public Object getEventByEventId(@RequestParam(value = "eventId") Long eventId) throws Exception {

        return eventService.getEventByEventId(eventId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Object postEvent(
        @Valid @RequestBody AddEventRequest event
    ) throws Exception {

        return eventService.addEvent(event);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping
    public Object patchEvent(
        @RequestParam(value = "eventId") Long eventId,
        @Valid @RequestBody UpdateEventRequest event
    ) throws Exception {

        event.setEventId(eventId);

        return eventService.updateEvent(event);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public void deleteEvent(@RequestParam(value = "eventId") Long eventId) throws Exception {

        eventService.deleteEvent(eventId);
    }
}
