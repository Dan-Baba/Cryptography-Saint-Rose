import java.math.BigInteger;

public class Utilities {

    public static int calcGCD(int a, int b) {
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }

        int q, r;

        while(true) {
            q = a / b;
            r = a % b;

            if (r == 0) {
                return b;
            }

            a = b;
            b = r;
        }
    }

    public static int modularExponentiation(int x, int y, int n) {
        String binaryString = Integer.toBinaryString(x);
        int w = binaryString.length();
        int[] r = new int[w];
        int[] s = new int[w+1];
        s[0] = 1;

        for (int k = 0; k < w; k++) {

            // Step 2
            if (binaryString.charAt(k) == '1') {
                r[k] = s[k] * y % n;
            } else {
                r[k] = s[k];
            }

            // Step 3
            s[k+1] = r[k] * r[k] % n;
        }

        return r[w-1];
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


}
