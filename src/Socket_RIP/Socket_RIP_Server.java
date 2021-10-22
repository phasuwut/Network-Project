package Socket_RIP;
import model.RouterModel;
import model.RoutingTableModel;
import service.ClientService;
import service.RouterService;
import service.RoutingTable;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Socket_RIP_Server {



    public void  Socket_RIP_Server(){}
    public void Response(ConfigRouter configRouter){
        try {
            System.out.println(configRouter);
            String reply = "";
            DatagramSocket socket = null;
            socket = new DatagramSocket(configRouter.getPort());
            byte[] incomingData = new byte[1024];
            while (true){
                DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
                socket.receive(incomingPacket);
                byte[] data = incomingPacket.getData();
                ByteArrayInputStream in = new ByteArrayInputStream(data);
                ObjectInputStream is = new ObjectInputStream(in);
                try {

                    System.out.println(0);
                    List<SocketData> listOfMessages = (List<SocketData>) is.readObject();

                    System.out.println(listOfMessages);
                    for(int i=0;i<listOfMessages.size();i++){
                        System.out.println(listOfMessages.get(i).getRouterName());
                        System.out.println(listOfMessages.get(i).getRouterPort());
                        System.out.println(listOfMessages.get(i).getRoutingTableModels());

                        for(int j=0;j<listOfMessages.get(i).getRoutingTableModels().size();j++){
                            System.out.println("**********");
                            System.out.println(listOfMessages.get(i).getRoutingTableModels().get(j).getDest_sub());
                            System.out.println(listOfMessages.get(i).getRoutingTableModels().get(j).getNext_router());
                            System.out.println(listOfMessages.get(i).getRoutingTableModels().get(j).getHops_to_dest());
                        }
                        System.out.println(" ");
                    }
                    System.out.println(2);
                    reply="ok";

                } catch (Exception e) {
                    System.out.println("error");
                    System.out.println(e);
                    reply="error";
                }

                // responst to clients
                InetAddress IPAddress = incomingPacket.getAddress();
                int port = incomingPacket.getPort();
                byte[] replyBytea = reply.getBytes();
                DatagramPacket replyPacket = new DatagramPacket(replyBytea, replyBytea.length, IPAddress, port);
                socket.send(replyPacket);
            }
        }
        catch (Exception e) {
            System.out.println("error");
            System.out.println(e);
        }
    }

    public void waitingForClient(RouterModel routerModel){




        try {
//            Socket socket = new Socket(routerModel.getName(), parseInt(routerModel.getPort()));

            ServerSocket ss = new ServerSocket((routerModel.getPort()));

            System.out.println("ServerSocket awaiting connections...");

            for(int i = 0; i < routerModel.getNeighbors().size(); i++){


                Socket_RIP_Client socket_RIP_client = new Socket_RIP_Client();
                socket_RIP_client.sendToServer(routerModel.getNeighbors().get(i), routerModel);
            }


            while (true) {
                Socket socket = ss.accept(); // blocking call, this will wait until a connection is attempted on this port.
                System.out.println("Connection from " + socket + "!");
                // get the input stream from the connected socket
                InputStream inputStream = socket.getInputStream();
                // create a DataInputStream so we can read data from it.
                DataInputStream dataInputStream = new DataInputStream(inputStream);
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);


                List<RoutingTableModel> listOfMessages = (List<RoutingTableModel>) objectInputStream.readObject();
                // read the message from the socket
                String routerName = dataInputStream.readUTF();
                RoutingTable routingTable = new RoutingTable();
                System.out.println("---------------------------------" + routerName + " online -----------------------");
                System.out.println(listOfMessages.toString());

//                routingTable.printRoutingTable(listOfMessages,routerName);
                for (int i = 0;i < routerModel.getNeighbors().size(); i++){
                    System.out.println(routerModel.getNeighbors().get(i).getName());
                    System.out.println(routerName);

                    if( routerModel.getNeighbors().get(i).getName().equals(routerName)){
                        if(routingTable.compare(routerModel.getNeighbors().get(i).getRoutingTableModel(),listOfMessages)){ // เพื่อนบ้านไม่ได้ ไม่อัพเดท  up
                            RouterService routerService = new RouterService();

                            routerService.updateRoutingTableWhenNeighborOnline(routerModel.getRoutingTableModels(), listOfMessages,routerName); // อัพเดทตัวเอง
//                            routerService.tellNeighborToHaveUpdate(routerModel); // update ตารางเพื่อนบ้าน


                            routingTable.printRouterModel(routerModel);


                        }
                        else{ // เพื่อนบ้านมีอัพเดท
                            routerModel.getNeighbors().get(i).setRoutingTableModel(listOfMessages); // เปลี่ยนเพื่อนบ้าน
                            RouterService routerService = new RouterService();

                            routerService.updateRoutingTableWhenNeighborOnline(routerModel.getRoutingTableModels(), listOfMessages,routerName); // อัพเดทตัวเอง
                            routerService.tellNeighborToHaveUpdate(routerModel);
                            routingTable.printRouterModel(routerModel);

//                            routerService.tellNeighborToHaveUpdate(routerModel);
                            //        routerService.updateNeighborRoutingTable(routerList, routerModelB); // update ตารางเพื่อนบ้าน
//                            for(int j = 0; j < routerModel.getNeighbors().size(); j++){
//
//                                Socket_RIP_Client socket_RIP_client = new Socket_RIP_Client();
//                                socket_RIP_client.sendToServer(routerModel.getNeighbors().get(i), routerModel);
//                            }

                        }

                    }
                }

            }

        }
        catch (Exception e) {
            System.out.println("error 555");
            System.out.println(e);
        }



    }
}
