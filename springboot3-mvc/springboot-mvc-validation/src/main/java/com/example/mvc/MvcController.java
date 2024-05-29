package com.example.mvc;

import com.example.mvc.model.Order;
import com.example.mvc.model.Orders;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/")
public class MvcController {

    @Autowired
    Orders orders;

    /**
     * names ordergg and orderx used to avoid binding by name.
     */

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("ordergg", new Order());

        model.addAttribute("types", List.of(Order.Type.FAST, Order.Type.SUPERFAST, Order.Type.SLOW));
    }


    @GetMapping("")
    public String home() {
        return "home";
    }

    @GetMapping("/form")
    public String orderForm(Model model) {
        model.addAttribute("order", new Order());
        return "form";
    }

    @GetMapping("/orders")
    public ModelAndView orders() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("orders", orders.getOrders());
        modelAndView.setViewName("orders");

        return modelAndView;
    }


    @PostMapping("createOrder")
    public String processOrder(@Valid @ModelAttribute("ordergg") Order orderx, Errors errors, BindingResult bindingResult) {

        if (errors.hasErrors()) {
            return "form";
        }
        var orderId = orders.addOrder(orderx);

        return "redirect:/formDone?id=" + orderId;
    }


    @GetMapping("/formDone")
    public ModelAndView orderForm(@RequestParam String id) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id", id);
        modelAndView.setViewName("form-done");

        return modelAndView;
    }


}


