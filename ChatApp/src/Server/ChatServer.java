package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ChatServer {

    public static Vector<ClientHandler> clients =
            new Vector<>();

    public static void main(String[] args) {

        try {

            ServerSocket serverSocket =
                    new ServerSocket(5000);

            System.out.println("Server Started...");

            while (true) {

                Socket socket = serverSocket.accept();

                System.out.println("Client Connected");

                ClientHandler handler =
                        new ClientHandler(socket);

                clients.add(handler);

                handler.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}