package io.alxndr.demoexceptionhandler;

import org.springframework.http.HttpStatus;

/**
 * @author Alexander
 * @date 2020-11-12
 **/
public class ErrorResult {

    private int code;

    private HttpStatus status;

    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
