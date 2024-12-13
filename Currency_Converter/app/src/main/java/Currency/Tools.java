package Currency;

//import org.junit.platform.commons.util.StringUtils;

import java.awt.*;
import java.text.*;
import java.util.*;
import java.io.*;


public class Tools {
    public static ArrayList<Currency> Currencies = new ArrayList<>();
    public static ArrayList<Currency> PopularCurrencies = new ArrayList<>();
    public static String[] Popular = new String[4];
    public static Date date;
    public static int checkAdmin = 0;

    public static void UpdateTable(String[] currency){
        int count = 4;
        for (String str: currency){
            for (Currency c: Currencies) {
                if (c.currency.equals(currency)) {
                    count--;
                }
            }
        }
        if (count == 0 ){
            Popular[0] = currency[0];
            Popular[1] = currency[1];
            Popular[2] = currency[2];
            Popular[3] = currency[3];
            System.out.println("Success Update Table.");
        }
        else{
            System.out.println("Failed update table.");
        }
    }


    public static void AddNewRate(String currency, Double rate, Date date){
        int checkExist = 0;
        if (currency.equals("AUD")){
            for (Currency c: Currencies){
                if (c.currency.equals(currency)) {
                    if (c.getRateByDate(date)!= null && c.getRateByDate(date).equals(rate)){
                        checkExist = 1;
                    }
                    else{
                        c.addNewRate(date, rate);
                    }
                }
            }
            if (checkExist == 0) {
                for (Currency c : Currencies) {
                    if (!c.currency.equals("AUD")) {
                        double r = c.getCurrentRate() * rate;
                        c.addNewRate(date, r);
                    }
                }
            }
        }
        else {
            checkExist = 0;
            for (Currency c : Currencies) {
                if(c.currency.equals(currency)) {
                    if (c.getRateByDate(date)!= null && c.getRateByDate(date).equals(rate)){
                        checkExist = 1;
                    }
                    else {
                        c.addNewRate(date, rate);
                    }
                    break;
                }
            }
        }

        if (checkExist == 0){
            System.out.println(String.format("Successful add new rate to %s",currency));
        }
        else{
            System.out.println(String.format("%s rate already exist",currency));
        }
    }

    public static void AddNewCurrency(double rates,String currency,Date rawdate)  {
        int count = 0;
        for (Currency c : Currencies) {//Traverse through the arraylist,if the currency name has a match in the arraylist, increment count by 1.
            if (Objects.equals(c.currency, currency)) {
                count++;
            }
        }
        if (count == 0) {//We only add the new currency if it hasn't appeared in the arraylist,which count should be 0.
            Currency newCurrency = new Currency(currency, rates, rawdate);
            Currencies.remove(0);//Get rid of the old currecy as we only need 4 currencies at one time
            Currencies.add(newCurrency);
            System.out.println(String.format("Successful add new currency %s",currency));
        }
    }

    public static ArrayList<Double> getRatelist(String currency, Date start, Date end) {
        Currency c = null;

        for (Currency a : Currencies) {
            if (a.currency.equals(currency)) {
                c = a;
            }
        }

        if (c != null) {
            ArrayList<Double> rateList = new ArrayList<>();
            // find the start rate
            int dateIndex = 0;
            int endIndex = 0;
            for (Date d : c.dateList) {
                if (start.compareTo(d) == -1) {
                    break;
                }
                dateIndex++;
            }

            for (Date d : c.dateList) {
                if (end.compareTo(d) == -1) {
                    break;
                }
                endIndex++;
            }

            for (int i = dateIndex; i < endIndex; i++) {
                rateList.add(c.getRateByDate(c.dateList.get(dateIndex)));
            }
            return rateList;
        } else {
            System.out.println("Currency not found");
        }
        return null;
    }

    public static Double getAvg(ArrayList <Double> rList) {
        Double sum = 0.0;
        for (Double num : rList) {
            sum += num;
        }
        return sum / rList.size();
    }

    public static Double getMedian(ArrayList <Double> rList) {
        Double median;
        if (rList.size() % 2 != 0) {
            median = rList.get(rList.size()/2);
        } else {
            median = (rList.get(rList.size() / 2) + rList.get((rList.size() / 2) - 1)) / 2;
        }
        return median;
    }

    public static Double getMax(ArrayList <Double> rList) {
        double max  = 0.0;
        for (Double num : rList) {
            if (max < num) {
                max = num;
            }
        }
        return max;
    }

    public static Double getMin(ArrayList <Double> rList) {
        Double min  = rList.get(0);
        for (Double num : rList) {
            if (min > num) {
                min = num;
            }
        }
        return min;
    }

