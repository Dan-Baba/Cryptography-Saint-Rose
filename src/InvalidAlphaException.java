public class InvalidAlphaException extends RuntimeException {
    public InvalidAlphaException() {
        super("GCD of alph and 26 must be 1");
    }
}
