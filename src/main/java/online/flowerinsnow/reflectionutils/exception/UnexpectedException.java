package online.flowerinsnow.reflectionutils.exception;

/**
 * <p>Thrown when a problem occurs that shouldn't occur</p>
 */
public class UnexpectedException extends RuntimeException {
    public UnexpectedException() {
        super();
    }

    public UnexpectedException(String message) {
        super(message);
    }

    public UnexpectedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexpectedException(Throwable cause) {
        super(cause);
    }
}
