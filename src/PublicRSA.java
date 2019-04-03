import java.nio.ByteBuffer;
import java.util.Base64;

public class PublicRSA {
    int n, e;

    public PublicRSA(int n, int e) {
        this.n = n;
        this.e = e;
    }

    public String encrypt(String plainText) {
        char[] message = plainText.toCharArray();
        ByteBuffer buffer = ByteBuffer.allocate(message.length * Integer.BYTES);

        for (int i = 0; i < message.length; i++) {
            buffer.putInt((i * 4), encode((int)message[i]));
        }

        return Base64.getEncoder().encodeToString(buffer.array());
    }

    public int encode(int character) {
        return Utilities.modularExponentiation(this.e, character, this.n);
    }
}
