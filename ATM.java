import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATM {
    private Menu menu;
    private User user;
    private List<User> userList;

    public ATM() {
        this.menu = new Menu();
        // Initialize userList with example users
        this.userList = new ArrayList<>();
        this.userList.add(new User("user1", 1000.0)); // Example user with ID "user1" and initial balance 1000.0
        this.userList.add(new User("user2", 500.0)); // Example user with ID "user2" and initial balance 500.0
        this.user = userList.isEmpty() ? null : userList.get(0);
    }
    


    public void run() {
        // Authenticate user
        boolean authenticated = authenticateUser();
        if (!authenticated) {
            System.out.println("Authentication failed. Exiting.");
            return;
        }
    
        // Display menu and process user choice
        while (true) {
            menu.displayMenu();
            int choice = menu.getChoice();
            switch (choice) {
                case 1:
                    // View transaction history
                    user.viewTransactionHistory();
                    break;
                case 2:
                    // Withdraw
                    user.withdraw(100); // Example withdrawal amount
                    break;
                case 3:
                    // Deposit
                    user.deposit(200); // Example deposit amount
                    break;
                case 4:
                    // Transfer
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Enter recipient user ID: ");
                    String recipientUserID = scanner.nextLine();
                    user.transfer(50.0, recipientUserID, userList); // Example transfer amount
                    break;
                case 5:
                    // Quit
                    System.out.println("Exiting ATM. Thank you for using our services!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private boolean authenticateUser() {
        // Mock authentication (always return true for now)
        return true;
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.run();
    }
}

class User {
    private String userID;
    private double balance;
    private List<Transaction> transactionHistory;

    public User(String userID, double initialBalance) {
        this.userID = userID;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public void viewTransactionHistory() {
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdraw", -amount));
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add(new Transaction("Deposit", amount));
        System.out.println("Deposit successful. New balance: " + balance);
    }

    public void transfer(double amount, String recipientUserID, List<User> userList) {
        User recipient = null;
        for (User user : userList) {
            if (user.getUserID().equals(recipientUserID)) {
                recipient = user;
                break;
            }
        }

        if (recipient == null) {
            System.out.println("Recipient user not found.");
            return;
        }

        if (amount <= balance) {
            balance -= amount;
            recipient.balance += amount;
            transactionHistory.add(new Transaction("Transfer to " + recipientUserID, -amount));
            recipient.transactionHistory.add(new Transaction("Transfer from " + userID, amount));
            System.out.println("Transfer successful. New balance: " + balance);
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public String getUserID() {
        return userID;
    }
}

class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "type='" + type + '\'' +
                ", amount=" + amount +
                '}';
    }
}

class Menu {
    private Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("Welcome to the ATM System");
        System.out.println("1. View Transaction History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Quit");
        System.out.print("Enter your choice: ");
    }

    public int getChoice() {
        return scanner.nextInt();
    }
}
