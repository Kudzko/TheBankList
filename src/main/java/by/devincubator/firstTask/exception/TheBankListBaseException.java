package by.devincubator.firstTask.exception;

public class TheBankListBaseException extends Exception {
    public TheBankListBaseException() {
    }

    public TheBankListBaseException(String message) {
        super(message);
    }

    public TheBankListBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public TheBankListBaseException(Throwable cause) {
        super(cause);
    }

    public TheBankListBaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
