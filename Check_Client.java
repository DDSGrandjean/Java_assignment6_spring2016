/**
	DYLAN GRANDJEAN,
	Assignment-J6,
	April 18th, 2016 -
	Program designed to let the user enter checks and returns the largest and smallest amount
	as well as all the checks, both in entry order, and check number order.
*/

//Import the Scanner class
import java.util.Scanner;

public class Check_Client
{
	/**
		The main method allows the user to choose the amount of checks to enter, and enter a check number,
		payee name, and check amount for each of these check object.
		@param args Takes an array of String arguments.
	*/
	public static void main (String[] args)
	{
		//Scanner instantiation
		Scanner keyboard = new Scanner(System.in);

		Check[] checks;								//Hold all the checks entered by user
		Check[] largestCheck;						//Hold all the checks with the largest amount
		Check[] smallestCheck;						//Hold all the checks with the smallest amount

		int checkNumber;							//Check's check number
		String payee;								//Name of the current check's payee
		double amount;								//Amount of the current check

		boolean checkExists;						//Check if check number has already been used
		int arraySize;								//Hold size of the array as defined by the user
		int largeCount = 0;							//Keep track of amount of checks with largest amount
		int smallCount = 0;							//Keep trakc of amount of check with smallest amount
		int count = 0;								//Index of array checks
		double largestAmount = Double.MIN_VALUE;	//Keep track of largest amount entered by user
		double smallestAmount = Double.MAX_VALUE;	//Keep track of smallestt amount entered by user
		double totalAmount = 0;						//Calculate total amount entered by user

		//Display programmer's name
		System.out.println("DYLAN GRANDJEAN\n");
		//Prompt user for size of the array
		System.out.print("How many checks? ");
		arraySize = keyboard.nextInt();

		//Instantiate all Check[] to arraySize
		checks = new Check[arraySize];
		largestCheck = new Check[arraySize];
		smallestCheck = new Check[arraySize];

		//Executes loop while the array is not full
		while (count < checks.length)
		{
			//Prompts user for check number
			System.out.print("\nCheck number: ");
			checkNumber = keyboard.nextInt();

			//Check number validation
			do
			{
				//Validates check number
				while (checkNumber <= 0)
				{
					//Display error message for invalid check number
					System.out.printf("Invalid check number: %d. Re-enter.\n", checkNumber);
					//Prompts for new check number
					System.out.print("Check number: ");
					checkNumber = keyboard.nextInt();
				}

				//Resets bool to false
				checkExists = false;

				//Loops through checks[] to find duplicate check number
				for (int i = 0; i < count && !checkExists; i++)
				{
					if (checkNumber == checks[i].getCheckNumber())
					{
						//Display error message for duplicate check number
						System.out.printf("Duplicate check number: %d. Re-enter.\n", checkNumber);
						//Prompts for new check number
						System.out.print("Check number: ");
						checkNumber = keyboard.nextInt();

						checkExists = true;
					}
				}
			} while (checkExists);

			//Clean up the input line
			payee = keyboard.nextLine();
			//Promtps user for payee's name
			System.out.print("Payee: ");
			payee = keyboard.nextLine();
			//Prompts user for check amount
			System.out.print("Amount: ");
			amount = keyboard.nextDouble();

			//Validates check amount
			while (amount <= 0 )
			{
				//Display error message for invalid check amount
				System.out.printf("Invalid amount: %,.2f. Re-enter.\n", amount);
				//Promtps user for new check amount
				System.out.print("Amount: ");
				amount = keyboard.nextDouble();
			}

			//Create new Check object in checks[count]
			checks[count] = new Check(checkNumber, payee, amount);

			//Determines largest and smallest amount entered by user
			if (amount > largestAmount)
				largestAmount = amount;
			if (amount < smallestAmount)
				smallestAmount = amount;

			//Add amount to totalAmount
			totalAmount += amount;
			//Increment count for next reading
			count++;
		}

		//Display total amount entered by user
		System.out.printf("\nTotal is $%,.2f\n", totalAmount);

		//Determines largest and smallest check amount entered
		for (int i = 0; i < checks.length; i++)
		{
			if (checks[i].getAmount() == largestAmount)
			{
				//If check[i] has the highest value, place it in largestCheck[largeAmount]
				largestCheck[largeCount] = checks[i];
				//Increment largeCount in case more than one check has the highest amount
				largeCount++;
			}
			if (checks[i].getAmount() == smallestAmount)
			{
				//If check[i] has the lowest value, place it in smallestCheck[smallAmount]
				smallestCheck[smallCount] = checks[i];
				//Increment smallCount in case more than one check has the lowest amount
				smallCount++;
			}
		}

		//Display largest amount entered by user
		System.out.printf("\nLargest is $%,.2f\n", largestAmount);
		//Display all the checks with the largest amount
		for(int i = 0; i < largeCount; i++)
			System.out.println(largestCheck[i].toString());

		//Display smallest amount entered by user
		System.out.printf("\nSmallest is $%,.2f\n", smallestAmount);
		//Display all the checks with smallest amount
		for(int i = 0; i < smallCount; i++)
			System.out.println(smallestCheck[i].toString());

		//Display title for entry ordered checks
		System.out.println("\nChecks in entry order:");
		//Display every check in checks[]
		for (Check check : checks)
			System.out.println(check.toString());

		//Re-order checks[] in decreasing order
		bubbleSort(checks);

		//Display title for checks ordered by check number
		System.out.println("\nChecks in check number order:");
		//Display every check in checks[]
		for (Check check : checks)
			System.out.println(check.toString());
	}

	/**
		The bubbleSort method scans through the Check objects and re-orders them in
		decreasing order by check number.
		@param array The array of type Check[] to sort.
	*/
	public static void bubbleSort(Check[] array)
	{

		Check temp;							//Temporary check object holder to allow swap
		boolean swap;						//Check if a swap has occured
		int arraySize = array.length - 1;	//Get array size for testing

		//Re-order checks[] in decreasing order
		do
		{
			//Reset swap to false
			swap = false;

			//Move indexes into place in decreasing order by check number
			for(int i = 0; i < arraySize; i++)
			{
				//Check if check number is smaller than the next one
				if(array[i].getCheckNumber() < array[i+1].getCheckNumber())
				{
					//Swap checks object if condition is true
					temp = array[i];
					array[i] = array[i+1];
					array[i+1] = temp;
					//Set swap to true to restart the loop
					swap = true;
				}
			}
			//Decrease length of test
			arraySize--;
		} while(swap);
	}
}
