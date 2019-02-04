public class GCD {

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


}