    public static Double getSD(ArrayList <Double> rList) {
        double SD = 0.00;
        Double mean = getAvg(rList);
        for (Double num : rList) {
            SD += Math.pow(num - mean, 2);
        }
        return Math.sqrt(SD / rList.size());
    }

    @Generated
    public static void printFunc(String currency, Date start, Date end, ArrayList <Double> rList, Double avg, Double median, Double max, Double min, Double SD) {
        System.out.printf("Summary of %s between %s and %s: \n\tAll rates: \n\t\t ", currency, start, end);
        for (Double num : rList) {
            System.out.printf("%,.2f ", num);
        }
        System.out.printf("\n\tAverage: %,.2f\n\tMedian:  %,.2f\n\tMaximum: %,.2f\n\tMinimum: %,.2f\n\tStandard Deviation: %,.2f\n", avg, median, max, min, SD);
    }

    @Generated
    public static void printSummary(String currencyA, String currencyB, Date start, Date end) {
        // Summary of currencyA
        ArrayList <Double> rateListA = Tools.getRatelist(currencyA, start, end);
        Double avgA = Tools.getAvg(rateListA);
        Double medianA = Tools.getMedian(rateListA);
        Double maxA = Tools.getMax(rateListA);
        Double minA = Tools.getMin(rateListA);
        Double sdA = Tools.getSD(rateListA);

        // Summary of currencyB
        ArrayList <Double> rateListB = getRatelist(currencyA, start, end);
        Double avgB = Tools.getAvg(rateListA);
        Double medianB = Tools.getMedian(rateListA);
        Double maxB = Tools.getMax(rateListA);
        Double minB = Tools.getMin(rateListA);
        Double sdB = Tools.getSD(rateListA);

        printFunc(currencyA, start, end, rateListA, avgA, medianA, maxA, minA, sdA);
        System.out.println();
        printFunc(currencyB, start, end, rateListB, avgB, medianB, maxB, minB, sdB);
    }

