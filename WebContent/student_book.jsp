<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.user.mysqloperate.Mysqloperate"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Set"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.user.userhistory.history"%>
<%@ page import="java.util.List"%>
<%@ page import="com.user.successbooking.successbooking"%>
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
  
  Calendar today=Calendar.getInstance();
  int nowyear=today.get(Calendar.YEAR);
  int nowmonth=today.get(Calendar.MONTH)+1;
  int nowdate=today.get(Calendar.DATE);
%>
<% //查找老师后的信息
    ArrayList<Map<String,String>> professorList=(ArrayList<Map<String,String>>)session.getAttribute("professorList");
    ArrayList<String> testlist=new ArrayList<>();
    /* String myid="";
    if(professorList!=null){
    	int teachernum=professorList.size();
        testlist.add("1");
        testlist.add("2");
        myid=professorList.get(0).get("id");
    } */
%>
<% //得到当前所有没评价的历史列表
    List<history> historynograde= mysql.losegrade(id);
   int nogradenum=historynograde.size();
%>
<% //选择老师后的发布时间信息
    Map<String,String> chosenTimes=(Map<String,String>)session.getAttribute("chosenTimes");
    String chosenTeacherId=(String)session.getAttribute("chosenTeacherId");
    String chosenTeacherName="";
    String chosenphone="";
    String chosenemail="";
    if(chosenTeacherId!=null){
    	/* Set<String> ks=chosenTimes.keySet();
        for(String s:ks){
        	 System.out.println(chosenTimes.get(s));
        } */
    	Map<String,String> m=mysql.showteacher(chosenTeacherId);
    	chosenTeacherName=m.get("用户名");
    	chosenphone=m.get("手机号");
    	chosenemail=m.get("邮箱");
    }
    
