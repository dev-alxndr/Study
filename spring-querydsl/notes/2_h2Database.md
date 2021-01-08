# H2

- H2 데이터베이스 설치
개발이나 테스트 용도로 가볍고 편리한 DB, 웹 화면 제공
https://www.h2database.com   
- 다운로드 및 설치   
h2 데이터베이스 버전은 스프링 부트 버전에 맞춘다. 
- 권한 주기: `chmod 755 h2.sh`
- 데이터베이스 파일 생성 방법   
`jdbc:h2:~/querydsl`(최소 한번)
~/querydsl.mv.db 파일 생성 확인
이후 부터는 `jdbc:h2:tcp://localhost/~/querydsl` 이렇게 접속
      
> 참고: H2 데이터베이스의 MVCC 옵션은 H2 1.4.198 버전부터 제거되었습니다. 이후 부터는 옵션 없이 사용하면 됩니다.   
> 주의: 가급적 안정화 버전을 사용하세요. 1.4.200 버전은 몇가지 오류가 있습니다.   
> 현재 안정화 버전은 1.4.199(2019-03-13) 입니다.  
> 다운로드 링크: https://www.h2database.com/html/download.html