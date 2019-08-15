<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>人力资源管理系统</title>
<link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<style type="text/css">
/* Sticky footer styles
-------------------------------------------------- */
html {
  position: relative;
  min-height: 100%;
}
body {
  /* Margin bottom by footer height */
  margin-bottom: 60px;
}
.footer {
  position: absolute;
  bottom: 0;
  width: 100%;
  /* Set the fixed height of the footer here */
  height: 60px;
  background-color: #101010;
}


/* Custom page CSS
-------------------------------------------------- */
/* Not required for template or sticky footer method. */

body > .container {
  padding: 60px 15px 0;
}
.container .text-muted {
  margin: 20px 0;
}

.footer > .container {
  padding-right: 15px;
  padding-left: 15px;
}

code {
  font-size: 80%;
}
</style>
</head>
<body>
<%
	//若用户未登录访问主页，则页面重定向至登录界面login.jsp
	if(session.getAttribute("currentUser")==null){
		response.sendRedirect(request.getContextPath()+"/login.jsp");
		return;
	}

	String mainPage=(String)request.getAttribute("mainPage");
	if(mainPage==null || mainPage.equals("")){
		mainPage="/common/default.jsp";
	}
%>
<!-- 页头 -->
<jsp:include page="/common/head.jsp"/>

<div class="container">
  <div class="row">
	  <div class="col-md-3">
	    <!-- 左侧菜单 -->
	  	<jsp:include page="/common/menu.jsp"/>
	  </div>
	  <div class="col-md-9">
	    <!-- bootstrap路径导航 -->
	  	<div>
			<ol class="breadcrumb">
			  <li><span class="glyphicon glyphicon-home"></span>&nbsp;<a href="${pageContext.request.contextPath}/main.jsp">主页</a></li>
			  <li class="active">${modeName }</li>
			</ol>
		</div>
		<!-- 核心内容 -->
		<jsp:include page="<%=mainPage %>"/>
	  </div>
  </div>
</div>

<!-- 页脚 -->
<jsp:include page="/common/foot.jsp"/>

<script src="${pageContext.request.contextPath}/bootstrap3/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.fr.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">
	$('.form_date').datetimepicker({
		format: 'yyyy-mm-dd',
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
    });
</script>
</body>
</html>