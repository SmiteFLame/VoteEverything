# Vote Everything

원하는(쓸모 있든 없든) 모든 투표를 만들어서 같이 투표 결과를 볼 수 있는 사이트

## Database

### Vote

```MySQL
docker run -p 43306:3306 --name VoteDB -e MYSQL_ROOT_PASSWORD=toy -e MYSQL_DATABASE="vote" -d mysql:latest --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci

```

### User

```MySQL
docker run -p 43306:3306 --name VoteDB -e MYSQL_ROOT_PASSWORD=toy -e MYSQL_DATABASE="vote" -d mysql:latest --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci

-- User
CREATE TABLE user(
    ndi VARCHAR(36) PRIMARY KEY,
    email VARCHAR(50) not null unique,
    password VARCHAR(70) not null,
    status INT NOT NULL,
    name VARCHAR(30) NOT NULL
);

```

### Vote Redis

```
docker run --name voteRedis -d -p 6379:6379 redis
```
