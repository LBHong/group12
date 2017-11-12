<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/datepicker3.css" rel="stylesheet">
	<link href="css/styles.css" rel="stylesheet">
	<link href="css/font-awesome.min.css" rel="stylesheet">
<title>注册成功</title>
<style>
body {                                
        background: url("images/bg1.jpg") no-repeat;
        background-size:cover;
} 
.headingcolor{
   background-color:#F0FFF0;/*  */
}
.mytextalign{
   text-align:center;
}
.myfont{
   font:30px bold;
}
.myfont2{
    font-size:25px;
    color:#FF3030;
}
.mycolor{
  color:#D1EEEE;
}
.panel h
</style>
</head>
<body>
       <div class="row">
          <div class="col-md-4 col-md-offset-4">
				<div class="panel">
					<div class="panel-heading headingcolor mytextalign myfont">注册成功
					 <a href="login.jsp" class="pull-right"><em class="fa fa-lg fa-close mycolor"></em></a>
				     </div> 
					<div class="panel-body">
					     	<s:set var="identity" value="identity" scope="request" />
					     	<s:set var="username" value="username" scope="request" />
					     	<s:set var="id" value="id" scope="request" />
					         <%
									String identity = (String) request.getAttribute("identity");
					                String username = (String) request.getAttribute("username");
					                String id = (String) request.getAttribute("id");
					                
					                String job;
					                if(identity.equals("professor")){
					                	job="教授";
					                }else{
					                	job="同学";
					                }
					         %>
						<p>亲爱的<%=username%><%=job%>,欢迎您注册使用本预约系统！</p>
						<p>您的账号：<font class="myfont2"><%=id%></font>&nbsp&nbsp&nbsp请牢记</p>
						
					</div>
				</div>
			</div>
	   </div>
</body>
</html>