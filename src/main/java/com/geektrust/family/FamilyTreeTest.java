package com.geektrust.family;

import com.geektrust.family.dto.Family;
import com.geektrust.family.dto.Person;
import com.geektrust.family.dto.Relationship;
import com.geektrust.family.dto.Sex;
import com.geektrust.family.service.FamiliyTree;

public class FamilyTreeTest {

    private static FamiliyTree familiyTree;

    public static void main(String[] args) {
	createInitialFamilyTree();
	Person person;
	// Person person = familiyTree.getPersonByName("Vich");
	// System.out.println(familiyTree.getRelations(person,
	// Relationship.BROTHER_IN_LAW));

	// person = familiyTree.getPersonByName("Vich");
	// System.out.println(familiyTree.getRelations(person, Relationship.BROTHERS));

	// person = familiyTree.getPersonByName("Satya");
	// System.out.println(familiyTree.getRelations(person, Relationship.CHILDREN));
	// person = familiyTree.getPersonByName("Kriya");
	// System.out.println(familiyTree.getRelations(person, Relationship.COUSINS));

	// person = familiyTree.getPersonByName("Vich");
	// System.out.println(familiyTree.getRelations(person, Relationship.BROTHERS));
	person = familiyTree.getPersonByName("Lavnya");
	familiyTree.addNewChild(person, "Vanya", Relationship.DAUGHTER);
	familiyTree.addNewChild(person, "Backi", Relationship.DAUGHTER);

	Person p = familiyTree.getPersonByName("Jnki");
	System.out.println(familiyTree.getRelations(p, Relationship.GRAND_DAUGHTER));

	System.out.println(familiyTree.getMothersWithMostChildren());

    }

    private static void createInitialFamilyTree() {

	Person king = new Person("King Shan", Sex.MALE);
	Person queen = new Person("Queen Anga", Sex.FEEMALE);

	Family kingFamily = new Family(king, queen);
	familiyTree = new FamiliyTree(kingFamily);

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

	familiyTree.addFamily(kingFamily, ishFamily);
	familiyTree.addFamily(kingFamily, chitFamily);
	familiyTree.addFamily(kingFamily, vichFamily);
	familiyTree.addFamily(kingFamily, satyaFamily);

	Person drita = new Person("Drita", Sex.MALE, true);
	Person jaya = new Person("Jaya", Sex.FEEMALE);
	Family dritaFamily = new Family(drita, jaya);

	Person vrita = new Person("Vrita", Sex.MALE, true);
	Family vritaFamily = new Family(vrita);

	familiyTree.addFamily(chitFamily, dritaFamily);
	familiyTree.addFamily(chitFamily, vritaFamily);

	Person vila = new Person("Vila", Sex.MALE, true);
	Person jnki = new Person("Jnki", Sex.FEEMALE);
	Family vilaFamily = new Family(vila, jnki);

	Person kpila = new Person("Kpila", Sex.MALE);
	Person chika = new Person("Chika", Sex.FEEMALE, true);
	Family chikaFamily = new Family(kpila, chika);

	familiyTree.addFamily(vichFamily, vilaFamily);
	familiyTree.addFamily(vichFamily, chikaFamily);

	Person satvy = new Person("Satvy", Sex.FEEMALE, true);
	Person asva = new Person("Asva", Sex.MALE);
	Family satvyFamily = new Family(asva, satvy);

	Person savya = new Person("Savya", Sex.MALE, true);
	Person krpi = new Person("Krpi", Sex.FEEMALE);
	Family savyaFamily = new Family(savya, krpi);

	Person saayan = new Person("Saayan", Sex.MALE, true);
	Person mina = new Person("Mina", Sex.FEEMALE);
	Family saayanFamily = new Family(saayan, mina);

	familiyTree.addFamily(satyaFamily, satvyFamily);
	familiyTree.addFamily(satyaFamily, savyaFamily);
	familiyTree.addFamily(satyaFamily, saayanFamily);

	Person jata = new Person("Jata", Sex.MALE, true);
	Family jataFamily = new Family(jata);

	Person driya = new Person("Driya", Sex.FEEMALE, true);
	Person mnu = new Person("Mnu", Sex.MALE);
	Family driyaFamily = new Family(mnu, driya);
	familiyTree.addFamily(dritaFamily, jataFamily);
	familiyTree.addFamily(dritaFamily, driyaFamily);

	Person lavnya = new Person("Lavnya", Sex.FEEMALE, true);
	Person gru = new Person("Gru", Sex.MALE);
	Family lavnyaFamily = new Family(gru, lavnya);
	familiyTree.addFamily(vilaFamily, lavnyaFamily);

	Person kriya = new Person("Kriya", Sex.MALE, true);
	Family kriyaFamily = new Family(kriya);
	familiyTree.addFamily(savyaFamily, kriyaFamily);

	Person misa = new Person("Misa", Sex.MALE, true);
	Family misaFamily = new Family(misa);
	familiyTree.addFamily(saayanFamily, misaFamily);

    }
}
