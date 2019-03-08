<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑商品</title>
</head>
<body>
    <h3>添加商品</h3>
<form action="/product?cmd=save" method="post">
    <input type="hidden" name="id" value="${products.id}">
    <table border="1">
        <tr>
            <td>商品名称</td>
            <td><input type="text" name="productName" value="${products.productName}"></td>
        </tr>
        <tr>
            <td>商品品牌</td>
            <td><input type="text"name="brand" value="${products.brand}"></td>
        </tr>
        <tr>
            <td>供应商</td>
            <td><input type="text" name="supplier" value="${products.supplier}"></td>
        </tr>
        <tr>
            <td>零售价</td>
            <td><input type="number" name="salePrice" value="${products.salePrice}"></td>
        </tr>
        <tr>
            <td>成本价</td>
            <td><input type="number" name="costPrice" value="${products.costPrice}"></td>
        </tr>
        <tr>
            <td>折扣</td>
            <td><input type="text" name="cutoff" value="${products.cutoff}"></td>
        </tr>
        <tr>
            <td>商品分类</td>
            <td><select name="dir_id">
                <c:forEach items="${dir_list}" var="dir">
                <option value="${dir.id}" ${dir.id==products.dir_id?"selected":""}>${dir.dirName}</option>
                </c:forEach>
            </select>
        </tr>
        <tr><td colspan="2"><input type="submit" value="保存"></td></tr>
    </table>
</form>
</body>
</html>
