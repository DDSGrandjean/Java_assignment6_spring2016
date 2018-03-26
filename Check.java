/**
	DYLAN GRANDJEAN,
	Assignment-J6,
	April 18th, 2016 -
	The Check class is designed to create a check object.
*/
public class Check
{
	//Field declarations
	private int checkNumber;		//The check number
	private String payee;			//The payee's name
	private double amount;			//The amount written on the check

	/**
		Check class constructor.
		@param checkNum The check's check number.
		@param name The payee's name.
		@param money The amount written on the check.
	*/
	public Check(int checkNum, String name, double money)
	{
		//Validate check number and money
		if (checkNum <= 0 || money <= 0)
		{
			if (checkNum <= 0)
			{
				//Display error message for invalide check number
				System.out.printf("Invalid check number: %d. Re-enter.", checkNum);
			}
			if (money <= 0)
			{
				//Display error message for invalide check amount
				System.out.printf("Invalid amount: %f. Re-enter.", money);
			}
			//Exit the program
			System.exit(0);
		}
		else
		{
			//Instantiate fields if no error is found
			checkNumber = checkNum;
			payee = name;
			amount = money;
		}
	}

	/**
		Method getCheckNumber returns the check's check number.
		@return The check number.
	*/
	public int getCheckNumber()
	{
		//Return te check number
		return checkNumber;
	}

	/**
		Method getPayee returns the payee's name.
		@return The payee's name.
	*/
	public String getPayee()
	{
		//Return payee
		return payee;
	}

	/**
		Method getAmount returns the amount written on the check.
		@return The check amount.
	*/
	public double getAmount()
	{
		//Return amount
		return amount;
	}

	/**
		Method toString returns a formatted String of the class' fields in a sentence
		@return The formatted String
	*/
	public String toString()
	{
		//Returns a formatted String
		return String.format("Check number %d was written to %s for $%,.2f.", checkNumber, payee, amount);
	}
}
