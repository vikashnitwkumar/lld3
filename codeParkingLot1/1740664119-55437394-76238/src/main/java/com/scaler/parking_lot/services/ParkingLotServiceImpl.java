package com.scaler.parking_lot.services;

import com.scaler.parking_lot.exceptions.InvalidParkingLotException;
import com.scaler.parking_lot.models.FloorStatus;
import com.scaler.parking_lot.models.ParkingFloor;
import com.scaler.parking_lot.models.ParkingLot;
import com.scaler.parking_lot.models.ParkingSpot;
import com.scaler.parking_lot.models.ParkingSpotStatus;
import com.scaler.parking_lot.models.VehicleType;
import com.scaler.parking_lot.respositories.ParkingLotRepository;

import java.util.*;

public class ParkingLotServiceImpl implements ParkingLotService{

    private final ParkingLotRepository parkingLotRepository;

    public ParkingLotServiceImpl(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    @Override
    public Map<ParkingFloor, Map<String, Integer>> getParkingLotCapacity(long parkingLotId, List<Long> parkingFloorIds, List<VehicleType> requiredVehicleTypes) throws InvalidParkingLotException {
        Optional<ParkingLot> parkingLotOptional = parkingLotRepository.getParkingLotById(parkingLotId);
        if(parkingLotOptional.isEmpty()){
            throw new InvalidParkingLotException("Invalid Parking Lot Id");
        }

        ParkingLot parkingLot = parkingLotOptional.get();
        Map<ParkingFloor, Map<String, Integer>> map = new HashMap<>();
        Set<Long> parkingFloorIdSet = parkingFloorIds == null ? new HashSet<>(): new HashSet<>(parkingFloorIds);

        if(requiredVehicleTypes == null || requiredVehicleTypes.isEmpty()){
            requiredVehicleTypes = Arrays.asList(VehicleType.values());
        }

        for(ParkingFloor parkingFloor : parkingLot.getParkingFloors()){
            if(parkingFloorIdSet.size() > 0 && !parkingFloorIdSet.contains(parkingFloor.getId())){
                continue;
            }
            Map<String, Integer> vehicleTypeIntegerMap = new HashMap<>();
            for(VehicleType vehicleType : requiredVehicleTypes){
                vehicleTypeIntegerMap.put(vehicleType.name(), calculateAvailableSpotsByVehicleType(parkingFloor, vehicleType));
            }
            map.put(parkingFloor, vehicleTypeIntegerMap);
        }
        return map;
    }
    private  int calculateAvailableSpotsByVehicleType(ParkingFloor parkingFloor, VehicleType vehicleType){
        int count = 0;
        if(parkingFloor == null || vehicleType == null || !parkingFloor.getStatus().equals(FloorStatus.OPERATIONAL)){
            return count;
        }

        for (ParkingSpot spot : parkingFloor.getSpots()) {
            if(spot.getSupportedVehicleType().equals(vehicleType) && spot.getStatus().equals(ParkingSpotStatus.AVAILABLE)){
                count++;
            }
        }
        return count;
    }
}
