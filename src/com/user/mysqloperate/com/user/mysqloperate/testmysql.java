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
    	 String [] C=new String [14];
    	 Mysqloperate mysql=new Mysqloperate();
    	 C[0]="1";C[1]="1";C[2]="1";C[3]="1";C[4]="1";C[5]="1";C[6]="1";C[7]="1";C[8]="1";
 		C[9]="1";C[10]="1";C[11]="1";C[12]="1";C[13]="0";
 		boolean d;
 		 
 		mysql.teacherdelete("115031050","201","1","1","9:30-10:00");
 		System.out.println("1256");
    	 //mysql.addstudent("锟斤拷锟斤拷锟斤拷", "1234", "188458895380", "wangchunyangsz@163.com", "023456", "锟斤拷锟斤拷锟窖г貉э拷锟�","锟斤拷锟斤拷锟窖г�");
    	 //mysql.addteacher("锟斤拷锟斤拷锟斤拷", "1234", "188458895380", "lxy@163.com", "120000","锟斤拷锟斤拷锟窖г�","锟斤拷锟斤拷锟窖г猴拷锟绞�");
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
    	 //System.out.println(mysql.getIdByNameOfPro("鏉庡浆寮�"));
    	// for(successbooking abook:mysql.teashowtime("10500001")){
    		// System.out.println(abook.studentid+abook.year+":"+abook.month+":"+abook.day+abook.teacherid+abook.time+abook.instruction);
    	 //}
    	 
    }
}
