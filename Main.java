import java.util.Scanner;

import ClientSide.ClientConnector;
import serverSide.Server;

public class Main{
    public static void main(String[] args) {
    System.out.println("Welcome to the One-to-One Chat Application!");
    System.out.println("1. Create Server (Host)");
    System.out.println("2. Connect to Server (Client)");
    System.out.print("Please select an option (1 or 2): ");


    Scanner scanner = new Scanner(System.in);
    int choice = scanner.nextInt();

    switch (choice) {
        case 1:
                try{
                    Server server = new Server(8989);
                }catch(Exception e){
                    System.out.println("Error starting server: " + e.getMessage());
                }
            break;
    
        case 2:
                System.out.print("Enter server port to connect: ");
                int port = scanner.nextInt();
                try{
                    ClientConnector client = new ClientConnector(port);
                }catch(Exception e){
                    System.out.println("Error connecting to server: " + e.getMessage());
                }
                break;
        default:

            System.out.println("Invalid choice. Please select 1 or 2.");
            break;
    }
    scanner.close();
    

    
}
}