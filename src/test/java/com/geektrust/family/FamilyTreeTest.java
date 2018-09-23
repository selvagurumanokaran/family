package com.geektrust.family;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.geektrust.family.dto.Family;
import com.geektrust.family.dto.Person;
import com.geektrust.family.dto.Relationship;
import com.geektrust.family.dto.Sex;
import com.geektrust.family.service.FamiliyTree;

public class FamilyTreeTest {

    private FamiliyTree subject;

    @Before
    public void setup() {
	createInitialFamilyTree();
    }

    @Test
    public void testRelationshipBrotherInLaw() {

	Person person = subject.getPersonByName("Vich");
	Set<String> brotherInLaws = subject.getRelations(person, Relationship.BROTHER_IN_LAW);
	assertEquals(brotherInLaws.size(), 1);
	assertEquals(brotherInLaws.iterator().next(), "Vyan");

	person = subject.getPersonByName("Driya");
	brotherInLaws = subject.getRelations(person, Relationship.BROTHER_IN_LAW);
	assertEquals(brotherInLaws.size(), 0);

	person = subject.getPersonByName("King Shan");
	brotherInLaws = subject.getRelations(person, Relationship.BROTHER_IN_LAW);
	assertEquals(brotherInLaws.size(), 0);
    }

    @Test
    public void testRelationshipBrother() {
	Person person = subject.getPersonByName("Satvy");
	Set<String> brothers = subject.getRelations(person, Relationship.BROTHER);
	assertEquals(brothers.size(), 2);
	Iterator<String> it = brothers.iterator();
	assertEquals("Saayan", it.next());
	assertEquals("Savya", it.next());

	person = subject.getPersonByName("King Shan");
	brothers = subject.getRelations(person, Relationship.BROTHER);
	assertEquals(brothers.size(), 0);
    }

    @Test
    public void testChildren() {
	Person person = subject.getPersonByName("Vila");
	Set<String> children = subject.getRelations(person, Relationship.CHILDREN);
	assertEquals(children.size(), 1);
	Iterator<String> it = children.iterator();
	assertEquals("Lavnya", it.next());

	person = subject.getPersonByName("Jnki");
	children = subject.getRelations(person, Relationship.CHILDREN);
	assertEquals(children.size(), 1);
	it = children.iterator();
	assertEquals("Lavnya", it.next());
    }

    @Test
    public void testCousin() {
	Person person = subject.getPersonByName("Vila");
	Set<String> result = subject.getRelations(person, Relationship.COUSIN);
	assertEquals(result.size(), 5);
	Iterator<String> it = result.iterator();
	assertEquals("Saayan", it.next());
	assertEquals("Satvy", it.next());
	assertEquals("Drita", it.next());
	assertEquals("Vrita", it.next());
	assertEquals("Savya", it.next());

	person = subject.getPersonByName("Gru");
	result = subject.getRelations(person, Relationship.COUSIN);
	assertEquals(result.size(), 0);

	person = subject.getPersonByName("Lavnya");
	result = subject.getRelations(person, Relationship.COUSIN);
	assertEquals(result.size(), 0);

	person = subject.getPersonByName("King Shan");
	result = subject.getRelations(person, Relationship.COUSIN);
	assertEquals(result.size(), 0);
    }

    @Test
    public void testDaughter() {
	Person person = subject.getPersonByName("Lika");
	Set<String> result = subject.getRelations(person, Relationship.DAUGHTER);
	assertEquals(result.size(), 1);
	Iterator<String> it = result.iterator();
	assertEquals("Chika", it.next());

	person = subject.getPersonByName("Vich");
	result = subject.getRelations(person, Relationship.DAUGHTER);
	assertEquals(result.size(), 1);

	person = subject.getPersonByName("Ish");
	result = subject.getRelations(person, Relationship.DAUGHTER);
	assertEquals(result.size(), 0);
    }

