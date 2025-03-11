package com.example.ecom.services;

import com.example.ecom.exceptions.OrderCannotBeCancelledException;
import com.example.ecom.exceptions.OrderDoesNotBelongToUserException;
import com.example.ecom.exceptions.OrderNotFoundException;
import com.example.ecom.exceptions.UserNotFoundException;
import com.example.ecom.models.Order;
import com.example.ecom.models.OrderStatus;
import com.example.ecom.models.User;
import com.example.ecom.repositories.OrderRepository;
import com.example.ecom.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{
   UserRepository userRepository;
   OrderRepository orderRepository;
    @Autowired
    public OrderServiceImpl(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order cancelOrder(int orderId, int userId) throws UserNotFoundException, OrderNotFoundException, OrderDoesNotBelongToUserException, OrderCannotBeCancelledException {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) throw new UserNotFoundException("no such user");
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if(orderOptional.isEmpty()) throw new OrderNotFoundException("no such order");
        User user = userOptional.get();
        Order order = orderOptional.get();
        if(order.getUser().getId() != user.getId()) throw new OrderDoesNotBelongToUserException("order user user");
        if(order.getOrderStatus() != OrderStatus.PLACED) throw new OrderCannotBeCancelledException("order can't be cancelled");
        order.setOrderStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
        return order;
    }
}
