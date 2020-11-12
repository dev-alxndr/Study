package io.alxndr.demoexceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alexander
 * @date 2020-11-12
 **/
@RestController
public class IndexController {


    @GetMapping
    public String index() {
        return "Hello ExceptionHandler Example";
    }

    @GetMapping("nfe")
    public String notFound() {
        throw new NotFoundException(ErrorCode.INDEX_NOT_FOUND.getErrorCode(), ErrorCode.INDEX_NOT_FOUND.getErrorMessage());
    }


    @ExceptionHandler(NotFoundException.class)
    public ErrorResult notFoundException(NotFoundException e) {
        ErrorResult er = new ErrorResult();
        er.setCode(e.getCode());
        er.setStatus(HttpStatus.NOT_FOUND);
        er.setMessage(e.getMessage());

        return er;
    }

}
