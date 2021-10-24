package service;

import Socket_RIP.Socket_RIP_Client;
import model.RouterModel;

public class ClientService {


    public void OpenClient(RouterModel routerModel) {
        for (int i = 0; i < routerModel.getNeighbors().size(); i++) {

            Socket_RIP_Client socket_RIP_client = new Socket_RIP_Client();
            socket_RIP_client.sendToServer(routerModel.getNeighbors().get(i), routerModel);
        }
    }

}
