package com.scaler.services;

import com.scaler.exceptions.CustomerSessionNotFound;
import com.scaler.models.*;
import com.scaler.repositories.CustomerSessionRepository;
import com.scaler.repositories.OrderRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderServiceImpl implements OrderService{
    OrderRepository orderRepository;
    CustomerSessionRepository customerSessionRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerSessionRepository customerSessionRepository) {
        this.orderRepository = orderRepository;
        this.customerSessionRepository = customerSessionRepository;
    }

    @Override
    public Bill generateBill(long userId) throws CustomerSessionNotFound {
        Optional<CustomerSession> customerSessionOptional =
                customerSessionRepository.
                findActiveCustomerSessionByUserId(userId);
        if(customerSessionOptional.isEmpty()) throw new CustomerSessionNotFound("no active customer session");
        CustomerSession customerSession = customerSessionOptional.get();
        List<Order> orderList = orderRepository.findOrdersByCustomerSession(customerSession.getId());
        Bill bill = new Bill();
        Map<MenuItem, Integer> orderedItems = new HashMap<>();
        for(Order itr : orderList){
            for(Map.Entry<MenuItem,Integer> mi : itr.getOrderedItems().entrySet()){
                orderedItems.putIfAbsent(mi.getKey(), 0);
                orderedItems.put(mi.getKey(), orderedItems.get(mi.getKey()) + mi.getValue());
            }
        }
        customerSession.setCustomerSessionStatus(CustomerSessionStatus.ENDED);
        bill.setOrderedItems(orderedItems);
        double totalAmount = 0;
        for (Map.Entry<MenuItem,Integer> itr : orderedItems.entrySet()){
            totalAmount += itr.getKey().getPrice()* itr.getValue();
        }
        bill.setTotalAmount(totalAmount);
        bill.setGst(.05*totalAmount);
        bill.setServiceCharge(.1*totalAmount);
        return bill;
    }
}
