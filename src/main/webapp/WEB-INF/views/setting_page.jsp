<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>User Setting Page</title>
<link href="/resources/css/bootstrap.css" type="text/css" rel="stylesheet" />
<link href="/resources/style_main.css" type="text/css" rel="stylesheet" />
<style>
        input[type=text] {
            color: #908d8d;
            font-size: 11px;
        }

        input[type=tel] {
            color: #908d8d;
            font-size: 11px;
        }

        input[type=password] {
            color: #908d8d;
            font-size: 11px;
            width: 200px;
            height: 30px;
        }

        input[name=update_pw] {
            margin-left: 6%;
        }

        input[name=update_pw_chk] {
            margin-left: 2%;
        }

        input[type=submit], input[type=reset] {
            margin-top: 5%;
            background-color: #fafafa;
            font-size: 16px;
            font-weight: bold;
            border-style: none;
            border-radius: 4px;
            border: 0.5px solid #e8e8e8;
            padding: 4px 10px 4px 10px;
            cursor: pointer;
        }

       input[type=submit]:hover, input[type=reset]:hover {
           border: 0.5px solid #000000;
       }
</style>
</head>
<body>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" ></script>
    <script src="/resources/js/bootstrap.js"></script>
    <script src="/resources/Search.js" type="text/javascript"></script>
    <!-- Btn EventFunction -->
    <script>
        function edit_password() {
            document.getElementById("edit_pw").readOnly = false;
            document.getElementById("edit_pw2").readOnly = false;
            document.getElementById("edit_name").readOnly = true;
            document.getElementById("edit_tel1").readOnly = true;
            document.getElementById("edit_tel2").readOnly = true;
            document.getElementById("edit_tel3").readOnly = true;
        };
        function edit_etc() {
            document.getElementById("edit_pw").readOnly = true;
            document.getElementById("edit_pw2").readOnly = true;
            document.getElementById("edit_name").readOnly = false;
            document.getElementById("edit_tel1").readOnly = false;
            document.getElementById("edit_tel2").readOnly = false;
            document.getElementById("edit_tel3").readOnly = false;
        };
    </script>
    
    <div id="header">
        <div class="headbar_div">
            <ul class="headbar">
                <li class="head_logo"><a href="main_page.jsp"><img src="img/sol_logo_40px.png" alt="logo" /></a></li>
                <li class="head_icon">
                    <a href="#" class="icon_like"><img src="img/like.png" alt="like" /></a>
                    <a href="User_Page.html" class="icon_me"><img src="img/me.png" alt="me" /></a>
                    <a href="�۾����Է�.html" class="icon_pic"><img src="img/pic.png" alt="pic" /></a>
                </li>
            </ul>
            <div class="head_input">
                <form action="Search_Page.html" name="hd_frm" method="get">
                    <input type="search" id="keyword" placeholder="�˻�" onkeypress="if (event.keyCode == 13) { goSearch();}" style="text-align :center; font-size:13px; color:#cacaca; border:0.7px solid #cacaca;" />
                </form>
            </div>
        </div>
    </div>

    <div id="write_div_wrap">
        <div id="userEditform_wrap">
            <form class="user_update_formtag" action="#" method="post" name="formtag">
                <!--�̸��� ����ȣ�� ��������� ������ ������ Update, ��й�ȣ�� �ʼ��Է��ϱ�-->
                <h4 class="modify_inf">ȸ������ ����</h4>
                <p class="modify_inf2">������ �׸��� �ۼ����ּ���</p>
                <div class="user_update_btns"><button type="button" class="update_etc_check_btn" onclick="edit_etc()">ȸ������ ����</button>
                <button type="button" class="update_pw_check_btn" onclick="edit_password()">��й�ȣ ����</button></div> <br/>
                �̸�: <input type="text" id="edit_name" class="update_name" name="update_name" value="���� �̸�" /><br /><br />
                ��й�ȣ: <input type="password" id="edit_pw" name="update_pw" readonly="true" value="���� ��й�ȣ" /><br/><br/>
                ��й�ȣȮ��: <input type="password" id="edit_pw2" name="update_pw_chk" readonly="true" value="���� ��й�ȣ" /><br /><br />
                �޴��� ��ȣ:
                <select name="tel1" id="edit_tel1" class="tel">
                    <option value="010" selected>010</option>
                    <option value="011">011</option>
                    <option value="016">016</option>
                    <option value="017">017</option>
                    <option value="019">019</option>
                </select>
                 - <input type="tel" id="edit_tel2" class="tel" name="tel2" value="�޴�����ȣ 2"/> - <input type="tel" id="edit_tel3" class="tel" name="tel3" value="�޴�����ȣ 3"/>
                <br />
                <input type="submit" value="����" /> <input type="reset" value="���" /> <br/><br />
                <a href="#" class="go_out">Ż���ϱ�</a>
            </form>
        </div>
    </div>
	
</body>
</html>