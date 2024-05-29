package com.example.mvc.model;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Scope("singleton")
@Component
@ComponentScan
public class Orders {
    private final List<Order> orders = new LinkedList<>();

    public List<Order> getOrders() {
        return orders;
    }

    public String addOrder(Order order) {
        var id = UUID.randomUUID().toString();
        order.setId(id);

        orders.add(order);
        return id;
    }
}
