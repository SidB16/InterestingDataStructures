/**
 * A simple model of a credit card
 * A class is a blue print for an object. 
 * 
 * @author sid16
 *
 */
public class CreditCard {
	
	/**
	 * Declaration of Feilds. Feilds define what attributes objects have.
	 * 
	 */
	//instance variables 
	private String customer;
	private String bank;
	private String account;
	private int limit;
	protected double balance;
	
	/**
	 * Constructor#1
	 * Constructs the credit card.
	 * A constructor intialzies the state of an object. in
	 * @param cust 	intializes the name of the customer, who owns this creditcard
	 * @param bk 	initializes the name of the bank, of the owner of the creditcard
	 * @param acnt 	initializes the account number, of the owner of the account 
	 * @param lim 	intializes the limit of the creditcard, int value in $
	 * @param initialBal 	initilazes the initial balance on the creditcard
	 */
	//constructors
	public CreditCard( String cust, String bk, String acnt, int lim, double initialBal) {
		customer = cust;
		bank = bk;
		account = acnt;
		limit = lim;
		balance = initialBal;
	}
	/**
	 * Constructor#2 - initialzes the state the creditcard object 
	 * @param cust		Name of Owner of CreditCard
	 * @param bk		Name of Bank Account of CreditCard
	 * @param acnt		Account number of Owner, taken as string literals
	 * @param lim		Credit Limit of credit card
	 */
	public CreditCard (String cust, String bk, String acnt, int lim) {
		this(cust, bk, acnt, lim, 0.0); //contructor chaining 
									//intial balance is 0
	}

	// Acessor methods
	/**
	 * Returns the name of the owner of the creditcard
	 * @return customer the name of the owner
	 */
	public String getCustomer() { return customer;	}
	
	public String getBank() { return bank; }
	public String getAccount() { return account;}
	public int getLimit() { return limit; }
	public double getBalance() { return balance;}
	
	//Update methods	
	public boolean charge( double price) {
		if ( price + balance > limit) {
			return false;
		}//else
			balance += price;
			return true;  
	}
	public void makePayment( double amount) { 
		if (amount > 0) {
			balance -= amount;
		}
	}
	
	//update method for limit
	public void updateLimit ( int lim) { lim = limit; }  
	
	//Utility method to print a card's information
	public static void printSummary (CreditCard card) {
		System.out.println("Customer = " + card.customer);
		System.out.println("Bank = " + card.bank);
		System.out.println("Account = " + card.account);
		System.out.println("Card Limit = " + card.limit); //implitcit casting from int to String
		System.out.println("Card Balance = " + card.balance); // implicit casting from double to String
	}
	
	//TESTER
	
	public static void main ( String[] args) {
		CreditCard[] wallet =  new CreditCard[3];
		wallet[0] = new CreditCard( "Jonny G", "TD bank", "5391 0375 9387 5309", 5000);
		wallet[1] = new CreditCard( "Jonny G", "RBC", "3485 0399 3395 1954", 3500);
		wallet[2] = new CreditCard( "Jonny G", "CIBC", "5391 0375 9387 5309", 2500, 300);
		
		
		for (int val = 1; val <= 16; val++) {
			wallet[0].charge(3*val);
			wallet[1].charge(2*val);
			wallet[2].charge(val);
		}
		
		for ( CreditCard card : wallet) {
			CreditCard.printSummary(card); //staic method so call with class name
			while ( card.getBalance() > 200) {
				card.makePayment(-200);
				System.out.println("New balance = " + card.getBalance());
			}
		}
		
	}

}
