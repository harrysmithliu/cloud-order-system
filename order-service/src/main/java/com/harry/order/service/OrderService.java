package com.harry.order.service;

import com.harry.common.PageResult;
import com.harry.order.model.dto.OrderDTO;

import java.time.LocalDate;

public interface OrderService {

    PageResult<OrderDTO> queryOrders(
            Long userId,
            String orderNo,
            Integer status,
            String productCode,
            String keyword,
            LocalDate createdAfter,
            LocalDate createdBefore,
            int page,
            int size
    );
}
