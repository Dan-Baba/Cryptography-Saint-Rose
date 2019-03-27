package main;

public class PublicRSA {
    int n, e;

    public PublicRSA(int n, int e) {
        this.n = n;
        this.e = e;
    }

    public String encrypt(String plainText) {
        char[] message = plainText.toCharArray();
        int[] encryptedMessage = new int[message.length];
        for (int i = 0; i < message.length; i++) {
            encryptedMessage[i] = encode((int)message[i]);
        }

        return Utilities.joinMessage(encryptedMessage, " ");
    }

    public int encode(int character) {
        return Utilities.modularExponentiation(this.e, character, this.n);
    }
}
