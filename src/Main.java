import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.Queue;


// ================= MAIN =================
public class Main {

    static LinkedList<BankAccount> accounts = new LinkedList<>();
    static Stack<String> history = new Stack<>();
    static Queue<String> billQueue = new LinkedList<>();
    static Queue<BankAccount> accountRequests = new LinkedList<>();

    static Scanner sc = new Scanner(System.in);
    static int accountCounter = 1;

    public static void main(String[] args) {

        // ===== Task 6 (ARRAY) =====
        BankAccount[] arr = new BankAccount[3];
        arr[0] = new BankAccount(1, "Ali", 150000);
        arr[1] = new BankAccount(2, "Sara", 220000);
        arr[2] = new BankAccount(3, "John", 100000);

        System.out.println("=== ARRAY ACCOUNTS ===");
        for (BankAccount a : arr) {
            System.out.println(a);
        }

        // ===== MAIN MENU =====
        while (true) {
            System.out.println("\n1 – Enter Bank");
            System.out.println("2 – Enter ATM");
            System.out.println("3 – Admin Area");
            System.out.println("4 – Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    bankMenu();
                    break;
                case 2:
                    atmMenu();
                    break;
                case 3:
                    adminMenu();
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ================= BANK MENU =================
    static void bankMenu() {
        while (true) {
            System.out.println("\n--- BANK MENU ---");
            System.out.println("1. Request account");
            System.out.println("2. Show accounts");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Back");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String name = sc.next();
                    accountRequests.add(new BankAccount(accountCounter++, name, 0));
                    System.out.println("Request submitted!");
                    break;

                case 2:
                    showAccounts();
                    break;

                case 3:
                    deposit();
                    break;

                case 4:
                    withdraw();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ================= ATM MENU =================
    static void atmMenu() {
        System.out.print("Enter username: ");
        String name = sc.next();

        BankAccount acc = findAccount(name);
        if (acc == null) {
            System.out.println("Account not found!");
            return;
        }

        while (true) {
            System.out.println("\n--- ATM ---");
            System.out.println("1. Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Back");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Balance: " + acc.balance);
                    break;

                case 2:
                    System.out.print("Withdraw amount: ");
                    double amount = sc.nextDouble();

                    if (acc.balance >= amount) {
                        acc.balance -= amount;
                        history.push("Withdraw " + amount + " from " + name);
                        System.out.println("Success!");
                    } else {
                        System.out.println("Not enough money!");
                    }
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ================= ADMIN MENU =================
    static void adminMenu() {
        while (true) {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. Process account request");
            System.out.println("2. Show requests");
            System.out.println("3. Add bill");
            System.out.println("4. Process bill");
            System.out.println("5. Show bills");
            System.out.println("6. Transaction history");
            System.out.println("7. Undo last transaction");
            System.out.println("8. Back");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    processAccounts();
                    break;

                case 2:
                    System.out.println(accountRequests);
                    break;

                case 3:
                    System.out.print("Enter bill name: ");
                    String bill = sc.next();
                    billQueue.add(bill);
                    System.out.println("Added!");
                    break;

                case 4:
                    if (!billQueue.isEmpty())
                        System.out.println("Processing: " + billQueue.poll());
                    else
                        System.out.println("No bills!");
                    break;

                case 5:
                    System.out.println(billQueue);
                    break;

                case 6:
                    showHistory();
                    break;

                case 7:
                    undoTransaction();
                    break;

                case 8:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ================= FUNCTIONS =================

    static void showAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts!");
            return;
        }

        System.out.println("\nAccounts List:");
        for (BankAccount acc : accounts) {
            System.out.println(acc);
        }
    }

    static BankAccount findAccount(String username) {
        for (BankAccount acc : accounts) {
            if (acc.username.equals(username)) return acc;
        }
        return null;
    }

    static void deposit() {
        System.out.print("Enter username: ");
        String name = sc.next();

        BankAccount acc = findAccount(name);
        if (acc == null) {
            System.out.println("Not found!");
            return;
        }

        System.out.print("Deposit amount: ");
        double amount = sc.nextDouble();

        acc.balance += amount;
        history.push("Deposit " + amount + " to " + name);

        System.out.println("New balance: " + acc.balance);
    }

    static void withdraw() {
        System.out.print("Enter username: ");
        String name = sc.next();

        BankAccount acc = findAccount(name);
        if (acc == null) {
            System.out.println("Not found!");
            return;
        }

        System.out.print("Withdraw amount: ");
        double amount = sc.nextDouble();

        if (acc.balance >= amount) {
            acc.balance -= amount;
            history.push("Withdraw " + amount + " from " + name);
            System.out.println("New balance: " + acc.balance);
        } else {
            System.out.println("Not enough money!");
        }
    }

    static void processAccounts() {
        if (accountRequests.isEmpty()) {
            System.out.println("No requests!");
            return;
        }

        BankAccount acc = accountRequests.poll();
        accounts.add(acc);

        System.out.println("Account created: " + acc.username);
    }

    static void showHistory() {
        if (history.isEmpty()) {
            System.out.println("No transactions!");
            return;
        }

        System.out.println("Last transaction: " + history.peek());
        System.out.println("All history: " + history);
    }

    static void undoTransaction() {
        if (history.isEmpty()) {
            System.out.println("Nothing to undo!");
            return;
        }

        System.out.println("Removed: " + history.pop());
    }
}