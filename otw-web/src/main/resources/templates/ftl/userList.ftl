<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
    <title>用户数据</title>
</head>
<body>
<table border="1" width="50%" align="center">
    <tr>
        <th>ID</th>
        <th>NAME</th>
    </tr>
    <#list userList as user>
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
        </tr>
    </#list>
</table>
</body>
</html>