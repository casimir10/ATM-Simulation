
public class bank {
	private int numaccts;
	private account[] database;
	private final int MAX_NUMS = 50;

//No-arg Constructor
	public bank() {
//numaccts = 0;
		database = new account[MAX_NUMS];
	}

//6-arg Constructor for new Account creation Receipt
	public transactionreciept openNewAcct(transactionticket i, boolean j, String k, double l, double m, int wantedacct,
			String acct, double bal, String accd, String depssn, String depf, String depl) {
		transactionreciept t = new transactionreciept(j, k, l, m);
		account newacc = new account(wantedacct, acct, bal, accd, depssn, depf, depl);
		addaccount(newacc);
		return t;
	}

//6-arg Constructor for delete account Receipt
	public transactionreciept deleteAcct(transactionticket i, boolean j, String k, double l, double m,
			int acctodelete) {
		transactionreciept t = new transactionreciept(j, k, l, m);
		if (j == true) {
			deleteaccount(acctodelete);
		}
		return t;
	}

//FIND ACCOUNT METHOD
//Input: Int account number
//Process: For loop runs through array to match account number
//Output: Returns index if found, -1 if not
	public int findAcct(int reqaccount) {
		for (int i = 0; i < numaccts; i++)
			if (database[i].getAcctNumber() == reqaccount)
				return i;
		return -1;
	}

//returns account object
	public account getAcct(int i) {
		return database[i];
	}

//returns number of accounts currently in database
	public int getnumaccts() {
		return numaccts;
	}
//adds a new account to the end of database
//increases number of accounts

	public void addaccount(account i) {
		database[numaccts] = i;
		numaccts += 1;
	}

//deletes an account by swapping with last account
//decreases number of accounts
	public void deleteaccount(int i) {
		database[i] = database[numaccts - 1];
		numaccts -= 1;
	}

	public void setnumaccts(int i) {
		numaccts = i;
	}
}