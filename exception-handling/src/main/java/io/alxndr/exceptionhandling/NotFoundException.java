package io.alxndr.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author : Alexander Choi
 * @date : 2021/08/12
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "CAN NOT FOUND...SORRY")
public class NotFoundException extends Exception {

    private final int code;

    public int getCode() {
        return code;
    }

    public NotFoundException(int code, String message) {
        super(message);
        this.code = code;
    }

    public NotFoundException(int code) {
        this.code = code;
    }
}
