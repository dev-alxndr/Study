
# Mariadb Example

## 1. Pull Mariadb Image
```bash
$docker pull mariadb
```

## 2. Create Mariadb Container
```bash
$docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=<PASSWORD> --restart unless-stopped \
--name <CONTAINER_NAME>  mariadb \ 
--character-set-server=utf8mb4 \ 
--collation-server=utf8mb4_unicode_ci
```
- `3306:3306` container port와 local port를 연결합니다.
- `--restart unless-stopped` docker가 재시작하면 자동으로 재시작합니다.

## 3. Connect Container
```bash
$docker exec -it <CONTAINER_NAME> bash
$mysql -u root -p # mysql 대신 mariadb도 가능 
> enter password:

MariaDB>> 
```


### References 
- [Docker Hub](https://hub.docker.com/_/mariadb)