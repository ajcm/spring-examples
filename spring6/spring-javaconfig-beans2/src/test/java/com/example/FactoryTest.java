package com.example;

import com.example.tools.ITool;
import com.example.tools.ToolFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class FactoryTest {

    @Test
    public void testFactory(@Autowired ITool tool) {
        Assertions.assertNotNull(tool);
        Assertions.assertEquals("Hammer", tool.getMessage());
    }

    @Test
    public void testFactory(@Autowired ToolFactory toolFactory) throws Exception {
        Assertions.assertNotNull(toolFactory);
        Assertions.assertEquals("Hammer", toolFactory.getObject().getMessage());
    }

    @Test
    public void testFactory2(@Autowired @Qualifier("improvedTool")  ITool tool ) {
        Assertions.assertNotNull(tool);
        Assertions.assertEquals("Screwdriver", tool.getMessage());
    }

}
