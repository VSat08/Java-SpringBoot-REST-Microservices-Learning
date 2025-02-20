// Customer.java
public class Customer {
    // Instance variables
    private int customerId;
    private String customerName;
    private static String bank = "SBI"; // Static variable
    private Account account; // Aggregation: Customer has an Account

    // Constructor
    public Customer(int customerId, String customerName, Account account) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.account = account;
    }

    // Method to display customer details
    public void display() {
        System.out.println("Customer ID: " + customerId);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Bank: " + bank);
        account.getAccount(); // Display account details
    }

    // Main method
    public static void main(String[] args) {
        // Create an Account object
        Account account = new Account("12345", "Savings", 100000);

        // Create a Customer object
        Customer customer = new Customer(1122, "ABC", account);

        // Display customer details
        customer.display();
    }
}