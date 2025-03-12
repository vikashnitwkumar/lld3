package com.example.ecom.services;

import com.example.ecom.exceptions.*;
import com.example.ecom.models.*;
import com.example.ecom.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{
    UserRepository userRepository;
    AddressRepository addressRepository;
    OrderDetailRepository orderDetailRepository;
    ProductRepository productRepository;
    InventoryRepository inventoryRepository;
    HighDemandProductRepository highDemandProductRepository;
    OrderRepository orderRepository;
    @Autowired
    public OrderServiceImpl(UserRepository userRepository,
                            AddressRepository addressRepository,
                            OrderDetailRepository orderDetailRepository,
                            ProductRepository productRepository,
                            InventoryRepository inventoryRepository,
                            HighDemandProductRepository highDemandProductRepository,
                            OrderRepository orderRepository)
    {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
        this.highDemandProductRepository = highDemandProductRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order placeOrder(int userId, int addressId, List<Pair<Integer, Integer>> orderDetails) throws UserNotFoundException, InvalidAddressException, OutOfStockException, InvalidProductException, HighDemandProductException {
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User not found"));
        Address address = addressRepository.findById(addressId).orElseThrow(() ->new InvalidAddressException("address not found")) ;
        if (address.getUser().getId() != userId) throw new InvalidAddressException("User address is not correct");
        List<OrderDetail> orderDetailList = new ArrayList<>();
        List<Inventory> inventoryList = new ArrayList<>();
        Order order = new Order();
        for (Pair<Integer, Integer> itr : orderDetails){
           Product product = productRepository.findById(itr.getFirst()).orElseThrow(()-> new InvalidProductException("No such Product"));
           Inventory inventory = inventoryRepository.findByProduct(product).get();
           if(inventory.getQuantity() < itr.getSecond()) throw new OutOfStockException("out of Stock");
           Optional<HighDemandProduct> hdpO = highDemandProductRepository.findByProduct(product);
           if(hdpO.isPresent() && hdpO.get().getMaxQuantity() < itr.getSecond()) throw new HighDemandProductException("Product is in high Demand");
           OrderDetail orderDetail = new OrderDetail();
           orderDetail.setQuantity(itr.getSecond());
           orderDetail.setOrder(order);
           orderDetail.setProduct(product);
           orderDetailList.add(orderDetail);
           inventory.setQuantity(inventory.getQuantity()- itr.getSecond());
           inventoryList.add(inventory);
        }
        order.setOrderDetails(orderDetailList);
        order.setUser(user);
        order.setOrderStatus(OrderStatus.PLACED);
        order.setDeliveryAddress(address);
        order = orderRepository.save(order);
        for (OrderDetail itr : orderDetailList) itr.setOrder(order);
        orderDetailRepository.saveAll(orderDetailList);
        inventoryRepository.saveAll(inventoryList);
        return order;
    }
}
