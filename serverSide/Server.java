package serverSide;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
                in.close();
                s.close();
                continue;
            }
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);

            clientList.add(new Client(name, s, in,out));
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
                            client.closeall();
                            clientList.remove(client);
                            client.getSocket().close();
                            continue;
                        }
                        String sendmsg = client.getName() + ": " + msg;
                        System.out.println(sendmsg);
                        sendmessagetoOthers(client, sendmsg);

                        
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
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void sendmessagetoOthers(Client client, String sendmsg) {
        for (Client c : clientList) {
            if (!c.getName().equals(client.getName())) {
                try {
                    
                    if (c.out != null) {
                        c.out.println(sendmsg);
                    }
                } catch (Exception e) {
                    System.out.println("Error sending message to " + c.getName() + ": " + e.getMessage());
                }
            }
        }
    }

    public void closeServer() {
        try {
            for (Client client : clientList) {
                client.closeall();
            }
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
            System.out.println("Server closed.");
        } catch (Exception e) {
            System.out.println("Error closing server: " + e.getMessage());
        }
    }
}
