public class Main{
    public static void main(String[] args) {
    System.out.println("Welcome to the One-to-One Chat Application!");
    System.out.println("1. Create Server (Host)");
    System.out.println("2. Connect to Server (Client)");
    System.out.print("Please select an option (1 or 2): ");


    try{
        Server server = new Server(8989);
    }catch(Exception e){
        System.out.println("Error starting server: " + e.getMessage());
    }

    
}
}