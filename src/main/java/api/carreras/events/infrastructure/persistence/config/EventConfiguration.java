package api.carreras.events.infrastructure.persistence.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import api.carreras.SimuladorCarrerasApplication;
import api.carreras.events.application.EventService;
import api.carreras.events.application.EventServiceImp;
import api.carreras.events.infrastructure.persistence.postgresql.EventReposirotyImpl;

@Configuration
@ComponentScan(basePackageClasses = SimuladorCarrerasApplication.class)
public class EventConfiguration {

    @Bean
    EventService eventService(EventReposirotyImpl repo) {
        
        return EventServiceImp.build(repo);
    }
}
