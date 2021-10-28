package Socket_RIP;
import model.Count;
import model.RouterModel;
import model.RoutingTableModel;
import service.*;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Integer.parseInt;

public class Socket_RIP_Server {

//    public service.NeighborService neighborService;

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

    public void waitingForClient(RouterModel routerModel, List<Count> countList){
//        List<Count> countList = new ArrayList<Count>();



        try {
//            Socket socket = new Socket(routerModel.getName(), parseInt(routerModel.getPort()));

            ServerSocket ss = new ServerSocket((routerModel.getPort()));
            routerModel.setStatus(true);
            System.out.println("ServerSocket awaiting connections...");

            for(int i = 0; i < routerModel.getNeighbors().size(); i++){
//                Count count = new Count();
//                countList.add(new Count(routerModel.getNeighbors().get(i).getName(),0,0,false));
                Socket_RIP_Client socket_RIP_client = new Socket_RIP_Client();
                socket_RIP_client.sendToServer(routerModel.getNeighbors().get(i), routerModel);
            }


            while (true) {
                Socket socket = ss.accept(); // blocking call, this will wait until a connection is attempted on this port.
//                System.out.println("Connection from " + socket + "!");
                // get the input stream from the connected socket
                InputStream inputStream = socket.getInputStream();
                // create a DataInputStream so we can read data from it.
                DataInputStream dataInputStream = new DataInputStream(inputStream);
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);


                List<RoutingTableModel> listOfMessages = (List<RoutingTableModel>) objectInputStream.readObject();
                // read the message from the socket
                String message = dataInputStream.readUTF();
                RoutingTable routingTable = new RoutingTable();

                if(message.length() == 8){ // has update
                    System.out.println("------------------------ " + message + " has update ----------------------");
                    for (int i = 0;i < routerModel.getNeighbors().size(); i++){
//                    System.out.println(routerModel.getNeighbors().get(i).getName());
//                    System.out.println(routerName);

                        if( routerModel.getNeighbors().get(i).getName().equals(message)){
                            routerModel.getNeighbors().get(i).setStatus(true);
                            if(routingTable.compare(routerModel.getNeighbors().get(i).getRoutingTableModel(),listOfMessages)){ // เพื่อนบ้านไม่ได้ ไม่อัพเดท  up
                                RouterService routerService = new RouterService();

                                routerService.updateRoutingTableWhenNeighborOnline(routerModel.getRoutingTableModels(), listOfMessages,message); // อัพเดทตัวเอง


                                routingTable.printRouterModel(routerModel);


                            }
                            else{ // เพื่อนบ้านมีอัพเดท
                                routerModel.getNeighbors().get(i).setRoutingTableModel(listOfMessages); // เปลี่ยนเพื่อนบ้าน
                                RouterService routerService = new RouterService();

                                routerService.updateRoutingTableWhenNeighborOnline(routerModel.getRoutingTableModels(), listOfMessages,message); // อัพเดทตัวเอง
                                routerService.tellNeighborToHaveUpdate(routerModel);
                                routingTable.printRouterModel(routerModel);

//                            }

                            }

                        }
                    }
                }
                else { // update status
                    if(message.startsWith("Hello")) {
                        System.out.println(message);
                        routingTable.printRouterModel(routerModel);

                        for (int i = 0; i < routerModel.getNeighbors().size(); i++) {
                            if (routerModel.getNeighbors().get(i).getName().equals(message.split("from ")[1])) {
                                for (int j = 0; j < countList.size(); j++) {


                                    if (countList.get(j).getName().equals(message.split("from ")[1])) {

                                        countList.get(i).setUpdatedTime(System.currentTimeMillis());

                                        // check 180 sec
                                        Timer myTimer = new Timer();
                                        myTimer.schedule(new TimerTask() {
                                            NeighborService neighborService = new NeighborService();

                                            public void run() {

                                                String name = message.split("from ")[1];

                                                System.out.println("-------------------" + name + "---------------------");
                                                neighborService.checkStatusNeighbors(routerModel, countList, name);
                                            }

                                        }, 18000);




                                    }


                                }
                            }
                        }



                    }
                    else if(message.startsWith("Delete")){
                        System.out.println(message);

                        for (int i = 0; i < routerModel.getRoutingTableModels().size();){
//
                            if(routerModel.getRoutingTableModels().get(i).getNext_router().equals(message.split("from ")[1]) &&
                                    routerModel.getRoutingTableModels().get(i).getDest_sub().equals(message.split(" from")[0].split("Delete ")[1])  ){
                                RouterService routerService = new RouterService();

                                routerModel.getRoutingTableModels().remove(i);
                                System.out.println("removeeeeeee");
                                System.out.println(routerModel.getRoutingTableModels().toString());
                                routingTable.printRouterModel(routerModel);

//                                routerService.tellNeighborToHaveUpdate(routerModel);

                            }
                            else{
                                i++;
                            }
                        }
//                        routingTable.printRouterModel(routerModel);


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
