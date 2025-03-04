package com.scaler.parking_lot.respositories;

import com.scaler.parking_lot.models.Gate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryGateRepository implements GateRepository{
    Map<Long,Gate> map;
    public InMemoryGateRepository(){
        map = new HashMap<>();
    }
    public InMemoryGateRepository(Map<Long,Gate> map){
        this.map = map;
    }

    @Override
    public Optional<Gate> findById(long gateId) {
        return Optional.ofNullable(map.get(gateId));
    }

    @Override
    public Gate save(Gate gate) {
        if(gate.getId() == 0) gate.setId(map.size()+1);
        map.put(gate.getId(), gate);
        return gate;
    }
}
