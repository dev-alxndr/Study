package io.alxndr.batch.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Alexander Choi
 * @date : 2021/03/04
 */
@Slf4j
@Configuration
@RequiredArgsConstructor    // 생성자 DI를 위한 Lombok Annotation
public class SimpleJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job simpleJob() {
        return jobBuilderFactory.get("simpleJob")   // 1. simpleJob 이란 이름의 Batch Job을 생성합니다.
                .start(simpleStep1())
                .build();
    }

    @Bean
    public Step simpleStep1() {
        return stepBuilderFactory.get("simpleStep1")    // 2. simpleStep1이란 이름의 Step을 생성합니다.
                .tasklet(((contribution, chunkContext) -> { // Step안에 수행될 기능들을 명시합니다.
                    log.info(">>> STEP 1");    // Batch가 수행되면 실행
                    return RepeatStatus.FINISHED;
                }))
                .build();
    }

}
