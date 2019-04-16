package guiElements;

import data.Variables;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server {

    public static void main(String[] args)
    {
        DataInputStream din;
        DataOutputStream dout;
        ServerSocket ss;
        System.out.println("welcome to server");
        ss= null;
        try {
            ss = new ServerSocket(3333);
            Socket s=ss.accept();

            din =new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            System.out.println("HERE: " + Variables.paid);
            String temp=din.readUTF();
            if(temp.equals("YES"))
            {
                dout.writeUTF("YES");
            }
            System.out.println("HERE 2: " + temp );
            System.out.println(Variables.paid);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
