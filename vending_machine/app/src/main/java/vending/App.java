package vending;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.json.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class App {
    public List<User> userList = new ArrayList<User>();
    public List<Transaction> transactionList = new ArrayList<Transaction>();
    public List<Snack> snackList = new ArrayList<Snack>();
    public Double totalPrice = 0.0;
    public List<currentBuy> buyList = new ArrayList<currentBuy>();
    public List<CreditCard> cardList = new ArrayList<CreditCard>();
    public String currentUser;
    public Transaction currentTransaction;
    public Cash cash;
    public List<Cash> cashList = new ArrayList<Cash>();
    public ArrayList<Integer> changesInMachine = new ArrayList<Integer>();
    public HashMap<Double, Integer> cashIn = new HashMap<Double, Integer>();
    public HashMap<Double, Integer> changes = new HashMap<Double, Integer>();
    public ArrayList<Double> denomination = new ArrayList<Double>() {{
        add(100.0);
        add(50.0);
        add(20.0);
        add(10.0);
        add(5.0);
        add(2.0);
        add(1.0);
        add(0.5);
        add(0.2);
        add(0.1);
        add(0.05);
    }};

    ///////////////
    // Load Json //
    ///////////////

    public void readCard(){
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("credit_cards.json");
            List<CreditCard> cards = mapper.readValue(file, new TypeReference<List<CreditCard>>(){});
            cardList = cards;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Read snacks json file
    public void readSnacks() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("Snacks.json");
            List<Snack> snacks = mapper.readValue(file, new TypeReference<List<Snack>>(){});
            snackList = snacks;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Read user json file
    public void readUser() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("Users.json");
            List<User> users = mapper.readValue(file, new TypeReference<List<User>>(){});
            userList = users;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Read transaction json file
    public void readTransaction() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("Transaction.json");
            List<Transaction> transactionss = mapper.readValue(file, new TypeReference<List<Transaction>>(){});
            transactionList = transactionss;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Read cash json file
    public void readCash() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("Cash.json");
            List<Cash> cashs = mapper.readValue(file, new TypeReference<List<Cash>>(){});
            cashList = cashs;
            this.cash = this.cashList.get(0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Read currentBuy file
    public void readBuy() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("CurrentBuy.json");
            List<currentBuy> buys = mapper.readValue(file, new TypeReference<List<currentBuy>>(){});
            this.buyList = buys;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // add user file
    public void addUser(){
        try{
//            if (userList.isEmpty()) {
//                readUser();
//            }
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("Users.json");
            mapper.writeValue(file, this.userList);
        }
        catch (Exception e){
            System.out.println("Could not add user to database.");
            System.out.print(e.getStackTrace());
        }
    }

    // add snacks file
    public void addSnack(){
        try{
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("Snacks.json");
            mapper.writeValue(file, this.snackList);
        }
        catch (Exception e){
            System.out.println("Could not add Snack to database.");
            System.out.print(e.getStackTrace());
        }
    }

    // add transaction file
    public void addTransaction(){
        try{
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("Transaction.json");
            mapper.writeValue(file, this.transactionList);
        }
        catch (Exception e){
            System.out.println("Could not add Transaction to database.");
            System.out.print(e.getStackTrace());
        }
    }

    // add cash file
    public void addCash(){
        try{
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("Cash.json");
            mapper.writeValue(file, this.cashList);
        }
        catch (Exception e){
            System.out.println("Could not add Cash to database.");
            System.out.print(e.getStackTrace());
        }
    }

    // add total file
    public void addBuy(){
        try{
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("CurrentBuy.json");
            mapper.writeValue(file, this.buyList);
        }
        catch (Exception e){
            System.out.println("Could not add Buylist to database.");
            System.out.print(e.getStackTrace());
        }
    }

    //////////////////
    // Default Page //
    //////////////////

    // Display all Snacks By category
    public List<Snack> displaySnacksClass(String category){
        List<Snack> snackLs = new ArrayList<Snack>();
        for (Snack s : snackList) {
            if (s.getCategory().equals(category)) {
                snackLs.add(s);
            }
        }
        return snackLs;
    }

    public List<String> displaySnacksByCategory(String category){
        List<String> snackNames = new ArrayList<>();
        for (Snack s : snackList) {
            if (s.getCategory().equals(category)) {
                snackNames.add(s.getName());
            }
        }
        return snackNames;
    }

    // list 5 bought product name
    public List<String> displayBoughtItems(){
        Integer count = 0;
        List<String> boughtList = new ArrayList<>();
        if (!transactionList.isEmpty()){
            Transaction cur = transactionList.get(transactionList.size() - 1);
            currentUser = cur.name;
            for (Transaction t : transactionList) {
                if (!t.equals(transactionList.get(transactionList.size() - 1))){
                    if (t.cancel == false && t.name.equals(currentUser)) {
                        for (int i = 0; i < t.itemList.length/2; i++) {
                            if (t.itemList[i * 2] != null) {
                                boughtList.add(t.itemList[i * 2]);
                                count++;
                            }
                            if (count == 5) {
                                break;
                            }
                        }
                    }
                }
                if (count == 5) {
                    break;
                }
            }
        }
        // user bought before
        return boughtList;
    }

    ////////////////
    // Login Page //
    ////////////////

    public Integer checkLogin(String userName, String password){
        // return 1, username and password match, log in successfully
        // return -1, username correct, password incorrect
        // return 0, empty user list
        Integer checkValue = 0;
        if (userList.size() == 0){
            checkValue = 0;
        }
        else {
            for (User u : userList) {
                if (u.getName().equals(userName)) {
                    if (u.getPassword().equals(password)) {
                        checkValue = 1;
                        u.Login();
                        this.addUser();
                    } else {
                        checkValue = -1;
                    }
                    break;
                }
            }
        }
        return checkValue;
    }

    public void findLogin(){
        int checkAnonymous = 0;
        for (User u:userList){
            if (u.login == true){
                currentUser = u.getName();
                checkAnonymous = 1;
                break;
            }
        }
        if (checkAnonymous == 0){
            currentUser = "Anonymous";
        }
    }

    public void Logout(){
        findLogin();
        for (User u: userList){
            if (u.getName().equals(currentUser)){
                u.Logout();
                this.addUser();
                buyList.clear();
                this.addBuy();
                break;
            }
        }
        if (currentUser.equals("Anonymous")) {
            this.buyList.clear();
            this.addBuy();
        }
    }

    /////////////////
    // Create Page //
    /////////////////
    public Integer checkCreate(String userName, String password){
        // return -1, name existed
        // return 1, user created successfully
        Integer checkValue = 1;
        if (userList.size() == 0){
            User newUser = new User(userName,password,"customer");
            newUser.Login();
            userList.add(newUser);
            this.addUser();
        }
        else {
            for (User u : userList) {
                if (u.getName().equals(userName)) {
                    checkValue = -1;
                    break;
                }
            }
            if (checkValue == 1) {
                User newUser = new User(userName, password, "customer");
                newUser.Login();
                userList.add(newUser);
                this.addUser();
            }
        }
        return checkValue;
    }

    ///////////////////
    // Purchase Page //
    ///////////////////


    public void startTransaction(String name){
        Date dateTime = new java.util.Date(System.currentTimeMillis());
        String time = new SimpleDateFormat("HH:mm:ss").format(dateTime);
        String date = new SimpleDateFormat("yyyy/MM/dd").format(dateTime);
        currentTransaction = new Transaction(name,date,time);
        transactionList.add(currentTransaction);
        this.addTransaction();
    }

    public void addPurchaseItems(String chosenSnack, Integer amount){
        currentBuy c = new currentBuy(chosenSnack, amount);
        Double currentPrice = 0.0;
        for (Snack s : snackList) {
            if (s.getName() == chosenSnack) {
                currentPrice = s.getPrice() * amount;
                break;
            }
        }
        c.setPrice(currentPrice);
        buyList.add(c);
        this.addBuy();
    }

    public void cancelPurchase(String reason){
        Transaction cur = transactionList.get(transactionList.size() - 1);
        cur.setCancel();
        cur.setReason(reason);
        this.addTransaction();
    }

    //////////////////
    // Payment Page //
    //////////////////

    public void getTotal() {
        totalPrice = 0.0;
        for (currentBuy c: buyList){
            totalPrice += c.price;
        }
    }

    public Double cashPayment(HashMap<Double, Integer> cashIn) {
        // calculate the total cash user put in
        Double totalCashIn = 0.0;
        for (Double i : cashIn.keySet()) {
            totalCashIn += i * cashIn.get(i);
        }
        // if change required
        if (totalCashIn > totalPrice) {
            Double change = totalCashIn - totalPrice;
            return change;
        }
        // if no change required
        else if (totalCashIn.equals(totalPrice)) {
            return 0.0;
        }
        // if cash in not enough
        else {
            return -1.0;
        }
    }

    public Integer countChange(Double change) {
        changes.clear();
        for (Double i : denomination) {
            if (change.equals(0.0)) {
                break;
            }
            if (change >= i) {
                int amount = (int)(change / i);
                if (amount <= cash.get(i)) {
                    change -= amount * i;
                    String str = String.format("%.2f", change);
                    change = Double.parseDouble(str);
                    changes.put(i, amount);
                }
            }
        }
        if (change.equals(0.0)) {
            for (Double j : changes.keySet()) {
                cash.less(j, changes.get(j));
            }
            for (Double j : cashIn.keySet()) {
                cash.add(j, cashIn.get(j));
            }
            cashList.set(0,cash);
            addCash();
            return 1;
        }
        return 0;
    }

    public Integer checkCard(String holder, String number){
        Integer checkValue = 0;
        for (CreditCard u: cardList){
            if (holder.equals(u.name)){
                if (number.equals(u.number)){
                    checkValue = 1;
                }
                else{
                    checkValue = -1;
                }
            }
        }
        return checkValue;
    }

    public String findHolder(){
        findLogin();
        String holder = null;
        for (User u: userList){
            if (u.getName().equals(currentUser) && u.haveCard == true){
                holder = u.holder;
                break;
            }
        }
        return holder;
    }

    public String findNumber(String holder){
        String number = "";
        for (CreditCard c: cardList){
            if (holder.equals(c.name)){
                number = c.number;
            }
        }
        return number;
    }

    public void completePurchase(String method, Double change){
        Transaction cur = this.transactionList.get(transactionList.size() - 1);
        cur.setMethod(method);
        this.getTotal();
        cur.setAmountPaid(totalPrice);
        cur.setChange(change);
        cur.setChanges(changes);
        String[] items = new String[32];
        cur.setItemNumber(buyList.size());
        String is = "";
        int p = 0;
        for (currentBuy c: buyList){
            for (Snack s: snackList){
                if (s.getName().equals(c.name)){
                    s.changeAmount(-c.amount);
                    s.changeSold(c.amount);
                    break;
                }
            }
            items[p] = c.name;
            items[p+1] = (String.format("%d",c.amount));
            p = p + 2;
            is += c.name;
            is += " ";
        }
        cur.setItemList(items);
        cur.setItems(is);
        this.addTransaction();
        this.addSnack();
    }

    //////////////////
    // Summary Page //
    //////////////////

    public String summary() {
        Transaction cur = transactionList.get(transactionList.size() - 1);
        String changeSum = "";
        for (Double i : cur.changes.keySet()) {
            String add = "";
            if (i.equals(100.0)) {
                add = String.format("100$: %d\n", cur.changes.get(i));
            } else if (i.equals(50.0)) {
                add = String.format("50$: %d\n", cur.changes.get(i));
            } else if (i.equals(20.0)) {
                add = String.format("20$: %d\n", cur.changes.get(i));
            } else if (i.equals(10.0)) {
                add = String.format("10$: %d\n", cur.changes.get(i));
            } else if (i.equals(5.0)) {
                add = String.format("5$: %d\n", cur.changes.get(i));
            } else if (i.equals(2.0)) {
                add = String.format("2$: %d\n", cur.changes.get(i));
            } else if (i.equals(1.0)) {
                add = String.format("1$: %d\n", cur.changes.get(i));
            } else if (i.equals(0.5)) {
                add = String.format("50c: %d\n", cur.changes.get(i));
            } else if (i.equals(0.2)) {
                add = String.format("20c: %d\n", cur.changes.get(i));
            } else if (i.equals(0.1)) {
                add = String.format("10c: %d\n", cur.changes.get(i));
            } else if (i.equals(0.05)) {
                add = String.format("5c: %d\n", cur.changes.get(i));
            }
            changeSum += add;
        }
        if (cur.method.equals("card")) {
            return String.format("You hava purchase %d products\nTotal: %.2f\n", cur.itemNumber, cur.amountPaid);
        } else {
            return String.format("You hava purchase %d products\nTotal: %.2f\nChange: %.2f\n%s", cur.itemNumber, cur.amountPaid, cur.change, changeSum);
        }
    }

    ////////////////
    // Owner Page //
    ////////////////

    public Integer removeUser(String name){
        Integer checkExist = 0;
        int l = 0;
        for (int i = 0; i < userList.size(); i++ ){
            if (userList.get(i).getName().equals(name)){
                if (!userList.get(i).getType().equals("customer")){
                    l = i;
                    checkExist = 1;
                    break;
                }
            }
        }
        if (checkExist == 1){
            userList.remove(l);
            this.addUser();
        }
        return checkExist;
    }

    public Integer addUserOwner(String userName, String password, String type){
        Integer checkValue = 1;
        for (User u: userList){
            if (u.getName().equals(userName)){
                checkValue = -1;
                break;
            }
        }
        if (type == "customer"){
            checkValue = 0;
        }
        if (!type.equals("seller") && !type.equals("cashier") && !type.equals("owner")){
            checkValue = 0;
        }
        if (checkValue == 1){
            User newUser = new User(userName,password,type);
            this.userList.add(newUser);
            this.addUser();
        }
        return checkValue;
    }


    ///////////////////
    // Main Function //
    ///////////////////
    public void init(){
//        Cash cash = new Cash(5,5,5,5,5,5,5,5,5,5,5);
//        this.cashList.add(cash);
//        this.addCash();
//        User user1 = new User("ab123","123","customer");
//        User user2 = new User("zzz","123","seller");
//        User user3 = new User("pop","123","owner");
//        this.userList.add(user1);
//        this.userList.add(user2);
//        this.userList.add(user3);
//        this.addUser();
        // load database file
        this.readCard();
        this.readUser();
        this.readTransaction();
        this.readSnacks();
        this.readCash();
        this.readBuy();
    }
    public static void main(String[] args){

    }
}
