// Import all necessary packages and liibraries
package client;
import java.io.*;
import java.net.*;
import java.util.Scanner;

// Define the main client class
public class UsernamePasswordGeneratorClient {
    public static void main(String[] args) {
        // Specify the IP address and PORT number
        final String SERVER_IP = "127.0.0.1";
        final int SERVER_PORT = 12345;

        // Use a try catch block to connect to the server
        // if an error happens return the stack tracer
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT)) {
            BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter serverOut = new PrintWriter(socket.getOutputStream(), true);
            // Create a Scanner object to get client input
            Scanner scanner = new Scanner(System.in);

            // Ask user to input in their preferred username & password
            System.out.print("Please enter your username: ");
            String username = scanner.nextLine();

            System.out.print("Please enter your password: ");
            String password = scanner.nextLine();

            // Send the username & password responses to the server
            serverOut.println(username);
            serverOut.println(password);

            // Receive and display to user the newly generated username & password from the Server
            System.out.println(serverIn.readLine());
            System.out.println(serverIn.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
