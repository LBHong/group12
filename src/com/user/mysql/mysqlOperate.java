package com.user.mysql;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import com.user.userteacher.teacher;
import com.user.userstudent.student;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
public class mysqlOperate {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://w.rdc.sae.sina.com.cn:3306/app_tianchen1016";
 
    // ���ݿ���û��������룬��Ҫ�����Լ�������
    static final String USER = "z5omyo340o";
    static final String PASS = "j520jylh02zl30jxki4w0zj31k15xj023kj5534y";
	public   Map<String,String> showstudent(String id)
	{
		Connection conn = null;
	    PreparedStatement pstmt = null; 
	    try{
	    	Class.forName("com.mysql.jdbc.Driver");
	    	 conn = DriverManager.getConnection(DB_URL,USER,PASS);
	    	
	    	 String sql;
	    	 sql = "SELECT username,password,email, telephone,instruction,id FROM student where id=?";
	    	 pstmt = conn.prepareStatement(sql);
	    	 pstmt.setString(1, id);
	    	 ResultSet rs = pstmt.executeQuery();
	    	 if(rs.next()==false)
	    	 {
	    		 return null;
	    	 }
	    	 else
	    	 {
	    		return (Map<String, String>) rs;
	    	 }
	    }catch(SQLException se){
	        // ���� JDBC ����
	        se.printStackTrace();
	    }catch(Exception e){
	        // ���� Class.forName ����
	        e.printStackTrace();
	    }finally{
	        // �ر���Դ
	        try{
	            if(conn!=null) conn.close();
	        }catch(SQLException se){
	            se.printStackTrace();
	        }
	    }
		return null;
	
	}
	public   Map<String,String> showteacher(String id)
	{
		Connection conn = null;
	    PreparedStatement pstmt = null; 
	    try{
	    	Class.forName("com.mysql.jdbc.Driver");
	    	 conn = DriverManager.getConnection(DB_URL,USER,PASS);
	    	
	    	 String sql;
	    	 sql = "SELECT username,password,email, telephone,instruction,faculty,id FROM teacher where id=?";
	    	 pstmt = conn.prepareStatement(sql);
	    	 pstmt.setString(1, id);
	    	 ResultSet rs = pstmt.executeQuery();
	    	 if(rs.next()==false)
	    	 {
	    		 return null;
	    	 }
	    	 else
	    	 {
	    		return (Map<String, String>) rs;
	    	 }
	    }catch(SQLException se){
	        // ���� JDBC ����
	        se.printStackTrace();
	    }catch(Exception e){
	        // ���� Class.forName ����
	        e.printStackTrace();
	    }finally{
	        // �ر���Դ
	        try{
	            if(conn!=null) conn.close();
	        }catch(SQLException se){
	            se.printStackTrace();
	        }
	    }
		return null;
	
	}
public boolean login(String password,String id) throws SQLException
{
	
        if(showstudent(id)!=null)
        {
        	ResultSet sr=(ResultSet) showstudent(id);
        	if(password==sr.getString(password))
        	{
        		return true;
        	}
        	else{
        		return false;
        	}
        }
        else if(showteacher(id)!=null)
        {
        	ResultSet sr=(ResultSet) showteacher(id);
        	if(password==sr.getString(password))
        	{
        		return true;
        	}
        	else{
        		return false;
        	}
        }
        else{
        	return false;
        }
}

}