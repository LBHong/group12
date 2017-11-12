/**
 * 
 */
package com.user.mysqloperate;

import java.sql.SQLException;

/**
 * @author lenovo
 *
 */
public class testmysql {
    public static void main(String args[]){
    	 Mysqloperate mysql=new Mysqloperate();
    	 //mysql.addstudent("王春阳", "1234", "188458895380", "wangchunyangsz@163.com", "023456", "计算机学院学生","计算机学院");
    	 //mysql.addteacher("李旭阳", "1234", "188458895380", "lxy@163.com", "120000","计算机学院","计算机学院老师");
    	 //System.out.println(mysql.showstudent("1256"));
    	 //System.out.println(mysql.showteacher("120000"));
		//System.out.println(mysql.login("1234","023456"));
    	// mysql.addprofessornum(3);
    	 //mysql.addstudentnum(2);
    	 System.out.println(mysql.getstudentnum());
    	 System.out.println(mysql.getprofessornum());
    }
}
