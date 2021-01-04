package io.alxndr.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {

//    Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/")
    public String home() {
        log.info("HOME CONTROLLER");
        return "home";
    }


}
