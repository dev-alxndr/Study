package io.alxndr.batch.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Alexander Choi
 * @date : 2021/03/12
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class StepNextConditionJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job stepNextConditionalJob() {
        return jobBuilderFactory.get("stepNextConditionalJob")
                .start(conditionalJobStep1())
                .on("FAILED")  // Failed 일경우
                .to(conditionalJobStep3()) // Step3로 이동한다.
                .on("*")    //step3의 결과와 관계없이
                .end()  // step3로 이동하면 Flow가 종료된다.
            .from(conditionalJobStep1())    // step1으로 부터
                .on("*")    // Failed 외에 모든 경우
                .to(conditionalJobStep2())  // STEP2로 이동한다.
                .next(conditionalJobStep3())    // Step2가 정상 종료되면 Step3로 이동한다
                .on("*")    // Step3의 결과와 관계없이
                .end()  // Step3로 이동하면 Flow가 종료된다.
            .end()
            .build();
    }

    @Bean
    public Step conditionalJobStep1() {
        return stepBuilderFactory.get("step1")
                .tasklet(((contribution, chunkContext) -> {
                    log.info(">>>>>> THIS is StepNextConditionalJob STEP1");
                    /**
                     * ExitStatus를 FAILED로 지정한다.
                     * 해당 Status를 보고 Flow가 진행된다.
                     */
                    contribution.setExitStatus(ExitStatus.FAILED);  // 1 -> 3
//                    contribution.setExitStatus(ExitStatus.FAILED);    // 1 -> 2 -> 3
                    return RepeatStatus.FINISHED;
                })).build();
    }

    @Bean
    public Step conditionalJobStep2() {
        return stepBuilderFactory.get("conditionalJobStep2")
                .tasklet(((contribution, chunkContext) -> {
                    log.info(">>>>>> THIS is StepNextConditionalJob STEP2");
                    return RepeatStatus.FINISHED;
                })).build();
    }

    @Bean
    public Step conditionalJobStep3() {
        return stepBuilderFactory.get("conditionalJobStep3")
                .tasklet(((contribution, chunkContext) -> {
                    log.info(">>>> This is stepNexConditionalJob STEP3");
                    return RepeatStatus.FINISHED;
                })).build();
    }

}
