# ATM-System-Complete

PURPOSE: Implmement the necessary systems for a typical Automated Teller System (ATM) utilizing a combination
of Classes, Methods and interfaces.

UI : 

Console Displayed Menu,Text Based Input and Output also done via Console. A txt file is created to display
all existing contents of the ATM folling the end of a session.

FEATURE LIST:

There are 3 seperate account types that are Supported
- Checking
- Savings
- Certificate of Deposit (CD)  

Each account type is linked to the following

- Name(First,Last)
- A unique Account Number
- Social Security Number

Every Account has access to the following queries treated as transactions at the ATM:

- Withdrawals (CD accounts have Maturity Dates before applicable)
- Deposits
- Balance Checks
- Account Deletions and new account Creation
- Information Lookup for existing accounts
- Clearing Checks 


GENERAL INFO ABOUT THE IMPLEMENTATION: 

- The ATM stores all accounts within a central database (Bank Class) with a total capacity of 50 accounts

- Every transaction generates a Transaction Recepit and Ticket

Transaction Reciept: Manages transaction sucess or failure, Provides the post and pre 
transaction Balances, creates a trasaction ticket 

Transaction Ticket: Manages the type of a transaction, its date, and the total cash value of the 
transaction. Also tracks the term of CD accounts


This was an exercise in utilizing a modern Java programming approach to completing an ATM.

The following techniques were utilized:

- Seperating functions into repsective objects which communicate
- Using an interface for shared methods
- Taking advantage of immutable/final variables and abstraction
- Implmementing switch cases to call methods
- Printing to txt files
