<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	
	function checkForm(){
		var ddValue=$("#ddValue").val();
		var ddDesc=$("#ddDesc").val();
		var ddTypeId=$("#ddTypeId").val();
		if(ddValue==null || ddValue==""){
			$("#error").html("数据字典值不能为空！");
			return false;
		}
		if(ddDesc==null || ddDesc==""){
			$("#error").html("数据字典描述不能为空！");
			return false;
		}
		if(ddTypeId==null || ddTypeId==""){
			$("#error").html("请选择所属类别！");
			return false;
		}
		return true;
	}
	
	
	function resetValue(){
		$("#ddValue").val("");
		$("#ddDesc").val("");
		$("#ddTypeId").val("");
	}
</script>

<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title"><span class="glyphicon glyphicon-edit"></span> ${actionName }</h3>
  </div>
  <div class="panel-body">
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/dataDic/save.do" onsubmit="return checkForm()">
	  <div class="form-group">
	    <label class="col-sm-2 control-label">
	    	<input type="hidden" id="ddId" name="ddId" value="${dataDic.ddId }"/>
	    	数据字典值：
	    </label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="ddValue" name="ddValue" value="${dataDic.ddValue }" style="width: 300px">
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">数据字典描述：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="ddDesc" name="ddDesc" value="${dataDic.ddDesc }" style="width: 300px">
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">所属类别：</label>
	    <div class="col-sm-10">
	    	<select class="form-control" style="width: 300px" id="ddTypeId" name="ddTypeId">
	    		<option value="">请选择...</option>
	    		<c:forEach var="dataDicType" items="${dataDicTypeList }">
	    			<option value="${dataDicType.ddTypeId }" ${dataDicType.ddTypeId==dataDic.ddTypeId? 'selected':''}>${dataDicType.ddTypeName }</option>
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