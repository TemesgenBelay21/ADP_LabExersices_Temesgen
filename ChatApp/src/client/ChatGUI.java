package client;

import common.Message;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ChatGUI extends JFrame {

    public static JTextArea chatArea =
            new JTextArea();

    private JTextField messageField =
            new JTextField(20);

    public ChatGUI(ChatClient client, String username) {

        setTitle("Chat Room");

        setSize(500, 400);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        chatArea.setEditable(false);

        JPanel panel = new JPanel();

        JButton sendButton =
                new JButton("Send");

        JButton fileButton =
                new JButton("Send File");

        JScrollPane scrollPane =
                new JScrollPane(chatArea);

        add(scrollPane, BorderLayout.CENTER);

        panel.add(messageField);
        panel.add(sendButton);
        panel.add(fileButton);

        add(panel, BorderLayout.SOUTH);

        sendButton.addActionListener(e -> {

            String text =
                    messageField.getText();

            client.sendMessage(
                    new Message(
                            username,
                            text,
                            "text"
                    )
            );

            messageField.setText("");
        });

        fileButton.addActionListener(e -> {

            JFileChooser chooser =
                    new JFileChooser();

            int option =
                    chooser.showOpenDialog(this);

            if (option ==
                    JFileChooser.APPROVE_OPTION) {

                File file =
                        chooser.getSelectedFile();

                client.sendMessage(
                        new Message(
                                username,
                                file.getName(),
                                "file"
                        )
                );
            }
        });

        setVisible(true);
    }
}