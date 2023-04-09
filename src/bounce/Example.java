package bounce;

import javax.swing.*;
import java.awt.event.*;

public class Example extends JFrame implements ActionListener {
    JTextField textField;
    JButton button;
    JLabel label;

    public Example() {
        setLayout(null);

        textField = new JTextField(10);
        textField.setBounds(10, 10, 100, 20);
        add(textField);

        button = new JButton("‘ã“ü");
        button.setBounds(10, 40, 100, 20);
        add(button);
        button.addActionListener(this);

        label = new JLabel("");
        label.setBounds(10, 70, 200, 20);
        add(label);

        setSize(300, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            String text = textField.getText();
            label.setText(text);
        }
    }

    public static void main(String[] args) {
        Example example = new Example();
    }
}
