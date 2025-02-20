// Account.java
public class Account {
    // Private instance variables
    private String accountNumber;
    private String accountType;
    private double accountBalance;

    // Constructor
    public Account(String accountNumber, String accountType, double accountBalance) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
    }

    // Method to display account details
    public void getAccount() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Type: " + accountType);
        System.out.println("Account Balance: " + accountBalance);
    }
}