package com.user.action;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.user.userstudent.student;
import com.user.userteacher.teacher;
import com.user.mysqloperate.Mysqloperate;

@SuppressWarnings("serial")
public class Useraction extends ActionSupport {

	private String username;
	private String password;
	private String email;
	private String telephone;
	private String instruction;
	private String faculty;
	private String id;
	private String identity;
	private Mysqloperate sqloperate=new Mysqloperate();
	private student st=null;
	private teacher te=null;
	private Map<String,String> map;
	
    public String loginflag="failure";
	public String comeoutids(){
		
		String xueyuan;
		if(faculty.equals("计算机科学与技术学院")){xueyuan="00";}
		else if(faculty.equals("航天学院")){xueyuan="01";}
		else if(faculty.equals("材料科学与工程学院")){xueyuan="02";}
		else if(faculty.equals("电气工程与自动化学院")){xueyuan="03";}
		else if(faculty.equals("人文与社会科学学院")){xueyuan="04";}
		else if(faculty.equals("市政环境科学学院")){xueyuan="05";}
		else if(faculty.equals("交通科学与工程学院")){xueyuan="06";}
		else if(faculty.equals("法学院")){xueyuan="07";}
		else if(faculty.equals("机电工程学院")){xueyuan="08";}
		else if(faculty.equals("能源科学与工程学院")){xueyuan="09";}
		else if(faculty.equals("理学院")){xueyuan="10";}
		else if(faculty.equals("管理学院")){xueyuan="11";}
		else if(faculty.equals("土木工程学院")){xueyuan="12";}
		else if(faculty.equals("建筑学院")){xueyuan="13";}
		else if(faculty.equals("外国语学院")){xueyuan="14";}
		else if("基础学部".equals(faculty)){xueyuan="15";}
		else{
			xueyuan="16";
		}
		
		int thenum=sqloperate.getstudentnum()+1;
		String strValue=String.format("%05d",thenum);
		String id='0'+xueyuan+strValue;
		return id;
	}
public String comeoutidp(){
		
		String xueyuan;
		if(faculty.equals("计算机科学与技术学院")){xueyuan="00";}
		else if(faculty.equals("航天学院")){xueyuan="01";}
		else if(faculty.equals("材料科学与工程学院")){xueyuan="02";}
		else if(faculty.equals("电气工程与自动化学院")){xueyuan="03";}
		else if(faculty.equals("人文与社会科学学院")){xueyuan="04";}
		else if(faculty.equals("市政环境科学学院")){xueyuan="05";}
		else if(faculty.equals("交通科学与工程学院")){xueyuan="06";}
		else if(faculty.equals("法学院")){xueyuan="07";}
		else if(faculty.equals("机电工程学院")){xueyuan="08";}
		else if(faculty.equals("能源科学与工程学院")){xueyuan="09";}
		else if(faculty.equals("理学院")){xueyuan="10";}
		else if(faculty.equals("管理学院")){xueyuan="11";}
		else if(faculty.equals("土木工程学院")){xueyuan="12";}
		else if(faculty.equals("建筑学院")){xueyuan="13";}
		else if(faculty.equals("外国语学院")){xueyuan="14";}
		else if("基础学部".equals(faculty)){xueyuan="15";}
		else{
			xueyuan="16";
		}
		
		int thenum=sqloperate.getprofessornum()+1;
		String strValue=String.format("%05d",thenum);
		String id='1'+xueyuan+strValue;
		return id;
	}

	public String register() {
    	String Identity= getIdentity();
    	
    	if(Identity.equals("student"))
    	{
    		id=comeoutids();
    		this.st=sqloperate.addstudent(username,password,telephone,email,id,instruction,faculty);
    		sqloperate.addstudentnum(1);
    		if(this.st==null){return "failure";}
    		else{return "success";}
    	}
    	else{
    		id=comeoutidp();
    		this.te=sqloperate.addteacher(username,password,telephone,email,id,faculty,instruction);
    		sqloperate.addprofessornum(1);
    		if(this.te==null){return "failure";}
    		else{return "success";}
    	}
    }
	public String alogin() {
		String loginresult=sqloperate.login(password,id);
		
			// TODO Auto-generated catch block
		//ServletActionContext.getRequest().setAttribute("a", b);
		//ServletActionContext.getRequest().getSession().setAttribute("user", u);
		if(loginresult=="student_success"){
			loginflag="student_success"; 	System.out.println(loginflag);
			ServletActionContext.getRequest().getSession().setAttribute("id", id);
			return "student_success";}
		else if(loginresult=="teacher_success"){
			loginflag="teacher_success"; 	System.out.println(loginflag);
			ServletActionContext.getRequest().getSession().setAttribute("id", id);
			return "teacher_success";
		}else if(loginresult=="wrong_id"){
			loginflag="wrong_id"; 	
			ServletActionContext.getRequest().setAttribute("loginresult",loginresult);
			System.out.println(loginflag);
			return "wrong_id";
		}else if(loginresult=="wrong_password"){
			loginflag="wrong_password"; 	
			ServletActionContext.getRequest().setAttribute("loginresult",loginresult);
			return "wrong_password";
		}else{
			loginflag="others"; 	System.out.println(loginflag);
			return "others";
		}
	
	}

	/******************************************************************************************/
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}
}
