## Docker 

### Redis Example
1. Redis Image Download
```bash
$docker pull redis
```

2. Create Container
```bash
$docker run --name <CONTAINER_NAME> -p 6379:6379 redis
```
- local 6379 port를 Docker Container 6379 Port에 연결한다.



3. Connect to Container
```bash
$docker exec -it <CONTAINER_NAME> /bin/bash
:/data $redis-cli
127.0.0.1:6379>
```
- `-i`(interactive), `-t`(Pseudo-tty) 옵션을 사용하면 실행된 Bash 셀에 입력 및 출력 가능
- `exec` 명령을 이용해 2번에서 만든 Container에 접속
- `redis-cli`
- `redis-cli` 접속 확인
- `/bin/bash` 말고 `redis-cli`로 연결하면 바로 `redis-cli`로 연결됨

4. Container Start/Stop/Restart
```bash
$docker start <CONTAINER_NAME>
$docker stop <CONTAINER_NAME>
$docker restart <CONTAINER_NAME>
```

5. Remove Container
```bash
$docker rm <CONTAINER_NAME>
```

6. Delete Image
```bash
$docker rmi redis
$docekr rmi -f redis
```
- 이미지로 실행 중인 컨테이를 삭제해야 이미지 삭제 가능
- `-f` 옵션을 이용하면 강제 삭제 가능하지만 **비추천**


7. Useful Commands
- `$docker ps (-a)` : 실행중인 컨테이너 목록 (만들어진 컨테이너 포함)



### Mysql Example

1. Download Mysql Image
```bash
$docker pull mysql # not select version
$docker pull mysql:8.0.22 # select version
```
- 다운로드 할 수 있는 Version은 [Docker hub](https://hub.docker.com/_/mysql/?tab=tags)에서 확인 가능하다.


2. Create Mysql Container

```bash
$docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=<PASSWORD> --name <CONTAINER_NAME> m
ysql --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
```

3. Connect to Mysql
```bash
$docker exec -it <CONTAINER_NAME> bash
$root@e1d31c340d09:/# mysql -u root -p
Enter password:
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 8
Server version: 8.0.22 MySQL Community Server - GPL

Copyright (c) 2000, 2020, Oracle and/or its affiliates. All rights reserved.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql>
```

---
## References
- [Docker Docs](https://docs.docker.com/)
- [Useful Command Lines](https://www.daleseo.com/docker-containers/)
- [가장 빨리 만나는 Dcoker - BOOK](http://pyrasis.com/docker.html)