package com.buzzcoder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class FrequentView {
    JFrame f;
    JLabel email, password;
    JTextField emailF, passwordF;
    JButton submit;
    public FrequentView() {

        email = new JLabel("Email ID:");
        password = new JLabel("Password:");
        emailF = new JTextField();
        passwordF = new JTextField();

        submit = new JButton("Submit");

        email.setBounds(20,20, 100, 30);
        emailF.setBounds(20, 60,100,30);
        password.setBounds(20, 100, 100, 30);
        passwordF.setBounds(20,140,100,30);
        submit.setBounds(100, 170, 100, 50);

        f = new JFrame("View Points");
        f.setVisible(true);
        f.setLayout(null);
        f.setSize(300,300);

        f.add(email);
        f.add(emailF);
        f.add(password);
        f.add(passwordF);
        f.add(submit);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = emailF.getText();
                String password = passwordF.getText();
                String query = "select * from frequentFlyer where emailID = " + "\"" + id + "\""
                        + " and " + "userpassword = " + "\"" + password + "\"";
                Statement statement = null;
                String dbUrl = "jdbc:mysql://localhost/hackGT?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                String dbUsername = "root";
                String dbPassword = "password";
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
                    statement = connection.createStatement();
                    ResultSet rs = null;
                    rs = statement.executeQuery(query);
                    while (rs.next()) {
                        String checkID = rs.getString("emailID");
                        String checkPass = rs.getString("userpassword");
                        if (checkID.equals(id) && checkPass.equals(password)) {
                            JOptionPane.showMessageDialog(f,"You have 400 points!");
                        } else {
                            JOptionPane.showMessageDialog(f,"No user found!");
                        }

                    }

                } catch (SQLException k) {
                    k.printStackTrace();
                } catch (ClassNotFoundException k) {
                    k.printStackTrace();
                }
            }
        });

    }
}
