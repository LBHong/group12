/**
 * 
 */
package com.user.action;

import java.util.List;

import com.user.mysqloperate.Mysqloperate;
import com.user.userteacher.teacher;

/**
 * @author lenovo
 *
 */
public class Releaseaction {
   
	private String id;
	private  String date;
	private  String alltimes;
	private List<teacher> teacherlist;
	private Mysqloperate sqloperate=new Mysqloperate();
    public String teacherfabu(){
		String date=getDate();
		String alltimes=getAlltimes();
		String [] malltimes=alltimes.split("|");
		String [] mdate=date.split("-");
		System.out.println(date);
		System.out.println(alltimes);
		System.out.println(id);
		Boolean f=false;
		String A[]={"0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
		for(int i=0;i<malltimes.length;i++)
		{
			
			try {
				int aaa;
			    aaa = Integer.parseInt(malltimes[i]);
			    System.out.println(aaa);
			    A[aaa-1]="1";

			} catch (NumberFormatException e) {

			    e.printStackTrace();
			}
		}
		f=sqloperate.releasebooking(A, id, mdate[0], mdate[1], mdate[2]);
		return "success";
	}
/*	public String chaxunallteachers(){
		List<teacher> tel;
		tel=sqloperate.chaxunallteachers(faculty);
		this.teacherlist=tel;
		if(tel==null||tel.size()==0){
    		return "failure";
    	}
    	else{
    	return "success";
    	}
	}*/
public List<teacher> getTeacherlist() {
		return teacherlist;
	}
	public void setTeacherlist(List<teacher> teacherlist) {
		this.teacherlist = teacherlist;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
