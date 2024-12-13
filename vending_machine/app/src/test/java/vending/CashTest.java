package vending;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class CashTest {
    App app = new App();
    Cash cash = new Cash(5,5,5,5,5,5,5,5,5,5,0);
    Snack s1 = new Snack("Drink", "a", 9, 8.0, 1, 0);
    Snack s2 = new Snack("Drink", "b", 9, 3.5, 2, 0);

    @Test
    void testCashGet() {
        boolean check = false;
        if (cash.get(100.0) == 5) {
            check = true;
        }
        assertTrue(check, "pass!");
    }

    @Test
    void testCashGetD50() {
        boolean check = false;
        if (cash.getD50() == 5) {
            check = true;
        }
        assertTrue(check, "pass!");
    }

    @Test
    void testCashAdd() {
        boolean check = false;
        cash.add(100.0, 2);
        if (cash.get(100.0) == 7) {
            check = true;
        }
        assertTrue(check, "pass!");
    }

    @Test
    void testCashLess() {
        boolean check = false;
        cash.less(100.0, 2);
        if (cash.get(100.0) == 3) {
            check = true;
        }
        assertTrue(check, "pass!");
    }

    @Test
    void testCashModify() {
        boolean check = false;
        cash.modify("100$", 10);
        if (cash.get(100.0) == 10) {
            check = true;
        }
        assertTrue(check, "pass!");
    }

    @Test
    void testCashPayment1() {
        HashMap<Double, Integer> cashIn = new HashMap<Double, Integer>(){{
            put(100.0, 1);
        }};
        app.totalPrice = 100.0;
        boolean check = false;
        Double change = app.cashPayment(cashIn);
        if (change == 0.0) {
            check = true;
        }
        assertTrue(check, "pass!");
    }

    @Test
    void testCashPayment2() {
        HashMap<Double, Integer> cashIn = new HashMap<Double, Integer>(){{
            put(50.0, 1);
        }};
        app.totalPrice = 100.0;
        boolean check = false;
        Double change = app.cashPayment(cashIn);
        if (change == -1.0) {
            check = true;
        }
        assertTrue(check, "pass!");
    }

    @Test
    void testCashPayment3() {
        HashMap<Double, Integer> cashIn = new HashMap<Double, Integer>(){{
            put(50.0, 3);
        }};
        app.totalPrice = 100.25;
        boolean check = false;
        Double change = app.cashPayment(cashIn);
        if (change == 49.75) {
            check = true;
        }
        assertTrue(check, "pass!");
    }

    @Test
    void testCashPayment4() {
        HashMap<Double, Integer> cashIn = new HashMap<Double, Integer>(){{
            put(100.0, 1);
            put(50.0, 1);
        }};
        app.totalPrice = 100.25;
        boolean check = false;
        Double change = app.cashPayment(cashIn);
        if (change == 49.75) {
            check = true;
        }
        assertTrue(check, "pass!");
    }

    @Test
    void testGetTotal() {
        currentBuy b1 = new currentBuy("a", 2);
        currentBuy b2 = new currentBuy("b", 3);
        b1.setPrice(s1.getPrice() * 2);
        b2.setPrice(s2.getPrice() * 3);
        app.buyList.add(b1);
        app.buyList.add(b2);
        boolean check = false;
        app.getTotal();
        if (app.totalPrice.equals(26.5)) {
            check = true;
        }
        assertTrue(check, "pass!");
    }

//    @Test
//    // not enough changes in the machine
//    void testCountChange1() {
//        HashMap<Double, Integer> cashIn = new HashMap<Double, Integer>(){{
//            put(50.0, 3);
//        }};
//        app.totalPrice = 100.25;
//        boolean check = false;
//        Double change = app.cashPayment(cashIn);
//        Integer changeCheck = app.countChange(change);
//        if (changeCheck == 0) {
//            check = true;
//        }
//        assertTrue(check, "pass!");
//    }
//
//    @Test
//    void testCountChange2() {
//        HashMap<Double, Integer> cashIn = new HashMap<Double, Integer>(){{
//            put(100.0, 2);
//        }};
//        app.totalPrice = 122.3;
//        boolean check = false;
//        Double change = app.cashPayment(cashIn);
//        Integer changeCheck = app.countChange(change);
//        if (changeCheck == 1 && app.changes.size() == 6 && app.changes.get(50.0) == 1) {
//            check = true;
//        }
//        assertTrue(check, "pass!");
//    }
}