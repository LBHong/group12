<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.user.mysqloperate.Mysqloperate"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Set"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
 .specialdays{
   background-color:#ff0000;
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
		
		<form role="search" action="searchprofessor" method="post"><!-- 写js脚本，在按下enter之后直接提交表单 -->
			<div class="form-group">
				<input type="text" class="form-control" placeholder="输入要预约的教授的名字" name="professorname">
			</div>
		</form>

		<ul class="nav menu">
			<li><a href="student_home.jsp"><em class="fa fa-dashboard">&nbsp;</em>  首   页</a></li>
			<li class="active"><a href="student_book.jsp"><em class="fa fa-calendar">&nbsp;</em> 预   约</a></li>
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
		
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">预 约</h1>
			</div>
		</div><!--/.row-->
		
	   <div class="row">
	       <form action="searchprofessor" method="post">
               <div class="input-group  mymargin"> 
                 <input name="professorname" type="text" class="form-control input-md" 
                 placeholder="请输入教授的名字查询教授的空闲时段"  list="authorlist"/>
				   <!-- <datalist id="authorlist" style="color:#ff0000" >
						        <option>cnm</option>
						    </datalist> -->
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
			<div class="panel-body articles-container">
			     <%
			         if(professorList!=null){
			        	 for(Map<String,String> m:professorList){
			        		 String thisname=m.get("用户名");
			        		 String thisid=m.get("id");
			        		 String thisintro=m.get("介绍");
			        		 String thisfaculty=m.get("科目");
			        		 String thisphone=m.get("手机号");
			        		 String thisemail=m.get("邮箱");
			        		 
			        		/*  out.println("<h1>\"hahahha\"</h1>"); */
				             out.println("<div class=\"article border-bottom\">");
				        	 out.println("<div class=\"col-xs-12\">");
				        	 out.println("<div class=\"row\">");
				        	 out.println("<div class=\"col-xs-2 col-md-2 date\">");
				        	 out.println("<div class=\"large\"><img src=\"http://placehold.it/50/30a5ff/fff\" class=\"img-responsive img-circle\" alt=\"loading\"></div>");
				        	 out.println("</div>");		
				        	 out.println("<div class=\"col-xs-10 col-md-10\">"); 
				        	 out.println("<h4><a href=\"queryOneProfessorAllTimes?teacherid="+thisid+"\">"+thisname+"</a><small class=\"pull-right\">电话:"+thisphone+"&nbsp&nbsp&nbsp&nbsp邮箱:"+thisemail+"</small></h4><!-- 点击之后跳转到与该用户的chat中 -->");
				        	
				        	 out.println("<p>简介：哈尔滨工业大学<strong>"+thisfaculty+"教授</strong>,"+thisintro+"</p>");
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
						<div id="calendar"></div>
					</div>
				</div>
		</div>
		
	   <div class="panel panel-default">
					<div class="panel-heading " >
						预 约 申 请
					    <span class="pull-right clickable panel-toggle panel-button-tab-left"><em class="fa fa-toggle-up"></em></span>
					</div>
					<div class="panel-body">
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
				 		alert(text.value);
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
		           alert(speciald[<%=j%>]);
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
            	  alert("right");
            	  abletimestring="<%=chosenTimes.get(use)%>";
            	  flag=true;
            	  /* break; */
              }
	     <%}
	     }%>
    	  //alert(thedate);
    	  if(flag){
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
   alert("<%=chosenTeacherName%>");
	</script>
		
</body>
</html>