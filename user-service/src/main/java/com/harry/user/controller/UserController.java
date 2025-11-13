package com.harry.user.controller;

import com.harry.order.model.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        UserDTO dto = new UserDTO();
        dto.setId(id);
        dto.setName("User-" + id);
        dto.setEmail("user" + id + "@example.com");
        return dto;
    }
}
