/**
 * description 用户登录
 * @author Bright
 * */

//休息时间true,正常false
var offTime = false;

function login() {
	if(offTime){
		alert('很抱歉现在属于维护时间，系统不能使用，明日上午9点后可恢复，给您带来不便深感歉意。');
		return false;
	}
	if(check()) {
        $.ajax({
            type: "POST",
            url: ctx + "/member/login",
            data: {
                userName: $("#username").val(),
                password: $("#passwd").val(),
                remember: $('input[type=checkbox]:checked').val()
            },

            dataType: "json",
            success: function (resp) {
                if (resp.successful) {
                    window.location.href = ctx + "/index"
                } else{
                    $(".alert,.alert-danger,.display-hide").removeClass("display-hide").text("用户名或密码不正确，请重新输入");
                }
            },
            error: function (error) {
                console.log("error==", error);
                $(".alert,.alert-danger,.display-hide").removeClass("display-hide").text("登录失败，请重新输入");
            }
        });
    }
}

/**
 * description 检测账号和密码
 * @author Bright
 * */
function check() {
    var userName = $("#username").val();
	var password = $("#passwd").val();
	if(!userName||!userName.trim()){
        $(".alert,.alert-danger,.display-hide").removeClass("display-hide").text("用户名不能为空，请重新输入");
		return false;
	}else if(/^[\u4e00-\u9fa5]+$/.test(userName)){
        $(".alert,.alert-danger,.display-hide").removeClass("display-hide").text("用户名不能为汉字，请重新输入");
		return false;
	}else if(!password||!password.trim()){
        $(".alert,.alert-danger,.display-hide").removeClass("display-hide").text("密码不能为空，请重新输入");
        return false;
	}
	return true;
}

/*function showLoadPanel(){
	$("#loadPanel").show();
	$('#loadPanel').removeClass().addClass('fadeIn animated');
}

function hideLoadPanel(loaded){
	setTimeout(function(){
		$('#loadPanel').removeClass().addClass('fadeOut animated').one('animationend', function(){
			$("#loadPanel").hide();
			if(loaded){
				loaded();
			}
		});
	},500)
}

function success() {
	var ui = document.getElementById("suc");
	ui.style.display = "block";
}

function wrong() {
	var ui = document.getElementById("wor");
	ui.style.display = "block";
}

function suc() {
	$.get(ctx + '/loginAuthentication/getContextPath').success(function(result){
		window.location.href = result.data;
	})
	return;
	//window.location.href = ctx;
	//if(window.event){
	//	window.event.returnValue=false;
	//}
}
function wor() {
	var ui = document.getElementById("wor");
	ui.style.display = "none";
}
function removeUserName() {
	$('#userName').val('');
}
function removePassword() {
	$('#password').val('');
}*/
