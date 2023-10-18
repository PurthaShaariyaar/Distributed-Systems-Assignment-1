// Import all necessary packages and liibraries
package server;
import java.io.*;
import java.net.*;
import java.util.Random;

// Define the main server class
public class UsernamePasswordGeneratorServer {
  public static void main(String[] args) {
    // Initiate a port that server will listen for incoming client connections
    final int PORT = 12345;

    // Use a try catch block to listen for incoming client connections on the port
    // print the stack trace error if any errors appear when listening
    try (ServerSocket serverSocket = new ServerSocket(PORT)) {
      System.out.println("Server is running...");

      // Continuously listen for incoming client connections
      // when client connects create a socket object
      // a new thread is then created for the purpose of multithreading
      while (true) {
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client is connected: " + clientSocket.getInetAddress());

        // Handle the client in a seperate thread
        new ClientHandler(clientSocket).start();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

// Define class to handle each connected client, purpose for multithreading via Thread
class ClientHandler extends Thread {
  // Initiate a constructor socket
  private Socket clientSocket;

  // Define a public class to take in the Socket as an argument to initiate a
  // communication with each new client
  public ClientHandler(Socket clientSocket) {
    this.clientSocket = clientSocket;
  }

  // Execute a run method to begin the logic behind the novel features
  @Override
  public void run() {
    // Initiate a try catch block to communicate with the client
    // use a BufferredReader to read input from the client
    // use a PrintWriter to write responses back to the client
    try (
      BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
    ) {
      // From the client read the input for username and password
      String username = reader.readLine();
      String password = reader.readLine();

      // Call method to generate novel username & password
      String generatedUsername = generateUsername(username);
      String generatedPassword = generatePassword(password);

      // Send the generated username & password to the client
      writer.println("Generated Username: " + generatedUsername);
      writer.println("Generated Password: " + generatedPassword);

      // Close socket connection
      clientSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Method to generate a new username based on client input
  private String generateUsername(String baseUsername) {
    // Make string into lowercase and then use Random method to add random integer
    return baseUsername.toLowerCase() + new Random().nextInt(1000);
  }

  private String generatePassword(String basePassword) {
    // Use Random method to add random integer
    return basePassword + new Random().nextInt(1000000);
  }
}
