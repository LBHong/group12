package com.user.mysqloperate;

import com.user.releasebooking.releasebooking;
import com.user.successbooking.successbooking;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.user.userstudent.student;
import com.user.userteacher.teacher;

public class Mysqloperate {

	public Connection getConn() {
	    String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/project";
	    String Qusername = "root";
	    String Qpassword = "1234";//1234
	    Connection conn = null;
	    try {
	    	Class.forName(driver);
	        conn = (Connection) DriverManager.getConnection(url, Qusername, Qpassword);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
	public student addstudent(String username,String password,String telephone,String email,String id,String instruction,String faculty)
	{
		student s=new student();
		PreparedStatement pstmt = null;
			try{
				  System.out.println("0");
				Connection conn=getConn();
				
				String sql = "SELECT username,password,email,telephone,instruction,faculty,id FROM student where id=?";
				pstmt = (PreparedStatement)conn.prepareStatement(sql);
	            pstmt.setString(1, id);
	            ResultSet rs = pstmt.executeQuery();
	            System.out.println("1");
	         // 展开结果集数据库 	
	            if(rs.next()){
	            	return null;
	            }
	            System.out.println("2");
				sql="insert into student(username,password,email, telephone,instruction,id,faculty) values(?,?,?,?,?,?,?)";
				PreparedStatement ps=(PreparedStatement) conn.prepareStatement(sql);
				s.setUsername(username);
				s.setPassword(password);
				s.setEmail(email);
				s.setTelephone(telephone);
				s.setInstruction(instruction);
				s.setId(id);
				s.setFaculty(faculty);
				ps.setString(1,s.getUsername());
				ps.setString(2,s.getPassword());
				ps.setString(3,s.getEmail());
				ps.setString(4,s.getTelephone());
				ps.setString(5,s.getInstruction());
				ps.setString(6,s.getId());
				ps.setString(7,s.getFaculty());
				 int row=ps.executeUpdate();
		         if(row>0){
		        	 System.out.println("成功添加学生！");
		         }
		         System.out.println("3");
		         ps.close();
		         pstmt.close();
				conn.close();
			}
			catch(Exception e){
				System.out.print("信息添加失败！");
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
            if(rs.next()){
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
	        	 System.out.println("添加老师成功");
	         }
	         ps.close();
	         pstmt.close();
			conn.close();
		}
		catch(Exception e){
			System.out.print("添加老师失败");
			e.printStackTrace();
			}
		return t;
		}
	public   Map<String,String> showstudent(String id)
	{
		Map<String,String> detailMap=new HashMap<String,String>();
		Connection conn = null;
	    java.sql.PreparedStatement pstmt = null; 
	    try{
	    	
	    	 conn = getConn();
	    	
	    	 String sql;
	    	 sql = "SELECT username,password,email, telephone,instruction,id,faculty FROM student where id=?";
	    	 pstmt = conn.prepareStatement(sql);
	    	 pstmt.setString(1, id);
	    	 ResultSet rs = pstmt.executeQuery();
	    	
	    	 if(!rs.next())
	    	 {
	    		 return null;
	    	 }
	    	 else{
	    		    detailMap.put("用户名",rs.getString("username"));
		    		detailMap.put("密码",rs.getString("password"));
		    		detailMap.put("邮箱",rs.getString("email"));
		    		detailMap.put("手机号",rs.getString("telephone"));
		    		detailMap.put("介绍",rs.getString("instruction"));
		    		detailMap.put("id",rs.getString("id")); 
		    		detailMap.put("学院",rs.getString("faculty")); 
	    	        return detailMap;
	    	 }
	    }catch(SQLException se){
	      
	        se.printStackTrace();
	    }catch(Exception e){
	        e.printStackTrace();
	    }finally{
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
	    java.sql.PreparedStatement pstmt = null; 
	    try{
	    	
	    	 conn =getConn();
	    	
	    	 String sql;
	    	 sql = "SELECT username,password,email, telephone,instruction,faculty,id FROM teacher where id=?";
	    	 pstmt = conn.prepareStatement(sql);
	    	 pstmt.setString(1, id);
	    	 ResultSet rs = pstmt.executeQuery();
	    
	    	 if(!rs.next())
	    	 {
	    		  System.out.println("haha");
	    		 return null;
	    	 }
	    	 else
	    	 {
	    		   detailMap.put("用户名",rs.getString("username"));
		    		detailMap.put("密码",rs.getString("password"));
		    		detailMap.put("邮箱",rs.getString("email"));
		    		detailMap.put("手机号",rs.getString("telephone"));
		    		detailMap.put("介绍",rs.getString("instruction"));
		    		detailMap.put("科目",rs.getString("faculty"));
		    		detailMap.put("id",rs.getString("id"));	
		    		 System.out.println("no");
	    		return detailMap;
	    	 }
	    	 
	    }catch(SQLException se){
	        // 锟斤拷锟斤拷 JDBC 锟斤拷锟斤拷
	        se.printStackTrace();
	    }catch(Exception e){
	        // 锟斤拷锟斤拷 Class.forName 锟斤拷锟斤拷
	        e.printStackTrace();
	    }finally{
	        // 锟截憋拷锟斤拷源
	        try{
	            if(conn!=null) conn.close();
	        }catch(SQLException se){
	            se.printStackTrace();
	        }
	    }
		return null;
	
	}

	public String login(String password,String id)
	{
		Map<String,String> detailMap;
	        if(id.charAt(0)=='0')
	        {
	        	detailMap=showstudent(id);
	        	if(detailMap==null){
	        		System.out.println("h");
	        		return "wrong_id";
	        	}
	        	else{
	        		String key="密码";		
		        	String value=detailMap.get(key);
		            if(password.equals(value))
		        	{
		        		return "student_success";
		        	}
		            else{
		            	System.out.println("r");
		        			return "wrong_password";
		        	}
	        	}       	
	        }
	        else if(id.charAt(0)=='1')
	        {
	        	detailMap=showteacher(id);
	        	if(detailMap==null){
	        		System.out.println("h");
	        		return "wrong_id";
	        	}
	        	else{
	        		String key="密码";		
		        	String value=detailMap.get(key);
		            if(password.equals(value))
		        	{
		        		return "teacher_success";
		        	}
		            else{
		            	System.out.println("r");
		        			return "wrong_password";
		        	}
	        	}       
	        }else{
	            return "wrong_id";
	        }
	    
	}
   public int getstudentnum(){
	   Connection conn = null;
	    java.sql.PreparedStatement pstmt = null; 
	    try{
	    	
	    	 conn =getConn();
	    	
	    	 String sql;
	    	 sql = "SELECT studentnum FROM console";
	    	 pstmt = conn.prepareStatement(sql);
	    	 ResultSet rs = pstmt.executeQuery();
	    	 if(!rs.next())
	    	 {
	    		 return -1;
	    	 } else{
	    		 return rs.getInt("studentnum");
	    	 }
	    }catch(SQLException se){
	        se.printStackTrace();
	    }catch(Exception e){
	        e.printStackTrace();
	    }finally{
	        try{
	            if(conn!=null) conn.close();
	        }catch(SQLException se){
	            se.printStackTrace();
	        }
	    }
	    return -1;
   }
   public int getprofessornum(){
	   Connection conn = null;
	    java.sql.PreparedStatement pstmt = null; 
	    try{
	    	
	    	 conn =getConn();
	    	
	    	 String sql;
	    	 sql = "SELECT professornum FROM console";
	    	 pstmt = conn.prepareStatement(sql);
	    	 ResultSet rs = pstmt.executeQuery();
	    	 if(!rs.next())
	    	 {
	    		 return -1;
	    	 } else{
	    		 return rs.getInt("professornum");
	    	 }
	    }catch(SQLException se){
	        se.printStackTrace();
	    }catch(Exception e){
	        e.printStackTrace();
	    }finally{
	        try{
	            if(conn!=null) conn.close();
	        }catch(SQLException se){
	            se.printStackTrace();
	        }
	    }
	    return -1;
   }
   public Boolean addprofessornum(int addnum){
	   Connection conn = null;
	    java.sql.PreparedStatement pstmt = null; 
	    Boolean flag=false;
	    try{
	    	
	    	 conn =getConn();
	    	 int nownum= getprofessornum();
	    	 int newnum=nownum+addnum;
	    	 
	    	 String sql;
	    	 sql = "UPDATE console SET professornum="+newnum+" WHERE professornum="+nownum+";";
	    	 
	    	 pstmt = conn.prepareStatement(sql);
	    	 int rowsAffected = pstmt.executeUpdate();
	        
	    	 if(rowsAffected>0){
					flag=true;
				}
	    }catch(SQLException se){
	        se.printStackTrace();
	    }catch(Exception e){
	        e.printStackTrace();
	    }finally{
	        try{
	            if(conn!=null) conn.close();
	        }catch(SQLException se){
	            se.printStackTrace();
	        }
	    }
	    return flag;
   }
   public Boolean addstudentnum(int addnum){
	   Connection conn = null;
	    java.sql.PreparedStatement pstmt = null; 
	    Boolean flag=false;
	    try{
	    	
	    	 conn =getConn();
	    	 int nownum= getstudentnum();
	    	 int newnum=nownum+addnum;
	    	 
	    	 String sql;
	    	 sql = "UPDATE console SET studentnum="+newnum+" WHERE studentnum="+nownum+";";
	    	 
	    	 pstmt = conn.prepareStatement(sql);
	    	 int rowsAffected = pstmt.executeUpdate();
	        
	    	 if(rowsAffected>0){
					flag=true;
				}
	    }catch(SQLException se){
	        se.printStackTrace();
	    }catch(Exception e){
	        e.printStackTrace();
	    }finally{
	        try{
	            if(conn!=null) conn.close();
	        }catch(SQLException se){
	            se.printStackTrace();
	        }
	    }
	    return flag;
   }
   public  ArrayList<String> getIdByNameOfPro(String username){
	   Connection conn = null;
	    java.sql.PreparedStatement pstmt = null; 
	    ArrayList<String>idlist=new ArrayList<>();
	    try{
	    	
	    	 conn = getConn();
	    	 String sql;
	    	 sql = "SELECT id FROM teacher where username=?";
	    	 pstmt = conn.prepareStatement(sql);
	    	 pstmt.setString(1, username);
	    	 ResultSet rs = pstmt.executeQuery();
	    	 if(!rs.next())
	    	 {
	    		return null;
	    	 }else{
	    		 do{
	    			 System.out.println(rs.getString("id"));
	    			 idlist.add(rs.getString("id"));
	    		 }while(rs.next());
	    		 return idlist;
	    	 }
	    }catch(SQLException se){
		      
	        se.printStackTrace();
	    }catch(Exception e){
	        e.printStackTrace();
	    }finally{
	        try{
	            if(conn!=null) conn.close();
	        }catch(SQLException se){
	            se.printStackTrace();
	        }
	    }
		return null;
   }
   public boolean releasebooking(String A[],String id,String year,String month,String day)
	{
		 char [] B=new char [14];
		 B[0]='a';B[1]='b';B[2]='c';B[3]='d';B[4]='e';B[5]='f';B[6]='g';B[7]='h';B[8]='i';
		 B[9]='j';B[10]='k';B[11]='l';B[12]='m';B[13]='n';
	
		try{
			Connection conn=getConn();
			
			//String sql="replace into release booking(id,year,month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			String sql = "SELECT id, year, month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM releasebooking where id=? and year=? and month=? and day=?";
			java.sql.PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,id);
			ps.setString(2, year);
			ps.setString(3, month);
			ps.setString(4,day);
			ResultSet rs = ps.executeQuery();
			 
			int i;
			if(rs.next())
			{   
				
				for(i=0;i<14;i++)
				{    
					if(A[i].equals("0")){ }
					else {
						
						if(B[i]=='a')
						{	String sq="SELECT id, year, month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM  releasebooking where id=? and year=? and month=? and day=? and a=?";
							PreparedStatement sp=(PreparedStatement) conn.prepareStatement(sq);
							sp.setString(1, id);sp.setString(2, year);
							sp.setString(3, month);sp.setString(4, day);
							sp.setString(5, "2");ResultSet sr = ps.executeQuery();
							if(sr.next()){}
							else{String qs="update releasebooking set a=? where id=? and year=? and month=? and day=?";
							PreparedStatement pss=(PreparedStatement)conn.prepareStatement(qs);
							pss.setString(1, A[0]);
							pss.setString(2, id);
							pss.setString(3, year);
							pss.setString(4, month);
							pss.setString(5, day);
							pss.executeUpdate();
							pss.close();}
							sr.close();
							sp.close();
						}
						else if(B[i]=='b')
						{
							String sq="SELECT id, year, month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM  releasebooking where id=? and year=? and month=? and day=? and b=?";
							PreparedStatement sp=(PreparedStatement)conn.prepareStatement(sq);
							sp.setString(1, id);sp.setString(2, year);
							sp.setString(3, month);sp.setString(4, day);
							sp.setString(5, "2");ResultSet sr = ps.executeQuery();
							if(sr.next()){}
							else{
							String qs="update releasebooking set b=? where id=? and year=? and month=? and day=?";
							PreparedStatement pss=(PreparedStatement)conn.prepareStatement(qs);
							pss.setString(1, A[1]);
							pss.setString(2, id);
							pss.setString(3, year);
							pss.setString(4, month);
							pss.setString(5, day);pss.executeUpdate();
							pss.close();}
							sr.close();
							sp.close();
						}
						else if(B[i]=='c')
						{	
							String sq="SELECT id, year, month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM  releasebooking where id=? and year=? and month=? and day=? and c=?";
							PreparedStatement sp=(PreparedStatement)conn.prepareStatement(sq);
							sp.setString(1, id);sp.setString(2, year);
							sp.setString(3, month);sp.setString(4, day);
							sp.setString(5, "2");ResultSet sr = ps.executeQuery();
							if(sr.next()){}
							else{
							String qs="update releasebooking set c=? where id=? and year=? and month=? and day=?";
							PreparedStatement pss=(PreparedStatement)conn.prepareStatement(qs);
							pss.setString(1, A[2]);
							pss.setString(2, id);
							pss.setString(3, year);
							pss.setString(4, month);
							pss.setString(5, day);pss.executeUpdate();
							pss.close();}
							sr.close();
							sp.close();
						}
						else if(B[i]=='d')
						{
							String sq="SELECT id, year, month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM  releasebooking where id=? and year=? and month=? and day=? and d=?";
							PreparedStatement sp=(PreparedStatement)conn.prepareStatement(sq);
							sp.setString(1, id);sp.setString(2, year);
							sp.setString(3, month);sp.setString(4, day);
							sp.setString(5, "2");ResultSet sr = ps.executeQuery();
							if(sr.next()){}
							else{
							String qs="update releasebooking set d=? where id=? and year=? and month=? and day=?";
							PreparedStatement pss=(PreparedStatement)conn.prepareStatement(qs);
							pss.setString(1, A[3]);
							pss.setString(2, id);
							pss.setString(3, year);
							pss.setString(4, month);
							pss.setString(5, day);pss.executeUpdate();
							pss.close();}
							sr.close();
							sp.close();
						}
						else if(B[i]=='e')
						{
							String sq="SELECT id, year, month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM  releasebooking where id=? and year=? and month=? and day=? and e=?";
							PreparedStatement sp=(PreparedStatement)conn.prepareStatement(sq);
							sp.setString(1, id);sp.setString(2, year);
							sp.setString(3, month);sp.setString(4, day);
							sp.setString(5, "2");ResultSet sr = ps.executeQuery();
							if(sr.next()){}
							else{
							String qs="update releasebooking set e=? where id=? and year=? and month=? and day=?";
							PreparedStatement pss=(PreparedStatement)conn.prepareStatement(qs);
							pss.setString(1, A[4]);
							pss.setString(2, id);
							pss.setString(3, year);
							pss.setString(4, month);
							pss.setString(5, day);pss.executeUpdate();
							pss.close();}
							sr.close();
							sp.close();
						}
						else if(B[i]=='f')
						{
							String sq="SELECT id, year, month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM  releasebooking where id=? and year=? and month=? and day=? and f=?";
							PreparedStatement sp=(PreparedStatement)conn.prepareStatement(sq);
							sp.setString(1, id);sp.setString(2, year);
							sp.setString(3, month);sp.setString(4, day);
							sp.setString(5, "2");ResultSet sr = ps.executeQuery();
							if(sr.next()){}
							else{
							String qs="update releasebooking set f=? where id=? and year=? and month=? and day=?";
							PreparedStatement pss=(PreparedStatement)conn.prepareStatement(qs);
							pss.setString(1, A[5]);
							pss.setString(2, id);
							pss.setString(3, year);
							pss.setString(4, month);
							pss.setString(5, day);pss.executeUpdate();
							pss.close();}
							sr.close();
							sp.close();
						}
						else if(B[i]=='g')
						{
							String sq="SELECT id, year, month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM  releasebooking where id=? and year=? and month=? and day=? and g=?";
							PreparedStatement sp=(PreparedStatement)conn.prepareStatement(sq);
							sp.setString(1, id);sp.setString(2, year);
							sp.setString(3, month);sp.setString(4, day);
							sp.setString(5, "2");ResultSet sr = ps.executeQuery();
							if(sr.next()){}
							else{
							String qs="update releasebooking set g=? where id=? and year=? and month=? and day=?";
							PreparedStatement pss=(PreparedStatement)conn.prepareStatement(qs);
							pss.setString(1, A[6]);
							pss.setString(2, id);
							pss.setString(3, year);
							pss.setString(4, month);
							pss.setString(5, day);pss.executeUpdate();
							pss.close();}
							sr.close();
							sp.close();
						}
						else if(B[i]=='h')
						{	
							String sq="SELECT id, year, month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM  releasebooking where id=? and year=? and month=? and day=? and h=?";
							PreparedStatement sp=(PreparedStatement)conn.prepareStatement(sq);
							sp.setString(1, id);sp.setString(2, year);
							sp.setString(3, month);sp.setString(4, day);
							sp.setString(5, "2");ResultSet sr = ps.executeQuery();
							if(sr.next()){}
							else{
							String qs="update releasebooking set h=? where id=? and year=? and month=? and day=?";
							PreparedStatement pss=(PreparedStatement)conn.prepareStatement(qs);
							pss.setString(1, A[7]);
							pss.setString(2, id);
							pss.setString(3, year);
							pss.setString(4, month);
							pss.setString(5, day);pss.executeUpdate();
							pss.close();}
							sr.close();
							sp.close();
						}
						else if(B[i]=='i')
						{
							String sq="SELECT id, year, month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM  releasebooking where id=? and year=? and month=? and day=? and i=?";
							PreparedStatement sp=(PreparedStatement)conn.prepareStatement(sq);
							sp.setString(1, id);sp.setString(2, year);
							sp.setString(3, month);sp.setString(4, day);
							sp.setString(5, "2");ResultSet sr = ps.executeQuery();
							if(sr.next()){}
							else{
							String qs="update releasebooking set i=? where id=? and year=? and month=? and day=?";
							PreparedStatement pss=(PreparedStatement)conn.prepareStatement(qs);
							pss.setString(1, A[8]);
							pss.setString(2, id);
							pss.setString(3, year);
							pss.setString(4, month);
							pss.setString(5, day);pss.executeUpdate();
							pss.close();}
							sr.close();
							sp.close();
							
						}
						else if(B[i]=='j')
						{
							String sq="SELECT id, year, month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM  releasebooking where id=? and year=? and month=? and day=? and j=?";
							PreparedStatement sp=(PreparedStatement)conn.prepareStatement(sq);
							sp.setString(1, id);sp.setString(2, year);
							sp.setString(3, month);sp.setString(4, day);
							sp.setString(5, "2");ResultSet sr = ps.executeQuery();
							if(sr.next()){}
							else{
							String qs="update releasebooking set j=? where id=? and year=? and month=? and day=?";
							PreparedStatement pss=(PreparedStatement)conn.prepareStatement(qs);
							pss.setString(1, A[9]);
							pss.setString(2, id);
							pss.setString(3, year);
							pss.setString(4, month);
							pss.setString(5, day);pss.executeUpdate();
							pss.close();}
							sr.close();
							sp.close();
						}
						else if(B[i]=='k')
						{
							String sq="SELECT id, year, month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM  releasebooking where id=? and year=? and month=? and day=? and k=?";
							PreparedStatement sp=(PreparedStatement)conn.prepareStatement(sq);
							sp.setString(1, id);sp.setString(2, year);
							sp.setString(3, month);sp.setString(4, day);
							sp.setString(5, "2");ResultSet sr = ps.executeQuery();
							if(sr.next()){}
							else{
							String qs="update releasebooking set k=? where id=? and year=? and month=? and day=?";
							PreparedStatement pss=(PreparedStatement)conn.prepareStatement(qs);
							pss.setString(1, A[10]);
							pss.setString(2, id);
							pss.setString(3, year);
							pss.setString(4, month);
							pss.setString(5, day);pss.executeUpdate();
							pss.close();}
							sr.close();
							sp.close();
						}
						else if(B[i]=='l')
						{
							String sq="SELECT id, year, month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM  releasebooking where id=? and year=? and month=? and day=? and l=?";
							PreparedStatement sp=(PreparedStatement)conn.prepareStatement(sq);
							sp.setString(1, id);sp.setString(2, year);
							sp.setString(3, month);sp.setString(4, day);
							sp.setString(5, "2");ResultSet sr = ps.executeQuery();
							if(sr.next()){}
							else{
							String qs="update releasebooking set l=? where id=? and year=? and month=? and day=?";
							PreparedStatement pss=(PreparedStatement)conn.prepareStatement(qs);
							pss.setString(1, A[11]);
							pss.setString(2, id);
							pss.setString(3, year);
							pss.setString(4, month);
							pss.setString(5, day);pss.executeUpdate();
							pss.close();}
							sr.close();
							sp.close();
						}
						else if(B[i]=='m')
						{
							
							String sq="SELECT id, year, month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM  releasebooking where id=? and year=? and month=? and day=? and m=?";
							PreparedStatement sp=(PreparedStatement)conn.prepareStatement(sq);
							sp.setString(1, id);sp.setString(2, year);
							sp.setString(3, month);sp.setString(4, day);
							sp.setString(5, "2");ResultSet sr = ps.executeQuery();
							if(sr.next()){}
							else{
							String qs="update releasebooking set m=? where id=? and year=? and month=? and day=?";
							PreparedStatement pss=(PreparedStatement)conn.prepareStatement(qs);
							pss.setString(1, A[12]);
							pss.setString(2, id);
							pss.setString(3, year);
							pss.setString(4, month);
							pss.setString(5, day);pss.executeUpdate();
							pss.close();}
							
							sr.close();
							sp.close();
						}
						else if(B[i]=='n')
						{
							String sq="SELECT id, year, month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM  releasebooking where id=? and year=? and month=? and day=? and n=?";
							PreparedStatement sp=(PreparedStatement)conn.prepareStatement(sq);
							sp.setString(1, id);sp.setString(2, year);
							sp.setString(3, month);sp.setString(4, day);
							sp.setString(5, "2");ResultSet sr = ps.executeQuery();
							if(sr.next()){}
							else{
							String qs="update releasebooking set n=? where id=? and year=? and month=? and day=?";
							PreparedStatement pss=(PreparedStatement)conn.prepareStatement(qs);
							pss.setString(1, A[13]);
							pss.setString(2, id);
							pss.setString(3, year);
							pss.setString(4, month);
							pss.setString(5, day);pss.executeUpdate();
							pss.close();}
							sr.close();
							sp.close();
								}
							}
					}
				}
			
			else{
				String sq="insert into releasebooking (id,year,month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				java.sql.PreparedStatement sp=conn.prepareStatement(sq);
				
				sp.setString(1, id);sp.setString(2, year);sp.setString(3, month);
				sp.setString(4,day);sp.setString(5,A[0]);sp.setString(6, A[1]);
				sp.setString(7, A[2]);sp.setString(8,A[3]);sp.setString(9, A[4]);
				sp.setString(10, A[5]);sp.setString(11, A[6]);sp.setString(12, A[7]);
				sp.setString(13, A[8]);sp.setString(14, A[9]);sp.setString(15, A[10]);
				sp.setString(16, A[11]);sp.setString(17, A[12]);sp.setString(18, A[13]);
				int row=sp.executeUpdate();
				sp.close();
				ps.close();
				conn.close();
				}
		}
			catch(Exception e){
				System.out.print("信息添加失败！");
				e.printStackTrace();
			}
			
		return true;		
	}
   public boolean teacherdelete(String teacherid,String year,String month,String day,String time)
   {
	   String [] B=new String [15];int qq;
		 B[0]="8:00-8:30";B[1]="8:30-9:00";B[2]="9:00-9:30";B[3]="9:30-10:00";B[4]="10:00-10:30";
		 B[5]="10:30-11:00";B[6]="11:00-11:30";B[7]="14:00-14:30";B[8]="14:30-15:00";B[9]="15:00-15:30";
		 B[10]="15:30-16:00";B[11]="16:00-16:30";B[12]="16:30-17:00";B[13]="17:00:17:30";B[14]="1";
	   Connection conn = null;
	   try{
		   conn=getConn();
		   if(time.equals(B[0])){
				String sql="update releasebooking set a=? where id=? and year=? and month=? and day=? ";
				PreparedStatement pre=(PreparedStatement)conn.prepareStatement(sql);
				pre.setString(1, "0");pre.setString(2,teacherid);
				pre.setString(3, year);pre.setString(4, month);
				pre.setString(5, day);qq = pre.executeUpdate();pre.close();
			}
	   
	   else if(time.equals(B[1])){
			String sql="update releasebooking set b=? where id=? and year=? and month=? and day=? ";
			PreparedStatement pre=(PreparedStatement)conn.prepareStatement(sql);
			pre.setString(1, "0");pre.setString(2,teacherid);
			pre.setString(3, year);pre.setString(4, month);
			pre.setString(5, day);qq = pre.executeUpdate();pre.close();
		}
	   else if(time.equals(B[2])){
			String sql="update releasebooking set c=? where id=? and year=? and month=? and day=? ";
			PreparedStatement pre=(PreparedStatement)conn.prepareStatement(sql);
			pre.setString(1, "0");pre.setString(2,teacherid);
			pre.setString(3, year);pre.setString(4, month);
			pre.setString(5, day);qq = pre.executeUpdate();pre.close();
		}
	   else if(time.equals(B[3])){
			String sql="update releasebooking set d=? where id=? and year=? and month=? and day=? ";
			PreparedStatement pre=(PreparedStatement)conn.prepareStatement(sql);
			pre.setString(1, "0");pre.setString(2,teacherid);
			pre.setString(3, year);pre.setString(4, month);
			pre.setString(5, day);qq = pre.executeUpdate();pre.close();
		}
	   else if(time.equals(B[4])){
			String sql="update releasebooking set e=? where id=? and year=? and month=? and day=? ";
			PreparedStatement pre=(PreparedStatement)conn.prepareStatement(sql);
			pre.setString(1, "0");pre.setString(2,teacherid);
			pre.setString(3, year);pre.setString(4, month);
			pre.setString(5, day);qq = pre.executeUpdate();pre.close();
		}
	   else if(time.equals(B[5])){
			String sql="update releasebooking set f=? where id=? and year=? and month=? and day=? ";
			PreparedStatement pre=(PreparedStatement)conn.prepareStatement(sql);
			pre.setString(1, "0");pre.setString(2,teacherid);
			pre.setString(3, year);pre.setString(4, month);
			pre.setString(5, day);qq = pre.executeUpdate();pre.close();
		}
	   else if(time.equals(B[6])){
			String sql="update releasebooking set g=? where id=? and year=? and month=? and day=? ";
			PreparedStatement pre=(PreparedStatement)conn.prepareStatement(sql);
			pre.setString(1, "0");pre.setString(2,teacherid);
			pre.setString(3, year);pre.setString(4, month);
			pre.setString(5, day);qq = pre.executeUpdate();pre.close();
		}
	   else if(time.equals(B[7])){
			String sql="update releasebooking set h=? where id=? and year=? and month=? and day=? ";
			PreparedStatement pre=(PreparedStatement)conn.prepareStatement(sql);
			pre.setString(1, "0");pre.setString(2,teacherid);
			pre.setString(3, year);pre.setString(4, month);
			pre.setString(5, day);qq = pre.executeUpdate();pre.close();
		}
	   else if(time.equals(B[8])){
			String sql="update releasebooking set i=? where id=? and year=? and month=? and day=? ";
			PreparedStatement pre=(PreparedStatement)conn.prepareStatement(sql);
			pre.setString(1, "0");pre.setString(2,teacherid);
			pre.setString(3, year);pre.setString(4, month);
			pre.setString(5, day);qq = pre.executeUpdate();pre.close();
		}
	   else if(time.equals(B[9])){
			String sql="update releasebooking set j=? where id=? and year=? and month=? and day=? ";
			PreparedStatement pre=(PreparedStatement)conn.prepareStatement(sql);
			pre.setString(1, "0");pre.setString(2,teacherid);
			pre.setString(3, year);pre.setString(4, month);
			pre.setString(5, day);qq = pre.executeUpdate();pre.close();
		}
	   else if(time.equals(B[10])){
			String sql="update releasebooking set k=? where id=? and year=? and month=? and day=? ";
			PreparedStatement pre=(PreparedStatement)conn.prepareStatement(sql);
			pre.setString(1, "0");pre.setString(2,teacherid);
			pre.setString(3, year);pre.setString(4, month);
			pre.setString(5, day);qq = pre.executeUpdate();pre.close();
		}
	   else if(time.equals(B[11])){
			String sql="update releasebooking set l=? where id=? and year=? and month=? and day=? ";
			PreparedStatement pre=(PreparedStatement)conn.prepareStatement(sql);
			pre.setString(1, "0");pre.setString(2,teacherid);
			pre.setString(3, year);pre.setString(4, month);
			pre.setString(5, day);qq = pre.executeUpdate();pre.close();
		}
	   else if(time.equals(B[12])){
			String sql="update releasebooking set m=? where id=? and year=? and month=? and day=? ";
			PreparedStatement pre=(PreparedStatement)conn.prepareStatement(sql);
			pre.setString(1, "0");pre.setString(2,teacherid);
			pre.setString(3, year);pre.setString(4, month);
			pre.setString(5, day);qq = pre.executeUpdate();pre.close();
		}
	   else {
			String sql="update releasebooking set n=? where id=? and year=? and month=? and day=? ";
			PreparedStatement pre=(PreparedStatement)conn.prepareStatement(sql);
			pre.setString(1, "0");pre.setString(2,teacherid);
			pre.setString(3, year);pre.setString(4, month);
			pre.setString(5, day);qq = pre.executeUpdate();pre.close();
		}
		   String sq="DELETE FROM successbooking where teacherid=? and year=? and month=? and day=? and time=?";
			 PreparedStatement pp=(PreparedStatement)conn.prepareStatement(sq);
			 pp.setString(1,teacherid);
			 pp.setString(2, year);
			 pp.setString(3, month);
			 pp.setString(4, day);pp.setString(5, time);
				int sr = pp.executeUpdate();
				pp.close();
	   }catch(SQLException se){
           // 处理 JDBC 错误
           se.printStackTrace();
       }catch(Exception e){
           // 处理 Class.forName 错误
           e.printStackTrace();
       }
	   return false;
	   
   }
   public boolean studentdelete(String year,String month,String day,String time,String studentid)
   {
	   String [] B=new String [15];int qq;
		 B[0]="8:00-8:30";B[1]="8:30-9:00";B[2]="9:00-9:30";B[3]="9:30-10:00";B[4]="10:00-10:30";
		 B[5]="10:30-11:00";B[6]="11:00-11:30";B[7]="14:00-14:30";B[8]="14:30-15:00";B[9]="15:00-15:30";
		 B[10]="15:30-16:00";B[11]="16:00-16:30";B[12]="16:30-17:00";B[13]="17:00:17:30";B[14]="1";
	   Connection conn = null;
	   try{
		   conn=getConn();
		   
				   String qs="SELECT teacherid,year,month,day,time,studentid,instruction FROM successbooking where year=? and month=? and day=? and time=? and studentid=?";
				   
				   PreparedStatement ppp=(PreparedStatement)conn.prepareStatement(qs);
				   ppp.setString(1,year);ppp.setString(2, month);
				   ppp.setString(3, day);ppp.setString(4, time);ppp.setString(5, studentid);
					ResultSet rs = ppp.executeQuery();
					rs.next();
					
					String teacher=rs.getString("teacherid");System.out.println(teacher);
					rs.close();ppp.close();
					if(time.equals(B[0])){
						String sql="update releasebooking set a=? where id=? and year=? and month=? and day=? ";
						PreparedStatement pre=(PreparedStatement)conn.prepareStatement(sql);
						pre.setString(1, "1");pre.setString(2,teacher);
						pre.setString(3, year);pre.setString(4, month);
						pre.setString(5, day);qq = pre.executeUpdate();pre.close();
					}
					else if(time.equals(B[1])){
						String sql="update releasebooking set b=? where id=? and year=? and month=? and day=? ";
						PreparedStatement pre=(PreparedStatement)conn.prepareStatement(sql);
						pre.setString(1, "1");pre.setString(2,teacher);
						pre.setString(3, year);pre.setString(4, month);
						pre.setString(5, day);qq = pre.executeUpdate();pre.close();
					}
					else if(time.equals(B[2])){
						String sql="update releasebooking set c=? where id=? and year=? and month=? and day=? ";
						PreparedStatement pre=(PreparedStatement)conn.prepareStatement(sql);
						pre.setString(1, "1");pre.setString(2,teacher);
						pre.setString(3, year);pre.setString(4, month);
						pre.setString(5, day);qq = pre.executeUpdate();pre.close();
					}
					else if(time.equals(B[3])){
						String sql="update releasebooking set d=? where id=? and year=? and month=? and day=? ";
						PreparedStatement pre=(PreparedStatement)conn.prepareStatement(sql);
						pre.setString(1, "1");pre.setString(2,teacher);
						pre.setString(3, year);pre.setString(4, month);
						pre.setString(5, day);qq = pre.executeUpdate();pre.close();
					}
					else if(time.equals(B[4])){
						String sql="update releasebooking set e=? where id=? and year=? and month=? and day=? ";
						PreparedStatement pre=(PreparedStatement)conn.prepareStatement(sql);
						pre.setString(1, "1");pre.setString(2,teacher);
						pre.setString(3, year);pre.setString(4, month);
						pre.setString(5, day);qq = pre.executeUpdate();pre.close();
					}
					else if(time.equals(B[5])){
						String sql="update releasebooking set f=? where id=? and year=? and month=? and day=? ";
						PreparedStatement pre=(PreparedStatement)conn.prepareStatement(sql);
						pre.setString(1, "1");pre.setString(2,teacher);
						pre.setString(3, year);pre.setString(4, month);
						pre.setString(5, day);qq = pre.executeUpdate();pre.close();
					}
					else if(time.equals(B[6])){
						String sql="update releasebooking set g=? where id=? and year=? and month=? and day=? ";
						PreparedStatement pre=(PreparedStatement)conn.prepareStatement(sql);
						pre.setString(1, "1");pre.setString(2,teacher);
						pre.setString(3, year);pre.setString(4, month);
						pre.setString(5, day);qq = pre.executeUpdate();pre.close();
					}
					else if(time.equals(B[7])){
						String sql="update releasebooking set h=? where id=? and year=? and month=? and day=? ";
						PreparedStatement pre=(PreparedStatement)conn.prepareStatement(sql);
						pre.setString(1, "1");pre.setString(2,teacher);
						pre.setString(3, year);pre.setString(4, month);
						pre.setString(5, day);qq = pre.executeUpdate();pre.close();
					}
					else if(time.equals(B[8])){
						String sql="update releasebooking set i=? where id=? and year=? and month=? and day=? ";
						PreparedStatement pre=(PreparedStatement)conn.prepareStatement(sql);
						pre.setString(1, "1");pre.setString(2,teacher);
						pre.setString(3, year);pre.setString(4, month);
						pre.setString(5, day);qq = pre.executeUpdate();pre.close();
					}
					else if(time.equals(B[9])){
						String sql="update releasebooking set j=? where id=? and year=? and month=? and day=? ";
						PreparedStatement pre=(PreparedStatement)conn.prepareStatement(sql);
						pre.setString(1, "1");pre.setString(2,teacher);
						pre.setString(3, year);pre.setString(4, month);
						pre.setString(5, day);qq =pre.executeUpdate();pre.close();
					}
					else if(time.equals(B[10])){
						String sql="update releasebooking set k=? where id=? and year=? and month=? and day=? ";
						PreparedStatement pre=(PreparedStatement)conn.prepareStatement(sql);
						pre.setString(1, "1");pre.setString(2,teacher);
						pre.setString(3, year);pre.setString(4, month);
						pre.setString(5, day);qq = pre.executeUpdate();pre.close();
					}
					else if(time.equals(B[11])){
						String sql="update releasebooking set l=? where id=? and year=? and month=? and day=? ";
						PreparedStatement pre=(PreparedStatement)conn.prepareStatement(sql);
						pre.setString(1, "1");
						pre.setString(2,teacher);
						pre.setString(3, year);
						pre.setString(4, month);
						pre.setString(5, day);pre.execute();pre.close();
					}
					else if(time.equals(B[12])){
						String sql="update releasebooking set m=? where id=? and year=? and month=? and day=? ";
						PreparedStatement pre=(PreparedStatement)conn.prepareStatement(sql);
						pre.setString(1, "1");pre.setString(2,teacher);
						pre.setString(3, year);pre.setString(4, month);
						pre.setString(5, day);qq = pre.executeUpdate();pre.close();
					}
					else{
						String sql="update releasebooking set n=? where id=? and year=? and month=? and day=? ";
						PreparedStatement pre=(PreparedStatement)conn.prepareStatement(sql);
						pre.setString(1, "1");pre.setString(2,teacher);
						pre.setString(3, year);pre.setString(4, month);
						pre.setString(5, day);qq = pre.executeUpdate();pre.close();
					}
					
					String sq="DELETE FROM successbooking where year=? and month=? and day=? and time=? and studentid=?";
					 PreparedStatement pp=(PreparedStatement)conn.prepareStatement(sq);
					 pp.setString(1,year);pp.setString(2, month);
					 pp.setString(3, day);pp.setString(4, time);pp.setString(5, studentid);
						int sr = pp.executeUpdate();
						pp.close();
						
		   }catch(SQLException se){
	           // 处理 JDBC 错误
	           se.printStackTrace();
	       }catch(Exception e){
	           // 处理 Class.forName 错误
	           e.printStackTrace();
	       }
		   return true;
   }
   public List<releasebooking> Queryateacher(String id)
	{
       Connection conn = null;
       
       java.sql.PreparedStatement pstmt = null; 
       List<releasebooking> booking=new LinkedList<releasebooking>();
       try{
           
           conn = getConn();
       
           // 执行查询
          
           String sql;
           sql = "SELECT id, year,month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM releasebooking where id=?";
           pstmt = conn.prepareStatement(sql); 
           pstmt.setString(1, id);
           ResultSet rs = pstmt.executeQuery();
          // List<String> IDList=new ArrayList<String>();
           while(rs.next())
           {
           	releasebooking book=new releasebooking();
           	book.id =rs.getString("id");book.year=rs.getString("year");
           	book.month=rs.getString("month");book.day=rs.getString("day");
           	book.a=rs.getString("a");book.b=rs.getString("b");
           	book.c=rs.getString("c");book.d=rs.getString("d");
           	book.e=rs.getString("e");book.e=rs.getString("e");
           	book.f=rs.getString("f");book.g=rs.getString("g");
           	book.h=rs.getString("h");book.i=rs.getString("i");
           	book.j=rs.getString("j");book.k=rs.getString("k");
           	book.l=rs.getString("l");book.m=rs.getString("m");
           	book.n=rs.getString("n");
           	booking.add(book);
           	
           }
           	rs.close();
           	pstmt.close();
           	conn.close();
       }
           	catch(SQLException se){
                   // 处理 JDBC 错误
                   se.printStackTrace();
               }catch(Exception e){
                   // 处理 Class.forName 错误
                   e.printStackTrace();
               }
		return booking;
		
	}
   
   public boolean appointment(String tid,String sid,String A[],String year,String month,String day,String instruction)
	{
		 Connection conn = null;
	         
	        try{
	            conn=getConn();
	            
	            char [] B=new char [14];
	            B[0]='a';B[1]='b';B[2]='c';B[3]='d';B[4]='e';B[5]='f';B[6]='g';B[7]='h';B[8]='i';
	   		 	B[9]='j';B[10]='k';B[11]='l';B[12]='m';B[13]='n';
	   		 	int i;
	   		 	for(i=0;i<14;i++)
	   		 	{
		   		 		if(!A[i].equals("0"))
		   		 		{
			   		 		if(B[i]=='a')
							{
			   		 			String ss;
			   		 			java.sql.PreparedStatement stmt = null;
			   		 			ss="SELECT teacherid,year,month,day,time,studentid,instruction FROM successbooking where year=? and month=? and day=? and time=? and studentid=?";
			   		 			stmt = conn.prepareStatement(ss); 
			   		 			stmt.setString(1,year);stmt.setString(2, month);stmt.setString(3, day);
			   		 			stmt.setString(4, "8:00-8:30");stmt.setString(5,sid);ResultSet sr = stmt.executeQuery();
			   		 			if(sr.next()){}
			   		 			else{
				   		 			String sql;
				   		 			java.sql.PreparedStatement pstmt = null;
				   		 			sql = "SELECT id, year, month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM releasebooking where id=? and year=? and month=? and day=? and a=?";
				   		 			pstmt = conn.prepareStatement(sql); 
				   		 			pstmt.setString(1, tid);pstmt.setString(2, year);
				   		 			pstmt.setString(3, month);pstmt.setString(4, day);pstmt.setString(5, "1");
				   		 			ResultSet rs = pstmt.executeQuery();
				   		 			System.out.println("进去了");
				   		 			if(rs.next())
				   		 			{
				   		 				System.out.println("进");
				   		 				String sq;java.sql.PreparedStatement pstm = null;
				   		 				sq="update releasebooking set a=? where id=? and year=? and month=? and day=?";
				   		 				pstm=conn.prepareStatement(sq);
				   		 				pstm.setString(1, "2");pstm.setString(2, tid);pstm.setString(3,year);
				   		 				pstm.setString(4, month);pstm.setString(5, day);
				   		 				pstm.executeUpdate();System.out.println("进去");
				   		 				sq="insert into successbooking (teacherid, year,month,day,time,studentid,instruction)  values(?,?,?,?,?,?,?)";
				   		 				java.sql.PreparedStatement pstm1 = null;
				   		 				pstm1=conn.prepareStatement(sq);
				   		 				pstm1.setString(1, tid);pstm1.setString(2, year);
				   		 				pstm1.setString(3,month);pstm1.setString(4,day);
				   		 				pstm1.setString(5, "8:00-8:30");pstm1.setString(6, sid);pstm1.setString(7, instruction);
				   		 				pstm1.executeUpdate();
				   		 			 System.out.println("写进去了");
				   		 			 	rs.close();
				   		 				pstm1.close();
				   		 				pstm.close();
				   		 			}
				   		 			else{rs.close();pstmt.close();}
					   		 		}sr.close();stmt.close();
							}
			   		 		
			   		 		else if(B[i]=='b')
			   		 		{
				   		 		String ss;
			   		 			java.sql.PreparedStatement stmt = null;
			   		 			ss="SELECT teacherid,year,month,day,time,studentid,instruction FROM successbooking where year=? and month=? and day=? and time=? and studentid=?";
			   		 			stmt = conn.prepareStatement(ss); 
			   		 			stmt.setString(1,year);stmt.setString(2, month);stmt.setString(3, day);
			   		 			stmt.setString(4, "8:30-9:00");stmt.setString(5,sid);ResultSet sr = stmt.executeQuery();
			   		 			if(sr.next()){}
			   		 			else{
			   		 			String sql;
			   		 			java.sql.PreparedStatement pstmt = null;
			   		 			sql = "SELECT id, year, month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM releasebooking where id=? and year=? and month=? and day=? and b=?";
			   		 			pstmt = conn.prepareStatement(sql); 
			   		 			pstmt.setString(1, tid);pstmt.setString(2, year);
			   		 			pstmt.setString(3, month);pstmt.setString(4, day);pstmt.setString(5, "1");
			   		 			ResultSet rs = pstmt.executeQuery();
			   		 			if(rs.next())
			   		 			{
			   		 				String sq;java.sql.PreparedStatement pstm = null;
			   		 				sq="update releasebooking set b=? where id=? and year=? and month=? and day=?";
			   		 				pstm=conn.prepareStatement(sq);
			   		 				pstm.setString(1, "2");pstm.setString(2, tid);pstm.setString(3,year);
			   		 				pstm.setString(4, month);pstm.setString(5, day);
			   		 				pstm.executeUpdate();
			   		 				sq="insert into successbooking (teacherid, year,month,day,time,studentid,instruction)  values(?,?,?,?,?,?,?)";
			   		 				java.sql.PreparedStatement pstm1 = null;pstm1=conn.prepareStatement(sq);
			   		 				pstm1.setString(1, tid);pstm1.setString(2, year);
			   		 				pstm1.setString(3,month);pstm1.setString(4,day);
			   		 				pstm1.setString(5, "8:30-9:00");pstm1.setString(6, sid);pstm1.setString(7, instruction);
			   		 				pstm1.executeUpdate();
			   		 				rs.close();
			   		 				pstm1.close();
			   		 				pstm.close();
			   		 			}
			   		 			else{rs.close();pstmt.close();}
			   		 			}sr.close();stmt.close();
			   		 		}
				   		 	else if(B[i]=='c')
			   		 		{
					   		 	String ss;
			   		 			java.sql.PreparedStatement stmt = null;
			   		 			ss="SELECT teacherid,year,month,day,time,studentid,instruction FROM successbooking where year=? and month=? and day=? and time=? and studentid=?";
			   		 			stmt = conn.prepareStatement(ss); 
			   		 			stmt.setString(1,year);stmt.setString(2, month);stmt.setString(3, day);
			   		 			stmt.setString(4, "9:00-9:30");stmt.setString(5,sid);ResultSet sr = stmt.executeQuery();
			   		 			if(sr.next()){}
			   		 			else{
			   		 			String sql;
			   		 			java.sql.PreparedStatement pstmt = null;
			   		 			sql = "SELECT id, year, month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM releasebooking where id=? and year=? and month=? and day=? and c=?";
			   		 			pstmt = conn.prepareStatement(sql); 
			   		 			pstmt.setString(1, tid);pstmt.setString(2, year);
			   		 			pstmt.setString(3, month);pstmt.setString(4, day);pstmt.setString(5, "1");
			   		 			ResultSet rs = pstmt.executeQuery();
			   		 			if(rs.next())
			   		 			{
			   		 				String sq;java.sql.PreparedStatement pstm = null;
			   		 				sq="update releasebooking set c=? where id=? and year=? and month=? and day=?";
			   		 				pstm=conn.prepareStatement(sq);
			   		 				pstm.setString(1, "2");pstm.setString(2, tid);pstm.setString(3,year);
			   		 				pstm.setString(4, month);pstm.setString(5, day);
			   		 				pstm.executeUpdate();
			   		 				sq="insert into successbooking (teacherid, year,month,day,time,studentid,instruction)  values(?,?,?,?,?,?,?)";
			   		 				java.sql.PreparedStatement pstm1 = null;pstm1=conn.prepareStatement(sq);
			   		 				pstm1.setString(1, tid);pstm1.setString(2, year);
			   		 				pstm1.setString(3,month);pstm1.setString(4,day);
			   		 				pstm1.setString(5, "9:00-9:30");pstm1.setString(6, sid);pstm1.setString(7, instruction);
			   		 				pstm1.executeUpdate();
			   		 				rs.close();
			   		 				pstm1.close();
			   		 				pstm.close();
			   		 			}
			   		 			else{rs.close();pstmt.close();}
			   		 			}sr.close();stmt.close();
			   		 		}
					   		 else if(B[i]=='d')
				   		 		{
						   			String ss;
				   		 			java.sql.PreparedStatement stmt = null;
				   		 			ss="SELECT teacherid,year,month,day,time,studentid,instruction FROM successbooking where year=? and month=? and day=? and time=? and studentid=?";
				   		 			stmt = conn.prepareStatement(ss); 
				   		 			stmt.setString(1,year);stmt.setString(2, month);stmt.setString(3, day);
				   		 			stmt.setString(4, "9:30-10:00");stmt.setString(5,sid);ResultSet sr = stmt.executeQuery();
				   		 			if(sr.next()){}
				   		 			else{
				   		 			String sql;
				   		 			java.sql.PreparedStatement pstmt = null;
				   		 			sql = "SELECT id, year, month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM releasebooking where id=? and year=? and month=? and day=? and d=?";
				   		 			pstmt = conn.prepareStatement(sql); 
				   		 			pstmt.setString(1, tid);pstmt.setString(2, year);
				   		 			pstmt.setString(3, month);pstmt.setString(4, day);pstmt.setString(5, "1");
				   		 			ResultSet rs = pstmt.executeQuery();
				   		 			if(rs.next())
				   		 			{
				   		 				String sq;java.sql.PreparedStatement pstm = null;
				   		 				sq="update releasebooking set d=? where id=? and year=? and month=? and day=?";
				   		 				pstm=conn.prepareStatement(sq);
				   		 				pstm.setString(1, "2");pstm.setString(2, tid);pstm.setString(3,year);
				   		 				pstm.setString(4, month);pstm.setString(5, day);
				   		 				pstm.executeUpdate();
				   		 				sq="insert into successbooking (teacherid, year,month,day,time,studentid,instruction)  values(?,?,?,?,?,?,?)";
				   		 				java.sql.PreparedStatement pstm1 = null;pstm1=conn.prepareStatement(sq);
				   		 				pstm1.setString(1, tid);pstm1.setString(2, year);
				   		 				pstm1.setString(3,month);pstm1.setString(4,day);
				   		 				pstm1.setString(5, "9:30-10:00");pstm1.setString(6, sid);pstm1.setString(7, instruction);
				   		 				pstm1.executeUpdate(); rs.close();
				   		 				pstm1.close();
				   		 				pstm.close();
				   		 			}
				   		 			else{rs.close();pstmt.close();}
				   		 			}sr.close();stmt.close();
				   		 		}
						   		else if(B[i]=='e')
				   		 		{
						   			String ss;
				   		 			java.sql.PreparedStatement stmt = null;
				   		 			ss="SELECT teacherid,year,month,day,time,studentid,instruction FROM successbooking where year=? and month=? and day=? and time=? and studentid=?";
				   		 			stmt = conn.prepareStatement(ss); 
				   		 			stmt.setString(1,year);stmt.setString(2, month);stmt.setString(3, day);
				   		 			stmt.setString(4, "10:00-10:30");stmt.setString(5,sid);ResultSet sr = stmt.executeQuery();
				   		 			if(sr.next()){}
				   		 			else{
				   		 			String sql;
				   		 			java.sql.PreparedStatement pstmt = null;
				   		 			sql = "SELECT id, year, month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM releasebooking where id=? and year=? and month=? and day=? and e=?";
				   		 			pstmt = conn.prepareStatement(sql); 
				   		 			pstmt.setString(1, tid);pstmt.setString(2, year);
				   		 			pstmt.setString(3, month);pstmt.setString(4, day);pstmt.setString(5, "1");
				   		 			ResultSet rs = pstmt.executeQuery();
				   		 			if(rs.next())
				   		 			{
				   		 				String sq;java.sql.PreparedStatement pstm = null;
				   		 				sq="update releasebooking set e=? where id=? and year=? and month=? and day=?";
				   		 				pstm=conn.prepareStatement(sq);
				   		 				pstm.setString(1, "2");pstm.setString(2, tid);pstm.setString(3,year);
				   		 				pstm.setString(4, month);pstm.setString(5, day);
				   		 				pstm.executeUpdate();
				   		 				sq="insert into successbooking (teacherid, year,month,day,time,studentid,instruction)  values(?,?,?,?,?,?,?)";
				   		 				java.sql.PreparedStatement pstm1 = null;pstm1=conn.prepareStatement(sq);
				   		 				pstm1.setString(1, tid);pstm1.setString(2, year);
				   		 				pstm1.setString(3,month);pstm1.setString(4,day);
				   		 				pstm1.setString(5, "10:00-10:30");pstm1.setString(6, sid);pstm1.setString(7, instruction);
				   		 				pstm1.executeUpdate(); rs.close();
				   		 				pstm1.close();
				   		 				pstm.close();
				   		 			}
				   		 			else{rs.close();pstmt.close();}
				   		 			}sr.close();stmt.close();
				   		 		}
						   		else if(B[i]=='f')
				   		 		{
						   			String ss;
				   		 			java.sql.PreparedStatement stmt = null;
				   		 			ss="SELECT teacherid,year,month,day,time,studentid,instruction FROM successbooking where year=? and month=? and day=? and time=? and studentid=?";
				   		 			stmt = conn.prepareStatement(ss); 
				   		 			stmt.setString(1,year);stmt.setString(2, month);stmt.setString(3, day);
				   		 			stmt.setString(4, "10:30-11:00");stmt.setString(5,sid);ResultSet sr = stmt.executeQuery();
				   		 			if(sr.next()){}
				   		 			else{
				   		 			String sql;
				   		 			java.sql.PreparedStatement pstmt = null;
				   		 			sql = "SELECT id, year, month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM releasebooking where id=? and year=? and month=? and day=? and f=?";
				   		 			pstmt = conn.prepareStatement(sql); 
				   		 			pstmt.setString(1, tid);pstmt.setString(2, year);
				   		 			pstmt.setString(3, month);pstmt.setString(4, day);pstmt.setString(5, "1");
				   		 			ResultSet rs = pstmt.executeQuery();
				   		 			if(rs.next())
				   		 			{
				   		 				String sq;java.sql.PreparedStatement pstm = null;
				   		 				sq="update releasebooking set f=? where id=? and year=? and month=? and day=?";
				   		 				pstm=conn.prepareStatement(sq);
				   		 				pstm.setString(1, "2");pstm.setString(2, tid);pstm.setString(3,year);
				   		 				pstm.setString(4, month);pstm.setString(5, day);
				   		 				pstm.executeUpdate();
				   		 				sq="insert into successbooking (teacherid, year,month,day,time,studentid,instruction)  values(?,?,?,?,?,?,?)";
				   		 				java.sql.PreparedStatement pstm1 = null;pstm1=conn.prepareStatement(sq);
				   		 				pstm1.setString(1, tid);pstm1.setString(2, year);
				   		 				pstm1.setString(3,month);pstm1.setString(4,day);
				   		 				pstm1.setString(5, "10:30-11:00");pstm1.setString(6, sid);pstm1.setString(7, instruction);
				   		 				pstm1.executeUpdate(); rs.close();
				   		 				pstm1.close();
				   		 				pstm.close();
				   		 			}
				   		 			else{rs.close();pstmt.close();}
				   		 			}sr.close();stmt.close();
				   		 		}
						   		else if(B[i]=='g')
				   		 		{
						   			String ss;
				   		 			java.sql.PreparedStatement stmt = null;
				   		 			ss="SELECT teacherid,year,month,day,time,studentid,instruction FROM successbooking where year=? and month=? and day=? and time=? and studentid=?";
				   		 			stmt = conn.prepareStatement(ss); 
				   		 			stmt.setString(1,year);stmt.setString(2, month);stmt.setString(3, day);
				   		 			stmt.setString(4, "11:00-11:30");stmt.setString(5,sid);ResultSet sr = stmt.executeQuery();
				   		 			if(sr.next()){}
				   		 			else{
				   		 			String sql;
				   		 			java.sql.PreparedStatement pstmt = null;
				   		 			sql = "SELECT id, year, month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM releasebooking where id=? and year=? and month=? and day=? and g=?";
				   		 			pstmt = conn.prepareStatement(sql); 
				   		 			pstmt.setString(1, tid);pstmt.setString(2, year);
				   		 			pstmt.setString(3, month);pstmt.setString(4, day);pstmt.setString(5, "1");
				   		 			ResultSet rs = pstmt.executeQuery();
				   		 			if(rs.next())
				   		 			{
				   		 				String sq;java.sql.PreparedStatement pstm = null;
				   		 				sq="update releasebooking set g=? where id=? and year=? and month=? and day=?";
				   		 				pstm=conn.prepareStatement(sq);
				   		 				pstm.setString(1, "2");pstm.setString(2, tid);pstm.setString(3,year);
				   		 				pstm.setString(4, month);pstm.setString(5, day);
				   		 				pstm.executeUpdate();
				   		 				sq="insert into successbooking (teacherid, year,month,day,time,studentid,instruction)  values(?,?,?,?,?,?,?)";
				   		 				java.sql.PreparedStatement pstm1 = null;pstm1=conn.prepareStatement(sq);
				   		 				pstm1.setString(1, tid);pstm1.setString(2, year);
				   		 				pstm1.setString(3,month);pstm1.setString(4,day);
				   		 				pstm1.setString(5, "11:00-11:30");pstm1.setString(6, sid);pstm1.setString(7, instruction);
				   		 				pstm1.executeUpdate(); rs.close();
				   		 				pstm1.close();
				   		 				pstm.close();
				   		 			}
				   		 			else{rs.close();pstmt.close();}
				   		 			}sr.close();stmt.close();
				   		 		}
						   		else if(B[i]=='h')
				   		 		{

						   			String ss;
				   		 			java.sql.PreparedStatement stmt = null;
				   		 			ss="SELECT teacherid,year,month,day,time,studentid,instruction FROM successbooking where year=? and month=? and day=? and time=? and studentid=?";
				   		 			stmt = conn.prepareStatement(ss); 
				   		 			stmt.setString(1,year);stmt.setString(2, month);stmt.setString(3, day);
				   		 			stmt.setString(4, "14:00-14:30");stmt.setString(5,sid);ResultSet sr = stmt.executeQuery();
				   		 			if(sr.next()){}
				   		 			else{
				   		 			String sql;
				   		 			java.sql.PreparedStatement pstmt = null;
				   		 			sql = "SELECT id, year, month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM releasebooking where id=? and year=? and month=? and day=? and h=?";
				   		 			pstmt = conn.prepareStatement(sql); 
				   		 			pstmt.setString(1, tid);pstmt.setString(2, year);
				   		 			pstmt.setString(3, month);pstmt.setString(4, day);pstmt.setString(5, "1");
				   		 			ResultSet rs = pstmt.executeQuery();
				   		 			if(rs.next())
				   		 			{
				   		 				String sq;java.sql.PreparedStatement pstm = null;
				   		 				sq="update releasebooking set h=? where id=? and year=? and month=? and day=?";
				   		 				pstm=conn.prepareStatement(sq);
				   		 				pstm.setString(1, "2");pstm.setString(2, tid);pstm.setString(3,year);
				   		 				pstm.setString(4, month);pstm.setString(5, day);
				   		 				pstm.executeUpdate();
				   		 				sq="insert into successbooking (teacherid, year,month,day,time,studentid,instruction)  values(?,?,?,?,?,?,?)";
				   		 				java.sql.PreparedStatement pstm1 = null;pstm1=conn.prepareStatement(sq);
				   		 				pstm1.setString(1, tid);pstm1.setString(2, year);
				   		 				pstm1.setString(3,month);pstm1.setString(4,day);
				   		 				pstm1.setString(5, "14:00-14:30");pstm1.setString(6, sid);pstm1.setString(7, instruction);
				   		 				pstm1.executeUpdate(); rs.close();
				   		 				pstm1.close();
				   		 				pstm.close();
				   		 			}
				   		 			else{rs.close();pstmt.close();}
				   		 			}sr.close();stmt.close();
				   		 		}
						   		else if(B[i]=='i')
				   		 		{
						   			String ss;
				   		 			java.sql.PreparedStatement stmt = null;
				   		 			ss="SELECT teacherid,year,month,day,time,studentid,instruction FROM successbooking where year=? and month=? and day=? and time=? and studentid=?";
				   		 			stmt = conn.prepareStatement(ss); 
				   		 			stmt.setString(1,year);stmt.setString(2, month);stmt.setString(3, day);
				   		 			stmt.setString(4, "14:30-15:00");stmt.setString(5,sid);ResultSet sr = stmt.executeQuery();
				   		 			if(sr.next()){}
				   		 			else{
				   		 			String sql;
				   		 			java.sql.PreparedStatement pstmt = null;
				   		 			sql = "SELECT id, year, month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM releasebooking where id=? and year=? and month=? and day=? and i=?";
				   		 			pstmt = conn.prepareStatement(sql); 
				   		 			pstmt.setString(1, tid);pstmt.setString(2, year);
				   		 			pstmt.setString(3, month);pstmt.setString(4, day);pstmt.setString(5, "1");
				   		 			ResultSet rs = pstmt.executeQuery();
				   		 			if(rs.next())
				   		 			{
				   		 				String sq;java.sql.PreparedStatement pstm = null;
				   		 				sq="update releasebooking set i=? where id=? and year=? and month=? and day=?";
				   		 				pstm=conn.prepareStatement(sq);
				   		 				pstm.setString(1, "2");pstm.setString(2, tid);pstm.setString(3,year);
				   		 				pstm.setString(4, month);pstm.setString(5, day);
				   		 				pstm.executeUpdate();
				   		 				sq="insert into successbooking (teacherid, year,month,day,time,studentid,instruction)  values(?,?,?,?,?,?,?)";
				   		 				java.sql.PreparedStatement pstm1 = null;pstm1=conn.prepareStatement(sq);
				   		 				pstm1.setString(1, tid);pstm1.setString(2, year);
				   		 				pstm1.setString(3,month);pstm1.setString(4,day);
				   		 				pstm1.setString(5, "14:30-15:00");pstm1.setString(6, sid);pstm1.setString(7, instruction);
				   		 				pstm1.executeUpdate(); rs.close();
				   		 				pstm1.close();
				   		 				pstm.close();
				   		 			}
				   		 			else{rs.close();pstmt.close();}
				   		 			}sr.close();stmt.close();
				   		 		}
						   		else if(B[i]=='j')
				   		 		{
						   			String ss;
				   		 			java.sql.PreparedStatement stmt = null;
				   		 			ss="SELECT teacherid,year,month,day,time,studentid,instruction FROM successbooking where year=? and month=? and day=? and time=? and studentid=?";
				   		 			stmt = conn.prepareStatement(ss); 
				   		 			stmt.setString(1,year);stmt.setString(2, month);stmt.setString(3, day);
				   		 			stmt.setString(4, "15:00-15:30");stmt.setString(5,sid);ResultSet sr = stmt.executeQuery();
				   		 			if(sr.next()){}
				   		 			else{
				   		 			String sql;
				   		 			java.sql.PreparedStatement pstmt = null;
				   		 			sql = "SELECT id, year, month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM releasebooking where id=? and year=? and month=? and day=? and j=?";
				   		 			pstmt = conn.prepareStatement(sql); 
				   		 			pstmt.setString(1, tid);pstmt.setString(2, year);
				   		 			pstmt.setString(3, month);pstmt.setString(4, day);pstmt.setString(5, "1");
				   		 			ResultSet rs = pstmt.executeQuery();
				   		 			if(rs.next())
				   		 			{
				   		 				String sq;java.sql.PreparedStatement pstm = null;
				   		 				sq="update releasebooking set j=? where id=? and year=? and month=? and day=?";
				   		 				pstm=conn.prepareStatement(sq);
				   		 				pstm.setString(1, "2");pstm.setString(2, tid);pstm.setString(3,year);
				   		 				pstm.setString(4, month);pstm.setString(5, day);
				   		 				pstm.executeUpdate();
				   		 				sq="insert into successbooking (teacherid, year,month,day,time,studentid,instruction)  values(?,?,?,?,?,?,?)";
				   		 				java.sql.PreparedStatement pstm1 = null;pstm1=conn.prepareStatement(sq);
				   		 				pstm1.setString(1, tid);pstm1.setString(2, year);
				   		 				pstm1.setString(3,month);pstm1.setString(4,day);
				   		 				pstm1.setString(5, "15:00-15:30");pstm1.setString(6, sid);pstm1.setString(7, instruction);
				   		 				pstm1.executeUpdate(); rs.close();
				   		 				pstm1.close();
				   		 				pstm.close();
				   		 			}
				   		 			else{rs.close();pstmt.close();}
				   		 			}sr.close();stmt.close();
				   		 		}
						   		else if(B[i]=='k')
				   		 		{
						   			String ss;
				   		 			java.sql.PreparedStatement stmt = null;
				   		 			ss="SELECT teacherid,year,month,day,time,studentid,instruction FROM successbooking where year=? and month=? and day=? and time=? and studentid=?";
				   		 			stmt = conn.prepareStatement(ss); 
				   		 			stmt.setString(1,year);stmt.setString(2, month);stmt.setString(3, day);
				   		 			stmt.setString(4, "15:30-16:00");stmt.setString(5,sid);ResultSet sr = stmt.executeQuery();
				   		 			if(sr.next()){}
				   		 			else{
				   		 			String sql;
				   		 			java.sql.PreparedStatement pstmt = null;
				   		 			sql = "SELECT id, year, month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM releasebooking where id=? and year=? and month=? and day=? and k=?";
				   		 			pstmt = conn.prepareStatement(sql); 
				   		 			pstmt.setString(1, tid);pstmt.setString(2, year);
				   		 			pstmt.setString(3, month);pstmt.setString(4, day);pstmt.setString(5, "1");
				   		 			ResultSet rs = pstmt.executeQuery();
				   		 			if(rs.next())
				   		 			{
				   		 				String sq;java.sql.PreparedStatement pstm = null;
				   		 				sq="update releasebooking set k=? where id=? and year=? and month=? and day=?";
				   		 				pstm=conn.prepareStatement(sq);
				   		 				pstm.setString(1, "2");pstm.setString(2, tid);pstm.setString(3,year);
				   		 				pstm.setString(4, month);pstm.setString(5, day);
				   		 				pstm.executeUpdate();
				   		 				sq="insert into successbooking (teacherid, year,month,day,time,studentid,instruction)  values(?,?,?,?,?,?,?)";
				   		 				java.sql.PreparedStatement pstm1 = null;pstm1=conn.prepareStatement(sq);
				   		 				pstm1.setString(1, tid);pstm1.setString(2, year);
				   		 				pstm1.setString(3,month);pstm1.setString(4,day);
				   		 				pstm1.setString(5, "15:30-16:00");pstm1.setString(6, sid);pstm1.setString(7, instruction);
				   		 				pstm1.executeUpdate(); rs.close();
				   		 				pstm1.close();
				   		 				pstm.close();
				   		 			}
				   		 			else{rs.close();pstmt.close();}
				   		 			}sr.close();stmt.close();
				   		 		}
						   		else if(B[i]=='l')
				   		 		{
						   			String ss;
				   		 			java.sql.PreparedStatement stmt = null;
				   		 			ss="SELECT teacherid,year,month,day,time,studentid,instruction FROM successbooking where year=? and month=? and day=? and time=? and studentid=?";
				   		 			stmt = conn.prepareStatement(ss); 
				   		 			stmt.setString(1,year);stmt.setString(2, month);stmt.setString(3, day);
				   		 			stmt.setString(4, "16:00-16:30");stmt.setString(5,sid);ResultSet sr = stmt.executeQuery();
				   		 			if(sr.next()){}
				   		 			else{
				   		 			String sql;
				   		 			java.sql.PreparedStatement pstmt = null;
				   		 			sql = "SELECT id, year, month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM releasebooking where id=? and year=? and month=? and day=? and l=?";
				   		 			pstmt = conn.prepareStatement(sql); 
				   		 			pstmt.setString(1, tid);pstmt.setString(2, year);
				   		 			pstmt.setString(3, month);pstmt.setString(4, day);pstmt.setString(5, "1");
				   		 			ResultSet rs = pstmt.executeQuery();
				   		 			if(rs.next())
				   		 			{
				   		 				String sq;java.sql.PreparedStatement pstm = null;
				   		 				sq="update releasebooking set l=? where id=? and year=? and month=? and day=?";
				   		 				pstm=conn.prepareStatement(sq);
				   		 				pstm.setString(1, "2");pstm.setString(2, tid);pstm.setString(3,year);
				   		 				pstm.setString(4, month);pstm.setString(5, day);
				   		 				pstm.executeUpdate();
				   		 				sq="insert into successbooking (teacherid, year,month,day,time,studentid,instruction)  values(?,?,?,?,?,?,?)";
				   		 				java.sql.PreparedStatement pstm1 = null;pstm1=conn.prepareStatement(sq);
				   		 				pstm1.setString(1, tid);pstm1.setString(2, year);
				   		 				pstm1.setString(3,month);pstm1.setString(4,day);
				   		 				pstm1.setString(5, "16:00-16:30");pstm1.setString(6, sid);pstm1.setString(7, instruction);
				   		 				pstm1.executeUpdate(); rs.close();
				   		 				pstm1.close();
				   		 				pstm.close();
				   		 			}
				   		 			else{rs.close();pstmt.close();}
				   		 			}sr.close();stmt.close();
				   		 		}
						   		else if(B[i]=='m')
				   		 		{
						   			String ss;
				   		 			java.sql.PreparedStatement stmt = null;
				   		 			ss="SELECT teacherid,year,month,day,time,studentid,instruction FROM successbooking where year=? and month=? and day=? and time=? and studentid=?";
				   		 			stmt = conn.prepareStatement(ss); 
				   		 			stmt.setString(1,year);stmt.setString(2, month);stmt.setString(3, day);
				   		 			stmt.setString(4, "16:30-17:00");stmt.setString(5,sid);ResultSet sr = stmt.executeQuery();
				   		 			if(sr.next()){}
				   		 			else{
				   		 			String sql;
				   		 			java.sql.PreparedStatement pstmt = null;
				   		 			sql = "SELECT id, year, month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM releasebooking where id=? and year=? and month=? and day=? and m=?";
				   		 			pstmt = conn.prepareStatement(sql); 
				   		 			pstmt.setString(1, tid);pstmt.setString(2, year);
				   		 			pstmt.setString(3, month);pstmt.setString(4, day);pstmt.setString(5, "1");
				   		 			ResultSet rs = pstmt.executeQuery();
				   		 			if(rs.next())
				   		 			{
				   		 				String sq;java.sql.PreparedStatement pstm = null;
				   		 				sq="update releasebooking set m=? where id=? and year=? and month=? and day=?";
				   		 				pstm=conn.prepareStatement(sq);
				   		 				pstm.setString(1, "2");pstm.setString(2, tid);pstm.setString(3,year);
				   		 				pstm.setString(4, month);pstm.setString(5, day);
				   		 				pstm.executeUpdate();
				   		 				sq="insert into successbooking (teacherid, year,month,day,time,studentid,instruction)  values(?,?,?,?,?,?,?)";
				   		 				java.sql.PreparedStatement pstm1 = null;pstm1=conn.prepareStatement(sq);
				   		 				pstm1.setString(1, tid);pstm1.setString(2, year);
				   		 				pstm1.setString(3,month);pstm1.setString(4,day);
				   		 				pstm1.setString(5, "16:30-17:00");pstm1.setString(6, sid);pstm1.setString(7, instruction);
				   		 				pstm1.executeUpdate(); rs.close();
				   		 				pstm1.close();
				   		 				pstm.close();
				   		 			}
				   		 			else{rs.close();pstmt.close();}
				   		 			}sr.close();stmt.close();
				   		 		}
						   		else if(B[i]=='n')
				   		 		{
						   			String ss;
				   		 			java.sql.PreparedStatement stmt = null;
				   		 			ss="SELECT teacherid,year,month,day,time,studentid,instruction FROM successbooking where year=? and month=? and day=? and time=? and studentid=?";
				   		 			stmt = conn.prepareStatement(ss); 
				   		 			stmt.setString(1,year);stmt.setString(2, month);stmt.setString(3, day);
				   		 			stmt.setString(4, "17:00-17:30");stmt.setString(5,sid);ResultSet sr = stmt.executeQuery();
				   		 			if(sr.next()){}
				   		 			else{
				   		 			String sql;
				   		 			java.sql.PreparedStatement pstmt = null;
				   		 			sql = "SELECT id, year, month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM releasebooking where id=? and year=? and month=? and day=? and n=?";
				   		 			pstmt = conn.prepareStatement(sql); 
				   		 			pstmt.setString(1, tid);pstmt.setString(2, year);
				   		 			pstmt.setString(3, month);pstmt.setString(4, day);pstmt.setString(5, "1");
				   		 			ResultSet rs = pstmt.executeQuery();
				   		 			if(rs.next())
				   		 			{
				   		 				String sq;java.sql.PreparedStatement pstm = null;
				   		 				sq="update releasebooking set n=? where id=? and year=? and month=? and day=?";
				   		 				pstm=conn.prepareStatement(sq);
				   		 				pstm.setString(1, "2");pstm.setString(2, tid);pstm.setString(3,year);
				   		 				pstm.setString(4, month);pstm.setString(5, day);
				   		 				pstm.executeUpdate();
				   		 				sq="insert into successbooking (teacherid, year,month,day,time,studentid,instruction)  values(?,?,?,?,?,?,?)";
				   		 				java.sql.PreparedStatement pstm1 = null;pstm1=conn.prepareStatement(sq);
				   		 				pstm1.setString(1, tid);pstm1.setString(2, year);
				   		 				pstm1.setString(3,month);pstm1.setString(4,day);
				   		 				pstm1.setString(5, "17:00-17:30");pstm1.setString(6, sid);pstm1.setString(7, instruction);
				   		 				pstm1.executeUpdate(); rs.close();
				   		 				pstm1.close();
				   		 				pstm.close();
				   		 			}
				   		 			else{rs.close();pstmt.close();}
				   		 			}sr.close();stmt.close();
				   		 		}
							}
		   		 		}
	   		         
		   		 	}catch(Exception e){
		   				System.out.print("信息添加失败！");
		   				e.printStackTrace();
		   				return false;
		   			}
	        return true;
	}
   
   public List<successbooking> teashowtime(String id)
   {
          Connection conn = null;
           
        
           List<successbooking> booking=new LinkedList<successbooking>();
           try{
              
               conn = getConn();
               // 执行查询
               java.sql.PreparedStatement pstmt = null; 
               String sql;
               sql = "SELECT teacherid, year,month,day,time,studentid,instruction FROM successbooking where teacherid=?";
               pstmt = conn.prepareStatement(sql); 
               pstmt.setString(1, id);
               ResultSet rs = pstmt.executeQuery();
              // List<String> IDList=new ArrayList<String>();
               while(rs.next())
               {
               	successbooking book=new successbooking();
               	book.teacherid=rs.getString("teacherid");book.year=rs.getString("year");
               	book.month=rs.getString("month");book.day=rs.getString("day");book.time=rs.getString("time");
               	book.studentid=rs.getString("studentid");book.instruction=rs.getString("instruction");
               	booking.add(book);System.out.println("ISBN: " + book.time);
               }
               	rs.close();
               	pstmt.close();
               	conn.close();
           }
               	catch(SQLException se){
                       // 处理 JDBC 错误
                       se.printStackTrace();
                   }catch(Exception e){
                       // 处理 Class.forName 错误
                       e.printStackTrace();
                   }
   return booking;
   }
   public List<successbooking> stushowtime(String id)
   {
           Connection conn = null;
           java.sql.PreparedStatement pstmt = null; 
           List<successbooking> booking=new LinkedList<successbooking>();
           try{
           	 conn = getConn();
           
               // 执行查询
               System.out.println(" 实例化Statement对...");
               String sql;
               sql = "SELECT teacherid, year,month,day,time,studentid,instruction FROM successbooking where studentid=?";
               pstmt = conn.prepareStatement(sql); 
               pstmt.setString(1, id);
               ResultSet rs = pstmt.executeQuery();
              // List<String> IDList=new ArrayList<String>();
               while(rs.next())
               {
               	successbooking book=new successbooking();
               	book.teacherid=rs.getString("teacherid");book.year=rs.getString("year");
               	book.month=rs.getString("month");book.day=rs.getString("day");book.time=rs.getString("time");
               	book.studentid=rs.getString("studentid");book.instruction=rs.getString("instruction");
               	booking.add(book);System.out.println("ISBN: " + book.month);
               }
               	rs.close();
               	pstmt.close();
               	conn.close();
           }
               	catch(SQLException se){
                       // 处理 JDBC 错误
                       se.printStackTrace();
                   }catch(Exception e){
                       // 处理 Class.forName 错误
                       e.printStackTrace();
                   }
                  return booking;
   }
   public Map<String,String> QueryAllTimesOfAteacher(String teacherID){
	   Map<String,String> timesmap=new  HashMap<String,String>();//存放（2017-11-12，1|2|3）形式的键值对
	   String key;
		ArrayList<String> value;
		releasebooking rb=new releasebooking();
		List<releasebooking> rblist=new LinkedList<releasebooking>();
		rblist=Queryateacher(teacherID);
		
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
			finalvalue+=value.get(value.size()-1);
		   timesmap.put(key,finalvalue);
		}
		
		return timesmap;
   }

}
