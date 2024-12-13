package vending;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class SnackTest {
    Snack snackTest = new Snack("Drink", "a", 9, 8.0, 1, 0);

    @Test
    void testGetSnackName() {
        boolean check = false;
        if (snackTest.getName().equals("a")){
            check = true;
        }
        assertTrue(check,"pass!");
    }

    @Test
    void testGetCategory() {
        boolean check = false;
        if (snackTest.getCategory().equals("Drink")){
            check = true;
        }
        assertTrue(check,"pass!");
    }

    @Test
    void testGetPrice() {
        boolean check = false;
        if (snackTest.getPrice() == 8.0){
            check = true;
        }
        assertTrue(check,"pass!");
    }

    @Test
    void testGetAmount() {
        boolean check = false;
        if (snackTest.getAmount() == 9){
            check = true;
        }
        assertTrue(check,"pass!");
    }

    @Test
    void testGetCode() {
        boolean check = false;
        if (snackTest.getCode() == 1){
            check = true;
        }
        assertTrue(check,"pass!");
    }

    @Test
    void testChangeAmount() {
        boolean check = false;
        snackTest.changeAmount(-3);
        if (snackTest.getAmount() == 6){
            check = true;
        }
        assertTrue(check,"pass!");
    }

    @Test
    void testChangePrice() {
        boolean check = false;
        snackTest.changePrice(9.9);
        if (snackTest.getPrice().equals(17.9)){
            check = true;
        }
        assertTrue(check,"pass!");
    }

    @Test
    void testChangeCode() {
        boolean check = false;
        snackTest.changeCode(100);
        if (snackTest.getCode() == 100){
            check = true;
        }
        assertTrue(check,"pass!");
    }

    @Test
    void testChangeCategory() {
        boolean check = false;
        snackTest.changeCategory("ll");
        if (snackTest.getCategory().equals("ll")){
            check = true;
        }
        assertTrue(check,"pass!");
    }

    @Test
    void testChangeName() {
        boolean check = false;
        snackTest.changeName("as");
        if (snackTest.getName().equals("as")){
            check = true;
        }
        assertTrue(check,"pass!");
    }

    @Test
    void testChangeSold() {
        boolean check = false;
        snackTest.changeSold(3);
        if (snackTest.sold == 3){
            check = true;
        }
        assertTrue(check,"pass!");
    }
}
