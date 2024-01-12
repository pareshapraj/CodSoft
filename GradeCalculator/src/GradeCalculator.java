import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GradeCalculator extends JFrame implements ActionListener {
    private JLabel[] labels;
    private JTextField[] textFields;
    private JButton calculateButton;

    public GradeCalculator() {
        labels = new JLabel[5];
        textFields = new JTextField[5];

        String[] subjects = {"Subject 1", "Subject 2", "Subject 3", "Subject 4", "Subject 5"};

        for (int i = 0; i < 5; i++) {
            labels[i] = new JLabel(subjects[i]);
            textFields[i] = new JTextField();
            labels[i].setBounds(50, 50 + i * 50, 100, 30);
            textFields[i].setBounds(160, 50 + i * 50, 100, 30);
            labels[i].setForeground(new Color(53, 168, 125)); // Set text color
            textFields[i].setFont(new Font("Arial", Font.PLAIN, 14)); // Set font
            add(labels[i]);
            add(textFields[i]);
        }

        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(100, 300, 100, 30);
        calculateButton.addActionListener(this);
        calculateButton.setBackground(new Color(255, 138, 0)); // Set button color
        calculateButton.setForeground(Color.WHITE); // Set text color
        calculateButton.setFont(new Font("Arial", Font.BOLD, 14)); // Set font
        add(calculateButton);

        setTitle("Student Grade Calculator");
        setSize(300, 400);
        getContentPane().setBackground(Color.WHITE); // Set background color
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            int totalMarks = 0;
            int numSubjects = 0;

            for (int i = 0; i < 5; i++) {
                String marksText = textFields[i].getText();
                if (!marksText.isEmpty()) {
                    int marks = Integer.parseInt(marksText);
                    totalMarks += marks;
                    numSubjects++;
                }
            }

            double averagePercentage = (double) totalMarks / (numSubjects * 100) * 100;
            String grade = calculateGrade(averagePercentage);

            JOptionPane.showMessageDialog(this, "Total Marks: " + totalMarks +
                    "\nAverage Percentage: " + averagePercentage + "%" +
                    "\nGrade: " + grade);
        }
    }

    private String calculateGrade(double percentage) {
        if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B";
        } else if (percentage >= 60) {
            return "C";
        } else if (percentage >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GradeCalculator();
            }
        });
    }
}
