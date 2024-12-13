package vending;

import java.util.HashMap;

public class Transaction {
    public String name;
    public String date;
    public String time;
    public Integer itemNumber;
    public String[] itemList = new String[32];
    public Double amountPaid;
    public Double change;
    public HashMap<Double, Integer> changes;
    public String method;
    public Boolean cancel;
    public String reason;
    public String items;

    public Transaction(){}
    public Transaction(String name, String date, String time){
        this.name = name;
        this.date = date;
        this.time = time;
        this.cancel = false;
        this.itemNumber = 0;
    }

    public String getDate(){
        return this.date;
    }

    public String getTime(){
        return this.time;
    }

    public String getName(){
        return this.name;
    }

    public Integer getItemNumber(){
        return this.itemNumber;
    }

    public String[] getItemList(){
        return this.itemList;
    }

    public Double getAmountPaid(){
        return this.amountPaid;
    }

    public Double getChange(){
        return this.change;
    }

    public String getMethod(){
        return this.method;
    }

    public Boolean getCancel(){
        return this.cancel;
    }

    public String getReason(){
        return this.reason;
    }

    public String getItems(){
        return this.items;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public void setChange(Double change) {
        this.change = change;
    }
    public void setChanges(HashMap<Double, Integer> changes) { this.changes = changes; }

    public void setCancel() {
        this.cancel = true;
    }

    public void setReason(String reason){
        this.reason = reason;
    }
    public void setMethod(String method){
        this.method = method;
    }
    public void setItemList(String[] itemList){
        this.itemList = itemList;
    }
    public void setItemNumber(Integer num){
        this.itemNumber = num;
    }
    public void setItems(String i){this.items = i;}
}
