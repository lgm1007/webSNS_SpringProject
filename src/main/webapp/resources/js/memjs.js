

function infoConfirm() {
    if (document.reg_frm.id.value.length == 0) {
        alert("아이디를 입력해야 합니다.");
        reg_frm.id.focus();
        return;
    }

    else if (document.reg_frm.pw.value.length == 0) {
        alert("비밀번호를 입력해야 합니다.");
        reg_frm.pw.focus();
        return;
    }

    else if (document.reg_frm.pw.value != document.reg_frm.pw_chk.value) {
        alert("비밀번호가 일치하지 않습니다.");
        reg_frm.pw.focus();
        return;
    }

    else if (document.reg_frm.name.value.length == 0) {
        alert("이름을 입력해야 합니다.");
        reg_frm.name.focus();
        return;
    }

    else {
    	document.reg_frm.submit();
    }
    
}


function infoUpdate() {
	if (document.formtag.update_pw.value != document.formtag.update_pw_chk.value) {
		alert("비밀번호가 일치하지 않습니다.");
		formtag.update_pw.focus();
		return;
	} 
	
	else {
		document.formtag.submit();
	}
}