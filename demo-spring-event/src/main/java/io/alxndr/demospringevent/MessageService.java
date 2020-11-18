package io.alxndr.demospringevent;

import org.springframework.stereotype.Service;

@Service
public class MessageService {
    public void sendKakao() {
        System.out.println("SEND KAKAO MESSAGE");
    }

    public void sendEmail() {
        System.out.println("SEND EMAIL");
    }
}
