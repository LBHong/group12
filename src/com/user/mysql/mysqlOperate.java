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
 
    // 数据库的用户名与密码，需要根据自己的设置
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
	        // 处理 JDBC 错误
	        se.printStackTrace();
	    }catch(Exception e){
	        // 处理 Class.forName 错误
	        e.printStackTrace();
	    }finally{
	        // 关闭资源
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
	        // 处理 JDBC 错误
	        se.printStackTrace();
	    }catch(Exception e){
	        // 处理 Class.forName 错误
	        e.printStackTrace();
	    }finally{
	        // 关闭资源
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
public List<student> addstudent(String username,String password,String telephone,String email,String id,String instruction)
{
	List studentList=new LinkedList<student>();
	student s=new student();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://w.rdc.sae.sina.com.cn:3306/app_tianchen1016";
			String username1="z5omyo340o";
			String password1="j520jylh02zl30jxki4w0zj31k15xj023kj5534y";
			Connection conn=DriverManager.getConnection(url,username1,password1);
			String sql="insert into student(username,password,email, telephone,instruction,id) values(?,?,?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			s.setUsername(username);
			s.setPassword(password);
			s.setEmail(email);
			s.setTelephone(telephone);
			s.setInstruction(instruction);
			s.setId(id);
			ps.setString(1,s.getUsername());
			ps.setString(2,s.getPassword());
			ps.setString(3,s.getEmail());
			ps.setString(4,s.getTelephone());
			ps.setString(5,s.getInstruction());
			ps.setString(6,s.getId());
			 int row=ps.executeUpdate();
	         if(row>0){
	        	 System.out.println("成功添加数据！");
	         }
	         ps.close();
			conn.close();
		}
		catch(Exception e){
			System.out.print("信息添加失败！");
			e.printStackTrace();
		}
		return null;
}
public void addteacher(String username,String password,String telephone,String email,String id,String faculty,String instruction)
{
	teacher t=new teacher();
	try{
		Class.forName("com.mysql.jdbc.Driver");
		String url="jdbc:mysql://w.rdc.sae.sina.com.cn:3306/app_tianchen1016";
		String username1="z5omyo340o";
		String password1="j520jylh02zl30jxki4w0zj31k15xj023kj5534y";
		Connection conn=DriverManager.getConnection(url,username1,password1);
		String sql="insert into teacher(username,password,email,telephone,instruction,faculty,id) values(?,?,?,?,?,?,?)";
		PreparedStatement ps=conn.prepareStatement(sql);
		t.setUsername(username);
		t.setPassword(password);
		t.setEmail(email);
		t.setTelephone(telephone);
		t.setInstruction(instruction);
		t.setFaculty(faculty);
		t.setId(id);
		ps.setString(1,t.getUsername());
		ps.setString(2,t.getPassword());
		ps.setString(3,t.getEmail());
		ps.setString(4,t.getTelephone());
		ps.setString(5,t.getInstruction());
		ps.setString(6,t.getFaculty());
		ps.setString(7,t.getId());
		 int row=ps.executeUpdate();
         if(row>0){
        	 System.out.println("成功添加数据！");
         }
         ps.close();
		conn.close();
	}
	catch(Exception e){
		System.out.print("信息添加失败！");
		e.printStackTrace();
		}
	}
}