    @Test
    public void testFather() {
	Person person = subject.getPersonByName("Vich");
	Set<String> result = subject.getRelations(person, Relationship.FATHER);
	assertEquals(result.size(), 1);
	Iterator<String> it = result.iterator();
	assertEquals("King Shan", it.next());

	person = subject.getPersonByName("King Shan");
	result = subject.getRelations(person, Relationship.FATHER);
	assertEquals(result.size(), 0);
    }

    @Test
    public void testGrandDaughter() {
	Person person = subject.getPersonByName("King Shan");
	Set<String> result = subject.getRelations(person, Relationship.GRAND_DAUGHTER);
	assertEquals(result.size(), 2);
	Iterator<String> it = result.iterator();
	assertEquals("Satvy", it.next());
	assertEquals("Chika", it.next());

	person = subject.getPersonByName("Queen Anga");
	result = subject.getRelations(person, Relationship.GRAND_DAUGHTER);
	assertEquals(result.size(), 2);
	it = result.iterator();
	assertEquals("Satvy", it.next());
	assertEquals("Chika", it.next());

	person = subject.getPersonByName("Lika");
	result = subject.getRelations(person, Relationship.GRAND_DAUGHTER);
	assertEquals(result.size(), 1);
	it = result.iterator();
	assertEquals("Lavnya", it.next());
    }

    @Test
    public void testMaternalAunt() {
	Person person = subject.getPersonByName("Saayan");
	Set<String> result = subject.getRelations(person, Relationship.MATERNAL_AUNT);
	assertEquals(result.size(), 2);
	Iterator<String> it = result.iterator();
	assertEquals("Lika", it.next());
	assertEquals("Ambi", it.next());

	person = subject.getPersonByName("Misa");
	result = subject.getRelations(person, Relationship.MATERNAL_AUNT);
	assertEquals(result.size(), 0);

	person = subject.getPersonByName("King Shan");
	result = subject.getRelations(person, Relationship.MATERNAL_AUNT);
	assertEquals(result.size(), 0);
    }

    @Test
    public void testMaternalUncle() {
	Person person = subject.getPersonByName("Saayan");
	Set<String> result = subject.getRelations(person, Relationship.MATERNAL_UNCLE);
	assertEquals(result.size(), 3);
	Iterator<String> it = result.iterator();
	assertEquals("Chit", it.next());
	assertEquals("Vich", it.next());
	assertEquals("Ish", it.next());

	person = subject.getPersonByName("Satvy");
	result = subject.getRelations(person, Relationship.MATERNAL_UNCLE);
	assertEquals(result.size(), 3);
	it = result.iterator();
	assertEquals("Chit", it.next());
	assertEquals("Vich", it.next());
	assertEquals("Ish", it.next());

	person = subject.getPersonByName("Kriya");
	result = subject.getRelations(person, Relationship.MATERNAL_UNCLE);
	assertEquals(result.size(), 0);

	person = subject.getPersonByName("King Shan");
	result = subject.getRelations(person, Relationship.MATERNAL_UNCLE);
	assertEquals(result.size(), 0);
    }

    @Test
    public void testMother() {
	Person person = subject.getPersonByName("Ish");
	Set<String> result = subject.getRelations(person, Relationship.MOTHER);
	assertEquals(result.size(), 1);
	Iterator<String> it = result.iterator();
	assertEquals("Queen Anga", it.next());

	person = subject.getPersonByName("King Shan");
	result = subject.getRelations(person, Relationship.MOTHER);
	assertEquals(result.size(), 0);
    }

    @Test
    public void testPaternalAunt() {
	Person person = subject.getPersonByName("Chika");
	Set<String> result = subject.getRelations(person, Relationship.PATERNAL_AUNT);
	assertEquals(result.size(), 2);
	Iterator<String> it = result.iterator();
	assertEquals("Satya", it.next());
	assertEquals("Ambi", it.next());

	person = subject.getPersonByName("King Shan");
	result = subject.getRelations(person, Relationship.PATERNAL_AUNT);
	assertEquals(result.size(), 0);
    }

