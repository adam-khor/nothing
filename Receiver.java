import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Receiver {
    public static void main(String args[]) throws Exception {
        Receiver receiver = new Receiver();
        receiver.run();
    }

    public void run() throws Exception {
        String temp = "any message", str = "exit";
        ServerSocket serverSocket = new ServerSocket(7777);
        Socket socket = serverSocket.accept();

        BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintStream socketWriter = new PrintStream(socket.getOutputStream());

        while (!temp.equals(str)) {
            Thread.sleep(1000);
            temp = socketReader.readLine();

            if (temp.equals(str)) {
                break;
            }

            System.out.println("Frame " + temp + " was received");
            Thread.sleep(500);
            socketWriter.println("Received");
        }

        System.out.println("ALL FRAMES WERE RECEIVED SUCCESSFULLY");
    }
}
