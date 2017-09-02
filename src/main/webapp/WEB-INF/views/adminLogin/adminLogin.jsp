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
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/metronic/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <%--<link href="${ctx}/static/metronic/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />--%>
    <link href="${ctx}/static/metronic/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/static/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <%--<link href="${ctx}/static/metronic/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />--%>
    <!-- END GLOBAL MANDATORY STYLES -->

    <!-- BEGIN THEME GLOBAL STYLES -->
    <link href="${ctx}/static/metronic/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
    <link href="${ctx}/static/metronic/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
    <!-- END THEME GLOBAL STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link href="${ctx}/static/metronic/pages/css/login.min.css" rel="stylesheet" type="text/css" />
    <!-- END PAGE LEVEL STYLES -->
    <!-- BEGIN THEME LAYOUT STYLES -->
    <!-- END THEME LAYOUT STYLES -->
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
            margin-top: 200px !important;
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
            margin-top: 300px !important;
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
            margin-top:400px !important;
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
            margin-top: 350px !important;
        }
    }

    /* Large Devices, Wide Screens */
    @media only screen and (min-width : 1200px) {
        .login {
            background-color:#01407d !important;
            background-image:url('${ctx}/static/metronic/layouts/layout2/img/bg-pc.jpg');
            background-size: 800px 600px;
            background-repeat: no-repeat;
            background-position: center top ;
        }
        .content{
            margin-top: 350px !important;
        }
    }

</style>

<body class="login" >
<!-- BEGIN LOGIN -->
<div class="content"  >
    <!-- BEGIN LOGIN FORM -->
    <form class="login-form" method="post">
        <h3 class="form-title font-green">会员登录</h3>
        <div class="alert alert-danger display-hide">
            <button class="close" data-close="alert"></button>
            <span> 请输入用户名和密码 </span>
        </div>
        <div class="form-group">
            <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
            <label class="control-label visible-ie8 visible-ie9">用户名</label>
            <input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off" placeholder="请输入用户名" name="username" id="username"/> </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">密码</label>
            <input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off" placeholder="请输入密码" name="password" id="passwd"/> </div>
        <div class="form-actions">
            <input type="button" class="btn green uppercase" onclick="login();" value="登录"/>
            <label class="rememberme check mt-checkbox mt-checkbox-outline">
                <input type="checkbox" name="remember" value="1" />管理员登录
                <span></span>
            </label>
            <a href="javascript:;" id="forget-password" class="forget-password">忘记密码?</a>
        </div>

    </form>
    <!-- END LOGIN FORM -->
    <!-- BEGIN FORGOT PASSWORD FORM -->
    <form class="forget-form" method="post">
        <h3 class="font-green">忘记密码 ?</h3>
        <p> 输入手机号</p>
        <div class="form-group">
            <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Email" name="email" /> </div>
        <div class="form-actions">
            <button type="button" id="back-btn" class="btn green btn-outline">Back</button>
            <button type="submit" class="btn btn-success uppercase pull-right">Submit</button>
        </div>
    </form>
    <!-- END FORGOT PASSWORD FORM -->

</div>

<script src="${ctx}/static/js/login.js" type="text/javascript"></script>
<!--[if lt IE 9]>
<script src="${ctx}/static/metronic/global/plugins/respond.min.js"></script>
<script src="${ctx}/static/metronic/global/plugins/excanvas.min.js"></script>
<script src="${ctx}/static/metronic/global/plugins/ie8.fix.min.js"></script>
<![endif]-->
<!-- BEGIN CORE PLUGINS -->
<script src="${ctx}/static/metronic/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/static/metronic/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>

<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${ctx}/static/metronic/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/metronic/global/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
<%--<script src="${ctx}/static/metronic/global/plugins/select2/js/select2.full.min.js" type="text/javascript"></script>--%>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN THEME GLOBAL SCRIPTS -->
<script src="${ctx}/static/metronic/global/scripts/app.min.js" type="text/javascript"></script>
<!-- END THEME GLOBAL SCRIPTS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${ctx}/static/metronic/pages/scripts/login.min.js" type="text/javascript"></script>

<!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN THEME LAYOUT SCRIPTS -->
<!-- END THEME LAYOUT SCRIPTS -->
<script>
    $(document).ready(function()
    {
        $('#clickmewow').click(function()
        {
            $('#radio1003').attr('checked', 'checked');
        });
    })
</script>
</body>

</html>