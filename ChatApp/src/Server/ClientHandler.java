package Server;

import common.Message;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {

    private Socket socket;

    private ObjectInputStream input;
    private ObjectOutputStream output;

    private String username;

    public ClientHandler(Socket socket) {

        this.socket = socket;

        try {

            output =
                    new ObjectOutputStream(socket.getOutputStream());

            output.flush();

            input =
                    new ObjectInputStream(socket.getInputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try {

            DatabaseManager db =
                    new DatabaseManager();

            username = input.readUTF();
            String password = input.readUTF();

            boolean valid =
                    db.authenticate(username, password);
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            System.out.println("Valid user: " + valid);
            output.writeBoolean(valid);
            output.flush();

            if (!valid) {
                socket.close();
                return;
            }

            broadcast(username + " joined chat");

            while (true) {

                Message msg =
                        (Message) input.readObject();

                if (msg.getType().equals("text")) {

                    broadcast(
                            msg.getSender()
                                    + ": "
                                    + msg.getContent()
                    );
                }

                else if (msg.getType().equals("file")) {

                    broadcast(
                            "[FILE] "
                                    + msg.getSender()
                                    + " sent file: "
                                    + msg.getContent()
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(username + " disconnected");
        }
    }

    private void broadcast(String message) {

        try {

            for (ClientHandler client :
                    ChatServer.clients) {

                client.output.writeObject(
                        new Message(
                                "SERVER",
                                message,
                                "text"
                        )
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}