import java.util.Calendar;

public class account {
	private depositer dep;
	private int accountnumber;
	private String accountype;
	private double balance;
	private String accountdate;

//no-arg constructor for new account
	public account() {
		dep = new depositer();
		accountnumber = 0;
		accountype = "";
		balance = 0.0;
		accountdate = "n/a";
	}

//3-arg constructor for account that is not CD
	public account(int i, String j, double k) {
		dep = new depositer();
		accountnumber = i;
		accountype = j;
		balance = k;
		accountdate = "n/a";
	}

//4-arg constructor for account that is CD
	public account(int i, String j, double k, String l, String ssn, String first, String last) {
		dep = new depositer(ssn, first, last);
		accountnumber = i;
		accountype = j;
		balance = k;
		accountdate = l;
	}

	public account(int i, String j, double k, depositer m) {
		dep = m;
		accountnumber = i;
		accountype = j;
		balance = k;
		accountdate = "n/a";
	}

//GETBALANCE METHOD
//Input: transactionticket, boolean, string, doubles
//Process: Create transactionreceipt and fill based of given values
//Output: Print all values of the created receipt and ticket for balance check
	public transactionreciept getBalance(transactionticket i, boolean j, String k, double l, double m) {
		transactionreciept t = new transactionreciept(j, k, l, m, i);
		return t;
	}
//MAKEDEPOSIT METHOD FOR NON CD ACCOUNTS

//Input: transactionticket, boolean, string, doubles
//Process: Create transactionreceipt and fill based of given values
//Output: Print all values of the created receipt and ticket for Deposit
	public transactionreciept makeDeposit(transactionticket i, double amount, boolean j, String k, double l, double m,
			account z) {
		transactionreciept t = new transactionreciept(j, k, l, m, i);
		z.balance += amount;
		return t;
	}

//For CD Accounts
	public transactionreciept makeDeposit(transactionticket i, double amount, boolean j, String k, double l, double m,
			Calendar b, int v, account z) {
		transactionreciept t = new transactionreciept(j, k, l, m, b, v, i);
		z.balance += amount;
		return t;
	}

//MAKEWITHDRAWAL METHOD FOR NON CD ACCOUNTS
//Input: transactionticket, boolean, string, doubles
//Process: Create transactionreceipt and fill based of given values
//Output: Print all values of the created receipt and ticket for withdrawal
	public transactionreciept makeWithdrawal(transactionticket i, double amount, boolean j, String k, double l,
			double m, account z) {
		transactionreciept t = new transactionreciept(i);
		z.balance -= amount;
		return t;
	}

//For CD Accounts
	public transactionreciept makeWithdrawal(transactionticket i, double amount, boolean j, String k, double l,
			double m, Calendar b, int v, account z) {
		transactionreciept t = new transactionreciept(j, k, l, m, b, v, i);
		z.balance -= amount;
		return t;
	}

//CLEARCHECK METHOD FOR NON CD ACCOUNTS
//Input: transactionticket, boolean, string, doubles
//Process: Create transactionreceipt and fill based of given values
//Output: Print all values of the created receipt for check
	public transactionreciept clearCheck(check i, double amount, boolean j, String k, double l, double m, account z) {
		transactionreciept t = new transactionreciept(j, k, l, m);
		z.balance -= amount;
		return t;
	}

//returns account depositer
	public depositer getDepositer() {
		return dep;

	}

//returns account number
	public int getAcctNumber() {
		return accountnumber;
	}

//returns account number
	public String getAcctType() {
		return accountype;
	}

//returns maturity date of a CD account
	public Calendar getMaturityDate() {
		Calendar date = Calendar.getInstance();
		date.clear();
//String [] dateArray = accountdate.split("/");
//date.set(Integer.parseInt(dateArray[2]),
// Integer.parseInt(dateArray[0]) - 1,
// Integer.parseInt(dateArray[1]));
		return date;
	}

//returns an accounts balance
	public double getbalance() {
		return balance;
	}

//returns the account date string
	public String getaccountdate() {
		return accountdate;
	}

//Setter for accountdate
	private void setAccountdate(String i) {
		accountdate = i;
	}

	public void setaccountdate(String i) {
		accountdate = i;
	}
}
