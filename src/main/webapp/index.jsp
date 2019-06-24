<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Students</title>
    <meta charset="UTF-8"/>
</head>
<body>
<h1>Students</h1>
<ul>
    <%   if (request.getSession().getAttribute("user")!=null) { %>
    <li><a href="${pageContext.request.contextPath}/person/list">List students</a></li>
    <li><a href="${pageContext.request.contextPath}/person">New student</a></li>
    <% }
    else { %>
    <li><a href="${pageContext.request.contextPath}/auth">Авторизация</a></li>
    <% } %>
</ul>
</body>
</html>
