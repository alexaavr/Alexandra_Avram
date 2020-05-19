package Classes;

public class Item {
    public String name;
    public Integer code;
    public Integer amount;
    public Integer price;

    public Item(String name, Integer code, Integer amount, Integer price) {
        this.name = name;
        this.code = code;
        this.amount = amount;
        this.price = price;
    }

    public Item(String name) {
        this.name = name;
    }

    public Item(Integer code) {
        this.code = code;
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
                "}\n";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }


}
