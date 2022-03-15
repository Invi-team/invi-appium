package invi.exceptions;

public class IncorrectDeviceFarmUploadException extends RuntimeException{
    public IncorrectDeviceFarmUploadException(String errorMessage) {
        super(errorMessage);
    }
}
