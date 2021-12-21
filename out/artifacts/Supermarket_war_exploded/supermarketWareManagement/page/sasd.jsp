<%@ page import="bean.Good" %>
<%@ page import="home.sqlD" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: OMEN
  Date: 2021/5/8
  Time: 8:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../../layui-v2.6.5/layui/css/layui.css" media="all">
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
<%--<table class="layui-hide" id="test" lay-filter="test"></table>--%>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
        <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
        <button class="layui-btn  layui-btn-sm" lay-event="add" id="btnAdd">
        <i class="layui-icon layui-icon-form"></i>添加</button>
        <button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="del" id="btnDel">
            <i class="layui-icon layui-icon-delete"></i>删除选中项
        </button>
    </div>

</script>
<div style="margin: 10px;">
    <div class="layui-inline">
        <!--搜索框，应用了layui中的样式，记住id值，后面会使用-->
        <input class="layui-input" name="keyId" id="keyId" autocomplete="off" placeholder="请输入待搜索的商品编号">
    </div>
    <button class="layui-btn"><i class="layui-icon"></i>搜索</button>
</div>
<script type="text/html" id="img"><!--图片模板-->
    <img src="../images/{{d.photo}}" style="width: 48px;border:1px solid #cccccc;padding: 1px;" />
</script>
<table class="layui-hide" id="tbGoodsinfo" lay-filter="tbGoodsinfo" ></table><!--设置要装载数据的表格，注意需要设置id-->
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script src="../../layui-v2.6.5/layui/layui.js" charset="utf-8"></script>
<script>
    layui.use('table', function(){
        var table = layui.table,
            $ = layui.jquery;
        var addLink = function (d) {
            var html = '<a style="color:#1E9FFF" href="Goods.jsp?id='+d.id+'" lay-event="showRec" >'+d.id+'</a>';
            return html;
        }
        table.render({//执行渲染
            elem: '#tbGoodsinfo',//指定原始表格元素选择器（推荐id选择器）
            id: 'stuinfo',
            url: 'http://localhost:8080/text_war_exploded/GoodServlet2', //数据接口，为json格式
            height: 'full-100',//设置表格高度，离浏览器窗口上下各100px
            toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板
            cols: [[ //设置表头
                {type: "checkbox", width: 50},
                {field: 'id', title: '编号',align:'center',sort: true,templet: addLink},
                {field: 'goods', title: '商品名',align:'center'},
                {field: 'num', title: '剩余数量',align:'center'},
                {field: 'time', title: '进货时间',align:'center'},
                {field: 'supplier', title: '供货商',align:'center'},
                {field: 'Photo', title: '图片',templet:'#img',align:'center'},
                {fixed: 'right', title:'操作', toolbar: '#barDemo'}
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
            }, done: function (res, curr, count) {//表格渲染完成后的回调函数
                bindTableToolbarFunction();//绑定表格自定义按钮的事件，防止表格reload后，自定义按钮事件失效
            }
        });
        //头工具栏事件
        table.on('toolbar(tbGoodsinfo)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：'+ data.length + ' 个');
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选': '未全选');
                    break;

                //自定义头工具栏右侧图标 - 提示
                case 'LAYTABLE_TIPS':
                    layer.alert('这是工具栏右侧自定义的一个图标按钮');
                    break;
            };
        });
        table.on('tool(tbGoodsinfo)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    $.ajax({
                        data:{id2:data.id},
                        url:'http://localhost:8080/text_war_exploded/GoodServlet2',
                        async:true,
                        type:'get',
                        dateType:'application/json',
                        success:function (data) {
                           layui.msg(data.id)
                        }
                    });
                    obj.del();
                    layer.close(index);
                });
            } else if(obj.event === 'edit'){
                var index = layer.open({
                    title: '修改商品信息',
                    type: 2,//type为1 ，content显示的是纯文本内容，type为2，content为跳转页面
                    shade: 0.2,
                    // maxmin:true,
                    shadeClose: true,
                    area: ['90%', '90%'],
                    offset: ['5%', '5%'],
                    content: 'UpdateGood.jsp?id=' + data.id,
                    end: function () {
                        location.reload();//弹出层结束后，刷新主页面
                    }
                });
            }
        });
        // 监听搜索操作
        $('.layui-btn').click(function () {//点击搜索按钮
            var inputVal = $('#keyId').val()//获取学号文本框的值
            table.reload('stuinfo', {//按搜索条件重新加载表格，值”ginfo“为table.render中的基础参数id的值
                where: {//设定异步数据接口的额外参数,在servlet可用request.getParameter("下列键")获取值
                    id : inputVal//设置键值对，可有多个
                }
                ,page: {
                    curr: 1//重新从第 1 页开始
                }
            });
        })
        function bindTableToolbarFunction() {
            $('#btnAdd').click(function () {//定义表格头部单击添加事件
                // window.open("<%=request.getContextPath()%>/home/addGoods.jsp");//直接跳转
                var index = layer.open({
                    title: '添加商品信息',
                    type: 2,//type为1 ，content显示的是纯文本内容，type为2，content为跳转页面
                    shade: 0.2,
                    // maxmin:true,
                    shadeClose: true,
                    area: ['90%', '90%'],
                    offset: ['5%', '5%'],
                    content: '<%=request.getContextPath()%>/supermarketWareManagement/page/addGoods.jsp',
                    end: function () {
                        // table.reload('stuinfo',{});//弹出层结束后，刷新表格，参数stuinfo即为基础参数id对应的值
                        location.reload();//弹出层结束后，刷新页面
                    }
                });
            });
            $('#btnDel').click(function () {//点击添加按钮事件，需要设置按钮的id
                var checkStatus = table.checkStatus('stuinfo');//stuinfo 即为基础参数 id 对应的值
                var data = checkStatus.data;//获取被中行的数据
                var arrSno = [];//定义数组，存放选中的学号
                var arrPic = [];////定义数组，存放选中的图片文件名
                if (data.length == 0) {
                    layer.msg("未选则数据，请重新选择！")
                } else {
                    // for (i = 0; i < data.length; i++) {
                    //     arr[i]=data[i].sno;
                    // }
                    data.forEach(function (data) {//遍历选中的行
                        arrSno.push(data.id);//将选中的学号存放在数组中
                        arrPic.push(data.photo);//将选中的图片存放在数组中
                    });
                    // layer.alert(JSON.stringify(arr));
                    //在弹出层中不能直接输出数组或对象，可通过JSON.stringify(arr)转换成json字符串显示
                    layer.confirm('真的删除学号为' + JSON.stringify(arrSno) + '的记录吗？', function (index) {
                        // window.location='/layuimini/DelStu_16_Servlet?xh='+data.sno//直接跳转
                        $.ajax({//无刷新提交
                            url: '<%=request.getContextPath()%>/DelGoodsServlet',
                            dataType: 'text',//返回的数据类型
                            data: {id: arrSno,pic2:arrPic},//传递给DelStuServlet的参数为xh，DelStuServlet中使用request.getParameterValues("xh");获取数组arr
                            traditional: true,//传递数组参数到Servlet时，必须设置为true，默认为false
                            success: function (result) {//提交成功后，对返回数据的处理，返回数据存放在result中
                                if (result == "ok") {
                                    table.reload('stuinfo', {});//刷新数据表格，stuinfo为table的基础参数id的值
                                    layer.msg('学号为' + JSON.stringify(arrSno) + '的记录删除成功！')
                                } else {
                                    layer.msg('删除不成功！')
                                }
                            }
                        });
                        layer.close(index);//点击确定后关闭弹出层
                    });
                }
            });
        }
    });
</script>
</body>
</html>
