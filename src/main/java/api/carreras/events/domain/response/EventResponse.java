package api.carreras.events.domain.response;

import java.sql.Date;

public class EventResponse {

    Long eventId;

    String eventDescription;

    String eventLocation;

    Date eventDate;

    public EventResponse() {
        super();
    }

    public EventResponse(Long eventId, String eventDescription, String eventLocation, Date eventDate) {
        super();
        this.eventId = eventId;
        this.eventDescription = eventDescription;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;
    }
    
    public static EventResponse build(Long eventId, String eventDescription, String eventLocation, Date eventDate) {
    
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

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
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
