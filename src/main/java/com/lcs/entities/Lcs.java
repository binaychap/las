package com.lcs.entities;

public class Lcs {
	private String value;

	public Lcs() {
	}

	public Lcs(String value) {

		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "{" + "value='" + value + '\'' + '}';
	}

}
