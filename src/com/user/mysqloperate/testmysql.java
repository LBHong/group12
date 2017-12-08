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
 		 
 		mysql.addgrade("115031050","2018","1","1","16:30-17:00","12",0);
 		System.out.println("1256");
    	 //mysql.addstudent("閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷�", "1234", "188458895380", "wangchunyangsz@163.com", "023456", "閿熸枻鎷烽敓鏂ゆ嫹閿熺獤谐璨壯嶆嫹閿燂拷","閿熸枻鎷烽敓鏂ゆ嫹閿熺獤谐锟�");
    	 //mysql.addteacher("閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷�", "1234", "188458895380", "lxy@163.com", "120000","閿熸枻鎷烽敓鏂ゆ嫹閿熺獤谐锟�","閿熸枻鎷烽敓鏂ゆ嫹閿熺獤谐鐚存嫹閿熺粸锟�");
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
    	 //System.out.println(mysql.getIdByNameOfPro("閺夊骸娴嗗锟�"));
    	// for(successbooking abook:mysql.teashowtime("10500001")){
    		// System.out.println(abook.studentid+abook.year+":"+abook.month+":"+abook.day+abook.teacherid+abook.time+abook.instruction);
    	 //}
    	 
    }
}
