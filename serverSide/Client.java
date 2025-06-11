package serverSide;
import java.io.BufferedReader;
import java.net.Socket;


public class Client {
    public String Name;
    public Socket clienSocket;
    private BufferedReader in;
    public Client(String name,Socket socket, BufferedReader in2) {
        this.Name = name;
        this.clienSocket = socket;
        this.in = in2;
    }
    public String getName() {
        return Name;
    }
    public Socket getSocket() {
        return clienSocket;
    }
    public BufferedReader getBufferedReader() {
        return in;
    }
}
