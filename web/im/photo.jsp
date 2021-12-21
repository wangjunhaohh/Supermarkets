<%--
  Created by IntelliJ IDEA.
  User: OMEN
  Date: 2021/6/29
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <title>Title</title>
    <link rel="stylesheet" href="../layui-v2.6.5/layui/css/layui.css" media="all">
    <style>
        .layui-table-cell{
            height: auto;
        }
        .layui-table-cell .layui-form-checkbox[lay-skin="primary"]{
            top: 50%;
        }
    </style>
</head>
<body>
<table class="layui-hide" id="tbGoodsinfo" lay-filter="tbGoodsinfo" ></table>
<script type="text/html" id="img"><!--图片模板-->
<img src="../images01/{{d.photo}}" style="width: 48px;border:1px solid #cccccc;padding: 1px;" />
</script>
<script src="../layui-v2.6.5/layui/layui.js" charset="utf-8"></script>
<script>
    var websocket = null;
    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        //建立连接，这里的/websocket ，是Servlet中注解中的那个值
        websocket = new WebSocket("ws://localhost:8080/Supermarket_war_exploded/websocket");
    }
    else {
        alert('当前浏览器 Not support websocket');
    }
    //连接发生错误的回调方法
    websocket.onerror = function () {

    };
    //连接成功建立的回调方法
    websocket.onopen = function () {

    }
    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        console.log(event.data);
        if(event.data=="1"){
            console.log("数据更新啦");
            location.replace("http://localhost:8080/Supermarket_war_exploded/im/photo.jsp")
        }
    }
    //连接关闭的回调方法
    websocket.onclose = function () {

    }
    //监听窗口关闭事件，当窗口关闭时，主动去关闭WebSocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        closeWebSocket();
    }
    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
    }
    layui.use('table', function(){
        var table = layui.table,
            $ = layui.jquery;
        table.render({//执行渲染
            elem: '#tbGoodsinfo',//指定原始表格元素选择器（推荐id选择器）
            id: 'stuinfo',
            url: 'http://localhost:8080/Supermarket_war_exploded/imageServlet', //数据接口，为json格式
            height: 'full-100',//设置表格高度，离浏览器窗口上下各100px
            toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板
            cols: [[ //设置表头
                {type: "checkbox", width: 50},
                {field: 'id', title: '编号',align:'center',sort: true},
                {field: 'time', title: '时间',align:'center' },
                {field: 'Photo', title: '图片',templet:'#img',align:'center'},
            ]],

            // height: 300, //容器高度
            // skin: 'line',
            limits: [5,10, 15, 20, 25, 50, 100],//每页条数的选择项
            limit: 10,//每页显示的条数（默认：10）
            page: true,//开启分页
            parseData: function(res) { //res为原始返回的数据，需要将其拆分成分页数据
                var result;
                if (this.page.curr) {//如果不是第1页
                    result = res.data.slice(this.limit * (this.page.curr - 1), this.limit * this.page.curr);
                } else {
                    result = res.data.slice(0, this.limit);//获取原始数据1-10条数据
                }
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.count, //解析数据长度
                    "data": result //解析数据列表
                }
            },
        });



    });
</script>
</body>
</html>
