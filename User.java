import java.util.ArrayList;
import java.util.List;

public class User {
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
