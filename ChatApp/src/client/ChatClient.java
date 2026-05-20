package client;
import javax.swing.*;
import common.Message;

import java.io.*;
import java.net.Socket;

public class ChatClient {

    private Socket socket;

    private ObjectOutputStream output;
    private ObjectInputStream input;

    public ChatClient(
            String host,
            int port,
            String username,
            String password
    ) {

        try {

            socket = new Socket(host, port);

            output =
                    new ObjectOutputStream(
                            socket.getOutputStream()
                    );

            output.flush();

            input =
                    new ObjectInputStream(
                            socket.getInputStream()
                    );

            output.writeUTF(username);
            output.writeUTF(password);
            output.flush();
            System.out.println("Waiting for server response...");

            boolean authenticated =
                    input.readBoolean();
            System.out.println("Received auth result");

            if (authenticated) {

                SwingUtilities.invokeLater(() -> {

                    JOptionPane.showMessageDialog(
                            null,
                            "Login Successful"
                    );

                    SwingUtilities.invokeLater(() -> {
                        new ChatGUI(this, username);
                    });
                });

                listenForMessages();

            } else {

                SwingUtilities.invokeLater(() -> {

                    JOptionPane.showMessageDialog(
                            null,
                            "Invalid Username or Password"
                    );
                });

                socket.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Message msg) {

        try {

            output.writeObject(msg);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listenForMessages() {

        new Thread(() -> {

            try {

                while (true) {

                    Message msg =
                            (Message) input.readObject();

                    ChatGUI.chatArea.append(
                            msg.getContent() + "\n"
                    );
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();
    }
}