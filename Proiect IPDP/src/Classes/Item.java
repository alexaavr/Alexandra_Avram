package Classes;

public class Item {
    public String name;
    public int code;
    public int amount;
    public int price;
    public String reserved;

    public Item(String name, int code, int amount, int price) {
        this.name = name;
        this.code = code;
        this.amount = amount;
        this.price = price;
    }

    public Item(String name, int code, int amount, int price, String reserved) {
        this.name = name;
        this.code = code;
        this.amount = amount;
        this.price = price;
        this.reserved = reserved;
    }

    @Override
    public String toString() {
        return "Item: " +
                "name = '" + name + '\'' +
                ", amount = " + amount +
                ", price = " + price;
    }
}
