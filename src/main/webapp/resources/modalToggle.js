 /*팔로워목록 버튼누르자마자 팔로워먼저 보여주기 -> 팔로워목록 버튼에 onclick이벤트 view_follower()*/

function view_follower() {
    document.getElementById("modal_follow_lists").style.display = "none";
    document.getElementById("modal_follower_lists").style.display = "block";    
}
function view_follow() {
    document.getElementById("modal_follower_lists").style.display = "none";
    document.getElementById("modal_follow_lists").style.display = "block";
}
