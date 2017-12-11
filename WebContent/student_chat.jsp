<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.user.mysqloperate.Mysqloperate"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.List"%>
<%@ page import="com.user.userhistory.history"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.user.successbooking.successbooking"%>
<%@ page import="java.util.Calendar"%>
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
  
  Calendar today=Calendar.getInstance();
  int nowyear=today.get(Calendar.YEAR);
  int nowmonth=today.get(Calendar.MONTH)+1;
  int nowdate=today.get(Calendar.DATE);
%>
<%//得到所有历史信息
      List<history> allhistory= mysql.stushowhistory(id);
%>
<% //评价结束之后的反馈信息
     String evaluateresult = (String) request.getAttribute("result");    
%>
<% //得到当前所有没评价的历史列表
   List<history> historynograde= mysql.losegrade(id);
   int nogradenum=historynograde.size();
%>
<% //得到当前预约的信息
  List<successbooking> mysuccessbooking=mysql.stushowtime(id);
  int successbookingnum=mysuccessbooking.size();
  int twodaynum=0;
  for(successbooking asuccessbooking:mysuccessbooking){//得到所有两天内的日期数

		 String ayear=asuccessbooking.year;
		 String amonth=asuccessbooking.month;
		 String aday=asuccessbooking.day;
	     
	        Calendar cal=Calendar.getInstance();
	        cal.set(nowyear,nowmonth-1,nowdate);
	        long time1 = today.getTimeInMillis();       //得到当前时间的毫秒数 
	        cal.set(Integer.parseInt(ayear), Integer.parseInt(amonth)-1,Integer.parseInt(aday));
	        long time2 = cal.getTimeInMillis();          
	        long between_days=(time2-time1)/(1000*3600*24);   //得到当前时间相差不超过一天的日期
	        int datediff=Integer.parseInt(String.valueOf(between_days));
	        if(datediff<2){
	        	twodaynum++;
	        }
  }
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
	
    <link href="css/datalist.css" type="text/css" rel="Stylesheet" /> 
 
    
	<!--Custom Font-->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
<style>
.mycolor{
   backgorund:0xff0000;
}
.myfont1{
  font-size:18px;
}
.mymargin{
    margin-left:20px;
    margin-right:20px;
    margin-bottom:20px;
}
.myalign1{
      align:center;
  }
