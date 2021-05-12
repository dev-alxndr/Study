
# Mariadb Example

## 1. Pull Mariadb Image
```bash
$docker pull mariadb
```

## 2. Create Mariadb Container
```bash
$docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=<PASSWORD> --restart unless-stopped \
-v <PATH>:/var/lib/mysql \
--name <CONTAINER_NAME>  mariadb \ 
--character-set-server=utf8mb4 \ 
--collation-server=utf8mb4_unicode_ci
```
- `3306:3306` container port와 local port를 연결합니다.
- `--restart unless-stopped` docker가 재시작하면 자동으로 재시작합니다.
- `-v [PATH]:[CONATINER PATH]` DB Volume을 Docker 내부가 아닌 외부로 지정합니다. [참고](https://joonhwan.github.io/2018-11-14-fix-mysql-volume-share-issue/)

## 3. Connect Container
```bash
$docker exec -it <CONTAINER_NAME> bash
$mysql -u root -p # mysql 대신 mariadb도 가능 
> enter password:

MariaDB>> 
```


### References 
- [Docker Hub](https://hub.docker.com/_/mariadb)