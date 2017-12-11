<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%//登陆结果
 String loginresult=(String) request.getAttribute("loginresult"); 
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>OnlineBookingSystem</title>
	<link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/datepicker3.css" rel="stylesheet">
	<link href="css/styles.css" rel="stylesheet">
	
<style>
body {                                
        background: url("images/bg1.jpg") no-repeat;
        background-size:cover;
}            
.bgtransparent{ 
  opacity:0.8;
}
.bgnottransparent{
   opacity:1;
}
.myfloatr{
	float:right;
}
.myfloatl{
	float:left;
}
.mymarginr{
    margin-right:10px;
}
</style>
</head>
<body>
	<div class="row">
		<div class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-4 col-md-offset-4">
			<div class="login-panel panel panel-default bgnottransparent">
				<div class="panel-heading" >Log in</div>
				<div class="panel-body">
					<form role="form" action="login" method="post" onsubmit="return validcheck(this)">
						<fieldset>
							<div class="form-group">
								<input class="form-control" placeholder="账号" name="id" type="text" autofocus="" onkeyup="this.value=this.value.replace(/[^\d]/g,'')">
							</div>
							<div class="form-group">
								<input class="form-control" placeholder="密码" name="password" type="password" >
							</div>
							<!-- <div class="checkbox">
								<label>
									<input name="remember" type="checkbox" value="Remember Me">Remember Me
								</label>
							</div> -->
							<%-- <a href="<s:url action="login"><s:param name="selectedbook">asdasd</s:param></s:url>" class="btn btn-primary myfloatr"> &nbsp&nbspLogin&nbsp&nbsp</a> --%>
							<button type="submit" class="btn btn-primary myfloatr" >&nbsp&nbspLogin&nbsp&nbsp</button>
							<a href="register.jsp" class="btn btn-primary myfloatr mymarginr" >Register</a></fieldset>
					</form>
					<script>
						      function validcheck(myform){
					        		 if(myform.id.value.length!=8){
					        			 alert("请输入正确的8位有效id");
					        			 myform.id.focus();//即设置焦点在对应的元素
						        		 return false;
					        		 }else if(myform.password.value.length==0){
					        			 alert("请输入密码！！");
					        			 myform.password.focus();//即设置焦点在对应的元素,使用name，而非id
						        		 return false;
						        	 }else{
						        			return true;
						        	 }
					        	 }
						      
						   </script>
				<%-- 	<s:set var="loginflag" value="loginflag" scope="request" />
					<%
									String s = (String) request.getAttribute("loginflag");
					%>
					<script type="text/javascript" language="javascript">
                        if(<%=s%>=="success"){
                        	alert(<%=s%>);
                       
                        /* window.document.location.href="login.jsp"; */
                    </script>
 --%>				</div>
			</div>
		</div><!-- /.col-->
	</div><!-- /.row -->	
	

<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script>
	  if("<%=loginresult%>"=="wrong_id"){
	 	   alert("账号错误");
	    }else if("<%=loginresult%>"=="wrong_password"){
	 	   alert("密码错误");
	    } 
	</script>
</body>
</html>
