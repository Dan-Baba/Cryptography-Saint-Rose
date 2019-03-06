public class PrivateRSA {
    int p, q, d;
    int n, e;

    // Input 2 primes
    public PrivateRSA(int p, int q) {
        this.p = p;
        this.q = q;
        this.n = p*q;
        int intermediate = (p-1)*(q-1);
        this.e = intermediate - 1;
        while (true) {
            if (Utilities.calcGCD(e, intermediate) == 1) {
                break;
            } else {
                this.e--;
            }
        }
        this.d = Utilities.modularInverse(this.e, intermediate);
    }

    public PublicRSA getPublicKey() {
        return new PublicRSA(n, d);
    }

    public String decrypt(String cipherText) {
        char[] message = cipherText.toCharArray();

        for (int i = 0; i < message.length; i++) {
            message[i] = (char)(message[i]);
        }

        return new String(message);
    }

    public int decode(Character character) {
        int charVal = (int)character;
        return Utilities.modularExponentiation(charVal, this.d, this.n);
    }
}
