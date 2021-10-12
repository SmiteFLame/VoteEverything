# Vote Everything

원하는(쓸모 있든 없든) 모든 투표를 만들어서 같이 투표 결과를 볼 수 있는 사이트

## Database

```
docker run -p 43306:3306 --name VoteDB -e MYSQL_ROOT_PASSWORD=toy -e MYSQL_DATABASE="vote" -d mysql:latest --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci

docker run -p 53306:3306 --name UserDB -e MYSQL_ROOT_PASSWORD=toy -e MYSQL_DATABASE="user" -d mysql:latest --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci

```
