<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>

	<meta charset="utf-8">
	<title>表情包列表</title>

	<!-- Bootstrap Core CSS -->
	<link href="${ctx}/resource/css/bootstrap.min.css" rel="stylesheet">

	<!-- Custom CSS -->
	<link href="${ctx}/resource/css/4-col-portfolio.css" rel="stylesheet">

	<link href="${ctx}/resource/css/style.css" rel="stylesheet">
	<!-- jQuery -->
	<script src="${ctx}/resource/js/jquery-1.11.1.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="${ctx}/resource/js/bootstrap.min.js"></script>


</head>

<body>



<%@include file="/WEB-INF/common/head_menu.jsp"%>
<!-- Navigation -->

<!-- Page Content -->
<div class="container" id="content">
	<!-- Projects Row -->
	<div class="row">
		<!-- 左边图片布局 -->
		<div class="col-lg-12">
			<c:forEach  items="${pageModel.records}" var="imgResource" >
				<div class="col-md-3 portfolio-item">
						<%--${imgResource}--%>
					<a href="${ctx}/img/detail.html?id=${imgResource.imgId}">
						<div class="img-background">
							<img class="img-responsive" src="${baesImgSrc}${imgResource.imgUrl}?imageView/1/w/207/h/124" width="207px" height="124px" alt="">

							<span class="img-name">${imgResource.imgName}</span>
						</div>
					</a>
				</div>
			</c:forEach>



		</div>

		<!--右边扩展布局 -->
		<%--<div class="col-md-2">

			<!-- Blog Search Well -->
			<div class="well">
				<!-- <h4>Blog Search</h4>-->
				Search
				<div class="input-group">
					<input type="text" class="form-control">
                        <span class="input-group-btn">
                            <button type="button" class="btn btn-default">
								<span class="glyphicon glyphicon-search"></span>
							</button>
                        </span>
				</div>
				<!-- /.input-group -->
			</div>

			<!-- Blog Categories Well -->
			<div class="well">
				<h4>热点表情</h4>


				<ul class="list-unstyled">
					<li class="text-warning">单身狗</li>
					<li class="text-uppercase">呆呆头</li>
					<li class="text-info">金馆长</li>
					<li class="text-success">最多字符串窜传传</li>

				</ul>


			</div>

			<!-- Side Widget Well -->
			<div class="well">


				<a class="btn btn-app btn-success" href="#">
					<i class="icon-refresh bigger-80 "></i>
					糗百每日一水
				</a>
				<h4>
				</h4>
				<p>昨天到幼儿园接侄女去早了，蹲在门口和一个家长闲聊，聊到我侄女的班主任的时候，我忍不住夸赞起来，张老师善良、温柔、漂亮、有责任心......吧啦吧啦一大堆，看他听得饶有兴趣，我向他神秘一笑，说实话啊，要不是为了拿下这颗大白菜，我也不会天天抢着接孩子了......
					我话音没落，突然觉得场面不对劲儿，连忙岔开话题，哥们儿，每天都是你来接孩子吗？
					他向我摇头一笑，不是啊，我每天都是准时来接大白菜的，今天来早了，噢，我是她男朋友...</p>
			</div>

		</div>--%>
	</div>
	<!-- /.row -->


	<hr>

	<!-- Pagination -->
	<div class="row text-center">
		<div class="col-lg-12">
			<ul class="pagination">
				<li>
					<a href="${ctx}/img/queryByPage.html?pageNo=1&pageSize=${pageModel.pageSize}" >首页</a>
				</li>

				<li >
					<a href="${ctx}/img/queryByPage.html?pageNo=${pageModel.pageNow-1 < 1 ? 1 : pageModel.pageNow-1 }&pageSize=${pageModel.pageSize}">上一页</a>
				</li>

				<li class="active">
					<a href="${ctx}/img/queryByPage.html?pageNo=${pageModel.pageNow}&pageSize=${pageModel.pageSize}">第${pageModel.pageNow}页</a>
				</li>

				<li >
					<a href="${ctx}/img/queryByPage.html?pageNo=${pageModel.pageNow+1 > pageModel.pageCount ? pageModel.pageCount: pageModel.pageNow+1}&pageSize=${pageModel.pageSize}">下一页</a>
				</li>

				<%--<li>
					<a href="#">2</a>
				</li>
				<li>
					<a href="#">3</a>
				</li>
				<li>
					<a href="#">4</a>
				</li>
				<li>
					<a href="#">5</a>
				</li>--%>
				<li>
					<a href="${ctx}/index/queryByPage.html?pageNo=${pageModel.pageCount}&pageSize=${pageModel.pageSize}">末页</a>
				</li>
			</ul>
		</div>
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
	})



</script>


</html>
