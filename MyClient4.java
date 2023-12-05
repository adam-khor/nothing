import java.net.*;
import java.io.*;

public class MyClient4 {
    public static void main(String arg[]) throws Exception {

        // InetAddress ia = InetAddress.getLocalHost();
        Socket s = new Socket("localhost", 7777);
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "", str2 = "";
        while (!str.equals("Stop")) {
            str = br.readLine();
            dout.writeUTF(str);
            dout.flush();
            str2 = din.readUTF();
            if (str2.equals("Stop"))
                break;
            char ch = str2.charAt(0);
            ch += 1;
            System.out.println(ch);
        }
        dout.flush();
        dout.close();
        s.close();
    }
}
