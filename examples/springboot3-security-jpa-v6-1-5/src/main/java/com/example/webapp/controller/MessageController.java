package com.example.webapp.controller;

import com.example.webapp.model.Message;
import com.example.webapp.service.MessageJdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    MessageJdbcService messageService;


    @GetMapping("/all")
    public ModelAndView messages() {
        List<Message> messages = messageService.getMessages();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("messages");
        modelAndView.addObject("messages", messages);

        return modelAndView;
    }

    @PostMapping("post")
    public String doPost(Message message, Principal principal, BindingResult bindingResult) {

        if (principal != null) {
            message.setSender(principal.getName());
        } else {
            message.setSender("-");
        }

        if (message.getBody() != null) {
            messageService.add(message);
        }

        return "redirect:/messages/all";
    }


    @GetMapping("read/{id}")
    public ModelAndView getDetails(@PathVariable String id) {
        Message message = messageService.get(id);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("message", message);
        modelAndView.setViewName("messageDetail");

        return modelAndView;
    }


    @GetMapping("delete/{id}")
    public String delete(@PathVariable String id) {
        messageService.delete(id);
        return "redirect:/messages/all";
    }


}
