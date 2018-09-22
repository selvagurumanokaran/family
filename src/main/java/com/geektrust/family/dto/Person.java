package com.geektrust.family.dto;

public class Person {

    private String name;
    private Sex sex;
    private boolean isDirectChild;

    public Person(String name, Sex sex) {
	this.name = name;
	this.sex = sex;
    }

    public Person(String name, Sex sex, boolean isDirectChild) {
	this(name, sex);
	this.isDirectChild = isDirectChild;
    }

    public String getName() {
	return name;
    }

    public Sex getSex() {
	return sex;
    }

    public boolean isDirectChild() {
	return isDirectChild;
    }

}
