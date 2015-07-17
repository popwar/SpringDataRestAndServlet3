package org.lu.utils;

public enum Gender {
	MALE(1), FEMALE(2);
	private int type;

	private Gender(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
}
