package main;

import java.util.Base64;
import java.util.Scanner;

public class Main {

    private static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Which tool would you like to use?");
        System.out.println("1. Shift Cipher");
        System.out.println("2. Affine Cipher");
        System.out.println("3. RSA");
        System.out.println("4. Modular Exponentiation");
        System.out.println("5. Chinese Remainder");
        System.out.println("6. Euler's Phi Function");
        System.out.println("7. Miller-Rabin Primality Test");
        System.out.println("'q' to exit.");

        switch (s.nextLine()) {
            case "1":
                usingShiftCipher();
                break;
            case "2":
                usingAffineCipher();
                break;
            case "3":
                usingRSA();
                break;
            case "4":
                usingModularExponentiation();
                break;
            case "5":
                usingChinesesRemainder();
                break;
            case "6":
                usingPhiFunction();
                break;
            case "7":
                millerRabin();
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

    private static void usingRSA() {
        PublicRSA publicRSA;
        PrivateRSA privateRSA;

        // Test case, 283, 293.
        System.out.println("Using RSA. Please note that (p-1)*(q-1) MUST BE greater than " + PrivateRSA.BLOCK_MAX_VAL);
        System.out.println("What would you like to use as p? (MUST BE PRIME)");
        int p = s.nextInt();
        s.nextLine();
        System.out.println("What would you like to use as q? (MUST BE PRIME)");
        int q = s.nextInt();
        s.nextLine();
        privateRSA = new PrivateRSA(p, q);
        publicRSA = privateRSA.getPublicKey();

        while(true) {
            System.out.println("Input 'e' to encrypt or 'd' to decrypt. '-1' to quit.");
            String input = s.nextLine();

            if (input.toLowerCase().equals("e")) {
                System.out.println("What text would you like to encrypt?");
                String plainText = s.nextLine();
                String encryptedText = publicRSA.encrypt(plainText);
                System.out.println(encryptedText);
            } else if (input.toLowerCase().equals("d")) {
                System.out.println("What text would you like to decrypt?");
                String cipherText = s.nextLine();
                System.out.println(privateRSA.decrypt(cipherText));
            } else if (input.equals("-1")) {
                break;
            } else {
                System.out.println("Sorry, I don't know what you're trying to do.");
            }
        }
    }

    private static void usingModularExponentiation() {
        System.out.println("Using module exponentiation.");

        int x, y, n;
        while(true) {
            System.out.println("Please input y, x, and n separated by spaces (or just -1 to exit).");
            System.out.println("Answer calculated as (y^x mod n)");
            y = s.nextInt();
            if (y == -1)
                break;
            x = s.nextInt();
            n = s.nextInt();
            System.out.println(String.format("%d^%d mod %d = %d", x, y, n, Utilities.modularExponentiation(x, y, n)));
        }
    }

    private static void usingChinesesRemainder() {
        System.out.println("Using Chinese Remainder.");
        // Example input
        // 3 mod 7 (a mod m)
        // 5 mod 15 (b mod n)
        int a, m, b, n;
        while(true) {
            System.out.println("Please input a, m, b, and n separated by spaces (or just -1 to exit).");
            System.out.println("Answer is calculated as (a mod m) and (b mod n)");
            a = s.nextInt();
            if (a == -1)
                break;
            m = s.nextInt();
            b = s.nextInt();
            n = s.nextInt();
            System.out.println(String.format("(%d mod %d) and (%d mod %d) = (%d mod %d)",
                    a, m, b, n, Utilities.chineseRemainderTheorem(a, m, b, n), (m*n)));
        }
    }

    private static void usingPhiFunction() {
        int n;
        while(true) {
            System.out.println("Please input n (or -1 to exit).");
            n = s.nextInt();
            if (n < 0)
                break;
            System.out.println(Utilities.phiFunction(n));
        }
    }

    private static void millerRabin() {
        int n;
        while(true) {
            System.out.println("Please input a number to test (or -1 to exit).");
            n = s.nextInt();
            if (n < 0)
                break;
            if (Utilities.millerRabinPrimality(n))
                System.out.println("Probably prime");
            else
                System.out.println("Composite");
        }
    }
}
