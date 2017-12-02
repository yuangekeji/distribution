<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script>
    var _ctx = "${ctx}";
    var ctx = _ctx;
    var version = '${version}';
</script>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8" />
    <title>用户登录</title>
    <link rel="icon" href="favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
    <link rel="bookmark" href="favicon.ico" type="image/x-icon" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport" />
    <meta content="Preview page of Metronic Admin Theme #2 for " name="description" />
    <meta content="" name="author" />
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="${ctx}/static/metronic/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/static/metronic/global/plugins/jquery.min.js" type="text/javascript"></script>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN THEME GLOBAL STYLES -->
    <%--<link href="${ctx}/static/metronic/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />--%>
    <!-- END THEME GLOBAL STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link href="${ctx}/static/metronic/pages/css/login.min.css" rel="stylesheet" type="text/css" />
    <!-- END PAGE LEVEL STYLES -->
    <link rel="shortcut icon" href="favicon.ico" /> </head>
<!-- END HEAD -->
<style>
    /*==========  Mobile First Method  ==========*/

    /* Custom, iPhone Retina */
    @media only screen and (min-width : 320px) {
        .login {
            background-color:#01407d !important;
            background-image:url('${ctx}/static/metronic/layouts/layout2/img/bg-m.jpg');
            background-size: 100% ;
            background-repeat: no-repeat;
            background-position: center top ;
        }
        .content{
            margin-top: 240px !important;
            width: 280px !important;
        }
    }

    /* Custom, iPhone Retina */
    @media only screen and (min-width : 370px) {
        .login {
            background-color:#01407d !important;
            background-image:url('${ctx}/static/metronic/layouts/layout2/img/bg-m.jpg');
            background-size: 100% ;
            background-repeat: no-repeat;
            background-position: center top ;
        }
        .content{
            margin-top: 280px !important;
            width: 330px !important;
        }
    }

    /* Custom, iPhone Retina */
    @media only screen and (min-width : 400px) {
        .login {
            background-color:#01407d !important;
            background-image:url('${ctx}/static/metronic/layouts/layout2/img/bg-m.jpg');
            background-size: 100% ;
            background-repeat: no-repeat;
            background-position: center top ;
        }
        .content{
            margin-top: 320px !important;
            width: 360px !important;
        }
    }
    /* Extra Small Devices, Phones */
    @media only screen and (min-width : 480px) {
        .login {
            background-color:#01407d !important;
            background-image:url('${ctx}/static/metronic/layouts/layout2/img/bg-m.jpg');
            background-size: 100%;
            background-repeat: no-repeat;
            background-position: center top ;
        }
        .content{
            margin-top: 450px !important;
            width: 400px !important;
        }
    }

    /* Small Devices, Tablets */
    @media only screen and (min-width : 768px) {
        .login {
            background-color:#01407d !important;
            background-image:url('${ctx}/static/metronic/layouts/layout2/img/bg-m.jpg');
            background-size: 100%;
            background-repeat: no-repeat;
            background-position: center top ;
        }
        .content{
            margin-top:550px !important;
            width: 400px !important;
        }
    }

    /* Medium Devices, Desktops */
    @media only screen and (min-width : 992px) {
        .login {
            background-color:#01407d !important;
            background-image:url('${ctx}/static/metronic/layouts/layout2/img/bg-pc.jpg');
            background-size: 600px 450px;
            background-repeat: no-repeat;
            background-position: center top ;
        }
        .content{
            margin-top: 250px !important;
            width: 320px !important;
        }
    }

    /* Large Devices, Wide Screens */
    @media only screen and (min-width : 1200px) {
        .login {
            background-color:#01407d !important;
            background-image:url('${ctx}/static/metronic/layouts/layout2/img/bg-pc.jpg');
            background-size: 1000px 800px;
            background-repeat: no-repeat;
            background-position: center top ;
        }
        .content{
            margin-top: 250px !important;
            width: 320px !important;
        }
    }

    .display-hide{
        display: none;
    }


</style>

<body class="login" >
<!-- BEGIN LOGIN -->
<div class="content"  style="border-radius: 10px !important;padding: 10px 30px !important;" >
    <!-- BEGIN LOGIN FORM -->
    <form class="login-form" method="post">
        <h4 class="form-title text-center" style="color: #01407d">欢迎登录</h4>
        <div class="alert alert-danger display-hide">
            <button class="close" data-close="alert"></button>
            <span>  </span>
        </div>
        <div class="form-group">
            <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
            <label class="control-label visible-ie8 visible-ie9">用户名</label>
            <input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off" placeholder="请输入用户名" name="username" id="username"/> </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">密码</label>
            <input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off" placeholder="请输入密码" name="password" id="passwd"/> </div>
        <div class="form-actions" style="border-bottom:none !important;padding: 10px 30px !important;">
            <input type="button" class="btn uppercase" onclick="login();"
                   style="background-color: #01407d;color: white;border-radius: 5px !important;" value="登录"/>
            <label class="rememberme check mt-checkbox mt-checkbox-outline">
                <input type="checkbox" name="remember" value="1" />管理员登录
                <span></span>
            </label>
            <%--<a href="javascript:;" id="forget-password" class="forget-password">忘记密码?</a>--%>
        </div>

    </form>
    <!-- END LOGIN FORM -->

</div>

<script src="${ctx}/static/js/login.js" type="text/javascript"></script>
<!--[if lt IE 9]>
<script src="${ctx}/static/metronic/global/plugins/respond.min.js"></script>
<script src="${ctx}/static/metronic/global/plugins/excanvas.min.js"></script>
<script src="${ctx}/static/metronic/global/plugins/ie8.fix.min.js"></script>
<![endif]-->
<!-- BEGIN CORE PLUGINS -->

<script src="${ctx}/static/metronic/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<script>
    $(document).ready(function()
    {
        $('.login-form input').keypress(function(e) {
            if (e.which == 13) {
                login();
                return false;
            }
        });
    })
</script>
</body>

</html>