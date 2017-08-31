<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%--<%@ include file="/common/taglibs.jsp"%>--%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Error</title>
</head>
<body>
<div id="wrapper">
    <div class="bg-white">
        <div class="hint-404">
            <h2>页面出错啦！</h2>
            <h3>
                测试正在调试 <br>开发人员在拼命修改！
            </h3>
            <h4>
                你或许可以浏览其它页面或者 <a href="#">返回主页</a>
            </h4>
        </div>
        <!-- end of row-fluid -->
        <c:set var="exception" value="${requestScope.exception}" />
        <c:if test="${!empty exception}">
            <!-- Exception:${exception} -->
            <!-- Message:${exception.message} -->
            <!-- STACK TRACE -->
            <c:forEach items="${exception.stackTrace}" var="trace">
                <!-- ${trace} -->
            </c:forEach>
            <!-- CAUSE:${exception.cause} -->
        </c:if>
    </div>
</div>
</body>
</html>