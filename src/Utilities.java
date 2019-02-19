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


}
