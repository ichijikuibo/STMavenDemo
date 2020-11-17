package mavendemo;

import java.util.Scanner;

public class CarpetCostConsole {
	private static Scanner input = new Scanner(System.in);
	public static void main(String[] args) {
		//create an object of the the CostCalculator class and set the parameters to meet the requirements of the carpet task requirements
		CostCalculator carpet = new CostCalculator();
		carpet.deliveryZones.add(5.0);
		carpet.deliveryZones.add(10.0);
		carpet.exitingCustomerDiscount = 5;
		carpet.freeDeliveryThreshold = 200;
		carpet.vatRate = 0;
		
		double metresSquared = 0;
		double pricePerMetreSquared = 0;
		char deliveryZone = 'a';
		boolean existingCustomer = false;
		double cost = 0;
		
		System.out.print("Please enter Number of Square Metres > ");
		metresSquared = getDouble(0.1,Double.MAX_VALUE);//get the number of metres squared from the user
		
		System.out.print("Please enter Price Per Square Metre > ");
		pricePerMetreSquared = getDouble(0.01,Double.MAX_VALUE);//get the price from the user
		
		deliveryZone = getChar("AB","Please enter Delivery Zone");//get the delivery zone from the user
		
		existingCustomer = getChar("YN","Are you an existing customer")=='y';//ask the user if already a customer
		
		cost = carpet.getCost(pricePerMetreSquared, metresSquared, existingCustomer, deliveryZone=='a'?0:1);//get the cost of the carpet
		
		System.out.printf("\nTotal cost of carpet is £%.2f", cost);//print out the cost of the carpet to the user
	}
	/**
	 * Get a real number from console input between 2 values
	 * @param min the minimum value
	 * @param max the maximum value
	 * @return a real number
	 */
	public static double getDouble(double min,double max)// get an double between a minimum or maximum
	{
		double entereDouble = 0;
		boolean valid = false;
		String enteredText="";
		do
		{
			enteredText = input.nextLine();
			try
			{
				entereDouble = Double.parseDouble(enteredText);			
				if(entereDouble>max)
				{
					System.out.print("Invalid please enter a number less than or equal to " + max + " > ");
				}
				else if(entereDouble<min)
				{
					System.out.print("Invalid please enter a number greater than or equal to " + min + " > ");
				}
				else 
				{
					valid = true;
				}
			}
			catch(NumberFormatException e)
			{
				System.out.print("Invalid please enter a number > ");

			}
		}while(!valid);

		return entereDouble;
	}
	/**
	 * Display a message asking the user to input a char value that must match the given values
	 * @param options the characters that will be accepted, null to accept any char
	 * @param message the message to be displayed to the user
	 * @return a lower case character
	 */
	public static char getChar(String optionsString,String message)
	{
		char[] options = optionsString.toLowerCase().toCharArray();//convert the string to a lower case char array
		if(message!="")System.out.print(message + " (" + charArrayToString(options)+") > ");
		char response = 'x';
		String responseString = "";
		boolean valid = false;
		while(!valid)//continue asking for input from the user until a valid char is found
		{
			responseString = input.nextLine().toLowerCase();
			if(responseString.length()==1)//if multiple character were entered ignore
			{
				response = responseString.charAt(0);
				if(options!=null)//if there are options select
				{
					for(char c : options)
					{
						c = Character.toLowerCase(c);
						if(response==c)valid=true;
						
					}
				}
				else valid = true;//no character options were passed to the method so accept any 
			}

			if(!valid)
				System.out.print("Invalid selection please enter " + charArrayToString(options)+ " ");
		}
		return response;//return the responded character in lower case
	}
	/**
	 * Convert an array of char into a string separated by , 
	 * @param chars the characters to be converted to a string
	 * @return a comma separated string of characters
	 */
	private static String charArrayToString(char[] chars)
	{
		String output = "";
		for(char c : chars)
		{
			output += c +", ";
		}
		output = output.substring(0,output.lastIndexOf(","));
		return output;
		
	}
}
