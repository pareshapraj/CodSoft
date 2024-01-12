import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false; // Insufficient funds
        }
    }
}

class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public boolean withdraw(double amount) {
        return account.withdraw(amount);
    }

    public void deposit(double amount) {
        account.deposit(amount);
    }

    public double checkBalance() {
        return account.getBalance();
    }
}

public class ATMGUI {
    private ATM atm;

    public ATMGUI(ATM atm) {
        this.atm = atm;

        JFrame frame = new JFrame("ATM Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.getContentPane().setBackground(new Color(1, 13, 24)); // ATM Blue background

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton balanceButton = new JButton("Check Balance");

        JLabel resultLabel = new JLabel("");
        resultLabel.setForeground(Color.BLACK); // Set text color

        withdrawButton.setBackground(new Color(255, 0, 51)); // Red withdraw button
        depositButton.setBackground(new Color(51, 204, 51)); // Green deposit button
        balanceButton.setBackground(new Color(255, 204, 0)); // Yellow balance button

        withdrawButton.setForeground(Color.BLACK); // Set text color
        depositButton.setForeground(Color.BLACK); // Set text color
        balanceButton.setForeground(Color.BLACK); // Set text color

        panel.add(withdrawButton);
        panel.add(depositButton);
        panel.add(balanceButton);
        panel.add(resultLabel);

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String amountString = JOptionPane.showInputDialog("Enter amount to withdraw:");
                try {
                    double amount = Double.parseDouble(amountString);
                    boolean success = atm.withdraw(amount);
                    if (success) {
                        resultLabel.setText("Withdrawal successful. New balance: $" + atm.checkBalance());
                    } else {
                        resultLabel.setText("Insufficient funds. Current balance: $" + atm.checkBalance());
                    }
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Invalid input. Please enter a valid number.");
                }
            }
        });

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String amountString = JOptionPane.showInputDialog("Enter amount to deposit:");
                try {
                    double amount = Double.parseDouble(amountString);
                    atm.deposit(amount);
                    resultLabel.setText("Deposit successful. New balance: $" + atm.checkBalance());
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Invalid input. Please enter a valid number.");
                }
            }
        });

        balanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resultLabel.setText("Current balance: $" + atm.checkBalance());
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000); // Initial balance of 1000
        ATM atm = new ATM(account);
        SwingUtilities.invokeLater(() -> new ATMGUI(atm));
    }
}
