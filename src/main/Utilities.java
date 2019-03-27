package main;

import java.math.BigInteger;
import java.util.Random;

public class Utilities {

    public static int calcGCD(int a, int b) {
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }

        int r;

        while(true) {
            r = a % b;

            if (r == 0) {
                return b;
            }

            a = b;
            b = r;
        }
    }

    // Input is: (y^x mod n)
    public static int modularExponentiation(int x, int y, int n) {
        String binaryString = Integer.toBinaryString(x);
        int w = binaryString.length();
        long r = 0;
        long s = 1;

        for (int k = 0; k < w; k++) {

            // Step 2
            if (binaryString.charAt(k) == '1') {
                r = Math.floorMod(s * y, n);
            } else {
                r = s;
            }

            // Step 3
            s = Math.floorMod(r * r, n);
        }

        return (int)r;
    }

    public static int modularInverse(int a, int b) {
        return BigInteger.valueOf(a).modInverse(BigInteger.valueOf(b)).intValue();
    }

    // Example input
    // 3 mod 7 (a mod m)
    // 5 mod 15 (b mod n)
    public static int chineseRemainderTheorem(int a, int m, int b, int n) {
        // Check GCD(m, n) = 1
        if (calcGCD(m, n) != 1) {
            return -1;
        }
        int newMod = m * n;
        // Solve for k: b + nk = a mod m
        int k = (a - b) * modularInverse(n, m);
        // Solve for x: x = b + nk mod (m * n)
        int x = (b + n * k) % newMod;

        if (x < 0) {
            x = x + newMod;
        }

        return x;
    }

    // Euler's phi function
    public static int phiFunction(int n) {
        int num = 0;
        for (int i = 1; i <= n; i++) {
            if (calcGCD(n, i) == 1) {
                num++;
            }
        }
        return num;
    }

    //Miller-Rabin primality test
    public static boolean millerRabinPrimality(int n) {
        if (n == 1)
            return false;
        if (n == 2 || n == 3)
            return true;
        if (n % 2 == 0)
            return false;
        // find k and m.
        int k = 1;
        int m = (n-1)/2;
        while ((n-1) != (Math.pow(2, k) * m)) {
            k++;
            m = (int)((n-1) / Math.pow(2, k));
        }

        //find a where 1 < a < n-1
        Random random = new Random();
        int a = random.nextInt(n-3) + 2;

        // find b_0
        int b = modularExponentiation(m, a, n);
        if (b == 1 || b == n-1) {
            return true;
        }

        // Otherwise b_1 = b_0^2 mod n
        b = modularExponentiation(2, b, n);
        if (b == 1)
            return false;
        else if (b == n-1)
            return true;

        // Do iterations b_2 -> b_k-2
        for (int i = 2; i <= k-2; i++) {
            b = modularExponentiation(2, b, n);
            if (b == 1)
                return false;
            else if (b == n-1)
                return true;
        }

        // if b_k-1 = -1 mod n then is composite, else it's prime? Have to ask.
        // ASK ABOUT THIS STATEMENT, I'M CHANGING TO RETURN FALSE ALL THE TIME DOWN HERE.
        b = modularExponentiation(2, b, n);
        if (b == n-1)
            return false;
        else
            return false;
    }

    // TODO: How can I dry this out?
    public static String joinMessage(int[] message, String separator) {
        String joinedMessage = "";
        if (message.length == 0)
            return joinedMessage;

        joinedMessage += message[0];
        for (int i = 1; i < message.length; i++) {
            joinedMessage += separator;
            joinedMessage += message[i];
        }

        return joinedMessage;
    }

    public static String joinMessage(String[] message, String separator) {
        String joinedMessage = "";
        if (message.length == 0)
            return joinedMessage;

        joinedMessage += message[0];
        for (int i = 1; i < message.length; i++) {
            joinedMessage += separator;
            joinedMessage += message[i];
        }

        return joinedMessage;
    }

    public static String joinMessage(char[] message, String separator) {
        String joinedMessage = "";
        if (message.length == 0)
            return joinedMessage;

        joinedMessage += message[0];
        for (int i = 1; i < message.length; i++) {
            joinedMessage += separator;
            joinedMessage += message[i];
        }

        return joinedMessage;
    }


}
