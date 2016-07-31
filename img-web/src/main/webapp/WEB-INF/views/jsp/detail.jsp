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

    <link href="${ctx}/resource/css/style.css" rel="stylesheet">

    <!-- Bootstrap Core JavaScript -->
    <script src="${ctx}/resource/js/bootstrap.min.js"></script>

    <!--拾色器插件 -->
    <script src="${ctx}/resource/plugin/icolor/colorPicker.js"></script>
    <link href="${ctx}/resource/plugin/icolor/colorPicker.css" rel="stylesheet">
    <script>

        //全局位置变量
        var textX = 0
        var textY = 0;
        var fontSize = 16;//默认字体
        var color = "#000000";//默认黑色
        var imgWidth = 0 ;//图片宽度
        var imgHeight = 0;//图片高度
        /**
         * 实现拖拽的js效果
         */
        $(function(){

            $(".img-responsive").on('load', function(){
                $("#imgWidth").val($(this).width());
                $("#imgHeight").val($(this).height());

                imgWidth = $("#imgWidth").val();
                imgHeight = $("#imgHeight").val();
            });


            /*--------------拖曳效果----------------
             *原理：标记拖曳状态dragging ,坐标位置iX, iY
             * mousedown:fn(){dragging = true, 记录起始坐标位置，设置鼠标捕获}
             * mouseover:fn(){判断如果dragging = true, 则当前坐标位置 - 记录起始坐标位置，绝对定位的元素获得差值}
             * mouseup:fn(){dragging = false, 释放鼠标捕获，防止冒泡}
             */
            var dragging = false;
            var iX, iY,iX1,iY1;



            console.log(imgWidth);

            var maxX = 460 - 200;//最大x值,200为文本框的宽度
            var maxY = 500 - 40;//最大y值，40为文本框的高度
            var minX = 0;//最小x值
            var minY = 0;//最小y值
            var indexCount = 0;
            $("#dragText").mousedown(function(e) {

                indexCount++;//
                dragging = true;
                if(indexCount == 1){
                    iX = e.clientX ;//位置含义：当前的位置与dom的距离 x
                    iY = e.clientY ;//位置含义： 当前的位置与dom的距离 y
                }
                iX1 = e.clientX ;//位置含义：当前的位置与dom的距离 x
                iY1 = e.clientY ;//位置含义： 当前的位置与dom的距离 y
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
                var img = $(".img-responsive").attr("src");
                color = $("#bau").val();
                imgWidth = $("#imgWidth").val();
                imgHeight = $("#imgHeight").val();
                $.ajax({
                    url: ctx +" /generatorImg.html",    //请求的url地址
                    dataType: "json",   //返回格式为json
                    data: {

                        "text": text,
                        "x" : x,
                        "y" : y,
                        "img" :img,
                        "fontSize" : fontSize,
                        "color" : color,
                        "width" : imgWidth,
                        "height" : imgHeight
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

            /**
             * 字体变大
             */
            $("#add_font").click(function() {

                //获取para的字体大小
                var thisEle = $("#imageName").css("font-size");
                //parseFloat的第二个参数表示转化的进制，10就表示转为10进制
                var textFontSize = parseFloat(thisEle , 10);
                //javascript自带方法
                var unit = thisEle.slice(-2); //获取单位:px
                var cName = $(this).attr("class");
                if(textFontSize < 30){//最大字体为30px
                    textFontSize += 2;//字体增大
                }
                fontSize = textFontSize;
                console.log(textFontSize);
                $("#imageName").css("font-size",  textFontSize + unit );
            })

            /**
             * 字体变小
             */
            $("#sub_font").click(function(){
                //获取para的字体大小
                var thisEle = $("#imageName").css("font-size");
                //parseFloat的第二个参数表示转化的进制，10就表示转为10进制
                var textFontSize = parseFloat(thisEle , 10);
                //javascript自带方法
                var unit = thisEle.slice(-2); //获取单位:px
                var cName = $(this).attr("class");
                if(textFontSize > 12){//最小字体为12px
                    textFontSize -= 2;//字体减小
                }
                fontSize = textFontSize;
                console.log(textFontSize);
                $("#imageName").css("font-size",  textFontSize + unit );
            })

            //TODO 1.文本框只能够在图片中，不能超过图片

            //TODO 2.解决掉定位每次都从0开始的bug

            //TODO 3.把图片的宽度和高度作为参数传递过去



            //TODO 4.
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

    #imageName{
        border: 0px;
        background-color: rgba(0, 0, 0, 0.04);
        width: 200px;
        height: 40px;
        float: left;
        color: #000000;
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

    /*.img-responsive{
        width: 440px;
        height: 460px;
    }*/
    /**********end of 图层定位************************/

</style>


<body>

<!--导航条 -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
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
<!--end of 导航条 -->

<div class="container">
    <hr>
    <hr>
    <!--热搜表情 -->
    <%--<div class="navi-block">
        <ul>
            <li>
                <a href="Home.aspx">表情主页</a>
            </li>
            <li>
                <a href="ListWithImage.aspx">图片列表</a>
            </li>
            <li>
                <a href="List.aspx">文字列表</a>
            </li>
            <li>
                <a href="Like.aspx">最赞表情</a>
            </li>
        </ul>
        <div class="search-bar">
            <input name="ctl00$ctl00$ContentPlaceHolder1$TextBoxSearch" type="text" maxlength="100" id="ctl00_ctl00_ContentPlaceHolder1_TextBoxSearch" class="search-box">
            <a href="SearchText.aspx" id="ctl00_ctl00_ContentPlaceHolder1_searchgo" class="search-go">搜索</a>
        </div>
    </div>--%>
    <hr>
    <!--end of 热搜表情 -->
    <!-- Projects Row -->
    <div class="row">
        <div>
            <!--图片 -->
            <div class="col-md-12 portfolio-item ">
                <!--图片层 -->
                <div id="imgLayer">
                    <div class="img-content">

                        <img class="img-responsive" src="http://7xweel.com1.z0.glb.clouddn.com/T8U_G4KZY~SUQ8V%254C%5BA%25CF.jpg" alt="">
                        <input type="hidden" id="imgWidth">
                        <input type="hidden" id="imgHeight">
                    </div>
                    <!--拖拽字体层 -->
                    <div id="dragLayer">
                        <input type="text" name="imageName" id="imageName"  ><span id="dragText">点击拖动</span>
                    </div>
                    <!-- end of 拖拽字体层 -->
                </div>
                <!--end of 图片层 -->
                <span class="col-md-1  ">颜色选择:</span><input  class="col-md-1" value="#000000"  type="text" id="bau" onclick="startColorPicker(this)" onkeyup="maskedHex(this);setColor();">
                <button id="add_font">字体变大</button>
                <button id="sub_font">字体变小</button>
                <button id="submitBtn">生成图片</button>
            </div>
            <!--end of 图片 -->
            <!--评论 -->
            <div class="col-lg-5">
                <label >评论:</label>
                    <textarea class="form-control " cols="5" rows="5"></textarea>
                <div class="right">
                    <button>提交</button>
                </div>

                <div id="post-content">
                    <ol class="post-list">
                        <li id="2782">
                            <div class="post-content">
                                <span>QQ钱包那个是够可以的</span>
                            </div>
                            <div class="post-info">
                                <span class="post-name">119.179.74.153</span> |
                                <span class="post-date">2016-04-13 19:19:40</span>
                            </div>
                         </li>
                        <li id="2688">
                            <div class="post-content">
                                <span>哈哈</span>
                            </div>
                            <div class="post-info">
                                <span class="post-name">116.1.127.146</span> |
                                <span class="post-date">2016-03-16 16:03:22</span>
                            </div>
                        </li>
                        <li id="2632">
                            <div class="post-content">
                                <span>怎么下载啊</span>
                            </div>
                            <div class="post-info">
                                <span class="post-name">111.225.59.77</span> |
                                <span class="post-date">2016-02-26 23:00:51</span>
                            </div>
                        </li>
                        <li id="2527">
                            <div class="post-content">
                                <span>挺好  很喜欢</span>
                            </div>
                            <div class="post-info">
                                <span class="post-name">119.167.66.42</span> |
                                <span class="post-date">2016-01-31 23:43:52</span>
                            </div>
                        </li>
                        <li id="2524">
                            <div class="post-content">
                                <span>nb</span>
                            </div>
                            <div class="post-info">
                                <span class="post-name">123.150.205.250</span> |
                                <span class="post-date">2016-01-29 16:58:42</span>
                            </div>
                        </li>
                </ol>

                    <div class="bean-page">
                        <a href="javascript:void(0);" class="current">1</a>
                        <a href="javascript:void(0);" >2</a>
                        <a href="javascript:void(0);">3</a>
                    </div>
                </div>

            </div>
            <!--end of评论 -->
        </div>

    </div>


    <hr>


    <footer>
        <div class="row">
            <div class="col-lg-12">
                <p>Copyright &copy; sb-img表情包</p>
            </div>
        </div>
        <!-- /.row -->
    </footer>

</div>




</body>
</html>
