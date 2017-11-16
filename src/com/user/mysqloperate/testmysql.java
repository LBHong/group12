/**
 * 
 */
package com.user.mysqloperate;

import java.sql.SQLException;

import com.user.releasebooking.releasebooking;
import com.user.successbooking.successbooking;

/**
 * @author lenovo
 *
 */
public class testmysql {
    public static void main(String args[]){
    	 Mysqloperate mysql=new Mysqloperate();
    	 //mysql.addstudent("������", "1234", "188458895380", "wangchunyangsz@163.com", "023456", "�����ѧԺѧ��","�����ѧԺ");
    	 //mysql.addteacher("������", "1234", "188458895380", "lxy@163.com", "120000","�����ѧԺ","�����ѧԺ��ʦ");
    	 //System.out.println(mysql.showstudent("1256"));
    	 //System.out.println(mysql.showteacher("120000"));
		//System.out.println(mysql.login("1234","023456"));
    	// mysql.addprofessornum(3);
    	 //mysql.addstudentnum(2);
    	// System.out.println(mysql.getstudentnum());
    	 //System.out.println(mysql.getprofessornum());
    	 /*String[] A={"1","1","1","0","0","0","0","0","0","0","0","0","0","0"};
    	 mysql.releasebooking(A,"12341234","2000","10","20");*/
    	// String[] A={"0","1","1","1","1","1","1","1","1","1","1","1","1","1"};
    	// mysql.releasebooking(A,"12241234","2000","10","20");
    	 //System.out.println(mysql.getIdByNameOfPro("李彬弘"));
    	 for(successbooking abook:mysql.teashowtime("10500001")){
    		 System.out.println(abook.studentid+abook.year+":"+abook.month+":"+abook.day+abook.teacherid+abook.time+abook.instruction);
    	 }
    	
    }
}
