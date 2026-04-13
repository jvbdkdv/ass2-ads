import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Инициализируем ТВОИ структуры из лекции
        MyLinkedList<BankAccount> allAccounts = new MyLinkedList<>();
        MyQueue<String> transactionQueue = new MyQueue<>();
        MyStack<String> historyStack = new MyStack<>();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- BANK SYSTEM MENU ---");
            System.out.println("1. Create Account (LinkedList add)");
            System.out.println("2. Add Transaction to Queue (Enqueue)");
            System.out.println("3. Process Next Transaction (Dequeue)");
            System.out.println("4. Undo/View Last Action (Stack Pop)");
            System.out.println("5. Show All Accounts");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Очистка буфера

            if (choice == 1) {
                System.out.print("Number: "); String num = sc.nextLine();
                System.out.print("Name: "); String name = sc.nextLine();
                System.out.print("Balance: "); double bal = sc.nextDouble();

                BankAccount acc = new BankAccount(num, name, bal);
                allAccounts.add(acc); // Используем LinkedList
                historyStack.push("Created account " + num);

            } else if (choice == 2) {
                System.out.print("Transaction details: ");
                String task = sc.nextLine();
                transactionQueue.enqueue(task); // Используем Queue
                historyStack.push("Queued: " + task);

            } else if (choice == 3) {
                String task = transactionQueue.dequeue();
                if (task != null) {
                    System.out.println("Done: " + task);
                    historyStack.push("Processed: " + task);
                } else {
                    System.out.println("No tasks in queue.");
                }

            } else if (choice == 4) {
                String last = historyStack.pop(); // Используем Stack
                System.out.println(last != null ? "Last action: " + last : "History empty");

            } else if (choice == 5) {
                for (int i = 0; i < allAccounts.getSize(); i++) {
                    System.out.println(allAccounts.get(i));
                }

            } else if (choice == 0) break;
        }
    }
}