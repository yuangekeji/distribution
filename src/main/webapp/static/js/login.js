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
