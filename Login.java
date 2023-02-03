package graphic;

import account.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;


public class Login extends JFrame implements ActionListener {
    public Container container;
    public JLabel mailLabel = new JLabel("MAIL");
    public JLabel passwordLabel = new JLabel("PASSWORD");
    public JTextField mailTextField = new JTextField();
    public JPasswordField passwordField = new JPasswordField();
    public JButton loginButton = new JButton("LOGIN");
    public JButton resetButton = new JButton("RESET");
    public JButton viewCredentials = new JButton("VIEW CREDENTIALS");
    public JCheckBox showPassword = new JCheckBox("SHOW PASSWORD");
    public Account selectedAccount;

    public Login(Account account) {
        container = getContentPane();

        container.setLayout(null);
        container.setBackground(new Color(246, 255, 217, 255));

        mailLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        mailTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);
        viewCredentials.setBounds(100, 100, 160, 30);

        container.add(mailLabel);
        container.add(passwordLabel);
        container.add(mailTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
        container.add(viewCredentials);

        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
        viewCredentials.addActionListener(e -> {
            if(e.getSource() == viewCredentials){
                printCredentials();
            }
        });

        selectedAccount = account;
    }

    public void printCredentials(){
        System.out.println(selectedAccount.player.getCredentials().getEmail());
        System.out.println(selectedAccount.player.getCredentials().getPassword());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        printCredentials();
        if (e.getSource() == loginButton) {
            String mailText;
            String pwdText;
            mailText = mailTextField.getText();
            pwdText = passwordField.getText();
            if (mailText.equalsIgnoreCase(selectedAccount.player.getCredentials().getEmail()) && pwdText.equalsIgnoreCase(selectedAccount.player.getCredentials().getPassword())) {
                JOptionPane.showMessageDialog(this, "Login Successful! Please wait!");
                AddToJList demo = new AddToJList(selectedAccount);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password. Try again!");
            }
        }

        if (e.getSource() == resetButton) {
            mailTextField.setText("");
            passwordField.setText("");
        }

        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        }
    }
}
