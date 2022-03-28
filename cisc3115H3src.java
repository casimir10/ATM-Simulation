
//CHRISTIAN CASIMIR Homework 3
import java.io.*;
import java.util.*;

public class cisc3115H3src {
	public static void main(String[] args) throws IOException {
// Bank Object that holds array of Accounts
		bank mainbank = new bank();
// Outputfile and Scanner
		PrintWriter outputfile = new PrintWriter("cisc3115H3out.txt");
		File test = new File("C:/Users/Christian Casimir/" + "eclipse-workspace/cisc3115-3/cisc3115H3test.txt");
		Scanner key = new Scanner(test);
		boolean notdone = true;
		char choice;
// Fill bank object Array
		readAccts(mainbank);
// Print initial database
		printAccts(mainbank, key, outputfile);
// Do while loop that runs through the Switch Cases
		do {
// Call to menu method to print the chart
// menu();
			choice = key.next().charAt(0);
			switch (choice) {
			case 'Q':
			case 'q':
				notdone = false;
				break;
			case 'W':
			case 'w':
				withdrawal(mainbank, key, outputfile);
				break;
			case 'D':
			case 'd':
				deposit(mainbank, key, outputfile);
				break;
			case 'N':
			case 'n':
				newAcct(mainbank, key, outputfile);
				break;
			case 'B':
			case 'b':
				balance(mainbank, key, outputfile);
				break;
			case 'X':
			case 'x':
				deleteAcct(mainbank, key, outputfile);
				break;
			case 'I':
			case 'i':
				acctInfo(mainbank, key, outputfile);
				break;

			case 'C':
			case 'c':
				clearCheck(mainbank, key, outputfile);
				break;
			default:
				outputfile.println("ERROR: Entered an Invalid Value");
				outputfile.println("The Given " + choice + " Is not a Valid Character");
				outputfile.println();
				break;
			}
		} while (notdone);// End Do While Loop
// Print final database
		printAccts(mainbank, key, outputfile);
		key.close();
		outputfile.close();
	}// END MAIN
// MENU METHOD
// Output: Prints the complete menu to Screen

