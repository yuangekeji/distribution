<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="roleId" value="${sessionScope.currentUserRole}"/>
<c:set var="userStatus" value="${sessionScope.currentUserStatus}"/>

<script>

    var _ctx = "${ctx}";
    var ctx = _ctx;
    var roleId ="${roleId}";
    var userStatus ="${userStatus}";
    var version = Math.floor(Math.random()*10);

</script>
<!--[if IE 8]> <html lang="en" class="ie8 no-js" data-ng-app="app"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js" data-ng-app="app"> <![endif]-->
<html data-ng-app="app">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title></title>

    <link rel="icon" href="static/favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="static/favicon.ico" type="image/x-icon" />
    <link rel="bookmark" href="static/favicon.ico" type="image/x-icon" />
    <!-- metronic  start -->
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <%--<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" />--%>
    <link href="${ctx}/static/metronic/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/metronic/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN DYMANICLY LOADED CSS FILES(all plugin and page related styles must be loaded between GLOBAL and THEME css files ) -->
    <link id="ng_load_plugins_before" />
    <!-- END DYMANICLY LOADED CSS FILES -->
    <!-- BEGIN THEME STYLES -->
    <!-- DOC: To use 'rounded corners' style just load 'components-rounded.css' stylesheet instead of 'components.css' in the below style tag -->
    <link href="${ctx}/static/metronic/global/css/components.min.css" id="style_components" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/metronic/layouts/layout2/css/layout.min.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/metronic/layouts/layout2/css/themes/blue.min.css" rel="stylesheet" type="text/css" id="style_color" />
    <link href="${ctx}/static/metronic/layouts/layout2/css/custom.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/notify/notify.min.css" rel="stylesheet" type="text/css" />
    <%--<link href="${ctx}/static/metronic/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />--%>
    <%--<link href="${ctx}/static/metronic/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css" />--%>
    <!-- metronic  end -->
<body ng-controller="AppCtrl" class="page-header-fixed page-sidebar-closed-hide-logo page-container-bg-solid" ng-class="{'page-sidebar-closed': settings.layout.pageSidebarClosed}">
<!-- BEGIN PAGE SPINNER -->
<div ng-spinner-bar class="page-spinner-bar">
    <div class="bounce1"></div>
    <div class="bounce2"></div>
    <div class="bounce3"></div>
</div>
<!-- END PAGE SPINNER -->
<div ui-view class="full-view"></div>

<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE JQUERY PLUGINS -->
<!--[if lt IE 9]>
<script src="${ctx}/static/metronic/global/plugins/respond.min.js"></script>
<script src="${ctx}/static/metronic/global/plugins/excanvas.min.js"></script>
<![endif]-->
<script src="${ctx}/static/metronic/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/static/metronic/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${ctx}/static/metronic/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<!-- END CORE JQUERY PLUGINS -->
<!-- BEGIN CORE ANGULARJS PLUGINS -->
<script src="https://cdn.bootcss.com/angular.js/1.5.8/angular.min.js" type="text/javascript"></script>
<script src="${ctx}/static/metronic/global/plugins/angularjs/plugins/angular-ui-router.min.js" type="text/javascript"></script>
<script src="${ctx}/static/metronic/global/plugins/angularjs/plugins/ocLazyLoad.min.js" type="text/javascript"></script>
<script src="${ctx}/static/metronic/global/plugins/angularjs/plugins/ui-bootstrap-tpls.min.js" type="text/javascript"></script>
<!-- END CORE ANGULARJS PLUGINS -->
<script src="${ctx}/static/metronic/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
<script src="${ctx}/static/metronic/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js" type="text/javascript"></script>
<script src="${ctx}/static/metronic/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script src="${ctx}/static/notify/notify.min.js" type="text/javascript"></script>
<script src="${ctx}/app/common/pagination/dirPagination.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${ctx}/static/metronic/global/scripts/ngStorage.js?v=${version}"></script>
<script src="${ctx}/app/app.min.js?v=${version}"></script>
<script src="${ctx}/app/config.min.js?v=${version}"></script>
<script src="${ctx}/app/router.min.js?v=${version}"></script>
<script src="${ctx}/app/controllers.min.js?v=${version}"></script>
<script src="${ctx}/app/services.min.js?v=${version}"></script>
<script src="${ctx}/app/filter.min.js?v=${version}"></script>
<!-- BEGIN APP LEVEL JQUERY SCRIPTS -->
<script src="${ctx}/static/metronic/global/scripts/app.min.js" type="text/javascript"></script>
<script src="${ctx}/static/metronic/layouts/layout2/scripts/layout.min.js" type="text/javascript"></script>
<!-- END APP LEVEL JQUERY SCRIPTS -->
<!-- END JAVASCRIPTS --
</body>
</html>