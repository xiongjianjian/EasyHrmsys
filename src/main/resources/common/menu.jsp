<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="list-group">
  <a href="#" class="list-group-item active">系统菜单</a>
  <a href="${pageContext.request.contextPath}/user/list.do" class="list-group-item">系统用户管理</a>
  <a href="${pageContext.request.contextPath}/dataDic/list.do" class="list-group-item">系统数据管理</a>
  <a href="${pageContext.request.contextPath}/recruit/list.do" class="list-group-item">招聘管理</a>
  <a href="${pageContext.request.contextPath}/interview/list.do" class="list-group-item">面试管理</a>
  <a href="${pageContext.request.contextPath}/employee/list.do" class="list-group-item">人事管理</a>
  <a href="${pageContext.request.contextPath}/train/list.do" class="list-group-item">培训管理</a>
  <a href="${pageContext.request.contextPath}/salary/list.do" class="list-group-item">员工奖惩</a>
  <a href="${pageContext.request.contextPath}/salary/list.do" class="list-group-item">员工考评</a>
  <a href="${pageContext.request.contextPath}/salary/list.do" class="list-group-item">员工调薪</a>
  <a href="${pageContext.request.contextPath}/salary/list.do" class="list-group-item">员工调动</a>
  <a href="${pageContext.request.contextPath}/user/logout.do" class="list-group-item">退出系统</a>
</div>