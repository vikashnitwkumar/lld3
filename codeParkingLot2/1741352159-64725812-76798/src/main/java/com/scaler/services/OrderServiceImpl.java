package com.scaler.services;

import com.scaler.exceptions.InvalidMenuItem;
import com.scaler.exceptions.UserNotFoundException;
import com.scaler.models.*;
import com.scaler.repositories.CustomerSessionRepository;
import com.scaler.repositories.MenuItemRepository;
import com.scaler.repositories.OrderRepository;
import com.scaler.repositories.UserRepository;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    UserRepository userRepository;
    MenuItemRepository menuItemRepository;
    OrderRepository orderRepository;
    CustomerSessionRepository customerSessionRepository;
    public OrderServiceImpl(
            UserRepository userRepository,
        MenuItemRepository menuItemRepository,
        OrderRepository orderRepository,
        CustomerSessionRepository customerSessionRepository
    ){
      this.orderRepository = orderRepository;
      this.customerSessionRepository = customerSessionRepository;
      this.userRepository = userRepository;
      this.menuItemRepository = menuItemRepository;
    }
    @Override
    public Order placeOrder(long userId, Map<Long, Integer> orderedItems) throws UserNotFoundException, InvalidMenuItem {
        Optional<CustomerSession> csOptional = customerSessionRepository.findActiveCustomerSessionByUserId(userId);
        if(csOptional.isEmpty()) {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isEmpty()) throw new UserNotFoundException("No User");
            User user = userOptional.get();
            CustomerSession customerSession = new CustomerSession();
            customerSession.setUser(user);
            customerSession.setCustomerSessionStatus(CustomerSessionStatus.ACTIVE);
            customerSession = customerSessionRepository.save(customerSession);
            csOptional = Optional.of(customerSession);
        }
        Map<MenuItem,Integer> oi = new HashMap<>();
        for(Map.Entry<Long,Integer> menuItem : orderedItems.entrySet()){
            Optional<MenuItem> menuItemOptional = menuItemRepository.findById(menuItem.getKey());
            if(menuItemOptional.isEmpty()) throw new InvalidMenuItem("No item");
            oi.put(menuItemOptional.get(), menuItem.getValue());
        }
        Order order = new Order();
        order.setCustomerSession(csOptional.get());
        order.setOrderedItems(oi);
        return orderRepository.save(order);
    }
}
