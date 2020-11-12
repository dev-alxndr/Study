package io.alxndr.demoexceptionhandler;

/**
 * @author Alexander
 * @date 2020-11-12
 **/
public class NotFoundException extends RuntimeException {

    public final static int INDEX_NOT_FOUND = 101;
    public final static int BOARD_NOT_FOUND = 201;

    private final int code;

    public int getCode() {
        return code;
    }

    public NotFoundException(int code) {
        this.code = code;
    }

    public NotFoundException(int code, String message) {
        super(message);
        this.code = code;
    }
}
