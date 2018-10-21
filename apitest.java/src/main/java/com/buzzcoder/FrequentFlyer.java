package com.buzzcoder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Random;

public class FrequentFlyer extends  MainMenu {
    JFrame f;
    JLabel name, email, password, age, passportNum, dateOfBirth;
    JLabel nameError, emailError, ageError, passportError, passwordError;
    JTextField nameField, emailField, ageField, passportNumField;
    JTextField passwordField;
    JButton submit;

    public FrequentFlyer() {
        f = new JFrame("Frequent Flyer");
        name = new JLabel("Name:");
        email = new JLabel("E-Mail:");
        age = new JLabel("Age:");
        passportNum = new JLabel("Passport Number:");
        password = new JLabel("Password:");

        nameField = new JTextField();
        emailField = new JTextField();
        ageField = new JTextField();
        passportNumField = new JTextField();

        passwordField = new JTextField();

        nameError = new JLabel();
        emailError = new JLabel();
        passportError = new JLabel();
        ageError = new JLabel();
        passwordError = new JLabel();

        submit = new JButton("Submit");

        name.setBounds(20, 20, 50, 30);
        nameField.setBounds(20, 50, 150, 30);
        nameError.setBounds(180, 50, 300, 30);
        email.setBounds(20, 100, 150, 30);
        emailField.setBounds(20, 130, 200, 30);
        emailError.setBounds(230, 130, 300, 30);
        age.setBounds(20, 180, 50, 30);
        ageField.setBounds(20, 210, 50, 30);
        ageError.setBounds(80, 210, 200, 30);
        passportNum.setBounds(20, 260, 150, 30);
        passportNumField.setBounds(20, 290, 150, 30);
        passportError.setBounds(180, 290, 300, 30);
        password.setBounds(20, 350, 150, 30);
        passwordField.setBounds(20, 380, 150, 30);
        passportError.setBounds(180, 470, 300, 30);
        submit.setBounds(200, 450, 100, 50);

        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.setSize(550, 600);

        f.add(name);
        f.add(email);
        f.add(age);
        f.add(passportNum);
        f.add(nameField);
        f.add(emailField);
        f.add(ageField);
        f.add(passportNumField);
        f.add(password);
        f.add(passwordField);
        f.add(nameError);
        f.add(emailError);
        f.add(ageError);
        f.add(passportError);
        f.add(passwordError);
        f.add(submit);

        emailField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = emailField.getText();
                if (!value.contains("@")) {
                    emailError.setText("Invalid Email - must contain @");
                }
                if (!value.contains(".com") || !value.contains(".org") || !value.contains(".in") || !value.contains(".co.in")) {
                    emailError.setText("Invalid Email - must contain .com/.org/.in/...");
                }
            }
        });
        ageField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = ageField.getText();
                int val = Integer.parseInt(value);
                if (val < 12 || val > 120) {
                    ageError.setText("Invalid age");
                }
            }
        });

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Statement statement = null;
                String dbUrl = "jdbc:mysql://localhost/hackGT?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                String dbUsername = "root";
                String dbPassword = "password";
                Random rand = new Random();
                long x = rand.nextInt(10000000 + 90000000);
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
                    statement = connection.createStatement();
                    String name = "\"" + nameField.getText() + "\"";
                    String email = "\"" + emailField.getText() + "\"";
                    String passportNum = "\"" + passportNumField.getText() + "\"";
                    String password = "\"" + passwordField.getText() + "\"";
                    //String age = "\"" + ageField.getText() + "\"";
                    String sqlString = "insert into frequentFlyer values ( " + name + ", " +
                            email + ", " + Integer.parseInt(ageField.getText()) + ", " + passportNum + ", "
                            + password + ", " + "\"" + x + "\"" + ");";
                    System.out.println(sqlString);
                    //ResultSet rs = null;
                    statement.execute(sqlString);
                    JOptionPane.showMessageDialog(f, "Frequent Flyer added!");
                    f.setVisible(false);
                    f.setVisible(false);
                    returnF().setVisible(true);
                } catch (SQLException f) {
                    f.printStackTrace();
                    JOptionPane.showMessageDialog(submit, "Unable to add user. Please try again");
                } catch (ClassNotFoundException g) {
                    g.printStackTrace();
                }
            }
        });

    }
}
