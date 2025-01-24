Q1. Parking Lot Class Diagram

Parking Lot Class Diagram

Requirements:

Parking lot will have multiple floors.

There will be multiple entry and exit gates.

Parking lot will support parking for these vehicles: CAR, BIKE, TRUCK. We should park only appropriate vehicle at their spots i.e. a spot for CAR cannot be used to park bikes.

When a user enters via an entry gate, the operator generates a ticket and gives it to the user. The ticket contains information like entry time, vehicle number, assigned parking spot. Parking spots should be assigned as per the nearest available spot first i.e. find the nearest appropriate spot for a vechile and assign it. This way of assigning spot may change in the future.

Once the user decides to exit the parking lot, they will handover the ticket to the operator at the exit gate upon which their final bill will be calculated.

Bill calculation for a vehicle depends upon to two things, time spent in the parking lot and the type of vehicle. Refer the below tables to understand pricing. For BIKEs: Hours spent Price per hour 0-2 20 2-4 25 4-6 30 6 onwards 40

For CARs: Hours spent Price per hour 0-2 25 2-4 30 4-6 35 6 onwards 45

For TRUCKs: Hours spent Price per hour 0-2 50 2-4 60 4-6 65 6 onwards 80

For eg. If a car spends 5.5 hours in the parking lot then their final bill will be: 2 * 25 (Charge for first 2 hours) + 2 * 30 (Charge for the next two hours i.e hour 3 and hour 4) + 2 * 35 (Charge for 5th hour and 6th hour (Note: even tough the vehicle was not in the parking lot for the entire hour, we charged it for the entire hour)) = Rs. 180 Note: This is one way of charging customers, in the future we might change this.

We will integrate with Razorpay to manage payments.