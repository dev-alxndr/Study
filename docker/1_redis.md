# Docker 

## Redis Example
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
127.0.0.1:6379> keys *
```
- `-i`(interactive), `-t`(Pseudo-tty) 옵션을 사용하면 실행된 Bash 셀에 입력 및 출력 가능
- `exec` 명령을 이용해 2번에서 만든 Container에 접속
- `--restart unless-stopped` Docker Container를 자동으로 재시작하고 싶다면 옵션 추가
- `redis-cli`
- `redis-cli` 접속 확인
- `/bin/bash` 말고 `redis-cli`로 연결하면 바로 `redis-cli`로 연결됨


---
## References
- [Docker Docs](https://docs.docker.com/)
- [Useful Command Lines](https://www.daleseo.com/docker-containers/)
- [가장 빨리 만나는 Dcoker - BOOK](http://pyrasis.com/docker.html)