%>
<% //预约结束之后的反馈信息
     String appointresult = (String) request.getAttribute("appointresult");    
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
.mycolor1{
  color:#ff0000;
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
 .specialdays{
   background-color:#ff0000;
 }
 .myscrollofuser{
  max-height:500px;
  overflow-y: auto;
}
.myscroll2{
  max-height:200px;
  overflow-y: auto;
}
.imagemargin{
   margin-left:10px;
   margin-right:10px;
}
.myscroll3{
  max-height:600px;
  overflow-x: auto;
}
.myimage{
   width: 40px;
   height:40px;
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
				        		 out.println("<img alt=\"image\" class=\"myimage pull-left img-circle\" src=\"images/"+ateacherid+".jpg\">");
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
										out.println("<img alt=\"image\" class=\"myimage img-circle imagemargin\" src=\"images/"+ateacherid+".jpg\">");
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
				</ul>
			</div>
		</div><!-- /.container-fluid -->
	</nav>
	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar"><!--侧面导航条  -->
		<div class="profile-sidebar"><!-- 头像 -->
			<div class="profile-userpic">
				<img src="images/<%=id%>.jpg" class="img-responsive" alt="loading">
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
			<li class="active"><a href="student_book.jsp"><em class="fa fa-calendar">&nbsp;</em> 预   约</a></li>
			<li><a href="student_chat.jsp"><em class="fa fa-comments">&nbsp;</em> 历   史</a></li><!-- 
			<li><a href="student_profile.jsp"><em class="fa fa-user">&nbsp;</em> 个 人 主 页</a></li> -->
			<li class="parent "><a data-toggle="collapse" href="#sub-item-1">
				<em class="fa fa-navicon">&nbsp;</em> 更 多 功 能 <span data-toggle="collapse" href="#sub-item-1" class="icon pull-right"><em class="fa fa-plus"></em></span>
				</a>
				<ul class="children collapse" id="sub-item-1" >
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
				<h1 class="page-header">预 约</h1>
			</div>
		</div><!--/.row-->
		
	   <div class="row">
	       <form action="searchprofessor" method="post">
               <div class="input-group  mymargin "> <!-- input_wrap -->
                 <input name="professorname" type="text" class="form-control input-md" 
                 placeholder="请输入教授的名字或选择指定学院查询教授的空闲时段"  list="authorlist"/>
				   <datalist id="authorlist">
						        <option  class="myfont1">计算机科学与技术学院</option>
						        <option  class="myfont1">航天学院</option>
						        <option  class="myfont1">材料科学与工程学院</option>
						        <option  class="myfont1">电气工程与自动化学院</option>
						        <option  class="myfont1">人文与社会科学学院</option>
						        <option  class="myfont1">市政环境科学学院</option>
						        <option  class="myfont1">交通科学与工程学院</option>
						        <option  class="myfont1">法学院</option>
						        <option  class="myfont1">机电工程学院</option>
						         <option  class="myfont1">能源科学与工程学院</option>
						        <option  class="myfont1">理学院</option>
						        <option  class="myfont1">管理学院</option>
						         <option  class="myfont1">土木工程学院</option>
						        <option  class="myfont1">建筑学院</option>
						        <option  class="myfont1">外国语学院</option>
						        <option  class="myfont1">基础学部</option>
						        
						    </datalist>
						    
				<!-- <ul class="select_list">
                        <li>计算机科学与技术学院</li>
                        <li>航天学院</li>
                        <li>材料科学与工程学院</li>
                        <li>电气工程与自动化学院</li>
                        <li>人文与社会科学学院</li>
                        <li>市政环境科学学院</li>
                        <li>交通科学与工程学院</li>
                        <li>法学院</li>
                        <li>机电工程学院</li>
                        <li>能源科学与工程学院</li>
                        <li>理学院</li>
                        <li>管理学院</li>
                        <li>土木工程学院</li>
                        <li>建筑学院</li>
                        <li>外国语学院</li>
                        <li>基础学部</li>
                    </ul> -->
				  <span class="input-group-btn">
				     <input type="submit" class="btn btn-primary btn-md" value="Search">
				     <!-- <input type="button" class="btn btn-primary btn-md" value="Search"> -->
				  </span>
				</div>
		  </form>
		</div>
		
		<div class="panel panel-default articles">
			<div class="panel-heading">
			     相关用户
			   <span class="pull-right clickable panel-toggle panel-button-tab-left"><em class="fa fa-toggle-up"></em></span>
			</div>
			<div class="panel-body articles-container myscrollofuser">
			     <%
			         if(professorList!=null){
			        	 for(Map<String,String> m:professorList){
			        		 String thisname=m.get("用户名");
			        		 String thisid=m.get("id");
			        		 String thisintro=m.get("介绍");
			        		 String thisfaculty=m.get("科目");
			        		 String thisphone=m.get("手机号");
			        		 String thisemail=m.get("邮箱");
			        		 String thegrade=mysql.average(thisid);
			        		 if(thegrade=="0"){
			        			 thegrade="5";
			        		 }
			        		/*  out.println("<h1>\"hahahha\"</h1>"); */
				             out.println("<div class=\"article border-bottom\">");
				        	 out.println("<div class=\"col-xs-12\">");
				        	 out.println("<div class=\"row\">");
				        	 out.println("<div class=\"col-xs-2 col-md-2 date\">");
				        	 out.println("<div class=\"large\"><img src=\"images/"+thisid+".jpg\" class=\"img-responsive img-circle myimage\" alt=\"loading\"></div>");
				        	 out.println("</div>");		
				        	 out.println("<div class=\"col-xs-10 col-md-10\">"); 
				        	 out.println("<h4><a href=\"queryOneProfessorAllTimes?teacherid="+thisid+"\">"+thisname+"</a><small class=\"pull-right\">电话:"+thisphone+"&nbsp&nbsp&nbsp&nbsp邮箱:"+thisemail+"</small></h4><!-- 点击之后跳转到与该用户的chat中 -->");
				        	
				        	 out.println("<p>简介：哈尔滨工业大学<strong>"+thisfaculty+"教授</strong>,"+thisintro+"<small class=\"myfont1 mycolor1 pull-right\">"+thegrade+"</small></p>");
				        	 out.println("</div>");
				        	 out.println("</div>");
				        	 out.println("</div>");
				        	 out.println("<div class=\"clear\">");
				        	 out.println("</div><!--End .article-->");
				        	 out.println("</div>");
			        	 }
			        }
			     %>
					
				</div>
			 </div><!--End .articles-->
				
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
		
	   <div class="panel panel-default" id="bookingpanel">
					<div class="panel-heading " >
						预 约 申 请
					    <span class="pull-right clickable panel-toggle panel-button-tab-left"><em class="fa fa-toggle-up"></em></span>
					</div>
					<div class="panel-body" id="bookingpanel">
						<form class="form-horizontal" action="appoint" method="post" onsubmit="return check(this)">
							<fieldset>
							    <!-- Appointment information-->
								<div class="form-group">
									<label class="col-md-3 control-label" for="timeinterval">Time Interval</label>
									   <div class="col-md-3">
										   <div class="checkbox">
											<label>
												<input type="checkbox" value="1" name="times">8：00-8：30
											</label>
										   </div>
										<div class="checkbox">
											<label>
												<input type="checkbox" value="2" name="times">8：30-9：00
											</label>
										</div>
										<div class="checkbox">
											<label>
												<input type="checkbox" value="3" name="times">9：00-9：30
											</label>
										</div>
										<div class="checkbox">
											<label>
												<input type="checkbox" value="4" name="times">9：30-10：00
											</label>
										</div>
										<div class="checkbox">
											<label>
												<input type="checkbox" value="5" name="times">10：00-10：30
											</label>
										</div>
										<div class="checkbox">
											<label>
<!-- onclick="return unchange()" -->	<input type="checkbox" value="6" name="times">10：30-11：00
											</label>
										</div>
										<div class="checkbox">
											<label>
												<input type="checkbox" value="7" name="times">11：00-11：30
											</label>
										</div>
									  </div>
									  <div class="col-md-3">
										   <div class="checkbox">
											<label>
												<input type="checkbox" value="8" name="times" >14：00-14：30
											</label>
										   </div>
										<div class="checkbox">
											<label>
												<input type="checkbox" value="9" name="times">14：30-15：00
											</label>
										</div>
										<div class="checkbox">
											<label>
												<input type="checkbox" value="10" name="times">15：00-15：30
											</label>
										</div>
										<div class="checkbox">
											<label>
												<input type="checkbox" value="11" name="times">15：30-16：00
											</label>
										</div>
										<div class="checkbox">
											<label>
												<input type="checkbox" value="12" name="times">16：00-16：30
											</label>
										</div>
										<div class="checkbox">
											<label>
												<input type="checkbox" value="13" name="times">16：30-17：00
											</label>
										</div>
										<div class="checkbox">
											<label>
												<input type="checkbox" value="14" name="times">17：00-17：30
											</label>
										</div>
									  </div>
									</div>
								
								<div class="form-group">
									<label class="col-md-3 control-label" for="date">Date</label>
									<div class="col-md-9">
										<input id="date" name="date" type="text" placeholder="选定的时段" class="form-control" readonly="readonly">
									</div>
								</div>
								
								 <!-- Selected Professor-->
								<div class="form-group">
									<label class="col-md-3 control-label" for="name">Selected Professor</label>
									<div class="col-md-9">
										<input id="selectedprofessor" name="selectedprofessor" type="text" placeholder="选定的教授" class="form-control" readonly="readonly">
									</div>
								</div>
								
							
								<!-- Email input-->
								<div class="form-group">
									<label class="col-md-3 control-label" for="email">Professor E-mail</label>
									<div class="col-md-9">
										<input id="email" name="email" type="text" placeholder="Your email" class="form-control" readonly="readonly"> 
									</div>
								</div>
								
								<!-- phone input-->
								<div class="form-group">
									<label class="col-md-3 control-label" for="phone">Professor Telephone</label>
									<div class="col-md-9">
										<input id="phone" name="phone" type="text" placeholder="Your phone" class="form-control" readonly="readonly">
									</div>
								</div>
								
								<!-- Message body -->
								<div class="form-group">
									<label class="col-md-3 control-label" for="message">Your message</label>
									<div class="col-md-9">
										<textarea class="form-control" id="message" name="successinstruction" placeholder="Please enter your message here..." rows="5" ></textarea>
									</div>
								</div>
								
								
								<input id="studentid" name="sid" type="hidden" value=<%=id%>>
								<input id="teacherid" name="tid" type="hidden" value=<%=chosenTeacherId%>>
								<input id="alltimes"  name="alltimes" type="hidden">
								
								<!-- Form actions -->
								<div class="form-group">
									<div class="col-md-12 widget-right">
										<button type="submit" class="btn btn-default btn-md pull-right">预约</button>
									</div>
								</div>
							</fieldset>
						</form>
						
				<script>
				function check(myform){
			    	 if(myform.date.value.length==0){
			    		 alert("请选择一个日期");
			    		 return false;
			    	 }else{
			    		 return getselectedtime();
			    	 }
			     }
				    function getselectedtime() {
				    	
					 checkboxes = document
							.getElementsByName("times");
					check_val =new Array();
					 for(i=0;i<checkboxes.length;i++){ 
						 if(checkboxes[i].checked)
						check_val.push(checkboxes[i].value);
					}
					text = document
							.getElementById("alltimes");
				 	text.value = check_val.join("|");
				 	
				 	if(text.value==""){
				 		alert("请选择至少一个时间段发布！");
				 		return false;
				 	}else{
				 		return true;
				 	}
				   }
				 </script>
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
	<script src="js/datalist.js"></script>
	<%-- <script >
       $(document).ready(function(){
       $(".input_wrap").myDatalist({"bgcolor":"gray","widths":1,"heights":1}); 
       });
   </script> --%>
	<script type="text/javascript">
	   /*  $('#calender').datepicker('option', 'minDate', '2017-12-10');  */
	    	 var speciald=new Array();
	    	 <% 
	    	      
	             if(chosenTimes!=null){
	            	 ArrayList<String> list = new ArrayList<>();
	        	     Set<String> ks=chosenTimes.keySet();
	                 for(String s:ks){
	            	    list.add(s);
	                 }
	                 for(int j=0;j<list.size();j++){
		     %>
		           speciald[<%=j%>]='<%=list.get(j)%>';//此处为添加的特殊日期，也可以都设置为yyyy-mm-dd
		          <%--  alert(speciald[<%=j%>]); --%>
	                 <%}
		         
	             }%>
		           
		           
		      $('#calendar').datepicker({
		    	startDate: '+1d',/* 只能预约明天以后的时间 */ 
			    beforeShowDay:function(date){
					 var d=date;
					 var curr_date=d.getDate();
					 var curr_month=d.getMonth()+1;
					 var curr_year=d.getFullYear();
					 var formatDate=curr_year+"-"+curr_month+"-"+curr_date;
					//特殊日期的匹配
					if($.inArray(formatDate,speciald)!=-1){
					return {classes:'specialdays'};
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
	        if(chosenTimes!=null){
     	      Set<String> ks=chosenTimes.keySet();
              for(String use:ks){
         %>  
              if(thedate=="<%=use%>"){
            	  abletimestring="<%=chosenTimes.get(use)%>";
            	  flag=true;
            	  /* break; */
              }
	     <%}
	     }%>
    	  //alert(thedate);
    	  if(flag){
    		  document.getElementById("bookingpanel").scrollIntoView();
    		  
    		  text1 = document.getElementById("date");
        	  text1.value=thedate;
        	  
        	  text2 = document.getElementById("selectedprofessor");
        	  text2.value="<%=chosenTeacherName%>";
        	  
        	  text3 = document.getElementById("phone");
        	  text3.value="<%= chosenphone%>";
        	  
        	  text4 = document.getElementById("email");
        	  text4.value="<%=chosenemail%>";
        	  
        	  checkboxes = document.getElementsByName("times");
        	  
    	      abletimes =new Array();
    	      
    	      /* for(i=0;i<checkboxes.length;i++){ 
    		     if(checkboxes[i].checked)
    		     check_val.push(checkboxes[i].value);
    	      } */
    	        
    	      abletimes=abletimestring.split("|");
    	      for(i=0;i<checkboxes.length;i++){ 
    	    	  checkboxes[i].checked=false;
    	    	  checkboxes[i].onclick=function(){/*动态添加事件  */
    	    		  alert("该时段老师未发布，不可勾选");
    	    	       return false;
    	    	  }
     	      } 
    	      for(i=0;i<abletimes.length;i++){
    	    	  checkboxes[abletimes[i]-1].checked=true;
    	    	  checkboxes[abletimes[i]-1].onclick=null;/*动态删除事件  */
    	      }
    	  }else{
    		  alert("请选择教授已发布了的日期(红色背景日期)去预约！");
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
};
   <%--  alert("<%=username%>"); --%>
   
   //jsp变量在js中当字符串一定要加括号啊
   if("<%=appointresult%>"=="finished"){
	   alert("恭喜您！预约成功!");
   }else if("<%=appointresult%>"=="used"){
	   alert("很遗憾！您已经与其他老师预约了该时间段，请注意查看");
   }  
   
	</script>
		
</body>
</html>