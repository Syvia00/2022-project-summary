package Currency;

import java.util.HashMap;
import java.util.*;

public class Currency {

    // initial value
    public String currency;
    public Double lastRate;
    public Double currentRate;
    public static HashMap<Date, Double> rates = new HashMap<>();
    public static ArrayList<Date> dateList = new ArrayList<>();

    public Currency(String currency, double rate, Date date){
        this.currency = currency;
        this.rates.put(date,rate);
        this.lastRate = 0.00;
        this.currentRate = rate;
        this.dateList.add(date);
    }

    public void addNewRate(Date date, double rate){
        rates.put(date, rate);
        this.lastRate = this.currentRate;
        this.currentRate = rate;
        dateList.add(date);
    }

    public String getCurrency(){
        return this.currency;
    }

    public Double getCurrentRate(){

        return this.currentRate;
    }

    public Double getComparison(){

        return this.currentRate-this.lastRate;
    }

    public Double getRateByDate(Date date){

        return rates.get(date);
    }
}
