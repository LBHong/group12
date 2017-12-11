<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.user.mysqloperate.Mysqloperate"%>
<%@ page import="java.util.Map"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.user.releasebooking.releasebooking"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="com.user.successbooking.successbooking"%>
<% 
  /* String s = (String) request.getAttribute("test");  */
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
  
  /* List<releasebooking> myreleasebooking=mysql.Queryateacher(id);/* 所有老师发布了的没有被预约走的时间段的日期 */ 
  Map<String,String> AllTimes=mysql.QueryAllTimesOfAteacher(id);/* 所有老师发布了的没有被预约走的时间段的日期与当天具体时段 */
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
.imagemargin{
   margin-left:10px;
   margin-right:10px;
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
		
		<!-- <form role="search">写js脚本，在按下enter之后直接提交表单
			<div class="form-group">
				<input type="text" class="form-control" placeholder="输入要预约的教授的名字">
			</div>
		</form> -->
		<ul class="nav menu">
			<li><a href="professor_home.jsp"><em class="fa fa-dashboard">&nbsp;</em>  首   页</a></li>
			<li class="active"><a href="professor_book.jsp"><em class="fa fa-calendar">&nbsp;</em> 预   约</a></li>
			<li><a href="professor_chat.jsp"><em class="fa fa-comments">&nbsp;</em> 历   史</a></li>
			<li class="parent"><a data-toggle="collapse" href="#sub-item-1">
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
				  <a href="professor_home.jsp">
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
		
	   <div class="panel panel-default">
					<div class="panel-heading " >
						空 闲 时 段 发 布 及 取 消
					    <span class="pull-right clickable panel-toggle panel-button-tab-left"><em class="fa fa-toggle-up"></em></span>
					</div>
					
					 <div class="panel-body tabs">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#tab1" data-toggle="tab">发 布</a></li>
							<li><a href="#tab2" data-toggle="tab">取 消</a></li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane fade in active" id="tab1">
								<form class="form-horizontal" action="release" method="post" onsubmit="return getselectedtime()">
							<fieldset>
							    <!-- Appointment information-->
								<!-- <div class="form-group">
									<label class="col-md-3 control-label" for="name">Time Interval</label>
									<div class="col-md-9">
										<input id="timeinterval" name="timeinterval" type="text" placeholder="要发布的时段" class="form-control">
									</div>
								</div> -->
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
												<input type="checkbox" value="6" name="times">10：30-11：00
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
												<input type="checkbox" value="8" name="times">14：00-14：30
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
									<input type="hidden" value="" name="alltimes" id="alltimes">
									<input type="hidden" value=<%=id%> name="id">
								 <!-- Appointment information-->
								<div class="form-group">
									<label class="col-md-3 control-label" for="name">Date</label>
									<div class="col-md-9">
										<input id="date" name="date" type="text" placeholder="日期" class="form-control" readonly="readonly"/>
									</div>
								</div>
								
								<!-- Form actions -->
								<div class="form-group" id="bookingpanel">
									<div class="col-md-12 widget-right">
										<button type="submit" class="btn btn-default btn-md pull-right">发布</button>
									</div>
								</div>
							</fieldset>
						</form>
							</div>
							<div class="tab-pane fade" id="tab2">
								<form class="form-horizontal" action="teacherdelete" method="post" onsubmit="return getdeletetime()">
							<fieldset>
							    <!-- Appointment information-->
								<!-- <div class="form-group">
									<label class="col-md-3 control-label" for="name">Time Interval</label>
									<div class="col-md-9">
										<input id="timeinterval" name="timeinterval" type="text" placeholder="要发布的时段" class="form-control">
									</div>
								</div> -->
								<div class="form-group">
									<label class="col-md-3 control-label" for="timeinterval">Time Interval</label>
									   <div class="col-md-3">
										   <div class="checkbox">
											<label>
												<input type="checkbox" value="1" name="times2">8：00-8：30
											</label>
										   </div>
										<div class="checkbox">
											<label>
												<input type="checkbox" value="2" name="times2">8：30-9：00
											</label>
										</div>
										<div class="checkbox">
											<label>
												<input type="checkbox" value="3" name="times2">9：00-9：30
											</label>
										</div>
										<div class="checkbox">
											<label>
												<input type="checkbox" value="4" name="times2">9：30-10：00
											</label>
										</div>
										<div class="checkbox">
											<label>
												<input type="checkbox" value="5" name="times2">10：00-10：30
											</label>
										</div>
										<div class="checkbox">
											<label>
												<input type="checkbox" value="6" name="times2">10：30-11：00
											</label>
										</div>
										<div class="checkbox">
											<label>
												<input type="checkbox" value="7" name="times2">11：00-11：30
											</label>
										</div>
									  </div>
									  <div class="col-md-3">
										   <div class="checkbox">
											<label>
												<input type="checkbox" value="8" name="times2">14：00-14：30
											</label>
										   </div>
										<div class="checkbox">
											<label>
												<input type="checkbox" value="9" name="times2">14：30-15：00
											</label>
										</div>
										<div class="checkbox">
											<label>
												<input type="checkbox" value="10" name="times2">15：00-15：30
											</label>
										</div>
										<div class="checkbox">
											<label>
												<input type="checkbox" value="11" name="times2">15：30-16：00
											</label>
										</div>
										<div class="checkbox">
											<label>
												<input type="checkbox" value="12" name="times2">16：00-16：30
											</label>
										</div>
										<div class="checkbox">
											<label>
												<input type="checkbox" value="13" name="times2">16：30-17：00
											</label>
										</div>
										<div class="checkbox">
											<label>
												<input type="checkbox" value="14" name="times2">17：00-17：30
											</label>
										</div>
									  </div>
									</div>
									<input type="hidden" value="" name="alltimes2" id="alltimes2">
									<input type="hidden" value=<%=id%> name="id2">
								 <!-- Appointment information-->
								<div class="form-group">
									<label class="col-md-3 control-label" for="name">Date</label>
									<div class="col-md-9">
										<input id="date2" name="date2" type="text" placeholder="日期" class="form-control" readonly="readonly"/>
									</div>
								</div>
								
								<!-- Form actions -->
								<div class="form-group">
									<div class="col-md-12 widget-right">
										<button type="submit" class="btn btn-default btn-md pull-right">取消</button>
									</div>
								</div>
							</fieldset>
						</form>
							</div>
						</div>
					</div>
				</div>
				
				<script>
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
				 	date = document.getElementById("date");
				 	if(text.value==""){
				 		alert("请选择至少一个时间段发布！");
				 		return false;
				 	}else if(date.value==""){
				 		alert("请选择要发布空闲时间的日期！");
				 		return false;
				 	}else{
				 		alert(text.value);
				 		return true;
				 	}
				   }
				    function getdeletetime(){
				   	 checkboxes = document.getElementsByName("times2");
				      check_val =new Array();
				      for(i=0;i<checkboxes.length;i++){ 
					  if(checkboxes[i].checked)
					  check_val.push(checkboxes[i].value);
				        }
				      text = document.getElementById("alltimes2");
			 	      text.value = check_val.join("|");
			 	      date = document.getElementById("date2");
			 	      if(text.value==""){
			 		      alert("请选择至少一个时间段取消！");
			 		       return false;
			 	     }else if(date.value==""){
					 		alert("请选择要发布空闲时间的日期！");
					 		return false;
					 	}else{
			 		      alert(text.value);
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
	<script>
	var speciald=new Array();
	 <% 
	    if(AllTimes!=null){
       	  ArrayList<String> list = new ArrayList<>();
   	     Set<String> ks=AllTimes.keySet();
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
    	 startDate: '+1d',/* 只能发布明天以后的时间 */
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

	  /*  $('#calendar').datepicker({dateFormat: "yy-mm-dd"}); */
	   $('#calendar').datepicker({
		  onSelect: gotoDate
			}).on('changeDate',gotoDate);
	   
      function gotoDate(ev){
      /* window.location.href="login.jsp"; 
     /*  window.location.href = "XXXX.html" + "?Date=" + ev.date.getFullYear().toString() + "-"+ (ev.date.getMonth()+1).toString()+ "-"+ ev.date.getDate().toString();*/
      /* var thedate=ev.date.getFullYear().toString()+"-"+(ev.date.getMonth()+1).toString()+"-"+ev.date.getDate().toString(); 
    	  text = document.getElementById("date");
    	  text.value=thedate; */
    	  var flag=false;
    	  var thedate=ev.date.getFullYear().toString()+"-"+(ev.date.getMonth()+1).toString()+"-"+ev.date.getDate().toString(); 
    	  text = document.getElementById("date");
    	  text.value=thedate;
    	  text2 = document.getElementById("date2");
    	  text2.value=thedate;
    	  /* 将当天点选这天老师的未被预约的时间展示出来，需要提前准备好所有的具体时间信息*/
    	  var abletimestring;
    	  <% /* 先判断点击了正确的有发布的日子 */
	        if(AllTimes!=null){
	        	Set<String> ks=AllTimes.keySet();
	        	for(String use:ks){
         %>  
              if(thedate=="<%=use%>"){
            	  alert("选了已经发布过预约信息的日期");
            	  abletimestring="<%=AllTimes.get(use)%>";
            	  flag=true;
            	  /* break; */
              }
	     <%}
	     }%>
    	  //alert(thedate);
    	   
    	  if(flag){ //选择了有空闲预约时间的日期
    		  document.getElementById("bookingpanel").scrollIntoView();
    		  checkboxes = document.getElementsByName("times");
    		  checkboxes2 = document.getElementsByName("times2");
    	      abletimes =new Array();
    	      /* for(i=0;i<checkboxes.length;i++){ 
    		     if(checkboxes[i].checked)
    		     check_val.push(checkboxes[i].value);
    	      } */
    	        
    	      abletimes=abletimestring.split("|");
    	      for(i=0;i<checkboxes.length;i++){ 
    	    	  checkboxes[i].checked=false;
    	    	  checkboxes[i].onclick=null;/*动态删除事件  */
    	    	  checkboxes2[i].checked=false;//所有的设置为没选中
    	    	  checkboxes2[i].onclick=function(){//所有设置为不可选中
    	    		  alert("该时段您尚未发布，无需勾选");
	    	          return false;
    	    	  }/*动态删除事件  */
     	      } 
    	      for(i=0;i<abletimes.length;i++){
    	    	  checkboxes[abletimes[i]-1].checked=true;
    	    	  checkboxes[abletimes[i]-1].onclick=function(){/* 动态添加事件   */
    	    		  alert("该时段您已经发布过了，无法勾选");
	    	          return false;
    	    	  }
    	    	  checkboxes2[abletimes[i]-1].checked=true;
    	    	  checkboxes2[abletimes[i]-1].onclick=null;
    	      }
    	  }else{
    		  checkboxes = document.getElementsByName("times");
    		  checkboxes2 = document.getElementsByName("times2");
    		  alert("该天您没有发布过空闲时段"); 
    		  for(i=0;i<checkboxes.length;i++){ //没添加过的时段随意填写
    	    	  checkboxes[i].checked=false;
    	    	  checkboxes[i].onclick=null;
    	    	  checkboxes2[i].checked=false;
    	    	  checkboxes2[i].onclick=function(){/* 动态添加事件   */
    	    		  alert("该时段您尚未发布过");
	    	          return false;
    	    	  };
    		  }
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
    alert("<%=theid%>");
	</script>
	
		
</body>
</html>