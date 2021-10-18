import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {


        while (true) {
            ServerSocket ss = new ServerSocket(7777);
            Socket socket = ss.accept(); // detail sender
            System.out.println("Connection from " + socket + "!");

            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream); // -้อมูลที่ส่งเข้ามา
            List<Message> listOfMessages = (List<Message>) objectInputStream.readObject();

            System.out.println(listOfMessages);
            listOfMessages.forEach((msg)-> System.out.println(msg.getText()));

            System.out.println("Closing sockets.");
            socket.close();
            ss.close();
        }

    }
}
