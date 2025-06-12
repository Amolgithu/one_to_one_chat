package serverSide;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client {
    public String Name;
    public Socket clienSocket;
    private BufferedReader in;
    PrintWriter out;
    public Client(String name,Socket socket, BufferedReader in2, PrintWriter out) {
        this.Name = name;
        this.clienSocket = socket;
        this.in = in2;
        this.out = out;
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
    public PrintWriter getPrintWriter() {
        return out;
    }
    public void closeall() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (clienSocket != null && !clienSocket.isClosed()) clienSocket.close();
        } catch (Exception e) {
            System.out.println("Error closing resources: " + e.getMessage());
        }
    }
}
