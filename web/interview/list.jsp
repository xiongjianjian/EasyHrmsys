<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">	
	function beginInterview(){
		var selectOne=$("#selectOne").val();
		if(selectOne==""){
			alert("请选择应聘者！");
			return;
		}
		$('#selectInterviewer').modal('show'); //打开模态框
		$("#chooseInte").submit(function() {
			//取面试官ID，求情后台
			var chooseInterviewer=$("#chooseInterviewer").val();
			if(chooseInterviewer==""){
				alert("请选择面试官！");
				return false;
			}
			$.post("${pageContext.request.contextPath}/interview/begin.do",{recruitId:selectOne,userId:chooseInterviewer},function(result){
				var result=eval('('+result+')');
				if(result.success=="true"){
					window.location.href="/list.do";
				}
	        });			
		});
	}

	function pass(id){
		$('#passModal').modal('show'); //打开模态框
		$("#passForm").submit(function() {
			$.post("${pageContext.request.contextPath}/interview/setPass.do",{id:id},
				function(result){
					var result=eval('('+result+')');
					if(result.success=="true"){
						window.location.href="/list.do";
					}
				}
			);
		});
	}
	function notPass(id){
		$('#notPassModal').modal('show'); //打开模态框
		$("#notPassForm").submit(function() {			
			var missReason=$('#missReason').val(); //取得措施原因
			//JQuery Ajax请求
			$.post("${pageContext.request.contextPath}/interview/setNotPass.do",{id:id,missReason:missReason},
				function(result){
					var result=eval('('+result+')');
					if(result.success=="true"){
						window.location.href="/list.do";
					}
				}
			);
		});
	}
</script>

<div class="row">
	<div class="col-md-6">
		<form class="form-inline">
		  <div class="form-group">
		    <label for="exampleInputName2">选择应聘者</label>
		    <select class="form-control" id="selectOne" name="selectOne">
		      <option value="">请选择...</option>
		      <c:forEach var="recruit" items="${recruitList }">
		      	<option value="${recruit.id }">${recruit.name }&nbsp;&nbsp;${recruit.idcard }&nbsp;&nbsp;${recruit.recruitFrom }</option>
		      </c:forEach>
			</select>
		  </div>
		</form>	
	</div>
	<div class="col-md-6">
		<button class="btn btn-default" id="beginCon" onclick="beginInterview()">面试开始</button>
	</div>
</div>


<div class="row search" style="margin-top: 20px;">
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/interview/list.do" method="post">
	    <div class="input-group" style="width: 300px">
		      <input type="text" class="form-control" name="name"  value="${s_interview.name }" placeholder="请输入姓名...">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		      </span>
	    </div>
    </form>
  </div>
</div>
<div>
	<table class="table table-striped table-bordered table-hover table-condensed" style="margin-top: 10px;">
	  <tr>
	  	<th>序号</th>
	  	<th>姓名</th>
	  	<th>性别</th>
	  	<th>身份证号</th>
	  	<th>联系方式</th>
	  	<th>招聘来源</th>
	  	<th>面试官</th>
	  	<th>状态</th>
	  	<th>错失原因</th>
	  	<th>操作</th>
	  </tr>
	  <c:forEach var="interview" items="${interviewList }" varStatus="status">
	  	<tr>
	  		<td>${status.index+1 }</td>
	  		<td>${interview.name }</td>
	  		<td>${interview.sex }</td>
	  		<td>${interview.idcard }</td>
	  		<td>${interview.tellphone }</td>
	  		<td>${interview.recruitFrom }</td>
	  		<td>${interview.interviewer }</td>
	  		<td>
	  			<c:if test="${interview.isOk==0 }"><span style="color: blue;">待定</span></c:if>
	  			<c:if test="${interview.isOk==-1 }"><span style="color: red;">淘汰</span></c:if>
	  			<c:if test="${interview.isOk==1 }"><span style="color: green;">合格</span></c:if>
	  		</td>
	  		<td>${interview.missReason }</td>
	  		<td>
	  			<button type="button" class="btn btn-info btn-xs" onclick="pass(${interview.id })">通过</button>
	  			<button type="button" class="btn btn-danger btn-xs" onclick="notPass(${interview.id })">不通过</button>
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


<div id="selectInterviewer" class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">选择面试官</h4>
      </div>
      <div class="modal-body">
      	<form class="form-inline" id="chooseInte">
		  <div class="form-group">
		    <div class="input-group">
		        <select class="form-control" id="chooseInterviewer" name="chooseInterviewer">
		        	<option value="">请选择...</option>
			        <c:forEach var="userExceptSuperAdmin" items="${userListExceptSuperAdmin }">
			        	<option value="${userExceptSuperAdmin.userId }">${userExceptSuperAdmin.trueName }</option>
			        </c:forEach>
				</select>
		    </div>
		  </div>
		  <button type="submit" class="btn btn-primary">选择</button>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<div id="passModal" class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">信息提示！</h4>
      </div>
      <div class="modal-body">
        <form class="form-inline" id="passForm">
		  <div class="form-group">
		    <label>确定通过面试吗？</label>
		  </div>
		  <button type="submit" class="btn btn-primary">确定</button>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<div id="notPassModal" class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">信息提示！</h4>
      </div>
      <div class="modal-body">
        <form class="form-inline" id="notPassForm">
		  <div class="form-group">
		    <label>确定淘汰面试者吗？</label>
		  </div>
		  <div class="form-group">
		    <label>面试者措施原因？</label>
		    <input class="form-control" type="text" id="missReason" name="missReason">
		  </div>
		  <button type="submit" class="btn btn-primary">确定</button>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->