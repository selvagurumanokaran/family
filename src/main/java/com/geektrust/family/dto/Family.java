package com.geektrust.family.dto;

import java.util.ArrayList;
import java.util.List;

public class Family {

    private Person husband;
    private Person wife;
    private Family parentFamily;
    private List<Family> childFamilies;

    public Family(Person child) {
	if (child.getSex().equals(Sex.MALE)) {
	    this.husband = child;
	} else {
	    this.wife = child;
	}
	this.childFamilies = new ArrayList<>();
    }

    public Family(Person husband, Person wife) {
	this.husband = husband;
	this.wife = wife;
	this.childFamilies = new ArrayList<>();
    }

    public Person getHusband() {
	return husband;
    }

    public Person getWife() {
	return wife;
    }

    public List<Family> getChildFamilies() {
	return childFamilies;
    }

    public Family getParentFamily() {
	return parentFamily;
    }

    public void setParentFamily(Family parentFamily) {
	this.parentFamily = parentFamily;
    }

    public Person getDirectChild() {
	if (this.husband != null && this.husband.isDirectChild()) {
	    return this.husband;
	}
	return this.wife;
    }

    public Person getSpouse(Person person) {
	if (this.husband != null && this.husband.getName().equals(person.getName())) {
	    return this.wife;
	}
	return this.husband;
    }

    public Person getPersonByName(String name) {
	if (this.husband != null && this.husband.getName().equals(name)) {
	    return this.husband;
	}
	return this.wife;
    }

}