.datalistfont{
  font-size:20px;
}
.myscroll{
  max-height:600px;
  overflow-y: auto;
}
.mymargintop2{
margin-top:13px;
}
.myfont2{
   font-size: 20px;
   font-weight:bold;
}
.mycolor1{
  color:#ff0000;
}
.imagemargin{
   margin-left:10px;
   margin-right:10px;
}
.myscroll2{
  max-height:200px;
  overflow-y: auto;
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
						<span class="label label-danger"><%=nogradenum%></span><!-- 消息数量  -->
					  </a>
					  
						<ul class="dropdown-menu dropdown-messages myscroll1"><!-- 消息概述，列表呈现  -->
						  <%
						  
						  for(history ahistory:historynograde){
						    	 String astudentid=ahistory.studentid;
				        		 String ayear=ahistory.year;
				        		 String amonth=ahistory.month;
				        		 String aday=ahistory.day;
				        		 String atime=ahistory.time;
				        	     String ateacherid=ahistory.teacherid;
				        	     
				        	     Map<String,String> sinfomation=mysql.showteacher(ateacherid);
				        	     String susername=sinfomation.get("用户名");
				        		 String sfaculty=sinfomation.get("科目");
				        		 out.println("<li>");
				        		 out.println("<div class=\"dropdown-messages-box\">");
				        		 /* out.println("<a href=\"#\" class=\"\">"); */
				        		 out.println("<img alt=\"image\" class=\"pull-left img-circle\" src=\"http://placehold.it/40/30a5ff/fff\">");
				        	/* 	 out.println("</a>"); */
				        		 out.println("<div class=\"message-body\">");
				        		/*  out.println("<small class=\"pull-right\">"++"</small>"); */
				        		 out.println("通知：您与<strong>"+susername+"("+sfaculty+")</strong>教授的预约已经结束，请前往对本次预约给出评价.<br /><!-- 点击消息跳转至预约情况页面 -->");
				        		 out.println("<small class=\"text-muted\">"+ayear+"-"+amonth+"-"+aday+"&nbsp;&nbsp;&nbsp;&nbsp;"+atime+"</small>");
				        		 out.println("</div>");
				        		 out.println("</div>");
				        		 out.println("</li>");
				        		 out.println("<li class=\"divider\"></li>");
						  }
						  %>
						
							<li>
								<div class="all-button"><a href="student_chat.jsp"><!-- 查看所有信息至消息页面 -->
									<em class="fa fa-inbox"></em> <strong>All Histories</strong>
								</a></div>
							</li>
						</ul>
					</li>
					<li class="dropdown"><!-- 预约提醒导航 -->
					  <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
						<em class="fa fa-bell"></em><span class="label label-info"><%=twodaynum%></span><!--未完成预约数量  -->
					  </a>
						<ul class="dropdown-menu dropdown-alerts myscroll2"><!-- 列出所有未完成预约 dropdown-alerts-->
							<%//预约提醒导航
							 int i=0,k=0;
						     for(successbooking asuccessbooking:mysuccessbooking){
						    	 String astudentid=asuccessbooking.studentid;
				        		 String ayear=asuccessbooking.year;
				        		 String amonth=asuccessbooking.month;
				        		 String aday=asuccessbooking.day;
				        	     String ateacherid=asuccessbooking.teacherid;
				        	        Calendar cal=Calendar.getInstance();
				        	        cal.set(nowyear,nowmonth-1,nowdate);
				        	        long time1 = today.getTimeInMillis();       //得到当前时间的毫秒数 
				        	        cal.set(Integer.parseInt(ayear), Integer.parseInt(amonth)-1,Integer.parseInt(aday));
				        	        long time2 = cal.getTimeInMillis();          
				        	        long between_days=(time2-time1)/(1000*3600*24);   //得到当前时间相差不超过一天的日期
				        	        int datediff=Integer.parseInt(String.valueOf(between_days));
				        	       
				        	     if(datediff<2){
				        	    	 k++;
				        	    	 /*   String atime=asuccessbooking.time;
					        		 String ainstruction=asuccessbooking.instruction; */
					        		 
					        		 Map<String,String> sinfomation=mysql.showteacher(ateacherid);
					        	     String susername=sinfomation.get("用户名");
					        		 /* String semail=sinfomation.get("邮箱");
					        		 String sphone=sinfomation.get("手机号");
					        		 String sintroduction=sinfomation.get("介绍");
					        		 String stheid=sinfomation.get("id"); */
					        		 String sfaculty=sinfomation.get("科目");

					        		    out.println("<li>");
										out.println("<div class=\"dropdown-messages-box\">");
										out.println("<div class=\"message-body  verticalparent\">");
										out.println(" <a href=\"student_home.jsp\" class=\"pull-left\">");/* <!-- 点击图像显示对方个人主页 --> */
										out.println("<img alt=\"image\" class=\"img-circle imagemargin\" src=\"http://placehold.it/40/30a5ff/fff\">");
										out.println("<strong class=\"myfont1\"> " +susername+"（"+sfaculty+"）</strong></a>");/*  <!-- 点击跳转至预约情况页面 --> */
										out.println("<small class=\"pull-right\">"+ayear+"-"+amonth+"-"+aday+"</small><!-- 预约日期 -->");
										out.println("</div>");
										out.println("</div>");
										out.println("</li>");
										out.println("<li class=\"divider\"></li>");
				        	     }
				        	     
						     }
						     
							%>
				   </ul>
				</li>
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
		
		<form role="search" action="searchprofessor" method="post"><!-- 写js脚本，在按下enter之后直接提交表单 -->
			<div class="form-group">
				<input type="text" class="form-control" placeholder="输入要预约的教授的名字" name="professorname">
			</div>
		</form>
	
		<ul class="nav menu">
			<li><a href="student_home.jsp"><em class="fa fa-dashboard">&nbsp;</em>  首   页</a></li>
			<li><a href="student_book.jsp"><em class="fa fa-calendar">&nbsp;</em> 预   约</a></li>
			<li class="active"><a href="student_chat.jsp"><em class="fa fa-comments">&nbsp;</em> 历史</a></li>
			<!-- <li><a href="student_profile.jsp"><em class="fa fa-user">&nbsp;</em> 个 人 主 页</a></li> -->
			<li class="parent "><a data-toggle="collapse" href="#sub-item-1">
				<em class="fa fa-navicon">&nbsp;</em> 更 多 功 能 <span data-toggle="collapse" href="#sub-item-1" class="icon pull-right"><em class="fa fa-plus"></em></span>
				</a>
				<ul class="children collapse" id="sub-item-1">
					<li><a class="" href="#" onclick="return future()"> 
						<span class="fa fa-arrow-right">&nbsp;</span> 功 能 1
					</a></li>
					<li><a class="" href="#" onclick="return future()">
						<span class="fa fa-arrow-right">&nbsp;</span> 功 能 2
					</a></li>
					<li><a class="" href="#" onclick="return future()">
						<span class="fa fa-arrow-right">&nbsp;</span> 功 能 3
					</a></li>
				</ul>
			</li>
			<li><a href="login.jsp"><em class="fa fa-power-off">&nbsp;</em> Logout</a></li>
		</ul>
	</div><!--/.sidebar-->
	<script>
	function future(){
		alert("尚未开放，敬请期待！");
		return false;
	}
	</script>
		
		
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
		
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">预约历史</h1>
			</div>
		</div><!--/.row-->
		
	  <%-- <div class="row">
	       <form action="showselectedauthor" method="post">
               <div class="input-group  mymargin input_wrap"> 
                 <input name="selectedauthor" type="text" class="form-control input-md " 
                 placeholder="请输入教授的名字或选择院系查询教授用户" />
				         <!-- <datalist id="authorlist"  >
						        <option><font class="datalistfont">cnm</font></option>
						    </datalist> -->
					<ul class="select_list">
                        <li>问题1</li>
                        <li>问题2</li>
                          <li>问题3</li>
                      <li>问题4</li>
                       <li>问题5</li>
                    </ul>
                  <span class="input-group-btn">
				     <input type="submit" class="btn btn-primary btn-md" value="Search">
				  </span>
				</div>
		  </form>
		</div> --%>
				
		<%-- <div class="panel panel-default articles">
			<div class="panel-heading">
			     相关用户
			   <span class="pull-right clickable panel-toggle panel-button-tab-left"><em class="fa fa-toggle-up"></em></span>
			</div>
			<div class="panel-body articles-container">
					<div class="article border-bottom">
						<div class="col-xs-12">
							 <div class="row">
								<div class="col-xs-2 col-md-2 date">
									 <div class="large"><img src="http://placehold.it/50/30a5ff/fff" class="img-responsive img-circle" alt="loading"></div>
								</div>
								<div class="col-xs-10 col-md-10">
<!-- 添加一个跳转到更新的action--><h4><a href="#">WHZ</a></h4><!-- 点击之后跳转到与该用户的chat中 -->
									 <p>简介：哈尔滨工业大学<strong>计算机科学学院教授</strong></p>
									</div>
								</div>
							</div>
							<div class="clear"></div>
						</div><!--End .article-->
					
					</div>
				</div><!--End .articles-->
				
		 --%>
		
		<div class="panel panel-default ">
					<div class="panel-heading">
						预 约 历 史
						<!-- <ul class="pull-right panel-settings panel-button-tab-right">
							<li class="dropdown"><a class="pull-right dropdown-toggle" data-toggle="dropdown" href="#">
								<em class="fa fa-cogs"></em>
							</a>
								<ul class="dropdown-menu dropdown-menu-right">
									<li>
										<ul class="dropdown-settings">
											<li><a href="#">
												<em class="fa fa-cog"></em> Settings 1
											</a></li>
											<li class="divider"></li>
											<li><a href="#">
												<em class="fa fa-cog"></em> Settings 2
											</a></li>
											<li class="divider"></li>
											<li><a href="#">
												<em class="fa fa-cog"></em> Settings 3
											</a></li>
										</ul>
									</li>
								</ul>
							</li>
						</ul> -->
						<span class="pull-right clickable panel-toggle panel-button-tab-left"><em class="fa fa-toggle-up"></em></span></div>
					<div class="panel-body timeline-container myscroll">
						<ul class="timeline">
						   <% 
						        
						       for(i=allhistory.size()-1;i>=0;i--){
						    	     history ahistory=allhistory.get(i);
					        		 String ayear=ahistory.year;
					        		 String amonth=ahistory.month;
					        		 String aday=ahistory.day;
					        		 String atime=ahistory.time;
					        	     String ateacherid=ahistory.teacherid;
					        	     int agrade=ahistory.grade;
					        	     String ainstruction=ahistory.instruction;
					        	     if(agrade==0){
					        	    	 ainstruction="尚未评价此次预约";
					        	     }
					        	     
					        	     
					        	     Map<String,String> sinfomation=mysql.showteacher(ateacherid);
					        	     String susername=sinfomation.get("用户名");
					        		 String semail=sinfomation.get("邮箱");
					        		 String sphone=sinfomation.get("手机号");
					        		 //String sintroduction=sinfomation.get("介绍"); 
					        		 String sfaculty=sinfomation.get("科目");
						    	     
					        		 out.println("<li>");/* "+susername+" */
					        		 if(agrade==0){//没评价过
					        			 out.println("<div class=\"timeline-badge\"><a href=\"javascript:void(0)\" onclick=\"return gotoevaluate(this)\" userId=\""+ateacherid+"\" username=\""+susername+"\" date=\""+ayear+"-"+amonth+"-"+aday+"\" time=\""+atime+"\"><i class=\"glyphicon glyphicon-pushpin\"></i></a></div>");
					        		 }else{
					        			 out.println("<div class=\"timeline-badge\"><i class=\"glyphicon glyphicon-pushpin\"></i></div>");
					        		 }
					        		 
					        		 out.println("<div class=\"timeline-panel\">");
					        		 out.println("<div class=\"timeline-heading\">");
					        		 out.println("<h4 class=\"timeline-title\"><b>"+ayear+"-"+amonth+"-"+aday+"</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+atime+"</h4>");
					        		 out.println("</div>");
					        		 out.println("<div class=\"timeline-body\">");
					        		 out.println("<p>约见了<b>"+susername+"</b>教授("+sfaculty+" 联系方式:"+sphone+"/"+semail+")</p>");
					        		 if(agrade==0){//没评价过
					        			 out.println("<p class=\"myfont1 mycolor1\">"+ainstruction+"</p>");
					        		 }else{
					        			 out.println("<p class=\"myfont1\">"+ainstruction+"<small class=\"myfont1 mycolor1 pull-right\">"+agrade+"</small></p>");
					        		 }
					        		
					        		 out.println("</div>");
					        		 out.println("</div>");
					        
						       }
						   %>
						   
						</ul>
					</div>
				</div>
				
				<div class="panel panel-default" id="evaluationpanel">
					<div class="panel-heading">
						预 约 评 价
						<!-- <ul class="pull-right panel-settings panel-button-tab-right">
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
						</ul> -->
						<span class="pull-right clickable panel-toggle panel-button-tab-left"><em class="fa fa-toggle-up"></em></span></div>
					<div class="panel-body">
					   <form class="form-horizontal" action="addevaluate" method="post" onsubmit="return check(this)">
							<fieldset>
						<div class="form-group">
									<label class="col-md-3 control-label" for="date">Date</label>
									<div class="col-md-9">
										<input id="date" name="date" type="text" placeholder="选定的日期" class="form-control" readonly="readonly">
									</div>
								</div>
								
						<!-- Selected Time-->
								<div class="form-group">
									<label class="col-md-3 control-label" for="time">Time Internal</label>
									<div class="col-md-9">
										<input id="time" name="time" type="text" placeholder="选定的时段" class="form-control" readonly="readonly">
									</div>
								</div>
										
						 <!-- Selected Professor-->
					    <div class="form-group">
							<label class="col-md-3 control-label" for="name">Selected Professor</label>
							<div class="col-md-9">
							<input id="selectedprofessor" name="selectedprofessor" type="text" placeholder="选定的教授" class="form-control" readonly="readonly">
							</div>
					   </div>
								
						  
						  <div class="form-group">
                                 <label class="col-md-3 control-label myfont2">评分:</label>
								 <label class="col-md-1 mymargintop2"><!-- 放在标签里可以直接点击标签选中单选按钮 -->
									<input type="radio" name="grade" id="optionsRadios1" value="1" checked>  <b>1</b>
						    	 </label>
						    	 <label class="col-md-1 mymargintop2">
									<input type="radio" name="grade" id="optionsRadios2" value="2" >  <b>2</b>
								</label>
								<label class="col-md-1 mymargintop2">
									<input type="radio" name="grade" id="optionsRadios3" value="3" >  <b>3</b>
								</label>
								<label class="col-md-1 mymargintop2">
									<input type="radio" name="grade" id="optionsRadios4" value="4" >  <b>4</b>
								</label>
								<label class="col-md-1 mymargintop2">
									<input type="radio" name="grade" id="optionsRadios5" value="5" >  <b>5</b>
								</label>
	
							</div>
						
						<input id="studentid" name="studentid" type="hidden" value=<%=id%>>
						<input id="teacherid" name="teacherid" type="hidden" ><!-- 动态设置teacherId -->
					 <div class="panel-footer">
						<div class="input-group">
							<input id="btn-input" name="instruction" type="text" class="form-control input-md" placeholder="请在这里记录您对这次预约的感受以及对老师的评价" /><span class="input-group-btn">
								<button class="btn btn-primary btn-md" id="btn-chat">提交评价</button>
						</span></div>
					  </div>
							</fieldset>
						</form>
					</div>
					
				</div>
				<script>
						   function gotoevaluate(obj){
							  /*  chosenTeacherId */
							  /*  var thisObj=obj;  
							    var userId=thsiObj.attr("userId");  
							    alert(userId);   */
							    
							   var userId= obj.attributes["userId"].nodeValue; //自定义属性采用此方式获得
							   var username= obj.attributes["username"].nodeValue;
							   var date= obj.attributes["date"].nodeValue;
							   var time= obj.attributes["time"].nodeValue;
							   
							   text1 = document.getElementById("date");
					           text1.value=date;
					        	  
					           text2 = document.getElementById("selectedprofessor");
					           text2.value=username;
					           
					           text3 = document.getElementById("time");
					           text3.value=time;
					        	  
					           text4 = document.getElementById("teacherid");
					           text4.value=userId;
							   
							   alert(userId);
							   alert(username);
							   alert(date);
							   alert(time);
							
								   document.getElementById("evaluationpanel").scrollIntoView();/* 用于网站内跳转 */
								
							  
							   return false;
						   }
						   function check(myform){
							   if(myform.date.value.length==0){
						    		 alert("请选择一个预约历史去评价");
						    		 return false;
						    	 }else{
						    		 return true;
						    	 }
						   }
						   </script>
			<div class="col-sm-12">
				<p class="back-link">OnlineBookingSystem by 王春阳</p>
			</div>
			
	</div>	<!--/.main-->
	
	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/chart.min.js"></script>
	<script src="js/chart-data.js"></script>
	<script src="js/easypiechart.js"></script>
	<script src="js/easypiechart-data.js"></script>
	<script src="js/bootstrap-datepicker.js"></script>
	<script src="js/custom.js"></script>
	<script src="js/datalist.js"></script>
    <script >
       $(document).ready(function(){
       $(".input_wrap").myDatalist({"bgcolor":"gray","widths":1,"heights":1}); 
       });
   </script>
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
    if("<%=evaluateresult%>"=="evaluated"){
	   alert("恭喜您！评价成功!");
   }else if("<%=evaluateresult%>"=="wrong"){
	   alert("很遗憾！由于未知原因评价失败");
   }  

	</script>
		
</body>
</html>