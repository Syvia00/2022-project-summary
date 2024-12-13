package vending;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {
    User userTest = new User("a","b","c");

    @Test
    void testGetUserName() {
        boolean check = false;
        if (userTest.getName().equals("a")){
            check = true;
        }
        assertTrue(check,"pass!");
    }

    @Test
    void testGetPassword() {
        boolean check = false;
        if (userTest.getPassword().equals("b")){
            check = true;
        }
        assertTrue(check,"pass!");
    }

    @Test
    void testGetType() {
        boolean check = false;
        if (userTest.getType().equals("c")){
            check = true;
        }
        assertTrue(check,"pass!");
    }

    @Test
    void testAddCard() {
        boolean check = false;
        userTest.addCard("mm");
        if (userTest.holder.equals("mm")){
            check = true;
        }
        assertTrue(check,"pass!");
    }

    @Test
    void testRemoveCard() {
        boolean check = false;
        userTest.removeCard();
        if (userTest.haveCard == false){
            check = true;
        }
        assertTrue(check,"pass!");
    }

    @Test
    void testLogin() {
        boolean check = false;
        userTest.Login();
        if (userTest.login == true){
            check = true;
        }
        assertTrue(check,"pass!");
    }

    @Test
    void testLogout() {
        boolean check = false;
        userTest.Logout();
        if (userTest.login == false){
            check = true;
        }
        assertTrue(check,"pass!");
    }
}
