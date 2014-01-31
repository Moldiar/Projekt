package mpr.proj;

public class Main {
	public static void main(String[] args) {
		int x = 0;
		do {

			System.out.println("|\\  /|  ___ |\\  | |   |");
			System.out.println("| \\/ | |___ | \\ | |   |");
			System.out.println("|    | |___ |  \\| |___|");
			System.out.println("1. Add new entry");
			System.out.println("2. Show tables");
			System.out.println("3. Update entry");
			System.out.println("4. Delete entry");
			System.out.println("5. Search for Horse Offspring");
			System.out.println("6. Generate a genealogy PDF file");
			System.out.println("7. Exit");

			switch (EasyIn.getInt()) {
			case 1: {
				Options.dodawanieWpisu();

				break;
			}
			case 2: {

				Options.wypisywanieDanych();
				break;
			}
			case 3: {
				Options.updateDanych();
				break;
			}
			case 4: {
				Options.kasowanieDanych();
				break;
			}
			case 5: {
				Options.childrenSearch();
				break;
			}
			case 6: {
				Options.GenealogyGenerator();
				break;
			}
			case 7: {
				x = 1;
				break;
			}

			default: {
				System.out.println("Bad choice!");
				EasyIn.getChar();

			}
			}

		} while (x == 0);
	}

}