package com.scaler.repositories;

import com.scaler.models.Order;
import org.hibernate.mapping.Collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryOrderRepository implements OrderRepository {
    Map<Long, Order> orderMap;

    public InMemoryOrderRepository(Map<Long, Order> orderMap) {
        this.orderMap = orderMap;
    }
    public InMemoryOrderRepository() {
            this.orderMap = new HashMap<>();
        }

    @Override
    public Order save(Order order) {
        if(order.getId() == 0) order.setId(orderMap.size()+1);
        return orderMap.put(order.getId(), order);
    }

    @Override
    public List<Order> findOrdersByCustomerSession(long customerSessionId) {
         return orderMap.
                values().
                stream().
                filter(
                        order -> order.getCustomerSession() != null &&
                        order.getCustomerSession().getId() == customerSessionId
                ).toList();
    }
}
