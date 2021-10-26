# Vote Everything

원하는(쓸모 있든 없든) 모든 투표를 만들어서 같이 투표 결과를 볼 수 있는 사이트

## Database

### Vote

```MySQL
docker run -p 43306:3306 --name VoteDB -e MYSQL_ROOT_PASSWORD=toy -e MYSQL_DATABASE="vote" -d mysql:latest --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci

CREATE TABLE vote(
    vote_id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(50) NOT NULL,
    time LONG NOT NULL,
    vote_name VARCHAR(200) NOT NULL,
    start_time TIMESTAMP NOT NULL,
    status INT NOT NULL DEFAULT(0)
);

CREATE TABLE vote_column(
    column_id INT PRIMARY KEY AUTO_INCREMENT,
    vote_id INT NOT NULL,
    column_name VARCHAR(200) NOT NULL,
    FOREIGN KEY(vote_id) REFERENCES vote(vote_id)
);

CREATE TABLE vote_user(
    vote_user_id INT PRIMARY KEY AUTO_INCREMENT,
    column_id INT NOT NULL,
    email VARCHAR(200) NOT NULL,
    FOREIGN KEY(column_id) REFERENCES vote_column(column_id)
);


```

### User

```MySQL
docker run -p 53306:3306 --name UoteDB -e MYSQL_ROOT_PASSWORD=toy -e MYSQL_DATABASE="user" -d mysql:latest --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci

-- User
CREATE TABLE user(
    ndi VARCHAR(36) PRIMARY KEY,
    email VARCHAR(50) not null unique,
    password VARCHAR(70) not null,
    status INT NOT NULL,
    name VARCHAR(30) NOT NULL
);

```

### Community

```
docker run -p 63306:3306 --name CommunityDB -e MYSQL_ROOT_PASSWORD=toy -e MYSQL_DATABASE="community" -d mysql:latest --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci

-- Community
CREATE TABLE community(
    community_id INT PRIMARY KEY,
    email VARCHAR(50) NOT NULL,
    name VARCHAR(30) NOT NULL,
    title VARCHAR(50) NOT NULL,
    time TIMESTAMP NOT NULL,
    content VARCHAR(500) NOT NULL
);

-- Comment
CREATE TABLE comment(
    comment_id INT NOT NULL,
    community_id INT NOT NULL,
    email VARCHAR(50) NOT NULL,
    name VARCHAR(30) NOT NULL,
    time TIMESTAMP NOT NULL,
    content VARCHAR(500) NOT NULL,
    FOREIGN KEY(community_id) REFERENCES community(community_id)
);


```
