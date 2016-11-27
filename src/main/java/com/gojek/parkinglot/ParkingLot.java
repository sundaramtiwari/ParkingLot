package com.gojek.parkinglot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gojek.entity.Car;
import com.gojek.entity.Vehicle;

/**
 * @author sundaram
 *
 */
public class ParkingLot {

    protected int capacity = 0;

    // Available slots list
    protected List<Integer> availableSlotList;

    // Map of Slot, Vehicle
    protected Map<String, Vehicle> slotVehicleMap;

    // Map of RegNo, Slot
    protected Map<String, String> regNoSlotMap;

    // Map of Color, List of RegNo
    protected Map<String, ArrayList<String>> colourVehicleMap;

    /**
     * Constructor for Parking Lot
     * @param lotCount
     */
    public void createParkingLot(String lotCount) {
        try {

            this.capacity = Integer.parseInt(lotCount);

        } catch (Exception e) {
            System.out.println("Invalid lot count");
        }

        this.availableSlotList = new ArrayList<Integer>();

        for (int i=1; i<= this.capacity; i++) {
            availableSlotList.add(i);
        }
        this.slotVehicleMap = new HashMap<String, Vehicle>();
        this.regNoSlotMap = new HashMap<String, String>();
        this.colourVehicleMap = new HashMap<String, ArrayList<String>>();
        System.out.println("Created parking lot with " + lotCount + " slots");
        System.out.println();
    }
    
    /**
     * Parks the provided details into parking lot
     * @param regNo
     * @param color
     */
    public synchronized void park(String regNo, String color) {

        if (this.capacity == 0) {

            System.out.println("Sorry, parking lot is not created");

        } else if (this.slotVehicleMap.size() == this.capacity) {

            System.out.println("Sorry, parking lot is full");

        } else {

            Collections.sort(availableSlotList);

            String slot = availableSlotList.get(0).toString();
            Vehicle vehicle = new Car(regNo, color);

            this.slotVehicleMap.put(slot, vehicle);
            this.regNoSlotMap.put(regNo, slot);

            if (this.colourVehicleMap.containsKey(color)) {

                ArrayList<String> regNoList = this.colourVehicleMap.get(color);
                regNoList.add(regNo);
                this.colourVehicleMap.put(color, regNoList);

            } else {

                ArrayList<String> regNoList = new ArrayList<String>();
                regNoList.add(regNo);
                this.colourVehicleMap.put(color, regNoList);

            }

            System.out.println("Allocated slot number: " + slot);
            availableSlotList.remove(0);
        }
    }

    /**
     * Removes the vehicle parked at provided slotNo
     * @param slotNo
     */
    public void leave(String slotNo) {

        if (this.capacity == 0) {

            System.out.println("Sorry, parking lot is not created");

        } else if (this.slotVehicleMap.size() > 0) {

            Vehicle carToLeave = this.slotVehicleMap.get(slotNo);

            if (carToLeave != null) {

                this.slotVehicleMap.remove(slotNo);
                this.regNoSlotMap.remove(carToLeave.getRegNo());

                ArrayList<String> regNoList = this.colourVehicleMap.get(carToLeave.getColor());
                if (regNoList.contains(carToLeave.getRegNo())) {
                    regNoList.remove(carToLeave.getRegNo());
                }

                // Add the slot No. back to available slot list.
                this.availableSlotList.add(Integer.parseInt(slotNo));
                System.out.println("Slot number " + slotNo + " is free");

            } else {
                System.out.println("Slot number " + slotNo + " is already empty");
            }
        } else {
            System.out.println("Parking lot is empty");
            System.out.println();
        }
    }

    /**
     * Prints to standard output, the current status of Parking Lot 
     */
    public void status() {
        if (this.capacity == 0) {

            System.out.println("Sorry, parking lot is not created");

        } else if (this.slotVehicleMap.size() > 0) {

        	// Print the current status.
            System.out.println("Slot No.\tRegistration No.\tColor");

            Vehicle vehicle;
            for (int i = 1; i <= this.capacity; i++) {

                String key = Integer.toString(i);
                if (this.slotVehicleMap.containsKey(key)) {
                    vehicle = this.slotVehicleMap.get(key);
                    System.out.println(i + "\t" + vehicle.getRegNo() + "\t" + vehicle.getColor());
                }

            }
        } else {
            System.out.println("Parking lot is empty");
        }
    }

    /**
     * Fetches the vehicle's reg number by color provided
     * @param color
     */
    public void getRegistrationNumbersFromColor(String color) {
        if (this.capacity == 0) {

            System.out.println("Sorry, parking lot is not created");

        } else if (this.colourVehicleMap.containsKey(color)) {

            ArrayList<String> regNoList = this.colourVehicleMap.get(color);
            System.out.println();

            for (int i=0; i < regNoList.size(); i++) {

            	if (!(i==regNoList.size() - 1)){
                    System.out.print(regNoList.get(i) + ",");
                } else {
                    System.out.print(regNoList.get(i));
                }
            }
        } else {
            System.out.println("Not found");
        }
    }

    /**
     * Get Slot number of vehicles of same colour
     * @param color
     */
    public void getSlotNumbersFromColor(String color) {
        if (this.capacity == 0) {

            System.out.println("Sorry, parking lot is not created");

        } else if (this.colourVehicleMap.containsKey(color)) {

            ArrayList<String> regNoList = this.colourVehicleMap.get(color);
            ArrayList<Integer> slotList = new ArrayList<Integer>();
            System.out.println();

            for (int i=0; i < regNoList.size(); i++) {
                slotList.add(Integer.valueOf(this.regNoSlotMap.get(regNoList.get(i))));
            }

            Collections.sort(slotList);
            for (int j=0; j < slotList.size(); j++) {

                if (!(j == slotList.size() - 1)) {
                    System.out.print(slotList.get(j) + ",");
                } else {
                    System.out.print(slotList.get(j));
                }
            }
            System.out.println();
        } else {
            System.out.println("Not found");
            System.out.println();
        }
    }

    /**
     * @param regNo
     */
    public void getSlotNumberFromRegNo(String regNo) {

        if (this.capacity == 0) {

            System.out.println("Sorry, parking lot is not created");

        } else if (this.regNoSlotMap.containsKey(regNo)) {

            System.out.println(this.regNoSlotMap.get(regNo));

        } else {

            System.out.println("Not found");
            System.out.println();
        }
    }

	public List<Integer> getAvailableSlotList() {
		return availableSlotList;
	}

	public void setAvailableSlotList(List<Integer> availableSlotList) {
		this.availableSlotList = availableSlotList;
	}

	public Map<String, Vehicle> getSlotVehicleMap() {
		return slotVehicleMap;
	}

	public void setSlotVehicleMap(Map<String, Vehicle> slotVehicleMap) {
		this.slotVehicleMap = slotVehicleMap;
	}

	public Map<String, String> getRegNoSlotMap() {
		return regNoSlotMap;
	}

	public void setRegNoSlotMap(Map<String, String> regNoSlotMap) {
		this.regNoSlotMap = regNoSlotMap;
	}

	public Map<String, ArrayList<String>> getColourVehicleMap() {
		return colourVehicleMap;
	}

	public void setColourVehicleMap(Map<String, ArrayList<String>> colourVehicleMap) {
		this.colourVehicleMap = colourVehicleMap;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}
