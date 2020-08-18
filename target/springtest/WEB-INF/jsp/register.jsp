<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <form action="${pageContext.request.contextPath }/index/user/register" method="post" name="registForm">
        <table border=1 bgcolor="lightblue" align="center">
            <tr>
                <td>姓名：</td>
                <td>
                    <input class="textSize" type="text" name="uname"  />
                </td>
            </tr>
            <tr>
                <td>密码：</td>
                <td>
                    <input class="textSize" type="password" maxlength="20" name="upass" />
                </td>
            </tr>
            <tr>
                <td>确认密码：</td>
                <td>
                    <input class="textSize" type="password" maxlength="20" name="reupass" />
                </td>
            </tr>
            <tr>
               <input type="submit" value="注册">
            </tr>
        </tab1e>
    </form>
</body>
</html>