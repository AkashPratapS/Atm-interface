import java.util.Scanner;

public class Menu {
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
        int choice = 0;
        try {
            choice = scanner.nextInt();
        } catch (Exception e) {
            // Consume the invalid input
            scanner.nextLine();
        }
        return choice;
    }
}
