import java.io.*;
import java.net.*;

public class MyServer3 {
    public static void main(String args[]) throws Exception {
        ServerSocket ss = new ServerSocket(7777);
        Socket s = ss.accept();
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        while (!str.equals("STOP")) {
            str = din.readUTF();
            String rstr = "";
            char ch;
            int i;
            for (i = 0; i < str.length(); i++) {
                ch = str.charAt(i);
                rstr = ch + rstr;
            }
            System.out.println("Operation done.");
            dout.writeUTF(rstr);
            dout.flush();
        }
        din.close();
        s.close();
        ss.close();
    }
}