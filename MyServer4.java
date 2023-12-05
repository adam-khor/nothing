import java.net.*;
import java.io.*;

public class MyServer4 {
    public static void main(String arg[]) throws Exception {
        ServerSocket ss = new ServerSocket(7777);
        Socket s = ss.accept();
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "", str2 = "";

        while (!str.equals("Stop")) {
            str = din.readUTF();
            if (str.equals("Stop")) {
                str2 = str;
            } else {
                char ch = str.charAt(0);
                ch += 1;

                System.out.println(ch);
                str2 = br.readLine();
            }
            dout.writeUTF(str2);
            dout.flush();
        }
        din.close();
        s.close();
        ss.close();
    }
}
