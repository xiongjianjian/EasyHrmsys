<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	function userDelete(ddId){
		if(confirm("确定要删除这条记录吗?")){
			//JQuery Ajax请求
			$.post("${pageContext.request.contextPath}/dataDic/delete.do",{ddId:ddId},
				function(result){
					var result=eval('('+result+')');
					if(result.errorInfo){
						alert(result.errorInfo)
					}else{
						alert("删除成功");
						window.location.href="/list.do";
					}
				}
			);
		}
	}
</script>
<div class="row search">
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/dataDic/list.do" method="post">
	    <div class="input-group" style="width: 300px">
		      <input type="text" class="form-control" name="ddValue"  value="${s_dataDic.ddValue }" placeholder="请输入...">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		      </span>
	    </div>
    </form>
  </div>
  <div class="col-md-6" >
  	<button type="button" class="btn btn-primary" style="float: right;" onclick="javascript:window.location.href='/preSave.do'">添加</button>
  </div>
</div>
<div>
	<table class="table table-striped table-bordered table-hover table-condensed" style="margin-top: 10px;">
	  <tr>
	  	<th>序号</th>
	  	<th>数据字典值</th>
	  	<th>数据字典描述</th>
	  	<th>所属数据字典类别</th>
	  	<th>操作</th>
	  </tr>
	  <c:forEach var="dataDic" items="${dataDicList }" varStatus="status">
	  	<tr>
	  		<td>${status.index+1 }</td>
	  		<td>${dataDic.ddValue }</td>
	  		<td>${dataDic.ddDesc }</td>
	  		<td>${dataDic.ddTypeName }</td>
	  		<td>
	  			<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='/preSave.do?ddId=${dataDic.ddId }'">修改</button>
	  			<button type="button" class="btn btn-danger btn-xs" onclick="userDelete(${dataDic.ddId })">删除</button>
	  		</td>
	  	</tr>
	  </c:forEach>
	</table>
	<!-- list后的分页 -->
	<nav aria-label="Page navigation" class="text-center">
	  	<ul class="pagination">
			${pageCode }
		</ul>
	</nav>
</div>
