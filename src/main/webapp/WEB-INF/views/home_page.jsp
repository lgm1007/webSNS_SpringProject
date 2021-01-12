<%@page import="com.mycomp.sns_pjt.dto.BDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycomp.sns_pjt.dto.IDto"%>
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
							<!--팔로워(나를 팔로우한 사람) 1명 값-->
							<table class="fl_table_modal">
								<c:forEach items="${followerList}" var="followerDtos">
								<!-- 팔로우/언팔로우버튼 ajax Script 1 -->
								<script type="text/javascript" charset="utf-8">
                            	var Followrequest = new XMLHttpRequest();
                            	var Unfollowrequest = new XMLHttpRequest();
                            	
                            	function insertFollow'${followerDtos.follower}'() {
                        			Followrequest.open("Post","./FollowRegisterServlet?follow="+ encodeURIComponent(document.getElementById("followID"'${followerDtos.follower}').value) +
                        								 "&follower="+ encodeURIComponent(document.getElementById("followerID"'${followerDtos.follower}').value), true);
                        			
                        			Followrequest.onreadystatechange = FollowProcess'${followerDtos.follower}';
                        			Followrequest.send(null);
                        		}
                            	function FollowProcess'${followerDtos.follower}'() {
                            		if(Followrequest.readyState == 4 && Followrequest.status == 200) {
                        				var getWrite = Followrequest.responseText;
                        				if(getWrite == 0) {
                        					alert("팔로우를 실패하였습니다! (DB에러)");
                        				} 
                        				else {
                        					document.getElementById("fl_btn"'${followerDtos.follower}').style.display = "none";
                        	  				document.getElementById("ufl_btn"'${followerDtos.follower}').style.display = "block";
                        				}
                        			}
                            	}
                            	
                            	function doUnFollow'${followerDtos.follower}'() {
                        			Unfollowrequest.open("Post","./UnfollowServlet?follow="+ encodeURIComponent(document.getElementById("unfollowID"'${followerDtos.follower}').value) +
                        					 "&follower="+ encodeURIComponent(document.getElementById("unfollowerID"'${followerDtos.follower}').value), true);
                        			Unfollowrequest.onreadystatechange = UnfollowProcess'${followerDtos.follower}';
                        			Unfollowrequest.send(null);
                        		}
                            	function UnfollowProcess'${followerDtos.follower}'() {
                            		if(Unfollowrequest.readyState == 4 && Unfollowrequest.status == 200) {
                        				var getWrite = Unfollowrequest.responseText;
                        				if(getWrite == 0) {
                        					alert("팔로우를 실패하였습니다! (DB에러)");
                        				}
                        				else {
                        					document.getElementById("ufl_btn"'${followerDtos.follower}').style.display = "none";
                        	 				document.getElementById("fl_btn"'${followerDtos.follower}').style.display = "block";
                        				}
                        			}
                            	}
                            </script>
                            
								<tr>
									<td>
										<form action="OtherUser_Page.jsp" method="post"
											class="modal_fl_frm">
											<input type="hidden" value="${followerDtos.follower}" name="otherUserID" /> 
											<input name="otherUser" class="fl_id_submit" type="submit" value="<%=erList.get(follower_i)%>" />
										</form>
										<p class="modal_frm_realname"><%=rsForName.getString("name")%></p>
									</td>
									<td>
										<div class="fl_ufl_btn_wrap">
											<input type="hidden" id="followerID<%=follower_i%>" value="<%=sID%>" name="unfollower_session" /> 
											<input type="hidden" id="followID<%=follower_i%>" value="<%=erList.get(follower_i)%>" name="unfollow_mem" />
											<button type="button" class="modal_fl_btn" id="fl_btn<%=follower_i%>" onclick="insertFollow<%=follower_i%>()">팔로우</button>

											<input type="hidden" id="unfollowerID<%=follower_i%>" value="<%=sID%>" name="unfollower_session" /> 
											<input type="hidden" id="unfollowID<%=follower_i%>" value="<%=erList.get(follower_i)%>" name="unfollow_mem" />
											<button type="button" class="modal_ufl_btn"
												id="ufl_btn<%=follower_i%>"
												onclick="doUnFollow<%=follower_i%>()">언팔로우</button>

											<%
												String isFollowerSQL = "select * from bfollow where follow='" + erList.get(follower_i)
															+ "' and follower='" + sID + "'";
													ResultSet isFollowerRS = Dbui.selectSQL(isFollowerSQL);
													boolean b = isFollowerRS.next();
											%>
											<script type="text/javascript">
    										/* 팔로우 되어있지 않으면 팔로우 버튼이 보이게 */
    										if(<%=b%>==false) {
           		 								document.getElementById("ufl_btn"'${followerDtos.follower}').style.display = "none";
           		 								document.getElementById("fl_btn"'${followerDtos.follower}').style.display = "block";
    										}
    										/* 팔로우 되어있으면 언팔로우 버튼이 보이게 */
    										else {
          		  								document.getElementById("fl_btn"'${followerDtos.follower}').style.display = "none";
         		  								document.getElementById("ufl_btn"'${followerDtos.follower}').style.display = "block";
    										}
   		 									</script>

										</div>
									</td>
								</tr>
								</c:forEach>
								<%
									//folow_i 가 0이라면 팔로워가 없을때 
									
									if (follower_i == 0) {
								%>
								<tr>
									<h3>팔로워가 없습니다. ㅠㅠ</h3>
								</tr>
								<%
									}
								%>
							</table>
						</div>

						<div id="modal_follow_lists">
							<!--팔로우(내가 팔로우한 사람) 1명 값-->
							<table class="fl_table_modal">
								<%
									//해당 유저의 팔로우(해당유저가 팔로우한 사람)
									String chkFlowSql = "select * from bfollow where follower='" + sID + "'";
									ArrayList<Follow> listOfFollow = dao.SelectFollow(chkFlowSql);

									int follow_i = 0;
									ArrayList<String> owList = new ArrayList<>();
									//while문 수행 : 1명이라도 팔로워가 있다면
									for (follow_i = 0; follow_i < listOfFollow.size(); follow_i++) {
										DButil Dbui = new DButil();
										Follow obj_follow = listOfFollow.get(follow_i);
										owList.add(follow_i, obj_follow.getFollow());

										ResultSet rsForName = Dbui.selectSQL("select * from member where id='" + owList.get(follow_i) + "'");
										boolean b2 = rsForName.next();
								%>
								<!-- 팔로우/언팔로우버튼 ajax Script 2 -->
								<script type="text/javascript" charset="utf-8">
                            	var Followrequest2 = new XMLHttpRequest();
                            	var Unfollowrequest2 = new XMLHttpRequest();
                            	
                            	function insertFollow2<%=follow_i%>() {
                        			Followrequest2.open("Post","./FollowRegisterServlet?follow="+ encodeURIComponent(document.getElementById("followID2<%=follow_i%>").value) +
                        								 "&follower="+ encodeURIComponent(document.getElementById("followerID2<%=follow_i%>").value), true);
                        			
                        			Followrequest2.onreadystatechange = FollowProcesss<%=follow_i%>;
                        			Followrequest2.send(null);
                        		}
                            	function FollowProcesss<%=follow_i%>() {
                            		if(Followrequest2.readyState == 4 && Followrequest2.status == 200) {
                        				var getWrite = Followrequest2.responseText;
                        				if(getWrite == 0) {
                        					alert("팔로우를 실패하였습니다! (DB에러)");
                        				} 
                        				else {
                        					document.getElementById("fl_btn2<%=follow_i%>").style.display = "none";
                        	  				document.getElementById("ufl_btn2<%=follow_i%>").style.display = "block";
                        				}
                        			}
                            	}
                            	
                            	function doUnFollow2<%=follow_i%>() {
                        			Unfollowrequest2.open("Post","./UnfollowServlet?follow="+ encodeURIComponent(document.getElementById("unfollowID2<%=follow_i%>").value) +
                        					 "&follower="+ encodeURIComponent(document.getElementById("unfollowerID2<%=follow_i%>").value), true);
                        			Unfollowrequest2.onreadystatechange = UnfollowProcesss<%=follow_i%>;
                        			Unfollowrequest2.send(null);
                        		}
                            	function UnfollowProcesss<%=follow_i%>() {
                            		if(Unfollowrequest2.readyState == 4 && Unfollowrequest2.status == 200) {
                        				var getWrite = Unfollowrequest2.responseText;
                        				if(getWrite == 0) {
                        					alert("팔로우를 실패하였습니다! (DB에러)");
                        				}
                        				else {
                        					document.getElementById("ufl_btn2<%=follow_i%>").style.display = "none";
                        	 				document.getElementById("fl_btn2<%=follow_i%>").style.display = "block";
                        				}
                        			}
                            	}
                            </script>
								<tr>
									<td>
										<form action="OtherUser_Page.jsp" method="post"
											class="modal_fl_frm">
											<input type="hidden" value="<%=owList.get(follow_i)%>" name="otherUserID" /> 
											<input type="hidden" value="<%=rsForName.getString("name")%>" name="otherUserName" /> 
											<input name="otherUser" class="fl_id_submit" type="submit" value="<%=owList.get(follow_i)%>" />
										</form>
										<p class="modal_frm_realname"><%=rsForName.getString("name")%></p>
									</td>
									<td>
										<div class="fl_ufl_btn_wrap">

											<input type="hidden" id="followerID2<%=follow_i%>" value="<%=sID%>" name="unfollower_session" /> 
											<input type="hidden" id="followID2<%=follow_i%>" value="<%=owList.get(follow_i)%>" name="unfollow_mem" />
											<button type="button" class="modal_fl_btn" id="fl_btn2<%=follow_i%>" onclick="insertFollow2<%=follow_i%>()">팔로우</button>

											<input type="hidden" id="unfollowerID2<%=follow_i%>" value="<%=sID%>" name="unfollower_session" /> 
											<input type="hidden" id="unfollowID2<%=follow_i%>" value="<%=owList.get(follow_i)%>" name="unfollow_mem" />
											<button type="button" class="modal_ufl_btn" id="ufl_btn2<%=follow_i%>" onclick="doUnFollow2<%=follow_i%>()">언팔로우</button>

											<%
												String isFollowSQL = "select * from bfollow where follow='" + owList.get(follow_i) + "' and follower='"
															+ sID + "'";
													ResultSet isFollowRS = Dbui.selectSQL(isFollowSQL);
													boolean bb = isFollowRS.next();
											%>
											<script type="text/javascript">
    									/* 팔로우 되어있지 않으면 팔로우 버튼이 보이게 */
    									if(<%=bb%>==false) {
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
									}
									if (follow_i == 0) {
								%>
								<tr>
									<h3>팔로우가 없습니다. 팔로우를 찾아보세요!</h3>
								</tr>
								<% }%>
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
    		IDao iDao = new IDao();
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