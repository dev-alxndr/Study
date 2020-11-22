package io.alxndr.demospringformatter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable(value = "name") Person person) {
        return "hello " + person.getName() ;
    }

    @GetMapping("/hello")
    public String hello2(@RequestParam("name") Person person) {
        return "hello" + person.getName();
    }

}
