package vending;

public class currentBuy {
    public String name;
    public Integer amount;
    public Double price;

    public currentBuy(){}
    public currentBuy(String name, Integer amount){
        this.name = name;
        this.amount = amount;
    }
    public void setPrice(Double price){
        this.price = price;
    }
    public String getName(){return this.name;}
    public Integer getAmount(){return this.amount;}
}
