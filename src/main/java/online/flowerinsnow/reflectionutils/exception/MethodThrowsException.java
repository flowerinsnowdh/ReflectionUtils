package online.flowerinsnow.reflectionutils.exception;

/**
 * <p>Thrown when an exception throws when methods invoke</p>
 * @see java.lang.reflect.InvocationTargetException
 */
public class MethodThrowsException extends RuntimeException {
    public MethodThrowsException() {
        super();
    }

    public MethodThrowsException(String message) {
        super(message);
    }

    public MethodThrowsException(String message, Throwable cause) {
        super(message, cause);
    }

    public MethodThrowsException(Throwable cause) {
        super(cause);
    }
}
