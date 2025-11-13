package com.harry.order.controller;

import com.harry.order.client.UserClient;
import com.harry.order.model.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final UserClient userClient;

    @GetMapping("/{id}")
    public String getOrder(@PathVariable Long id) {
        UserDTO user = userClient.getUser(1L);
        return "Order " + id + " belongs to " + user.getName();
    }
}
