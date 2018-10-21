package com.buzzcoder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class Chat {
    JFrame f;
    JTextArea inputArea, inputArea2;
    JTextArea output;
    JButton submit, chatBoi;
    public Chat() {
        apitest a = new apitest();
        f = new JFrame();
        inputArea = new JTextArea();
        inputArea2 = new JTextArea();
        output = new JTextArea("Welcome! I'm ChattyBoi");
        output.setEnabled(false);
        inputArea2.setEditable(false);
        chatBoi = new JButton("ChatBoi");
        submit = new JButton("Say!");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setLayout(null);

        f.setSize(700,500);
        inputArea2.setBounds(20,20, 300, 100);
        output.setBounds(140,150,550,100);
        inputArea.setBounds(20, 280, 300, 100);
        submit.setBounds(350,280,100,100);
        chatBoi.setBounds(20,140,100,100);
        f.add(inputArea);
        f.add(submit);
        f.add(inputArea2);
        f.add(output);
        f.add(chatBoi);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputArea2.setText(inputArea.getText());
                String userSays = inputArea.getText();
                userSays = userSays.toLowerCase();
                try {
                    output.setText(a.sendToBot(userSays));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                if (a.intent.contains("water")) {
                    JOptionPane.showMessageDialog(f, "Flight attendants have been notified of your request");
                } else if (a.intent.contains("snack")) {
                    output.setText("Food will be served in 20 minutes!");
                } else if (a.intent.contains("movies")) {
                    output.setText("Sure! Enjoy your show...");
                    //new Movies();
                    com.buzzcoder.Movies x = new com.buzzcoder.Movies();
                    x.connectDatabase();
                    f.setVisible(true);
                } else if (a.intent.contains("flight")) {
                    if (Desktop.isDesktopSupported()) {
                        try {
                            Desktop.getDesktop().browse(new URI("https://www.google.com/flights"));
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (URISyntaxException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });
    }
}
