package cashregisterCS401;

import java.text.NumberFormat;
import java.util.*;
import java.util.Scanner;
/**
 * University of Pittsburgh 
 * CS401 
 * Fall 2014
 * @author William O'Toole
 */
public class CashRegister {

	public static void main(String[] args) {
		boolean customerInLine;      
		boolean valueCard;
		boolean ordering = true;
		boolean checkingOut = false;      
		int valuePoints = 0;    
		float gasReductionf = 0;
		double priceWork;
		double propaneReduc1 = 0;
		double propaneReduc2 = 0;
		double subPrice = 0;
		double cPay = 0;
		double dPrice = 0;
		double grandTotal = 0;
		double tax = 0.07;
		double newSubTotal = 0;
		double taxCalc = 0;

		String reminder = "Note: Any answer beginning with the letter Y is Yes."+
				"\nAnything else will exit the program.";        
		String regularGas = "\n#1) Buy Gasoline: $3.75/gal(Regular)";
		String plusGas = "\n#2) Buy Gasoline: $4.00/gal(Plus)";
		String returnTank = "\n#3) Buy Propane:(with return tank): $20.00/ 20lb tank";
		String newTank = "\n#4) Buy Propane:(no return  tank): $35.00/ 20lb tank";
		String burritos = "\n#5) Buy Burritos: $1.00 /each (with a volume discount)";
		String checkOut = "\n#6) Check Out";       
		String menu = "Here are your options:"+regularGas+plusGas+returnTank+
				newTank+burritos+checkOut;       
		String choiceWas = "Your choice was ";
		String heading1="# Item";
		String heading2="Amount";

		// Priming the Scanner for input
		Scanner scan = new Scanner(System.in); 

		//Setting up currency format
		NumberFormat priceFormat = NumberFormat.getCurrencyInstance();

		//setting up Array to store Items
		ArrayList<StoreItems> items = new ArrayList<StoreItems>();

		// Welcome Statement
		System.out.println("Welcome to Gas Gas and Gas!");

		// First Question

		System.out.println("Is a customer in line?");            
		System.out.println(reminder);

		// Prompting Input for Choice
		String lineChoice = scan.next();

		// used toUpper to accept both lower case and upper case Y
		customerInLine = lineChoice.toUpperCase().startsWith("Y"); 

		// Boolean checks True False question. Either someone is in line or not.
		if (customerInLine) {   

			//declaring variables that are added into the array
			double itemPrice = 0.0;
			String itemName = "";
			int itemQuantity = 0;

			// Value Card question!
			System.out.println("Is the customer a Value Card Member");

			String valueChoice = scan.next();

			valueCard = valueChoice.toUpperCase().startsWith("Y");   

			if (valueCard) {

				System.out.println("What is there name? (Hint: Bill, John, Jack, Phil)");

				String mNameVal= scan.next();

				switch (mNameVal){
				case "Bill":
					valuePoints=45;
					propaneReduc1= 2;
					propaneReduc2= 3.5;
					gasReductionf =(float) ((valuePoints/50)*0.10);   
					System.out.println("Member Bill found!"); 
					System.out.println("You save "+priceFormat.format(gasReductionf) + " off the price of Gas per Gallon!"+
							"\n10% off Propane!"+"\n10 cents off Burritos!");
					break;

				case "John":
					valuePoints=151;
					propaneReduc1= 2;
					propaneReduc2= 3.5;
					gasReductionf =(float) ((valuePoints/50)*0.10);   
					System.out.println("Member John found!"); 
					System.out.println("You save "+priceFormat.format(gasReductionf) + " off the price of Gas per Gallon!"+
							"\n10% off Propane!"+"\n10 cents off Burritos!");
					break;

				case "Jack":
					valuePoints=16;
					propaneReduc1= 2;
					propaneReduc2= 3.5;
					gasReductionf =(float) ((valuePoints/50)*0.10);   
					System.out.println("Member Jack found!");
					System.out.println("You save "+priceFormat.format(gasReductionf) + " off the price of Gas per Gallon!"+
							"\n10% off Propane!"+"\n10 cents off Burritos!");
					break;

				case "Phil":
					valuePoints=75;
					propaneReduc1= 2;
					propaneReduc2= 3.5;
					gasReductionf =(float) ((valuePoints/50)*0.10);   
					System.out.println("Member Phil found!");
					System.out.println("You save "+priceFormat.format(gasReductionf) + " off the price of Gas per Gallon!"+
							"\n10% off Propane!"+"\n10 cents off Burritos!");
					break;

				default:
					System.out.println("Name not found!");
					gasReductionf =(float) ((valuePoints/50)*0.10);
					valueCard=false;
					break;                
				}


				System.out.println("Value Dollar Total: "+valuePoints);


			} else {
				System.out.println("No! They are not a Value Card Member");

			}

			// Display Menu
			while (ordering) {

				System.out.println("\n"+menu);

				int menuChoice = scan.nextInt();

				StoreItems newItem = new StoreItems(itemName, itemQuantity, itemPrice);

				switch (menuChoice) {

				case 1:

					System.out.println(choiceWas+regularGas);
					System.out.println("How many Gallons would you like?");
					itemQuantity = scan.nextInt(); 
					priceWork = 3.75 - gasReductionf;                    
					itemPrice = priceWork;
					itemName = "Regular Gas               ";

					items.add(newItem);   

					break;

				case 2:
					// Plus Gasoline
					System.out.println (choiceWas+plusGas);
					System.out.println("How many Gallons would you like?");

					priceWork = 4.0 - gasReductionf;
					itemPrice = priceWork;

					itemName = "Plus Gas                   ";

					itemQuantity = scan.nextInt();

					items.add(newItem);

					break;

				case 3:
					//Propane Returned Tank
					System.out.println(choiceWas+returnTank);
					itemPrice = 20.0f-propaneReduc1;

					itemName = "Propane(returned tank)     ";

					itemQuantity = 1;

					items.add(newItem);

					break;

				case 4:
					// New tank of Propane
					System.out.println (choiceWas+newTank);

					itemPrice = 35.0f-propaneReduc2;

					itemName = "Propane Tank               ";

					itemQuantity = 1;

					items.add(newItem);

					break;

				case 5:
					System.out.println(choiceWas+burritos);
					System.out.println("How many burritos would you like?"+
							"\n $1.00 (<5)\n $0.90 (6 to 10)\n $0.80 (>10)");

					itemName = "Burritos                   ";

					itemQuantity = scan.nextInt();

					if(itemQuantity > 10){
						if (valueCard==true){
							itemPrice = 0.70;
						}else{
							itemPrice = 0.80;      
						}

					}
					if((itemQuantity > 6)&& (itemQuantity < 10)){
						if (valueCard==true){
							itemPrice = 0.80;
						}else{
							itemPrice = 0.90;      
						}

					}
					if(itemQuantity < 5){
						if (valueCard==true){
							itemPrice = 0.90;
						}else{
							itemPrice = 1.00;      
						}       
					}

					items.add(newItem);

					break;

				case 6:
					System.out.println (choiceWas+checkOut);
					itemPrice = 0.0f;

					// Needed a blank order to store in the final index
					// The output cuts out the last item in the array.

					itemName = "----------";
					itemQuantity = 0;                    
					items.add(newItem);

					checkingOut = true;
					ordering = false;

					if (items.size() > 0) {

						System.out.println("----------------------------------------");
						System.out.println("Your Order"); 
						System.out.printf("%2s %30s", heading1,heading2);
						System.out.println("\n"); 
						for (int i = 1; i<items.size(); i++) {
							StoreItems curItem = items.get(i);                                
							double curPrice = curItem.price * curItem.quantity;
							subPrice = subPrice+(curItem.price* curItem.quantity);
							String printOut = curItem.quantity + " x " + curItem.name + priceFormat.format(curPrice);
							System.out.println(printOut);                            
						}


					} else {
						System.out.println("The customer didn't want anything!");
					}
					break;
				}
				// END OF SWITCH

				if (checkingOut){

					if(subPrice >= 50){
						dPrice = subPrice*0.10;                       
					}else if(valueCard==true){
						dPrice = subPrice*0.10;
					}else{
						dPrice = 0;  
					} 
					newSubTotal = (subPrice-dPrice);
					taxCalc = newSubTotal * tax;
					grandTotal= (newSubTotal+taxCalc);

					String txtTax = "7% Tax:";
					String txtDiscount = "Discount:";
					String txtSubPrice = "Sub Total:";
					String txtNewSubTotal = "New Sub Total:";
					String txtGrandTotal = "TOTAL AMOUNT DUE:";

					String strNewSubTotal = priceFormat.format(newSubTotal);
					String strTax = priceFormat.format(taxCalc);
					String strDiscount = "("+priceFormat.format(dPrice)+")";
					String strGrandTotal = priceFormat.format(grandTotal);
					String strSubPrice = priceFormat.format(subPrice);

					System.out.println("----------------------------------------");                   
					System.out.println(txtSubPrice+"                     "+strSubPrice);
					System.out.println(txtDiscount+"                     "+strDiscount);
					System.out.println(txtTax+"                        "+strTax);                   
					System.out.println("----------------------------------------");
					System.out.println(txtNewSubTotal+"                 "+strNewSubTotal);
					System.out.println(txtGrandTotal+"              "+strGrandTotal);

					while(cPay<grandTotal){
						System.out.println("\nPlease enter payment amount. (Cash Only!)");
						cPay = scan.nextDouble();                   
					}

					System.out.println("Cash Entered: "+priceFormat.format(cPay));
					double change = cPay-grandTotal;
					String strChange= priceFormat.format(change);
					System.out.println("Your Change: "+ strChange);
					System.out.println("Thank You! Please come agian!");
					scan.close();
				}

			}

		}

	}
	// Inner Class stItems (stItems stands for StoreItems
	public static class StoreItems {
		public double price;
		public String name;
		public int quantity;

		StoreItems(String nm, int qn, double pr) {
			name = nm;
			quantity = qn;   
			price = pr;
		}

		public String getName(){
			return name;
		}

		public int getQuantitiy(){
			return quantity;
		}

		public double getPrice(){
			return price;
		}

		public String toString(){
			return ("Name: "+name+"x"+quantity+" "+ price);

		} 
	}
}
