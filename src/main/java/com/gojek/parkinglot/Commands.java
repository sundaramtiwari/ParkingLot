package com.gojek.parkinglot;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.HashMap;

/**
 * 
 * @author sundaram
 *
 */
public class Commands {

	private static final String PARK = "park";

	private static final String LEAVE = "leave";

	private static final String STATUS = "status";

	private static final String CREATE_PARKING_LOT = "create_parking_lot";

	private static final String SLOT_NUMBERS_FOR_CARS_WITH_COLOUR = "slot_numbers_for_cars_with_colour";

	private static final String SLOT_NUMBER_FOR_REGISTRATION_NUMBER = "slot_number_for_registration_number";

	private static final String REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR = "registration_numbers_for_cars_with_colour";

	public Map<String, Method> commandsMap;

	public Commands() {
		commandsMap = new HashMap<String, Method>();
		try {
			populateCommandsHashMap();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	private void populateCommandsHashMap() throws NoSuchMethodException {

		commandsMap.put(STATUS, ParkingLot.class.getMethod(STATUS));

		commandsMap.put(LEAVE, ParkingLot.class.getMethod(LEAVE, String.class));

		commandsMap.put(PARK, ParkingLot.class.getMethod(PARK, String.class, String.class));

		commandsMap.put(CREATE_PARKING_LOT, ParkingLot.class.getMethod("createParkingLot", String.class));

		commandsMap.put(REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR,
				ParkingLot.class.getMethod("getRegistrationNumbersFromColor", String.class));
		
		commandsMap.put(SLOT_NUMBERS_FOR_CARS_WITH_COLOUR,
				ParkingLot.class.getMethod("getSlotNumbersFromColor", String.class));
		
		commandsMap.put(SLOT_NUMBER_FOR_REGISTRATION_NUMBER,
				ParkingLot.class.getMethod("getSlotNumberFromRegNo", String.class));
	}
}
