<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	function recruitDelete(id){
		if(confirm("确定要删除这条记录吗?")){
			$.post("${pageContext.request.contextPath}/recruit/delete.do",{id:id},
				function(result){
					var result=eval('('+result+')');
					if(result.errorInfo){
						alert(result.errorInfo)
					}else{
						alert("删除成功");
						window.location.href="${pageContext.request.contextPath}/recruit/list.do";
					}
				}
			);
		}
	}
	
	function recruitCheckHealth(id) {
		if(confirm("确定体检通过吗?")){
			$.post("${pageContext.request.contextPath}/recruit/checkHealth.do",{id:id},
				function(result){
					var result=eval('('+result+')');
					if(result.errorInfo){
						alert(result.errorInfo)
					}else{
						alert("体检通过");
						window.location.href="${pageContext.request.contextPath}/recruit/list.do";
					}
				}
			);
		}
	}
	
	function recruitCheckIdcard(id) {
		if(confirm("确定身份证有效吗?")){
			$.post("${pageContext.request.contextPath}/recruit/checkIdcard.do",{id:id},
				function(result){
					var result=eval('('+result+')');
					if(result.errorInfo){
						alert(result.errorInfo)
					}else{
						alert("身份证通过");
						window.location.href="${pageContext.request.contextPath}/recruit/list.do";
					}
				}
			);
		}
	}
	
	$(function () { //Bootstrap工具提示
		  $('[data-toggle="tooltip"]').tooltip()
	})
	
	function baseInfoTable() {
		$('#myModalOfBaseInfo').modal('show');
	}
	
	function healthTable() {
		$('#myModalOfHealth').modal('show');
	}

		
</script>

<div class="row">
<div class="col-md-12">
	<form class="form-inline">
	  <button type="button" class="btn btn-info btn-sm" 
	  	data-toggle="tooltip" 
	  	data-placement="top" onclick="baseInfoTable()"
	  	title="打印招聘信息表">招聘信息表</button>
	  <button type="button" class="btn btn-info btn-sm" 
	  	data-toggle="tooltip" 
	  	data-placement="top" onclick="healthTable()"
	  	title="打印体检表">体检表</button>
	</form>
</div>
</div>
<div class="row search" style="margin-top: 20px;">
  <div class="col-md-8">
	<form class="form-inline" action="${pageContext.request.contextPath}/recruit/list.do" method="post">
	  <div class="form-group">
	    <label for="exampleInputName2">姓名</label>
	    <input type="text" class="form-control" id="name" name="name" value="${s_recruit.name }" placeholder="请输入姓名">
	  </div>
	  <div class="form-group">
	    <label for="exampleInputEmail2">省份证号</label>
	    <input type="text" class="form-control" id="idcard" name="idcard" value="${s_recruit.idcard }" placeholder="请输入身份证">
	  </div>
	  <button type="submit" class="btn btn-default">查询</button>
    </form>
  </div>
  <div class="col-md-4" >
  	<button type="button" class="btn btn-primary" style="float: right;" onclick="javascript:window.location.href='${pageContext.request.contextPath}/recruit/preSave.do'">添加</button>
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
	  	<th>健康状况</th>
	  	<th>身份核验</th>
	  	<th>操作</th>
	  </tr>
	  <c:forEach var="recruit" items="${recruitList }" varStatus="status">
	  	<tr>
	  		<td>${status.index+1 }</td>
	  		<td>${recruit.name }</td>
	  		<td>${recruit.sex }</td>
	  		<td>${recruit.idcard }</td>
	  		<td>${recruit.tellphone }</td>
	  		<td>${recruit.recruitFrom }</td>
	  		<td>${recruit.healthyState==0? '<font color="red">待验</font>':'<font color="green">合格</font>'}</td>
	  		<td>${recruit.idcardState==0? '<font color="red">待验</font>':'<font color="green">合格</font>'}</td>
	  		<td>
	  			<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/recruit/preSave.do?id=${recruit.id }'">修改</button>
	  			<button type="button" class="btn btn-danger btn-xs" onclick="recruitDelete(${recruit.id })">删除</button>
	  			<button type="button" class="btn btn-danger btn-xs" onclick="recruitCheckHealth(${recruit.id })">核验体检</button>
	  			<button type="button" class="btn btn-danger btn-xs" onclick="recruitCheckIdcard(${recruit.id })">核验身份</button>
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

<!-- 基本信息表 -->
<div id="myModalOfBaseInfo" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="gridSystemModalLabel">招聘基本信息表打印以及注意事项</h4>
      </div>
      <div class="modal-body">
        <div class="row text-center">
			<!-- img alt="baseInfo" src="${pageContext.request.contextPath}/images/baseInfo.jpg" -->
			假设有信息...
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary">打印</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- sample modal content -->
<div id="myModalOfHealth" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">

      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">体检表打印以及注意事项</h4>
      </div>
      <div class="modal-body">
        <h4>（1）体检项目</h4>
        <p>血液 , 视力, 听力, 心电图 , 血压, 有无色盲、色弱.</p>

        <h4>（2）本单位指定医疗机构</h4>
        <p>合作医院 <a href="#" role="button" class="btn btn-default popover-test" title="A Title" data-content="And here's some amazing content. It's very engaging. right?">查看</a> 建议到指定医院进行体检.</p>

        <h4>（3）不指定医疗机构</h4>
        <p>需县级以上医疗机构并含有发票.</p>

       </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary">打印</button>
      </div>

    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->