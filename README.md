# Spring Boot 게시판

![image](https://github.com/dongyeop00/Board_Project/assets/117625978/eeb47e7a-d57e-4ca3-b562-7405e6db14db)

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

<details>
  <summary> 1. 로그인 기능 </summary>

- Post /index
- 로그인 성공 시 index 페이지 rediect 후 닉네임으로 변환 ✅
- ![image](https://github.com/dongyeop00/Board_Project/assets/117625978/349d51d3-11fb-45de-bc6e-20a553780af2) <br>
- ![image](https://github.com/dongyeop00/Board_Project/assets/117625978/c3d68b50-8fea-4a68-82a4-f821dfa0e1c0)
</details>

<details>
  <summary> 2. 회원 가입 기능</summary>

- Post /user/save
- 이메일, 닉네임이 중복되면 회원가입 불가 ✅
- ![image](https://github.com/dongyeop00/Board_Project/assets/117625978/1ca5be4d-8e22-46ed-835a-9ef889e6e91a)

</details>

<details>
 <summary> 3. 마이 페이지</summary>

- Get /member/detail/{id}
- 회원 탈퇴 기능 ✅
- 비밀번호 변경 기능 ✅
- 회원 별 게시한 게시글 확인 가능 ✅
- 회원 별 게시한 댓글 확인 가능 ✅
- ![image](https://github.com/dongyeop00/Board_Project/assets/117625978/f5b8b02c-9efd-4365-9d24-e024185eeb1c)
- ![image](https://github.com/dongyeop00/Board_Project/assets/117625978/59c41338-fc0e-4a34-a228-da36f3764f73)
- ![image](https://github.com/dongyeop00/Board_Project/assets/117625978/1cae57f9-3972-4ced-9b68-ac44554f1ad1)
</details>


### 게시판 기능
- 게시판 종류
  - 가입인사, 자유게시판, 골드게시판
- 게시판 세부 내용
  - Get /board/{category}
  - 해당 카테고리의 글 리스트 출력 ✅
  - 글 작성 버튼 시 해당 카테고리에 작성할 수 있는 글 작성 페이지로 이동 ✅
  - 글 클릭 시 해당 글 조회 페이지로 이동 ✅
  - ![image](https://github.com/dongyeop00/Board_Project/assets/117625978/12e9ac3d-2891-4ea1-ba2f-3dae3a5fa503)

- 글 작성 페이지
  - Get /board/{category}/write
  - 로그인 한 유저만 접근 가능 ✅
  - 현재 로그인한 회원의 닉네임과 id 출력 ✅
  - 글 작성 성공 시 작성된 글 카테고리로 redirect ✅
  - ![image](https://github.com/dongyeop00/Board_Project/assets/117625978/89aaf2eb-ce1d-4cb5-802f-760b38cfcf85)
  - ![image](https://github.com/dongyeop00/Board_Project/assets/117625978/6cd4e5f0-b673-4c66-a222-f76cb3c24688)
  - ![image](https://github.com/dongyeop00/Board_Project/assets/117625978/173edf3d-df95-4b3d-8f46-217972c9a290)

- 글 조회 페이지
  - Get /borad/{category}/{id}
  - id에 해당하는 글 내용을 화면에 출력 ✅
  - 해당 글의 작성자라면 수정 가능 ✅
  - 해당 글의 작성자라면 삭제 가능 ✅
  - 로그인한 유저는 댓글 작성 가능 ✅
  - 본인 댓글 수정 및 삭제 가능
  - ![image](https://github.com/dongyeop00/Board_Project/assets/117625978/442370c2-f2e5-431c-9545-911970325b88)

- 댓글 기능 (Ajax)
  - 댓글 작성 ✅
  - 댓글 수정 
  - 댓글 삭제 
  - 로그인 한 유저만 댓글 작성 가능 ✅
  - 본인이 작성한 댓글 수정, 삭제 가능
  - ![image](https://github.com/dongyeop00/Board_Project/assets/117625978/2343d1c0-0412-4848-83d5-90d521789c56)
  - ![image](https://github.com/dongyeop00/Board_Project/assets/117625978/decaa815-3b6b-4f0a-a5d2-a5b266d28cef)

  
