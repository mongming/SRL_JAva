<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/21 0021
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
    String contextPath = request.getContextPath();
    request.setAttribute("basePath", basePath);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="text.do" method="post">
    用户名:<input type="text" name="userName" id="userName"><br/>
    密码：<input type="password" name="userPwd" id="userPwd"><br/>
    <input type="submit" value="提交">
</form>

<script type="application/javascript">

</script>
</body>
</html>
