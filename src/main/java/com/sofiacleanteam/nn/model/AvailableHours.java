package com.sofiacleanteam.nn.model;

public enum AvailableHours {
	
	H9("09:00"), H10("10:00"), H11("11:00"), H12("12:00"), H13("13:00"),
	H14("14:00"), H15("15:00"), H16("16:00"), H17("17:00"), H18("18:00");

	
	public final String hour;

	private AvailableHours(String hour) {
		this.hour = hour;	
	}

//	@Override 
//	public String toString() {
//		return hour;
//	}
}
