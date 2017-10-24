<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
.mymargintop{
    margin-top:10px;
}
.mydiv1{
    width:50%;
}
.myfont1{
   font-size: 20px;
}
.myfont2{
   font-size: 20px;
   font-weight:bold;
}
.mylineheight1{
  line-height:35px;
}
.myalign1{
 text-align:right;
}
.mymargintop2{
margin-top:13px;
}
</style>
</head>
<body>
	<div class="row">
		<div class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-8 col-md-offset-3 mydiv1">
			<div class="login-panel panel panel-default">
				<div class="panel-heading" >Register</div>
				<div class="panel-body">
					<form role="form" class="form-horizontal"><!-- form-horizontal -->
						<fieldset>
						
							<div class="form-group">
							    <label class="col-md-3 control-label myfont1" for="username">username:</label>
								<div class="col-md-9">
								<input id="username" class="form-control" placeholder="Username" name="username" type="text" autofocus="autofocus">
							    </div>
							</div>		
												
							<div class="form-group">
							    <label class="col-md-3 control-label myfont1 for="password">password:</label>
								<div class="col-md-9">
								<input  id="password" class="form-control" placeholder="Password" name="Password" type="password" >
								</div>
							</div>
							
							<div class="form-group">
							    <label class="col-md-3 control-label myfont1" for="email">eamil:</label>
								<div class="col-md-9">
								<input  id="eamil" class="form-control" placeholder="please input your valid email address" name="eamil" type="text" >
								</div>
							</div>
							
							<div class="form-group">
							    <label class="col-md-3 control-label myfont1" for="phonenumer">telephone:</label>
								<div class="col-md-9">
								<input  id="phonenumber" class="form-control" placeholder="please input your valid phone number" name="phonenumber" type="text" >
								</div>
							</div>
							
							<div class="form-group">
							<label class="col-md-3 control-label myfont1" for="message"><br/>Introduction:</label>
									<div class="col-md-9">
										<textarea class="form-control" id="message" name="message" placeholder="Please enter your Personal introduction here...(no more than 140 words)" rows="5"></textarea>
									</div>
								</div>
								
							<div class="form-group">
							  <label class="col-md-3 control-label myfont2">identity:</label>
								 <label class="col-md-3 mymargintop2"><!-- 放在标签里可以直接点击标签选中单选按钮 -->
									<input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>student
						    	 </label>
						    	 <label class="col-md-3 mymargintop2">
									<input type="radio" name="optionsRadios" id="optionsRadios2" value="option3">professor
								</label>
								<label class="col-md-3 mymargintop2">
									<input type="radio" name="optionsRadios" id="optionsRadios3" value="option3">administrator
								</label>
							</div>
							
					        <div class="form-group">
								<label class="col-md-3 control-label myfont2">faculty:</label>
								<div class="col-md-9">		
							      <select class="form-control">
											<option selected="selected">计算机科学与技术学院</option>
											<option>航天学院</option>
											<option>材料科学与工程学院</option>
											<option>电气工程与自动化学院</option>
											<option>人文与社会科学学院</option>
											<option>市政环境科学学院</option>
											<option>交通科学与工程学院</option>
											<option>法学院</option>
											<option>机电工程学院</option>
											<option>能源科学与工程学院</option>
											<option>理学院</option>
											<option>管理学院</option>
											<option>土木工程学院</option>
											<option>建筑学院</option>
											<option>外国语学院</option>
											<option>基础学部</option>
										</select>
								</div>
							</div>
							<div class="checkbox">
								<label>
									<input name="login" type="checkbox" value="login">Log in directly
								</label>
							</div>
							<a href="login.jsp" class="btn btn-primary myfloatr mymarginr" >Register</a>
					   </fieldset>
					</form>
				</div>
			</div>
		</div><!-- /.col-->
	</div><!-- /.row -->	
	

<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
