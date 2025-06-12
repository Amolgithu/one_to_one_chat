import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;

import ClientSide.ClientConnector;
import serverSide.Server;

public class window extends JFrame{

    private int choice;
    private Server server;
    private ClientConnector client;

    public window() {
        setTitle("One-to-One Chat Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }
    public void RunApp(){
    //     System.out.println("Welcome to the One-to-One Chat Application!");
    // System.out.println("1. Create Server (Host)");
    // System.out.println("2. Connect to Server (Client)");
    // System.out.print("Please select an option (1 or 2): ");

    
    JButton createServerButton = new JButton("Create Server (Host)");
    JButton connectToServerButton = new JButton("Connect to Server (Client)");
    JButton choose = new JButton("Choose Option");
    JButton exButton = new JButton("Exit");

    createServerButton.addActionListener(e -> {
        choice = 1;
    });
    connectToServerButton.addActionListener(e -> {
        choice = 2;
    });

    choose.addActionListener(e->{
        Scanner scanner = new Scanner(System.in);

            switch (choice) {
                case 1:
                        try{
                             server = new Server(8989);
                        }catch(Exception ee){
                            System.out.println("Error starting server: " + ee.getMessage());
                        }
                    break;
            
                case 2:
                        System.out.print("Enter server port to connect: ");
                        int port = scanner.nextInt();
                        try{
                             client = new ClientConnector(port);
                        }catch(Exception ee){
                            System.out.println("Error connecting to server: " + ee.getMessage());
                        }
                        break;
                default:

                    System.out.println("Invalid choice. Please select 1 or 2.");
                    break;
            }
            scanner.close();
    });

    exButton.addActionListener(e -> {
        if(server != null) {
            server.closeServer();
            server = null;
        }
        if(client != null){
            client.closeConnection();
            client = null;
        }
        System.exit(0);
    });

    add(connectToServerButton);
    add(createServerButton);
    add(choose);
    add(exButton);
    setLayout(new java.awt.FlowLayout());

    
    }
}
