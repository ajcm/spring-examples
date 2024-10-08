// tag::baseClass[]
package tacos.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.Order;

import javax.validation.Valid;


@Controller
@RequestMapping("/orders")
public class OrderController {

    //end::baseClass[]
//tag::orderForm[]
    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute("order", new Order());
        return "orderForm";
    }
//end::orderForm[]


    //tag::handlePostWithValidation[]
    @PostMapping
    public String processOrder(@Valid Order order, Errors errors) {
        if (errors.hasErrors()) {
            return "orderForm";
        }


        return "redirect:/";
    }

//end::handlePostWithValidation[]

//tag::baseClass[]

}
//end::baseClass[]
