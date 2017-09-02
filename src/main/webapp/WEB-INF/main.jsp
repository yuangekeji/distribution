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
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title></title>

    <link rel="icon" href="favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
    <link rel="bookmark" href="favicon.ico" type="image/x-icon" />
    <!-- metronic  start -->
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/metronic/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/metronic/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/metronic/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/xcConfirm/css/xcConfirm.css" rel="stylesheet" type="text/css"/>
    <%--<link href="${ctx}/static/metronic/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />--%>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN DYMANICLY LOADED CSS FILES(all plugin and page related styles must be loaded between GLOBAL and THEME css files ) -->
    <link id="ng_load_plugins_before" />
    <!-- END DYMANICLY LOADED CSS FILES -->
    <!-- BEGIN THEME STYLES -->
    <!-- DOC: To use 'rounded corners' style just load 'components-rounded.css' stylesheet instead of 'components.css' in the below style tag -->
    <link href="${ctx}/static/metronic/global/css/components.min.css" id="style_components" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/metronic/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/metronic/layouts/layout2/css/layout.min.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/metronic/layouts/layout2/css/themes/grey.min.css" rel="stylesheet" type="text/css" id="style_color" />
    <link href="${ctx}/static/metronic/layouts/layout2/css/custom.min.css" rel="stylesheet" type="text/css" />
    <!-- END THEME STYLES -->
    <link href="${ctx}/static/metronic/layouts/layout2/css/custom.min.css" rel="stylesheet" type="text/css" />
    <!-- metronic  end -->
    <link href="${ctx}/static/angular-ui-tree/angular-ui-tree.css" rel="stylesheet" type="text/css"/>
    <!-- BEGIN CORE JQUERY PLUGINS -->
    <!--[if lt IE 9]>
    <script src="${ctx}/static/metronic/global/plugins/respond.min.js"></script>
    <script src="${ctx}/static/metronic/global/plugins/excanvas.min.js"></script>
    <![endif]-->
    <script src="${ctx}/static/metronic/global/plugins/jquery.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/metronic/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/metronic/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
    <%--<script src="${ctx}/static/metronic/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>--%>
    <script src="${ctx}/static/metronic/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/metronic/global/plugins/js.cookie.min.js" type="text/javascript"></script>
    <%--<script src="${ctx}/static/metronic/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>--%>
    <%--<script src="${ctx}/static/metronic/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>--%>
    <%--<script src="${ctx}/static/metronic/global/plugins/datatables/datatables.all.min.js" type="text/javascript"></script>--%>
    <!-- END CORE JQUERY PLUGINS -->

    <script src="${ctx}/static/xcConfirm/js/xcConfirm.js" type="text/javascript" charset="utf-8"></script>

    <!-- BEGIN CORE ANGULARJS PLUGINS -->
    <script src="${ctx}/static/metronic/global/plugins/angularjs/angular.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/metronic/global/plugins/angularjs/angular-sanitize.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/metronic/global/plugins/angularjs/angular-touch.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/metronic/global/plugins/angularjs/plugins/angular-ui-router.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/metronic/global/plugins/angularjs/plugins/ocLazyLoad.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/metronic/global/plugins/angularjs/plugins/ui-bootstrap-tpls.min.js" type="text/javascript"></script>
    <!-- END CORE ANGULARJS PLUGINS -->
    <script src="${ctx}/static/metronic/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>

    <script src="${ctx}/static/angular-ui-tree/main.js" type="text/javascript"></script>
    <script src="${ctx}/static/angular-ui-tree/controllers/handleCtrl.js" type="text/javascript"></script>
    <script src="${ctx}/static/angular-ui-tree/controllers/nodeCtrl.js" type="text/javascript"></script>
    <script src="${ctx}/static/angular-ui-tree/controllers/nodesCtrl.js" type="text/javascript"></script>
    <script src="${ctx}/static/angular-ui-tree/controllers/treeCtrl.js" type="text/javascript"></script>
    <script src="${ctx}/static/angular-ui-tree/directives/uiTree.js" type="text/javascript"></script>
    <script src="${ctx}/static/angular-ui-tree/directives/uiTreeHandle.js" type="text/javascript"></script>
    <script src="${ctx}/static/angular-ui-tree/directives/uiTreeNode.js" type="text/javascript"></script>
    <script src="${ctx}/static/angular-ui-tree/directives/uiTreeNodes.js" type="text/javascript"></script>
    <script src="${ctx}/static/angular-ui-tree/services/helper.js" type="text/javascript"></script>

    <%--<script src="source/controllers/handleCtrl.js"></script>--%>
    <%--<script src="source/controllers/nodeCtrl.js"></script>--%>
    <%--<script src="source/controllers/nodesCtrl.js"></script>--%>
    <%--<script src="source/controllers/treeCtrl.js"></script>--%>
    <%--<script src="source/directives/uiTree.js"></script>--%>
    <%--<script src="source/directives/uiTreeHandle.js"></script>--%>
    <%--<script src="source/directives/uiTreeNode.js"></script>--%>
    <%--<script src="source/directives/uiTreeNodes.js"></script>--%>
    <%--<script src="source/services/helper.js"></script>--%>


    <script src="${ctx}/static/metronic/global/scripts/ngStorage.js?v=${version}"></script>
    <script src="${ctx}/app/common/pagination/dirPagination.js" type="text/javascript" charset="utf-8"></script>

    <script src="${ctx}/app/app.js?v=${version}"></script>
    <script src="${ctx}/app/config.js?v=${version}"></script>
    <script src="${ctx}/app/router.js?v=${version}"></script>
    <script src="${ctx}/app/directives.js?v=${version}"></script>
    <script src="${ctx}/app/controllers.js?v=${version}"></script>
    <script src="${ctx}/app/services.js?v=${version}"></script>
    <script src="${ctx}/app/filter.js?v=${version}"></script>

<body ng-controller="AppCtrl" class="page-header-fixed page-sidebar-closed-hide-logo page-container-bg-solid" ng-class="{'page-sidebar-closed': settings.layout.pageSidebarClosed}">
<!-- BEGIN PAGE SPINNER -->
<div ng-spinner-bar class="page-spinner-bar">
    <div class="bounce1"></div>
    <div class="bounce2"></div>
    <div class="bounce3"></div>
</div>
<!-- END PAGE SPINNER -->
<div ui-view></div>
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN APP LEVEL JQUERY SCRIPTS -->
<script src="${ctx}/static/metronic/global/scripts/app.js" type="text/javascript"></script>
<script src="${ctx}/static/metronic/layouts/layout2/scripts/layout.min.js" type="text/javascript"></script>
<script src="${ctx}/static/metronic/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
<script src="${ctx}/static/metronic/layouts/global/scripts/quick-nav.min.js" type="text/javascript"></script>
<script src="${ctx}/static/metronic/layouts/layout2/scripts/demo.min.js" type="text/javascript"></script>

<!-- END APP LEVEL JQUERY SCRIPTS -->
<!-- END JAVASCRIPTS --
</body>
</html>