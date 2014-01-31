package mpr.proj;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mpr.proj.pedigree.*;

public abstract class CRUD {

	public static void addHorse() {

		insertHorse(getHorseIntel());
	}

	public static void showHorses() {
		List<Horse> Horses = getAllHorses();

		for (Horse hoars : Horses) {
			System.out.println("ID: " + hoars.getID() + " Name: "
					+ hoars.getName() + " Sex: " + hoars.getSex().toString()
					+ " Colour: " + hoars.getColor().getLname()
					+ " Date of Birth: " + hoars.getDob().getDate().toString()
					+ " Father: " + ifHorseNameNull(hoars.getSire())
					+ " Father ID: " + ifHorseIdNull(hoars.getSire())
					+ " Mother: " + ifHorseNameNull(hoars.getDam())
					+ " Mother ID: " + ifHorseIdNull(hoars.getDam())
					+ " Breeder: " + hoars.getBreeder().getName()
					+ " Breeder ID: " + hoars.getBreeder().getId()
					+ " Country: " + hoars.getBreeder().getCountry().getName());
		}

	}

	public static void deleteHorse(int choice) {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
			String query = "DELETE FROM HORSE WHERE ID=(?)";
			PreparedStatement state = con.prepareStatement(query);
			state.setInt(1, choice);
			state.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void modifyHorse() {
		System.out.println("Which Horse would You like to modify?");
		modifyHorseEntry(EasyIn.getInt(), getHorseIntel());
	}

	public static void modifyHorseEntry(int idChange, Horse horse) {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
			String query = "UPDATE HORSE SET NAME=(?), SEX=(?), COLOR=(?), DOB=(?), DAM=(?), SIRE=(?), BREEDER=(?) WHERE ID=(?)";
			PreparedStatement state = con.prepareStatement(query);
			state.setString(1, horse.getName());
			state.setInt(2, horse.getIntOfSex());
			state.setInt(3, horse.getColor().getID());
			state.setString(4, horse.getDob().getDate().toString());
			state.setInt(5, (int) ifHorseIdNull(horse.getDam()));
			state.setInt(6, (int) ifHorseIdNull(horse.getSire()));
			state.setInt(7, (int) horse.getBreeder().getId());
			state.setInt(8, (int) idChange);
			state.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static List<Horse> getAllHorses() {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
			List<Horse> Horses = new ArrayList<Horse>();
			String query = "SELECT * FROM HORSE";
			Statement state = con.createStatement();
			ResultSet result = state.executeQuery(query);
			while (result.next()) {
				Horses.add(
						(int) result.getLong("ID"),
						new Horse(result.getLong("ID"), result
								.getString("NAME"), Sex.valueOf(result
								.getInt("SEX")), new DateOfBirth(result
								.getDate("DOB")), downloadColour(result
								.getInt("COLOR")), downloadHorse(result
								.getInt("SIRE")), downloadHorse(result
								.getInt("DAM")), downloadBreeder(result
								.getInt("BREEDER"))));
			}
			con.close();
			return Horses;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public static Horse downloadHorse(int idk) {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
			String query = "SELECT * FROM HORSE WHERE id=" + idk;
			Statement state = con.createStatement();
			ResultSet result = state.executeQuery(query);
			if (result.next()) {
				con.close();
				return new Horse(result.getLong("ID"),
						result.getString("NAME"), Sex.valueOf(result
								.getInt("SEX")), new DateOfBirth(
								result.getDate("DOB")),
						downloadColour(result.getInt("COLOR")),
						downloadHorse(result.getInt("SIRE")),
						downloadHorse(result.getInt("DAM")),
						downloadBreeder(result.getInt("BREEDER")));

			} else {
				con.close();
				return null;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		return null;

	}

	public static void insertHorse(Horse horse) {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
			String query = "INSERT INTO HORSE (NAME , SEX, COLOR , DOB, YEARONLY, DAM, SIRE , BREEDER) VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement state = con.prepareStatement(query);
			state.setString(1, horse.getName());
			state.setInt(2, horse.getIntOfSex());
			state.setInt(3, horse.getColor().getID());
			state.setString(4, horse.getDob().getDate().toString());
			state.setBoolean(5, false);
			if (horse.getDam() == null) {
				state.setNull(6, ifHorseIdNull(horse.getDam()));
			} else {
				state.setInt(6, ifHorseIdNull(horse.getDam()));
			}
			if (horse.getSire() == null) {
				state.setNull(7, (int) ifHorseIdNull(horse.getSire()));
			} else {
				state.setInt(7, (int) ifHorseIdNull(horse.getSire()));
			}
			state.setInt(8, (int) horse.getBreeder().getId());
			state.executeUpdate();
			if(horse.getSire()==null||horse.getDam()==null){
				System.out.println("Error! Bad Parent information!");
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static String ifHorseNameNull(Horse horse) {
		if (horse == null) {
			return "No Parent!";
		} else {
			return horse.getName();
		}
	}

	public static Integer ifHorseIdNull(Horse horse) {
		Integer i = null;
		if (horse == null) {
			return i;
		} else {
			return (int) horse.getID();
		}
	}

	public static Horse getHorseIntel() {
		EasyIn.clear();
		System.out.println("Whats the name of Your horse?");
		String name = EasyIn.getString();
		System.out.println("What is the sex of Your horse? 1) Gelding 2) Mare 3) Stallion");
		Sex horseSex = Sex.valueOf(EasyIn.getInt());
		DateOfBirth dob = insertDateofBirth();
		System.out.println("Whats the ID of your horses colour?");
		int color = EasyIn.getInt();
		System.out.println("Whats the ID of the Father?");
		int fatherID = EasyIn.getInt();
		Horse father = checkIfAbleToBeFather(dob, downloadHorse(fatherID));
		System.out.println("Whats the ID of the Mother?");
		int motherID = EasyIn.getInt();
		Horse mother = checkIfAbleToBeMother(dob, downloadHorse(motherID));
		System.out.println("Whats the ID of the Breeder?");
		int breederID = EasyIn.getInt();
		Breeder breeder = downloadBreeder(breederID);
		return new Horse(0, name, horseSex, dob, downloadColour(color), mother,
				father, breeder);

	}

	private static Horse checkIfAbleToBeMother(DateOfBirth dob, Horse horse) {
		if (horse.getSex() == Sex.MARE) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(dob.getDate());
			cal.add(Calendar.YEAR, -3);
			java.util.Date dobTemp = cal.getTime();
			if (dob.getDate().compareTo(dobTemp) < 0) {
				cal.setTime(dob.getDate());
				cal.add(Calendar.YEAR, +30);
				java.util.Date dobTemp2 = cal.getTime();
				if (dob.getDate().compareTo(dobTemp2) < 0) {
					return horse;
				}
			}
		}
		return null;
	}

	private static Horse checkIfAbleToBeFather(DateOfBirth dob, Horse horse) {
		if (horse.getSex() == Sex.STALLION) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(dob.getDate());
			cal.add(Calendar.YEAR, -2);
			java.util.Date dobTemp = cal.getTime();
			if (dob.getDate().compareTo(dobTemp) < 0) {
				cal.setTime(dob.getDate());
				cal.add(Calendar.YEAR, +30);
				java.util.Date dobTemp2 = cal.getTime();
				if (dob.getDate().compareTo(dobTemp2) < 0) {
					return horse;
				}
			}
		}
		return null;
	}

	private static DateOfBirth insertDateofBirth() {

		System.out.println("In which year has the calf been born?");
		int year = EasyIn.getInt();
		System.out.println("In which month has the calf been born?");
		int month = EasyIn.getInt();
		System.out.println("On which day has the calf been born?");
		int day = EasyIn.getInt();
		return new DateOfBirth(new Date(year, month, day));

	}

	public static void addBreeder() {

		insertBreeder(getBreederIntel());

	}

	public static void showBreeders() {
		List<Breeder> Breeders = getAllBreeders();
		for (Breeder bree : Breeders) {
			System.out.println("Breeder ID: " + bree.getId()
					+ " Breeder Name: " + bree.getName() + " Country: "
					+ bree.getCountry().getName());
		}

	}

	public static void deleteBreeder(int choice) {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
			String query = "DELETE FROM BREEDER WHERE ID=(?)";
			PreparedStatement state = con.prepareStatement(query);
			state.setInt(1, choice);
			state.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void modifyBreeder() {
		System.out.println("Which Breeder would You like to modify?");
		modifyBreederEntry(EasyIn.getInt(), getBreederIntel());
	}

	public static void modifyBreederEntry(int choice, Breeder breeder) {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
			String query = "UPDATE BREEDER SET NAME=(?), COUNTRY=(?) WHERE ID=(?)";
			PreparedStatement state = con.prepareStatement(query);
			state.setString(1, breeder.getName());
			state.setInt(2, breeder.getCountry().getId());
			state.setLong(3, (long) choice);
			state.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static List<Breeder> getAllBreeders() {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
			List<Breeder> Breeders = new ArrayList<Breeder>();
			String query = "SELECT * FROM BREEDER";
			Statement state = con.createStatement();
			ResultSet result = state.executeQuery(query);
			while (result.next()) {
				Breeders.add(
						(int) result.getLong("ID"),
						new Breeder(result.getLong("ID"), result
								.getString("NAME"), downloadCountry(result
								.getInt("COUNTRY"))));
			}
			con.close();
			return Breeders;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public static Breeder downloadBreeder(int idk) {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
			String query = "SELECT * FROM BREEDER WHERE ID=" + idk;
			Statement state = con.createStatement();
			ResultSet result = state.executeQuery(query);
			if (result.next()) {
				return new Breeder(result.getLong("ID"),
						result.getString("NAME"),
						downloadCountry(result.getInt("COUNTRY")));
			} else {
				con.close();
				return null;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static void insertBreeder(Breeder breeder) {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
			String query = "INSERT INTO BREEDER (NAME , COUNTRY) VALUES (?,?)";
			PreparedStatement state = con.prepareStatement(query);
			state.setString(1, breeder.getName());
			state.setInt(2, breeder.getCountry().getId());
			state.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static Breeder getBreederIntel() {
		System.out.println("Whats the name of the Breeder?");
		String name = EasyIn.getString();
		System.out
				.println("What is the ID of the country the breeder comes from?");
		int countryID = EasyIn.getInt();
		Country country = downloadCountry(countryID);
		return new Breeder(0, name, country);
	}

	public static void addColour() {
		insertColour(getColourIntel());

	}

	public static void showColours() {
		List<Color> Colours = getAllColours();
		for (Color col : Colours) {
			System.out.println(" ID: " + col.getID() + " Name: "
					+ col.getLname() + " Short Name: " + col.getSname());
		}
	}

	public static void deleteColour(int choice) {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
			String query = "DELETE FROM COLOR WHERE ID=(?)";
			PreparedStatement state = con.prepareStatement(query);
			state.setInt(1, choice);
			state.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void modifyColour() {
		System.out.println("Which colour would You like to modify? ");
		modifyColourEntry(EasyIn.getInt(), getColourIntel());

	}

	public static void modifyColourEntry(int choice, Color kolor) {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
			String query = "UPDATE COLOR SET LNAME=(?), SNAME=(?) WHERE ID=(?)";
			PreparedStatement state = con.prepareStatement(query);
			state.setString(1, kolor.getLname());
			state.setString(2, kolor.getSname());
			state.setLong(3, (long) choice);
			state.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static List<Color> getAllColours() {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
			List<Color> Colours = new ArrayList<Color>();
			String query = "SELECT * FROM COLOR";
			Statement state = con.createStatement();
			ResultSet result = state.executeQuery(query);
			while (result.next()) {
				Colours.add(new Color(result.getInt("ID"), result
						.getString("LNAME"), result.getString("SNAME")));
			}
			con.close();
			return Colours;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public static Color downloadColour(int idk) {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
			String query = "SELECT * FROM COLOR WHERE id=" + idk;
			Statement state = con.createStatement();
			ResultSet result = state.executeQuery(query);
			if (result.next()) {
				return new Color(result.getInt("ID"),
						result.getString("LNAME"), result.getString("SNAME"));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static void insertColour(Color color) {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
			String query = "INSERT INTO COLOR (LNAME , SNAME) VALUES (?,?)";
			PreparedStatement state = con.prepareStatement(query);
			state.setString(1, color.getLname());
			state.setString(2, color.getSname());
			state.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static Color getColourIntel() {
		System.out.println("What colour would You like to add?");
		String name = EasyIn.getString();
		System.out.println("What is the short name for that colour?");
		String shortName = EasyIn.getString();
		return new Color(0, name, shortName);
	}

	public static void addCountry() {
		insertCountry(getCountryIntel());
	}

	public static void showCountries() {
		List<Country> Countries = getAllCountries();
		for (Country count : Countries) {
			System.out.println(" ID: " + count.getId() + " Name: "
					+ count.getName() + " Country Code: " + count.getCode());
		}

	}

	public static void deleteCountry(int choice) {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
			String query = "DELETE FROM COUNTRY WHERE ID=(?)";
			PreparedStatement state = con.prepareStatement(query);
			state.setInt(1, choice);
			state.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void modifyCountry() {
		System.out.println("What country would You like to modify?");
		modifyCountryEntry(EasyIn.getInt(), getCountryIntel());

	}

	public static void modifyCountryEntry(int choice, Country country) {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
			String query = "UPDATE COUNTRY SET NAME=(?), CODE=(?) WHERE ID=(?)";
			PreparedStatement state = con.prepareStatement(query);
			state.setString(1, country.getName());
			state.setString(2, country.getCode());
			state.setLong(3, (long) choice);
			state.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static List<Country> getAllCountries() {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
			List<Country> kolekcja = new ArrayList<Country>();
			String query = "SELECT * FROM COUNTRY";
			Statement state = con.createStatement();
			ResultSet result = state.executeQuery(query);
			while (result.next()) {
				kolekcja.add(new Country(result.getInt("ID"), result
						.getString("NAME"), result.getString("CODE")));
			}
			con.close();
			return kolekcja;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public static void insertCountry(Country kraj) {
		try {

			Connection con = DriverManager.getConnection(
					"jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
			String query = "INSERT INTO COUNTRY (NAME , CODE) VALUES (?,?)";
			PreparedStatement state = con.prepareStatement(query);
			state.setString(1, kraj.getName());
			state.setString(2, kraj.getCode());
			state.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static Country downloadCountry(int idk) {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
			String query = "SELECT * FROM COUNTRY WHERE ID=" + idk;
			Statement state = con.createStatement();
			ResultSet result = state.executeQuery(query);
			if (result.next()) {
				return new Country(result.getInt("ID"),
						result.getString("NAME"), result.getString("CODE"));
			} else {
				con.close();
				return null;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static Country getCountryIntel() {
		System.out.println("What is the name of the Country You want to add?");
		String name = EasyIn.getString();
		System.out.println("What is the Code of the Country?");
		String code = EasyIn.getString();
		return new Country(0, name, code);
	}

	public static void searchChildren() {
		System.out
				.println("What is the ID of the Horse whose offspring You want seek?");
		int choice = EasyIn.getInt();
		System.out.println("The offspring of "
				+ downloadHorse(choice).getName());
		searchOffspring(getAllHorses(), downloadHorse(choice));
	}

	private static void searchOffspring(List<Horse> Horses, Horse horse) {
		for (Horse hoars : Horses) {
			if (hoars.getSire().getID() == horse.getID()
					|| hoars.getDam().getID() == horse.getID()) {
				System.out.println(" Name: " + hoars.getName() + " ID: "
						+ hoars.getID());
			}
		}
	}

	public static void generateGenealogy() {
		System.out.println("From which horse should be the genealogy be made?");
		int choice = EasyIn.getInt();
		System.out.println("How deep would You like to get?");
		int depth = EasyIn.getInt();
		PDFExport.exportPDF(downloadHorse(choice), depth);

	}

}
