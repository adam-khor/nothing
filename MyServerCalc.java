import java.io.*;
import java.net.*;

public class MyServerCalc {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(3333);
        System.out.println("Server is waiting for client...");

        Socket s = ss.accept();
        System.out.println("Client connected.");

        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = "", a, b, result;
        int flag = 1;

        while (!str.equals("stop")) {
            int flag1 = 0;
            flag = 0;
            a = din.readUTF();
            b = din.readUTF();

            if (b.equals("0") && (str.equals("Division") || str.equals("division"))) {
                System.out.println("Cannot divide by zero");
                dout.writeUTF("Infinity");
                dout.flush();
                flag1 = 1;
            }

            if (flag1 == 0) {
                str = din.readUTF();
                if (str.equals("stop")) {
                    System.out.println("Operation stopped by client");
                    break;
                }

                System.out.println("Operation: " + str);
                int num1 = Integer.parseInt(a);
                int num2 = Integer.parseInt(b);

                switch (str.toLowerCase()) {
                    case "addition":
                        result = String.valueOf(num1 + num2);
                        break;
                    case "subtraction":
                        result = String.valueOf(num1 - num2);
                        break;
                    case "multiplication":
                        result = String.valueOf(num1 * num2);
                        break;
                    case "division":
                        result = (num2 != 0) ? String.valueOf(num1 / num2) : "Infinity";
                        break;
                    default:
                        result = "Invalid operation";
                        break;
                }

                dout.writeUTF(result);
                dout.flush();
                System.out.println("Result sent to client: " + result);
            }
        }

        din.close();
        dout.close();
        br.close();
        s.close();
        ss.close();
    }
}
