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
	public   Map<String,String> showstudent(String id)
	{
		Map<String,String> detailMap=new HashMap<String,String>();
		Connection conn = null;
	    PreparedStatement pstmt = null; 
	    try{
	    	
	    	 conn = getconn();
	    	
	    	 String sql;
	    	 sql = "SELECT username,password,email, telephone,instruction,id FROM student where id=?";
	    	 pstmt = conn.prepareStatement(sql);
	    	 pstmt.setString(1, id);
	    	 ResultSet rs = pstmt.executeQuery();
	    	/* ResultSetMetaData m=null;
	    	 m=rs.getMetaData();
	    	 int columns=m.getColumnCount();
	    	 for(int i=1;i<=columns;i++)
	    	   {
	    	    System.out.print(m.getColumnName(i));
	    	    System.out.print("\t\t");
	    	   }*/
	    	 while(rs.next())
	    	 {
	    		detailMap.put("�û���",rs.getString("username"));
	    		detailMap.put("����",rs.getString("password"));
	    		detailMap.put("����",rs.getString("email"));
	    		detailMap.put("�ֻ���",rs.getString("telephone"));
	    		detailMap.put("����",rs.getString("instruction"));
	    		detailMap.put("id",rs.getString("id"));		 
	    	 }
	    	 if(rs.next()==false)
	    	 {
	    		 return null;
	    	 }
	    	 else
	    	 {
	    		return detailMap;
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
		Map<String,String> detailMap=new HashMap<String,String>();
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
	    	/* ResultSetMetaData m=null;
	    	 m=rs.getMetaData();
	    	 int columns=m.getColumnCount();
	    	 for(int i=1;i<=columns;i++)
	    	   {
	    	    System.out.print(m.getColumnName(i));
	    	    System.out.print("\t\t");
	    	   }
	    	 while(rs.next())
	    	 {
	    		 for(int i=1;i<=columns;i++)
	    		    {
	    		     System.out.print(rs.getString(i));
	    		     System.out.print("\t\t");
	    		    }
	    		 
	    	 }*/
	    	 while(rs.next())
	    	 {
	    		detailMap.put("�û���",rs.getString("username"));
	    		detailMap.put("����",rs.getString("password"));
	    		detailMap.put("����",rs.getString("email"));
	    		detailMap.put("�ֻ���",rs.getString("telephone"));
	    		detailMap.put("����",rs.getString("instruction"));
	    		detailMap.put("��Ŀ",rs.getString("faculty"));
	    		detailMap.put("id",rs.getString("id"));		 
	    	 }
	    	 if(rs.next()==false)
	    	 {
	    		 return null;
	    	 }
	    	 else
	    	 {
	    		return detailMap;
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

	public Map<String,String> login(String password,String id) throws SQLException
	{
		Map<String,String> detailMap=new HashMap<String,String>();
	        if(id[0]=='0')
	        {
	        	detailMap=showstudent(id);
	        	String key="����";		
	        	String value=detailMap.get(key);
	            if(password==value)
	        	{
	        		return detailmap;
	        	}
	            else{
	        			return null;
	        	}
	        			       	
	        }
	        else if(id[0]=='1')
	        {
	        	/*ResultSet sr=(ResultSet) showteacher(id);
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
	        }*/
	        	detailMap=showteacher(id);
	        	String key="����";
	        	String value=detailMap.get(key);
	        	if(password==value)
	        	{
	        		return detailmap;
	        	}
	        	else{
	        			return null;
	        	}
	        }
			return false;
	}

}
