package online.flowerinsnow.reflectionutils.exception.notfound;

public class ConstructorNotFoundException extends RuntimeException {
    public ConstructorNotFoundException() {
        super();
    }

    public ConstructorNotFoundException(String message) {
        super(message);
    }

    public ConstructorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConstructorNotFoundException(Throwable cause) {
        super(cause);
    }
}
