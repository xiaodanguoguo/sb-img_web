<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="version" value=""/>
<c:set var="userSession" value="${sessionScope.userSession}"/>
<c:set var="baesImgSrc" value="http://7xweel.com1.z0.glb.clouddn.com/"/>

<!-- js直接引用变量 -->
<script type="text/javascript">
    var ctx = '${ctx}';
    var baseImgSrc = '${baesImgSrc}';
</script>
