import java.nio.ByteBuffer;
import java.util.Base64;

package main;

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
        ByteBuffer buffer = ByteBuffer.wrap(Base64.getDecoder().decode(cipherText));
        StringBuilder plainText = new StringBuilder();
        while (buffer.hasRemaining()) {
            plainText.append(decode(buffer.getInt()));
        }

        return plainText.toString();
    }

    public char decode(int character) {
        return (char)Utilities.modularExponentiation(this.d, character, this.n);
    }
}
