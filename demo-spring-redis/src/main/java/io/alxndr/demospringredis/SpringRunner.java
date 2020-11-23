package io.alxndr.demospringredis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SpringRunner implements ApplicationRunner {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set("choi", "최");
        values.set("seung", "승");
        values.set("won", "원");

        Account account = new Account();
        account.setUsername("alxndr");
        account.setPassword("1234");

        Account newAcoount = accountRepository.save(account);
        System.out.println(newAcoount.getUsername());
    }
}
