import java.io.*;
import java.net.*;

public class MyClientCalc {
    public static void main(String args[]) throws Exception {
        Socket s = new Socket("localhost", 3333);
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = "", a, b, res, sth2 = "";
        int flag = 1;

        while (!str.equals("stop")) {
            int flag1 = 0;
            flag = 0;
            System.out.println("Enter numbers:");
            a = br.readLine();
            b = br.readLine();

            dout.writeUTF(a);
            dout.writeUTF(b);

            if (b.equals("0") && str.equals("Division")) {
                System.out.println("Cannot divide by zero");
                dout.writeUTF("Infinity");
                dout.flush();
                flag1 = 1;
            }

            if (flag1 == 0) {
                str = din.readUTF();
                if (str.equals("stop")) {
                    System.out.println("Operation stopped");
                    break;
                }

                res = din.readUTF();

                if (!res.equals("Infinity")) {
                    System.out.println("Calculation done: Result = " + res);
                } else {
                    sth2 = din.readUTF();
                    System.out.println(sth2);
                }
            }
        }

        din.close();
        dout.close();
        br.close();
        s.close();
    }
}
