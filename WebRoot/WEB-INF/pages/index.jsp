<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主页</title>
<link href="/css/bootstrap.min.css" rel="stylesheet">
<meta name="viewport" content="width-device-wdith,initia-scale=1.0"/>

<style type="text/css">
body {
	padding-top: 80px;
	padding-bottom: 40px;
	font-family: Candara, arial,微软雅黑,sans-serif;
}
.sidebar-nav {
	padding: 9px 0;
}

</style>

<!--[if lte IE 6]>
  <link rel="stylesheet" type="text/css" href="/css/bootstrap-ie6.css">
  <![endif]-->
  <!--[if lte IE 7]>
  <link rel="stylesheet" type="text/css" href="/css/ie.css">
  <![endif]-->
  
  
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container"> <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> 
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </a>
				<div class="nav-collapse collapse">
					<p class="navbar-text pull-right" ><a href="/exit" class="navbar-link" >退出<i class="icon-off icon-white" style="margin-left:2px"></i></a></p> 
					<div class="navbar-text pull-right"> 
					  <i class="icon-user icon-white" style="margin-left:2px"></i>
						${session.UserName}
					  <b class="caret"></b>
					</div>
				  
					<p class="navbar-text pull-right">欢迎您: </p> 
				  
				  
					<!--判断是否登录
					<p class="navbar-text pull-right" style="margin-left:6px"> |<a href="/regpg" class="navbar-link" style="margin-left:6px">免费注册</a></p>
					<p class="navbar-text pull-right"><a href="/loginpg" class="navbar-link">登录 </a></p>
					-->
					<ul class="nav">
						<li><a href="/" style="font-size:15px"><i class="icon-home icon-white" style="margin-right:2px"></i>WagePay</a></li>            
						<li><a href="/pro/helppg"><i class="icon-question-sign icon-white" style="margin-right:2px"></i>本学年情况</a></li>
						<li><a href="/pro/all"><i class="icon-eye-open icon-white" style="margin-right:2px"></i>查看所有</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
<div class="row">
	<div class="span8 offset2">
		<div class="row"><i class="icon-th-list" style="margin-left:2px"></i><strong style="margin-left:2px">本学期课时信息</strong>

			<!--本学期课时合计-->
			<table class="table table-bordered">
				<tbody>
					<tr>
						<td width="15%">本科总课时</td>
						<td width="15%">研究生总课时</td>
						<td width="15%">成教总课时</td>
						<td width="15%">毕设总课时</td>
						<td width="15%">合计总课时</td>
					</tr>
					<c:forEach items="${classhourlist}" var="classhour">
					<tr> 
						<td>${classhour.本科总课时}</td>
						<td>${classhour.研究生总课时}</td>
						<td>${classhour.成教总课时}</td>
						<td>${classhour.毕设总课时}</td>
						<td>${classhour.合计总课时}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<!--确认按钮-->
			<div class="span2 pull-right">
				<button type="submit" class="btn btn-success btn-block">确认</button>
			</div>
		</div>
		
		
		<!--判断该课时是否为0-->
		<div class="row">
			<hr>
			<h4>详细信息如下：</h4>
		</div>
		<div class="row"><i class="icon-tag" style="margin-left:2px"></i><strong style="margin-left:2px">本科课时信息</strong>
			<div class="pull-right"><i class="icon-question-sign" style="margin-left:2px"></i><a href="#">查看详细</a></div>
			<!--本科课时-->
			<table class="table table-bordered">
				<tbody>
					<tr>
						<td width="15%">课程名称</td>
						<td width="15%">总课时</td>
						<td width="30%">补充说明</td>
						<td width="15%">最终总课时</td>
					</tr>
					<c:forEach items="${undergraduate_classhourlist}" var="classhour">
					<tr> 
						<td><c:out value="${classhour.课程名称}" default=""/></td>
						<td>${classhour.总课时}</td>
						<td>${classhour.补充说明}</td>
						<td>${classhour.最终总课时}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="row"><i class="icon-tag" style="margin-left:2px"></i><strong style="margin-left:2px">研究生课时信息</strong>
			<div class="pull-right"><i class="icon-question-sign" style="margin-left:2px"></i><a href="#">查看详细</a></div>
			<!--研究生课时-->
			<table class="table table-bordered">
				<tbody>
					<tr>
						<td width="15%">课程名称</td>
						<td width="15%">总课时</td>
						<td width="30%">备注</td>
						<td width="15%">最终总课时</td>
					</tr>
					<c:forEach items="${graduate_classhourlist}" var="classhour">
					<tr> 
						<td><c:out value="${classhour.课程名称}" default=""/></td>
						<td>${classhour.总计课时}</td>
						<td>${classhour.备注}</td>
						<td>${classhour.最终总课时}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>		
	</div>

	<div class="span3 offset1">
		<div class="row"><i class="icon-bullhorn"></i><strong style="margin-left:2px">公告</strong>
			<div class="alert alert-info">
				<ol>
					<li>请确认信息是否有误。</li><br/>			
					<li>若无误，点击确认按钮确认。</li>		
				</ol>
			</div>
		</div>
	</div>

</div>

<script type="text/javascript" src="/js/bootstrap-ie.js"></script>
<script  type="text/javascript" src="/js/bootstrap.min.js"></script> 
</body>
</html>