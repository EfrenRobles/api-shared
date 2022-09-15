package api.carreras.events.infrastructure.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/api/v1/events")
@Validated
public class EventController {

    @Autowired
    private EventService eventService;
    
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public Object getEventList() {
        
        return eventService.getEventList();
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(params = "eventId")
    public Object getEventByEventId(@RequestParam(value ="eventId") Long eventId) {
     
        return eventService.getEventById(eventId);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(params = "eventLocation")
    public Object getEventByEventLocation(@RequestParam(value = "eventLocation") String eventLocation) {
        
        return eventService.getEventByLocation(eventLocation);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Object postEvent(@Valid @RequestBody AddEventRequest event) throws Exception {
        
        return eventService.addEvent(event);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping
    public Object patchEvent(
        @RequestParam(value ="eventId") Long eventId,
        @Valid @RequestBody UpdateEventRequest event
    ) throws Exception {
        
        return eventService.updateEvent(eventId, event);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public void deleteEvent(@RequestParam(value ="eventId") Long eventId) throws Exception {
        
        eventService.deleteEvent(eventId);
    }
}
