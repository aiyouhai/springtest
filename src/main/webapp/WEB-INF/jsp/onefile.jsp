<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <form action="${pageContext.request.contextPath }/file/uploadone"
        method="post" enctype="multipart/form-data">
        选择文件：<input type="file" name="myfile"><br>
        文件描述：<input type="text" name="description"><br>
        <input type="submit" value="提交">
    </form>
</body>
</html>