<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>表情包页面测试</title>
    <!-- jQuery -->
    <script src="${ctx}/resource/js/jquery-1.11.1.min.js"></script>

    <!-- Bootstrap Core CSS -->
    <link href="${ctx}/resource/css/bootstrap.min.css" rel="stylesheet">

    <!-- Bootstrap Core JavaScript -->
    <script src="${ctx}/resource/js/bootstrap.min.js"></script>


    <script>
        /**
         * 实现拖拽的js效果
         */
        $(function(){

            var mouseStart={};
            var divStart={};


            //setCapture 可以捕获到 移动到浏览器外的鼠标事件。

            $("#dragText").mousedown(function(ev){
                //alert("鼠标点击拖拽事件");
                //console.log(ev);

                var oEvent=ev||event;
                mouseStart.x=oEvent.clientX;
                mouseStart.y=oEvent.clientY;
                divStart.x=oDiv.offsetLeft;
                divStart.y=oDiv.offsetTop;

                console.log(mouseStart);
                console.log(divStart);
            })


        })
    </script>

</head>

<style>

    /**********图层********************/
    #containerLayer{
        position: relative;
    }
    #dragLayer{
        position: absolute;
        top:100px;
        left: 100px;
        width: 400px;
        height: 40px;
    }
    #dragLayer input{
        border: 0px;
        background-color: rgba(0, 0, 0, 0.06);
        width: 200px;
        height: 40px;
        float: left;
    }
    #dragText{
        display: block;
        float: left;
        color: red;
    }
    #imgLayer{
        /*background-color: #5bc0de;*/
        position: absolute;
        top:100px;
        left: 100px;

    }
    /**********end of 图层定位************************/

</style>


<body>

<div>

    <div class="col-md-12 portfolio-item">
        <a href="#">
            <img class="img-responsive" src="http://7xweel.com1.z0.glb.clouddn.com/file" alt="">
        </a>
    </div>



    <div class="col-md-12 portfolio-item">



        <!--容器图层 -->
        <div id="containerLayer">
            <!--图片层 -->
            <div id="imgLayer">
                <img class="img-responsive" src="http://7xweel.com1.z0.glb.clouddn.com/F%60%29%609%7D40ML6X$NJ%7DC%5D%7DPVAR.jpg" alt="">
            </div>
            <!--end of 图片层 -->
            <!--拖拽字体层 -->
            <div id="dragLayer">
                <input type="text" name="imageName" ><span id="dragText">点此进行拖拽</span>

            </div>
            <!-- end of 拖拽字体层 -->
        </div>
        <!--end of 容器层 -->

    </div>

</div>


</body>
</html>
