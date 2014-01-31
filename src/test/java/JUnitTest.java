import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import mpr.proj.CRUD;
import mpr.proj.pedigree.Breeder;
import mpr.proj.pedigree.Color;
import mpr.proj.pedigree.Country;
import mpr.proj.pedigree.DateOfBirth;
import mpr.proj.pedigree.Horse;
import mpr.proj.pedigree.Sex;

import org.junit.Test;

public class JUnitTest {

	@Test
	public void BreederTableTest() {
		Random generate = new Random();
		String randName = "" + generate.nextInt(5000);
		Breeder testBreeder = new Breeder(0, randName, new Country(7, "Angli","AN"));
		CRUD.insertBreeder(testBreeder);
		try {
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
			String queryStr = "SELECT * FROM BREEDER WHERE NAME='" + randName+ "'";
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(queryStr);
			assertEquals(true, result.next());
			assertEquals(result.getString(2), randName);
			testBreeder.setName("Bloodlust");
			CRUD.modifyBreederEntry(result.getInt(1), testBreeder);
			assertEquals("Bloodlust", CRUD.downloadBreeder(result.getInt(1)).getName());
			CRUD.deleteBreeder(result.getInt(1));
			assertEquals(null, CRUD.downloadBreeder(result.getInt(1)));
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void horsesTableTest() {
		Random gen = new Random();
		String test = "" + gen.nextInt(5000);
		Horse testHorse = new Horse(0, test, Sex.STALLION, new DateOfBirth(
				new Date(1500)), new Color(0, test, test),
				CRUD.downloadHorse(2), CRUD.downloadHorse(2), new Breeder(1,test, null));
		CRUD.insertHorse(testHorse);
		try {
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
			String queryStr = "SELECT * FROM HORSE WHERE NAME='" + test + "'";
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(queryStr);
			assertEquals(true, result.next());
			assertEquals(result.getString(2), test);
			testHorse.setName("Bloodlust");
			CRUD.modifyHorseEntry(result.getInt(1), testHorse);
			assertEquals("Bloodlust", CRUD.downloadHorse(result.getInt(1)).getName());
			CRUD.deleteHorse(result.getInt(1));
			assertEquals(null, CRUD.downloadHorse(result.getInt(1)));
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void countryTableTest() {
		Random gen = new Random();
		String test1 = "" + gen.nextInt(5000);
		String test3 = "" + gen.nextInt(5000);
		Country testCountry = new Country(0, test1, "BZ");
		CRUD.insertCountry(testCountry);
		try {
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
			String queryStr = "SELECT * FROM COUNTRY WHERE NAME='" + test1+ "'";
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(queryStr);
			assertEquals(true, result.next());
			assertEquals(result.getString(2), test1);
			testCountry.setName(test3);
			CRUD.modifyCountryEntry(result.getInt(1), testCountry);
			assertEquals(test3, CRUD.downloadCountry(result.getInt(1)).getName());
			CRUD.deleteCountry(result.getInt(1));
			assertEquals(null, CRUD.downloadCountry(result.getInt(1)));
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void colourTableTest() {
		Random gen = new Random();
		String test1 = "" + gen.nextInt(5000);
		String test3 = "" + gen.nextInt(5000);
		Color testColour = new Color(0, test1, test3);
		CRUD.insertColour(testColour);
		try {
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
			String queryStr = "SELECT * FROM COLOR WHERE LNAME='" + test1 + "'";
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(queryStr);
			assertEquals(true, result.next());
			assertEquals(result.getString(2), test1);
			testColour.setLname(test3);
			CRUD.modifyColourEntry(result.getInt(1), testColour);
			assertEquals(test3, CRUD.downloadColour(result.getInt(1)).getLname());
			CRUD.deleteColour(result.getInt(1));
			assertEquals(null, CRUD.downloadColour(result.getInt(1)));
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
