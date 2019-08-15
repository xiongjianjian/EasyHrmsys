<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	
	function checkForm(){
		var userName=$("#userName").val();
		var password=$("#password").val();
		var trueName=$("#trueName").val();
		var role=$("#role").val();
		if(userName==null || userName==""){
			$("#error").html("用户名不能为空！");
			return false;
		}
		if(password==null || password==""){
			$("#error").html("密码不能为空！");
			return false;
		}
		if(trueName==null || trueName==""){
			$("#error").html("真实姓名不能为空！");
			return false;
		}
		if(role==null || role==""){
			$("#error").html("请选择用户角色！");
			return false;
		}
		return true;
	}
	
	
	function resetValue(){
		$("#userName").val("");
		$("#password").val("");
		$("#trueName").val("");
		$("#role").val("");
	}
</script>

<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title"><span class="glyphicon glyphicon-edit"></span> ${actionName }</h3>
  </div>
  <div class="panel-body">
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/user/save.do" onsubmit="return checkForm()">
	  <div class="form-group">
	    <label class="col-sm-2 control-label">
	    	<input type="hidden" id="userId" name="userId" value="${user.userId }"/>
	    	用户名：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="userName" name="userName" value="${user.userName }" style="width: 300px">
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">密码：</label>
	    <div class="col-sm-10">
	      <input type="password" class="form-control" id="password" name="password" value="${user.password }" style="width: 300px">
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">真实姓名：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="trueName" name="trueName" value="${user.trueName }" style="width: 300px">
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">用户角色：</label>
	    <div class="col-sm-10">
	    	<select class="form-control" style="width: 300px" id="role" name="role">
	    		<option value="">请选择...</option>
	    		<option value="0" ${'0'==user.role?'selected':''}>人资总监</option>
	    		<option value="1" ${'1'==user.role?'selected':''}>招聘专员</option>
	    		<option value="2" ${'2'==user.role?'selected':''}>培训专员</option>
	    		<option value="3" ${'3'==user.role?'selected':''}>人事专员</option>
	    	</select>
	    </div>
	  </div>
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <button type="submit" class="btn btn-primary">保存</button>&nbsp;&nbsp;
	      <button type="button" class="btn btn-primary" onclick="resetValue()">重置</button>&nbsp;&nbsp;
	      <button type="button" class="btn btn-primary" onclick="javascript:history.back(-1)">返回</button>&nbsp;&nbsp;
	      <span id="error" style="color:red;"></span>
	    </div>
	  </div>
	</form>
  </div>
</div>