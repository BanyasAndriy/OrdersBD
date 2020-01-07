package entity;

public class Stuff {

    private int stuffId;
    private String name;
    private String description;
    private int price;


    public Stuff( String name, String description, int price) {

        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Stuff() {
    }


    public int getStuffId() {
        return stuffId;
    }

    public void setStuffId(int stuffId) {
        this.stuffId = stuffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Stuff{" +
                "stuffId=" + stuffId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}

