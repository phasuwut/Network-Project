package model;

public class Router {

    private String dest_sub;

    private String next_router;

    private Integer hops_to_dest;

    public String getDest_sub() {
        return dest_sub;
    }

    public void setDest_sub(String dest_sub) {
        this.dest_sub = dest_sub;
    }

    public String getNext_router() {
        return next_router;
    }

    public void setNext_router(String next_router) {
        this.next_router = next_router;
    }

    public Integer getHops_to_dest() {
        return hops_to_dest;
    }

    public void setHops_to_dest(Integer hops_to_dest) {
        this.hops_to_dest = hops_to_dest;
    }
}
