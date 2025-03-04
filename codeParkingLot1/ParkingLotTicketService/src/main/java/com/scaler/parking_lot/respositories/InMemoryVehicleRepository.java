package com.scaler.parking_lot.respositories;

import com.scaler.parking_lot.models.Vehicle;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryVehicleRepository implements VehicleRepository{
   Map<String,Vehicle> map;
    public InMemoryVehicleRepository(){
        map = new HashMap<>();
    }
    @Override
    public Optional<Vehicle> getVehicleByRegistrationNumber(String registrationNumber) {
        return Optional.ofNullable(map.get(registrationNumber));
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        if(vehicle.getId() == 0){
            vehicle.setId(map.size()+1);
        }
        map.put(vehicle.getRegistrationNumber(), vehicle);
        return vehicle;
    }
}
