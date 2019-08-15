<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>人力资源管理系统-用户登录</title>
<link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/bootstrap3/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<style type="text/css">
body {
	  padding-top: 40px;
	  padding-bottom: 40px;
	}

	.form-signin {
	  max-width: 330px;
	  padding: 15px;
	  margin: 0 auto;
	}
	.form-signin .form-signin-heading,
	.form-signin .checkbox {
	  margin-bottom: 10px;
	}
	.form-signin .checkbox {
	  font-weight: normal;
	}
	.form-signin .form-control {
	  position: relative;
	  height: auto;
	  -webkit-box-sizing: border-box;
	     -moz-box-sizing: border-box;
	          box-sizing: border-box;
	  padding: 10px;
	  font-size: 16px;
	}
	.form-signin .form-control:focus {
	  z-index: 2;
	}
	.form-signin input[type="email"] {
	  margin-bottom: -1px;
	  border-bottom-right-radius: 0;
	  border-bottom-left-radius: 0;
	}
	.form-signin input[type="password"] {
	  margin-bottom: 10px;
	  border-top-left-radius: 0;
	  border-top-right-radius: 0;
	}
</style>
<script type="text/javascript">
	function checkForm(){
		var userName=document.getElementById("userName").value;
		var password=document.getElementById("password").value;
		if(userName==null || userName==""){
			document.getElementById("login_err").innerHTML="用户名不能为空";
			$('#myModal').modal();
			return false;
		}
		if(password==null || password==""){
			document.getElementById("login_err").innerHTML="密码不能为空";
			$('#myModal').modal();
			return false;
		}
		return true;
	}
</script>
</head>
<body>

<div class="container" style="margin-top: 100px;">
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-4">
			<div class="text-center">
				<img alt="人力资源" src="${pageContext.request.contextPath}/images/login.jpg">
			</div>
		</div>
		<div class="col-md-4">
		  <form class="form-signin" action="${pageContext.request.contextPath}/user/login.do" method="post" onsubmit="return checkForm()">
		    <label for="inputEmail" class="sr-only">用户名：</label>
		    <input type="text" id="userName" name="userName" class="form-control" placeholder="请输入用户名">
		    <label for="inputPassword" class="sr-only">密码：</label>
		    <input type="password" id="password"  name="password" class="form-control" placeholder="请输入密码">
		    <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
		    <button class="btn btn-lg btn-primary btn-block" type="reset">重置</button>
		  </form>
		</div>
		<div class="col-md-2"></div>
	</div>
</div> <!-- /container -->

<!-- footer页脚 -->
<div class="container">
  <div class="row">
	  <div class="col-md-12">
	  	<div align="center" style="padding-top: 20px;margin-top: 100px;">
			Copyright © 2017-2018 稚序员网 版权所有
		</div>
	  </div>
  </div>
</div>

<!-- 提示信息的模态框，配合JS代码使用 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">提示信息</h4>
      </div>
      <div class="modal-body">
        <span style="color:red;" id="login_err">${errorMsg }</span>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

</body>
<script type="text/javascript">
	if('${requestScope.errorMsg}'){
		$('#myModal').modal();
	}
</script>
</html>