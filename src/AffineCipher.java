import java.math.BigInteger;

public class AffineCipher {
    private int alpha;
    private int beta;

    public AffineCipher(int alpha, int beta) {
        if (Utilities.calcGCD(alpha, 26) != 1) {
            throw new InvalidAlphaException();
        }
        this.alpha = alpha;
        this.beta = beta;
    }

    public String encrypt(String plainText) {
        char[] chars = plainText.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (Character.isAlphabetic(chars[i])) {
                if (Character.isUpperCase(chars[i])) {
                    chars[i] = encryptCharacter(chars[i], (int)'A', (int)'Z');
                } else if (Character.isLowerCase(chars[i])) {
                    chars[i] = encryptCharacter(chars[i], (int)'a', (int)'z');
                }
            }
        }
        return new String(chars);
    }

    public String decrypt(String cipherText) {
        char[] chars = cipherText.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (Character.isAlphabetic(chars[i])) {
                if (Character.isUpperCase(chars[i])) {
                    chars[i] = decryptCharacter(chars[i], (int)'A', (int)'Z');
                } else if (Character.isLowerCase(chars[i])) {
                    chars[i] = decryptCharacter(chars[i], (int)'a', (int)'z');
                }
            }
        }
        return new String(chars);
    }

    private char encryptCharacter(char letter, int leftBound, int rightBound) {
        int charNum = ((int)letter - leftBound);
        int newCharNum = (charNum * this.alpha + this.beta) % 26;
        return (char)(newCharNum + leftBound);
    }

    private char decryptCharacter(char letter, int leftBound, int rightBound) {
        int charNum = ((int)letter - leftBound);
        int newCharNum = Math.floorMod(((charNum - this.beta) *
                BigInteger.valueOf(this.alpha).modInverse(BigInteger.valueOf(26)).intValue()), 26);
        return (char)(newCharNum + leftBound);
    }
}
