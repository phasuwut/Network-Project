package Router.A;

public class testServer {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        byte[] incomingData = new byte[1024];

        while (true) {
            DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
            socket.receive(incomingPacket);
        }
    }
}
