package com.scaler.parking_lot.respositories;

import com.scaler.parking_lot.models.ParkingLot;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryParkingLotRepository implements ParkingLotRepository{
   Map<Long,ParkingLot> map;
   public InMemoryParkingLotRepository(){
       map = new HashMap<>();
   }
   public InMemoryParkingLotRepository(Map<Long,ParkingLot>map){
       this.map = map;
   }
    @Override
    public Optional<ParkingLot> getParkingLotByGateId(long gateId) {
        return
                this.map.values().stream().filter(
                        parkingLot -> parkingLot.getGates().stream().anyMatch(
                                gate -> gate.getId() == gateId
                        )
                ).findFirst();

    }

    @Override
    public Optional<ParkingLot> getParkingLotById(long id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public ParkingLot save(ParkingLot parkingLot) {
       if(parkingLot.getId() == 0) parkingLot.setId(map.size()+1);
        map.put(parkingLot.getId(), parkingLot);
        return parkingLot;
    }
}
