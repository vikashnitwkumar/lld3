package com.scaler.parking_lot.services;

import com.scaler.parking_lot.exceptions.InvalidGateException;
import com.scaler.parking_lot.exceptions.InvalidParkingLotException;
import com.scaler.parking_lot.exceptions.ParkingSpotNotAvailableException;
import com.scaler.parking_lot.models.*;
import com.scaler.parking_lot.respositories.GateRepository;
import com.scaler.parking_lot.respositories.ParkingLotRepository;
import com.scaler.parking_lot.respositories.TicketRepository;
import com.scaler.parking_lot.respositories.VehicleRepository;
import com.scaler.parking_lot.strategies.assignment.SpotAssignmentStrategy;

import java.util.Date;
import java.util.Optional;

public class TicketServiceImp implements TicketService{

    GateRepository gateRepository;
    VehicleRepository vehicleRepository;
    TicketRepository ticketRepository;
    ParkingLotRepository parkingLotRepository;
    SpotAssignmentStrategy spotAssignmentStrategy;

    public TicketServiceImp(GateRepository gateRepository,
                            VehicleRepository vehicleRepository,
                            TicketRepository ticketRepository,
                            ParkingLotRepository parkingLotRepository,
                            SpotAssignmentStrategy spotAssignmentStrategy
    ) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.ticketRepository = ticketRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.spotAssignmentStrategy = spotAssignmentStrategy;
    }

    @Override
    public Ticket generateTicket(int gateId, String registrationNumber, String vehicleType) throws InvalidGateException, InvalidParkingLotException, ParkingSpotNotAvailableException {
        Optional<Gate> gateOptional = gateRepository.findById(gateId);
        if(gateOptional.isEmpty()){
            throw new InvalidGateException("Invalid Gate");
        }
        Gate gate = gateOptional.get();
        if(gate.getType() != GateType.ENTRY){
            throw  new InvalidGateException("Invalid Gate");
        }
        Optional<Vehicle> vehicleOptional = vehicleRepository.getVehicleByRegistrationNumber(registrationNumber);
        if(vehicleOptional.isEmpty()){
            Vehicle vehicle = new Vehicle();
            vehicle.setRegistrationNumber(registrationNumber);
            vehicle.setVehicleType(VehicleType.valueOf(vehicleType));
            vehicleRepository.save(vehicle);
            vehicleOptional = Optional.of(vehicle);
        }
        Optional<ParkingLot> parkingLotOptional = parkingLotRepository.getParkingLotByGateId(gateId);
        if(parkingLotOptional.isEmpty()){
            throw new InvalidParkingLotException("No Parking Lot with given Gate id");
        }
        Optional<ParkingSpot> parkingSpotOptional = spotAssignmentStrategy.assignSpot(parkingLotOptional.get(), VehicleType.valueOf(vehicleType));
        if(parkingSpotOptional.isEmpty()){
            throw new ParkingSpotNotAvailableException("No Parking Spot Available");
        }
        Ticket ticket = new Ticket();
        ticket.setGate(gate);
        ticket.setEntryTime(new Date());
        ticket.setVehicle(vehicleOptional.get());
        ticket.setParkingAttendant(gate.getParkingAttendant());
        ticket.setParkingSpot(parkingSpotOptional.get());
        parkingSpotOptional.get().setStatus(ParkingSpotStatus.OCCUPIED);
        ticketRepository.save(ticket);
        return ticket;
    }
}
