import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server is running...");

        Socket clientSocket = serverSocket.accept();
        BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

        output.println("Welcome to the chat server!");
        String line;
        while ((line = input.readLine()) != null) {
            System.out.println("Client says: " + line);
            output.println("Echo: " + line);
        }

        clientSocket.close();
        serverSocket.close();
    }
}
