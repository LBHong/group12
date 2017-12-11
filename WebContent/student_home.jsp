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
<%@ page import="com.user.userhistory.history"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar"%>

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
  

  
  /* SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-d");//设置日期格式
  Date today=new Date();
  System.out.println(df.format(today));// new Date()为获取当前系统时间 */
  //学生用户登陆之后，得到此时的时间，将过期的预约改成历史，并添加到历史中去等待评价，待评价的预约在右上角显示
  Calendar today=Calendar.getInstance();
  int nowyear=today.get(Calendar.YEAR);
  int nowmonth=today.get(Calendar.MONTH)+1;
  int nowdate=today.get(Calendar.DATE);
 /*System.out.println(nowyear+"-"+nowmonth+"-"+nowdate); */
%>
<%//对无效预约做出处理
   List<successbooking> successbookingbeforedelete=mysql.stushowtime(id);//未检测过期的日期
   for(successbooking asuccessbooking:successbookingbeforedelete){
		 String ayear=asuccessbooking.year;
		 String amonth=asuccessbooking.month;
		 String aday=asuccessbooking.day;
	     String ateacherid=asuccessbooking.teacherid;
	     
	     String atime=asuccessbooking.time;
	     String[] endtimes=atime.split("-");
	     String endtime=endtimes[1];//该预约的结束时间
	     
	     String endTime = ayear+"-"+amonth+"-"+aday+" "+endtime;  
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d HH:mm");  
         Date endDate = sdf.parse(endTime);//得到该截止日期的Date对象
         Calendar endCal = Calendar.getInstance();
         endCal.setTime(endDate);
         //比较时间
         if(today.after(endCal)){//过了期
        	 mysql.addhistory(ateacherid, ayear, amonth, aday, atime,id);//设为了历史
         }
   }
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
<% //得到当前所有没评价的历史列表
   List<history> historynograde= mysql.losegrade(id);
   int nogradenum=historynograde.size();
%>
<%
    List<history> allhistories= mysql.stushowhistory(id);
    int historynum=allhistories.size();
%>
<% //相关的附属信息
   int professornum=mysql.getprofessornum();
%>
<% //删除结束之后的反馈信息
     String deleteresult = (String) request.getAttribute("deleteresult");    
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
 .specialdays{
   background-color:#ff0000;
 }
