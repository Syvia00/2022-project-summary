package vending;

import org.junit.jupiter.api.Test;
import java.util.*;
import java.text.SimpleDateFormat;
import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {
    Date dateTime = new java.util.Date(System.currentTimeMillis());
    String time = new SimpleDateFormat("HH:mm:ss").format(dateTime);
    String date = new SimpleDateFormat("yyyy/MM/dd").format(dateTime);
    Transaction t = new Transaction("user1", date, time);

    @Test
    void testAmountPaid() {
        boolean check = false;
        t.setAmountPaid(100.0);
        Double ap = t.amountPaid;
        if (ap.equals(100.0)) {
            check = true;
        }
        assertTrue(check, "pass!");
    }

    @Test
    void testSetChange() {
        boolean check = false;
        t.setChange(4.25);
        Double c = t.change;
        if (c.equals(4.25)) {
            check = true;
        }
        assertTrue(check, "pass!");
    }

    @Test
    void testCancelandReason1() {
        boolean check = false;
        if (t.cancel == false && t.reason == null) {
            check = true;
        }
        assertTrue(check, "pass!");
    }

    @Test
    void testCancelandReason2() {
        boolean check = false;
        t.setCancel();
        t.setReason("2 mins timeout");
        if (t.cancel == true && t.reason.equals("2 mins timeout")) {
            check = true;
        }
        assertTrue(check, "pass!");
    }

    @Test
    void testSetMethod1() {
        boolean check = false;
        t.setMethod("Card");
        if (t.cancel == false && t.reason == null && t.method.equals("Card")) {
            check = true;
        }
        assertTrue(check, "pass!");
    }

    @Test
    void testSetMethod2() {
        boolean check = false;
        t.setMethod("Cash");
        if (t.cancel == false && t.reason == null && t.method.equals("Cash")) {
            check = true;
        }
        assertTrue(check, "pass!");
    }

//    @Test
//    void testSetItemList() {
//        boolean check = false;
//        String[] ls = new String[32];
//        String[] items = {"Sprite", "2"};
//        ls[0] = "Sprite";
//        ls[1] = "2";
//        t.setItemList(items);
//        if (t.itemList == ls) {
//            check = true;
//        }
//        assertTrue(check, "pass!");
//    }

    @Test
    void testSetItemNumber() {
        boolean check = false;
        t.setItemNumber(2);
        if (t.itemNumber == 2) {
            check = true;
        }
        assertTrue(check, "pass!");
    }
}
