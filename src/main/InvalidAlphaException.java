package main;

public class InvalidAlphaException extends RuntimeException {
    public InvalidAlphaException() {
        super("main.Utilities of alph and 26 must be 1");
    }
}