.myfont1{
  font-size:18px;
}
.imagemargin{
   margin-left:10px;
   margin-right:10px;
}
.textmargin{
  margin-right:10px;
}
.verticalparent{
   align-items: center;    /*垂直居中  */
   display: flex;
}
.verticalparent2{
   position: relative;
}
.verticalchild2{
  position: absolute;
  margin: 0,auto;
}
.externalwidth{
   width:100%;
}
.seccolor{
	color:#ff0080;
}
.secfont{
     line-height:20px;
	font-size:16px;
}
.myimage{
   width: 40px;
   height:40px;
}
.thirdfont{
 font-size:14px;
}
.myscroll1{
  max-height:300px;
  overflow-y: auto;
}
.myscroll2{
  max-height:200px;
  overflow-y: auto;
}
.myalign1{
      align:center;
  }
 .specialdays2{
   background-color:#00ff00;
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
										out.println(" <a href=\"profile.html\" class=\"pull-left\" onclick=\"return scrolltodetails()\">");/* <!-- 点击图像显示对方个人主页 --> */
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
						  <script>
						   function scrolltodetails(){
							   document.getElementById("bookingpanel").scrollIntoView();/* 用于网站内跳转 */
							  return false;
						   }
						  </script>
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
		
		<form role="search" action="searchprofessor" method="post"><!-- 写js脚本，在按下enter之后直接提交表单 -->
			<div class="form-group">
				<input type="text" class="form-control" placeholder="输入要预约的教授的名字" name="professorname">
			</div>
		</form>
	
		<ul class="nav menu">
			<li class="active"><a href="student_home.jsp"><em class="fa fa-dashboard">&nbsp;</em>  首   页</a></li>
			<li><a href="student_book.jsp"><em class="fa fa-calendar">&nbsp;</em> 预   约</a></li>
			<li><a href="student_chat.jsp"><em class="fa fa-comments">&nbsp;</em> 历   史</a></li>
			<!-- <li><a href="student_profile.jsp"><em class="fa fa-user">&nbsp;</em> 个 人 主 页</a></li> -->
			<li class="parent "><a data-toggle="collapse" href="#sub-item-1">
				<em class="fa fa-navicon">&nbsp;</em> 更 多 功 能 <span data-toggle="collapse" href="#sub-item-1" class="icon pull-right"><em class="fa fa-plus"></em></span>
				</a>
				<ul class="children collapse" id="sub-item-1">
					<li><a class="" href="#" onclick="return future()">
						<span class="fa fa-arrow-right">&nbsp;</span> 尚未开放
					</a></li>
					<li><a class="" href="#" onclick="return future()">
						<span class="fa fa-arrow-right">&nbsp;</span> 尚未开放
					</a></li>
					<li><a class="" href="#" onclick="return future()">
						<span class="fa fa-arrow-right">&nbsp;</span> 尚未开放
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
							<div class="large"><%=professornum%></div>
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
							<div class="large"><%=historynum%></div>
							<div class="text-muted">Historys</div>
						</div>
					</div>
				</div>
				<div class="col-xs-6 col-md-3 col-lg-3 no-padding">
					<div class="panel panel-red panel-widget ">
						<div class="row no-padding"><em class="fa fa-xl fa-comments color-teal"></em>
							<div class="large"><%=nogradenum%></div>
							<div class="text-muted">Evaluation</div>
						</div>
					</div>
				</div>
			</div><!--/.row-->
		</div>
		
		<%-- <div class="row">  <!-- 展示自己最近的预约情况，包括预约失败的和预约成功的 -->
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
	  <!--/.row--> --%>
	  <div class="panel panel-container">
				<div class="panel panel-default">
					<div class="panel-heading">
					    <label class="myalign1">日 历</label>
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
						<div id="calendar"></div>
					</div>
				</div>
		</div>
		
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default" id="bookingpanel">
					<div class="panel-heading">
						待赴预约
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
					<div class="panel-body myscroll1">
						<ul class="todo-list"><!-- 所有未完成预约 -->
						
							<% 
						     i=0;
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
				             out.println("<input type=\"checkbox\" id=\"checkbox-"+i+"\"/>");/*注意这个id要是变量 */
				             
				             out.println("<a class=\"dropdown-toggle count-info\" data-toggle=\"dropdown\"href=\"\"><label for=\"checkbox-"+i+"\">与"+susername+"教授于"+ayear+"-"+amonth+"-"+aday+"约见，见面时间:"+atime+"</label></a>");
				             out.println("<ul class=\"dropdown-menu dropdown-messages\">");
				             out.println("<li>");
				             out.println("<div class=\"dropdown-messages-box\">");
				             out.println("<img alt=\"image\" class=\"img-circle myimage\"");
				             out.println("src=\"http://mpic.tiankong.com/4c6/3b6/4c63b6daa54b259e01791ab9a4b2e653/640.jpg@360h\">");
				             out.println("<div class=\"message-body\">");
				             out.println("<div><font class=\"secfont\"><strong>"+susername+"</strong></font><br/><font class=\"thirdfont\">"+sfaculty+"</font></div>");
				              out.println("<small class=\"text-muted seccolor\" >"+sphone+"/"+semail+"</small></div>");
				             out.println("</div>");
				             out.println("</li>");
				             out.println("<li class=\"divider\"></li>");
				             out.println("<li>");
				             out.println("<div class=\"dropdown-messages-box\">");
				             out.println("<img alt=\"image\" class=\"img-circle myimage\"");
				             out.println("src=\"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2484490458,2674950783&fm=27&gp=0.jpg\">"); 
				             out.println("<div class=\"message-body\"><small class=\"pull-right\">"+ayear+"-"+amonth+"-"+aday+" "+atime+"</small>");
				             out.println("<div><font class=\"secfont\"><strong>预约信息:</strong><br/>"+ainstruction+"</font></div>");
	
				             out.println("</div>");
				             out.println("</li>");
				             out.println("</ul>");
				             
				             out.println("</div>");
				             out.println("<div class=\"pull-right action-button\"><a href=\"studentdelete?studentid="+astudentid+"&year="+ayear+"&month="+amonth+"&day="+aday+"&time="+atime+"\" class=\"trash\">");
				             /* out.println("");
				             out.println("");
				             out.print("");
				             out.print("");
				             out.print("");
							 out.print("");
							 out.print(""); */
				             out.println("<em class=\"fa fa-trash\"></em>");
				             out.println("</a></div>");
				             out.println("</li>");
						     }
				             %>
							
						</ul>
					</div>
					<div class="panel-footer">
						<div class="input-group">
							<input id="btn-input" type="text" class="form-control input-md" placeholder="Choose finished appointments"  readonly="readonly"/><span class="input-group-btn">
								<button class="btn btn-primary btn-md" id="btn-todo">Finish</button>
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
	<script type="text/javascript">
	   /*  $('#calender').datepicker('option', 'minDate', '2017-12-10');  */
	    	 var speciald=new Array();
	    	 <% 
	    	      
	             if(mysuccessbooking!=null){
	            	 /* ArrayList<String> list = new ArrayList<>();
	        	     Set<String> ks=chosenTimes.keySet();
	                 for(String s:ks){
	            	    list.add(s);
	                 } */
	                 int j=0;
	                 for(successbooking abook:mysuccessbooking){
	                	 j++;
		     %>
		           speciald[<%=j%>]='<%=abook.year+"-"+abook.month+"-"+abook.day%>';//此处为添加的特殊日期，也可以都设置为yyyy-mm-dd
		           alert(speciald[<%=j%>]);
	                 <%}
		         
	             }%>
		           
	            var speciald2=new Array();
		    	 <% 
		    	      
		             if(allhistories!=null){
		                 int j=0;
		                 for(history ahistory:allhistories){
		                	 j++;
			     %>
			           speciald2[<%=j%>]='<%=ahistory.year+"-"+ahistory.month+"-"+ahistory.day%>';//此处为添加的特殊日期，也可以都设置为yyyy-mm-dd
			           alert(speciald2[<%=j%>]);
		                 <%}
			         
		             }%>
		           
		      $('#calendar').datepicker({
		    	/* startDate: '+1d',/* 只能预约明天以后的时间 */ 
			    beforeShowDay:function(date){
					 var d=date;
					 var curr_date=d.getDate();
					 var curr_month=d.getMonth()+1;
					 var curr_year=d.getFullYear();
					 var formatDate=curr_year+"-"+curr_month+"-"+curr_date;
					//特殊日期的匹配
					if($.inArray(formatDate,speciald)!=-1){
					return {classes:'specialdays'};
		           }else if($.inArray(formatDate,speciald2)!=-1){
		        	   return {classes:'specialdays2'};
		           }
					return;
			 }    
		   });
	      
		      $('#calendar').datepicker({
				  onSelect: gotoDate
					}).on('changeDate',gotoDate);
		      function gotoDate(ev){
			      var flag=false;
		    	  var thedate=ev.date.getFullYear().toString()+"-"+(ev.date.getMonth()+1).toString()+"-"+ev.date.getDate().toString(); 
		    	  var abletimestring;
		    	  <% /* 先判断点击了正确的有发布的日子 */
			        if(mysuccessbooking!=null){
			        	for(successbooking abook:mysuccessbooking){
		         %>  
		              if(thedate=="<%=abook.year+"-"+abook.month+"-"+abook.day%>"){
		            	  flag=true;
		            	  /* break; */
		              }
			     <%}
			     }%>
			     var flag2=false;
			     <% /* 先判断点击了正确的有发布的日子 */
			        if(allhistories!=null){
			        	for(history ahistory:allhistories){
		         %>  
		              if(thedate=="<%=ahistory.year+"-"+ahistory.month+"-"+ahistory.day%>"){
		            	  flag2=true;
		            	  /* break; */
		              }
			     <%}
			     }%>
		    	  //alert(thedate);
		    	  if(flag){
		    		  document.getElementById("bookingpanel").scrollIntoView();
		    	  }else if(flag2){
		    		  window.location.href="student_chat.jsp";
		    	  }
		    		  
		      }
    	 
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
	/* document.getElementById("btn-input").focus(); */
	
    };
    alert("<%=id%>");
    
    if("<%=deleteresult%>"=="deleted"){
 	   alert("恭喜您！预约删除成功!");
    }else if("<%=deleteresult%>"=="wrong"){
 	   alert("很遗憾！由于未知错误，删除预约失败");
    }else if("<%=deleteresult%>"=="overtime"){
 	   alert("为了您和老师的方便，禁止删除今天的预约！");
    }  
	</script>
		
</body>
</html>