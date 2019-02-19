import java.util.Scanner;

public class Main {

    private static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Which cipher would you like to use?");
        System.out.println("1. Shift Cipher");
        System.out.println("2. Affine Cipher");
        System.out.println("'q' to exit.");

        switch (s.nextLine()) {
            case "1":
                usingShiftCipher();
                break;
            case "2":
                usingAffineCipher();
                break;
            case "q":
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Don't know what you mean, try re-launching...");
                break;
        }


    }

    private static void usingShiftCipher() {
        ShiftCipher shiftCipher;
        System.out.println("Using Shift Cipher. What would you like k to be?");
        int k = s.nextInt();
        s.nextLine();
        shiftCipher = new ShiftCipher(k);

        String input;
        do {
            System.out.println("Input 'e' to encrypt or 'd' to decrypt. '-1' to quit.");
            input = s.nextLine();

            if (input.toLowerCase().equals("e")) {
                System.out.println("What text would you like to encrypt?");
                String plainText = s.nextLine();
                String encryptedText = shiftCipher.encrypt(plainText);
                System.out.println(encryptedText);
            } else if (input.toLowerCase().equals("d")) {
                System.out.println("What text would you like to decrypt?");
                System.out.println(shiftCipher.decrypt(s.nextLine()));
            } else if (input.equals("-1")) {
                break;
            } else {
                System.out.println("Sorry, I don't know what you're trying to do.");
            }
        } while (true);
    }

    private static void usingAffineCipher() {
        AffineCipher affineCipher;
        System.out.println("Using Affine Cipher.");
        System.out.println("What would you like to use as Alpha?");
        int alpha = s.nextInt();
        s.nextLine();
        System.out.println("What would you like to use as Beta?");
        int beta = s.nextInt();
        s.nextLine();
        affineCipher = new AffineCipher(alpha, beta);

        while(true) {
            System.out.println("Input 'e' to encrypt or 'd' to decrypt. '-1' to quit.");
            String input = s.nextLine();

            if (input.toLowerCase().equals("e")) {
                System.out.println("What text would you like to encrypt?");
                String plainText = s.nextLine();
                String encryptedText = affineCipher.encrypt(plainText);
                System.out.println(encryptedText);
            } else if (input.toLowerCase().equals("d")) {
                System.out.println("What text would you like to decrypt?");
                String cipherText = s.nextLine();
                System.out.println(affineCipher.decrypt(cipherText));
            } else if (input.equals("-1")) {
                break;
            } else {
                System.out.println("Sorry, I don't know what you're trying to do.");
            }
        }
    }
}
