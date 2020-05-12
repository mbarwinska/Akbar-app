package pl.barwinscy.Akbarapp.exceptions;

public class PhoneNotFoundException extends RuntimeException {
    public PhoneNotFoundException(String message) {
        super(message);
    }
}
