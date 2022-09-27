package api.carreras.events.domain.request;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class AddEventRequest {

    @NotBlank(message = "The event descrition is required.")
    @Length(min = 10, max = 64, message = "The length of user name must be between 10 and 64 characters.")
    String eventDescription;

    @NotBlank(message = "The event location is required.")
    @Length(min = 4, max = 64, message = "The length of user name must be between 4 and 64 characters.")
    String eventLocation;

    @NotNull(message = "The eventDate is required.")
    @DateTimeFormat(iso = ISO.DATE)
    LocalDate eventDate;

    public AddEventRequest() {
        super();
    }

    public AddEventRequest(String eventDescription, String eventLocation, LocalDate  eventDate) {
        super();
        this.eventDescription = eventDescription;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;
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

    public LocalDate  getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate  eventDate) {
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
