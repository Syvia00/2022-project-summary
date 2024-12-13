package vending;

public class Snack {
    private String category;
    private String name;
    private Integer amount;
    private Double price;
    private Integer code;
    public Integer sold;

    public Snack(){}

    public Snack(String category, String name, Integer amount, Double price,  Integer code, Integer sold) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.amount = amount;
        this.code = code;
        this.sold = sold;
    }

    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.category;
    }

    public Double getPrice() {
        return this.price;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public Integer getCode(){ return this.code;}

    public Integer getSold(){return this.sold;}

    public void changeAmount(Integer num) {
        this.amount += num;
    }

    public void changePrice(Double price){
        this.price += price;
    }

    public void changeCode(Integer code){
        this.code = code;
    }

    public void changeCategory( String category){
        this.category = category;
    }

    public void changeName( String name){
        this.name = name;
    }
    public void changeSold(Integer sold){this.sold += sold;}
}