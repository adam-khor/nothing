import java.io.*;
import java.net.*;

public class MyClientCalc {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 3333);
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String a, b, operation, result;

        while (true) {
            System.out.println("Enter first number:");
            a = br.readLine();
            dout.writeUTF(a);

            System.out.println("Enter second number:");
            b = br.readLine();
            dout.writeUTF(b);

            System.out.println("Enter operation (Addition, Subtraction, Multiplication, Division):");
            operation = br.readLine();
            dout.writeUTF(operation);

            if (operation.equalsIgnoreCase("stop")) {
                System.out.println("Operation stopped by user");
                break;
            }

            result = din.readUTF();
            System.out.println("Server result: " + result);
        }

        din.close();
        dout.close();
        br.close();
        s.close();
    }
}
