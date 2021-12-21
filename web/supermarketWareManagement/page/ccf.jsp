<%--
  Created by IntelliJ IDEA.
  User: OMEN
  Date: 2021/4/18
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理-登陆</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="../lib/layui-v2.5.5/css/layui.css" media="all">
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>



    <![endif]-->
    <style>
        .main-body {top:50%;left:50%;position:absolute;-webkit-transform:translate(-50%,-50%);-moz-transform:translate(-50%,-50%);-ms-transform:translate(-50%,-50%);-o-transform:translate(-50%,-50%);transform:translate(-50%,-50%);overflow:hidden;}
        .login-main .login-bottom .center .item input {display:inline-block;width:227px;height:22px;padding:0;position:absolute;border:0;outline:0;font-size:14px;letter-spacing:0;}
        .login-main .login-bottom .center .item .icon-1 {background:url(../images/icon-login.png) no-repeat 1px 0;}
        .login-main .login-bottom .center .item .icon-2 {background:url(../images/icon-login.png) no-repeat -54px 0;}
        .login-main .login-bottom .center .item .icon-3 {background:url(../images/icon-login.png) no-repeat -106px 0;}
        .login-main .login-bottom .center .item .icon-4 {background:url(../images/icon-login.png) no-repeat 0 -43px;position:absolute;right:-10px;cursor:pointer;}
        .login-main .login-bottom .center .item .icon-5 {background:url(../images/icon-login.png) no-repeat -55px -43px;}
        .login-main .login-bottom .center .item .icon-6 {background:url(../images/icon-login.png) no-repeat 0 -93px;position:absolute;right:-10px;margin-top:8px;cursor:pointer;}
        .login-main .login-bottom .tip .icon-nocheck {display:inline-block;width:10px;height:10px;border-radius:2px;border:solid 1px #9abcda;position:relative;top:2px;margin:1px 8px 1px 1px;cursor:pointer;}
        .login-main .login-bottom .tip .icon-check {margin:0 7px 0 0;width:14px;height:14px;border:none;background:url(../images/icon-login.png) no-repeat -111px -48px;}
        .login-main .login-bottom .center .item .icon {display:inline-block;width:33px;height:22px;}
        .login-main .login-bottom .center .item {width:288px;height:35px;border-bottom:1px solid #dae1e6;margin-bottom:35px;}
        .login-main {width:428px;position:relative;float:left;}
        .login-main .login-top {height:117px;background-color:#148be4;border-radius:12px 12px 0 0;font-family:SourceHanSansCN-Regular;font-size:30px;font-weight:400;font-stretch:normal;letter-spacing:0;color:#fff;line-height:117px;text-align:center;overflow:hidden;-webkit-transform:rotate(0);-moz-transform:rotate(0);-ms-transform:rotate(0);-o-transform:rotate(0);transform:rotate(0);}
        .login-main .login-top .bg1 {display:inline-block;width:74px;height:74px;background:#fff;opacity:.1;border-radius:0 74px 0 0;position:absolute;left:0;top:43px;}
        .login-main .login-top .bg2 {display:inline-block;width:94px;height:94px;background:#fff;opacity:.1;border-radius:50%;position:absolute;right:-16px;top:-16px;}
        .login-main .login-bottom {width:428px;background:#fff;border-radius:0 0 12px 12px;padding-bottom:53px;}
        .login-main .login-bottom .center {width:288px;margin:0 auto;padding-top:40px;padding-bottom:15px;position:relative;}
        .login-main .login-bottom .tip {clear:both;height:16px;line-height:16px;width:288px;margin:0 auto;}
        body {background:url(../images/loginbg.png) 0% 0% / cover no-repeat;position:static;font-size:12px;}
        input::-webkit-input-placeholder {color:#a6aebf;}
        input::-moz-placeholder {/* Mozilla Firefox 19+ */            color:#a6aebf;}
        input:-moz-placeholder {/* Mozilla Firefox 4 to 18 */            color:#a6aebf;}
        input:-ms-input-placeholder {/* Internet Explorer 10-11 */            color:#a6aebf;}
        input:-webkit-autofill {/* 取消Chrome记住密码的背景颜色 */            -webkit-box-shadow:0 0 0 1000px white inset !important;}
        html {height:100%;}
        .login-main .login-bottom .tip {clear:both;height:16px;line-height:16px;width:288px;margin:0 auto;}
        .login-main .login-bottom .tip .login-tip {font-family:MicrosoftYaHei;font-size:12px;font-weight:400;font-stretch:normal;letter-spacing:0;color:#9abcda;cursor:pointer;}
        .login-main .login-bottom .tip .forget-password {font-stretch:normal;letter-spacing:0;color:#1391ff;text-decoration:none;position:absolute;right:62px;}
        .login-main .login-bottom .login-btn {width:288px;height:40px;background-color:#1E9FFF;border-radius:16px;margin:24px auto 0;text-align:center;line-height:40px;color:#fff;font-size:14px;letter-spacing:0;cursor:pointer;border:none;}
        .login-main .login-bottom .center .item .validateImg {position:absolute;right:1px;cursor:pointer;height:36px;border:1px solid #e6e6e6;}
        .footer {left:0;bottom:0;color:#fff;width:100%;position:absolute;text-align:center;line-height:30px;padding-bottom:10px;text-shadow:#000 0.1em 0.1em 0.1em;font-size:14px;}
        .padding-5 {padding:5px !important;}
        .footer a,.footer span {color:#fff;}
        @media screen and (max-width:428px) {.login-main {width:360px !important;}
            .login-main .login-top {width:360px !important;}
            .login-main .login-bottom {width:360px !important;}
        }
    </style>
    <script src="https://cdn.jsdelivr.net/npm/jquery/dist/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/font-awesome/css/font-awesome.min.css"/>
    <script src="https://cdn.jsdelivr.net/gh/stevenjoezhang/live2d-widget/autoload.js"></script>
</head>
<body>
<div class="main-body">
    <%
        Cookie[] c=request.getCookies();
        for(int i=0;i<c.length;i++){
            if("user".equals(c[i].getName())){
                request.setAttribute("uname",c[i].getValue());
            }
            if("password".equals(c[i].getName())){
                request.setAttribute("pwd",c[i].getValue());
            }
        }
    %>
    <div class="login-main">
        <div class="login-top">
            <span>超市仓库管理后台登录</span>
            <span class="bg1"></span>
            <span class="bg2"></span>
        </div>
        <form class="layui-form login-bottom" id="form">
            <div class="center">
                <div class="item">
                    <span class="icon icon-2"></span>
                    <input type="text" id="uname" name="uname" value="${uname}" lay-verify="required"  placeholder="请输入登录账号" maxlength="24"/>
                </div>

                <div class="item">
                    <span class="icon icon-3"></span>
                    <input type="password"  name="pwd" lay-verify="required" value="${pwd}" placeholder="请输入密码" maxlength="20">
                    <span class="bind-password icon icon-4"></span>
                </div>

                <div class="item">
                    <span class="icon icon-2"></span>
                    <input type="text" id="email" name="email" lay-verify="required"  placeholder="请输入qq邮箱" maxlength="20">
                </div>

                <div id="validatePanel" class="item" style="width: 137px;">
                    <input type="text" name="captcha" placeholder="请输入验证码" maxlength="6">
                    <img id="refreshCaptcha" class="validateImg"  src="http://localhost:8080/Supermarket_war_exploded/supermarketWareManagement/images/email.jpg"
                    onclick="code()">
                </div>

            </div>
            <div class="tip">
                <span class="icon-nocheck"></span>
                <span class="login-tip">保持登录</span>
                <a href="javascript:" class="forget-password">忘记密码？</a>
            </div>
            <div class="layui-form-item" style="text-align:center; width:100%;height:100%;margin:0px;">
                <input type="button" class="login-btn"  lay-filter="login" onclick="login()" value="立即登录">
            </div>
        </form>
    </div>
</div>
<div class="footer">
    ©版权所有 2019401331 汪俊豪<span class="padding-5">|</span><a target="_blank" href="http://www.miitbeian.gov.cn">粤ICP备16006642号-2</a>
</div>
<script src="../lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script src="../js/jquery.js" charset="utf-8"></script>

<script>
    function login(){
        $.ajax({
            type: "post",//提交类
            url: "http://localhost:8080/Supermarket_war_exploded/LoginServlet",//提交给谁处理
            data: $("#form").serialize(),//获取表单中组件的输入值
            success: function (result) {//提交成功
                console.log(result)
                if(result==="ok"){
                    alert("登录成功");
                    window.location.href="http://localhost:8080/Supermarket_war_exploded/supermarketWareManagement/index2.jsp?uname="+$('#uname').val();//js网页跳转
                }else if(result==="1"){
                    alert("用户名或密码为空");
                    $('#uname').select();//选中用户名输入的信息
                    $('#uname').focus();//将光标定位到用户名输入框
                }else if(result==="2"){
                    alert("用户名或密码错误");
                    $('#uname').select();//选中用户名输入的信息
                    $('#uname').focus();//将光标定位到用户名输入框
                }
            }});
    }
    function code(){
        $.ajax({
            type: "get",//提交类
            url: "http://localhost:8080/Supermarket_war_exploded/LoginServlet2",//提交给谁处理
            data: $("#email").serialize(),//获取表单中组件的输入值
            success: function (result) {//提交成功
                console.log(result)
                if(result==="0"){
                    alert("qq邮箱格式错误");
                    $('#email').select();//选中用户名输入的信息
                    $('#email').focus();//将光标定位到用户名输入框
                }if(result==='1') {
                    layer.msg("发送成功,请稍后");
                }
            }});
    }
</script>
</body>
</html>