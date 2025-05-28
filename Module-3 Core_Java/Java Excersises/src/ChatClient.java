import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234);
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader response = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        System.out.println(response.readLine()); // welcome message

        String userInput;
        while ((userInput = input.readLine()) != null) {
            output.println(userInput);
            System.out.println("Server: " + response.readLine());
        }

        socket.close();
    }
}
