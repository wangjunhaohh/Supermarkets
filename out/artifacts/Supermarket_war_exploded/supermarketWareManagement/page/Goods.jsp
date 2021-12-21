<%@ page import="bean.Good" %>
<%@ page import="home.sqlD" %><%--
  Created by IntelliJ IDEA.
  User: OMEN
  Date: 2021/4/30
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>超市仓库管理系统</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <%
        String id="2345928";
        String str=request.getParameter("id");
        if(str!=null){
            id=str;
        }
        Good good= sqlD.findById(id);
    %>
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">超市仓库管理系统</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">购物</a></li>
                <li><a href="javascript:void(0);" class="bottom-btn" onclick="code()">结算</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        帮助
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#">用户须知</a></li>
                        <li><a href="#">用户会员注册</a></li>
                        <li class="divider"></li>
                        <li><a href="#">退出系统</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="row">
    <div class="col-lg-1">
    </div>
    <div class="col-lg-4" >
        <div class="list-group">
            <a href="#" class="list-group-item active">
                商品信息
            </a>
            <a href="#"  class="list-group-item">编号: <%=good.getId()%></a>
            <a href="#" class="list-group-item">商品名: <%=good.getGoods()%></a>
            <a href="#" class="list-group-item">数量: <%=good.getNum()%></a>
            <a href="#" class="list-group-item">时间: <%=good.getTime()%></a>
            <a href="#" class="list-group-item">供货商: <%=good.getSupplier()%></a>
        </div>

    </div>
    <div class="col-lg-7">
        <img width="560" src="../images/<%=good.getPhoto()%>">
    </div>
</div>
<script>
    function code(){
        window.open('http://localhost:8080/Supermarket_war_exploded/supermarketWareManagement/page/addSales.jsp?id=<%=good.getId()%>')
    }
</script>
</body>
</html>
