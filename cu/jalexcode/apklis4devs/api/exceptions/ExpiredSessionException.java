package cu.jalexcode.apklis4devs.api.exceptions;

public class ExpiredSessionException extends Error{
    public ExpiredSessionException() {
    }

    public ExpiredSessionException(String detailMessage) {
        super(detailMessage);
    }

    public ExpiredSessionException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public ExpiredSessionException(Throwable throwable) {
        super(throwable);
    }
}
