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
.myimage{
  width:100%;
  height:70%;
}
.imgwidth{
  width:25%;
  position:absolute;/*弹窗 */
  right:2%;
}
.con4{
     height:180px;
     margin-right:20px;
     overflow: hidden;
}
.con4 .btn{
     width: 100%;
     height: 40px;
     line-height: 25px;
     text-align: center;
     background: #d8b49c;
     display: block;
     font-size: 16px;
     border-radius: 5px;/* 圆角  */
}
.upload{
     float: left;
     position: relative;
}
.upload_pic{
     display: block;
     width: 100%; /* 为保证与表面按钮完全重叠 */
     height: 40px; /* 为保证与表面按钮完全重叠 */
     position: absolute;/*这样就可以使组件重叠  */
     left: 0; /* 必须有 */
     top: 0; /* 必须有 */
     opacity: 0;/* 将系统的文件选择按钮隐藏，然后在相同地方放置一个合适图样替代按钮 */
     border-radius: 5px;
}
#cvs{
	border: 1px solid #000;
	margin:0px 0px 0px 0px;
}

</style>
</head>
<body>
  
	<div class="row">
		<div class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-8 col-md-offset-3 mydiv1">
			<div class="login-panel panel panel-default">
				<div class="panel-heading">Register</div>
				<div class="panel-body">
					<form role="form" class="form-horizontal" action="register" method="post" onsubmit="return validcheck1(this)"><!-- form-horizontal -->
						<fieldset>
                
						 	<div class="myfloatr imgwidth">
							     <div class="con4">
		                            <canvas id="cvs" class="myimage"></canvas>
		    						<span class="btn upload">上传头像<input type="file" class="upload_pic" id="upload" /></span>
		    						             <!-- type为file的就是读取本第文件的输入框 -->
								 </div>
							   <!-- <img  class="myimage" alt="touxiang"  src="images/1.jpg">"http://placehold.it/40/30a5ff/fff" -->
						    </div>
						   
							
						    <div class="form-group">
							    <label class="col-md-3 control-label myfont1" for="username">realname:</label>
								<div class="col-md-6">
								<input id="username" class="form-control" placeholder="Realname" name="username" type="text" autofocus="autofocus">
							    </div>
						    </div>	
										
							<div class="form-group">
							    <label class="col-md-3 control-label myfont1" for="password">password:</label>
								<div class="col-md-6">
								<input  id="password" class="form-control" placeholder="Password，6-16位" name="password" type="password" >
								</div>
							</div>
							
							<div class="form-group">
							    <label class="col-md-3 control-label myfont1" for="phonenumer">telephone:</label>
								<div class="col-md-6">
								<input  id="telephone" class="form-control" placeholder="please input your phone number" name="telephone" type="text" onkeyup="this.value=this.value.replace(/[^\d]/g,'')">
								</div>
							</div>
							
							<div class="form-group">
							    <label class="col-md-3 control-label myfont1" for="email">email:</label>
								<div class="col-md-9">
								<input  id="email" class="form-control" placeholder="please input your valid email address" name="email" type="text" onblur="isEmail()">
								<span id="checkmail"> </span>
								</div>
							</div>
							
							<div class="form-group">
							<label class="col-md-3 control-label myfont1" for="message"><br/>Introduction:</label>
									<div class="col-md-9">
										<textarea class="form-control" id="instruction" name="instruction" placeholder="Please enter your Personal introduction here...(no more than 140 words)" rows="5"></textarea>
									</div>
								</div>
								
							<div class="form-group">
							  <label class="col-md-3 control-label myfont2">identity:</label>
								 <label class="col-md-3 mymargintop2"><!-- 放在标签里可以直接点击标签选中单选按钮 -->
									<input type="radio" name="identity" id="optionsRadios1" value="student" checked>student
						    	 </label>
						    	 <label class="col-md-3 mymargintop2">
									<input type="radio" name="identity" id="optionsRadios2" value="professor" >professor
								</label>
								<!-- <label class="col-md-3 mymargintop2">
									<input type="radio" name="identity" id="optionsRadios3" value="option3">administrator
								</label> -->
							</div>
							
					        <div class="form-group">
								<label class="col-md-3 control-label myfont2">faculty:</label>
								<div class="col-md-9">		
							      <select class="form-control" name="faculty">
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
							  
							<button type="submit" class="btn btn-primary myfloatr mymarginr" >Register</button>
					   </fieldset>
					</form>
					<script>
						      function validcheck1(myform){
					        		 if(myform.username.value.length==0){
					        			 alert("请输入真实姓名!");
					        			 myform.username.focus();//即设置焦点在对应的元素
						        		 return false;
					        		 }else if(myform.password.value.length<6){
					        			 alert("请输入至少6位密码");
					        			 myform.password.focus();//即设置焦点在对应的元素
						        		 return false;
					        		 }else if(myform.password.value.length>16){
					        			 alert("密码至多16位");
					        			 myform.password.focus();//即设置焦点在对应的元素
						        		 return false;
					        		 }else if(myform.telephone.value.length!=11){
					        			 alert("请输入正确的11位手机号码");
					        			 myform.telephone.focus();//即设置焦点在对应的元素
						        		 return false;
					        		 }else if(!isEmail()){
					        			 alert("请输入有效邮箱地址");
					        			 myform.email.focus();//即设置焦点在对应的元素
						        		 return false;
					        		 }else{
					        			 return true;
					        		 }
						        		
					        	 }
						 
						    //验证邮箱格式  
						    function isEmail()   
						    {  
						    	 
						          var email=document.getElementById("email").value;  
						         if(email=="")  
						        {  
						            //alert("请输入邮箱！");  
						            document.getElementById("checkmail").style.color="red";  
						            document.getElementById("checkmail").innerHTML="✘请输入邮箱";  // 为一个之前隐形的span设置内容以作提示*/  
						            return false;   
						        }
						        var pattern= /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;  
						            strEmail=pattern.test(email);  
						            if (strEmail)  
						                {   
						                document.getElementById("checkmail").style.color="green";//设置邮箱可用是的字体颜色  
						                document.getElementById("checkmail").innerHTML="✔";  
						                return true;  
						                }  
						            else  
						            {  
						                document.getElementById("checkmail").style.color="red";//设置邮箱不可用时的字体颜色  
						                document.getElementById("checkmail").innerHTML="✘请输入正确的邮箱";  
						                document.getElementById("email").focus(); 
						                return false; 
						                //alert("邮箱格式不正确！");  
						            }  
						    }      
						   </script>
				</div>
			</div>
		</div><!-- /.col-->
	</div><!-- /.row -->	
	
    <script src="js/img_load.js"></script>
    <script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
