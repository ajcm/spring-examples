package com.example.rest.form;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("requestmapping")
public class RequestMappingController {


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

    @GetMapping(path = "/param/{pathVar}", params = "param=param1")
    public String param1(@PathVariable String pathVar) {
        return "param1 " + pathVar;
    }

    @GetMapping(path = "/param/{pathVar}", params = "param=param2")
    public String param2(@PathVariable String pathVar) {
        return "param2 " + pathVar;
    }


    @GetMapping(path = "/param", params = "id")
    public String param3(@RequestParam String id, @RequestHeader String header) {
        return "id param: " + id + " " + "header " + header;
    }


    @RequestMapping(value = "/path/{fooid}/and/{longId}", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String path(HttpServletRequest request, @PathVariable String fooid, @PathVariable long longId, @RequestParam(value = "id", required = false) long paramId) {
        return request.getMethod() + " " + fooid + " and " + longId + ", paramId: " + paramId;
    }

}
