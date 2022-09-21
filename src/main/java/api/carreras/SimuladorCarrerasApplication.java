package api.carreras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;

import api.carreras.shared.infrastructure.config.EnvConfig;

@SpringBootApplication
public class SimuladorCarrerasApplication {

    public static void main(String[] args) {
        EnvConfig.setProperty(
            SpringApplication
                .run(SimuladorCarrerasApplication.class, args)
                .getEnvironment()
        );
    }
}
