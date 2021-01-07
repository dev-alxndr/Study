# How to install mysql8.0 on Ubuntu

### 1. MySQL APT Repository 추가
``` shell
> sudo wget https://dev.mysql.com/get/mysql-apt-config_0.8.13-1_all.deb

```
###  2. MySQL APT Repository 패키지 다운로드
```shell
> sudo dpkg -i mysql-apt-config_0.8.13-1_all.deb
```

### 3. MySQL Repository 업데이트
```shell
> sudo apt update
```

### 4. Mysql 8.0 Server 설치
```shell
> sudo apt install mysql-server
```

### 5. 접속
```shell
> sudo mysql -u root -p

Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 11

```

> 기존 root 계정이 localhost만 접속하다면 모든 IP대역을 허용하는 root계정을 새로 만들어야함

## References
[Mysql 설정](https://www.digitalocean.com/community/tutorials/how-to-install-mysql-on-ubuntu-18-04)

[Mysql 외부접속](https://bizadmin.tistory.com/entry/MySql-%EC%99%B8%EB%B6%80-%EC%A0%91%EC%86%8D-%EA%B0%80%EB%8A%A5%ED%95%98%EA%B2%8C-%EC%84%A4%EC%A0%95%ED%95%98%EA%B8%B0)

[Public Key Retrieval is not allowed ERROR](https://joont92.github.io/java/java-mysql-%EC%97%B0%EB%8F%99%EC%8B%9C-%EC%98%A4%EB%A5%98/)
- `allowPublicKeyRetrieval=true&useSSL=false`