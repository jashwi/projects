import java.util.ArrayList;
import javax.swing.*;

class BankAccount {
    private double balance;
    private String accountHolderName;
    private ArrayList<String> transactionHistory;

    public BankAccount(String accountHolderName, double initialBalance) {
        this.accountHolderName = accountHolderName;
        balance = initialBalance;
        transactionHistory = new ArrayList<>();
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposit: Rs. " + amount);
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrawal: Rs. " + amount);
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<String> getTransactionHistory() {
        return transactionHistory;
    }
}

class ATM {
    private BankAccount account;
    private String pin;
    private int attemptsLeft;

    public ATM(BankAccount account, String pin) {
        this.account = account;
        this.pin = pin;
        attemptsLeft = 5;
    }

    public void displayMenu() {
        JOptionPane.showMessageDialog(null, "Welcome, " + account.getAccountHolderName() + "!\n"
                + "1. Check Balance\n"
                + "2. Deposit\n"
                + "3. Withdraw\n"
                + "4. Transaction History\n"
                + "5. Exit");
    }

    public void processTransaction() {
        int attemptCount = 0;
        while (attemptCount < attemptsLeft) {
            String enteredPin = JOptionPane.showInputDialog("Enter your PIN (Attempts left: " + (attemptsLeft - attemptCount) + "):");
            if (enteredPin.equals(pin)) {
                int choice;
                double amount;
                while (true) {
                    displayMenu();
                    choice = Integer.parseInt(JOptionPane.showInputDialog("Enter your choice:"));
                    switch (choice) {
                        case 1:
                            JOptionPane.showMessageDialog(null, "Your balance is: Rs. " + account.getBalance());
                            break;
                        case 2:
                            amount = Double.parseDouble(JOptionPane.showInputDialog("Enter the deposit amount: Rs. "));
                            if (amount > 0) {
                                account.deposit(amount);
                                JOptionPane.showMessageDialog(null, "Deposit successful.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Invalid deposit amount.");
                            }
                            break;
                        case 3:
                            amount = Double.parseDouble(JOptionPane.showInputDialog("Enter the withdrawal amount: Rs. "));
                            if (amount > 0 && account.withdraw(amount)) {
                                JOptionPane.showMessageDialog(null, "Withdrawal successful.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Invalid withdrawal amount or insufficient balance.");
                            }
                            break;
                        case 4:
                            ArrayList<String> transactions = account.getTransactionHistory();
                            StringBuilder transactionString = new StringBuilder("Transaction History:\n");
                            for (String transaction : transactions) {
                                transactionString.append(transaction).append("\n");
                            }
                            JOptionPane.showMessageDialog(null, transactionString.toString());
                            break;
                        case 5:
                            JOptionPane.showMessageDialog(null, "Thank you for using the ATM.");
                            return;
                        default:
                            JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid PIN. Please try again.");
                attemptCount++;
            }
        }
        JOptionPane.showMessageDialog(null, "Maximum attempts reached. Please try again later.");
    }
}

public class BankingSystem {
    public static void main(String[] args) {
        String accountHolderName = JOptionPane.showInputDialog("Enter your name:");
        String pin = JOptionPane.showInputDialog("Set your PIN:");
        BankAccount userAccount = new BankAccount(accountHolderName, 1000.0);
        ATM atm = new ATM(userAccount, pin);
        atm.processTransaction();
    }
}
