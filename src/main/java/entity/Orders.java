package entity;

import service.ClientService;
import service.StuffService;

public class Orders {

    private int orderID;
    private Clients client = new Clients();
    private Stuff stuff = new Stuff();

    public Orders( Clients client, Stuff stuff) {

        this.client = client;
        this.stuff = stuff;
    }

    public Orders() {
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getClientId() {
        return client.getId();
    }

    public void setClientId(int id) {
        client.setId(id);
    }

    public int getStuffId() {
        return this.stuff.getStuffId();
    }

    public void setStuffId(String id) {

        this.stuff.setStuffId(Integer.parseInt(id));
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderID=" + orderID +
                ", clientName=" + new ClientService().getPibById(client) +
                ", stuffName=" + new StuffService().getNameById(stuff) +
                "}\n";
    }
}
