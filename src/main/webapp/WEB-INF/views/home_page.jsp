<%@page import="java.util.ArrayList"%>
<%@page import="com.mycomp.sns_pjt.dao.LDao"%>
<%@page import="com.mycomp.sns_pjt.dto.FDto"%>
<%@page import="com.mycomp.sns_pjt.dto.BDto"%>
<%@page import="com.mycomp.sns_pjt.dto.IDto"%>
<%@page import="com.mycomp.sns_pjt.dao.FDao"%>
<%@page import="com.mycomp.sns_pjt.dao.BDao"%>
<%@page import="com.mycomp.sns_pjt.dao.IDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to SOL</title>
<link href="/sns_pjt/resources/style_main.css?after" type="text/css" rel="stylesheet" />
<link href="/sns_pjt/resources/slideshow.css?after" type="text/css" rel="stylesheet" />
<link href="/sns_pjt/resources/css/bootstrap.css?after" type="text/css" rel="stylesheet" />
</head>
<body>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="/sns_pjt/resources/js/bootstrap.js" type="text/javascript"></script>
    <script src="/sns_pjt/resources/Search.js" type="text/javascript" charset="utf-8"></script>
    <script src="/sns_pjt/resources/modalToggle.js" type="text/javascript" charset="utf-8"></script>
    <% 
    	String sid = (String)session.getAttribute("sid");
    	String sname = (String)session.getAttribute("sname");
    	int bdCount = 0;
    	IDao iDao = new IDao();
    	FDao fDao = new FDao();
    	LDao lDao = new LDao();
    	
    	ArrayList<FDto> followDtos = fDao.selectFollow(sid);
    	ArrayList<FDto> followerDtos = fDao.selectFollower(sid);
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
    	
    	<!-- Modal -->
    	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">팔로우 목록</h4>
					</div>
					<div class="modal-body">
						<div class="modal_btnlist_wrap">
							<ul class="modal_btn_ul">
								<li><button type="button" class="btn_follower"
										onclick="view_follower()">팔로워</button></li>
								<li><button type="button" class="btn_follow"
										onclick="view_follow()">팔로우</button></li>
							</ul>
						</div>
						<div id="modal_follower_lists">
							<!--팔로워(나를 팔로우한 사람) 1명분-->
							<table class="fl_table_modal">
							<%
								for(FDto followerDto : followerDtos) {
							%>
								<!--팔로워리스트 팔로우/언팔로우버튼 동작 비동기 Script-->
								<script type="text/javascript" charset="utf-8">
                            	var Followrequest = new XMLHttpRequest();
                            	var Unfollowrequest = new XMLHttpRequest();
                            	
                            	function insertFollow<%=followerDto.getFollower()%>() {
                        			Followrequest.open("Post","./FollowRegisterServlet?follow="+ encodeURIComponent(document.getElementById("followID<%=followerDto.getFollower()%>").value) +
                        								 "&follower="+ encodeURIComponent(document.getElementById("followerID<%=followerDto.getFollower()%>").value), true);
                        			
                        			Followrequest.onreadystatechange = FollowProcess<%=followerDto.getFollower()%>;
                        			Followrequest.send(null);
                        		}
                            	function FollowProcess<%=followerDto.getFollower()%>() {
                            		if(Followrequest.readyState == 4 && Followrequest.status == 200) {
                        				var getWrite = Followrequest.responseText;
                        				if(getWrite == 0) {
                        					alert("팔로우를 실패하였습니다! (DB에러)");
                        				} 
                        				else {
                        					document.getElementById("fl_btn<%=followerDto.getFollower()%>").style.display = "none";
                        	  				document.getElementById("ufl_btn<%=followerDto.getFollower()%>").style.display = "block";
                        				}
                        			}
                            	}
                            	
                            	function doUnFollow<%=followerDto.getFollower()%>() {
                        			Unfollowrequest.open("Post","./UnfollowServlet?follow="+ encodeURIComponent(document.getElementById("unfollowID<%=followerDto.getFollower()%>").value) +
                        					 "&follower="+ encodeURIComponent(document.getElementById("unfollowerID<%=followerDto.getFollower()%>").value), true);
                        			Unfollowrequest.onreadystatechange = UnfollowProcess<%=followerDto.getFollower()%>;
                        			Unfollowrequest.send(null);
                        		}
                            	function UnfollowProcess<%=followerDto.getFollower()%>() {
                            		if(Unfollowrequest.readyState == 4 && Unfollowrequest.status == 200) {
                        				var getWrite = Unfollowrequest.responseText;
                        				if(getWrite == 0) {
                        					alert("팔로우를 실패하였습니다! (DB에러)");
                        				}
                        				else {
                        					document.getElementById("ufl_btn<%=followerDto.getFollower()%>").style.display = "none";
                        	 				document.getElementById("fl_btn<%=followerDto.getFollower()%>").style.display = "block";
                        				}
                        			}
                            	}
                            </script>
                            
								<tr>
									<td>
										<form action="others_page" method="post" class="modal_fl_frm">
											<input type="hidden" value="<%=followerDto.getFollower()%>" name="otherUserID" /> 
											<input name="otherUser" class="fl_id_submit" type="submit" value="<%=followerDto.getFollower()%>" />
										</form>
									</td>
									<td>
										<div class="fl_ufl_btn_wrap">
											<input type="hidden" id="followerID<%=followerDto.getFollower()%>" value="<%=sid%>" name="follower_session" /> 
											<input type="hidden" id="followID<%=followerDto.getFollower()%>" value="<%=followerDto.getFollower()%>" name="follow_mem" />
											<button type="button" class="modal_fl_btn" id="fl_btn<%=followerDto.getFollower()%>" onclick="insertFollow<%=followerDto.getFollower()%>()">팔로우</button>

											<input type="hidden" id="unfollowerID<%=followerDto.getFollower()%>" value="<%=sid%>" name="unfollower_session" /> 
											<input type="hidden" id="unfollowID<%=followerDto.getFollower()%>" value="<%=followerDto.getFollower()%>" name="unfollow_mem" />
											<button type="button" class="modal_ufl_btn" id="ufl_btn<%=followerDto.getFollower()%>" onclick="doUnFollow<%=followerDto.getFollower()%>()">언팔로우</button>

											<% boolean checkI = fDao.checkIFollowU(sid, followerDto.getFollower()); %>
											<script type="text/javascript">
    										/* 내 팔로워가 팔로우 되어있지 않으면 팔로우 버튼이 보이게 */
    										if(<%=checkI%>==false) {
           		 								document.getElementById("ufl_btn<%=followerDto.getFollower()%>").style.display = "none";
           		 								document.getElementById("fl_btn<%=followerDto.getFollower()%>").style.display = "block";
    										}
    										/* 내 팔로워가 팔로우 되어있으면 언팔로우 버튼이 보이게 */
    										else {
          		  								document.getElementById("fl_btn<%=followerDto.getFollower()%>").style.display = "none";
         		  								document.getElementById("ufl_btn<%=followerDto.getFollower()%>").style.display = "block";
    										}
   		 									</script>
										</div>
									</td>
								</tr>
								<%
									}	//for 반복문 종료
									//folow_i 가 0이라면 팔로워가 없을때 
									if (followerDtos.size() == 0) {
								%>
								<tr>
									<h3>아직 팔로워가 없습니다.</h3>
								</tr>
								<% } %>
							</table>
						</div>

						<div id="modal_follow_lists">
							<!--팔로우(내가 팔로우한 사람) 1명분-->
							<table class="fl_table_modal">
								<%
									for (FDto followDto : followDtos) {
								%>
								<!-- 팔로우리스트 팔로우/언팔로우버튼 동작 비동기 Script -->
								<script type="text/javascript" charset="utf-8">
                            	var Followrequest2 = new XMLHttpRequest();
                            	var Unfollowrequest2 = new XMLHttpRequest();
                            	
                            	function insertFollow2<%=followDto.getFollow()%>() {
                        			Followrequest2.open("Post","./FollowRegisterServlet?follow="+ encodeURIComponent(document.getElementById("followID2<%=followDto.getFollow()%>").value) +
                        								 "&follower="+ encodeURIComponent(document.getElementById("followerID2<%=followDto.getFollow()%>").value), true);
                        			
                        			Followrequest2.onreadystatechange = FollowProcesss<%=followDto.getFollow()%>;
                        			Followrequest2.send(null);
                        		}
                            	function FollowProcesss<%=followDto.getFollow()%>() {
                            		if(Followrequest2.readyState == 4 && Followrequest2.status == 200) {
                        				var getWrite = Followrequest2.responseText;
                        				if(getWrite == 0) {
                        					alert("팔로우를 실패하였습니다! (DB에러)");
                        				} 
                        				else {
                        					document.getElementById("fl_btn2<%=followDto.getFollow()%>").style.display = "none";
                        	  				document.getElementById("ufl_btn2<%=followDto.getFollow()%>").style.display = "block";
                        				}
                        			}
                            	}
                            	
                            	function doUnFollow2<%=followDto.getFollow()%>() {
                        			Unfollowrequest2.open("Post","./UnfollowServlet?follow="+ encodeURIComponent(document.getElementById("unfollowID2<%=followDto.getFollow()%>").value) +
                        					 "&follower="+ encodeURIComponent(document.getElementById("unfollowerID2<%=followDto.getFollow()%>").value), true);
                        			Unfollowrequest2.onreadystatechange = UnfollowProcesss<%=followDto.getFollow()%>;
                        			Unfollowrequest2.send(null);
                        		}
                            	function UnfollowProcesss<%=followDto.getFollow()%>() {
                            		if(Unfollowrequest2.readyState == 4 && Unfollowrequest2.status == 200) {
                        				var getWrite = Unfollowrequest2.responseText;
                        				if(getWrite == 0) {
                        					alert("팔로우를 실패하였습니다! (DB에러)");
                        				}
                        				else {
                        					document.getElementById("ufl_btn2<%=followDto.getFollow()%>").style.display = "none";
                        	 				document.getElementById("fl_btn2<%=followDto.getFollow()%>").style.display = "block";
                        				}
                        			}
                            	}
                            </script>
								<tr>
									<td>
										<form action="others_page" method="post" class="modal_fl_frm">
											<input type="hidden" value="<%=followDto.getFollow()%>" name="otherUserID" /> 
											<input name="otherUser" class="fl_id_submit" type="submit" value="<%=followDto.getFollow()%>" />
										</form>
									</td>
									<td>
										<div class="fl_ufl_btn_wrap">
											<input type="hidden" id="followerID2<%=followDto.getFollow()%>" value="<%=sid%>" name="unfollower_session" /> 
											<input type="hidden" id="followID2<%=followDto.getFollow()%>" value="<%=followDto.getFollow()%>" name="unfollow_mem" />
											<button type="button" class="modal_fl_btn" id="fl_btn2<%=followDto.getFollow()%>" onclick="insertFollow2<%=followDto.getFollow()%>()">팔로우</button>

											<input type="hidden" id="unfollowerID2<%=followDto.getFollow()%>" value="<%=sid%>" name="unfollower_session" /> 
											<input type="hidden" id="unfollowID2<%=followDto.getFollow()%>" value="<%=followDto.getFollow()%>" name="unfollow_mem" />
											<button type="button" class="modal_ufl_btn" id="ufl_btn2<%=followDto.getFollow()%>" onclick="doUnFollow2<%=followDto.getFollow()%>()">언팔로우</button>

											<%
												boolean bCheckU = fDao.checkIFollowU(sid, followDto.getFollow());
											%>
											<script type="text/javascript">
    											/* 팔로우 되어있지 않으면 팔로우 버튼이 보이게 */
    											if(<%=bCheckU%>==false) {
           		 									document.getElementById("ufl_btn2<%=followDto.getFollow()%>").style.display = "none";
           		 									document.getElementById("fl_btn2<%=followDto.getFollow()%>").style.display = "block";
    											}
    											/* 팔로우 되어있으면 언팔로우 버튼이 보이게 */
    											else {
          		  									document.getElementById("fl_btn2<%=followDto.getFollow()%>").style.display = "none";
         		  									document.getElementById("ufl_btn2<%=followDto.getFollow()%>").style.display = "block";
    											}
   		 								</script>
										</div>
									</td>
								</tr>
								<%
									} // for 반복문 종료
									
									if (followDtos.size() == 0) {
								%>
								<tr>
									<h3>팔로우가 없습니다. 새로운 사람들을 팔로우해보세요!</h3>
								</tr>
								<% } %>
							</table>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					</div>
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
									<!-- 메인 페이지의 게시글 작성자, 검색에서 나오는 유저는 자신이 나오지 않으므로 바로 다른 유저의 프로필로 이동 -->
									<form action="others_page" method="post">
										<input type="hidden" name="userId" value="${bDtos.mem_id}" /> 
										<input type="submit" class="content_nick" value="${bDtos.mem_id}" style="padding-top: 11px;" />
									</form>
								</td>
							</tr>
							<!-- 좋아요 시 입력값 -->
							<input type="hidden" id="bd_key${bDtos.bd_key}" value="${bDtos.bd_key}" />
						</table>
						<%
							bdCount++;
							BDto bDto = (BDto)pageContext.findAttribute("bDtos");
							ArrayList<IDto> iDtos = iDao.iSelect(bDto.getBd_key());
						%>
						<div class="content_pic">
							<!-- 이미지 1장 이상일 경우 for문 (& Silde 기능 추가) -->
							
							<% if(iDtos.size() > 1) { %>
							<div class="slide" id="slide${bDtos.bd_key}">
								<div class="slideshow-container">
									<%
										for(IDto iDto : iDtos) {
											pageContext.setAttribute("filename", iDto.getFileName());
									%>
									<div class="mySlides${bDtos.bd_key} fade2">
										<img class="main_slideImg" src="<spring:url value='/img/${filename}'/>" />
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
							<!-- 이미지 1장인 경우 -->
							
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
            				var countArray = [];
            				
            				countArray[<%=bDto.getBd_key()%>] = <%=countLk%>;
            				
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
                    					var c = countArray[<%=bDto.getBd_key()%>] + 1;
                    					countArray[<%=bDto.getBd_key()%>] = c;
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
                    					var c = countArray[<%=bDto.getBd_key()%>] - 1;
                    					countArray[<%=bDto.getBd_key()%>] = c;
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
				<!-- 타임라인 글이 아무것도 없을 때 -->
				<% if(bdCount == 0) { %>
				<div class="content">
					<h3 style="line-height: 150px">
						포스팅한 글이 없습니다.<br />마음에 드는 사람을 팔로우 해보세요!
					</h3>
				</div>
				<% } %>
				<!-- End Timeline -->
				
				<div class="side_fixed">
					<div class="side_name_div">
						<p class="p_nicname"><%=sid%></p>
						<p class="p_realname"><%=sname%></p>
					</div>
					<button type="button" class="btn btn-primary btn-lg modal_btn"
					data-toggle="modal" data-target="#myModal"
					onclick="view_follower()">팔로우 목록</button>
				</div>
			</div>
		</div>
    </div>
    
    
</body>
</html>