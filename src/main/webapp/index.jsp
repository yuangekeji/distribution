<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script>
    var _ctx = "${ctx}";
    var ctx = _ctx;
    var version = '${version}';
</script>
<!--[if IE 8]> <html lang="en" class="ie8 no-js" data-ng-app="app"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js" data-ng-app="app"> <![endif]-->
<html ng-app="app">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title></title>
    <!-- metronic  start -->
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" />
    <link href="${ctx}/resources/metronic/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <%--<link href="${ctx}/resources/metronic/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />--%>
    <link href="${ctx}/resources/metronic/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <%--<link href="${ctx}/resources/metronic/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />--%>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN DYMANICLY LOADED CSS FILES(all plugin and page related styles must be loaded between GLOBAL and THEME css files ) -->
    <link id="ng_load_plugins_before" />
    <!-- END DYMANICLY LOADED CSS FILES -->
    <!-- BEGIN THEME STYLES -->
    <!-- DOC: To use 'rounded corners' style just load 'components-rounded.css' stylesheet instead of 'components.css' in the below style tag -->
    <link href="${ctx}/resources/metronic/global/css/components.min.css" id="style_components" rel="stylesheet" type="text/css" />
    <link href="${ctx}/resources/metronic/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/resources/metronic/layouts/layout2/css/layout.min.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/resources/metronic/layouts/layout2/css/themes/grey.min.css" rel="stylesheet" type="text/css" id="style_color" />
    <link href="${ctx}/resources/metronic/layouts/layout2/css/custom.min.css" rel="stylesheet" type="text/css" />
    <!-- END THEME STYLES -->

    <!-- metronic  end -->

    <!-- BEGIN CORE JQUERY PLUGINS -->
    <!--[if lt IE 9]>
    <script src="${ctx}/resources/metronic/global/plugins/respond.min.js"></script>
    <script src="${ctx}/resources/metronic/global/plugins/excanvas.min.js"></script>
    <![endif]-->
    <script src="${ctx}/resources/metronic/global/plugins/jquery.min.js" type="text/javascript"></script>
    <script src="${ctx}/resources/metronic/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${ctx}/resources/metronic/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
    <%--<script src="${ctx}/resources/metronic/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>--%>
    <%--<script src="${ctx}/resources/metronic/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>--%>
    <%--<script src="${ctx}/resources/metronic/global/plugins/js.cookie.min.js" type="text/javascript"></script>--%>
    <%--<script src="${ctx}/resources/metronic/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>--%>
    <%--<script src="${ctx}/resources/metronic/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>--%>
    <%--<script src="${ctx}/resources/metronic/global/plugins/datatables/datatables.all.min.js" type="text/javascript"></script>--%>
    <!-- END CORE JQUERY PLUGINS -->

    <!-- BEGIN CORE ANGULARJS PLUGINS -->
    <script src="${ctx}/resources/metronic/global/plugins/angularjs/angular.min.js" type="text/javascript"></script>
    <script src="${ctx}/resources/metronic/global/plugins/angularjs/angular-sanitize.min.js" type="text/javascript"></script>
    <script src="${ctx}/resources/metronic/global/plugins/angularjs/angular-touch.min.js" type="text/javascript"></script>
    <script src="${ctx}/resources/metronic/global/plugins/angularjs/plugins/angular-ui-router.min.js" type="text/javascript"></script>
    <script src="${ctx}/resources/metronic/global/plugins/angularjs/plugins/ocLazyLoad.min.js" type="text/javascript"></script>
    <script src="${ctx}/resources/metronic/global/plugins/angularjs/plugins/ui-bootstrap-tpls.min.js" type="text/javascript"></script>
    <!-- END CORE ANGULARJS PLUGINS -->
    <script src="${ctx}/resources/metronic/global/scripts/ngStorage.js?v=${version}"></script>
    <script src="${ctx}/app/app.js?v=${version}"></script>
    <script src="${ctx}/app/config.js?v=${version}"></script>
    <script src="${ctx}/app/router.js?v=${version}"></script>

    <script src="${ctx}/app/common/service/title.service.js?v=1"></script>
    <script src="${ctx}/app/common/directive/loading.directive.js?v=1"></script>


<body ng-controller="AppCtrl" class="page-header-fixed page-sidebar-closed-hide-logo page-container-bg-solid" ng-class="{'page-sidebar-closed': settings.layout.pageSidebarClosed}">
<div ui-view></div>
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN APP LEVEL JQUERY SCRIPTS -->
<script src="${ctx}/resources/metronic/global/scripts/app.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/metronic/layouts/layout2/scripts/layout.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/metronic/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/metronic/layouts/global/scripts/quick-nav.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/metronic/layouts/layout2/scripts/demo.min.js" type="text/javascript"></script>
<!-- END APP LEVEL JQUERY SCRIPTS -->
<!-- END JAVASCRIPTS --
</body>
</html>