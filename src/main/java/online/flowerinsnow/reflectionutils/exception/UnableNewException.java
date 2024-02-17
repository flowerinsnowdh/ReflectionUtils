package online.flowerinsnow.reflectionutils.exception;

/**
 * <p>Thrown when a constructor unable to new</p>
 * <p>Include but not limit the class object represents an abstract class, an interface, an array class, a primitive type, or void</p>
 * @see InstantiationException
 */
public class UnableNewException extends RuntimeException {
    public UnableNewException() {
        super();
    }

    public UnableNewException(String message) {
        super(message);
    }

    public UnableNewException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnableNewException(Throwable cause) {
        super(cause);
    }
}
