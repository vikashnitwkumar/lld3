package com.scaler.repositories;

import com.scaler.models.CustomerSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryCustomerSessionRepository implements CustomerSessionRepository {
   Map<Long, CustomerSession> customerSessionMap;

    public InMemoryCustomerSessionRepository(Map<Long, CustomerSession> customerSessionMap) {
        this.customerSessionMap = customerSessionMap;
    }

    public InMemoryCustomerSessionRepository() {
        customerSessionMap = new HashMap<>();
    }

    @Override
    public CustomerSession save(CustomerSession customerSession) {
        if(customerSession.getId() == 0 ) customerSession.setId(customerSessionMap.size()+1);
        return customerSessionMap.put(customerSession.getId(), customerSession);
    }

    @Override
    public Optional<CustomerSession> findActiveCustomerSessionByUserId(long userId) {
        return customerSessionMap.values().stream()
                .filter(customerSession ->
                                customerSession.isActive() &&
//                                customerSession.getUser() != null &&
                                customerSession.getUser().getId() == userId
                )
                .findFirst();
    }
}
