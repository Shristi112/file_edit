
package lab2;

import java.io.*;
import java.util.*;

public class FileEdit {
	public static LinkedList<String> list = new LinkedList<String>();

	public static void main(String[] args) throws IOException {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("EDIT ");
		String file = keyboard.nextLine();

		BufferedReader buffRead = new BufferedReader(new FileReader(new File(file)));
		String string;
		int lineNum = 0;

		while ((string = buffRead.readLine()) != null) { // displays the content of the text file
			list.add(lineNum, string);
			System.out.println((++lineNum) + "> " + string);
		}
		String text = "";
		String userInp;
		System.out.print((++lineNum) + "> ");
		userInp = keyboard.nextLine();
		String inputArr[];
		int commandSwitch;

		while (!userInp.equals("E")) { // keeps in the loop until E is entered
			commandSwitch = 0;

			inputArr = userInp.split(" ");

			if (inputArr[0].equals("I")) {

				while (true) { // prevents the code from stopping

					if (inputArr.length == 1 && commandSwitch != 2) { // If the input is I with no parameter
						text = keyboard.nextLine();
						i(text, lineNum - 1);
					}

					else if (inputArr.length == 2 && commandSwitch != 2) { // If the input is I with one parameter
						System.out.print((inputArr[1]) + "> ");
						lineNum = Integer.parseInt(inputArr[1]);
						text = keyboard.nextLine();
						i(text, Integer.parseInt(inputArr[1]) - 1);
					}

					else
						i(text, lineNum - 1);

					System.out.print((++lineNum) + "> "); // new user input
					userInp = keyboard.nextLine();
					inputArr = userInp.split(" ");
					if (inputArr[0].equals("I") || inputArr[0].equals("L") || inputArr[0].equals("D")
							|| inputArr[0].equals("E")) {

						commandSwitch = 1;
						break;
					} else {

						commandSwitch = 2; // helps to prevent infinite loop
						text = userInp;
					}
				}
			} else if (inputArr[0].equals("D")) {

				if (inputArr.length == 1) { // If the input D has no parameter
					int x = list.size();
					d(0, x - 1);
				}

				else if (inputArr.length == 2) { // If the input D has specific parameter
					d(Integer.parseInt(inputArr[1]) - 1);
				}

				else if (inputArr.length == 3) { // If the input D has a range
					d(Integer.parseInt(inputArr[1]) - 1, Integer.parseInt(inputArr[2]) - 1);
				}
				lineNum = list.size();
			} else if (inputArr[0].equals("L")) {

				if (inputArr.length == 1) { // if input is L with no parameter
					l();
				}

				else if (inputArr.length == 2) { // If the input is L with a specific line
					l(Integer.parseInt(inputArr[1]) - 1);
				}

				else if (inputArr.length == 3) { // If the input is L with a range
					l(Integer.parseInt(inputArr[1]) - 1, Integer.parseInt(inputArr[2]) - 1);
				}
			} else if (inputArr[0].equals("A")) {

				while (true) { // prevents the code from stopping

					System.out.print((lineNum) + "> ");
					text = keyboard.nextLine();
					a(text);

					System.out.print((++lineNum) + "> "); // new user input
					userInp = keyboard.nextLine();
					inputArr = userInp.split(" ");
					if (inputArr[0].equals("I") || inputArr[0].equals("L") || inputArr[0].equals("D")
							|| inputArr[0].equals("E"))
						break;
					else {
						commandSwitch = 2;
						text = userInp;
					}
				}
			}

			if (commandSwitch != 1) { // the code will run continuously as long as commandSwitch is one
				System.out.print((lineNum++) + "> ");
				userInp = keyboard.nextLine();
			}
		}

		if (userInp.equals("E")) {
			e(file);
			System.out.println("Text saved to file.");
		}
	}

	public static void i(String text, int n) {
		list.add(n, text);
	}

	public static void d(int n, int m) { // Deleting number of lines
		for (int i = n; i <= m; i++)
			list.remove(n);
	}

	public static void d(int n) { // Deleting a line
		list.remove(n);
	}

	public static void l(int n, int m) {
		for (int i = n; i <= m; i++)
			System.out.println((i + 1) + "> " + list.get(i));
	}

	public static void l(int n) {
		System.out.println((n + 1) + "> " + list.get(n));
	}

	public static void l() { // printing out all the lines
		for (int i = 0; i < list.size(); i++)
			System.out.println((i + 1) + "> " + list.get(i));
	}

	public static void a(String text) {
		list.add(text);
	}

	// exits and saves to the text file
	public static void e(String file) throws IOException {
		FileWriter writer = new FileWriter(file);
		PrintWriter printWriter = new PrintWriter(writer);
		for (int i = 0; i < list.size(); i++)
			printWriter.println(list.get(i));
		printWriter.close();
		System.exit(0);
	}
}