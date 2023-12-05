import java.io.*;
import java.net.*;

class MyClient3 {
    public static void main(String args[]) throws Exception {
        Socket s = new Socket("localhost", 7777);
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter STOP for close the connection:");
        String str = "";
        int i = 1;
        while (i != 0) {
            String rstr = "";
            System.out.println("Enter String:");
            str = br.readLine();
            dout.writeUTF(str);
            dout.flush();
            if (str.equals("STOP")) {
                break;
            }
            rstr = din.readUTF();
            System.out.println("Reverse = " + rstr);
        }
        dout.close();
        s.close();
    }
}