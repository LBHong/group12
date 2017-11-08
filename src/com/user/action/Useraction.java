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
	public String register() {
    	String Identity= getIdentity();
    	
    	if(Identity=="student")
    	{
    		this.st=sqloperate.addstudent(username,password,telephone,email,id,instruction);
    		if(st==null){return "failure";}
    		else{return "success";}
    	}
    	else{
    		this.te=sqloperate.addteacher(username,password,telephone,email,id,faculty,instruction);
    		if(te==null){return "failure";}
    		else{return "success";}
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
