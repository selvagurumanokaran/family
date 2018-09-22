package com.geektrust.family.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.geektrust.family.dto.Family;
import com.geektrust.family.dto.Person;
import com.geektrust.family.dto.Relationship;
import com.geektrust.family.dto.Sex;

public class FamiliyTree {

    private Family root;
    private Map<String, Family> familyMap;

    public FamiliyTree(Family root) {
	this.root = root;
	this.root.setParentFamily(null);
	familyMap = new HashMap<>();
	familyMap.put(root.getHusband().getName(), root);
	familyMap.put(root.getWife().getName(), root);
    }

    public void addFamily(Family family, Family childFamily) {
	family.getChildFamilies().add(childFamily);
	childFamily.setParentFamily(family);
	if (childFamily.getHusband() != null)
	    familyMap.put(childFamily.getHusband().getName(), childFamily);
	if (childFamily.getWife() != null)
	    familyMap.put(childFamily.getWife().getName(), childFamily);
    }

    public Set<String> getRelations(Person person, Relationship relationship) {
	Set<String> names = new HashSet<>();
	switch (relationship) {
	case BROTHER_IN_LAW:
	    Person spouse = getSpouse(person);
	    List<Person> spouseBrothers = getBrothers(spouse);

	    List<Person> sisters = getSisters(person);
	    List<Person> husbansOfSisters = new ArrayList<>();
	    sisters.stream().forEach((s) -> {
		Person husband = familyMap.get(s.getName()).getHusband();
		if (husband != null) {
		    husbansOfSisters.add(husband);
		}
	    });
	    husbansOfSisters.stream().forEach((p) -> names.add(p.getName()));
	    spouseBrothers.stream().forEach((p) -> names.add(p.getName()));
	    break;
	case BROTHER:
	    getBrothers(person).stream().forEach(p -> names.add(p.getName()));
	    break;
	case CHILDREN:
	    getChildren(person).stream().forEach(p -> names.add(p.getName()));
	    break;
	case COUSIN:
	    List<Person> cousins = new ArrayList<>();
	    if (person.isDirectChild()) {
		Family parentFamily = familyMap.get(person.getName()).getParentFamily();
		getSiblings(parentFamily.getHusband()).stream().forEach(p -> cousins.addAll(getChildren(p)));
		getSiblings(parentFamily.getWife()).stream().forEach(p -> cousins.addAll(getChildren(p)));
		cousins.stream().forEach(p -> names.add(p.getName()));
	    }
	    break;
	case DAUGHTER:
	    getDaughters(person).stream().forEach(d -> names.add(d.getName()));
	    break;
	case FATHER:
	    names.add(getFather(person).getName());
	    break;
	case GRAND_DAUGHTER:
	    getChildren(person).stream().forEach(c -> {
		getDaughters(c).stream().forEach(d -> names.add(d.getName()));
	    });
	    break;
	case MATERNAL_AUNT:
	    getSisters(getMother(person)).stream().forEach(s -> names.add(s.getName()));
	    getBrothers(getMother(person)).stream().forEach(b -> {
		Person s = getSpouse(b);
		if (s != null) {
		    names.add(s.getName());
		}
	    });
	    break;
	case MATERNAL_UNCLE:
	    getBrothers(getMother(person)).stream().forEach(b -> names.add(b.getName()));
	    getSisters(getMother(person)).stream().forEach(s -> {
		Person spous = getSpouse(s);
		if (spous != null) {
		    names.add(spous.getName());
		}
	    });
	    break;
	case MOTHER:
	    names.add(getMother(person).getName());
	    break;
	case PATERNAL_AUNT:
	    getSisters(getFather(person)).stream().forEach(s -> names.add(s.getName()));
	    getBrothers(getFather(person)).stream().forEach(b -> {
		Person s = getSpouse(b);
		if (s != null) {
		    names.add(s.getName());
		}
	    });
	    break;
	case PATERNAL_UNCLE:
	    getBrothers(getFather(person)).stream().forEach(b -> names.add(b.getName()));
	    getSisters(getFather(person)).stream().forEach(s -> {
		Person spous = getSpouse(s);
		if (spous != null) {
		    names.add(spous.getName());
		}
	    });
	    break;
	case SISTER_IN_LAW:
	    getSisters(getSpouse(person)).stream().forEach(s -> names.add(s.getName()));
	    getBrothers(person).forEach(b -> {
		Person s = getSpouse(b);
		if (s != null) {
		    names.add(s.getName());
		}
	    });
	    break;
	case SISTER:
	    getSisters(person).forEach(s -> names.add(s.getName()));
	    break;
	case SON:
	    getSons(person).forEach(s -> names.add(s.getName()));
	    break;
	default:
	    break;
	}
	return names;
    }

