package entity;

public class Clients {

    private int clientId;
    private String pib;
    private String phone;
    private String address;


    public Clients(String pib, String phone, String address) {

        this.pib = pib;
        this.phone = phone;
        this.address = address;
        
    }

    public Clients() {
    }

    public int getId() {
        return clientId;
    }

    public void setId(int id) {
        this.clientId = id;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client : " +
                "\nid=" + clientId +
                ",\npib='" + pib + '\'' +
                ",\nphone='" + phone + '\'' +
                ",\naddress='" + address + '\'' +
                '\n';
    }
}
