<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>七牛云上传文件测试</title>
</head>
<body>
<form id="form" method="post" enctype="multipart/form-data" action="${ctx}/index/qiniuFileTest.html">
    <div class="form-group">
        <label>File input</label>
        <input type="file" name="file">
    </div>

    <div class="form-group">
        <input type="submit" value="file上传测试">
        <input type="reset" value="重置">
    </div>
</form>



<form id="form1" method="post" enctype="multipart/form-data" action="${ctx}/index/qiniuFileTest1.html">
    <div class="form-group">
        <label>File input</label>
        <input type="file" name="file">
    </div>

    <div class="form-group">
        <input type="submit" value="字节码上传测试">
        <input type="reset" value="重置">
    </div>
</form>
</body>
</html>
