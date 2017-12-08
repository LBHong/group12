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
   //发布保存数据
	private String id; 
	private  String date;
	private  String alltimes;
	
	
	//删除保存数据
	private String id2;
	private String date2;
	private String alltimes2;
	
	private List<teacher> teacherlist;
	private Mysqloperate sqloperate=new Mysqloperate();
	
  
	public String teacherfabu(){
		String date=getDate();
		String alltimes=getAlltimes();
		String [] malltimes=alltimes.split("\\|");
		String [] mdate=date.split("-");
		/*System.out.println(date);
		System.out.println(alltimes);
		System.out.println(id);*/
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
    public String teacherdelete(){
    	String [] malltimes=alltimes2.split("\\|");
		String [] mdate=date2.split("-");
		Boolean flag=true;
		for(int i=0;i<malltimes.length;i++)
		{
			int aaa = Integer.parseInt(malltimes[i]);
			String time="";
			switch(aaa){
			case 1:{
				time="8:00-8:30";break;
			}
			case 2: {
				time="8:30-9:00";break;
			}
			case 3:{
				time="9:00-9:30";break;
			}
			case 4:{
				time="9:30-10:00";break;
			}
			case 5:{
				time="10:00-10:30";break;
			}
			case 6:{
				time="10:30-11:00";break;
			}
			case 7:{
				time="11:00-11:30";break;
			}
			case 8:{
				time="14:00-14:30";break;
			}
			case 9:{
				time="14:30-15:00";break;
			}
			case 10:{
				time="15:00-15:30";break;
			}
			case 11:{
				time="15:30-16:00";break;
			}
			case 12:{
				time="16:00-16:30";break;
			}
			case 13:{
				time="16:30-17:00";break;
			}
			case 14:{
				time="17:00:17:30";break;
			}
			}
			if(!sqloperate.teacherdelete(id2,mdate[0], mdate[1], mdate[2],time)){
				flag=false;
			}else{
				System.out.println(time+"删除成功");
			}
		}
    	if(flag){
    		System.out.println("预约时间删除成功");
    		return "success";
    	}
    	else{
    		System.out.println("预约时间删除失败");
    		return "failure" ;
      }
    	
    
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
	  /**
		 * @return the id2
		 */
		public String getId2() {
			return id2;
		}
		/**
		 * @param id2 the id2 to set
		 */
		public void setId2(String id2) {
			this.id2 = id2;
		}
		/**
		 * @return the date2
		 */
		public String getDate2() {
			return date2;
		}
		/**
		 * @param date2 the date2 to set
		 */
		public void setDate2(String date2) {
			this.date2 = date2;
		}
		/**
		 * @return the alltimes2
		 */
		public String getAlltimes2() {
			return alltimes2;
		}
		/**
		 * @param alltimes2 the alltimes2 to set
		 */
		public void setAlltimes2(String alltimes2) {
			this.alltimes2 = alltimes2;
		}
}