    private Person getMother(Person person) {
	return familyMap.get(person.getName()).getParentFamily().getWife();
    }

    private Person getFather(Person person) {
	return familyMap.get(person.getName()).getParentFamily().getHusband();
    }

    private List<Person> getChildren(Person person) {
	List<Person> children = new ArrayList<>();
	familyMap.get(person.getName()).getChildFamilies().forEach(f -> children.add(f.getDirectChild()));
	return children;
    }

    private List<Person> getSiblings(Person person) {
	List<Person> brothers = this.getBrothers(person);
	brothers.addAll(this.getSisters(person));
	return brothers;
    }

    private List<Person> getSisters(Person person) {
	if (person != null && person.isDirectChild()) {
	    Family parentFamily = familyMap.get(person.getName()).getParentFamily();
	    List<Person> sisters = this.getDaughters(parentFamily.getDirectChild());
	    sisters.remove(person);
	    return sisters;
	}
	return new ArrayList<>();
    }

    private List<Person> getBrothers(Person person) {
	if (person != null && person.isDirectChild()) {
	    Family parentFamily = familyMap.get(person.getName()).getParentFamily();
	    List<Person> sons = this.getSons(parentFamily.getDirectChild());
	    sons.remove(person);
	    return sons;
	}
	return new ArrayList<>();
    }

    private List<Person> getSons(Person person) {
	List<Person> sons = new ArrayList<>();
	familyMap.get(person.getName()).getChildFamilies().stream()
		.filter((c) -> c.getDirectChild().getSex().equals(Sex.MALE)).forEach((f) -> {
		    sons.add(f.getDirectChild());
		});
	return sons;
    }

    private List<Person> getDaughters(Person person) {
	List<Person> daughters = new ArrayList<>();
	familyMap.get(person.getName()).getChildFamilies().stream()
		.filter((c) -> c.getDirectChild().getSex().equals(Sex.FEEMALE)).forEach((f) -> {
		    daughters.add(f.getDirectChild());
		});
	return daughters;
    }

    public Family getRoot() {
	return root;
    }

    private Person getSpouse(Person person) {
	Family family = familyMap.get(person.getName());
	return family.getSpouse(person);
    }

    public Person getPersonByName(String name) {
	return familyMap.get(name).getPersonByName(name);
    }

    public void addNewChild(Person mother, String childName, Relationship relatopnship) {
	if (mother.getSex().equals(Sex.FEEMALE)) {

	    Sex sex = null;
	    if (relatopnship.equals(Relationship.DAUGHTER)) {
		sex = Sex.FEEMALE;
	    } else if (relatopnship.equals(Relationship.SON)) {
		sex = Sex.MALE;
	    }
	    if (sex != null) {
		Person person = new Person(childName, sex, true);
		Family newFamily = new Family(person);
		addFamily(familyMap.get(mother.getName()), newFamily);
	    }
	}

    }

    public Set<String> getMothersWithMostChildren() {
	Result result = new Result();
	getChildren(root.getWife()).forEach(c -> getMothersWithMostChildren(c, result));
	return result.names;
    }

    private void getMothersWithMostChildren(Person person, Result result) {

	Person wife = familyMap.get(person.getName()).getWife();
	if (wife != null) {
	    getChildren(wife).forEach(c -> getMothersWithMostChildren(c, result));

	    List<Person> daughters = getDaughters(wife);
	    if (daughters.size() == result.count) {
		result.names.add(wife.getName());
	    } else if (daughters.size() > result.count) {
		result.count = daughters.size();
		result.names = new HashSet<>();
		result.names.add(wife.getName());
	    }
	}
    }

    class Result {
	int count;
	Set<String> names;

	public Result() {
	    count = 0;
	    names = new HashSet<>();
	}
    }
}
