
import java.util.Calendar;
public class transactionticket {

	private Calendar dateoftransac;
	private String typeoftransac;
	private double amountoftransac;
	private int termofcd;
	//no-arg constructor for transaction ticket
	public transactionticket() {
		dateoftransac = Calendar.getInstance();
		dateoftransac.clear();
		typeoftransac = "";
		termofcd = 0;
		amountoftransac = 0.0;
	}
	//4-arg constructor for transaction ticket
	public transactionticket(String i, double j, int l, String date) {
		typeoftransac = i;
		amountoftransac = j;
		termofcd = l;
		dateoftransac = Calendar.getInstance();
		dateoftransac.clear();
		String [] dateArray = date.split("/");
		dateoftransac.set(Integer.parseInt(dateArray[2]), 
				Integer.parseInt(dateArray[0]) - 1, Integer.parseInt(dateArray[1]));
	}
	//3-arg constructor for transaction ticket
	public transactionticket(String i, double j, int l) {
		typeoftransac = i;
		amountoftransac = j;
		termofcd = l;
		dateoftransac = Calendar.getInstance();
		dateoftransac.clear();
	}
	//returns the calendar date of a transaction
	public Calendar getDateOfTransaction() {
	return Calendar.getInstance();
	}
	//returns the type of transaction
	public String getTransactionType() {
	return typeoftransac;	
	}
	//returns the amount of a transaction
	public double getTransactionAmount() {
	return amountoftransac;
	}
	//returns the term of a CD account
	public int gettermofCD() {
	return termofcd;
	}
	//allows the setting of a transaction type
	public void setTransactionType(String i) {
		typeoftransac = i;
	}
	//allows the setting of a CD accounts term
	public void settermofCD(int i) {
		termofcd = i;
	}
	//allows the setting of a transaction amount 
	public void setransacamount(double i) {
		 amountoftransac = i;
	 }
}
