<%@page import="com.mycomp.sns_pjt.dto.CDto"%>
<%@page import="com.mycomp.sns_pjt.dto.IDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycomp.sns_pjt.dao.IDao"%>
<%@page import="com.mycomp.sns_pjt.dto.BDto"%>
<%@page import="com.mycomp.sns_pjt.dao.LDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Post</title>
<link href="/sns_pjt/resources/style_main.css?after" type="text/css" rel="stylesheet" />
<link href="/sns_pjt/resources/slideshow.css?after" type="text/css" rel="stylesheet" />
<link href="/sns_pjt/resources/css/bootstrap.css?after" type="text/css" rel="stylesheet" />
</head>
<body>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="/sns_pjt/resources/js/bootstrap.js" type="text/javascript"></script>
    <script src="/sns_pjt/resources/Search.js" type="text/javascript" charset="utf-8"></script>
	<%
		String sid = (String) session.getAttribute("sid");
		String sname = (String) session.getAttribute("sname");
		String memID = pageContext.findAttribute("memID").toString();
		
		LDao lDao = new LDao();
		IDao iDao = new IDao();
		
		int bdKey = Integer.parseInt((pageContext.findAttribute("bdKey")).toString());
		int countLike = lDao.countLikeSelect(bdKey);
		ArrayList<IDto> iDtos = iDao.iSelect(bdKey);
	%>
	
	<!-- Header -->
   	<div id="header">
        <div class="headbar_div">
            <ul class="headbar">
                <li class="head_logo"><a href="home_page"><img src="/sns_pjt/resources/img/sol_logo_40px.png" alt="logo" /></a></li>
                <li class="head_icon">
                    <a href="like_page" class="icon_like"><img src="/sns_pjt/resources/img/like.png" alt="like" /></a>
                    <a href="profile_page" class="icon_me"><img src="/sns_pjt/resources/img/me.PNG" alt="me" /></a>
                    <a href="post_page" class="icon_pic"><img src="/sns_pjt/resources/img/pic.PNG" alt="pic" /></a>
                </li>
            </ul>
            <div class="head_input">
                <form action="search_page" name="hd_frm" method="get">
                    <input type="search" id="keyword" name="srch" placeholder="검색" onkeypress="if (event.keyCode == 13) { goSearch();}"  style="text-align :center; font-size:13px; color:#cacaca; border:0.7px solid #cacaca;" />
                </form>
            </div>
        </div>
   	</div>
   	
   	<!-- Board -->
   	<table class="comm_table">
   		<tr>
   			<td class="left_post">
   				<div id="container">
   					<div class="cont_wrap">
   						<div class="content">
   							<table class="content_head">
   								<tr>
   									<td class="post_head_name">
   										<form action="areUSession" method="post">
   											<input type="hidden" name="userId" value="${memID}" />
   											<input type="submit" class="content_nick" value="${memID}" style="padding-top: 11px;" />
   										</form>
   									</td>
   									<td>
   										<form action="post_edit" method="post">
   											<input type="hidden" name="bdEditKey" value="${bdKey}" />
   											<button type="submit" id="edit_btn" class="btn btn-default btn-lg btn-pencil" style="display:none;">
   											<span class="glyphicon glyphicon-pencil" aria-hidden="true" /></button>
   										</form>
   									</td>
   								</tr>
   								<% if(sid.equals(memID)) { %>
   								<script type="text/javascript" charset="utf-8">
   									document.getElementById("edit_btn").style.display = "block";
   								</script>
   								<% } %>
   							</table>
   							
   							<!-- 이미지 파트 -->
   							<div class="content_pic">
   								<!-- 이미지 1장 이상일 경우 Slide -->
   								<% if(iDtos.size() > 1) { %>
   								<div class="slide" id="slide${bdKey}">
   									<div class="slideshow-container">
   										<%
   											for(IDto iDto : iDtos) {
   												pageContext.setAttribute("filename", iDto.getFileName());
   										%>
   										<div class="mySlides${bdKey} fade2">
   											<img class="main_slideImg" src="<spring:url value='/img/${filename}'/>" />
   										</div>
   										<% } %>
   										<a class="prev" onclick="plusSlides<%=bdKey%>(-1)">❮</a> 
										<a class="next" onclick="plusSlides<%=bdKey%>(1)">❯</a>
										<div class="dot_wrap">
											<% for(int ii = 0; ii < iDtos.size(); ii++) { %>
											<span class="dot${bdKey} dot" onclick="currentSlide${bdKey}(<%=ii + 1%>)"></span>
											<% } %>
										</div>
   									</div>
   								</div>
   								<!-- 이미지 1장인 경우 -->
   								<% 
   									} else {
   										pageContext.setAttribute("filename", iDtos.get(0).getFileName());
   								%>
   								<div class="pic_wrap" id="pic${bdKey}" >
   									<img src="<spring:url value='/img/${filename}'/>" />
   								</div>
   								<% } %>
   							</div>
   							<div class="content_cont">
   								<p class="cont_in">${bdCont}</p>
   							</div>
   							<div class="content_lnc">
   								<input type="hidden" id="boardKey" value="${bdKey}" />
   								<input type="hidden" id="sessionID" value="<%=sid%>"/>
   								<table>
   									<tr>
   										<td>
   											<button type="button" id="btnlike_empty" class="content_like" onclick="insertLike()">
   												<img src="/sns_pjt/resources/img/like.png" />
   											</button>
   											<button type="button" id="btnlike_full" class="content_like" onclike="deleteLike()" style="display: none;">
   												<img src="/sns_pjt/resources/img/like_full.png" />
   											</button>
   										</td>
   										<td>
   											<b><b id="cnt_Lk"><%=countLike%></b> 명이 이 글을 좋아합니다.</b>
   										</td>
   										<td>
   											<div name="comm_btn_form">
   												<button type="button" class="content_comm"><img src="/sns_pjt/resources/img/comm.png" /></button>
   											</div>
   										</td>
   									</tr>
   								</table>
   							</div>
   							<% boolean bLikeBd = lDao.CheckThisBoardILike(sid, bdKey); %>
   							<!-- 좋아요 비동기 스크립트 -->
   							<script type="text/javascript" charset="utf-8">
	   							var likerequest = new XMLHttpRequest();
	            				var Unlikerequest = new XMLHttpRequest();
	            				var countArray = [];
	            				countArray[<%=bdKey%>] = <%=countLike%>;
	            				
	            				function insertLike() {
	            					likerequest.open("Post","./LikeServlet?mem_id="+ encodeURIComponent(document.getElementById("sessionID").value) + 
											"&bd_key="+ encodeURIComponent(document.getElementById("boardKey").value) , true);
									likerequest.onreadystatechange = LikeProcess;
									likerequest.send(null);
	            				}
	            				function LikeProcess() {
	            					if(likerequest.readyState == 4 && likerequest.status == 200) {
	                    				var getWrite = likerequest.responseText;
	                    				if(getWrite == 0) {
	                    					alert("좋아요를 실패했습니다. (DB에러)");
	                    				} else {
	                    					var c = countArray[<%=bdKey%>] + 1;
	                    					countArray[<%=bdKey%>] = c;
	                    					document.getElementById("cnt_Lk").innerHTML = c;
	                    					document.getElementById("btnlike_empty").style.display = "none";
	                    	 				document.getElementById("btnlike_full").style.display = "block";
	                    				}
	                    			}
	            				}
	            				
	            				function deleteLike() {
	            					Unlikerequest.open("Post","./UnlikeServlet?mem_id="+ encodeURIComponent(document.getElementById("sessionID").value) + 
        									"&bd_key="+ encodeURIComponent(document.getElementById("boardKey").value) , true);
				        			Unlikerequest.onreadystatechange = UnlikeProcess;
				        			Unlikerequest.send(null);
	            				}
	            				function UnlikeProcess() {
	            					if(Unlikerequest.readyState == 4 && Unlikerequest.status == 200) {
	                    				var getWrite = Unlikerequest.responseText;
	                    				if(getWrite == 0) {
	                    					alert("좋아요 취소를 실패했습니다. (DB에러)");
	                    				} else {
	                    					var c = countArray[<%=bdKey%>] - 1;
	                    					countArray[<%=bdKey%>] = c;
	                    					document.getElementById("cnt_Lk").innerHTML = c;
	                    					document.getElementById("btnlike_full").style.display = "none";
	                    					document.getElementById("btnlike_empty").style.display = "block";
	                    				}
	                    			}
	            				}
   							</script>
   							<!-- 좋아요 되어있는 글인 경우 채워진 하트, 아닌 경우 안 채워진 하트 표시 -->
							<script type="text/javascript" charset="utf-8">
								if(<%=bLikeBd%> == false) {
									document.getElementById("btnlike_full").style.display = "none";
			    					document.getElementById("btnlike_empty").style.display = "block";
								}
								else {
									document.getElementById("btnlike_empty").style.display = "none";
			    	 				document.getElementById("btnlike_full").style.display = "block";
								}
							</script>
							<!-- Slide Script -->
							<script type="text/javascript">
								var slideIndex = 1;
								showSlides<%=bdKey%>(slideIndex);
								
								function plusSlides<%=bdKey%>(n) {
				    				showSlides<%=bdKey%>(slideIndex += n);
								}
								
								function currentSlide<%=bdKey%>(n) {
				    				showSlides<%=bdKey%>(slideIndex = n);
								}
								
								function showSlides<%=bdKey%>(n) {
				    				var i;
				    				var slides = document.getElementsByClassName("mySlides${bdKey}");
				    				var dots = document.getElementsByClassName("dot${bdKey}");
	
				    				if (n > slides.length) {slideIndex = 1}
				    				if (n < 1) {slideIndex = slides.length}
	
				    				for (i = 0; i < slides.length; i++) {
				        				slides[i].style.display = "none";
				    				}
	
				    				for (i = 0; i < dots.length; i++) {
				        				dots[i].className = dots[i].className.replace(" active", "");
				    				}
				    				slides[slideIndex - 1].style.display = "block";
				    				dots[slideIndex - 1].className += " active";
								}
							</script>
							
							<!-- 댓글 입력 폼 -->
							<div class="comment_form">
								<table>
									<tr>
										<form action="write_comment" method="post" name="write_comm_frm">
											<td>
												<input type="hidden" name="boardKey" value="${bdKey}" />
                                        		<input type="text" name="comm_text" class="comm_text" />
											</td>
											<td>
												<input type="submit" class="comm_post_btn" value="게시" />
											</td>
										</form>
									</tr>
								</table>
							</div>
   						</div>
   					</div>
   				</div>
   			</td>
   			
   			<!-- Comment Part -->
   			<td class="right_post">
   				<div class="comm_wrapTag">
   					<div class="comm_container">
   						<div class="comm_small_container">
   							<ul>
   								<c:forEach items="${commList}" var="cDTO">
   									<li class="comt"><b>${cDTO.mem_id}</b> ${cDTO.comment_cont}
   										<form action="delete_comment" method="post" style="display:inline;">
   											<input type="hidden" name="boardKey" value="${bdKey}" />
   											<input type="hidden" name="commentKey" value="${cDTO.comment_key}" />
   											<button type="submit" id="comm_del${cDTO.comment_key}" class="comm_delete" style="display:none;">&#10006;</button>
   										</form>
   									</li>
   									<% 
   										CDto cdto = (CDto)pageContext.findAttribute("cDTO");
   										String commWriteID = cdto.getMem_id();
   										if(sid.equals(commWriteID)) {
   									%>
   									<script type="text/javascript" charset="utf-8">
   										document.getElementById("comm_del${cDTO.comment_key}").style.display = "inline";
   									</script>
   									<% } %>
   								</c:forEach>
   							</ul>
   						</div>
   					</div>
   				</div>
   			</td>
   		</tr>
   	</table>
   	
   	
</body>
</html>