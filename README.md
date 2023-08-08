
# 개발환경

1. IDE: IntelliJ IDEA Ultimate
2. Spring Boot 2.7.13 
3. JDK 11
4. mysql
5. Spring Data JPA
6. Thymeleaf

# 게시판 주요기능

1. 글 목록조회
2. 글쓰기
3. 글 상세조회
4. 글 수정
5. 글 삭제
6. 페이징

# 게시판 테이블

create table board_table
(
id             bigint auto_increment primary key,
created_time   datetime     null,
updated_time   datetime     null,
board_contents varchar(500) null,
board_hits     int          null,
board_pass     varchar(255) null,
board_title    varchar(255) null,
board_writer   varchar(20)  not null

);

