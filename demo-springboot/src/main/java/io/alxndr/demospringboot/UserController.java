package io.alxndr.demospringboot;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * HttpMessageConverters
 * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/converter/HttpMessageConverter.html
 *
 * 스프링 프레임워크에서 제공하는 인터페이스
 * mvc에 일부분
 *
 * http 본문에 들어오넌걸 객체로 변경 or
 * 리스폰스를 json으로 변환
 *
 */
@RestController
public class UserController {

    @PostMapping("user")
    public @ResponseBody User create(@RequestBody User user) {
        /*
        요청이 json이고 요청타입이 contenttype = JSOn 이면 JSONMessageConverter
        리턴할 때 user라는 composition 객체를 jsonMessageConverter를 사요하게된다.

        String 하나인 경우 StringMessageConverter를 사용한다.

        RestController를 사용시에는 resonseBody 생략가능
        그냥 controller인 경우 responseBody를 붙여줘야된다.
        안붙일경우 view/nameresolver를 찾게된다.
         */

        return user;
    }
}
