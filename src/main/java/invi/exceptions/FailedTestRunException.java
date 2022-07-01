package invi.exceptions;

public class FailedTestRunException extends RuntimeException {
    public FailedTestRunException(String message) {
        super(message);
    }
}