	public static void menu() {
		System.out.println("To quit, select Q.");
		System.out.println("\t****************************");
		System.out.println("\t List of Choices ");
		System.out.println("\t****************************");
		System.out.println("\t Q -- quit");
		System.out.println("\t W -- withdraw");
		System.out.println("\t D -- deposit");
		System.out.println("\t B -- balance");
		System.out.println("\t X -- delete account");
		System.out.println("\t I -- account info");
		System.out.println("\t C -- clear check ");
		System.out.println("\n\n\tEnter your selection: ");
	}

// READACCOUNTS METHOD
// Input: Bank account array, int number of accounts
// Process: While loop runs through input file fills Bank account array
// Output: Returns number of accounts filled
	public static int readAccts(bank bank) throws IOException {
		int count = 0;
		String line;
		File input = new File("C:/Users/Christian Casimir/" + "eclipse-workspace/cisc3115-3/cisc3115H3in.txt");
		Scanner in = new Scanner(input);
		while (in.hasNext()) {
			line = in.nextLine();
			String[] tokens = line.split(" ");
			name newname = new name(tokens[0], tokens[1]);
			depositer deposit = new depositer(newname, tokens[2]);

			account acc = new account(Integer.parseInt(tokens[3]), tokens[4], Double.parseDouble(tokens[5]), deposit);
			if (acc.getAcctType().contentEquals("CD")) {
				acc.setaccountdate(tokens[6]);
			}
			bank.addaccount(acc);
			count++;
		}
		in.close();
		return count;
	}

// BALANCE METHOD
// Input: Bank account array, Int number of accounts
// Process: call to find accounts method to match account
// Output:If not found- Prints error
// If found- Prints Receipt of requested accounts balance
	public static void balance(bank b, Scanner in, PrintWriter out) throws IOException {
		int wantedacct = in.nextInt();
		transactionticket baltick;
		int index = b.findAcct(wantedacct);
		if (index == -1) {
			baltick = new transactionticket("Balance Check", 0.0, 0);
			transactionreciept failed = new transactionreciept(false, "Account Does not Exist", 0, 0, baltick);
			out.println();
			out.println("Account Number: " + wantedacct);
// Print Fail Ticket
			out.println("Transaction-Type : " + failed.getTransactionTicket().getTransactionType());
			out.println("Indicator Flag: " + failed.getTransactionSuccessIndicatorFlag());
			out.println("Failure Reason: " + failed.getTransactionFailureReason());
			out.println();
		} else {
			baltick = new transactionticket("Balance Check", 0.0, 0);
			b.getAcct(index).getBalance(baltick, true, "none", b.getAcct(index).getbalance(),
					b.getAcct(index).getbalance());
			out.println();
			out.println("Account Number: " + wantedacct);

// Print Reciept
			out.println("Indicator Flag: " + true);
			out.println("Fail Reason: " + "none");
			out.println("Pre-Transaction Balance: " + b.getAcct(index).getbalance());
			out.println("Post-Transaction Balance: " + b.getAcct(index).getbalance());
			out.println("Type of Transaction: " + baltick.getTransactionType());
			out.println("Amount of Transaction: " + baltick.getTransactionAmount());
			out.println("Term of CD: " + baltick.gettermofCD());
			String str;
			str = String.format("%02d/%02d/%4d", baltick.getDateOfTransaction().get(Calendar.MONTH) + 1,
					baltick.getDateOfTransaction().get(Calendar.DAY_OF_MONTH),
					baltick.getDateOfTransaction().get(Calendar.YEAR));
			out.println("Date of Transaction: " + str);
			out.println();
		}
	}

// DEPOSIT METHOD
// Input: Array of Bank accounts, int number of accounts
// Process: Call to find account,getbalance and setbalance
// Output: If account not found/incorrect input amount-print error
// Print Reciept and increase balance if no error
	public static void deposit(bank b, Scanner in, PrintWriter out) throws IOException {
		transactionticket deptick;
		System.out.print("Enter the account Number that you want to deposit to: ");
		int wantedacct = in.nextInt();
		int index = b.findAcct(wantedacct);
		System.out.print("Enter the amount that you would like to deposit: ");
		double transac = in.nextDouble();
		if (index == -1) {
			deptick = new transactionticket("Deposit", 0.0, 0);
			transactionreciept failed = new transactionreciept(false, "Account Does not Exist", 0, 0, deptick);
			out.println();
			out.println("Account Number: " + wantedacct);
// Print Reciept
			out.println("Transaction-Type : " + failed.getTransactionTicket().getTransactionType());
			out.println("Indicator Flag: " + failed.getTransactionSuccessIndicatorFlag());
			out.println("Failure Reason: " + failed.getTransactionFailureReason());

			out.println();
		} else if (transac <= 0) {
			deptick = new transactionticket("Deposit", 0.0, 0);
			b.getAcct(index).makeDeposit(deptick, 0.0, false, "Invalid Deposit Amount", b.getAcct(index).getbalance(),
					b.getAcct(index).getbalance(), b.getAcct(index));
			out.println();
			out.println("Account Number: " + wantedacct);
// Print Reciept
			out.println("Indicator Flag: " + false);
			out.println("Fail Reason: " + "Invalid Deposit Amount");
			out.println("Type of Transaction: " + "Deposit");
			String str;
			str = String.format("%02d/%02d/%4d", deptick.getDateOfTransaction().get(Calendar.MONTH) + 1,
					deptick.getDateOfTransaction().get(Calendar.DAY_OF_MONTH),
					deptick.getDateOfTransaction().get(Calendar.YEAR));
			out.println("Date of Transaction: " + str);
			out.println();
		}
// Check to see if Account is a CD*****************************************
		else if (b.getAcct(index).getAcctType().equals("CD")) {
			if (b.getAcct(index).getMaturityDate().get(Calendar.DAY_OF_YEAR) < Calendar.getInstance()
					.get(Calendar.DAY_OF_YEAR)) {
				deptick = new transactionticket("Withdraw", 0.0, 0);
				b.getAcct(index).makeDeposit(deptick, 0, false, "Account not Mature", b.getAcct(index).getbalance(),
						b.getAcct(index).getbalance(), b.getAcct(index));
				out.println();
				out.println("Account Number: " + wantedacct);
// Print Reciept
				out.println("Indicator Flag: " + false);
				out.println("Fail Reason: " + "Account not Mature");
				out.println("Type of Transaction: " + "Deposit");
				String str;
				str = String.format("%02d/%02d/%4d", deptick.getDateOfTransaction().get(Calendar.MONTH) + 1,
						deptick.getDateOfTransaction().get(Calendar.DAY_OF_MONTH),
						deptick.getDateOfTransaction().get(Calendar.YEAR));
				out.println("Date of Transaction: " + str);
				out.println();
			} else {
				System.out.print("Select 12,18,or 24 Months for new Maturity");
				int addmonths = in.nextInt();
				Calendar date = Calendar.getInstance();
				deptick = new transactionticket("Withdraw", transac, addmonths);
				b.getAcct(index).makeDeposit(deptick, transac, true, "none", b.getAcct(index).getbalance(),

						b.getAcct(index).getbalance() + transac, date, addmonths, b.getAcct(index));
				out.println();
				out.println("Account Number: " + wantedacct);
// Print Reciept
				out.println("Indicator Flag: " + true);
				out.println("Fail Reason: " + "none");
				out.println("Pre-Transaction Balance: " + b.getAcct(index).getbalance());
				out.println("Post-Transaction Balance: " + b.getAcct(index).getbalance() + transac);
				String st;
				date.add(Calendar.MONTH, addmonths);
				st = String.format("%02d/%02d/%4d", date.get(Calendar.MONTH) + 1, date.get(Calendar.DAY_OF_MONTH),
						date.get(Calendar.YEAR));
				b.getAcct(index).setaccountdate(st);
				out.println("Post-Transaction Maturity Date: " + st);
				out.println("Type of Transaction: " + deptick.getTransactionType());
				out.println("Amount of Transaction: " + deptick.getTransactionAmount());
				out.println("Term of CD: " + deptick.gettermofCD());
				String str;
				str = String.format("%02d/%02d/%4d", deptick.getDateOfTransaction().get(Calendar.MONTH) + 1,
						deptick.getDateOfTransaction().get(Calendar.DAY_OF_MONTH),
						deptick.getDateOfTransaction().get(Calendar.YEAR));
				out.println("Date of Transaction: " + str);
				out.println();
			}
		} // *****************************************************************
		else {
			deptick = new transactionticket("Deposit", transac, 0);
			b.getAcct(index).makeDeposit(deptick, transac, true, "none", b.getAcct(index).getbalance(),
					b.getAcct(index).getbalance() + transac, b.getAcct(index));
			out.println();
			out.println("Account Number: " + wantedacct);
// Print Reciept
			out.println("Indicator Flag: " + true);
			out.println("Fail Reason: " + "none");
			out.println("Pre-Transaction Balance: " + b.getAcct(index).getbalance());
			out.println("Post-Transaction Balance: " + (b.getAcct(index).getbalance() + transac));
			out.println("Type of Transaction: " + deptick.getTransactionType());
			out.println("Amount of Transaction: " + deptick.getTransactionAmount());
			out.println("Term of CD: " + deptick.gettermofCD());
			String str;
			str = String.format("%02d/%02d/%4d", deptick.getDateOfTransaction().get(Calendar.MONTH) + 1,
					deptick.getDateOfTransaction().get(Calendar.DAY_OF_MONTH),
					deptick.getDateOfTransaction().get(Calendar.YEAR));

			out.println("Date of Transaction: " + str);
			out.println();
		}
	}

// WITHDRAW METHOD
// Input: Array of Bank accounts, int number of accounts
// Process: Call to find account,getbalance and setbalance
// Output: If account not found/less than required balance-print error
// Print Reciept and decrease balance if no error
	public static void withdrawal(bank b, Scanner in, PrintWriter out) throws IOException {
		transactionticket withtick;
		System.out.print("Enter the account Number that you want to deposit to: ");
		int wantedacct = in.nextInt();
		int index = b.findAcct(wantedacct);
		System.out.print("Enter the amount that you would like to deposit: ");
		double transac = in.nextDouble();
		if (index == -1) {
			withtick = new transactionticket("Withdraw", 0.0, 0);
			transactionreciept failed = new transactionreciept(false, "Account Does not Exist", 0, 0, withtick);
			out.println();
			out.println("Account Number: " + wantedacct);
// Print Reciept
			out.println("Transaction-Type : " + failed.getTransactionTicket().getTransactionType());
			out.println("Indicator Flag: " + failed.getTransactionSuccessIndicatorFlag());
			out.println("Failure Reason: " + failed.getTransactionFailureReason());
			out.println();
		} else if (transac > b.getAcct(index).getbalance() || transac <= 0) {
			withtick = new transactionticket("Withdraw", 0.0, 0);
			b.getAcct(index).makeWithdrawal(withtick, 0.0, false, "Invalid Withdraw Amount",
					b.getAcct(index).getbalance(), b.getAcct(index).getbalance(), b.getAcct(index));
			out.println();
			out.println("Account Number: " + wantedacct);
// Print Reciept
			out.println("Indicator Flag: " + false);
			out.println("Fail Reason: " + "Invalid Withdraw Amount");
			out.println("Type of Transaction: " + "Withdraw");
			String str;
			str = String.format("%02d/%02d/%4d", withtick.getDateOfTransaction().get(Calendar.MONTH) + 1,

					withtick.getDateOfTransaction().get(Calendar.DAY_OF_MONTH),
					withtick.getDateOfTransaction().get(Calendar.YEAR));
			out.println("Date of Transaction: " + str);
			out.println();
		}
// Check to see if Account is a CD**************************************
		else if (b.getAcct(index).getAcctType().equals("CD")) {
			if (b.getAcct(index).getMaturityDate().get(Calendar.DAY_OF_YEAR) < Calendar.getInstance()
					.get(Calendar.DAY_OF_YEAR)) {
				withtick = new transactionticket("Withdraw", 0.0, 0);
				b.getAcct(index).makeWithdrawal(withtick, 0, false, "Account not Mature", b.getAcct(index).getbalance(),
						b.getAcct(index).getbalance(), b.getAcct(index));
				out.println();
				out.print("Account Number: " + wantedacct);
// Print Reciept
				out.println("Indicator Flag: " + false);
				out.println("Fail Reason: " + "Account not Mature");
				out.println("Type of Transaction: " + "Withdraw");
				String str;
				str = String.format("%02d/%02d/%4d", withtick.getDateOfTransaction().get(Calendar.MONTH) + 1,
						withtick.getDateOfTransaction().get(Calendar.DAY_OF_MONTH),
						withtick.getDateOfTransaction().get(Calendar.YEAR));
				out.println("Date of Transaction: " + str);
				out.println();
				out.println();
			} else {
				System.out.print("Select 12,18,or 24 Months for new Maturity");
				int addmonths = in.nextInt();
				Calendar date = Calendar.getInstance();
				withtick = new transactionticket("Withdraw", transac, addmonths);
				b.getAcct(index).makeWithdrawal(withtick, transac, true, "none", b.getAcct(index).getbalance(),
						b.getAcct(index).getbalance() - transac, Calendar.getInstance(), addmonths, b.getAcct(index));
				out.println();
				out.println("Account Number: " + wantedacct);
// Print Reciept
				out.println("Indicator Flag: " + true);
				out.println("Fail Reason: " + "none");
				out.println("Pre-Transaction Balance: " + b.getAcct(index).getbalance());
				out.println("Post-Transaction Balance: " + (b.getAcct(index).getbalance() - transac));
				String st;
				date.add(Calendar.MONTH, addmonths);
				st = String.format("%02d/%02d/%4d", date.get(Calendar.MONTH) + 1, date.get(Calendar.DAY_OF_MONTH),
						date.get(Calendar.YEAR));

				b.getAcct(index).setaccountdate(st);
				out.println("Post-Transaction Maturity Date: " + st);
				out.println("Type of Transaction: " + withtick.getTransactionType());
				out.println("Amount of Transaction: " + withtick.getTransactionAmount());
				out.println("Term of CD: " + withtick.gettermofCD());
				String str;
				str = String.format("%02d/%02d/%4d", withtick.getDateOfTransaction().get(Calendar.MONTH) + 1,
						withtick.getDateOfTransaction().get(Calendar.DAY_OF_MONTH),
						withtick.getDateOfTransaction().get(Calendar.YEAR));
				out.println("Date of Transaction: " + str);
				out.println();
			}
		} // *******************************************************************8
		else {
			withtick = new transactionticket("Withdraw", transac, 0);
			b.getAcct(index).makeWithdrawal(withtick, transac, true, "none", b.getAcct(index).getbalance(),
					b.getAcct(index).getbalance() - transac, b.getAcct(index));
			out.println();
			out.println("Account Number: " + wantedacct);
// Print Reciept
			out.println("Indicator Flag: " + true);
			out.println("Fail Reason: " + "none");
			out.println("Pre-Transaction Balance: " + b.getAcct(index).getbalance());
			out.println("Post-Transaction Balance: " + (b.getAcct(index).getbalance() + transac));
			out.println("Type of Transaction: " + withtick.getTransactionType());
			out.println("Amount of Transaction: " + withtick.getTransactionAmount());
			out.println("Term of CD: " + withtick.gettermofCD());
			String str;
			str = String.format("%02d/%02d/%4d", withtick.getDateOfTransaction().get(Calendar.MONTH) + 1,
					withtick.getDateOfTransaction().get(Calendar.DAY_OF_MONTH),
					withtick.getDateOfTransaction().get(Calendar.YEAR));
			out.println("Date of Transaction: " + str);
			out.println();
		}
	}

// CLEARCHECK METHOD
// Input: Bank Object, Int accountnumber, Double check amount
// Process:Search for account by calling findAcct method
// Deducts check amount from balance if boolean is true
// Output: Prints receipt based on given values
	public static void clearCheck(bank b, Scanner in, PrintWriter out) throws IOException {
		Calendar date = Calendar.getInstance();
		date.clear();
		System.out.print("Enter the account number of the check :");
		int wantedacct = in.nextInt();
		System.out.print("Enter the check amount: ");

		double checkamount = in.nextDouble();
		System.out.print("Enter the date of the check: ");
		String checkdate = in.next();
		int index = b.findAcct(wantedacct);
		if (index == -1 || b.getAcct(index).getAcctType().contentEquals("Saving")
				|| b.getAcct(index).getAcctType().contentEquals("CD")) {
			transactionticket checktick = new transactionticket("Check Clear", checkamount, 0);
			transactionreciept failed = new transactionreciept(false, "Account Does not Exist", 0, 0, checktick);
			checktick = new transactionticket("Deposit", 0.0, 0);
			out.println();
			out.println("Account Number: " + wantedacct);
// Print Reciept
			out.println("Transaction-Type : " + failed.getTransactionTicket().getTransactionType());
			out.println("Indicator Flag: " + failed.getTransactionSuccessIndicatorFlag());
			out.println("Failure Reason: " + failed.getTransactionFailureReason());
			out.println();
		}
		check tempcheck = new check();
		if (date.get(Calendar.MONTH) - tempcheck.getCheckDate().get(Calendar.MONTH) > 6) {
			check newcheck = new check(wantedacct, checkamount, checkdate);
			b.getAcct(index).clearCheck(newcheck, 0.0, false, "Check Has Expired- Past 6 Months",
					b.getAcct(index).getbalance(), b.getAcct(index).getbalance(), b.getAcct(index));
			out.println();
			out.println("Account Number: " + wantedacct);
// Print Reciept
			out.println("Indicator Flag: " + false);
			out.println("Fail Reason: " + "Check Has Expired- Past 6 Months");
			out.println("Pre-Transaction Balance: " + b.getAcct(index).getbalance());
			out.println("Post-Transaction Balance: " + b.getAcct(index).getbalance());
			out.println("Check Amount: " + newcheck.getcheckamount());
			String str;
			str = String.format("%02d/%02d/%4d", newcheck.getCheckDate().get(Calendar.MONTH) + 1,
					newcheck.getCheckDate().get(Calendar.DAY_OF_MONTH), newcheck.getCheckDate().get(Calendar.YEAR));
			out.println("Check Date: " + str);
			out.println();
		} else if (checkamount > b.getAcct(index).getbalance()) {
			check newcheck = new check();
			b.getAcct(index).clearCheck(newcheck, 2.50, false,

					"Check Amount is Greater than Balance- 2.50$ for Bouncing", b.getAcct(index).getbalance(),
					b.getAcct(index).getbalance() - 2.50, b.getAcct(index));
			out.println();
			out.println("Account Number: " + wantedacct);
// Print Reciept
			out.println("Indicator Flag: " + false);
			out.println("Fail Reason: " + "Check Amount is Greater than Balance");
			out.println("Pre-Transaction Balance: " + b.getAcct(index).getbalance());
			out.println("Post-Transaction Balance: " + b.getAcct(index).getbalance());
			out.println("Check Amount: " + newcheck.getcheckamount());
			String str;
			str = String.format("%02d/%02d/%4d", newcheck.getCheckDate().get(Calendar.MONTH) + 1,
					newcheck.getCheckDate().get(Calendar.DAY_OF_MONTH), newcheck.getCheckDate().get(Calendar.YEAR));
			out.println("Check Date: " + str);
			out.println();
		} else {
			check newcheck = new check();
			b.getAcct(index).clearCheck(newcheck, checkamount, true, "none", b.getAcct(index).getbalance(),
					b.getAcct(index).getbalance() - checkamount, b.getAcct(index));
			out.println();
			out.println("Account Number: " + wantedacct);
// Print Reciept
			out.println("Indicator Flag: " + true);
			out.println("Fail Reason: " + "none");
			out.println("Pre-Transaction Balance: " + b.getAcct(index).getbalance());
			out.println("Post-Transaction Balance: " + (b.getAcct(index).getbalance() - newcheck.getcheckamount()));
			out.println("Check Amount: " + newcheck.getcheckamount());
			String str;
			str = String.format("%02d/%02d/%4d", newcheck.getCheckDate().get(Calendar.MONTH) + 1,
					newcheck.getCheckDate().get(Calendar.DAY_OF_MONTH), newcheck.getCheckDate().get(Calendar.YEAR));
			out.println("Check Date: " + str);
			out.println();
		}
	}

// ACCOUNT INFO METHOD
// Input: Bank object, String social security number
// Process: Prompt for ssn, while loop to match account number
// Output: if found - call to print accounts to print info
// not found - print error
	public static void acctInfo(bank b, Scanner in, PrintWriter out) throws IOException {
		System.out.print("Enter the SSN to Lookup");

		String social = in.next();
		int accountsfound = 0;
		for (int i = 0; i < b.getnumaccts(); i++) {
			if (b.getAcct(i).getDepositer().getSSN().equals(social)) {
				out.printf("%5d %18.2f %15s %15s %14s %18s %15s\n", b.getAcct(i).getAcctNumber(),
						b.getAcct(i).getbalance(), b.getAcct(i).getAcctType(),
						b.getAcct(i).getDepositer().getName().getFirstName(),
						b.getAcct(i).getDepositer().getName().getLastName(), b.getAcct(i).getDepositer().getSSN(),
						b.getAcct(i).getaccountdate());
				accountsfound++;
			} else if (i == b.getnumaccts() - 1 && accountsfound == 0) {
				out.println("ERROR : NO ACCOUNTS WITH THE SSN " + social + " EXIST");
			}
		}
		out.println();
	}

// NEWACCT METHOD
// Input : Take in Bank Object, Int number of accounts
// prompts for new account number
// Process :
// calls findacct to check if account exists
// if exists-prints error
// if does not exist-prompts for name,ssn,accountype
// increase number of accounts
// Output : prints receipt based off given values
	public static void newAcct(bank b, Scanner in, PrintWriter out) throws IOException {
		int wantedacct = in.nextInt();
		transactionticket newacctick;
		int index = b.findAcct(wantedacct);
		if (index != -1 || wantedacct < 0) {
			newacctick = new transactionticket("New Account Creation", 0.0, 0);
			out.println();
			out.println("Account Number: " + wantedacct);
// Print Reciept
			out.println("Indicator Flag: " + false);
			out.println("Fail Reason: " + "Account Already Exists");
			out.println("Type of Transaction: " + "New Account Creation");
			String str;
			str = String.format("%02d/%02d/%4d", newacctick.getDateOfTransaction().get(Calendar.MONTH) + 1,
					newacctick.getDateOfTransaction().get(Calendar.DAY_OF_MONTH),
					newacctick.getDateOfTransaction().get(Calendar.YEAR));
			out.println("Date of Transaction: " + str);
			out.println();
		}

		else {
			System.out.print("Enter the account type: ");
			String acct = in.next();
			System.out.print("Enter the account date");
			String accd = in.next();// Enter n/a if not a Cd
			System.out.print("Enter the account firstname");
			String depf = in.next();
			System.out.print("Enter the account lastname");
			String depl = in.next();
			System.out.print("Enter the account ssn");
			String depssn = in.next();

			newacctick = new transactionticket("New Account Creation", 0, 0);
			b.openNewAcct(newacctick, true, "none", 0.0, 0, wantedacct, acct, 0.0, accd, depssn, depf, depl);
			out.println();
			out.println("Account Number: " + wantedacct);
// Print Reciept
			out.println("Indicator Flag: " + true);
			out.println("Fail Reason: " + "none");
			out.println("Pre-Transaction Balance: " + "0.0");
			out.println("Post-Transaction Balance: " + "0.0");
			out.println("Type of Transaction: " + newacctick.getTransactionType());
			out.println("Amount of Transaction: " + newacctick.getTransactionAmount());
			out.println("Term of CD: " + newacctick.gettermofCD());
			String str;
			str = String.format("%02d/%02d/%4d", newacctick.getDateOfTransaction().get(Calendar.MONTH) + 1,
					newacctick.getDateOfTransaction().get(Calendar.DAY_OF_MONTH),
					newacctick.getDateOfTransaction().get(Calendar.YEAR));
			out.println("Date of Transaction: " + str);
			out.println();
		}
	}

// DELACCT METHOD
// Input : Take in Bank account array, Int number of accounts
// prompts for account number that will be deleted
// Process : calls findacct to check if account doesnt exist or has a balance
// if doesnt exist or has a balance - prints error
// if it does exist- Swap all values of selected account with last account
// Decrease the number of accounts by 1
// Output : Prints reciept based off given values
	public static void deleteAcct(bank b, Scanner in, PrintWriter out) throws IOException {
		int wantedacct = in.nextInt();
		transactionticket delacctick;
		int index = b.findAcct(wantedacct);

		if (index == -1) {
			delacctick = new transactionticket("Delete Account", 0.0, 0);
			transactionreciept failed = new transactionreciept(false, "Account Does not Exist", 0, 0, delacctick);
			delacctick = new transactionticket("Deposit", 0.0, 0);
			out.println();
			out.println("Account Number: " + wantedacct);
// Print Fail Ticket
			out.println("Transaction-Type : " + failed.getTransactionTicket().getTransactionType());
			out.println("Indicator Flag: " + failed.getTransactionSuccessIndicatorFlag());
			out.println("Failure Reason: " + failed.getTransactionFailureReason());
			out.println();
		}
		if (b.getAcct(index).getbalance() > 0) {
			delacctick = new transactionticket("Delete Account", 0.0, 0);
			b.deleteAcct(delacctick, false, "Given Account Has a Standing Balance", b.getAcct(index).getbalance(),
					b.getAcct(index).getbalance(), b.findAcct(wantedacct));
			out.println();
			out.println("Account Number: " + wantedacct);
// Print Reciept
			out.println("Indicator Flag: " + false);
			out.println("Fail Reason: " + "Given Account Has a Standing Balance- " + "Please remove to Delete Account");
			out.println("Type of Transaction: " + "Delete Account");
			out.println("Balance: " + b.getAcct(index).getbalance());
			String str;
			str = String.format("%02d/%02d/%4d", delacctick.getDateOfTransaction().get(Calendar.MONTH) + 1,
					delacctick.getDateOfTransaction().get(Calendar.DAY_OF_MONTH),
					delacctick.getDateOfTransaction().get(Calendar.YEAR));
			out.println("Date of Transaction: " + str);
			out.println();
		} else {
			delacctick = new transactionticket("Delete Account", 0.0, 0);
			b.deleteAcct(delacctick, true, "none", b.getAcct(index).getbalance(), b.getAcct(index).getbalance(),
					b.findAcct(wantedacct));
			out.println();
			out.println("Account Number: " + wantedacct);
// Print Reciept
			out.println("Indicator Flag: " + true);

			out.println("Fail Reason: " + "none");
			out.println("Pre-Transaction Balance: " + "0.0");
			out.println("Post-Transaction Balance: " + "0.0");
			out.println("Type of Transaction: " + delacctick.getTransactionType());
			out.println("Amount of Transaction: " + delacctick.getTransactionAmount());
			out.println("Term of CD: " + delacctick.gettermofCD());
			String str;
			str = String.format("%02d/%02d/%4d", delacctick.getDateOfTransaction().get(Calendar.MONTH) + 1,
					delacctick.getDateOfTransaction().get(Calendar.DAY_OF_MONTH),
					delacctick.getDateOfTransaction().get(Calendar.YEAR));
			out.println("Date of Transaction: " + str);
			out.println();
		}
	}

// PRINT ACCOUNTS METHOD
// Input: Bank account array, int number of accounts
// Process: For loop to run through all bank account objects
// Output: Prints all accounts to file
	public static void printAccts(bank b, Scanner in, PrintWriter out) throws IOException {
		out.println("CHRISTIAN CASIMIR");
		out.printf("%5s %10s %15s %15s %14s %13s %15s\n", "Account Number", "Balance", "Account Type", "First Name",
				"Last Name", "SSN", "Maturity Date");
		for (int count = 0; count < b.getnumaccts(); count++) {
			out.print(b.getAcct(count).getAcctNumber());
			out.printf("%19.2f", b.getAcct(count).getbalance());
			out.printf("%16s", b.getAcct(count).getAcctType());
			out.printf("%15s", b.getAcct(count).getDepositer().getName().getFirstName());
			out.printf("%14s", b.getAcct(count).getDepositer().getName().getLastName());
			out.printf("%18s", b.getAcct(count).getDepositer().getSSN());
			if (b.getAcct(count).getAcctType().contentEquals("CD")) {
				out.printf("%14s\n", b.getAcct(count).getaccountdate());
			} else {
				out.printf("%14s\n", "n/a");
			}
		}
		System.out.println();
	}
}// END CLASS
