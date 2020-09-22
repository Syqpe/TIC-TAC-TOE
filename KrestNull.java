import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class KrestNull {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String simvol = "";
		int mesto = 0;
		char[][] kro = new char[3][3];
		int numberHod = 0;
		char win = 'X';
		char choice = 'O';
		int n = 0;

		int hodPC = 1;

		System.out.println("====================================");
		System.out.println("Welcom to game");
		System.out.println("====================================");

		System.out.print("Today is: ");
		SimpleDateFormat data = new SimpleDateFormat("dd.MM.yyyy");
		Date date = new Date();
		System.out.println(data.format(date));
		System.out.println();

		boolean play = true;

		System.out.println("Zdelaite hod : X/O ");
		numberHod = printGame(numberHod);
		mesto = scan.nextInt();
		scan.nextLine();
		simvol = scan.nextLine();
		simvol = simvol.toLowerCase();
		choice = simvol.charAt(0);
		place(choice, mesto, kro);

		while (play == true) {

			if (n > 0) {
				System.out.println("Zdelaite hod : X/O ");
				numberHod = printNewGame(numberHod, choice, mesto, kro);
				mesto = scan.nextInt();
				scan.nextLine();
				simvol = scan.nextLine();
				simvol = simvol.toLowerCase();
				choice = simvol.charAt(0);

				place(choice, mesto, kro);

			}

			win = winner(kro, win);
			if (win == 'x' || win == 'o' || win == 'N') {
				break;
			} else if (numberHod == 9) {
				play = false;
				winner(kro, win);
			}

			computer(kro, hodPC);

			win = winner(kro, win);
			if (win == 'x' || win == 'o' || win == 'N') {
				break;
			} else if (numberHod == 9) {
				play = false;
				winner(kro, win);
			}

			n++;
		}
		System.out.println();
		printNewGame(numberHod, choice, mesto, kro);
		contin(win);

	}

	public static char[][] computer(char[][] kro, int hodPC) {
		int into = 0;
		int injo = 0;
		String winHOD = winFindHod(kro);
		String winhodProt = "";
		if (winHOD.equals("false")) { // Если свой победа не получается за ход

			winhodProt = WinHodProtivnica(kro);

			if (winhodProt.equals("false") == true) { // Если победа соперника не получается за ход

				// Придумать алгоритм оптимального и выгодного хода,а не просто так !
				if ((kro[0][0] == 'x' && kro[1][1] == 0 || kro[2][2] == 'x' && kro[1][1] == 0
						|| kro[2][0] == 'x' && kro[1][1] == 0 || kro[0][2] == 'x' && kro[1][1] == 0)
						|| (kro[1][0] == 'x' && kro[1][1] == 0 || kro[0][1] == 'x' && kro[1][1] == 0
								|| kro[1][2] == 'x' && kro[1][1] == 0 || kro[2][1] == 'x' && kro[1][1] == 0)) {
					kro[1][1] = 'o';
				} else if (kro[0][0] == 0 || kro[2][2] == 0 || kro[2][0] == 0 || kro[0][2] == 0) { // Создает опасую позицию для
																																														// соперника или контратака

					if (kro[0][0] == 'x' && kro[0][2] == 'x' && kro[1][1] == 0
							|| kro[0][0] == 'x' && kro[2][0] == 'x' && kro[1][1] == 0) {
						kro[1][1] = 'o';
					} else if (kro[2][0] == 'x' && kro[2][2] == 'x' && kro[1][1] == 0
							|| kro[2][2] == 'x' && kro[0][2] == 'x' && kro[1][1] == 0) {
						kro[1][1] = 'o';
					} else if (kro[0][0] == 'x' && kro[2][2] == 'x' && kro[1][1] == 0) {
						kro[1][1] = 'o';
					} else if (kro[0][2] == 'x' && kro[2][0] == 'x' && kro[1][1] == 0) {
						kro[1][1] = 'o';
					} else {

						if (kro[0][0] == 0 && kro[0][1] == 0 && kro[1][0] == 0 && kro[2][2] != 'o' && kro[1][1] == 0
								&& (kro[0][2] == 0 && kro[2][2] == 0 || kro[2][2] == 0 && kro[2][0] == 0
										|| kro[0][2] == 0 && kro[2][0] == 0)) {
							kro[0][0] = 'o';
						} else if (kro[0][0] == 0 && kro[1][1] == 'o' && kro[0][1] == 'x' && kro[1][0] == 'x') {
							kro[0][0] = 'o';
						} else if (kro[1][1] == 'x') {
							kro[0][0] = 'o';
						}

						else if (kro[0][2] == 0 && kro[0][1] == 0 && kro[1][2] == 0 && kro[2][0] != 'o' && kro[1][1] == 0
								&& (kro[0][0] == 0 && kro[2][2] == 0 || kro[2][2] == 0 && kro[2][0] == 0
										|| kro[2][0] == 0 && kro[0][0] == 0)) {
							kro[0][2] = 'o';
						} else if (kro[0][2] == 0 && kro[1][1] == 'o' && kro[0][1] == 'x' && kro[1][2] == 'x') {
							kro[0][2] = 'o';
						} else if (kro[1][1] == 'x') {
							kro[1][1] = 'o';
						}

						else if (kro[2][0] == 0 && kro[1][0] == 0 && kro[2][1] == 0 && kro[0][2] != 'o' && kro[1][1] == 0
								&& (kro[0][0] == 0 && kro[0][2] == 0 || kro[0][2] == 0 && kro[2][2] == 0
										|| kro[2][2] == 0 && kro[0][0] == 0)) {
							kro[2][0] = 'o';
						} else if (kro[2][0] == 0 && kro[1][1] == 'o' && kro[2][1] == 'x' && kro[1][0] == 'x') {
							kro[2][0] = 'o';
						} else if (kro[1][1] == 'x') {
							kro[2][0] = 'o';
						}

						else if (kro[2][2] == 0 && kro[1][2] == 0 && kro[2][1] == 0 && kro[0][0] != 'o' && kro[1][1] == 0
								&& (kro[0][0] == 0 && kro[0][2] == 0 || kro[0][2] == 0 && kro[2][0] == 0
										|| kro[2][0] == 0 && kro[0][0] == 0)) {
							kro[2][2] = 'o';
						} else if (kro[2][2] == 0 && kro[1][1] == 'o' && kro[1][2] == 'x' && kro[2][1] == 'x') {
							kro[2][2] = 'o';
						} else if (kro[1][1] == 'x') {
							kro[2][2] = 'o';
						}

						else {

							if (kro[0][1] == 0 || kro[1][2] == 0 || kro[2][1] == 0 || kro[1][0] == 0) {
								if (kro[0][1] == 0) {
									kro[0][1] = 'o';
								} else if (kro[1][0] == 0) {
									kro[1][0] = 'o';
								} else if (kro[2][1] == 0) {
									kro[2][1] = 'o';
								} else if (kro[1][2] == 0) {
									kro[1][2] = 'o';
								}
							} else {
								while (true) {
									into = (int) (Math.random() * 3);
									injo = (int) (Math.random() * 3);
									if (kro[into][injo] == 0) {
										kro[into][injo] = 'o';
										break;
									} else if (kro[0][0] != 0 && kro[0][1] != 0 && kro[0][2] != 0 && kro[1][0] != 0 && kro[1][1] != 0
											&& kro[1][2] != 0 && kro[2][0] != 0 && kro[2][1] != 0 && kro[2][2] != 0) {
										break;
									}
								}
							}
						}
					}

				} else { // Если угловые позиций заняты,то радномно ставит нолик
					while (true) {
						into = (int) (Math.random() * 3);
						injo = (int) (Math.random() * 3);
						if (kro[into][injo] == 0) {
							kro[into][injo] = 'o';
							break;
						} else if (kro[0][0] != 0 && kro[0][1] != 0 && kro[0][2] != 0 && kro[1][0] != 0 && kro[1][1] != 0
								&& kro[1][2] != 0 && kro[2][0] != 0 && kro[2][1] != 0 && kro[2][2] != 0) {
							break;
						}
					}
				}

			} else { // Если победа соперника получается за ход

				int i = Integer.parseInt("" + winhodProt.charAt(0));
				int j = Integer.parseInt("" + winhodProt.charAt(1));
				kro[i][j] = 'o';

			}
		} else { // Если свою победа получается за ход

			int i = Integer.parseInt("" + winHOD.charAt(0));
			int j = Integer.parseInt("" + winHOD.charAt(1));
			kro[i][j] = 'o';

		}

		return kro;
	}

	public static String WinHodProtivnica(char[][] kro) {

		int winHODnumberRow = 0;
		int winHODnumberCol = 0;
		char simvol = 'x';
		boolean fi = false;
		String str = "";
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (kro[i][j] == 0) {
					kro[i][j] = 'x';
					if (winHOD(kro, simvol) == true) {
						winHODnumberRow = i;
						winHODnumberCol = j;
						fi = true;
					}
					kro[i][j] = 0;
					if (fi == true) {
						break;
					}
				}
			}
			if (fi == true) {
				break;
			}
		}

		str = str + winHODnumberRow + winHODnumberCol;

		if (fi == false) {
			str = "false";
		}

		return str;

	}

	public static String winFindHod(char[][] kro) {

		int winHODnumberRow = 0;
		int winHODnumberCol = 0;
		char simvol = 'o';
		boolean fi = false;
		String str = "";
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (kro[i][j] == 0) {
					kro[i][j] = 'o';
					if (winHOD(kro, simvol)) {
						winHODnumberRow = i;
						winHODnumberCol = j;
						fi = true;
					}
					kro[i][j] = 0;
					if (fi == true) {
						break;
					}
				}
			}
			if (fi == true) {
				break;
			}
		}

		str = winHODnumberRow + "" + winHODnumberCol;

		if (fi == false) {
			str = "false";
		}

		return str;

	}

	public static boolean winHOD(char[][] kro, char simvol) {
		char pobeda = 'u';
		if (kro[0][0] == kro[0][1] && kro[0][1] == kro[0][2] && kro[0][0] != 0) {
			pobeda = kro[0][0];
		} else if (kro[1][0] == kro[1][1] && kro[1][1] == kro[1][2] && kro[1][0] != 0) {
			pobeda = kro[1][0];
		} else if (kro[2][0] == kro[2][1] && kro[2][1] == kro[2][2] && kro[2][0] != 0) {
			pobeda = kro[2][0];
		} else if (kro[0][0] == kro[1][0] && kro[1][0] == kro[2][0] && kro[0][0] != 0) {
			pobeda = kro[0][0];
		} else if (kro[0][1] == kro[1][1] && kro[1][1] == kro[2][1] && kro[0][1] != 0) {
			pobeda = kro[0][1];
		} else if (kro[0][2] == kro[1][2] && kro[1][2] == kro[2][2] && kro[0][2] != 0) {
			pobeda = kro[0][2];
		} else if (kro[0][0] == kro[1][1] && kro[1][1] == kro[2][2] && kro[0][0] != 0) {
			pobeda = kro[0][0];
		} else if (kro[0][2] == kro[1][1] && kro[1][1] == kro[2][0] && kro[0][2] != 0) {
			pobeda = kro[0][2];
		} else {
			if (kro[0][0] != 0 && kro[0][1] != 0 && kro[0][2] != 0 && kro[1][0] != 0 && kro[1][1] != 0 && kro[1][2] != 0
					&& kro[2][0] != 0 && kro[2][1] != 0 && kro[2][2] != 0) {
				pobeda = 'N';
			}

		}
		boolean find = false;
		if (pobeda == simvol) {
			find = true;
		}

		return find;
	}

	public static int printNewGame(int numberHod, char choice, int mesto, char[][] kro) {
		System.out.println("----------------------------");
		System.out.println("|        ||        ||        |");
		int c = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				c++;
				if (kro[i][j] != 0) {

					System.out.print("|    " + kro[i][j] + "   |");

				} else if (kro[i][j] == 0) {

					if (i == 0 && j == 0) {
						System.out.print("|   1   |");
					} else if (i == 0 && j == 1) {
						System.out.print("|   2   |");
					} else if (i == 0 && j == 2) {
						System.out.print("|   3   |");
					} else if (i == 1 && j == 0) {
						System.out.print("|   4    |");
					} else if (i == 1 && j == 1) {
						System.out.print("|   5   |");
					} else if (i == 1 && j == 2) {
						System.out.print("|   6   |");
					} else if (i == 2 && j == 0) {
						System.out.print("|   7    |");
					} else if (i == 2 && j == 1) {
						System.out.print("|   8   |");
					} else if (i == 2 && j == 2) {
						System.out.print("|   9   |");
					}

				}
			}

			if (c % 3 == 0) {
				System.out.println();
				System.out.println("|        ||        ||        |");
				System.out.println("----------------------------");
				System.out.println("|        ||        ||        |");
			} else if (c % 3 != 0) {
				System.out.println();
				System.out.println("|        ||        ||        |");
				System.out.println("----------------------------");
			}

		}

		numberHod = numberHod + 1;
		return numberHod;

	}

	public static int printGame(int numberHod) {

		System.out.println("----------------------------");
		System.out.println("|        ||       ||       |");
		System.out.println("|    1   ||   2   ||   3   |");
		System.out.println("|        ||       ||       |");
		System.out.println("-------------------------");
		System.out.println("|        ||       ||       |");
		System.out.println("|    4   ||   5   ||   6   |");
		System.out.println("|        ||       ||       |");
		System.out.println("-------------------------");
		System.out.println("|        ||       ||       |");
		System.out.println("|    7   ||   8   ||   9   |");
		System.out.println("|        ||       ||       |");
		System.out.println("----------------------------");
		numberHod = numberHod + 1;

		return numberHod;

	}

	public static char[][] place(char ch, int mesto, char[][] kro) {

		switch (mesto) {
			case 1:
				kro[0][0] = ch;
				break;
			case 2:
				kro[0][1] = ch;
				break;
			case 3:
				kro[0][2] = ch;
				break;
			case 4:
				kro[1][0] = ch;
				break;
			case 5:
				kro[1][1] = ch;
				break;
			case 6:
				kro[1][2] = ch;
				break;
			case 7:
				kro[2][0] = ch;
				break;
			case 8:
				kro[2][1] = ch;
				break;
			case 9:
				kro[2][2] = ch;
				break;
		}
		return kro;

	}

	public static char winner(char[][] kro, char win) {

		if (kro[0][0] == kro[0][1] && kro[0][1] == kro[0][2] && kro[0][0] != 0) {
			win = kro[0][0];
		} else if (kro[1][0] == kro[1][1] && kro[1][1] == kro[1][2] && kro[1][0] != 0) {
			win = kro[1][0];
		} else if (kro[2][0] == kro[2][1] && kro[2][1] == kro[2][2] && kro[2][0] != 0) {
			win = kro[2][0];
		} else if (kro[0][0] == kro[1][0] && kro[1][0] == kro[2][0] && kro[0][0] != 0) {
			win = kro[0][0];
		} else if (kro[0][1] == kro[1][1] && kro[1][1] == kro[2][1] && kro[0][1] != 0) {
			win = kro[0][1];
		} else if (kro[0][2] == kro[1][2] && kro[1][2] == kro[2][2] && kro[0][2] != 0) {
			win = kro[0][2];
		} else if (kro[0][0] == kro[1][1] && kro[1][1] == kro[2][2] && kro[0][0] != 0) {
			win = kro[0][0];
		} else if (kro[0][2] == kro[1][1] && kro[1][1] == kro[2][0] && kro[0][2] != 0) {
			win = kro[0][2];
		} else {
			if (kro[0][0] != 0 && kro[0][1] != 0 && kro[0][2] != 0 && kro[1][0] != 0 && kro[1][1] != 0 && kro[1][2] != 0
					&& kro[2][0] != 0 && kro[2][1] != 0 && kro[2][2] != 0) {
				win = 'N';
			}

		}
		return win;
	}

	public static void contin(char win) {

		System.out.println();
		System.out.println("#############################################");
		System.out.println("Become the winner of this game");
		System.out.println();
		if (win == 'x') {
			System.out.println("\\   / ");
			System.out.println(" \\ /  ");
			System.out.println(" / \\  ");
			System.out.println("/   \\ ");
		} else if (win == 'o') {
			System.out.println("   ____ ");
			System.out.println("  /    \\ ");
			System.out.println(" /      \\ ");
			System.out.println("|        |");
			System.out.println("|        |");
			System.out.println(" \\      / ");
			System.out.println("  \\____/ ");
		} else if (win == 'N') {
			System.out.println("I dont know!");
		}
		System.out.println();
		System.out.print("Date: ");
		printCurrentDate(); // prints current date
		System.out.print("\tTime: ");
		printCurrentTime(); // prints current time

	}

	public static void printCurrentDate() {

		SimpleDateFormat den = new SimpleDateFormat("dd.MM.yyyy");
		Date date = new Date();
		System.out.print(den.format(date));

	}

	public static void printCurrentTime() {

		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, 0);
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		System.out.println(dateFormat.format(c.getTime()));

	}

}