package io.alxndr.springquerydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;

@SpringBootApplication
public class SpringQuerydslApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringQuerydslApplication.class, args);
    }

    @Bean
    JPAQueryFactory queryFactory(EntityManager em) {
        return new JPAQueryFactory(em);
    }

}
