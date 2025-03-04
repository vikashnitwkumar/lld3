package com.scaler.parking_lot.strategies.assignment;

import com.scaler.parking_lot.models.*;

import java.util.Optional;

public class SpotAssignmentStrategyImp implements SpotAssignmentStrategy{
    @Override
    public Optional<ParkingSpot> assignSpot(ParkingLot parkingLot, VehicleType vehicleType) {
        ParkingFloor resultFloor = null;
        ParkingSpot resultParkingSpot = null;
        long resultSpotCount = 0;
        for(ParkingFloor parkingFloor : parkingLot.getParkingFloors()){
            if(parkingFloor.getStatus() == FloorStatus.OPERATIONAL){
                long spotCount = 0;
                ParkingSpot tempParkingSpot = null;
                for(ParkingSpot parkingSpot : parkingFloor.getSpots()){
                    if(parkingSpot.getStatus() == ParkingSpotStatus.AVAILABLE
                            && parkingSpot.getSupportedVehicleType() == vehicleType
                    ){
                        spotCount++;
                        if(tempParkingSpot == null || parkingSpot.getId() < tempParkingSpot.getId()){
                            tempParkingSpot = parkingSpot;
                        }
                    }
                }
                if( spotCount > 0 // have empty spot
                        && (    resultSpotCount == 0 || resultSpotCount > spotCount
                                || (resultSpotCount == spotCount && (resultFloor == null || resultFloor.getId() > parkingFloor.getId()) // selecting floor with min floor id
                                    )
                        )
                ){
                    resultSpotCount = spotCount;
                    resultParkingSpot = tempParkingSpot;
                    resultFloor = parkingFloor;
                }
            }
        }
        return Optional.ofNullable(resultParkingSpot);
    }
}
