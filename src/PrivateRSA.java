public class PrivateRSA {
    public static int BLOCK_MAX_VAL = 65535;
    int p, q, d;
    int n, e;

    // Input 2 primes
    public PrivateRSA(int p, int q) {
        this.p = p;
        this.q = q;
        this.n = p*q;
        int intermediate = (p-1)*(q-1);
        if (intermediate <= BLOCK_MAX_VAL)
            throw new RuntimeException("p and q aren't big enough to store characters.");
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
        return new PublicRSA(n, e);
    }

    public String decrypt(String cipherText) {
        String[] cipherBlocks = cipherText.split(" ");
        char[] plainText = new char[cipherBlocks.length];
        int cipherBlock;
        for (int i = 0; i < cipherBlocks.length; i++) {
            try {
                cipherBlock = Integer.parseInt(cipherBlocks[i]);
            } catch (Exception e) {
                System.out.println("What you gave me to decrypt isn't right. I guess I'll die.");
                throw new RuntimeException("Dead");
            }
            plainText[i] = decode(cipherBlock);
        }

        return Utilities.joinMessage(plainText, "");
    }

    public char decode(int character) {
        return (char)Utilities.modularExponentiation(this.d, character, this.n);
    }
}
