public class ShiftCipher {
    private int k;

    public ShiftCipher(int k) {
        this.k = k;
    }

    public String encrypt(String message) {
        return this.processMessage(message, this.k);
    }

    public String decrypt(String message) {
        return processMessage(message, -this.k);
    }

    private String processMessage(String message, int shiftAmount) {

        char[] chars = message.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (isAlpha(chars[i])) {
                chars[i] = shift(chars[i], shiftAmount);
            }
        }

        return new String(chars);
    }

    private char shift(char character, int amount) {
        int leftBound, rightBound;

        if (isUppercase(character)) {
            leftBound = 65;
            rightBound = 90;
        } else if (isLowercase(character)) {
            leftBound = 97;
            rightBound = 122;
        } else {
            return character;
        }

        int charNum = character - leftBound;
        charNum = Math.floorMod((charNum + amount), 26);
        if (charNum < 0)
            charNum += rightBound;
        else
            charNum += leftBound;
        return (char)charNum;
    }

    private Boolean isAlpha(char character) {
        return isLowercase(character) || isUppercase(character);
    }

    private Boolean isUppercase(char character) {
        int asciiValue = (int) character;
        return (asciiValue >= 65 && asciiValue <= 90);
    }

    private Boolean isLowercase(char character) {
        int asciiValue = (int) character;
        return (asciiValue >= 97 && asciiValue <= 122);
    }
}
