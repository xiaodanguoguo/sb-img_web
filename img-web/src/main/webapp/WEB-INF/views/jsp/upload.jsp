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


        <form action="${ctx}/img/uploadImg.html" class="imgForm" method="post" enctype="multipart/form-data">
            <div class="col-sm-4 center-block">

                <input type="file" name="file" id="id-input-file-2">

                <br/>

                <select name="menu"  class="form-control imgmenu" id="form-field-select-1">
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

               //$(".imgForm").submit();

       });











    })





</script>


</html>
