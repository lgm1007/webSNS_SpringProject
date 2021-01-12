<%@page import="java.util.ArrayList"%>
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
<meta charset="utf-8">
<title>Welcome to SOL</title>
<link href="/sns_pjt/resources/style_main.css" type="text/css" rel="stylesheet" />
<link href="/sns_pjt/resources/slideshow.css" type="text/css" rel="stylesheet" />
<link href="/sns_pjt/resources/css/bootstrap.css" type="text/css" rel="stylesheet" />
</head>
<body>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="/sns_pjt/resources/js/bootstrap.js" type="text/javascript"></script>
    <script src="/sns_pjt/resources/Search.js" type="text/javascript" charset="utf-8"></script>
    <script src="/sns_pjt/resources/modalToggle.js" type="text/javascript" charset="utf-8"></script>
    <% 
    	String sid = (String)session.getAttribute("sid");
    	String sname = (String)session.getAttribute("sname");
    	IDao iDao = new IDao();
    	FDao fDao = new FDao();
    	
    	ArrayList<FDto> followDtos = fDao.selectFollow(sid);
    	ArrayList<FDto> followerDtos = fDao.selectFollower(sid);
    %>
    <!-- Modal -->
    <div id="all_tag_wrap">
    	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
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
										<form action="OtherUser_Page.jsp" method="post"
											class="modal_fl_frm">
											<input type="hidden" value="<%=followerDto.getFollower()%>" name="otherUserID" /> 
											<input name="otherUser" class="fl_id_submit" type="submit" value="<%=followerDto.getFollower()%>" />
										</form>
									</td>
									<td>
										<div class="fl_ufl_btn_wrap">
											<input type="hidden" id="followerID<%=followerDto.getFollower()%>" value="<%=sid%>" name="unfollower_session" /> 
											<input type="hidden" id="followID<%=followerDto.getFollower()%>" value="<%=followerDto.getFollower()%>" name="unfollow_mem" />
											<button type="button" class="modal_fl_btn" id="fl_btn<%=followerDto.getFollower()%>" onclick="insertFollow<%=followerDto.getFollower()%>()">팔로우</button>

											<input type="hidden" id="unfollowerID<%=followerDto.getFollower()%>" value="<%=sid%>" name="unfollower_session" /> 
											<input type="hidden" id="unfollowID<%=followerDto.getFollower()%>" value="<%=followerDto.getFollower()%>" name="unfollow_mem" />
											<button type="button" class="modal_ufl_btn" id="ufl_btn<%=followerDto.getFollower()%>" onclick="doUnFollow<%=followerDto.getFollower()%>()">언팔로우</button>

											<%
												
												boolean checkI = fDao.checkIFollowU(sid, followerDto.getFollower());
											%>
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
										<form action="OtherUser_Page.jsp" method="post"
											class="modal_fl_frm">
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
												boolean bCheckU = fDao.checkUFollowMe(sid, followDto.getFollow());
											%>
											<script type="text/javascript">
    											/* 팔로우 되어있지 않으면 팔로우 버튼이 보이게 */
    											if(<%=bCheckU%>==false) {
           		 									document.getElementById("ufl_btn2<%=follow_i%>").style.display = "none";
           		 									document.getElementById("fl_btn2<%=follow_i%>").style.display = "block";
    											}
    											/* 팔로우 되어있으면 언팔로우 버튼이 보이게 */
    											else {
          		  									document.getElementById("fl_btn2<%=follow_i%>").style.display = "none";
         		  									document.getElementById("ufl_btn2<%=follow_i%>").style.display = "block";
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
    </div>
    
    <!-- 
    </hr>
    <a href="post_page" >글작성 페이지로</a>
    </hr>
    <c:forEach items="${boardList}" var="bDtos">
    	<p>${bDtos.mem_id}</p>
    	<p>${bDtos.bd_cont}</p>
    	<%
    		BDto bDto = (BDto)pageContext.findAttribute("bDtos");
    		ArrayList<IDto> iDtos = iDao.iSelect(bDto.getBd_key());
    		for (IDto iDto : iDtos) {
    			pageContext.setAttribute("filename", iDto.getFileName());
    	%>
    	<img src="<spring:url value='/img/${filename}'/>" />
    	<% } %>
    	
    </c:forEach>
     -->
</body>
</html>