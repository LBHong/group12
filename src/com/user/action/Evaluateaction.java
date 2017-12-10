/**
 * 
 */
package com.user.action;

import org.apache.struts2.ServletActionContext;

import com.user.mysqloperate.Mysqloperate;

/**
 * @author lenovo
 *
 */
public class Evaluateaction {
	     private  String date;
         private  String time;
		 private String teacherid;
		 private String studentid;
	     private  String instruction;
	     private String grade;
	     
	     private Mysqloperate sqloperate=new Mysqloperate();
	     
	    public String  addevaluate(){
	    	String [] mdate=date.split("-");
	    	 int intgrade= Integer.parseInt(grade);
	    	 Boolean flag1;
	    	 Boolean flag2;
	    	  
	    	flag2=sqloperate.addgrade(teacherid,mdate[0],mdate[1],mdate[2],time,studentid,intgrade);
	    	System.out.println(flag2);
	    	flag1=sqloperate.addinstruction(teacherid,mdate[0],mdate[1],mdate[2],time,studentid,instruction);
	    	System.out.println(flag1);
	    	if(flag1&&flag2){
	    		ServletActionContext.getRequest().setAttribute("result","evaluated");
	    		return "success";
	    	}else{
	    		ServletActionContext.getRequest().setAttribute("result","wrong");
	    		return  "failure";
	    	}
	    	
	    }
	     
	     /**
		 * @return the date
		 */
		public String getDate() {
			return date;
		}
		/**
		 * @param date the date to set
		 */
		public void setDate(String date) {
			this.date = date;
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
		 * @return the instruction
		 */
		public String getInstruction() {
			return instruction;
		}
		/**
		 * @param instruction the instruction to set
		 */
		public void setInstruction(String instruction) {
			this.instruction = instruction;
		}
		/**
		 * @return the grade
		 */
		public String getGrade() {
			return grade;
		}
		/**
		 * @param grade the grade to set
		 */
		public void setGrade(String grade) {
			this.grade = grade;
		}
}
