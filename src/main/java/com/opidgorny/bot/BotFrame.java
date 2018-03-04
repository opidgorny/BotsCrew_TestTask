package com.opidgorny.bot;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotFrame extends JFrame implements ActionListener {

    private final String TITLE_OF_PROGRAM = "BotsCrew TestTask";
    private final int START_LOCATION = 200;
    private final int WINDOW_WIDTH = 350;
    private final int WINDOW_HEIGHT = 450;

    private final JTextArea dialogue = new JTextArea(); //dialogue area
    private final JTextField message = new JTextField(); // entering msgs
    private final JTextField fieldNickname = new JTextField(); // entering nickname
    private final JLabel fieldNicknameComponent = new JLabel();
    private SimpleBot bot;

    BotFrame() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(START_LOCATION, START_LOCATION, WINDOW_WIDTH, WINDOW_HEIGHT);
        //area for dialogue
        dialogue.setEditable(false);
        dialogue.setLineWrap(true);
        JScrollPane scrollBar = new JScrollPane(dialogue);
        dialogue.append(onStartUp() + "\n");

        //panel for checkbox, message field, button
        JPanel panelBot = new JPanel();
        panelBot.setLayout(new BoxLayout(panelBot, BoxLayout.X_AXIS));
        message.addActionListener(this);
        JButton enter = new JButton("Enter");
        enter.addActionListener(this);

        fieldNicknameComponent.setText("Insert your nickname: ");

        //adding all elements to the window
        panelBot.add(message);
        panelBot.add(enter);

        JPanel panelTop = new JPanel(new BorderLayout());
        panelTop.add(fieldNicknameComponent, BorderLayout.WEST);
        panelTop.add(fieldNickname, BorderLayout.CENTER);

        add(BorderLayout.CENTER ,scrollBar);
        add(BorderLayout.NORTH, panelTop);
        add(BorderLayout.SOUTH, panelBot);

        setVisible(true);

        bot = new SimpleBot();
    }

    public void actionPerformed(ActionEvent e) {
        if(message.getText().trim().length() > 0) {
            dialogue.append(fieldNickname.getText() + ": " + message.getText() + "\n");
            dialogue.append("Albert Einstein" + ": " +
                bot.sayInReturn(message.getText()) + "\n");
        }
        message.setText("");
        message.requestFocusInWindow();
    }

    public String onStartUp() {
        return "Hello, I am simple ChatBot" + "\n" +
                "created to test my Master skills" + "\n" +
                "YOU can use following commands to get" + "\n" +
                "information about our university: " + "\n" +
                "1. Who is head of department {department_name}" + "\n" +
                "2. Show {department_name} statistic" + "\n" +
                "3. Show the average salary for department {department_name}" + "\n" +
                "4. Show count of employee for {department_name}" + "\n" +
                "5. Global search by {template}" + "\n";
    }
}
