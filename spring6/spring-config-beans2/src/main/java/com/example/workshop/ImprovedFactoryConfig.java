package com.example.workshop;

import com.example.workshop.factories.ImprovedToolFactory;
import com.example.workshop.tools.ITool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImprovedFactoryConfig {

    @Bean
    public ImprovedToolFactory improvedToolFactory() {
        ImprovedToolFactory factory = new ImprovedToolFactory();
        return factory;
    }

    @Bean
    public ITool improvedTool() throws Exception {
        return improvedToolFactory().getObject();
    }

}
