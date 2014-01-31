package mpr.proj;

public abstract class Options {

	public static void dodawanieWpisu() {

		EasyIn.clear();
		System.out.println("|\\  /|  ___ |\\  | |   |");
		System.out.println("| \\/ | |___ | \\ | |   |");
		System.out.println("|    | |___ |  \\| |___|");
		System.out.println("1. Add a Horse");
		System.out.println("2. Add a Breeder");
		System.out.println("3. Add a Country");
		System.out.println("4. Add a Colour");
		switch (EasyIn.getInt()) {
		case 1: {
			EasyIn.clear();
			CRUD.addHorse();
			break;
		}
		case 2: {
			EasyIn.clear();
			CRUD.addBreeder();
			break;
		}

		case 3: {
			EasyIn.clear();
			CRUD.addCountry();
			break;
		}
		case 4: {
			EasyIn.clear();
			CRUD.addColour();
			break;
		}

		default: {
			EasyIn.clear();
			System.out.println("Bad choice!");
			EasyIn.getChar();
			break;
		}
		}
	}

	public static void wypisywanieDanych() {
		EasyIn.clear();
		System.out.println("|\\  /|  ___ |\\  | |   |");
		System.out.println("| \\/ | |___ | \\ | |   |");
		System.out.println("|    | |___ |  \\| |___|");
		System.out.println("1. Show all Horses");
		System.out.println("2. Show all Breeders");
		System.out.println("3. Show all Countries");
		System.out.println("4. Show all Colours");

		switch (EasyIn.getInt()) {

		case 1: {
			EasyIn.clear();
			CRUD.showHorses();
			break;
		}

		case 2: {
			EasyIn.clear();
			CRUD.showBreeders();
			break;
		}
		case 3: {
			EasyIn.clear();
			CRUD.showCountries();
			break;
		}
		case 4: {
			EasyIn.clear();
			CRUD.showColours();
			break;
		}

		default: {
			EasyIn.clear();
			System.out.println("Bad choice!");
			EasyIn.getChar();
			break;
		}
		}

	}

	public static void updateDanych() {

		EasyIn.clear();
		System.out.println("|\\  /|  ___ |\\  | |   |");
		System.out.println("| \\/ | |___ | \\ | |   |");
		System.out.println("|    | |___ |  \\| |___|");
		System.out.println("1. Modify Horse entry");
		System.out.println("2. Modify Breeder entry");
		System.out.println("3. Modify Country entry");
		System.out.println("4. Modify Colour entry");

		switch (EasyIn.getInt()) {

		case 1: {
			EasyIn.clear();
			CRUD.modifyHorse();
			break;
		}

		case 2: {
			EasyIn.clear();
			CRUD.modifyBreeder();
			break;
		}
		case 3: {
			EasyIn.clear();
			CRUD.modifyCountry();
			break;
		}

		case 4: {
			EasyIn.clear();
			CRUD.modifyColour();
			break;
		}

		default: {
			EasyIn.clear();
			System.out.println("Bad choice!");
			EasyIn.getChar();
			break;
		}
		}

	}

	public static void kasowanieDanych() {
		EasyIn.clear();
		System.out.println("|\\  /|  ___ |\\  | |   |");
		System.out.println("| \\/ | |___ | \\ | |   |");
		System.out.println("|    | |___ |  \\| |___|");
		System.out.println("1. Delete Horse entry");
		System.out.println("2. Delete Breeder entry");
		System.out.println("3. Delete Country entry");
		System.out.println("3. Delete Colour entry");

		switch (EasyIn.getInt()) {

		case 1: {
			EasyIn.clear();
			System.out
					.println("What is the ID of the Horse You would like to delete?");
			CRUD.deleteHorse(EasyIn.getInt());
			break;
		}

		case 2: {
			EasyIn.clear();
			System.out
					.println("What is the ID of the Breeder You would like to delete?");
			CRUD.deleteBreeder(EasyIn.getInt());
			break;
		}
		case 3: {
			EasyIn.clear();
			System.out
					.println("What is the ID of the Country You would like to delete?");
			CRUD.deleteCountry(EasyIn.getInt());
			break;
		}
		case 4: {
			EasyIn.clear();
			System.out
					.println("What is the ID of the Colour You would like to delete?");
			CRUD.deleteColour(EasyIn.getInt());
			break;
		}

		default: {
			EasyIn.clear();
			System.out.println("Bad choice!");
			EasyIn.getChar();
			break;
		}
		}

	}

	public static void childrenSearch() {

		EasyIn.clear();
		CRUD.searchChildren();

	}

	public static void GenealogyGenerator() {
		EasyIn.clear();
		CRUD.generateGenealogy();
	}

}