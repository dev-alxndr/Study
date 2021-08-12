package io.alxndr.exceptionhandling;

/**
 * @author : Alexander Choi
 * @date : 2021/08/12
 */
public class NotFoundRunTimeException extends RuntimeException {

    private final int code;

    public int getCode() {
        return code;
    }

    public NotFoundRunTimeException(int code, String message) {
        super(message);
        this.code = code;
    }

}
