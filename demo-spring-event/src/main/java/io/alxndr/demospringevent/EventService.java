package io.alxndr.demospringevent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private Logger logger = LoggerFactory.getLogger(EventService.class);

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    public Long save(EventRequestDto body) {

        Event event = new Event();
        event.setId(1L);
        event.setTitle(body.getTitle());
        event.setContent(body.getContent());
        event.setWriter(body.getWriter());

        applicationEventPublisher.publishEvent(new MyEvent(this, event));
        // Repository save ..생략

        return event.getId();
    }

    public void print() {
        System.out.println("CALLED SERVICE");
    }

}
