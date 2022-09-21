package api.carreras.shared.domain;

import org.springframework.beans.factory.annotation.Value;

public class Logger {

    @Value("${spring.profiles.active}")
    private static String env;

    public static void log(Object input) {
        if (env.equalsIgnoreCase("local")) {
            System.out.println(" -------------------------------------------- ");
            System.out.println(input);
            System.out.println(" -------------------------------------------- ");
        }
    }
}
