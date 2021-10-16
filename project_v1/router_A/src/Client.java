import java.net.*;
import java.io.*;



class Client{
    public static void main(String args[])throws Exception{
        //fig ip port
        ConfigRouter configRouter = new ConfigRouter();
        int port = configRouter.getPort();
        String ipAddress =  configRouter.getIp();

        configRouter.getFile();

        // connection socket
        Socket socketServer=new Socket(ipAddress,port);

        DataInputStream din=new DataInputStream(socketServer.getInputStream());
        DataOutputStream dout=new DataOutputStream(socketServer.getOutputStream());
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        String str="",str2="";
        while(!str.equals("stop")){
            str=br.readLine();
            dout.writeUTF(str);
            dout.flush();
            str2=din.readUTF();
            System.out.println("Server says: "+str2);
        }

        dout.close();
        socketServer.close();
    }
}