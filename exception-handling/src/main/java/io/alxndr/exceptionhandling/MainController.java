package io.alxndr.exceptionhandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : Alexander Choi
 * @date : 2021/08/12
 */
@Controller
public class MainController {

    Logger logger = LoggerFactory.getLogger(MainController.class);

//    @ExceptionHandler(value = NotFoundException.class)
//    public String notFoundHandler(NotFoundException nfe) {
//        logger.error("못찾았다!!!");
//
//        return "error/404";
//    }


    @GetMapping
    public String index(Model model) {

        return "index";
    }

    @GetMapping("/not")
    public String notFound() throws NotFoundException {

        throw new NotFoundException(100, "어디갔지..");
    }

    @GetMapping("/run")
    public String runtimeNotFound() {
        throw new NotFoundRunTimeException(1010, "두둥탁");
    }

    @GetMapping("/internal")
    public String runtimeError() {
        throw new RuntimeException("문제발생!!!!!");
    }

}
