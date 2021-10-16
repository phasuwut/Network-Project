import java.net.*;
import java.io.*;

//ip address
import java.net.InetAddress;
import java.net.UnknownHostException;

class Server{
    public static void main(String args[])throws Exception{

        int port=9091;
        ServerSocket socketServer=new ServerSocket(port);

        Socket s=socketServer.accept();
        DataInputStream din=new DataInputStream(s.getInputStream());
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        String str="",str2="";
        while(!str.equals("stop")){
            str=din.readUTF();
            System.out.println("client says: "+str);
            str2=br.readLine();
            dout.writeUTF(str2);
            dout.flush();
        }
        din.close();
        s.close();
        socketServer.close();
    }}