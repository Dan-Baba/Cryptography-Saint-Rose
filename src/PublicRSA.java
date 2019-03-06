public class PublicRSA {
    int n, e;

    public PublicRSA(int n, int e) {
        this.n = n;
        this.e = e;
    }

    public String encrypt(String plainText) {
        char[] message = plainText.toCharArray();

        for (int i = 0; i < message.length; i++) {
            message[i] = (char)encode(message[i]);
        }

        return new String(message);
    }

    public int encode(Character character) {
        int charVal = (int) character;
        return Utilities.modularExponentiation(charVal, this.e, this.n);
    }
}
