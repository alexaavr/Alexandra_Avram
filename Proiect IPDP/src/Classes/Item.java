package Classes;

public class Item {
    public String name;
    public int code;
    public int amount;
    public int price;

    public Item(String name, int code, int amount, int price) {
        this.name = name;
        this.code = code;
        this.amount = amount;
        this.price = price;
    }

    public Item() {
    }

    @Override
    public String toString() {
        return "Item{ \n" +
                "Name = " + name + '\n' +
                "Code = " + code + '\n' +
                "Amount = " + amount + '\n' +
                "Price = " + price +
                '}';
    }
}
