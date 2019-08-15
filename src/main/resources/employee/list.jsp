<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function employeeDelete(id){
		if(confirm("确定要删除这条记录吗?")){
			$.post("${pageContext.request.contextPath}/employee/delete.do",{id:id},
				function(result){
					var result=eval('('+result+')');
					if(result.success=="true"){
						alert("删除成功");
					}else{
						alert("删除失败");
					}
					window.location.href="${pageContext.request.contextPath}/employee/list.do";
				}
			);
		}
	}
</script>
<div class="row search" >
  <div class="col-md-10">
	<form class="form-inline" action="${pageContext.request.contextPath}/employee/list.do" method="post">
	  <div class="form-group">
	    <label class="" for="exampleInputEmail3">工号</label>
	    <input type="text" class="form-control" id="empName" name="empName" value="${s_employee.empName }" placeholder="请输入工号...">
	  </div>
	  <div class="form-group">
	    <label class="" for="exampleInputPassword3">姓名</label>
	    <input type="text" class="form-control" id="empName" name="empName" value="${s_employee.empName }" placeholder="请输入姓名...">
	  </div>
	  <div class="checkbox">
	    <label>
	      <input type="checkbox" id="" name=""> 是否显示离职员工
	    </label>
	  </div>
	  <button type="submit" class="btn btn-default">查询</button>
	</form>
  </div>
  <div class="col-md-2" >
  	<button type="button" class="btn btn-primary" style="float: right;" onclick="javascript:window.location.href='${pageContext.request.contextPath}/employee/preSave.do'">添加</button>
  </div>
</div>
<div>
	<table class="table table-striped table-bordered table-hover table-condensed" style="margin-top: 10px;">
	  <tr>
	  	<th>序号</th>
	  	<th>工号</th>
	  	<th>姓名</th>
	  	<th>性别</th>
	  	<th>民族</th>
	  	<th>政治面貌</th>
	  	<th>学历</th>
	  	<th>专业</th>
	  	<th>雇佣日期</th>
	  	<th>聘用形式</th>
	  	<th>部门</th>
	  	<th>操作</th>
	  </tr>
	  <c:forEach var="employee" items="${employeeList }" varStatus="status">
	  	<tr>
	  		<td>${status.index+1 }</td>
	  		<td>${employee.empNo }</td>
	  		<td>${employee.empName }</td>
	  		<td>${employee.empSex }</td>
	  		<td>${employee.empNation }</td>
	  		<td>${employee.empZzmm }</td>
	  		<td>${employee.empRecord }</td>
	  		<td>${employee.major }</td>
	  		<td><fmt:formatDate value="${employee.hiredate }" type="date" pattern="yyyy-MM-dd"/></td>
	  		<td>${employee.empForm }</td>
	  		<td>${employee.deptName }</td>
	  		<td>
	  			<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/employee/view.do?id=${employee.empId }'">查看</button>
	  			<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/employee/preSave.do?id=${employee.empId }'">修改</button>
	  			<button type="button" class="btn btn-danger btn-xs" onclick="employeeDelete(${employee.empId })">删除</button>
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

