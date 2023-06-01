import java.util.Scanner;

//intializing variables 
class BankAccount {
	
	String name;
	String userName;
	String password;
	String accountNo;
	float balance = 100000f;
	int transactions = 0;
	String transactionHistory = "";
	
	
//method for registering new customer	
	public void register() {
		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter Your Name - ");
		this.name = sc.nextLine();//reading name
		System.out.print("\nEnter Your Username - ");
		this.userName = sc.nextLine();//reading user name
		System.out.print("\nEnter Your Password - ");
		this.password = sc.nextLine();//creating password from user
		System.out.print("\nEnter Your Account Number - ");
		this.accountNo = sc.nextLine();//creating account number 
		System.out.println("\nRegistration completed..kindly login");
	}
	//method for login into ATM interface
	public boolean login() {
		boolean isLogin = false;
		Scanner sc = new Scanner(System.in);
		while ( !isLogin ) {
			System.out.print("\nEnter Your Username - ");
			String Username = sc.nextLine();//entering username
			if ( Username.equals(userName) ) {
				while ( !isLogin ) {
					System.out.print("\nEnter Your Password - ");
					String Password = sc.nextLine();//if username is valid asking to enter password
					if ( Password.equals(password) ) {
						System.out.print("\nLogin successful!!"); //if password valid login is successful
						isLogin = true;
					}
					else {
						System.out.println("\nIncorrect Password");
					}
				}
			}
			else {
				System.out.println("\nUsername not found");//if usename is not correct
			}
		}
		return isLogin;
	}
//method for withdrawal of amount
	
	public void withdraw() {
		
		System.out.print("\nEnter amount to withdraw - ");
		Scanner sc = new Scanner(System.in);
		float amount = sc.nextFloat();//reading amount to be withdraw from user
		try {
			
			if ( balance >= amount ) //if balance is greater than equals to withdrawal amount 
			{
				transactions++;//increment of transctions
				balance -= amount;//decrement of balance in account
				System.out.println("\nWithdraw Successfully");
				String str = amount + " Rs Withdrawed\n";
				transactionHistory = transactionHistory.concat(str);
				
			}
			else {
				System.out.println("\nInsufficient Balance"); //if balance is less than withdrawal amount
			}
			
		}
		catch ( Exception e) {
		}
	}
//method for depositing amount
	
	public void deposit() {
		
		System.out.print("\nEnter amount to deposit - ");
		Scanner sc = new Scanner(System.in);
		float amount = sc.nextFloat(); // reading amount to be deposited
		
		try {
			if ( amount <= 100000f )  //if amount is less than intial amount
			{
				transactions++; //increment of transacations
				balance += amount; //incrementing balance in account
				System.out.println("\nSuccessfully Deposited"); //successfully deposited
				String str = amount + " Rs deposited\n";
				transactionHistory = transactionHistory.concat(str);
			}
			else {
				System.out.println("\nSorry...Limit is 100000.00");
			}
			
		}
		catch ( Exception e) {
		}
	}
 //method for transfering amount
	
	public void transfer() {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter Receipent's Name - "); //reading receipt namr
		String receipent = sc.nextLine();
		System.out.print("\nEnter amount to transfer - ");
		float amount = sc.nextFloat(); //reading transfer amount
		
		try {
			if ( balance >= amount ) //checking transfering amount is less than balance in account
			 {
				if ( amount <= 50000f ) {
					transactions++; //increment transctions
					balance -= amount; //decrement of amount
					System.out.println("\nSuccessfully Transfered to " + receipent);
					String str = amount + " Rs transfered to " + receipent + "\n";
					transactionHistory = transactionHistory.concat(str);
				}
				else {
					System.out.println("\nSorry...Limit is 50000.00");
				}
			}
			else {
				System.out.println("\nInsufficient Balance");
			}
		}
		catch ( Exception e) {
		}
	}
 //method for checking balance
	
	public void checkBalance() {
		System.out.println("\n" + balance + " Rs"); //printing balance amount
	}
 //method for transcations histroy
	
	public void transHistory() {
		if ( transactions == 0 ) {
			System.out.println("\nEmpty");
		}
		else {
			System.out.println("\n" + transactionHistory); //printing number of tranctions
		}
	}
}
 //creating class Atminterface

public class AtmInterface {
	
	
	public static int takeIntegerInput(int limit) {
		int input = 0;
		boolean flag = false;
		
		while ( !flag ) {
			try {
				Scanner sc = new Scanner(System.in);
				input = sc.nextInt();
				flag = true;
				
				if ( flag && input > limit || input < 1 ) {
					System.out.println("Choose the number between 1 to " + limit);
					flag = false;
				}
			}
			catch ( Exception e ) {
				System.out.println("Enter only integer value");
				flag = false;
			}
		};
		return input;
	}
	
	
	public static void main(String[] args) {
		
		System.out.println("\n**********WELCOME TO SBI ATM SYSTEM**********\n");
		System.out.println("1.Register \n2.Exit");
		System.out.print("Enter Your Choice - ");
		int choice = takeIntegerInput(2); //asking customer to enter register for atm interface or exit
		
		if ( choice == 1 ) {
			BankAccount b = new BankAccount(); //creating object
			b.register();//register for customer
			while(true) {
				System.out.println("\n1.Login \n2.Exit");
				System.out.print("Enter Your Choice - ");
				int ch = takeIntegerInput(2); //asking to login or exit
				if ( ch == 1 ) {
					if (b.login()) {
						System.out.println("\n\n**********WELCOME BACK " + b.name + " **********\n");
						boolean isFinished = false;
						while (!isFinished) {
							System.out.println("\n1.Withdraw \n2.Deposit \n3.Transfer \n4.Check Balance \n5.Transaction History \n6.Exit");
							System.out.print("\nEnter Your Choice - ");
							int c = takeIntegerInput(6); // asking type of transaction to customer
							switch(c) {
								case 1:
								b.withdraw(); //entering to withdrawal method
								break;
								case 2:
								b.deposit(); //entering to deposit method
								break;
								case 3:
								b.transfer(); //entering transfer method
								break;
								case 4:
								b.checkBalance(); //entering to checkbalance method
								break;
								case 5:
								b.transHistory(); //entering to transaction histroy method
								break;
								case 6:
								isFinished = true;
								break;
							}
						}
					}
				}
				else {
					System.exit(0);
				}
			}
		}
		else {
			System.exit(0);
		}
		
		
		
	}
}	