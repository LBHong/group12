package com.user.mysqloperate;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.user.userstudent.student;
import com.user.userteacher.teacher;

public class Mysqloperate {

	public Connection getConn() {
	    String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/project";
	    String Qusername = "root";
	    String Qpassword = "";
	    Connection conn = null;
	    try {
	        Class.forName(driver); //classLoader,���ض�Ӧ����
	        conn = (Connection) DriverManager.getConnection(url, Qusername, Qpassword);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
	public student addstudent(String username,String password,String telephone,String email,String id,String instruction)
	{
		student s=new student();
		PreparedStatement pstmt = null;
			try{
				Connection conn=getConn();
				
				String sql = "SELECT username,password,email,telephone,instruction,faculty,id FROM student where id=?";
				pstmt = (PreparedStatement)conn.prepareStatement(sql);
	            pstmt.setString(1, id);
	            ResultSet rs = pstmt.executeQuery();
	         // չ����������ݿ� 	
	            if(!rs.next()){
	            	return null;
	            }
				sql="insert into student(username,password,email, telephone,instruction,id) values(?,?,?,?,?,?)";
				PreparedStatement ps=(PreparedStatement) conn.prepareStatement(sql);
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
		        	 System.out.println("�ɹ����ѧ����");
		         }
		         ps.close();
		         pstmt.close();
				conn.close();
			}
			catch(Exception e){
				System.out.print("��Ϣ���ʧ�ܣ�");
				e.printStackTrace();
			}
			return s;
	}
	public teacher addteacher(String username,String password,String telephone,String email,String id,String faculty,String instruction)
	{
		teacher t=new teacher();
		PreparedStatement pstmt = null;
		try{
			
			//Connection conn=DriverManager.getConnection(url,username1,password1);
			Connection conn=getConn();
			String sql = "SELECT username,password,email,telephone,instruction,faculty,id FROM teacher where id=?";
			pstmt = (PreparedStatement)conn.prepareStatement(sql);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
         // չ����������ݿ� 	
            if(!rs.next()){
            	return null;
            }
			
			sql="insert into teacher(username,password,email,telephone,instruction,faculty,id) values(?,?,?,?,?,?,?)";
			PreparedStatement ps=(PreparedStatement) conn.prepareStatement(sql);
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
	        	 System.out.println("�ɹ������ʦ��");
	         }
	         ps.close();
	         pstmt.close();
			conn.close();
		}
		catch(Exception e){
			System.out.print("��Ϣ���ʧ�ܣ�");
			e.printStackTrace();
			}
		return t;
		}
		
}
