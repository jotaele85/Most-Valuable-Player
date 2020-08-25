package jle.codetest.mvp.exception;

public class MostValuablePlayerException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     * @param message for the exception.
     */
    public MostValuablePlayerException(final String message) {
        super(message);
    }
}
