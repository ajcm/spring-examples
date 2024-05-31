package com.example.workshop;

import com.example.workshop.tools.ITool;
import com.example.workshop.factories.ToolFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.workshop.**")
public class FactoryConfig {

    @Bean
    public ToolFactory toolFactory() {
        ToolFactory factory = new ToolFactory();
        return factory;
    }

    @Bean("tool")
    public ITool tool(ToolFactory toolFactory) throws Exception {
        return toolFactory.getObject();
    }


}
