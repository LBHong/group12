/**
 * 
 */
package com.user.mysqloperate;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.user.releasebooking.releasebooking;
import com.user.successbooking.successbooking;

/**
 * @author lenovo
 *
 */
public class testmysql {
    public static void main(String args[]){
    	
    	 Mysqloperate mysql=new Mysqloperate();
    	
    	 //mysql.addstudent("锟斤拷锟斤拷锟斤拷", "1234", "188458895380", "wangchunyangsz@163.com", "023456", "锟斤拷锟斤拷锟窖г貉э拷锟�","锟斤拷锟斤拷锟窖г�");
    	 //mysql.addteacher("锟斤拷锟斤拷锟斤拷", "1234", "188458895380", "lxy@163.com", "120000","锟斤拷锟斤拷锟窖г�","锟斤拷锟斤拷锟窖г猴拷锟绞�");
    	 //System.out.println(mysql.showstudent("1256"));
    	 //System.out.println(mysql.showteacher("120000"));
		//System.out.println(mysql.login("1234","023456"));
    	// mysql.addprofessornum(3);
    	 //mysql.addstudentnum(2);
    	// System.out.println(mysql.getstudentnum());
    	 //System.out.println(mysql.getprofessornum());
//    	 String[] A={"1","1","1","0","0","0","0","0","0","0","0","0","0","0"};
//    	 mysql.releasebooking(A,"123","2000","10","20");
    	// String[] A={"0","1","1","1","1","1","1","1","1","1","1","1","1","1"};
    	// mysql.releasebooking(A,"12241234","2000","10","20");
    	 //System.out.println(mysql.getIdByNameOfPro("鏉庡浆寮�"));
//    	 for(successbooking abook:mysql.teashowtime("10500001")){
//    		 System.out.println(abook.studentid+abook.year+":"+abook.month+":"+abook.day+abook.teacherid+abook.time+abook.instruction);
//    	 }
    	Mysqloperate sql=new Mysqloperate();
	    	List<successbooking> list= new LinkedList<successbooking>();
    	successbooking a=new successbooking();
    	successbooking b=new successbooking();
    	successbooking c=new successbooking();
    	successbooking d=new successbooking();
    	successbooking e=new successbooking();
    	a.setYear("1999");a.setMonth("12");a.setDay("22");a.setTime("9:00-9:30");
    	b.setYear("1999");b.setMonth("12");b.setDay("5");b.setTime("10:00-10:30");
    	c.setYear("1999");c.setMonth("12");c.setDay("9");c.setTime("11:00-11:30");
    	d.setYear("1999");d.setMonth("12");d.setDay("8");d.setTime("15:00-15:30");
    	e.setYear("1999");e.setMonth("12");e.setDay("20");e.setTime("8:00-14:30");
    	list.add(a);list.add(b);list.add(c);list.add(d);list.add(e);
    	System.out.println(d.getMonth());
    	sql.paixu(list);
    	
    	System.out.println(list.get(0).getTime());
    	System.out.println(list.get(1).getTime());
    	System.out.println(list.get(2).getTime());
    	System.out.println(list.get(3).getTime());
    	System.out.println(list.get(4).getTime());
    }
}