    @Test
    public void testPaternalUncle() {
	Person person = subject.getPersonByName("Chika");
	Set<String> result = subject.getRelations(person, Relationship.PATERNAL_UNCLE);
	assertEquals(result.size(), 3);
	Iterator<String> it = result.iterator();
	assertEquals("Chit", it.next());
	assertEquals("Vyan", it.next());
	assertEquals("Ish", it.next());

	person = subject.getPersonByName("King Shan");
	result = subject.getRelations(person, Relationship.PATERNAL_UNCLE);
	assertEquals(result.size(), 0);
    }

    @Test
    public void testSisterInLaw() {
	Person person = subject.getPersonByName("Ish");
	Set<String> result = subject.getRelations(person, Relationship.SISTER_IN_LAW);
	assertEquals(result.size(), 2);
	Iterator<String> it = result.iterator();
	assertEquals("Lika", it.next());
	assertEquals("Ambi", it.next());

	person = subject.getPersonByName("Satvy");
	result = subject.getRelations(person, Relationship.SISTER_IN_LAW);
	assertEquals(result.size(), 2);
	it = result.iterator();
	assertEquals("Krpi", it.next());
	assertEquals("Mina", it.next());

	person = subject.getPersonByName("King Shan");
	result = subject.getRelations(person, Relationship.SISTER_IN_LAW);
	assertEquals(result.size(), 0);
    }

    @Test
    public void testSister() {
	Person person = subject.getPersonByName("Ish");
	Set<String> result = subject.getRelations(person, Relationship.SISTER);
	assertEquals(result.size(), 1);
	Iterator<String> it = result.iterator();
	assertEquals("Satya", it.next());

	person = subject.getPersonByName("King Shan");
	result = subject.getRelations(person, Relationship.SISTER);
	assertEquals(result.size(), 0);
    }

    @Test
    public void testSon() {
	Person person = subject.getPersonByName("Vich");
	Set<String> result = subject.getRelations(person, Relationship.SON);
	assertEquals(result.size(), 1);
	Iterator<String> it = result.iterator();
	assertEquals("Vila", it.next());

	person = subject.getPersonByName("King Shan");
	result = subject.getRelations(person, Relationship.SON);
	assertEquals(result.size(), 3);
	it = result.iterator();
	assertEquals("Chit", it.next());
	assertEquals("Vich", it.next());
	assertEquals("Ish", it.next());
    }

    @Test
    public void testNewBorn() {
	Person person = subject.getPersonByName("Lavnya");
	subject.addNewChild(person, "Vanya", Relationship.DAUGHTER);
	subject.addNewChild(person, "Backi", Relationship.DAUGHTER);

	Set<String> result = subject.getRelations(person, Relationship.DAUGHTER);
	assertEquals(result.size(), 2);
	Iterator<String> it = result.iterator();
	assertEquals("Vanya", it.next());
	assertEquals("Backi", it.next());

	person = subject.getPersonByName("Queen Anga");
	subject.addNewChild(person, "Angam", Relationship.DAUGHTER);
	result = subject.getRelations(person, Relationship.DAUGHTER);
	assertEquals(result.size(), 2);
	it = result.iterator();
	assertEquals("Satya", it.next());
	assertEquals("Angam", it.next());
    }

    @Test
    public void testMothersWithMostFemaleChildren() {
	Set<String> result = subject.getMothersWithMostChildren();
	assertEquals(result.size(), 4);

	Person person = subject.getPersonByName("Lavnya");
	subject.addNewChild(person, "Vanya", Relationship.DAUGHTER);
	subject.addNewChild(person, "Backi", Relationship.DAUGHTER);
	result = subject.getMothersWithMostChildren();
	assertEquals(result.size(), 1);
	Iterator<String> it = result.iterator();
	assertEquals("Lavnya", it.next());
    }

