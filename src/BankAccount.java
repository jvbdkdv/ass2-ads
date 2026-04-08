// ================= CLASS =================
    public class BankAccount {
        int accountNumber;
        String username;
        double balance;

        public BankAccount(int accountNumber, String username, double balance) {
            this.accountNumber = accountNumber;
            this.username = username;
            this.balance = balance;
        }

        @Override
        public String toString() {
            return accountNumber + ". " + username + " – Balance: " + balance;
        }
    }


