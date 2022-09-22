package api.carreras.events.domain;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long eventId;

    @Column(name = "event_description")
    String eventDescription;

    @Column(name = "event_location")
    String eventLocation;

    @Column(name = "event_date")
    Date eventDate;
    
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
    public int hashCode() {
        return Objects.hash(eventDate, eventDescription, eventId, eventLocation);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Event other = (Event) obj;

        return Objects.equals(eventDate, other.eventDate)
            && Objects.equals(eventDescription, other.eventDescription)
            && Objects.equals(eventId, other.eventId)
            && Objects.equals(eventLocation, other.eventLocation);
    }

    @Override
    public String toString() {
        return "Events ["
            + "eventId=" + eventId
            + ", eventDescription=" + eventDescription
            + ", eventLocation=" + eventLocation
            + ", eventDate=" + eventDate
            + "]";
    }

}
