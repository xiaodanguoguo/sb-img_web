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

        //全局位置变量
        var textX = 0
        var textY = 0;
        /**
         * 实现拖拽的js效果
         */
        $(function(){


            /*--------------拖曳效果----------------
             *原理：标记拖曳状态dragging ,坐标位置iX, iY
             * mousedown:fn(){dragging = true, 记录起始坐标位置，设置鼠标捕获}
             * mouseover:fn(){判断如果dragging = true, 则当前坐标位置 - 记录起始坐标位置，绝对定位的元素获得差值}
             * mouseup:fn(){dragging = false, 释放鼠标捕获，防止冒泡}
             */
            var dragging = false;
            var iX, iY;
            var maxX = 440 - 200;//最大x值,200为文本框的宽度
            var maxY = 460 - 40;//最大y值，40为文本框的高度
            var minX = 0;//最小x值
            var minY = 0;//最小y值

            $("#dragText").mousedown(function(e) {
                dragging = true;
                iX = e.clientX ;//位置含义：当前的位置与dom的距离 x
                iY = e.clientY ;//位置含义： 当前的位置与dom的距离 y
                this.setCapture && this.setCapture();//捕获鼠标事件
                return false;
            });

            /**
             * 鼠标的移动事件
             * @param e
             * @returns {boolean}
             */
            document.onmousemove = function(e) {
                if (dragging) {
                    var e = e || window.event;
                    var oX = e.clientX - iX;
                    var oY = e.clientY - iY;
                    textX = oX;
                    textY = oY;
                    /*********把文本框的拖拽限制在图片之内********************/
                    oX = checkValueByType(oX,1);
                    oY = checkValueByType(oY,2);

                    $("#dragLayer").css({"left":oX + "px", "top":oY + "px"});
                    return false;
                }
            };

            /**
             * 鼠标停止事件
             */
            $(document).mouseup(function(e) {
                dragging = false;
                e.cancelBubble = true;
            })


            /**
             * 校验x和y的值超过范围
             * @param value 值
             * @param type 1.x值 2.y值
             */
            function  checkValueByType(value,type){
                if(type == 1){
                    if(value > maxX){
                        return maxX;
                    } else if( value < minX){
                        return minX;
                    }else{
                        return value;
                    }
                }else{
                    if(value > maxY){
                        return maxY;
                    } else if( value < minY){
                        return minY;
                    }else{
                        return value;
                    }
                }

            }


            /**
             * 点击生成图片
             */
            $("#submitBtn").click(function(){
               //alert("我要生成图片啦");
               var text = $("#imageName").val();
               var x = textX;
               var y = textY;
               var img = $("#img-content").attr("src");


                $.ajax({
                    url: ctx +" /generatorImg.html",    //请求的url地址
                    dataType: "json",   //返回格式为json
                    data: {

                        "text": text,
                        "x" : x,
                        "y" : y,
                        "img" :img
                    },    //参数值
                    type: "POST",   //请求方式
                    success: function(req) {
                        //请求成功时处理
                    },

                    error: function() {
                        //请求出错处理
                    }
                });
            })

        })
    </script>

</head>

<style>

    /**********图层********************/

    #dragLayer{
        position: absolute;
        top:0px;
        left: 0px;
        width: 400px;
        height: 40px;
    }

    #dragLayer input{
        border: 0px;
        background-color: rgba(0, 0, 0, 0.04);
        width: 200px;
        height: 40px;
        float: left;
        color: #5bc0de;
        font-size: 16px;
        font-weight: 300;
    }
    #dragText{
        display: block;
        float: left;
        color: red;
        cursor:move;
    }
    #imgLayer{
        /*background-color: #5bc0de;*/
        position: relative;

        /*
        top:100px;
        left: 100px;
        */
    }

    .img-responsive{
        width: 440px;
        height: 460px;
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

            <!--图片层 -->
            <div id="imgLayer">
                <img class="img-responsive" id="img-content"  src="http://7xweel.com1.z0.glb.clouddn.com/F%60%29%609%7D40ML6X$NJ%7DC%5D%7DPVAR.jpg" alt="">
                <!--拖拽字体层 -->
                <div id="dragLayer">
                    <input type="text" name="imageName" id="imageName"  ><span id="dragText">点击拖动</span>
                </div>
                <!-- end of 拖拽字体层 -->

            </div>
            <!--end of 图片层 -->

            <button id="submitBtn">生成图片</button>
    </div>

</div>


</body>
</html>
