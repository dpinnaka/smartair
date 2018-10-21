package com.buzzcoder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.ConnectException;
import java.sql.*;

public class Movies {
    JFrame f;
    JCheckBox[] cb;
    JButton submit;
    private String dbUrl, dbUsername, dbPassword;
    JScrollPane sp;
    JTable table;
    //int y;
    JLabel l;

    public Movies() {
        f = new JFrame("Movies");
        //y = 0;
        String[] columns = {"Name", "Genre", "Length", "Language"};
        cb = new JCheckBox[5];
        int x = 0;
        for (int i = 0; i < 5; i++) {
            cb[i] = new JCheckBox();
            cb[i].setBounds(x + 50, 25, 100, 50);
            x += 100;
            f.add(cb[i]);
        }
        submit = new JButton("Submit");
        submit.setBounds(250, 75, 100,50);
        cb[0].setText("Children");
        cb[1].setText("Sci-Fi");
        cb[2].setText("Drama");
        cb[3].setText("Romance");
        cb[4].setText("Horror");

        f.setSize(600,450);
        f.add(submit);

        f.setLayout(null);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);
        dbUsername = "root";
        dbPassword = "password";
        dbUrl = "jdbc:mysql://localhost/hackGT?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        Statement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            statement = connection.createStatement();


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Statement finalStatement = statement;
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[][] data = new String[25][4];
                int y = 0;
                table = new JTable(data, columns);
                table.setFillsViewportHeight(true);
                table.setBounds(20,150,500,250);
                sp = new JScrollPane(table);
                f.add(sp);
                sp.setBounds(20,150, 500,250);
                //sp.setVisible(true);
                for (int i = 0; i < 4; i++) {
                    if (cb[i].isSelected()) {
                        String sqlString = "select * from movies where genre = " + "\"" + cb[i].getText() + "\"";
                        ResultSet rs = null;
                        try {
                            rs = finalStatement.executeQuery(sqlString);
                            while (rs.next()) {
//                                System.out.println(rs.getString("moviename") + " " + rs.getString("genre") + " "
//                                        + rs.getInt("length") + " " + rs.getString("mlanguage"));
                                data[y][0] = rs.getString("moviename");
                                data[y][1] = rs.getString("genre");
                                data[y][2] = rs.getString("length");
                                data[y][3] = rs.getString("mlanguage");
                                y++;
                            }

                        } catch (SQLException t) {
                            t.printStackTrace();
                        }
                    }
                }
            }
        });
        l = new JLabel();
        l.setBounds(0,0,10,10);
        f.add(l);


    }
    public void connectDatabase() {

    }
    //public static void main(String[] args) {
    //    com.buzzcoder.Movies x = new com.buzzcoder.Movies();
    //    x.connectDatabase();
    //}
}
