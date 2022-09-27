package api.carreras.shared.infrastructure.config;

import org.springframework.core.env.ConfigurableEnvironment;

public class EnvConfig {

    private ConfigurableEnvironment env;
    
    private static EnvConfig instance = new EnvConfig();
    
    public static void setProperty(ConfigurableEnvironment env) {
        instance.env = env;
    }
    
    public static  String getProperty(String data) {
        String result = instance.env.getProperty(data);
        
        if (result == null) {
            return "";
        }
        
        return result;
    }
}
