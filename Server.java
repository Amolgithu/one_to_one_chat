import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

public class Server {
    public Server(int port) throws Exception {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        Socket s = serverSocket.accept(); 
        System.out.println("Client connected.");

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String msg;
        while ((msg = in.readLine()) != null) {
            System.out.println("Received: " + msg);
        }
        serverSocket.close();
        s.close();
    }
}
