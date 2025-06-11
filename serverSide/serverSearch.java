package serverSide;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

public class serverSearch {
 
    public static List<String> getServerList(int port){

        String subnet = getSubNet();
        List<String> serverList = new java.util.ArrayList<>();

        if(subnet.isEmpty()){
            System.out.println("Error: Unable to determine local subnet.");
            return serverList;
        }

        try (Socket socket = new Socket()) {
                socket.connect(new java.net.InetSocketAddress("localhost", port), 200);
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                out.println("test");
                System.out.println("Local server found on port " + port);
                serverList.add("localhost");
                socket.close();
            } catch (IOException e) {
            }
            System.out.println("subnet: " + subnet);
        // for(int i=1; i<255; i++){
        //     String ip = subnet + i;
        //     try (Socket socket = new Socket()) {
        //         socket.connect(new java.net.InetSocketAddress(ip, port), 200); 
        //         PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
        //         out.println("test");// timeout = 200 ms
        //         serverList.add(ip);
        //         socket.close();
        //     } catch (IOException e) {
        //     }
        // }


        System.out.println("Search done");
        return serverList;    
    


    }

    private static String getSubNet(){
        String subnet = "";
        try{
            InetAddress ip = InetAddress.getLocalHost();
            int index=  ip.getHostAddress().indexOf(".",8);
            subnet = ip.getHostAddress().substring(0, index);
        }catch(Exception e){
            System.out.println("Error getting local host address: " + e.getMessage());
        }
        return subnet+".";
    }

}
