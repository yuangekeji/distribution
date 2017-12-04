/**
 * description 用户登录
 * @author Bright
 * */


function login() {
    var login = false;
    var date = new Date();
    var now = date.getHours();
    if(now < 4){
        alert('凌晨12点到4点之间属于系统业务处理时间，给您带来不便深感歉意，请在其他时间访问系统，谢谢配合。');
        return;
    }
    if(login){
        alert('今日10:30后属于系统维护时间，给您带来不便深感歉意，请在其他时间访问系统，谢谢配合。');
        return false;
    }

    if(check()) {
        $.ajax({
            type: "POST",
            url: ctx + "/member/login",
            data: {
                userName: $("#username").val(),
                password: $("#passwd").val(),
                remember: $('input[type=checkbox]:checked').val(),
                code:$("#code").val()
            },

            dataType: "json",
            success: function (resp) {
                if (resp.successful) {
                    window.location.href = ctx + "/index"
                } else{
                    refresh();
                    if(resp.errorMessage == "memberDeleted") {
                        $(".alert,.alert-danger,.display-hide").removeClass("display-hide").text("用户账号已被禁用，请联系管理员");
                    }else if(resp.errorMessage == 'codeError') {
                        $(".alert,.alert-danger,.display-hide").removeClass("display-hide").text("验证码错误");
                    }else{
                        $(".alert,.alert-danger,.display-hide").removeClass("display-hide").text("用户名或密码不正确，请重新输入");
                    }
                }
            },
            error: function (error) {
                console.log("error==", error);
                $(".alert,.alert-danger,.display-hide").removeClass("display-hide").text("登录失败，请重新输入");
            }
        });
    }
}
function refresh() {
    var url = ctx + "/member/check?number="+Math.random();
    $("#codeImg").attr("src",url);
}
/**
 * description 检测账号和密码
 * @author Bright
 * */
function check() {


    var userName = $("#username").val();
    var password = $("#passwd").val();
    var code = $("#code").val();
    if(!userName||!userName.trim()){
        $(".alert,.alert-danger,.display-hide").removeClass("display-hide").text("用户名不能为空，请重新输入");
        return false;
    }else if(/^[\u4e00-\u9fa5]+$/.test(userName)){
        $(".alert,.alert-danger,.display-hide").removeClass("display-hide").text("用户名不能为汉字，请重新输入");
        return false;
    }else if(!password||!password.trim()){
        $(".alert,.alert-danger,.display-hide").removeClass("display-hide").text("密码不能为空，请重新输入");
        return false;
    }else if(!code||!code.trim()){
        $(".alert,.alert-danger,.display-hide").removeClass("display-hide").text("验证码不能为空，请重新输入");
        return false;
    }
    return true;
}
