package com.user.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.user.userstudent.student;
import com.user.userteacher.teacher;
import com.user.action.Useraction;

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
	public int number=0;
	public String comeoutids(String faculty ){
		
		String xueyuan;
		if(faculty=="�������ѧ�뼼��ѧԺ"){xueyuan="00";}
		else if(faculty=="����ѧԺ"){xueyuan="01";}
		else if(faculty=="���Ͽ�ѧ�빤��ѧԺ"){xueyuan="02";}
		else if(faculty=="�����������Զ���ѧԺ"){xueyuan="03";}
		else if(faculty=="����������ѧѧԺ"){xueyuan="04";}
		else if(faculty=="����������ѧѧԺ"){xueyuan="05";}
		else if(faculty=="��ͨ��ѧ�빤��ѧԺ"){xueyuan="06";}
		else if(faculty=="��ѧԺ"){xueyuan="07";}
		else if(faculty=="���繤��ѧԺ"){xueyuan="08";}
		else if(faculty=="��Դ��ѧ�빤��ѧԺ"){xueyuan="09";}
		else if(faculty=="��ѧԺ"){xueyuan="10";}
		else if(faculty=="����ѧԺ"){xueyuan="11";}
		else if(faculty=="��ľ����ѧԺ"){xueyuan="12";}
		else if(faculty=="����ѧԺ"){xueyuan="13";}
		else if(faculty=="�����ѧԺ"){xueyuan="14";}
		else if(faculty=="����ѧ��"){xueyuan="15";}
		number++;
		string strValue= number.ToString().PadLeft(5,'0');
		String id='0'+xueyuan+strValue;
		return id;
	}
	public String comeoutidt(String faculty ){
		
		String xueyuan;
		if(faculty=="�������ѧ�뼼��ѧԺ"){xueyuan="00";}
		else if(faculty=="����ѧԺ"){xueyuan="01";}
		else if(faculty=="���Ͽ�ѧ�빤��ѧԺ"){xueyuan="02";}
		else if(faculty=="�����������Զ���ѧԺ"){xueyuan="03";}
		else if(faculty=="����������ѧѧԺ"){xueyuan="04";}
		else if(faculty=="����������ѧѧԺ"){xueyuan="05";}
		else if(faculty=="��ͨ��ѧ�빤��ѧԺ"){xueyuan="06";}
		else if(faculty=="��ѧԺ"){xueyuan="07";}
		else if(faculty=="���繤��ѧԺ"){xueyuan="08";}
		else if(faculty=="��Դ��ѧ�빤��ѧԺ"){xueyuan="09";}
		else if(faculty=="��ѧԺ"){xueyuan="10";}
		else if(faculty=="����ѧԺ"){xueyuan="11";}
		else if(faculty=="��ľ����ѧԺ"){xueyuan="12";}
		else if(faculty=="����ѧԺ"){xueyuan="13";}
		else if(faculty=="�����ѧԺ"){xueyuan="14";}
		else if(faculty=="����ѧ��"){xueyuan="15";}
		number++;
		string strValue= number.ToString().PadLeft(5,'0');
		String id='1'+xueyuan+strValue;
		return id;
	}
	public String register() {
    	String Identity= getIdentity();
    	
    	if(Identity=="student")
    	{
    		id=comeoutids();
    		this.st=sqloperate.addstudent(username,password,telephone,email,id,instruction);
    		if(this.st==null){return "failure";}
    		else{return "success";}
    	}
    	else{
    		id=comeoutidt();
    		this.te=sqloperate.addteacher(username,password,telephone,email,id,faculty,instruction);
    		if(this.te==null){return "failure";}
    		else{return "success";}
    	}
    	
    }
	public String alogin(String id,String password) {
		
		this.map=sqloperate.login(password,id);
		if(this.map!=null){return "success";}
		else{return "failure";}
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
