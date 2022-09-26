package api.carreras.events.domain.request;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.lang.Nullable;

public class UpdateEventRequest {

    @Nullable
    Long eventId;

    @Nullable
    @Length(min = 10, max = 64, message = "The length of user name must be between 10 and 64 characters.")
    String eventDescription;

    @Nullable
    @Length(min = 4, max = 64, message = "The length of user name must be between 4 and 64 characters.")
    String eventLocation;

    @Nullable
    @DateTimeFormat(iso = ISO.DATE)
    LocalDate eventDate;

    public UpdateEventRequest() {
        super();
    }

    public UpdateEventRequest(String eventDescription, String eventLocation, LocalDate eventDate) {
        super();
        this.eventDescription = eventDescription;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;
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
            + ", eventDescription=" + eventDescription
            + ", eventLocation=" + eventLocation
            + ", eventDate=" + eventDate
            + "]";
    }
}
