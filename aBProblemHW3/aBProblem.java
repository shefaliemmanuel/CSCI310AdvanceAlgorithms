import java.util.Scanner;

//Shefali Emmanuel
//310 Programming HW Assignment
//September 23, 2018

public class aBProblem {

	public static int changeNotReturned(int n, int w) {
		int changeNOTR = w - n;

		String nine = "9";

		// single digit base case
		if (changeNOTR == 9) {
			return 0;
		}

		// single digit base case
		else if (changeNOTR <= 9) {
			return 9 - changeNOTR;
		}

		String changeNOTRasString = String.valueOf(changeNOTR);
		int newNumberInt = 0;

		// if[0] != 9
		if (changeNOTRasString.charAt(0) != 9) {

			String restOfNum = changeNOTRasString.substring(1);

			String newNumber = nine + restOfNum;

			newNumberInt = Integer.parseInt(newNumber);
			
			return newNumberInt - changeNOTR;
		}

		// if [0] == 9
		if (changeNOTRasString.charAt(0) == 9) {
			for (int i = 0; i < changeNOTRasString.length(); i++) {
				if (changeNOTRasString.charAt(i) != 9) {
					// create new number with i = 9
					String newNumber = changeNOTRasString.substring(0, i - 1) + nine
							+ changeNOTRasString.substring(i + 1);
					newNumberInt = Integer.parseInt(newNumber);
					return newNumberInt - changeNOTR;
				}
			}
		}
		return newNumberInt - changeNOTR;
	}

	public static void main(String[] args) {
		// get the users bill input
		Scanner userTyped = new Scanner(System.in);
		System.out.print("Please enter the positive bill integer: ");
		int billInt = userTyped.nextInt();

		// only positive numbers
		while (billInt < 0) {
			System.out.print("Please enter a positive integer: ");
			billInt = userTyped.nextInt();
			System.out.println("");
		}

		// get the user pay input
		System.out.print("Please enter the positive customer payment int:  ");
		int payedInt = userTyped.nextInt();

		// only positive numbers
		while (payedInt < 0) {
			System.out.print("Please enter a positive integer: /n");
			payedInt = userTyped.nextInt();
			System.out.println("");
		}

		int runThis = changeNotReturned(billInt, payedInt);
		System.out.print(runThis);
	}

}
