package com.example.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:app.properties")
public class AppPropertiesOther {

    private final String name;

    private final String version;

    private final String description;


    public AppPropertiesOther(
            @Value("${app.name}") String name,
            @Value("${app.version}") String version,
            @Value("${app.description}") String description) {
        this.name = name;
        this.version = version;
        this.description = description;
    }


    public String getMessage() {
        return name + " " + description + " " + version;
    }


}
