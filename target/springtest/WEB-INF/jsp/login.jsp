<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
${user.uname}注册成功，请登录 
<form action="${pageContext.request.contextPath }/index/login" method="post">
        <table>
            
            <tr>
                <td>姓名：</td>
                <td>
                    <input type="text" name="username" class="textSize">
                </td>
            </tr>
            <tr>
                <td>密码：</td>
                <td>
                    <input type="password" name="password" class="textsize">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                     <input type="submit" value="提交" />
                </td>
            </tr>
        </table>
        ${messageError }
    </form>
    
</body>
</html>