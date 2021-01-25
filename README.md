## SNS Project (with Spring)

[![](https://img.shields.io/badge/category-Web%20project-orange)](https://github.com/lgm1007/webSNS_SpringProject) 

**SNS Project**

<br/>

Java Spring 프레임워크를 이용한 Instagram 느낌의 SNS 사이트 프로젝트 <br/>

Instagram 사이트의 기능 및 동작들을 참고하여 view 페이지간 오고 가는 데이터들을 controller에서 model을 통해 전달하고 HttpServletRequest를 통해 받는 등의 상호 작용들을 구성하고, MySQL을 이용한 RDBMS와 Spring 과 연동하여 데이터베이스에 접근하여 다양한 데이터들의 CRUD 동작으로 데이터 관리를 수행해보는 경험을 할 수 있었던 프로젝트였습니다. <br/>

본 프로젝트에 포함된 툴

1. JAVA 14.0.2
2. Spring 3.9.14.RELEASE
3. MySQL 8.0
4. Bootstrap framework 3.3.2

  

## Table of Contents



- [회원가입](#회원가입)
- [로그인 및 회원가입 시 오류 처리](#로그인 및 회원가입 시 오류 처리)
- [유저 검색 및 팔로우](#유저 검색 및 팔로우)
- [유저 언팔로우](#유저 언팔로우)
- [좋아요 및 좋아요 페이지](#좋아요 및 좋아요 페이지)
- [작성된 게시글 보기 & 댓글 달기](#작성된 게시글 보기 & 댓글 달기)
- [댓글 삭제](#댓글 삭제)
- [다른 유저의 프로필](#다른 유저의 프로필)
- [게시글 작성](#게시글 작성)
- [게시글 내용 수정](#게시글 내용 수정)
- [게시글 삭제](#게시글 삭제)
- [회원정보 변경](#회원정보 변경)
- [회원 탈퇴](#회원 탈퇴)
- [데이터 접근 객체 및 전송 객체](#데이터 접근 객체 및 전송 객체)

## 회원가입

![](https://i.imgur.com/SXOjzwF.gif)

[![](https://img.shields.io/badge/controller-MController-blue)](https://github.com/lgm1007/webSNS_SpringProject/blob/master/src/main/java/com/mycomp/sns_pjt/controller/MController.java) [![](https://img.shields.io/badge/view-join%20page-brightgreen)](https://github.com/lgm1007/webSNS_SpringProject/blob/master/src/main/webapp/WEB-INF/views/join_page.jsp) [![](https://img.shields.io/badge/view-login%20page-brightgreen)](https://github.com/lgm1007/webSNS_SpringProject/blob/master/src/main/webapp/WEB-INF/views/login_page.jsp) 

로그인 페이지에서 아이디와 비밀번호를 입력하는 창이 있고 로그인 버튼과 회원가입 버튼을 배치하여 회원가입 버튼을 클릭하면 회원가입 페이지로 이동하도록 설정 <br/>

회원가입 페이지에서는 아이디, 비밀번호, 비밀번호 확인, 이름 그리고 휴대폰 번호를 입력하는 form tag 설정하여 회원가입 시 유저 정보를 받아와 데이터베이스에 추가 <br/>

Controller 에서는 Command 인터페이스를 정의해놓고 해당 인터페이스를 상속받은 다양한 클래스에서 여러 기능들을 Overriding하여 사용하도록 설계 

## 로그인 및 회원가입 시 오류 처리

![](https://i.imgur.com/wXgUqlf.gif)

[![](https://img.shields.io/badge/class-MCheckClass-yellow)](https://github.com/lgm1007/webSNS_SpringProject/blob/master/src/main/java/com/mycomp/sns_pjt/command/MCheckClass.java) 

로그인 시 비밀번호가 틀린 경우 로그인 처리하지 않고 alert 창 팝업 후 다시 로그인 페이지로 이동 <br/>

회원가입 시 작성하지 않은 항목이 있는 경우 alert 창 팝업 후 작성하지 않은 항목에 포커싱, 회원가입 시 생성하려는 아이디가 이미 존재하는 경우 회원가입 되지 않고 경고 후 다시 회원가입 페이지로 이동

## 유저 검색 및 팔로우

![](https://i.imgur.com/lW1MDhC.gif)

[![](https://img.shields.io/badge/view-main%20page-brightgreen)](https://github.com/lgm1007/webSNS_SpringProject/blob/master/src/main/webapp/WEB-INF/views/home_page.jsp) [![](https://img.shields.io/badge/view-search%20page-brightgreen)](https://github.com/lgm1007/webSNS_SpringProject/blob/master/src/main/webapp/WEB-INF/views/search_page.jsp) [![](https://img.shields.io/badge/class-MSearchCommand-yellow)](https://github.com/lgm1007/webSNS_SpringProject/blob/master/src/main/java/com/mycomp/sns_pjt/command/MSearchCommand.java) [![](https://img.shields.io/badge/servlet%20class-ajaxServlet-yellowgreen)](https://github.com/lgm1007/webSNS_SpringProject/tree/master/src/main/java/com/mycomp/sns_pjt/ajaxServlet) [![](https://img.shields.io/badge/dispatcherServlet-web.xml-blueviolet)](https://github.com/lgm1007/webSNS_SpringProject/blob/master/src/main/webapp/WEB-INF/web.xml) 

로그인 및 회원가입 후 메인 페이지로 이동 <br/>

메인 페이지에서 표기되는 게시글들은 유저가 팔로우하는 사람들의 게시글이 표기되도록 설계 <br/>

검색창에 입력한 단어가 포함되는 아이디를 가진 유저를 검색하도록 검색 기능 개발 <br/>

팔로우 시 Javascript의 XMLHttpRequest 객체를 사용하여 비동기 통신으로 데이터베이스 내 팔로우 테이블에 접근하여 팔로우 정보를 Insert하도록 개발

## 유저 언팔로우

![](https://i.imgur.com/G8lZWa0.gif)

팔로우하고 있는 유저를 언팔로우하기 <br/>

언팔로우 시에도 팔로우와 마찬가지로 XMLHttpRequest 객체를 사용하여 비동기 통신으로 데이터베이스에 접근하며, 팔로우 되어있는 정보를 Delete함으로써 언팔로우 기능 설계

## 좋아요 및 좋아요 페이지

![](https://i.imgur.com/pkWJPAa.gif)

[![](https://img.shields.io/badge/controller-BController-blue)](https://github.com/lgm1007/webSNS_SpringProject/blob/master/src/main/java/com/mycomp/sns_pjt/controller/BController.java) [![](https://img.shields.io/badge/view-like%20page-brightgreen)](https://github.com/lgm1007/webSNS_SpringProject/blob/master/src/main/webapp/WEB-INF/views/like_page.jsp) 

마음에 드는 게시글에 좋아요(하트)를 클릭하면 비동기 통신으로 데이터베이스에 접근하여 게시글의 좋아요와 관련된 정보를 Insert하게끔 설계 <br/>

좋아요 되어있는 글에 한번 더 좋아요를 누를 경우 좋아요가 취소되고, 해당 취소 또한 비동기 통신으로 기능이 동작하도록 개발 <br/>

메인 페이지와 좋아요한 게시글을 보는 페이지는 게시글들이 최근에 작성한 글이 위에서 출력되도록 구성 (게시글의 key는 auto_increase로 설정되어 있어 가장 최근에 작성한 글일수록 key값이 가장 크기 때문에 게시글을 가져올 때 key 값의 내림차순으로 받아옴 -> `ORDER BY bd_key DESC`) 

## 작성된 게시글 보기 & 댓글 달기

![](https://i.imgur.com/57XnYy9.gif)

[![](https://img.shields.io/badge/view-posted_board%20page-brightgreen)](https://github.com/lgm1007/webSNS_SpringProject/blob/master/src/main/webapp/WEB-INF/views/posted_board.jsp) [![](https://img.shields.io/badge/class-CWriteCommand-yellow)](https://github.com/lgm1007/webSNS_SpringProject/blob/master/src/main/java/com/mycomp/sns_pjt/command/CWriteCommand.java) 

작성된 게시글을 자세히 보는 페이지 <br/>

해당 페이지에서는 좋아요 및 본 게시글에 달린 댓글 확인, 댓글 달기 기능 수행 가능 

## 댓글 삭제

![](https://i.imgur.com/0tfdPlN.gif)

[![](https://img.shields.io/badge/class-CDeleteCommand-yellow)](https://github.com/lgm1007/webSNS_SpringProject/blob/master/src/main/java/com/mycomp/sns_pjt/command/CDeleteCommand.java) 

현재 로그인 중인 유저가 작성한 댓글 옆에 댓글 삭제 버튼이 보이도록 Javascript에서 구현 <br/>

해당 버튼을 클릭하면 해당 게시글 정보 및 유저 정보, 해당 댓글 정보를 넘기고, 데이터베이스에서 본 정보들에 해당하는 댓글 데이터를 Delete 수행 

## 다른 유저의 프로필

![](https://i.imgur.com/NybM4Lc.gif)

[![](https://img.shields.io/badge/view-othersProfile%20page-brightgreen)](https://github.com/lgm1007/webSNS_SpringProject/blob/master/src/main/webapp/WEB-INF/views/others_page.jsp) [![](https://img.shields.io/badge/class-OtherProfileCommand-yellow)](https://github.com/lgm1007/webSNS_SpringProject/blob/master/src/main/java/com/mycomp/sns_pjt/command/OtherProfileCommand.java) 

게시글에서나 검색창, 팔로우 유저창에서의 유저 아이디를 클릭하면 해당 유저의 프로필로 이동하도록 설계 <br/>

프로필에서 유저의 아이디와 이름, 게시한 글, 팔로우 및 팔로워 수와 같은 정보들을 표기해야 함으로 해당 정보들을 모델에 담아 해당 페이지에 전송 

## 게시글 작성

![](https://i.imgur.com/A5g0HMI.gif)

<br/>

![](https://i.imgur.com/C5InyUn.gif)

[![](https://img.shields.io/badge/view-profile%20page-brightgreen)](https://github.com/lgm1007/webSNS_SpringProject/blob/master/src/main/webapp/WEB-INF/views/profile_page.jsp) [![](https://img.shields.io/badge/view-posting%20page-brightgreen)](https://github.com/lgm1007/webSNS_SpringProject/blob/master/src/main/webapp/WEB-INF/views/post_page.jsp) [![](https://img.shields.io/badge/class-BIWriteClass-yellow)](https://github.com/lgm1007/webSNS_SpringProject/blob/master/src/main/java/com/mycomp/sns_pjt/command/BIWriteClass.java) [![](https://img.shields.io/badge/dependency-pom.xml-blueviolet)](https://github.com/lgm1007/webSNS_SpringProject/blob/master/pom.xml) [![](https://img.shields.io/badge/bean-servlet%20context.xml-blueviolet)](https://github.com/lgm1007/webSNS_SpringProject/blob/master/src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml) 

게시글에 이미지는 1장 또는 여러 장 업로드 가능 <br/>

파일 업로드를 하기 위한 dependency를 pom.xml에 추가 (`commons-fileupload`, `commons-io`)하고 servlet-context.xml에 추가된 라이브러리에 beans 설정 (id=`multipartResolver`) <br/>

이미지 여러 장일 경우엔 Javascript로 슬라이드 효과 적용하도록 구현 <br/>

본 프로젝트에서는 이미지 업로드 경로를 로컬로 지정하였지만 서버에 업로드하도록 경로 지정하는 경우 또한 고려 <br/>

내가 작성한 글은 자신의 프로필 페이지에서 확인 가능 (자신의 프로필 페이지에서 로그아웃 가능; 자신의 게시글도 좋아요 가능) 

## 게시글 내용 수정

![](https://i.imgur.com/yrKmh76.gif)

[![](https://img.shields.io/badge/view-edit%20page-brightgreen)](https://github.com/lgm1007/webSNS_SpringProject/blob/master/src/main/webapp/WEB-INF/views/edit_page.jsp) [![](https://img.shields.io/badge/class-BUpdateCommand-yellow)](https://github.com/lgm1007/webSNS_SpringProject/blob/master/src/main/java/com/mycomp/sns_pjt/command/BUpdateCommand.java) 

자신이 게시한 글에는 편집 버튼이 보이도록 함 <br/>

게시글 내용 편집 페이지의 form 태그에서 해당 게시글의 key 값과 변경할 내용 값을 전송, 데이터베이스에서 해당하는 게시글의 내용을 수정하도록 구현 

## 게시글 삭제

![](https://i.imgur.com/TSIOiV8.gif)

[![](https://img.shields.io/badge/class-BDeleteCommand-yellow)](https://github.com/lgm1007/webSNS_SpringProject/blob/master/src/main/java/com/mycomp/sns_pjt/command/BDeleteCommand.java) 

게시글 편집 페이지의 글 삭제 버튼을 클릭 시 해당 글을 삭제하도록 구현 <br/>

데이터베이스에 접근하여 해당 글의 key 값을 가진 게시글 데이터 삭제 

## 회원정보 변경

![](https://i.imgur.com/6jgwrRN.gif)

[![](https://img.shields.io/badge/view-user_setting%20page-brightgreen)](https://github.com/lgm1007/webSNS_SpringProject/blob/master/src/main/webapp/WEB-INF/views/setting_page.jsp) [![](https://img.shields.io/badge/class-MUpdateCommand-yellow)](https://github.com/lgm1007/webSNS_SpringProject/blob/master/src/main/java/com/mycomp/sns_pjt/command/MUpdateCommand.java) 

프로필에서 회원정보 변경 페이지로 이동할 수 있는 버튼 구현 <br/>

회원정보 변경 페이지에서 회원정보 변경 버튼을 누를 시 이름, 휴대폰 번호를 편집 가능한 텍스트 박스로 바꾸고 비밀번호 및 비밀번호 확인 텍스트 박스는 readOnly 상태로 만듬, 비밀번호 변경 버튼을 누르면 반대로 설정되도록 구현 

## 회원 탈퇴

![](https://i.imgur.com/rquSNgS.gif)

[![](https://img.shields.io/badge/view-withdrawal_check%20page-brightgreen)](https://github.com/lgm1007/webSNS_SpringProject/blob/master/src/main/webapp/WEB-INF/views/withdrawal_check.jsp) [![](https://img.shields.io/badge/class-MDeleteCommand-yellow)](https://github.com/lgm1007/webSNS_SpringProject/blob/master/src/main/java/com/mycomp/sns_pjt/command/MDeleteCommand.java) [![](https://img.shields.io/badge/class-MCheckClass-yellow)](https://github.com/lgm1007/webSNS_SpringProject/blob/master/src/main/java/com/mycomp/sns_pjt/command/MCheckClass.java) 

회원정보 변경 페이지에서 회원탈퇴 버튼 클릭 시 회원탈퇴 확인 페이지로 이동 <br/>

탈퇴 확인 페이지에서 비밀번호를 확인하고, 비밀번호와 비밀번호 확인 값이 일치하고 해당 비밀번호가 유저의 비밀번호가 맞는지 확인하고 모두 일치할 경우 데이터베이스에 접근하여 해당 유저의 아이디값을 가진 유저 데이터 Delete 기능 수행 

## 데이터 접근 객체 및 전송 객체

[![](https://img.shields.io/badge/-DAO-red)](https://github.com/lgm1007/webSNS_SpringProject/tree/master/src/main/java/com/mycomp/sns_pjt/dao) 

[![](https://img.shields.io/badge/-DTO-red)](https://github.com/lgm1007/webSNS_SpringProject/tree/master/src/main/java/com/mycomp/sns_pjt/dto) 

