package io.alxndr.demospringevent;

import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    public void sendKakao() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("SEND COMPLETE KAKAO");
    }

    public void sendEmail() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("SEND COMPLETE EMAIL");
    }

    public void sendPushMessage() {
        System.out.println("PUSH MESSAGE SENDING....");
        throw new RuntimeException("PUSH 메시지 전송 중 에러가 발생하였습니다.");
    }

}
