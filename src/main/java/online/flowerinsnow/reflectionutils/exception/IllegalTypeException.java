package online.flowerinsnow.reflectionutils.exception;

/**
 * <p>Thrown when type and instance not matched.</p>
 */
public class IllegalTypeException extends RuntimeException {
    public IllegalTypeException() {
        super();
    }

    public IllegalTypeException(String message) {
        super(message);
    }

    public IllegalTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalTypeException(Throwable cause) {
        super(cause);
    }
}
