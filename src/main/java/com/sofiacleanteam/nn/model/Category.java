package com.sofiacleanteam.nn.model;

public enum Category {

	COMPLETE_CLEANING("Основно почистване"), RESIDENTIAL_CLEANING("Почистване на домове"),
	OFFICE_CLEANING("Почистване на офиси"), SUBSCRIPTION("Абонаментен план"),
	WINDOWS_CLEANING("Почистване на прозорци"), FURNITURE_WASH("Пране на мека мебел"),
	CARPET_WASH("Пране на килими"), OTHERS("Други услуги")
	;

	private String category;

	Category(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return category;
	}
}
