<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.user.mysqloperate.Mysqloperate"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.user.successbooking.successbooking"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="com.user.userhistory.history"%>
 <%--  <s:set var="id" value="id" scope="request" /> --%>
<%/* 登录之后的用户基本信息*/
/* String id=request.getParameter("id");
session.setAttribute("id", id); */
    String id=new String(session.getAttribute("id").toString().getBytes("ISO-8859-1"),"UTF-8");
    Mysqloperate mysql=new Mysqloperate();
    Map<String,String> infomation=mysql.showteacher(id);
	String username=infomation.get("用户名");
	String password=infomation.get("密码");
	String email=infomation.get("邮箱");
	String phone=infomation.get("手机号");
	String introduction=infomation.get("介绍");
	String theid=infomation.get("id"); 
	String faculty=infomation.get("科目");
    
	Calendar today=Calendar.getInstance();
	  int nowyear=today.get(Calendar.YEAR);
	  int nowmonth=today.get(Calendar.MONTH)+1;
	  int nowdate=today.get(Calendar.DATE);
	
%>
<%//获得所有预约
   List<successbooking> mysuccessbooking=mysql.teashowtime(id);
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
<%//获得所有历史
    List<history> allhistory= mysql.teashowhistory(id);
    int historynum=allhistory.size();
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
.mymargin{
    margin-left:20px;
    margin-right:20px;
    margin-bottom:20px;
}
.myalign1{
      align:center;
  }
  .myfont1{
  font-size:18px;
}
.imagemargin{
   margin-left:10px;
   margin-right:10px;
}
.mycolor1{
  color:#ff0000;
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
					
					<li class="dropdown"><!-- 预约提醒导航 -->
					  <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
						<em class="fa fa-bell"></em><span class="label label-info"><%=twodaynum %></span><!--未完成预约数量  -->
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
										out.println(" <a href=\"professor_home.jsp\" class=\"pull-left\" onclick=\"return scrolltodetails()\">");/* <!-- 点击图像显示对方个人主页 --> */
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
				<img src="images/3.jpg" class="img-responsive" alt="loading">
			</div>
			<div class="profile-usertitle">
				<div class="profile-usertitle-name"><%=username%></div>
				<div class="profile-usertitle-status"><span class="indicator label-success"></span>Online</div>
			</div>
			<div class="clear"></div>
		</div>
		<div class="divider"></div>
		
	<!-- 	<form role="search">写js脚本，在按下enter之后直接提交表单
			<div class="form-group">
				<input type="text" class="form-control" placeholder="输入要预约的教授的名字">
			</div>
		</form> -->
		<ul class="nav menu">
			<li><a href="professor_home.jsp"><em class="fa fa-dashboard">&nbsp;</em>  首   页</a></li>
			<li><a href="professor_book.jsp"><em class="fa fa-calendar">&nbsp;</em> 预   约</a></li>
			<li class="active"><a href="professor_chat.jsp"><em class="fa fa-comments">&nbsp;</em> 历  史</a></li>
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
		
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">消 息</h1>
			</div>
		</div><!--/.row-->
		
		
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
					        	     String astudentid=ahistory.studentid;
					        	     int agrade=ahistory.grade;
					        	     String ainstruction=ahistory.instruction;
					        	     if(agrade==0){
					        	    	 ainstruction="同学尚未评价此次预约";
					        	     }
					
					        	     Map<String,String> sinfomation=mysql.showstudent(astudentid);
					        	     String susername=sinfomation.get("用户名");
					        		 String semail=sinfomation.get("邮箱");
					        		 String sphone=sinfomation.get("手机号");
					        		 //String sintroduction=sinfomation.get("介绍"); 
					        		 String sfaculty=sinfomation.get("科目");
						    	     
					        		 out.println("<li>");/* "+susername+" */
					        		
					        		 out.println("<div class=\"timeline-badge\"><i class=\"glyphicon glyphicon-pushpin\"></i></div>");
					        		 
					        		 
					        		 out.println("<div class=\"timeline-panel\">");
					        		 out.println("<div class=\"timeline-heading\">");
					        		 out.println("<h4 class=\"timeline-title\"><b>"+ayear+"-"+amonth+"-"+aday+"</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+atime+"</h4>");
					        		 out.println("</div>");
					        		 out.println("<div class=\"timeline-body\">");
					        		 out.println("<p>约见了<b>"+susername+"</b>同学("+sfaculty+" 联系方式:"+sphone+"/"+semail+")</p>");
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
	</script>
		
</body>
</html>