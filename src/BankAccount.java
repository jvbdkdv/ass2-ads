public class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    // Методы для изменения состояния (бизнес-логика)
    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    // Геттеры
    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }

    @Override
    public String toString() {
        return "ID: " + accountNumber + " | Owner: " + accountHolder + " | Balance: $" + balance;
    }
}
