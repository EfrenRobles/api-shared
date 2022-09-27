package api.carreras.events.domain.response;

import java.time.LocalDate;

public class EventResponse {

    Long eventId;

    String eventDescription;

    String eventLocation;

    LocalDate eventDate;

    public EventResponse() {
        super();
    }

    public EventResponse(Long eventId, String eventDescription, String eventLocation, LocalDate eventDate) {
        super();
        this.eventId = eventId;
        this.eventDescription = eventDescription;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;
    }

    public static EventResponse build(Long eventId, String eventDescription, String eventLocation, LocalDate eventDate) {

        return new EventResponse(eventId, eventDescription, eventLocation, eventDate);
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return "EventResponse ["
            + "eventId=" + eventId
            + ", eventDescription=" + eventDescription
            + ", eventLocation=" + eventLocation
            + ", eventDate=" + eventDate
            + "]";
    }

}
