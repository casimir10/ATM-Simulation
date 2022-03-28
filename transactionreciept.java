import java.io.*;
import java.util.Calendar;
public class transactionreciept {

	private transactionticket ticket;
	private boolean successindflag;
	private String failreason;
	private double pretransacbal;
	private double postransacbal;
	private Calendar postransacmaturity;
	
	//no-arg constructor for transaction receipt
	public transactionreciept() {
		ticket = new transactionticket();
		successindflag = false;
		failreason = "";
		pretransacbal = 0.0;
		postransacbal = 0.0;
		postransacmaturity = Calendar.getInstance();
		postransacmaturity.clear();
	}
	//5-arg constructor for transaction receipt
	public transactionreciept(boolean i, String j, double k, double l, String date) {
		ticket = new transactionticket();
	successindflag = i;
	failreason = j;
	pretransacbal = k;
	postransacbal =l;
	postransacmaturity = Calendar.getInstance();
	postransacmaturity.clear();
	String [] dateArray = date.split("/");
	postransacmaturity.set(Integer.parseInt(dateArray[2]), 
			Integer.parseInt(dateArray[0]) - 1, Integer.parseInt(dateArray[1]));
	}
	//6-arg constructor for transaction reciept For CD Accounts
	public transactionreciept(boolean i, String j, double k, double l, Calendar date
			, int add) {
		ticket = new transactionticket();
	successindflag = i;
	failreason = j;
	pretransacbal = k;
	postransacbal =l;
	postransacmaturity = date;
	postransacmaturity.add(Calendar.DATE, add);
	
	}
	
	//for when input fails
	public transactionreciept(boolean i, String j) {
	ticket = new transactionticket();
	successindflag = i;
	}
	
	public transactionreciept(boolean j, String k, double l, double m, Calendar b, int v, transactionticket i) {
		// TODO Auto-generated constructor stub
		successindflag = j;
		failreason = k;
		pretransacbal = l;
		postransacbal = m;
		postransacmaturity = b;
		postransacmaturity.add(Calendar.DATE, v);
		ticket = i;
	}
	public transactionreciept(boolean j, String k, double l, double m, transactionticket i) {
		// TODO Auto-generated constructor stub
		successindflag = j;
		failreason = k;
		pretransacbal = l;
		postransacbal = m;
		ticket = i;
	}
	public transactionreciept(transactionticket i) {
		// TODO Auto-generated constructor stub
		ticket = i;
	}
	public transactionreciept(boolean j, String k, double l, double m) {
		// TODO Auto-generated constructor stub
		successindflag = j;
		failreason = k;
		pretransacbal = l;
		postransacbal = m;
	}
	//returns transaction ticket object
	public transactionticket getTransactionTicket() {
		return ticket;
	}
	//returns the indicator flag
	public boolean getTransactionSuccessIndicatorFlag() {
	return successindflag;	
	}
	//returns a failure reason string
	public String getTransactionFailureReason() {
	return failreason;	
	}
	//returns balance before a transaction
	public double getPreTransactionBalance() {
	return pretransacbal;	
	}
	//returns balance after a transaction
	public double getPostTransactionBalance() {
	return postransacbal;	
	}
	//returns CD maturity date after a transaction
	public Calendar getPostTransactionMaturityDate() {
	return postransacmaturity;
	}
	//allows the setting of the indicator flag
	public void setindflag(boolean i) {
		successindflag = i;
	}
	//allows the setting of the failure string
	public void settransfailreason(String i) {
		failreason = i;
	}
	//allows the setting of balance before transaction
	public void setpretransacbal(Double i) {
		pretransacbal = i;
	}
	//allows the setting of balance after transaction
	public void setpostransacbal(Double i) {
		postransacbal = i;
	}
	//allows the setting of a transaction ticket
	public void setransacticket(transactionticket i) {
		ticket = i;
	}
	//allows the setting of a CD transaction maturity
	public void settransacmaturity(Calendar i) {
		postransacmaturity = i;
	}
	
	public void printfailreciept(transactionreciept i,PrintWriter j) {
		j.println();
		j.println("Transaction-Type : " + i.getTransactionTicket().getTransactionType());
		j.println("Indicator Flag: " + i.getTransactionSuccessIndicatorFlag());
		j.println("Failure Reason: " + i.getTransactionFailureReason());
		j.println();
	}
	
	
}
