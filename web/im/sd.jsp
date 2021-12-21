<%@ page import="bean.Detectionimg" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="utils.JDBCUtilsDruid" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.DetectionImgDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/layui-v2.6.5/css/layui.css" media="all">
</head>
<body>

<div class="layui-container">
    <%--搜索框--%>
    <div style="margin: 10px;">
        <div class="layui-inline">
            <label class="layui-form-label">起始时间</label>
            <div class="layui-input-inline">
                <input class="layui-input" name="stime" id="stime" autocomplete="off" placeholder="请输入查询起始时间">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">结束时间</label>
            <div class="layui-input-inline">
                <input class="layui-input" name="etime" id="etime" autocomplete="off" placeholder="无输入为当前时间">
            </div>
        </div>
        <button class="layui-btn" id="btnSearch"><i class="layui-icon"></i>搜索</button>
    </div>
    <%--图片列表容器--%>
    <div class="layui-row" id="img_list"></div>
    <%--分页容器--%>
    <div id="cpage"></div>
</div>
<%--获取入侵图片信息，文件名和创建时间--%>
<script>
    var tmp='<%=DetectionImgDAO.findQuerry("")%>';
    var data = JSON.parse(tmp);//将json字符串转换成json对象
</script>

<%--<script src="<%=request.getContextPath()%>/layui-v2.6.5/layui.js" charset="utf-8"></script>--%>
<script>
    layui.use(['layer', 'laypage', 'laydate'], function () {//使用layui的数据表格组件table
        var $ = layui.jquery,
            laypage = layui.laypage,
            layer = layui.layer,
            laydate = layui.laydate;
        pagedemo(data);
        laydate.render({//指定日期控件
            elem: '#stime', //指定元素
            type:'datetime'
        });
        laydate.render({
            elem: '#etime', //指定元素
            type:'datetime'
        });

        //调用分页
        function pagedemo(data) {
            laypage.render({
                elem: 'cpage'
                , count: data.length
                , limit: 12
                , layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']
                , limits: [6, 12, 18, 24, 30, 36]
                , jump: function (obj) {
                    //模拟渲染
                    document.getElementById('img_list').innerHTML = function () {
                        var arr = [],
                            thisData = data.concat().splice(obj.curr * obj.limit - obj.limit, obj.limit);
                        layui.each(thisData, function (index, item) {
                            arr.push('<div class="layui-col-md1">');//使用layui的栅格控制每行显示的个数，栅格为12，md2则每行显示6个
                            arr.push('<img src="<%=request.getContextPath()%>/images01/' + item.imgfile + '" style="width: 90%; padding: 5px; border: 1px solid #c2c2c2;margin: 5px;cursor:pointer;" onclick="previewImg(this)" />');
                            arr.push('<p style="text-align: center;font-size: 10px;">' + item.cdate + '</p></div>')
                        });
                        return arr.join('');
                    }();
                }
            });
        }
        //点击搜索按钮
        $("#btnSearch").click(function () {
            var stime=$("#stime").val();
            var etime = $("#etime").val();
            $.ajax({//无刷新提交
                url: '<%=request.getContextPath()%>/FindDetectionImgServlet',
                dataType: 'json',//返回的数据类型
                data: {stime: stime, etime: etime},//传递的参数
                success: function (result) {//提交成功后，对返回数据的处理，返回数据存放在result中
                    var fdata=result;//返回的json对象
                    pagedemo(fdata);//调用分页展示
                }
            });
        });

        //轮询
        var getdata={
            url: '<%=request.getContextPath()%>/FindDetectionImgServlet',
            dataType: 'json',//返回的数据类型
            data: {stime: "", etime: ""},//传递的参数
            success: function (result) {//提交成功后，对返回数据的处理，返回数据存放在result中
                var fdata=result;//返回的json对象
                pagedemo(fdata);//调用分页展示
            }
        }
        //每隔3秒，异步提交进行轮询，获取数据库最新数据
        window.setInterval(function () {
            $.ajax(getdata);
        },3000)
    });
</script>
<script>
    // 点击图片弹层显示原图
    function previewImg(obj) {
        var img = new Image();
        img.src = obj.src;
        var height = img.height + 45; // 原图片大小
        var width = img.width; //原图片大小
        // var imgHtml = "<img src='" + obj.src + "' width='500px' height='500px'/>";
        var imgHtml = "<img src='" + obj.src + "' />"
        //弹出层
        layer.open({
            type: 1,
            shade: 0.8,
            offset: 'auto',
            area: [width + 'px', height + 'px'],  // area: [width + 'px',height+'px']  //原图显示
            shadeClose: true,
            scrollbar: false,
            title: "图片预览", //不显示标题
            content: imgHtml, //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function () {
                //layer.msg('捕获就是从页面已经存在的元素上，包裹layer的结构', { time: 5000, icon: 6 });
            }
        });
    }
</script>
</body>
</html>
