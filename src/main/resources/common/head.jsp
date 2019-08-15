<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#"><span class="glyphicon glyphicon-globe"></span> 人力资源管理系统</a>
    </div>
    <div id="navbar" class="navbar-collapse collapse">
      <div id="navbar" class="navbar-right">
        <a class="navbar-brand" href="#"><span class="glyphicon glyphicon-hand-right"></span> 当前用户：${currentUser.userName }『${currentUser.trueName }』</a>
      </div>
    </div><!--/.navbar-collapse -->
  </div>
</nav>
