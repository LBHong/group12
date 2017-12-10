/**
 * 
 */
package com.user.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.user.mysqloperate.Mysqloperate;
import com.user.releasebooking.releasebooking;

/**
 * @author lenovo
 *
 */
public class bookingaction {
	
	 private String professorname;
	 private ArrayList<String> idList; 
	 private ArrayList<Map<String,String>> professorList=new ArrayList<Map<String,String>>();
	 
	 private String teacherid;
	 private Map<String,String> timesMap=new HashMap<String,String>();
	 
	 private  String date;
     private  String alltimes;
		 private String tid;
		private String sid;
	    private  String successinstruction;
	 
	 private Mysqloperate sqloperate=new Mysqloperate();
	 /*******************************************************************************/
	  public String searchprofessor(){
		  idList=sqloperate.getIdByNameOfPro(professorname);
		  if(idList!=null){
			  for(String oneid:idList){
				  professorList.add(sqloperate.showteacher(oneid));
			  }
			/*  ServletActionContext.getRequest().setAttribute("professorList",professorList );*/
			  ServletActionContext.getRequest().getSession().setAttribute("professorList",professorList);
			  ServletActionContext.getRequest().getSession().setAttribute("chosenTimes",this.timesMap);//重新查找了老师之后，存的时间和老师信息就是无效的，而且此时没有选择老师，需要清空section中的时间和老师
		   	  ServletActionContext.getRequest().getSession().setAttribute("chosenTeacherId",teacherid);
		  }else{//没有查到该老师
			  ServletActionContext.getRequest().getSession().setAttribute("professorList",professorList);
			  ServletActionContext.getRequest().getSession().setAttribute("chosenTimes",this.timesMap);
		   	  ServletActionContext.getRequest().getSession().setAttribute("chosenTeacherId",teacherid);
		  }
		  return "success";
	  }
	
