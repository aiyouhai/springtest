<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <form action="${pageContext.request.contextPath }/my/converter" method="post">
        <table border=1 bgcolor="lightblue" align="center">
            <tr>
                <td>商品名称：</td>
                <td><input class="textSize" type="text" name="goodsname" /></td>
            </tr>
            <tr>
                <td>商品价格：</td>
                <td><input class="textSize" type="text" name="goodsprice" /></td>
            </tr>
            <tr>
                <td>商品数量：</td>
                <td><input class="textSize" type="text" name="goodsnumber" /></td>
            </tr>
            <tr>
                <td>商品日期：</td>
                <td><input class="textSize" type="text" name="goodsdate" /></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="提交" />
                </td>
            </tr>
        </tab1e>
    </form>
</body>
</html>