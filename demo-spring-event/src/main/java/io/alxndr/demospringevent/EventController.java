package io.alxndr.demospringevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping("/")
    public String index() {
        return "hello EventListner";
    }

    @PostMapping("/")
    public ResponseEntity saveEvent(@RequestBody EventRequestDto body) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        eventService.save(body);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        System.out.println("save success // " + stopWatch.getTotalTimeSeconds());
        return ResponseEntity.ok().body("");
    }




}
