import java.awt.*;
import javax.swing.*;

public class Jcash extends JFrame {

    String accountHolderName;
    int accountNumber;
    double balance;

    JLabel balanceLabel;
    JTextField amountField;
    JButton depositButton, withdrawButton;

    public Jcash(String name, int accNum, double initialBal) {
        this.accountHolderName = name;
        this.accountNumber = accNum;
        this.balance = initialBal;

        setTitle("Jcash");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 1, 12, 12));

        add(new JLabel("Account Holder: " + accountHolderName, SwingConstants.CENTER));
        add(new JLabel("Account Number: " + accountNumber, SwingConstants.CENTER));

        balanceLabel = new JLabel("Current Balance: ₱" + String.format("%.2f", balance), SwingConstants.CENTER);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        balanceLabel.setForeground(Color.BLACK);
        add(balanceLabel);

        JPanel amountPanel = new JPanel(new FlowLayout());
        amountPanel.add(new JLabel("Enter Amount: ₱"));
        amountField = new JTextField(18);
        amountPanel.add(amountField);
        add(amountPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");

        depositButton.setBackground(Color.green);
        depositButton.setForeground(Color.WHITE);
        withdrawButton.setBackground(Color.red);
        withdrawButton.setForeground(Color.WHITE);

        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);

        add(buttonPanel);

        depositButton.addActionListener(e -> performDeposit());
        withdrawButton.addActionListener(e -> performWithdraw());

        setVisible(true);
    }

    public void updateBalanceLabel() {
        balanceLabel.setText("Current Balance: ₱" + String.format("%.2f", balance));
    }

    public void performDeposit() {
        try {
            double amount = Double.parseDouble(amountField.getText().trim());
            if (amount > 0) {
                balance += amount;
                updateBalanceLabel();
                JOptionPane.showMessageDialog(this, "Deposit successful!\nNew Balance: ₱" + String.format("%.2f", balance));
                amountField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Amount must be greater than 0.");
            }
        } catch (NumberFormatException error) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Input Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void performWithdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText().trim());
            if (amount > 0) {
                if (balance >= amount) {
                    balance -= amount;
                    updateBalanceLabel();
                    JOptionPane.showMessageDialog(this, "Withdrawal successful!\nNew Balance: ₱" + String.format("%.2f", balance));
                    amountField.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient funds!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Amount must be greater than 0.");
            }
        } catch (NumberFormatException error) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Input Error", JOptionPane.WARNING_MESSAGE);
        }
    }


    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog(null, "Enter Account Holder Name:");
        if (name == null || name.trim().isEmpty()) name = "Guest User";

        String accStr = JOptionPane.showInputDialog(null, "Enter Account Number:");
        int accNum = 123456;
        try {
            if (accStr != null) accNum = Integer.parseInt(accStr);
        } catch (Exception ignored) {
        }

        String balStr = JOptionPane.showInputDialog(null, "Enter Initial Balance:");
        double initialBal = 0.0;
        try {
            if (balStr != null) initialBal = Double.parseDouble(balStr);
        } catch (Exception ignored) {
        }

        final String finalName = name;
        final int finalAccNum = accNum;
        final double finalInitialBal = initialBal;

        SwingUtilities.invokeLater(() -> new Jcash(finalName, finalAccNum, finalInitialBal));
    }
}