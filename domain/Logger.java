package api.shared.domain;

import api.shared.infrastructure.config.EnvConfig;

public class Logger {

    public static void log(Object input) {
        if (EnvConfig.getProperty("spring.profiles.active").equalsIgnoreCase("local")) {
            System.out.println(" -------------------------------------------- ");
            System.out.println(input);
            System.out.println(" -------------------------------------------- ");
        }
    }
}
