package com.lcs.entities;

import java.util.HashSet;
import java.util.Set;

public class LcsWrapper {
	private Set<Lcs> setOfStrings = new HashSet<>();

	public Set<Lcs> getSetOfStrings() {
		return setOfStrings;
	}

	public void setSetOfStrings(Set<Lcs> setOfStrings) {
		this.setOfStrings = setOfStrings;
	}

}
