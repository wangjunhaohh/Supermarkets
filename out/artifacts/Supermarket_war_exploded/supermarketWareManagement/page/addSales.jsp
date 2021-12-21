<%@ page import="bean.Good" %>
<%@ page import="home.sqlD" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="../../layui-v2.6.5/layui/css/layui.css" media="all">

</head>
<body>
<%
    String id=request.getParameter("id");
    System.out.println(id);
    Good good = sqlD.findById(id);
    if(good!=null){
%>
<div style="padding: 20px;">

    <form class="layui-form" id="frmAddStu">
        <div class="layui-row">
            <div class="layui-col-sm8">
                <div class="layui-form-item">
                    <label class="layui-form-label">编号</label>
                    <div class="layui-input-block">
                        <input type="text" name="id" id="id" placeholder="请输入编号" class="layui-input" readonly value="<%=good.getId()%>"
                               lay-verify="required">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">商品名</label>
                    <div class="layui-input-block">
                        <input type="text" name="name" placeholder="请输入商品名" class="layui-input" readonly value="<%=good.getGoods()%>">
                    </div>
                </div>
                <div class="layui-form-item" pane="">
                    <label class="layui-form-label">数量</label>
                    <div class="layui-input-block">
                        <input type="text" name="Num" id="Num" placeholder="请输入数量" class="layui-input" value="<%=good.getNum()%>">
                    </div>
                </div>
                <div class="layui-form-item" pane="">
                    <label class="layui-form-label">单价</label>
                    <div class="layui-input-block">
                        <input type="text" name="price" placeholder="请输入数量" class="layui-input" readonly value="<%out.print(sqlD.Price(good.getId()));%>">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">进货时间</label>
                    <div class="layui-input-block">
                        <input type="text" name="time" id="time" placeholder="yyyy-MM-dd" class="layui-input" value="<%=good.getTime()%>">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">供货商</label>
                    <div class="layui-input-block">
                        <input type="text" name="supplier" placeholder="请输入供货商" class="layui-input" readonly value="<%=good.getSupplier()%>">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button type="button" class="layui-btn" lay-filter="ok" lay-submit="" id="btnAdd">购买</button>
                    </div>
                </div>
            </div>
            <div class="layui-col-sm4">
                <div class="layui-form-item" style="padding:0 10px;">
                    <img id="demo1" style="width: 300px; border:1px solid #F0F0F0; padding: 5px;"
                         src="../images/<%=good.getPhoto()%>">
                    <p id="demoText"></p>
                </div>
                <%--<button type="button" class="layui-btn" id="test1"><i class="layui-icon">&#xe67c;</i>预览头像</button>--%>
            </div>
        </div>
    </form>
</div>
<script src="../../layui-v2.6.5/layui/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'laydate', 'upload'], function () {
        var form = layui.form//定义layui的表单变量
            , $ = layui.jquery//定义layui的jquery变量
            , layer = layui.layer//定义layui的弹窗变量
            ,filesrc = $('#demo1')[0].src;
        filesrc = filesrc.substring(filesrc.lastIndexOf('\/') + 1);//获取文件名
        form.render();//渲染表单

        form.on('submit(ok)', function () {//单击添加按钮执行
            var formdata = $("#frmAddStu").serialize()+ "&filesrc=" + filesrc;
            var num=$("#Num").val();
            if(num<=<%=good.getNum()%>&&num>=0&&num.length!=""){
                $.ajax({
                    async:false,//同步方式提交表单
                    url: '<%=request.getContextPath()%>/salesRecord',//提交给谁处理
                    data: formdata,//传递表单中所有组件的值和上传的文件名
                    type: "post",//提交方式
                    dataType: "json",//返回的数据格式，常见有json、text、html等
                    success: function (data) {//提交成功，将AddStu2Servlet处理后的返回值存储在data中
                        if (data.code == 1) {//判断返回值
                            layer.msg('商品购买成功', {icon: 5, time: 1000}, function () {//使用Jquery弹层组件，自动关闭
                                // window.location.href = '/layuimini/ch15/ch15-showStuifoDetails.jsp?xh=' + $('#sno').val();//跳转到详细页，此处不能使用session
                                //当你在iframe页面关闭自身时
                                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                parent.layer.close(index); //再执行关闭
                            });
                        } else {
                            layer.msg('购买失败!', {icon: 2, time: 1000});
                        }
                    },
                    error: function (er) {//提交失败的处理

                        alert(er.responseText);
                    }
                });
            }else{
                layer.msg('输出数量错误',{icon: 2, time: 1000})
            }
        });

    })
</script>
<script>

</script>
<%
    }
%>
</body>
</html>