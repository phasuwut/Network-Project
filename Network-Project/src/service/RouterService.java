package service;

import model.Neighbor;
import model.RouterModel;

import java.util.List;

public class RouterService {

    public void addNeighbor(RouterModel routerModel, List<Neighbor> neighbors){
        routerModel.setNeighbors(neighbors);

    }
}
