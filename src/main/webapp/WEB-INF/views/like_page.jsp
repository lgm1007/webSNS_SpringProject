<%@page import="com.mycomp.sns_pjt.dto.IDto"%>
<%@page import="com.mycomp.sns_pjt.dto.BDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycomp.sns_pjt.dao.LDao"%>
<%@page import="com.mycomp.sns_pjt.dao.FDao"%>
<%@page import="com.mycomp.sns_pjt.dao.IDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Like Page</title>
<link href="/sns_pjt/resources/style_main.css?after" type="text/css" rel="stylesheet" />
<link href="/sns_pjt/resources/slideshow.css?after" type="text/css" rel="stylesheet" />
<link href="/sns_pjt/resources/css/bootstrap.css?after" type="text/css" rel="stylesheet" />
</head>
<body>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="/sns_pjt/resources/js/bootstrap.js" type="text/javascript"></script>
    <script src="/sns_pjt/resources/Search.js" type="text/javascript" charset="utf-8"></script>
    <%
    	String sid = (String)session.getAttribute("sid");
    	String sname = (String)session.getAttribute("sname");
    	int bdCount = 0;
    	
    	IDao iDao = new IDao();
    	FDao fDao = new FDao();
    	LDao lDao = new LDao();    	
    %>
    <div id="all_tag_wrap">
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
	   	
	   	<!-- TimeLine -->
	   	<div id="container">
	   		<div class="cont_wrap">
	   			<c:forEach items="${boardList}" var="bDtos">
	   				<div class="content">
	   					<table class="content_head">
	   						<tr>
	   							<td class="post_head_name">
	   								<!-- 좋아요 표기를 한 게시글은 다른 사람의 글 또는 자신의 글일 수 있기 때문에 작성자가 세션인지 확인 -->
	   								<form action="areUSession" method="post">
	   									<input type="hidden" name="userId" value="${bDtos.mem_id}" />
	   									<input type="submit" class="content_nick" value="${bDtos.mem_id}" style="padding-top: 11px;" />
	   								</form>
	   							</td>
	   						</tr>
	   					</table>
	   					<%
	   						bdCount++;
	   						BDto bDto = (BDto)pageContext.findAttribute("bDtos");
	   						ArrayList<IDto> iDtos = iDao.iSelect(bDto.getBd_key());
	   					%>
	   					<div class="content_pic">
	   					<!-- 이미지가 1장 이상일 경우 -->
	   					
	   					<% if(iDtos.size() > 1) { %>
	   					<div class="slide" id="slide${bDtos.bd_key}">
	   						<div class="slideshow-container">
	   							<%
	   								for(IDto iDto : iDtos) {
	   									pageContext.setAttribute("filename", iDto.getFileName());
	   							%>
	   							<div class="mySlides${bDtos.bd_key} fade2">
	   								<img class="main_slideImg" src="<spring:url value='/img/${filename}'/>"/>
	   							</div>
	   							<% } %>
	   							<a class="prev" onclick="plusSlides<%=bDto.getBd_key()%>(-1)">❮</a>
	   							<a class="next" onclick="plusSlides<%=bDto.getBd_key()%>(1)">❯</a>
	   							<div class="dot_wrap">
									<% for(int ii = 0; ii < iDtos.size(); ii++) { %>
									<span class="dot${bDtos.bd_key} dot" onclick="currentSlide${bDtos.bd_key}(<%=ii + 1%>)"></span>
									<% } %>
								</div>
	   						</div>
	   					</div>
	   					<!-- 이미지가 1장인 경우 -->
	   					<%
	   						} else {
	   							pageContext.setAttribute("filename", iDtos.get(0).getFileName());
	   					%>
	   					<div class="pic_wrap" id="pic${bDtos.bd_key}">
	   						<img src="<spring:url value='/img/${filename}'/>" />
	   					</div>
	   					<%
	   						}
	   						// 해당 포스트의 좋아요 수
	   						int countLk = lDao.countLikeSelect(bDto.getBd_key());
	   					%>
	   				</div>
	   				<div class="content_cont">
	   					<p class="cont_in">${bDtos.bd_cont}</p>
	   				</div>
	   				<div class="content_lnc">
	   					<!-- 좋아요 시 전송할 value -->
	   					<input type="hidden" id="sessionID" value="<%=sid%>" />
						<input type="hidden" id="bd_key<%=bDto.getBd_key()%>" value="<%=bDto.getBd_key()%>" />
						<table>
							<tr>
								<td>
									<button type="button" id="btnlike_empty<%=bDto.getBd_key()%>" class="content_like" onclick="insertLike<%=bDto.getBd_key()%>()">
										<img src="/sns_pjt/resources/img/like.png" />
									</button>
									<button type="button" id="btnlike_full<%=bDto.getBd_key()%>" class="content_like" onclick="deleteLike<%=bDto.getBd_key()%>()" style="display: none;">
										<img src="/sns_pjt/resources/img/like_full.png" />
									</button>
								</td>
								<td>
									<b><b id="cnt_Lk<%=bDto.getBd_key()%>"><%=countLk%></b> 명이 이 글을 좋아합니다.</b>
								</td>
								<td>
									<form action="posted_board" name="comm_btn_form" method="get">
										<input type="hidden" name="bdKey" value="${bDtos.bd_key}" />
										<input type="hidden" name="memID" value="${bDtos.mem_id }" />
										<button type="submit" class="content_comm"><img src="/sns_pjt/resources/img/comm.png" /></button>
									</form>
								</td>
							</tr>
						</table>
	   				</div>
	   				<% boolean bLikeBd = lDao.CheckThisBoardILike(sid, bDto.getBd_key()); %>
	   				
	   				<!-- 좋아요 비동기 스크립트 -->
	   				<script type="text/javascript" charset="utf-8">
		   				var likerequest = new XMLHttpRequest();
						var Unlikerequest = new XMLHttpRequest();
						var countVar<%=bDto.getBd_key()%> = 0;
						
						countVar<%=bDto.getBd_key()%> = <%=countLk%>;
						
						function insertLike<%=bDto.getBd_key()%>() {
							likerequest.open("Post","./LikeServlet?mem_id="+ encodeURIComponent(document.getElementById("sessionID").value) + 
									"&bd_key="+ encodeURIComponent(document.getElementById("bd_key<%=bDto.getBd_key()%>").value) , true);
							likerequest.onreadystatechange = LikeProcess<%=bDto.getBd_key()%>;
							likerequest.send(null);
						}
						
						function LikeProcess<%=bDto.getBd_key()%>() {
		        			if(likerequest.readyState == 4 && likerequest.status == 200) {
		        				var getWrite = likerequest.responseText;
		        				if(getWrite == 0) {
		        					alert("좋아요를 실패했습니다. (DB에러)");
		        				} else {
		        					var c = countVar<%=bDto.getBd_key()%> + 1;
		        					countVar<%=bDto.getBd_key()%> = c;
		        					document.getElementById("cnt_Lk<%=bDto.getBd_key()%>").innerHTML = c;
		        					document.getElementById("btnlike_empty<%=bDto.getBd_key()%>").style.display = "none";
		        	 				document.getElementById("btnlike_full<%=bDto.getBd_key()%>").style.display = "block";
		        				}
		        			}
		        		}
						
						function deleteLike<%=bDto.getBd_key()%>() {
		        			Unlikerequest.open("Post","./UnlikeServlet?mem_id="+ encodeURIComponent(document.getElementById("sessionID").value) + 
		        									"&bd_key="+ encodeURIComponent(document.getElementById("bd_key<%=bDto.getBd_key()%>").value) , true);
		        			Unlikerequest.onreadystatechange = UnlikeProcess<%=bDto.getBd_key()%>;
		        			Unlikerequest.send(null);
		        		}
						
						function UnlikeProcess<%=bDto.getBd_key()%>() {
		        			if(Unlikerequest.readyState == 4 && Unlikerequest.status == 200) {
		        				var getWrite = Unlikerequest.responseText;
		        				if(getWrite == 0) {
		        					alert("좋아요 취소를 실패했습니다. (DB에러)");
		        				} else {
		        					var c = countVar<%=bDto.getBd_key()%> - 1;
		        					countVar<%=bDto.getBd_key()%> = c;
		        					document.getElementById("cnt_Lk<%=bDto.getBd_key()%>").innerHTML = c;
		        					document.getElementById("btnlike_full<%=bDto.getBd_key()%>").style.display = "none";
		        					document.getElementById("btnlike_empty<%=bDto.getBd_key()%>").style.display = "block";
		        				}
		        			}
		        		}
	   				</script>
	   				<!-- 좋아요 되어있는 글인 경우 채워진 하트, 아닌 경우 안 채워진 하트 표시 -->
					<script type="text/javascript" charset="utf-8">
						if(<%=bLikeBd%> == false) {
							document.getElementById("btnlike_full<%=bDto.getBd_key()%>").style.display = "none";
	    					document.getElementById("btnlike_empty<%=bDto.getBd_key()%>").style.display = "block";
						}
						else {
							document.getElementById("btnlike_empty<%=bDto.getBd_key()%>").style.display = "none";
	    	 				document.getElementById("btnlike_full<%=bDto.getBd_key()%>").style.display = "block";
						}
					</script>
					<!-- 슬라이드 스크립트 -->
					<script type="text/javascript">
						var slideIndex = 1;
						showSlides<%=bDto.getBd_key()%>(slideIndex);
						
						function plusSlides<%=bDto.getBd_key()%>(n) {
		    				showSlides<%=bDto.getBd_key()%>(slideIndex += n);
						}
						
						function currentSlide<%=bDto.getBd_key()%>(n) {
		    				showSlides<%=bDto.getBd_key()%>(slideIndex = n);
						}
						
						function showSlides<%=bDto.getBd_key()%>(n) {
		    				var i;
		    				var slides = document.getElementsByClassName("mySlides${bDtos.bd_key}");
		    				var dots = document.getElementsByClassName("dot${bDtos.bd_key}");
	
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
	   				</div>
	   			</c:forEach>
	   			
	   			<!-- 좋아요 한 글이 없을 때 -->
	   			<% if(bdCount == 0) { %>
	   			<div class="content">
					<h3 style="line-height: 150px">
						좋아요한 포스트가 없습니다.<br />마음에 드는 포스트에 좋아요를 눌러보세요!
					</h3>
				</div>
				<% } %>
				<!-- End Timeline -->
				
				<div class="side_fixed">
					<div class="side_name_div">
						<p class="p_nicname"><%=sid%></p>
						<p class="p_realname"><%=sname%></p>
					</div>
				</div>
	   		</div>
	   	</div>
	 </div>
</body>
</html>