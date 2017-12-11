/**
 * 
 */
package com.user.action;

import java.util.Calendar;

import org.apache.struts2.ServletActionContext;

import com.user.mysqloperate.Mysqloperate;

/**
 * @author lenovo
 *
 */
public class Deleteaction {
	 private String studentid;
	 
	 private String year;
	 private String month;
	 private String day;
	 private String time;
	 
	 private String teacherid;
	
	private Mysqloperate sqloperate=new Mysqloperate();
	 
	 
	  
	 public String studentdelete(){
		 Boolean flag=false;
		 //先判断是否是至少明天
		 Calendar today=Calendar.getInstance();
		  int nowyear=today.get(Calendar.YEAR);
		  int nowmonth=today.get(Calendar.MONTH)+1;
		  int nowdate=today.get(Calendar.DATE);
		  
		   Calendar cal=Calendar.getInstance();
	        cal.set(nowyear,nowmonth-1,nowdate);
	        long time1 = today.getTimeInMillis();       //得到当前时间的毫秒数 
	        cal.set(Integer.parseInt(year), Integer.parseInt(month)-1,Integer.parseInt(day));
	        long time2 = cal.getTimeInMillis();          
	        long between_days=(time2-time1)/(1000*3600*24);   //得到当前时间相差不超过一天的日期
	        int datediff=Integer.parseInt(String.valueOf(between_days));
	
	        if(datediff<1){
	        	ServletActionContext.getRequest().setAttribute("deleteresult","overtime");
	        	return "failure";
	        }else{
	        	 flag=sqloperate.studentdelete(year,month,day,time,studentid);
	    		 if(flag){
	    			 ServletActionContext.getRequest().setAttribute("deleteresult","deleted");
	    			 return "success";
	    		 }else{
	    			 ServletActionContext.getRequest().setAttribute("deleteresult","wrong");
	    			 return "failure";
	    		 }
	        }
	       
		
     }
	 public String teacherdeleteappoint(){
		 Boolean flag;
		 
		 Calendar today=Calendar.getInstance();
		  int nowyear=today.get(Calendar.YEAR);
		  int nowmonth=today.get(Calendar.MONTH)+1;
		  int nowdate=today.get(Calendar.DATE);
		  
		   Calendar cal=Calendar.getInstance();
	        cal.set(nowyear,nowmonth-1,nowdate);
	        long time1 = today.getTimeInMillis();       //得到当前时间的毫秒数 
	        cal.set(Integer.parseInt(year), Integer.parseInt(month)-1,Integer.parseInt(day));
	        long time2 = cal.getTimeInMillis();          
	        long between_days=(time2-time1)/(1000*3600*24);   //得到当前时间相差不超过一天的日期
	        int datediff=Integer.parseInt(String.valueOf(between_days));

	        if(datediff<1){
	        	ServletActionContext.getRequest().setAttribute("teacherdeleteresult","overtime");
	        	return "failure";
	        }else{
	        	flag=sqloperate.teacherdelete(teacherid, year,month,day,time);
	        	 if(flag){
	    			 ServletActionContext.getRequest().setAttribute("teacherdeleteresult","deleted");
	    			 return "success";
	    		 }else{
	    			 ServletActionContext.getRequest().setAttribute("teacherdeleteresult","wrong");
	    			 return "failure";
	    		 }
	        }
		
		 
	 }
	 
	 /**
		 * @return the teacherid
		 */
		public String getTeacherid() {
			return teacherid;
		}
		/**
		 * @param teacherid the teacherid to set
		 */
		public void setTeacherid(String teacherid) {
			this.teacherid = teacherid;
		}
     /**
	 * @return the studentid
	 */
	public String getStudentid() {
		return studentid;
	}
	/**
	 * @param studentid the studentid to set
	 */
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	
}
