package io.alxndr.projectimprovements.controller;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Alexander Choi
 * @date : 2021/03/11
 */
@RestController
public class IndexController {

    @GetMapping
    public String index() {
        return  "hello";
    }

}
