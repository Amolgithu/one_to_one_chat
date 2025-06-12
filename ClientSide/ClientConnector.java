package ClientSide;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import serverSide.serverSearch;

public class ClientConnector{
      BufferedReader in;
     Socket s;

    public ClientConnector(int port) throws Exception {

        
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        
        String msg;
        
        System.out.println("available Servers : ");
        System.out.println(serverSearch.getServerList(port));
        
        System.out.println("Enter IP you want to connect to : ");
        String ip = input.readLine();
        
        s = new Socket(ip, port);
        System.out.println("Connected to server on port " + port);
        in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        System.out.print("Enter your name: ");
        String name = input.readLine();
        out.println(name);

        Thread thread = new Thread(() -> {
            try {
                recevingMessages();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Thread-for-Chat-Read");
        thread.start();

        while((msg = input.readLine())!=null){

            out.println(msg);
            if(msg.equalsIgnoreCase("exit")){
                System.out.println("Exiting chat.");
                thread.interrupt();
                break;
            }
        }

        s.close();
        in.close();
        out.close();
        input.close();
        System.out.println("Connection closed.");

    }

    public void recevingMessages()throws Exception {

        while(true){
            if(in.ready()){
                String msg = in.readLine();
                if(msg != null && !msg.isEmpty()) {
                    System.out.println(msg);
                }
            }
        }
        

    }

    public void closeConnection() {
        try {
            if (s != null && !s.isClosed()) s.close();
            if (in != null) in.close();
        } catch (Exception e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }

}