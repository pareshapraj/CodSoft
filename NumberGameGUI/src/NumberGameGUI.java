import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGameGUI {
    private JFrame frame;
    private JLabel label;
    private JTextField textField;
    private JButton submitButton;
    private int targetNumber;
    private int attempts;
    private int maxAttempts;
    private int score;

    public NumberGameGUI() {
        frame = new JFrame("Number Game");
        frame.getContentPane().setBackground(new Color(53, 168, 125)); // Set background color

        label = new JLabel("Enter your guess:");
        label.setForeground(Color.WHITE); // Set text color
        label.setFont(new Font("Arial", Font.BOLD, 16)); // Set font

        textField = new JTextField(10);
        textField.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font

        submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(255, 138, 0)); // Set button color
        submitButton.setForeground(Color.WHITE); // Set text color
        submitButton.setFont(new Font("Arial", Font.BOLD, 14)); // Set font

        targetNumber = generateRandomNumber();
        maxAttempts = 5;
        attempts = 0;
        score = 0;

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        frame.setLayout(new GridLayout(3, 1, 10, 10)); // Use GridLayout
        frame.add(label);
        frame.add(textField);
        frame.add(submitButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }

    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    private void checkGuess() {
        int userGuess = Integer.parseInt(textField.getText());
        attempts++;

        if (userGuess == targetNumber) {
            JOptionPane.showMessageDialog(frame, "Congratulations! You guessed the correct number.");
            score++;
        } else if (attempts < maxAttempts) {
            String message = (userGuess < targetNumber) ? "Too low! Try a higher number." : "Too high! Try a lower number.";
            JOptionPane.showMessageDialog(frame, message);
        } else {
            JOptionPane.showMessageDialog(frame, "Sorry! You've used all your attempts. The correct number was " + targetNumber);
        }

        if (attempts < maxAttempts) {
            targetNumber = generateRandomNumber();
            textField.setText("");
        } else {
            showScore();
        }
    }

    private void showScore() {
        JOptionPane.showMessageDialog(frame, "Your final score is: " + score, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        frame.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NumberGameGUI();
            }
        });
    }
}
