package service;

import model.Neighbor;
import model.RouterModel;

import javax.xml.ws.ServiceMode;
import java.util.List;
@ServiceMode
public class RouterService {

    public void addNeighbor(RouterModel routerModel, List<Neighbor> neighbors){
        routerModel.setNeighbors(neighbors);

    }
}