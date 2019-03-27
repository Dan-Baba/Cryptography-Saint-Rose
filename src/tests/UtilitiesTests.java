package tests;

import main.Utilities;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

public class UtilitiesTests {

    @Test
    void millerRabinPrimality_correct() {
        for (int i = 0; i < 1000; i++) {
            boolean actual = Utilities.millerRabinPrimality(i);
            boolean expected = BigInteger.valueOf(i).isProbablePrime(25);
            if (actual != expected)
                System.out.println("Problem with number: " + i);
        }
    }
}
