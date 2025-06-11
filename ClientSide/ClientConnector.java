package ClientSide;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import serverSide.serverSearch;

public class ClientConnector{

    public ClientConnector(int port) throws Exception {

        
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        
        String msg;
        
        System.out.println("available Servers : ");
        System.out.println(serverSearch.getServerList(port));
        
        System.out.println("Enter IP you want to connect to : ");
        String ip = input.readLine();
        
        Socket s = new Socket(ip, port);
        System.out.println("Connected to server on port " + port);
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        System.out.print("Enter your name: ");
        String name = input.readLine();
        out.println(name);
        while((msg = input.readLine())!=null){

            out.println(msg);
            if(msg.equalsIgnoreCase("exit")){
                System.out.println("Exiting chat.");
                break;
            }
        }

        s.close();
        System.out.println("Connection closed.");

    }

}