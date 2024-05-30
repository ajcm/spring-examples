package com.example;

import com.example.workshop.FactoryConfig;
import com.example.workshop.ImprovedFactoryConfig;
import com.example.workshop.tools.ITool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {FactoryConfig.class, ImprovedFactoryConfig.class})
public class FactoryTest {


    // inject bean using the factory
    @Test
    public void testFactory(@Autowired ITool tool) {
        Assertions.assertNotNull(tool);
        Assertions.assertEquals("Hammer", tool.getMessage());
    }


    @Test
    public void testFactory2(@Autowired @Qualifier("improvedTool") ITool tool) {
        Assertions.assertNotNull(tool);
        Assertions.assertEquals("Screwdriver", tool.getMessage());
    }

}
