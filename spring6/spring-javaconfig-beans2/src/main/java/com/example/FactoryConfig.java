package com.example;

import com.example.tools.ITool;
import com.example.tools.ImprovedToolFactory;
import com.example.tools.ToolFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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
