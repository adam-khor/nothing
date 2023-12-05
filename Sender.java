import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Sender {
    public static void main(String args[]) throws Exception {
        Sender sender = new Sender();
        sender.run();
    }

    public void run() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no of frames to be sent:");
        int noOfFrames = sc.nextInt();

        Socket socket = new Socket("localhost", 7777);
        PrintStream socketWriter = new PrintStream(socket.getOutputStream());
        BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        for (int i = 0; i < noOfFrames; i++) {
            System.out.println("Sending Frame: " + i);
            socketWriter.println(i);
            Thread.sleep(1000);

            String acknowledgement = socketReader.readLine();
            if (acknowledgement.equals("Received")) {
                System.out.println("Acknowledgment received for Frame: " + i);
            } else {
                System.out.println("Timeout! Resending Frame: " + i);
                i--; // To resend the current frame
            }
        }

        socketWriter.println("exit"); // Send exit signal to receiver
        socket.close();
    }
}
