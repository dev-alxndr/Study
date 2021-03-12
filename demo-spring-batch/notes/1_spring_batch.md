# Spring batch

[What Is Batch](https://github.com/jojoldu/spring-batch-in-action/blob/master/1_%EB%B0%B0%EC%B9%98%EB%9E%80.md)

`@EnableBatchProcessing` : 배치 활성화   

Spring batch는 메타 데이터 테이블이 필요하다.   
Spring Batch의 메타 데이터는 다음과 같은 내용들을 담고 있습니다.

- 이전에 실행한 Job이 어떤 것들이 있는지
- 최근 실패한 Batch Parameter가 어떤것들이 있고, 성공한 Job은 어떤것들이 있는지
- 다시 실행한다면 어디서 부터 시작하면 될지
- 어떤 Job에 어떤 Step들이 있었고, Step들 중 성공한 Step과 실패한 Step들은 어떤것들이 있는지   

`schema-` 로 검색하면 데이터베이스 별로 `sql`을 찾을 수 있음.


[Meta Table](https://github.com/jojoldu/spring-batch-in-action/blob/master/3_%EB%A9%94%ED%83%80%ED%85%8C%EC%9D%B4%EB%B8%94%EC%97%BF%EB%B3%B4%EA%B8%B0.md)


[BatchJobFlow](https://github.com/jojoldu/spring-batch-in-action/blob/master/4_BATCH_JOB_FLOW.md)   

#### 원하는 배치만 실행
- program argument
    - `-- job.name=stepNextJob`