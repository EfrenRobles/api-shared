package api.carreras.events.infrastructure.persistence.postgresql;

import org.springframework.data.jpa.repository.JpaRepository;

import api.carreras.events.domain.Event;

interface EventRepository extends JpaRepository<Event, Long> {

    Event findByEventId(Long eventId);

    Event findByEventLocation(String eventLocation);
}
