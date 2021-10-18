import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.*;
import java.io.ObjectOutputStream;

public class Client {
    DatagramSocket Socket;
    public Client() {}

    public void createAndListenSocket() {
        try {

            Socket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost");
            byte[] incomingData = new byte[1024];

            Student student = new Student(1, "Bijoy", "Kerala"); //
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(outputStream);
            os.writeObject(student);//

            byte[] data = outputStream.toByteArray(); // ข้อมูลที่ส่ง
            System.out.println("1");
            System.out.println(student);
            System.out.println("2");
            System.out.println(data);

            DatagramPacket sendPacket = new DatagramPacket(data, data.length, IPAddress, 9876);
            Socket.send(sendPacket);

            System.out.println("Message sent from client");

            //Response
            DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
            Socket.receive(incomingPacket);
            String response = new String(incomingPacket.getData());
            System.out.println("Response from server:" + response);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void main(String[] args) {
        Client client = new Client();
        client.createAndListenSocket();
    }
}


/*
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Client {

    public static void main(String[] args) throws IOException {
        // need host and port, we want to connect to the ServerSocket at port 7777
        Socket socket = new Socket("localhost", 7777);
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        // make a bunch of messages to send.
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("Hello from the other side!"));
        messages.add(new Message("How are you doing?"));
        messages.add(new Message("What time is it?"));
        messages.add(new Message("Hi hi hi hi."));

        System.out.println( messages);
        System.out.println("Sending messages to the ServerSocket");
        objectOutputStream.writeObject(messages);

        System.out.println("Closing socket and terminating program.");
        socket.close();
    }
}
// https://gist.github.com/chatton/14110d2550126b12c0254501dde73616*/
