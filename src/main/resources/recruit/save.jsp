<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	
	function checkForm(){
		var name=$("#name").val();
		var sex=$("#sex").val();
		var idcard=$("#idcard").val();
		var tellphone=$("#tellphone").val();
		var recruitFrom=$("#recruitFrom").val();
		if(name==null || name==""){
			$("#error").html("姓名不能为空！");
			return false;
		}
		if(sex==null || sex==""){
			$("#error").html("性别不能为空！");
			return false;
		}
		if(idcard==null || idcard==""){
			$("#error").html("身份证号不能为空！");
			return false;
		}
		if(tellphone==null || tellphone==""){
			$("#error").html("联系方式不能为空！");
			return false;
		}
		if(recruitFrom==null || recruitFrom==""){
			$("#error").html("请选择招聘来源！");
			return false;
		}
		return true;
	}
	
	
	function resetValue(){
		$("#name").val("");
		$("#sex").val("");
		$("#idcard").val("");
		$("#recruitFrom").val("");
	}
</script>

<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title"><span class="glyphicon glyphicon-edit"></span> ${actionName }</h3>
  </div>
  <div class="panel-body">
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/recruit/save.do" onsubmit="return checkForm()">
	  <div class="form-group">
	    <label class="col-sm-2 control-label">
	    	<input type="hidden" id="id" name="id" value="${recruit.id }"/>
	    	姓名：
	    </label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="name" name="name" value="${recruit.name }" style="width: 300px">
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">性别：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="sex" name="sex" value="${recruit.sex }" style="width: 300px">
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">身份证号：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="idcard" name="idcard" value="${recruit.idcard }" style="width: 300px">
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">联系方式：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="tellphone" name="tellphone" value="${recruit.tellphone }" style="width: 300px">
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">招聘来源：</label>
	    <div class="col-sm-10">
	        <select class="form-control" id="recruitFrom" name="recruitFrom" style="width: 300px">
			    <option value="">请选择招聘来源...</option>
				<c:forEach var="recruitFromData" items="${recruitFromDataDicList }">
					<option value="${recruitFromData.ddValue }" ${employee.recruitFrom==recruitFromData.ddValue?'selected':'' }>${recruitFromData.ddValue }</option>
				</c:forEach>
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