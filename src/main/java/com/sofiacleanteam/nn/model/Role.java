package com.sofiacleanteam.nn.model;

public enum Role {
	
	ADMIN("ADMIN"), CUSTOMER("CUSTOMER"),  ;

	
	private String userType;

	Role(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return userType;
	}
}
