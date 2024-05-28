package com.example.rest.form;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;


@RestController
@RequestMapping("form")
public class FormController {
    Logger logger = LoggerFactory.getLogger(FormController.class);


    @GetMapping()
    public String hello() {

        return "Form controller";
    }

    @RequestMapping("/{path}")
    public String echo(@RequestParam(defaultValue = "no message") String message, @PathVariable(required = true) String path) {
        return path + " " + message;
    }

}
