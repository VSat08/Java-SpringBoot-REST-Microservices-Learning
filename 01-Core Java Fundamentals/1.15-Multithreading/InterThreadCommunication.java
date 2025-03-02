class Customer {
    double balance;

    public Customer(double balance) {
        this.balance = balance;
    }

    public void getBalance() {
        System.out.println("Balance: " + balance);
    }

    @SuppressWarnings("UseSpecificCatch")
    public synchronized void withdraw(double amount) {
        System.out.println("Withdrawing in few seconds: ");
        if (amount > balance) {
            System.out.println("Insufficient balance --- Waiting for deposit --- ");
            try {
                wait();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        balance = balance - amount;
        System.out.println("Withdrawl completed!");
        getBalance();
    }

    public synchronized void deposite(double amount) {
        System.out.println("Depositing initiated: ");

        balance = amount + balance;
        System.out.println("Deposite completed!");
        getBalance();
        notify();
}
}


public class InterThreadCommunication {
    public static void main(String[] args) {
        Customer customer = new Customer(25000);

        new Thread() {
            @Override
            public  void run() {
                customer.withdraw(30000);
            }
        }.start();

        new Thread() {
            @Override
            public  void run() {
                customer.deposite(25000);
            }
        }.start();
    }
}
