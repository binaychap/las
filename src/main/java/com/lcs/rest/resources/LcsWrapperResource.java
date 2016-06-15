package com.lcs.rest.resources;

import java.util.Set;

import org.springframework.hateoas.ResourceSupport;

import com.lcs.entities.Lcs;

public class LcsWrapperResource extends ResourceSupport {
	private Set<Lcs> setOfStrings;


    public Set<Lcs> getSetOfStrings() {
        return setOfStrings;
    }

    public void setSetOfStrings(Set<Lcs> setOfStrings) {
        this.setOfStrings = setOfStrings;
    }
}
