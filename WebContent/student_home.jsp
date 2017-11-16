<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
    <%@ page import="com.user.mysqloperate.Mysqloperate"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Set"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.List"%>
<%@ page import="com.user.successbooking.successbooking"%>
<% //登录用户信息
  /* String s = (String) request.getAttribute("test");  */
  String id=new String(session.getAttribute("id").toString().getBytes("ISO-8859-1"),"UTF-8");
  
  Mysqloperate mysql=new Mysqloperate();
  Map<String,String> infomation=mysql.showstudent(id);
  String username=infomation.get("用户名");
  String password=infomation.get("密码");
  String email=infomation.get("邮箱");
  String phone=infomation.get("手机号");
  String introduction=infomation.get("介绍");
  String theid=infomation.get("id"); 
  String faculty=infomation.get("学院");
  
  List<successbooking> mysuccessbooking=mysql.stushowtime(id);
  int successbookingnum=mysuccessbooking.size();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>OnlinebookingSystem</title>
	<link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/font-awesome.min.css" rel="stylesheet">
	<link href="css/datepicker3.css" rel="stylesheet">
	<link href="css/styles.css" rel="stylesheet">
	
	<!--Custom Font-->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
<style>
.myfont1{
  font-size:18px;
}
</style>
</head>
<body>
	<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<!-- <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse"><span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span></button>  -->
				<a class="navbar-brand" href="#"><span>Online</span>BookingSystem</a>
				<ul class="nav navbar-top-links navbar-right"><!-- navbar-left/navbar-right -->
					<li class="dropdown"><!-- 新消息导航 -->
					  <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
						<em class="fa fa-envelope"></em><!-- 消息图案 -->
						<span class="label label-danger">15</span><!-- 消息数量  -->
					  </a>
						<ul class="dropdown-menu dropdown-messages"><!-- 消息概述，列表呈现  -->
							<li>
								<div class="dropdown-messages-box">
								    <a href="profile.html" class="pull-left"><!-- 点击图像显示对方个人主页 --><!--pull-right/pull-left设置头像左右  -->
									   <img alt="image" class="img-circle" src="http://placehold.it/40/30a5ff/fff">
									</a>
									<div class="message-body">
									    <small class="pull-right">3 mins ago</small><!-- 消息时间距离现在 -->
										<a href="#"><strong> LXY（professor）</strong> Accepted your appointment<strong>(2017.10.25)</strong>.</a><br /><!-- 点击消息跳转至预约情况页面 -->
									    <small class="text-muted"> 18:29pm - 22/10/2017</small><!-- 消息时间发送 -->
									</div>
								</div>
							</li>
							<li class="divider"></li>
							<li>
								<div class="dropdown-messages-box"><a href="profile.html" class="pull-left">
									<img alt="image" class="img-circle" src="http://placehold.it/40/30a5ff/fff">
									</a>
									<div class="message-body"><small class="pull-right">1 hour ago</small>
										<a href="#">New message from <strong>WCY（student）</strong>.</a><!-- 点击消息跳转至消息页面 -->
									<br /><small class="text-muted">17.33 pm - 22/10/2017</small></div>
								</div>
							</li>
							<li class="divider"></li>
							<li>
								<div class="all-button"><a href="#"><!-- 查看所有信息至消息页面 -->
									<em class="fa fa-inbox"></em> <strong>All Messages</strong>
								</a></div>
							</li>
						</ul>
					</li>
					<li class="dropdown"><!-- 预约提醒导航 -->
					  <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
						<em class="fa fa-bell"></em><span class="label label-info">5</span><!--未完成预约数量  -->
					  </a>
						<ul class="dropdown-menu dropdown-alerts"><!-- 列出所有未完成预约 dropdown-alerts-->
							<li>
								<div class="dropdown-messages-box">
								    <a href="profile.html" class="pull-left"><!-- 点击图像显示对方个人主页 -->
									   <img alt="image" class="img-circle" src="http://placehold.it/40/30a5ff/fff">
									</a>
									<div class="message-body">
									    <small class="pull-right">2017/10/25</small><!-- 预约日期 -->
										<a href="#"><strong class="myfont1"> LXY（professor）</strong></a><!-- 点击跳转至预约情况页面 -->
									</div>
								</div>
							</li>
							<li class="divider"></li>
							<li>
							  <div class="dropdown-messages-box">
								    <a href="profile.html" class="pull-left"><!-- 点击图像显示对方个人主页 -->
									   <img alt="image" class="img-circle" src="http://placehold.it/40/30a5ff/fff">
									</a>
									<div class="message-body">
									    <small class="pull-right">2017/10/28</small><!-- 预约日期 -->
										<a href="#"><strong class="myfont1"> WCY（professor）</strong></a><!-- 点击跳转至预约情况页面 -->
									</div>
							 </div>
							</li>
							<li class="divider"></li>
							<li>
							    <div class="dropdown-messages-box">
								    <a href="profile.html" class="pull-left"><!-- 点击图像显示对方个人主页 -->
									   <img alt="image" class="img-circle" src="http://placehold.it/40/30a5ff/fff">
									</a>
									<div class="message-body">
									    <small class="pull-right">2017/11/10</small><!-- 预约日期 -->
										<a href="#"><strong class="myfont1"> LBH(professor)</strong></a><!-- 点击跳转至预约情况页面 -->
									</div>
							  </div>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</div><!-- /.container-fluid -->
	</nav>
	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar"><!--侧面导航条  -->
		<div class="profile-sidebar"><!-- 头像 -->
			<div class="profile-userpic">
				<img src="images/2.jpg" class="img-responsive" alt="loading">
			</div>
			<div class="profile-usertitle">
				<div class="profile-usertitle-name"><%=username%></div>
				<div class="profile-usertitle-status"><span class="indicator label-success"></span>Online</div>
			</div>
			<div class="clear"></div>
		</div>
		<div class="divider"></div>
		
		<form role="search"><!-- 写js脚本，在按下enter之后直接提交表单 -->
			<div class="form-group">
				<input type="text" class="form-control" placeholder="输入要预约的教授的名字">
			</div>
		</form>
		<ul class="nav menu">
			<li class="active"><a href="student_home.jsp"><em class="fa fa-dashboard">&nbsp;</em>  首   页</a></li>
			<li><a href="student_book.jsp"><em class="fa fa-calendar">&nbsp;</em> 预   约</a></li>
			<li><a href="student_chat.jsp"><em class="fa fa-comments">&nbsp;</em> 消 息</a></li>
			<li><a href="student_profile.jsp"><em class="fa fa-user">&nbsp;</em> 个 人 主 页</a></li>
			<li class="parent "><a data-toggle="collapse" href="#sub-item-1">
				<em class="fa fa-navicon">&nbsp;</em> 更 多 功 能 <span data-toggle="collapse" href="#sub-item-1" class="icon pull-right"><em class="fa fa-plus"></em></span>
				</a>
				<ul class="children collapse" id="sub-item-1">
					<li><a class="" href="#">
						<span class="fa fa-arrow-right">&nbsp;</span> 功 能 1
					</a></li>
					<li><a class="" href="#">
						<span class="fa fa-arrow-right">&nbsp;</span> 功 能 2
					</a></li>
					<li><a class="" href="#">
						<span class="fa fa-arrow-right">&nbsp;</span> 功 能 3
					</a></li>
				</ul>
			</li>
			<li><a href="login.jsp"><em class="fa fa-power-off">&nbsp;</em> Logout</a></li>
		</ul>
	</div><!--/.sidebar-->
		
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main"><!-- 首页主面板  -->
	      <!-- col-sm-9指定宽度， col-sm-offset-3指定偏移，即位置，lg与sm对应不同尺寸-->
		<div class="row"><!-- 页眉 -->
			<ol class="breadcrumb">
				<li>
				  <a href="student_home.jsp">
					  <em class="fa fa-home"></em>
				   </a>
				</li>
				<li class="active">首页</li>
			</ol>
		</div><!--/.row-->
		
		<!-- <div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">首 页 </h1>
			</div>
		</div>/.row -->
		
		<div class="panel panel-container">
			<div class="row">
				<div class="col-xs-6 col-md-3 col-lg-3 no-padding"><!-- 屏幕小的时候会成两排 -->
					<div class="panel panel-teal panel-widget border-right">
						<div class="row no-padding"><em class="fa fa-xl fa-users color-blue"></em>
							<div class="large">120</div>
							<div class="text-muted">All Professors</div>
						</div>
					</div>
				</div>
				<div class="col-xs-6 col-md-3 col-lg-3 no-padding">
					<div class="panel panel-blue panel-widget border-right">
						<div class="row no-padding"><em class="fa fa-xl fa-calendar color-orange"></em>
							<div class="large"><%=successbookingnum%></div>
							<div class="text-muted">Appointments</div>
						</div>
					</div>
				</div>
				<div class="col-xs-6 col-md-3 col-lg-3 no-padding">
					<div class="panel panel-orange panel-widget border-right">
						<div class="row no-padding"><em class="fa fa-xl fa-history color-red"></em>
							<div class="large">10</div>
							<div class="text-muted">Historys</div>
						</div>
					</div>
				</div>
				<div class="col-xs-6 col-md-3 col-lg-3 no-padding">
					<div class="panel panel-red panel-widget ">
						<div class="row no-padding"><em class="fa fa-xl fa-comments color-teal"></em>
							<div class="large">15</div>
							<div class="text-muted">Messages</div>
						</div>
					</div>
				</div>
			</div><!--/.row-->
		</div>
		
		<div class="row">  <!-- 展示自己最近的预约情况，包括预约失败的和预约成功的 -->
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						近期预约总览
						<ul class="pull-right panel-settings panel-button-tab-right">
							<li class="dropdown"><a class="pull-right dropdown-toggle" data-toggle="dropdown" href="#">
								<em class="fa fa-cogs"></em>
							</a>
								<ul class="dropdown-menu dropdown-menu-right">
									<li>
										<ul class="dropdown-settings">
											<li><a href="#">
												<em class="fa fa-cog"></em> 功能1
											</a></li>
											<li class="divider"></li>
											<li><a href="#">
												<em class="fa fa-cog"></em> 功能2
											</a></li>
											<li class="divider"></li>
											<li><a href="#">
												<em class="fa fa-cog"></em> 功能3
											</a></li>
										</ul>
									</li>
								</ul>
							</li>
						</ul>
						
						<span class="pull-right clickable panel-toggle panel-button-tab-left"><em class="fa fa-toggle-up"></em></span></div>
					
					<div class="panel-body">
						 <div class="canvas-wrapper">
							<canvas class="main-chart" id="line-chart" height="200" width="600"></canvas>
						</div>
					</div>
				</div>
			</div>
		</div>
	  <!--/.row-->
		
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						待赴预约
						<ul class="pull-right panel-settings panel-button-tab-right">
							<li class="dropdown"><a class="pull-right dropdown-toggle" data-toggle="dropdown" href="#">
								<em class="fa fa-cogs"></em>
							</a>
								<ul class="dropdown-menu dropdown-menu-right">
									<li>
										<ul class="dropdown-settings">
											<li><a href="#">
												<em class="fa fa-cog"></em> 功 能 1
											</a></li> 
											<li class="divider"></li>
											<li><a href="#">
												<em class="fa fa-cog"></em> 功 能 2
											</a></li>
											<li class="divider"></li>
											<li><a href="#">
												<em class="fa fa-cog"></em> 功 能 3
											</a></li>
										</ul>
									</li>
								</ul>
							</li>
						</ul>
						<span class="pull-right clickable panel-toggle panel-button-tab-left"><em class="fa fa-toggle-up"></em></span></div>
					<div class="panel-body">
						<ul class="todo-list"><!-- 所有未完成预约 -->
						
							<% 
						    int i=0;
						     for(successbooking asuccessbooking:mysuccessbooking){
			        		 i++;
			        		 String astudentid=asuccessbooking.studentid;
			        		 String ayear=asuccessbooking.year;
			        		 String amonth=asuccessbooking.month;
			        		 String aday=asuccessbooking.day;
			        	     String ateacherid=asuccessbooking.teacherid;
			        	     String atime=asuccessbooking.time;
			        		 String ainstruction=asuccessbooking.instruction;
			        		 
			        		 Map<String,String> sinfomation=mysql.showteacher(ateacherid);
			        	     String susername=sinfomation.get("用户名");
			        		 String semail=sinfomation.get("邮箱");
			        		 String sphone=sinfomation.get("手机号");
			        		 String sintroduction=sinfomation.get("介绍");
			        		 String stheid=sinfomation.get("id"); 
			        		 String sfaculty=sinfomation.get("科目");

				             out.println("<li class=\"todo-list-item\">");
				             out.println("<div class=\"checkbox\">");
				             out.println("<input type=\"checkbox\" id=\"checkbox-"+i+"\" />");/*注意这个id要是变量 */
				             out.println("<label for=\"checkbox-"+i+"\">与"+susername+"教授于"+ayear+"-"+amonth+"-"+aday+"约见，见面时间:"+atime+",联系方式："+sphone+"/"+semail+"</label>");
				             out.println("</div>");
				             out.println("<div class=\"pull-right action-button\"><a href=\"#\" class=\"trash\">");
				             out.println("<em class=\"fa fa-trash\"></em>");
				             out.println("</a></div>");
				             out.println("</li>");
						     }
				             %>
							
						</ul>
					</div>
					<div class="panel-footer">
						<div class="input-group">
							<input id="btn-input" type="text" class="form-control input-md" placeholder="Delete selected appointments" /><span class="input-group-btn">
								<button class="btn btn-primary btn-md" id="btn-todo">Delete</button>
						</span></div>
					</div>
				</div>
			</div><!--/.col-->
			
			<div class="col-sm-12">
				<p class="back-link">OnlineBookingSystem by 王春阳</p>
			</div>
		
		</div><!--/.row-->
	</div>	<!--/.main-->
	
	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/chart.min.js"></script>
	<script src="js/chart-data.js"></script>
	<script src="js/easypiechart.js"></script>
	<script src="js/easypiechart-data.js"></script>
	<script src="js/bootstrap-datepicker.js"></script>
	<script src="js/custom.js"></script>
	<script>
    window.onload = function () {
	var chart1 = document.getElementById("line-chart").getContext("2d");
	window.myLine = new Chart(chart1).Line(lineChartData, {
	responsive: true,
	scaleLineColor: "rgba(0,0,0,.2)",
	scaleGridLineColor: "rgba(0,0,0,.05)",
	scaleFontColor: "#c5c7cc"
	});
};
    alert("<%=id%>");
	</script>
		
</body>
</html>