# Spring Boot 게시판

<br>

# 개발환경
- java 17
- Framwork : SpringBoot 3.2.1
- Build : Gradle 
- Server :
- Deploy :
- Datebase : MySql 8.0

<br>

# 라이브러리
- Spring Boot Web
- Lombok
- Spring Data JPA, Mysql
- Spring Boot DevTools
- Thymleaf

<br>

#  기능 및 End Point
### 유저 기능
- 로그인 기능
  - Post /index
  - 로그인 성공 시 index 페이지 rediect 후 memberName으로 변환

- 회원 가입 기능
  - Post /user/save
  - 이메일, 닉네임이 중복되면 회원가입 불가
  - 비밀번호는 암호화해서 저장
  - 회원가입 시 신규 유저 등급은 BRONZE로 설정
 
- 마이 페이지
  - Get /member/detail/{id}
  - 회원 탈퇴 기능
  - 비밀번호 변경 기능
  - 회원 별 게시한 게시글 확인 가능
  - 회원 별 게시한 댓글 확인 가능

### 게시판 기능
- 게시판 종류
  - 가입인사, 자유게시판, 골드게시판
- 게시판 세부 내용
  - Get /board/{category}
  - 해당 카테고리의 글 리스트 출력
  - 글 작성 버튼 시 해당 카테고리에 작성할 수 있는 글 작성 페이지로 이동
  - 글 클릭 시 해당 글 조회 페이지로 이동
  - 제목, 작성자, 내용으로 검색 가능
  - 최신 순서, 댓글 많은 순서, 조회수 순서로 조회 가능 (내림차순)
- 글 작성 페이지
  - Get /board/{category}/write
  - 로그인 한 유저만 접근 가능
  - 현재 로그인한 회원의 닉네임과 id 출력
  - 글 작성 성공 시 작성된 글 카테고리로 redirect
- 글 조회 페이지
  - Get /borad/{category}/{id}
  - id에 해당하는 글 내용을 화면에 출력
  - 해당 글의 작성자라면 수정 및 삭제 가능
  - 로그인한 유저는 댓글 작성 가능
  - 본인 댓글 수정 및 삭제 가능
- 댓글 기능
  - 댓글 작성 :
  - 댓글 수정 :
  - 댓글 삭제 :
  - 로그인 한 유저만 댓글 작성 가능
  - 본인이 작성한 댓글 수정, 삭제 가능
  - 글 조회 페이지 하단에서 해당 글 댓글 추가 기능
- 검색 기능
  - 글 제목, 작성자, 내용으로 검색 가능
- 페이징 기능
  
