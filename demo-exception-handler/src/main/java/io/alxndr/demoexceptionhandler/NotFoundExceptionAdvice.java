package io.alxndr.demoexceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Alexander
 * @date 2020-11-12
 **/
@RestControllerAdvice
public class NotFoundExceptionAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ErrorResult notFoundException(NotFoundException e) {
        ErrorResult er = new ErrorResult();
        er.setCode(e.getCode());
        er.setStatus(HttpStatus.NOT_FOUND);
        er.setMessage(e.getMessage());

        return er;
    }

}