    public static void loadCurrencies(String filename){
        try{
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] StringArray = line.split(",");
                if (StringArray.length != 3){
                    System.out.println("Invalid currency format. The program will continue.");
                    continue;
                }
                else{
                    try{
                        String name = StringArray[0];
                        Double rate = Double.parseDouble(StringArray[1]);
                        date = new SimpleDateFormat("dd/MM/yyyy").parse(StringArray[2]);
                        Currency currency = new Currency(name, rate, date);
                        Tools.Currencies.add(currency);
                    }
                    catch (Exception e){
                        System.out.println("Invalid currency details. The program will continue.");
                        continue;
                    }
                }
                
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File does not exist.");
            e.printStackTrace();
        }
    }

    public static Double convert(Double amount, String inputName, String outputName){
        Currency inputCurrency = null;
        Currency outputCurrency = null;
        for (Currency currency: Tools.Currencies){
            if (inputName.equals(currency.currency)){
                inputCurrency = currency;
            }
            else if (outputName.equals(currency.currency)){
                outputCurrency = currency;
            }
        }
        if (inputCurrency == null || outputCurrency == null){
            System.out.println("At least one currency doesn't exist. Exiting.");
            return 0.0;
        }
        else{
            Double rate = outputCurrency.currentRate / inputCurrency.currentRate;
            Double outputAmount = amount * rate;
            System.out.println(String.format("You have converted %.2f %s to %.2f %s", amount, inputName, outputAmount, outputName));
            return outputAmount;
        }
    }


    public static void check(int position) {
        //String empty = " ";
        String sameCurr = "-";
        StringBuilder output = new StringBuilder();
        Currency curr = PopularCurrencies.get(position);

        for (Currency popular : PopularCurrencies) {
            if (Objects.equals(curr, popular)) {
                output.append(sameCurr);
            }
            else {
                // output of the change of currency rate are wrong
                double rateChange = curr.getComparison();
                if (rateChange != 0.0) {
                    //double changeBetweenCurr =
                    output.append(rateChange);
                    if (rateChange > 1) {
                        System.out.println("c");
                        output.append("(I)");
                    }
                    else if (rateChange < 1) {
                        output.append("(D)");
                    }
                }
                else {
                    output.append(popular.currentRate);  // something wrong here, can not output the unchanged current rate
                }
            }
            output.append("          ");
        }
        System.out.println(output);
    }
    public static void displayTable() {
        String start = "From/To";
        System.out.format("%-16s %-16s %-16s %-16s %-16s\n", start, Popular[0], Popular[1], Popular[2], Popular[3]);
        for (int i = 0; i < 4; i++) {
            String current = Popular[i];
            // match the popular currency to the Currency, assume the input popular currencies are valid
            for (Currency cry : Currencies) {
                if (current.equals(cry.currency)) {
                    PopularCurrencies.add(i, cry);
                    //System.out.println(PopularCurrencies);
                    System.out.format("%-16s",Popular[i]);
                    check(i);
                }
            }
        }
    }


    @Generated
    public static void main(String[] args) throws ParseException {

        // read file to database
        Tools.loadCurrencies("src/main/resource/Database.txt");

        // init popular table
        Popular[0] = Currencies.get(0).getCurrency();
        Popular[1] = Currencies.get(1).getCurrency();
        Popular[2] = Currencies.get(2).getCurrency();
        Popular[3] = Currencies.get(3).getCurrency();

        // simple interface for user
        Scanner scan = new Scanner(System.in);
        System.out.println("Start for currency tools");

        // user login
        System.out.println("Do you want to login as Admin User or Normal User(Admin/Normal)");

        int checklogin = 0;
        while (scan.hasNextLine()) {
            String user = scan.nextLine();
            if (user.equals("Admin")) {
                checkAdmin = 1;
                checklogin = 1;
                break;

            } else if (user.equals("Normal")) {
                checkAdmin = 0;
                checklogin = 1;
                break;
            } else {
                System.out.println("Do you want to login as Admin User or Normal User(Admin/Normal)");
            }
        }
        if (checklogin == 1) {
            System.out.print("Welcome to Currency Tools, You can using following Tools(You can using QUIT to quit this application) :");
            if (checkAdmin == 1) {
                System.out.println("Convert, Display Popular, Summary, Update Popular, Add Currency, Add Rate");
            } else if (checkAdmin == 0) {
                System.out.println("Convert, Display Popular, Summary");
            }

            while (scan.hasNextLine()) {
                String s = scan.nextLine();
                // quit application
                if (s.equals("QUIT")) {
                    break;
                } else if (s.equals("Add Rate") && checkAdmin == 1) {
                    System.out.println("Please input currency: ");
                    String a = scan.nextLine();
                    System.out.println("Please input rate: (two decimal)");
                    String b = scan.nextLine();
                    Double num = Double.parseDouble(b);
                    System.out.println("Please input date:(dd/MM/yyyy)");
                    String c = scan.nextLine();
                    Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(c);
                    Tools.AddNewRate(a, num, date1);
                } else if (s.equals("Update Popular") && checkAdmin == 1) {
                    String[] update = new String[4];
                    System.out.println("Please input first currency: ");
                    String a = scan.nextLine();
                    update[0] = a;
                    System.out.println("Please input second currency: ");
                    String b = scan.nextLine();
                    update[1] = b;
                    System.out.println("Please input third currency: ");
                    String c = scan.nextLine();
                    update[2] = c;
                    System.out.println("Please input forth currency: ");
                    String d = scan.nextLine();
                    update[3] = d;
                    Tools.UpdateTable(update);
                } else if (s.equals("Convert")) {
                    System.out.println("Please input amount of money: ");
                    String a = scan.nextLine();
                    Double na = Double.parseDouble(a);
                    System.out.println("Please input input currency: ");
                    String b = scan.nextLine();
                    System.out.println("Please input output currency: ");
                    String c = scan.nextLine();
                    Tools.convert(na, b, c);
                } else if (s.equals("Display Popular")) {
                    Tools.displayTable();
                } else if (s.equals("Summary")) {
                    System.out.println("Please input first currency: ");
                    String a = scan.nextLine();
                    System.out.println("Please input second currency: ");
                    String b = scan.nextLine();
                    System.out.println("Please input start date:(dd/MM/yyyy)");
                    String c = scan.nextLine();
                    Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(c);
                    System.out.println("Please input end date:(dd/MM/yyyy)");
                    String d = scan.nextLine();
                    Date date3 = new SimpleDateFormat("dd/MM/yyyy").parse(d);
                    Tools.printSummary(a, b, date2, date3);
                } else if (s.equals("Add Currency") && checkAdmin == 1) {
                    System.out.println("Please input currency: ");
                    String a = scan.nextLine();
                    System.out.println("Please input rate: ");
                    String b = scan.nextLine();
                    Double nb = Double.parseDouble(b);
                    System.out.println("Please input date:(dd/MM/yyyy)");
                    String c = scan.nextLine();
                    Date date4 = new SimpleDateFormat("dd/MM/yyyy").parse(c);
                    Tools.AddNewCurrency(nb, a, date4);
                }
                else{
                    System.out.println("Please input valid command");
                }

                System.out.println("Do you want to continue or quit the application?(QUIT for exit; Tools Name for continue)");
                if (checkAdmin == 1) {
                    System.out.println("Convert, Display Popular, Summary, Update Popular, Add Currency, Add Rate");
                } else if (checkAdmin == 0) {
                    System.out.println("Convert, Display Popular, Summary");
                }
            }
        }

        System.out.print("Bye!");
    }
}

