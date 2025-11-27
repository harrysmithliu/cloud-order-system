package com.harry.order.controller;

import com.harry.common.PageResult;
import com.harry.order.model.dto.OrderDTO;
import com.harry.order.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public PageResult<OrderDTO> queryOrders(
            @RequestParam(required = false) String orderNo,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) String productCode,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createdAfter,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createdBefore,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            HttpServletRequest request
    ) {

        // TODO 暂时用固定测试值（以后 JWT 来替换）
        Long userId = 1L;
        String userKey = "0000";

        return orderService.queryOrders(
                userId, orderNo, status, productCode, keyword,
                createdAfter, createdBefore, page, size
        );
    }
}
