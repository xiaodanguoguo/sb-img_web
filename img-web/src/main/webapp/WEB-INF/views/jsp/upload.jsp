<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <title>上传图片</title>

    <!-- Bootstrap Core CSS -->
    <link href="${ctx}/resource/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${ctx}/resource/css/4-col-portfolio.css" rel="stylesheet">

    <link href="${ctx}/resource/css/style.css" rel="stylesheet">

    <link href="${ctx}/resource/css/upload.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="${ctx}/resource/js/jquery-1.11.1.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${ctx}/resource/js/bootstrap.min.js"></script>

    <!--引入弹窗的代码 -->
    <script src="${ctx}/resource/plugin/layer/layer.js"></script>

    <link href="${ctx}/resource/plugin/layer/skin/layer.css" rel="stylesheet">

    <!--ajax 上传文件 -->
    <script src="${ctx}/resource/js/ajaxfileupload.js"></script>
</head>

<body>

<!-- Navigation -->
<%@include file="/WEB-INF/common/head_menu.jsp"%>

<!-- Page Content -->
<div class="container" id="content">
    <!-- Projects Row -->
    <div class="row">

        <form action="javascript:void(0)" class="imgForm" method="post" enctype="multipart/form-data">
            <div class="col-sm-4 center-block">

                名称:<input type="text" name="name" class="form-control  name" >
                <br/>

                文件:<input type="file" name="file" class="form-control file" id="id-input-file-2">

                <br/>

                类别：<select name="imgmenu"  class="form-control imgmenu" id="form-field-select-1">
                    <option value="">请选择</option>
                    <c:forEach items="${menuList}" var="menu">
                        <option value="${menu.id}">${menu.name}</option>
                    </c:forEach>
                </select>

                <br/>

                <button class="btn btn-info subBtn" type="button">
                    <i class="icon-ok bigger-110"></i>
                    上传
                </button>


                <button class="btn" type="reset">
                    <i class="icon-undo bigger-110"></i>
                    取消
                </button>


            </div>

        </form>



    </div>
    <!-- /.row -->


    <hr>

    <!-- Footer -->
    <footer>
        <div class="row">
            <div class="col-lg-12">
                <p>Copyright &copy; sb-img表情包</p>
            </div>
        </div>
        <!-- /.row -->
    </footer>


</div>
<!-- /.container -->



</body>
<script>

    $(function(){
        //layer.alert('见到你真的很高兴', {icon: 6});


       $(".subBtn").click(function(){

           if($(".name").val() == null || $(".name").val() == ""){
               layer.alert('输入名称', {icon: 6});
               return;
           }

           if($(".file").val() == null || $(".file").val() == ""){
               layer.alert('选择文件再上传', {icon: 6});
               return;
           }

           if($(".imgmenu").val() == null || $(".imgmenu").val() == ""){
               layer.alert('请选择分类再上传', {icon: 6});
               return;
           }

           var file = $(".file").val();
           if(!/.(gif|jpg|jpeg|png|gif|jpg|png)$/.test(file)) {
               layer.alert('只允许上传图片', {icon: 6});
               return;
           }

           //alert($(".imgmenu").val())
            $(this).addClass('disabled');//按钮失效
           //上传中。。。。。
           layer.load(1, {
               shade: [0.1,'#fff'] //0.1透明度的白色背景
           });

            $.ajaxFileUpload({
                secureuri: false, //是否需要安全协议，一般设置为false
                url : ctx + "/img/doupload.html",//上传的路径
                fileElementId: 'id-input-file-2', //文件上传域的ID
                dataType : 'json',//返回值类型 一般设置为json
                data : {
                    imgmenu : $(".imgmenu").val(),
                    name    : $(".name").val()
                },
                success: function (data, status){ //服务器成功响应处理函数
                   if(data.success){
                       //询问框
                       layer.close(1);//关闭加载层
                       layer.msg('上传成功，选择操', {
                           time: 0 //不自动关闭
                           ,btn: ['继续上传', '制作表情']
                           ,yes:function(index){
                                layer.close(index);
                                window.location.reload();
                           }
                           ,btn2:function (index) {
                               //跳转到详细页面
                               alert("lalalla")
                            }
                       });


                   }


                   /* layer.confirm('您是如何看待前端开发？', {
                        btn: ['重要','奇葩'] //按钮
                    }, function(){
                        layer.msg('的确很重要', {icon: 1});
                    }, function(){
                        layer.msg('也可以这样', {
                            time: 20000, //20s后自动关闭
                            btn: ['明白了', '知道了']
                        });
                    });*/
                }
            })



       });











    })





</script>


</html>