    private void createInitialFamilyTree() {

	Person king = new Person("King Shan", Sex.MALE);
	Person queen = new Person("Queen Anga", Sex.FEEMALE);

	Family kingFamily = new Family(king, queen);
	subject = new FamiliyTree(kingFamily);

	Person ish = new Person("Ish", Sex.MALE, true);
	Family ishFamily = new Family(ish);

	Person chit = new Person("Chit", Sex.MALE, true);
	Person ambi = new Person("Ambi", Sex.FEEMALE);
	Family chitFamily = new Family(chit, ambi);

	Person vich = new Person("Vich", Sex.MALE, true);
	Person lika = new Person("Lika", Sex.FEEMALE);
	Family vichFamily = new Family(vich, lika);

	Person satya = new Person("Satya", Sex.FEEMALE, true);
	Person vyan = new Person("Vyan", Sex.MALE);
	Family satyaFamily = new Family(vyan, satya);

	subject.addFamily(kingFamily, ishFamily);
	subject.addFamily(kingFamily, chitFamily);
	subject.addFamily(kingFamily, vichFamily);
	subject.addFamily(kingFamily, satyaFamily);

	Person drita = new Person("Drita", Sex.MALE, true);
	Person jaya = new Person("Jaya", Sex.FEEMALE);
	Family dritaFamily = new Family(drita, jaya);

	Person vrita = new Person("Vrita", Sex.MALE, true);
	Family vritaFamily = new Family(vrita);

	subject.addFamily(chitFamily, dritaFamily);
	subject.addFamily(chitFamily, vritaFamily);

	Person vila = new Person("Vila", Sex.MALE, true);
	Person jnki = new Person("Jnki", Sex.FEEMALE);
	Family vilaFamily = new Family(vila, jnki);

	Person kpila = new Person("Kpila", Sex.MALE);
	Person chika = new Person("Chika", Sex.FEEMALE, true);
	Family chikaFamily = new Family(kpila, chika);

	subject.addFamily(vichFamily, vilaFamily);
	subject.addFamily(vichFamily, chikaFamily);

	Person satvy = new Person("Satvy", Sex.FEEMALE, true);
	Person asva = new Person("Asva", Sex.MALE);
	Family satvyFamily = new Family(asva, satvy);

	Person savya = new Person("Savya", Sex.MALE, true);
	Person krpi = new Person("Krpi", Sex.FEEMALE);
	Family savyaFamily = new Family(savya, krpi);

	Person saayan = new Person("Saayan", Sex.MALE, true);
	Person mina = new Person("Mina", Sex.FEEMALE);
	Family saayanFamily = new Family(saayan, mina);

	subject.addFamily(satyaFamily, satvyFamily);
	subject.addFamily(satyaFamily, savyaFamily);
	subject.addFamily(satyaFamily, saayanFamily);

	Person jata = new Person("Jata", Sex.MALE, true);
	Family jataFamily = new Family(jata);

	Person driya = new Person("Driya", Sex.FEEMALE, true);
	Person mnu = new Person("Mnu", Sex.MALE);
	Family driyaFamily = new Family(mnu, driya);
	subject.addFamily(dritaFamily, jataFamily);
	subject.addFamily(dritaFamily, driyaFamily);

	Person lavnya = new Person("Lavnya", Sex.FEEMALE, true);
	Person gru = new Person("Gru", Sex.MALE);
	Family lavnyaFamily = new Family(gru, lavnya);
	subject.addFamily(vilaFamily, lavnyaFamily);

	Person kriya = new Person("Kriya", Sex.MALE, true);
	Family kriyaFamily = new Family(kriya);
	subject.addFamily(savyaFamily, kriyaFamily);

	Person misa = new Person("Misa", Sex.MALE, true);
	Family misaFamily = new Family(misa);
	subject.addFamily(saayanFamily, misaFamily);

    }
}
