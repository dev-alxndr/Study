package io.alxndr.exceptionhandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author : Alexander Choi
 * @date : 2021/08/12
 */
@ControllerAdvice
public class ExceptionController {

    Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NotFoundException.class)
    public String notFoundHandler(NotFoundException nfe) {
        logger.error(">> {} : {} ", nfe.getCode(), nfe.getMessage());

        return "error/404";
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)    // Error를 잡으면 status200 으로 응답하기에 직접 status를 지정
    @ExceptionHandler(value = NotFoundRunTimeException.class)
    public String notFoundRunTime(NotFoundRunTimeException nfrte) {

        logger.error(">> {} : {}", nfrte.getCode(), nfrte.getMessage());

        return "error/404";
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = RuntimeException.class)
    public String internalServerError(RuntimeException re) {

        logger.error(">> SOMETHING WRONG");

        return "error/500";
    }


}
