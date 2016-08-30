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
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container" >
        <!-- Brand and toggle get grouped for better mobile display -->
        <!-- <div class="navbar-header">
             <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                 <span class="sr-only">Toggle navigation</span>
                 <span class="icon-bar"></span>
                 <span class="icon-bar"></span>
                 <span class="icon-bar"></span>
             </button>
             <a class="navbar-brand" href="#">首页</a>
         </div>-->
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <a href="#">金馆长</a>
                </li>
                <li>
                    <a href="#">单身狗</a>
                </li>
                <li>
                    <a href="#">懵逼</a>
                </li>

                <li>
                    <a href="#">金馆长</a>
                </li>
                <li>
                    <a href="#">单身狗</a>
                </li>
                <li>
                    <a href="#">懵逼</a>
                </li>

                <!--<li>
                    <a href="#">金馆长</a>
                </li>
                <li>
                    <a href="#">单身狗</a>
                </li>
                <li>
                    <a href="#">懵逼</a>
                </li>

                <li>
                    <a href="#">金馆长</a>
                </li>
                <li>
                    <a href="#">单身狗</a>
                </li>
                <li>
                    <a href="#">懵逼</a>
                </li>

                <li>
                    <a href="#">金馆长</a>
                </li>
                <li>
                    <a href="#">单身狗</a>
                </li>
                <li>
                    <a href="#">懵逼</a>
                </li>

                <li>
                    <a href="#">金馆长</a>
                </li>
                <li>
                    <a href="#">单身狗</a>
                </li>
                <li>
                    <a href="#">懵逼</a>
                </li>

                <li>
                    <a href="#">金馆长</a>
                </li>
                <li>
                    <a href="#">单身狗</a>
                </li>
                <li>
                    <a href="#">懵逼</a>
                </li>

                <li>
                    <a href="#">金馆长</a>
                </li>
                <li>
                    <a href="#">单身狗</a>
                </li>
                <li>
                    <a href="#">懵逼</a>
                </li>

                <li>
                    <a href="#">金馆长</a>
                </li>
                <li>
                    <a href="#">单身狗</a>
                </li>
                <li>
                    <a href="#">懵逼</a>
                </li>

                <li>
                    <a href="#">金馆长</a>
                </li>
                <li>
                    <a href="#">单身狗</a>
                </li>
                <li>
                    <a href="#">懵逼</a>
                </li>-->
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>

<!-- Page Content -->
<div class="container" id="content">
    <!-- Projects Row -->
    <div class="row">

        <form action="javascript:void(0)" class="imgForm" method="post" enctype="multipart/form-data">
            <div class="col-sm-4 center-block">


                <input type="file" name="file" class="file" id="id-input-file-2">

                <br/>

                <select name="imgmenu"  class="form-control imgmenu" id="form-field-select-1">
                    <option value="">请选择</option>
                    <option value="AL">金馆长</option>
                    <option value="AK">单身狗</option>
                    <option value="AZ">懵逼</option>
                    <option value="AR">金馆长</option>
                    <option value="CA">单身狗</option>
                    <option value="CO">懵逼</option>
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

            $.ajaxFileUpload({
                secureuri: false, //是否需要安全协议，一般设置为false
                url : ctx + "/img/doupload.html",//上传的路径
                fileElementId: 'id-input-file-2', //文件上传域的ID
                dataType : 'json',//返回值类型 一般设置为json
                data : {
                    imgmenu : $(".imgmenu").val(),
                },
                success: function (data, status){ //服务器成功响应处理函数
                   if(data.success){
                       alert(data.retMsg);
                   }
                }
            })



       });











    })





</script>


</html>
