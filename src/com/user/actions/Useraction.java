package com.user.actions;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.user.userstudent.student;
import com.user.userteacher.teacher;
import com.user.mysqloperate.Mysqloperate;
import com.user.releasebooking.releasebooking;

@SuppressWarnings("serial")
public class Useraction extends ActionSupport {

	private String username;
	private String password;
	private String email;
	private String telephone;
	private String instruction;
	private String faculty;
	private String id;
	
	
	
	private List<teacher> teacherlist;
	
	Map<String,String> detailMap=new HashMap<String,String>();
	

	private String identity;
	private Mysqloperate sqloperate=new Mysqloperate();
	private student st=null;
	private teacher te=null;
	private Map<String,String> map;
	public int number=0;
	
	
	private  String date;
	private  String alltimes;
	private String sid;
	

	private  String successinstruction;
	
	public String studentappalication()
	{
		String tid=id;
		String [] malltimes=alltimes.split("\\|");
		String [] mdate=date.split("-");
		String A[]=new String[14];
		for(int i=0;i<malltimes.length;i++)
		{
			try {

			    int aaa = Integer.parseInt(malltimes[i]);
			    A[aaa-1]="1";

			} catch (NumberFormatException e) {

			    e.printStackTrace();

			}
			
		}
		boolean f;
		f=sqloperate.appointment(tid, sid, A, mdate[0], mdate[1],mdate[2],successinstruction);
		if(f==true){return "success";}
		else {return "failure";}
		
	}
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	
	public String getSuccessinstruction() {
		return successinstruction;
	}
	public void setSuccessinstruction(String successinstruction) {
		this.successinstruction = successinstruction;
	}
	public String comeoutids(String faculty ){
		
		String xueyuan;
		if(faculty=="计算机科学与技术学院"){xueyuan="00";}
		else if(faculty=="航天学院"){xueyuan="01";}
		else if(faculty=="材料科学与工程学院"){xueyuan="02";}
		else if(faculty=="电气工程与自动化学院"){xueyuan="03";}
		else if(faculty=="人文与社会科学学院"){xueyuan="04";}
		else if(faculty=="市政环境科学学院"){xueyuan="05";}
		else if(faculty=="交通科学与工程学院"){xueyuan="06";}
		else if(faculty=="法学院"){xueyuan="07";}
		else if(faculty=="机电工程学院"){xueyuan="08";}
		else if(faculty=="能源科学与工程学院"){xueyuan="09";}
		else if(faculty=="理学院"){xueyuan="10";}
		else if(faculty=="管理学院"){xueyuan="11";}
		else if(faculty=="土木工程学院"){xueyuan="12";}
		else if(faculty=="建筑学院"){xueyuan="13";}
		else if(faculty=="外国语学院"){xueyuan="14";}
		else if(faculty=="基础学部"){xueyuan="15";}
		number++;
		string strValue= number.ToString().PadLeft(5,'0');
		String id='0'+xueyuan+strValue;
		return id;
	}
	public String comeoutidt(String faculty ){
		
		String xueyuan;
		if(faculty=="计算机科学与技术学院"){xueyuan="00";}
		else if(faculty=="航天学院"){xueyuan="01";}
		else if(faculty=="材料科学与工程学院"){xueyuan="02";}
		else if(faculty=="电气工程与自动化学院"){xueyuan="03";}
		else if(faculty=="人文与社会科学学院"){xueyuan="04";}
		else if(faculty=="市政环境科学学院"){xueyuan="05";}
		else if(faculty=="交通科学与工程学院"){xueyuan="06";}
		else if(faculty=="法学院"){xueyuan="07";}
		else if(faculty=="机电工程学院"){xueyuan="08";}
		else if(faculty=="能源科学与工程学院"){xueyuan="09";}
		else if(faculty=="理学院"){xueyuan="10";}
		else if(faculty=="管理学院"){xueyuan="11";}
		else if(faculty=="土木工程学院"){xueyuan="12";}
		else if(faculty=="建筑学院"){xueyuan="13";}
		else if(faculty=="外国语学院"){xueyuan="14";}
		else if(faculty=="基础学部"){xueyuan="15";}
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
    		this.st=sqloperate.addstudent(username,password,telephone,email,id,instruction,faculty);
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
	public String teacherfabu(){
		String date=getDate();
		String alltimes=getAlltimes();
		String [] malltimes=alltimes.split("|");
		String [] mdate=date.split("-");
		boolean f;String A[]=new String[14];
		for(int i=0;i<malltimes.length;i++)
		{
			try {

			    int aaa = Integer.parseInt(malltimes[i]);
			    A[aaa-1]="1";

			} catch (NumberFormatException e) {

			    e.printStackTrace();

			}
			
		}
		
		f=sqloperate.releasebooking(A, id, mdate[0], mdate[1], mdate[2]);
		return "success";
	}
	public String chaxunallteachers(){
		List<teacher> tel;
		tel=sqloperate.chaxunallteachers(faculty);
		this.teacherlist=tel;
		if(tel==null||tel.size()==0){
    		return "failure";
    	}
    	else{
    	return "success";
    	}
	}
	
	
	
	public String  teachershowtimes()
	{
		String key,value;
		releasebooking rb=new releasebooking();
		List<releasebooking> rblist=new LinkedList<releasebooking>();
		rblist=sqloperate.Queryateacher(id);
		
		for(int i=0;i<rblist.size();i++)
		{
			rb=rblist.get(i);
			key=rb.year+"-"+rb.month+"-"+rb.day;
			if(rb.a.equals("1")){value+="1";}
			if(rb.b.equals("1")){value+="|"+"2";}
			if(rb.c.equals("1")){value+="|"+"3";}
			if(rb.d.equals("1")){value+="|"+"4";}
			if(rb.e.equals("1")){value+="|"+"5";}
			if(rb.f.equals("1")){value+="|"+"6";}
			if(rb.g.equals("1")){value+="|"+"7";}
			if(rb.h.equals("1")){value+="|"+"8";}
			if(rb.i.equals("1")){value+="|"+"9";}
			if(rb.j.equals("1")){value+="|"+"10";}
			if(rb.k.equals("1")){value+="|"+"11";}
			if(rb.l.equals("1")){value+="|"+"12";}
			if(rb.m.equals("1")){value+="|"+"13";}
			if(rb.n.equals("1")){value+="|"+"14";}
			if(value.equals(null))
			{
				return "failure";
			}
			
			this.detailMap.put(key,value);
		}
		if(this.detailMap==null){return "failure";}
		return "success";
	}
	
	/******************************************************************************************/
	
	
	public Map<String, String> getDetailMap() {
		return detailMap;
	}
	public void setDetailMap(Map<String, String> detailMap) {
		this.detailMap = detailMap;
	}
	public List<teacher> getTeacherlist() {
		return teacherlist;
	}
	public void setTeacherlist(List<teacher> teacherlist) {
		this.teacherlist = teacherlist;
	}
	public String getUsername() {
		return username;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAlltimes() {
		return alltimes;
	}
	public void setAlltimes(String alltimes) {
		this.alltimes = alltimes;
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

