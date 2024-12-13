package vending;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class CurrentBuyTest {
    currentBuy b1 = new currentBuy("Sprite", 2);
    @Test
    void testSetPrice() {
        boolean check = false;
        b1.setPrice(6.0);
        if (b1.price.equals(6.0)) {
            check = true;
        }
        assertTrue(check, "pass!");
    }
}