		public String  queryOneProfessorAllTimes()
		{
			String key;
			ArrayList<String> value;
			releasebooking rb=new releasebooking();
			List<releasebooking> rblist=new LinkedList<releasebooking>();
			rblist=sqloperate.Queryateacher(teacherid);
			
			for(int i=0;i<rblist.size();i++)
			{
				rb=rblist.get(i);
				key=rb.year+"-"+rb.month+"-"+rb.day;
				value=new ArrayList<String>();
				if(rb.a.equals("1")){value.add("1");}
				if(rb.b.equals("1")){value.add("2");}
				if(rb.c.equals("1")){value.add("3");}
				if(rb.d.equals("1")){value.add("4");}
				if(rb.e.equals("1")){value.add("5");}
				if(rb.f.equals("1")){value.add("6");}
				if(rb.g.equals("1")){value.add("7");}
				if(rb.h.equals("1")){value.add("8");}
				if(rb.i.equals("1")){value.add("9");}
				if(rb.j.equals("1")){value.add("10");}
				if(rb.k.equals("1")){value.add("11");}
				if(rb.l.equals("1")){value.add("12");}
				if(rb.m.equals("1")){value.add("13");}
				if(rb.n.equals("1")){value.add("14");}
				String finalvalue="";
				for(int ii=0;ii<value.size()-1;ii++){
					  finalvalue+=value.get(ii)+"|";
				}
				if(value.size()!=0){
					finalvalue+=value.get(value.size()-1);//存在某一天发布过时间，但是全部被预约，现在是会取到这一天，但是不会有空闲
					this.timesMap.put(key,finalvalue);
				}//如果全部被预约就不作为一个有空闲时间传回
			}
			if(this.timesMap==null){return "failure";}
			else{
				ServletActionContext.getRequest().getSession().setAttribute("chosenTimes",this.timesMap);
				ServletActionContext.getRequest().getSession().setAttribute("chosenTeacherId",teacherid);
				System.out.println(teacherid);
				return "success";
			}
		}
		public String studentappalication()
		{
		String [] malltimes=alltimes.split("\\|");
		String [] mdate=date.split("-");
		String A[]={"0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
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
		for(String a:A){
			System.out.println(a);
		}
		System.out.println(tid);
		System.out.println(sid);
		System.out.println(mdate[0]);
		System.out.println(mdate[1]);
		System.out.println(mdate[2]);
		System.out.println(successinstruction);
		
		f=sqloperate.appointment(tid, sid, A, mdate[0], mdate[1],mdate[2],successinstruction);
		refreshtimesafterapppoint();
		if(f){ 
		   System.out.println(f);
		   ServletActionContext.getRequest().setAttribute("appointresult","finished");
		   return "success";}
		else {
			ServletActionContext.getRequest().setAttribute("appointresult","used");
			return "failure";
			}
		}
		
      public boolean refreshtimesafterapppoint(){
    	   String key;
			ArrayList<String> value;
			releasebooking rb=new releasebooking();
			List<releasebooking> rblist=new LinkedList<releasebooking>();
			rblist=sqloperate.Queryateacher(tid);
			
			for(int i=0;i<rblist.size();i++)
			{
				rb=rblist.get(i);
				key=rb.year+"-"+rb.month+"-"+rb.day;
				value=new ArrayList<String>();
				if(rb.a.equals("1")){value.add("1");}
				if(rb.b.equals("1")){value.add("2");}
				if(rb.c.equals("1")){value.add("3");}
				if(rb.d.equals("1")){value.add("4");}
				if(rb.e.equals("1")){value.add("5");}
				if(rb.f.equals("1")){value.add("6");}
				if(rb.g.equals("1")){value.add("7");}
				if(rb.h.equals("1")){value.add("8");}
				if(rb.i.equals("1")){value.add("9");}
				if(rb.j.equals("1")){value.add("10");}
				if(rb.k.equals("1")){value.add("11");}
				if(rb.l.equals("1")){value.add("12");}
				if(rb.m.equals("1")){value.add("13");}
				if(rb.n.equals("1")){value.add("14");}
				String finalvalue="";
				for(int ii=0;ii<value.size()-1;ii++){
					  finalvalue+=value.get(ii)+"|";
				}
				if(value.size()!=0){
					finalvalue+=value.get(value.size()-1);//瀛樺湪鏌愪竴澶╁彂甯冭繃鏃堕棿锛屼絾鏄叏閮ㄨ棰勭害锛岀幇鍦ㄦ槸浼氬彇鍒拌繖涓�澶╋紝浣嗘槸涓嶄細鏈夌┖闂�
					this.timesMap.put(key,finalvalue);
				}//濡傛灉鍏ㄩ儴琚绾﹀氨涓嶄綔涓轰竴涓湁绌洪棽鏃堕棿浼犲洖
				
			}
			if(this.timesMap==null){return false;}
			else{
				ServletActionContext.getRequest().getSession().setAttribute("chosenTimes",this.timesMap);
				ServletActionContext.getRequest().getSession().setAttribute("chosenTeacherId",tid);
				return true;
			}
      }
		
      /************************************************************************/
      public String getTid() {
		return tid;
		}
		public void setTid(String tid) {
		this.tid = tid;
		}

		public String getSuccessinstruction() {
		return successinstruction;
		}
		public void setSuccessinstruction(String successinstruction) {
		this.successinstruction = successinstruction;
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
			 * @return the alltimes
			 */
			public String getAlltimes() {
				return alltimes;
			}

			/**
			 * @param alltimes the alltimes to set
			 */
			public void setAlltimes(String alltimes) {
				this.alltimes = alltimes;
			}

			/**
			 * @return the sid
			 */
			public String getSid() {
				return sid;
			}

			/**
			 * @param sid the sid to set
			 */
			public void setSid(String sid) {
				this.sid = sid;
			}

		public String getTeacherid() {
			return teacherid;
		}
		
		public void setTeacherid(String id) {
			this.teacherid = id;
		}
		public Map<String, String> getTimesMap() {
			return timesMap;
		}
		public void setTimesMap(Map<String, String> TimesMap) {
			this.timesMap = TimesMap;
		}

	  public String getProfessorname() {
		return professorname;
	}

	public void setProfessorname(String professorname) {
		this.professorname = professorname;
	}
}
