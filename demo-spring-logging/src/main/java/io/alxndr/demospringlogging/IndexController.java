package io.alxndr.demospringlogging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping
    public String index() {
        logger.trace("TRACE LEVEL TEST");
        logger.debug("DEBUG LEVEL TEST");
        logger.info("INFO LEVEL TEST");
        logger.warn("WARN LEVEL TEST");
        logger.error("ERROR LEVEL TEST");

        return "Logging Test";
    }

}
