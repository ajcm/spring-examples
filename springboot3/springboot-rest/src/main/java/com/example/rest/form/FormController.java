package com.example.rest.form;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


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

    @RequestMapping(
            value = "/header",
            headers = {"header=h1"},
            method = RequestMethod.GET)
    public String h1(@RequestParam(defaultValue = "no message") String message) {
        return "h1";
    }

    @RequestMapping(
            value = "/header",
            headers = {"header=h2"},
            method = RequestMethod.GET)
    public String h2(@RequestParam(defaultValue = "no message") String message) {
        return "h2";
    }

}
