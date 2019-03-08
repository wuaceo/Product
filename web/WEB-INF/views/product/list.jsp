<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>商品列表</title>
</head>
<body>
<h3>商品列表</h3>
<span>当前登录用户：${sessionScope.SESSION_ON_USER.username}</span>
<a href="/logout">注销</a>
<hr>
<form action="/product" method="post">
    商品名称：<input  type="text" name="name" value="${queryObject.name}"><br>
    商品价格：<input type="text" name="minSalePrice" value="${queryObject.minSalePrice}" style="width: 72px">到
    <input type="text" name="maxSalePrice" value="${queryObject.maxSalePrice}"  style="width: 72px"><br>
    商品分类：
    <select name=dir_id>
        <option value="-1">无</option>
        <c:forEach items="${Dirs}" var="dir">
            <option value="${dir.id}" ${dir.id==queryObject.dir_id?"selected":""}>${dir.dirName}</option>
        </c:forEach>
    </select>
    <input type="submit" value="查询" style="border-color: blue; background-color: orange;">
    
</form>

<a href="/product?cmd=edit">添加商品</a>
    <table border="1">
        <tr>
            <th>商品编号</th>
            <th>商品名称</th>
            <th>商品品牌</th>
            <th>商品分类</th>
            <th>供应商</th>
            <th>零售价</th>
            <th>成本价</th>
            <th>折扣</th>
            <th colspan="2">操作</th>
        </tr>
        <c:forEach items="${products}" var="product">
        <tr>
            <td>${product.id}</td>
            <td>${product.productName}</td>
            <td>${product.brand}</td>
            <td>
                <c:choose>
                    <c:when test="${product.dir_id==1}">鼠标</c:when>
                    <c:when test="${product.dir_id==2}">无线鼠标</c:when>
                    <c:when test="${product.dir_id==3}">有线鼠标</c:when>
                    <c:when test="${product.dir_id==4}">义乌小商品</c:when>
                </c:choose>
            </td>
            <td>${product.supplier}</td>
            <td>${product.salePrice}</td>
            <td>${product.costPrice}</td>
            <td>${product.cutoff}</td>
            <td><a href="/product?cmd=edit&id=${product.id}">编辑</a></td>
            <td><a href="/product?cmd=delete&id=${product.id}">删除</a></td>
        </tr>
        </c:forEach>
    </table>
</body>
</html>
