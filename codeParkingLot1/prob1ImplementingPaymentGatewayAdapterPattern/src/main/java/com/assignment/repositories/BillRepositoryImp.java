package com.assignment.repositories;

import com.assignment.models.Bill;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BillRepositoryImp implements BillRepository {
    Map<Long, Bill> billMap;
    public BillRepositoryImp(){
        billMap = new HashMap<>();
    }
    @Override
    public Bill save(Bill bill) {
        billMap.put(bill.getId(), bill);
        return bill;
    }

    @Override
    public Optional<Bill> findById(long billId) {
        if(billMap.containsKey(billId)){
            return Optional.of(billMap.get(billId));
        }
        return Optional.empty();
    }
}
