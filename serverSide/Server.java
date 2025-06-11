package serverSide;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private ServerSocket serverSocket;
    private CopyOnWriteArrayList<Client> clientList = new CopyOnWriteArrayList<>();

    public Server(int port) throws Exception {
        serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        Thread thread1 = new Thread(() -> {
            try {
                this.acceptingClients();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Thread-for-Client-Accept");

        Thread thread2 = new Thread(() -> this.acceptingChats(), "Thread-for-Chat-Read");

        thread1.start();
        thread2.start();
    }

    private void acceptingClients() throws Exception {
        while (true) {
            Socket s = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String name = in.readLine();

            if (name == null || name.isEmpty() || name.equals("test")) {
                System.out.println("Client did not provide a valid name. Disconnecting.");
                s.close();
                continue;
            }

            clientList.add(new Client(name, s, in));
            System.out.println("Client " + name + " connected.");
        }
    }

    private void acceptingChats() {
        while (true) {
            for (Client client : clientList) {
                try {
                    if (client.getSocket().isClosed()) continue;

                    if (client.getBufferedReader().ready()) {
                        String msg = client.getBufferedReader().readLine();

                        if (msg == null || msg.equalsIgnoreCase("exit")) {
                            System.out.println("Client " + client.getName() + " has exited.");
                            clientList.remove(client);
                            client.getSocket().close();
                            continue;
                        }

                        System.out.println(client.getName() + ": " + msg);
                    }
                } catch (Exception e) {
                    System.out.println("Error with client " + client.getName() + ": " + e.getMessage());
                    try {
                        client.getSocket().close();
                    } catch (Exception ex) {
                        System.out.println("Failed to close socket: " + ex.getMessage());
                    }
                    clientList.remove(client);
                }
            }

            try {
                Thread.sleep(100); // avoid tight loop
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
