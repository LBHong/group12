package com.user.mysqloperate;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.user.userstudent.student;
import com.user.userteacher.teacher;
import com.user.releasebooking.releasebooking;

public class Mysqloperate {

	public Connection getConn() {
	    String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/project?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	    String Qusername = "root";
	    String Qpassword = "123456";
	    Connection conn = null;
	    try {
	        Class.forName(driver); //classLoader,加载对应驱动
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
				Connection conn=getConn();
				
				String sql = "SELECT username,password,email,telephone,instruction,faculty,id FROM student where id=?";
				pstmt = (PreparedStatement)conn.prepareStatement(sql);
	            pstmt.setString(1, id);
	            ResultSet rs = pstmt.executeQuery();
	         // 展开结果集数据库 	
	            if(!rs.next()){
	            	return null;
	            }
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
         // 展开结果集数据库 	
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
	        	 System.out.println("成功添加老师！");
	         }
	         ps.close();
	         pstmt.close();
			conn.close();
		}
		catch(Exception e){
			System.out.print("信息添加失败！");
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
	    		detailMap.put("用户名",rs.getString("username"));
	    		detailMap.put("密码",rs.getString("password"));
	    		detailMap.put("邮箱",rs.getString("email"));
	    		detailMap.put("手机号",rs.getString("telephone"));
	    		detailMap.put("介绍",rs.getString("instruction"));
	    		detailMap.put("id",rs.getString("id"));		 
	    	 }
	    	 if(rs.next()==false)
	    	 {
	    		 rs.close();
		            pstmt.close();
		           
		            conn.close();
	    		 return null;
	    	 }
	    	 else
	    	 {
	    		 rs.close();
		            pstmt.close();
		           
		            conn.close();
	    		return detailMap;
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
	    		detailMap.put("用户名",rs.getString("username"));
	    		detailMap.put("密码",rs.getString("password"));
	    		detailMap.put("邮箱",rs.getString("email"));
	    		detailMap.put("手机号",rs.getString("telephone"));
	    		detailMap.put("介绍",rs.getString("instruction"));
	    		detailMap.put("科目",rs.getString("faculty"));
	    		detailMap.put("id",rs.getString("id"));		 
	    	 }
	    	 if(rs.next()==false)
	    	 {
	    		 rs.close();
		            pstmt.close();
		           
		            conn.close();
	    		 return null;
	    	 }
	    	 else
	    	 {
	    		 rs.close();
		            pstmt.close();
		           
		            conn.close();
	    		return detailMap;
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

	public Map<String,String> login(String password,String id) throws SQLException
	{
		Map<String,String> detailMap=new HashMap<String,String>();
	        if(id[0]=='0')
	        {
	        	detailMap=showstudent(id);
	        	String key="密码";		
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
	        	String key="密码";
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

	
	public List<teacher> chaxunallteachers(String faculty)
	{
		//Map<String,String> detailMap=new HashMap<String,String>();
		Connection conn = null;
	    java.sql.PreparedStatement pstmt = null; 
	    List<teacher> teacherList=new LinkedList<teacher>();
	    try{
	    	
	    	 conn = getConn();
	    	
	    	 String sql;
	    	 sql = "SELECT username,password,email, telephone,instruction,id,faculty FROM teacher where faculty=?";
	    	 pstmt = conn.prepareStatement(sql);
	    	 pstmt.setString(1, faculty);
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
	    		teacher te=new teacher();
//	    		detailMap.put("用户名",rs.getString("username"));
//	    		detailMap.put("密码",rs.getString("password"));
//	    		detailMap.put("邮箱",rs.getString("email"));
//	    		detailMap.put("手机号",rs.getString("telephone"));
//	    		detailMap.put("介绍",rs.getString("instruction"));
//	    		detailMap.put("科目",rs.getString("faculty"));
//	    		detailMap.put("id",rs.getString("id"));		
	    		te.username=rs.getString("username");
	    		te.password=rs.getString("password");
	    		te.email=rs.getString("email");
	    		te.telephone=rs.getString("telephone");
	    		te.instruction=rs.getString("instruction");
	    		te.faculty=rs.getString("faculty");
	    		te.id=rs.getString("id");
	    		teacherList.add(te);
	    		
	    	 }
	    	 rs.close();
	            pstmt.close();
	           
	            conn.close();
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
		return teacherList;
	}
	
	public String[] teachershow(String id,String year,String month,String day) throws SQLException
	{
		
		String []D=new String[14];
	    	
	    	 Connection conn = getConn();
	    	 java.sql.PreparedStatement pstmt = null; 
	    	 String sql;
	    	 sql = "SELECT id,year,month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM releasebooking where id=? and year=? and month=? and day=?";
	    	
				pstmt = conn.prepareStatement(sql);
			
	    	 pstmt.setString(1, id);
	    	 pstmt.setString(2,year);
	    	 pstmt.setString(3,month);
	    	 pstmt.setString(4,day);
	    	 
	    	 ResultSet rs = pstmt.executeQuery();
	    	 while(rs.next())
	    	 {
	    		 
	    		 D[0]=rs.getString("a");D[1]=rs.getString("b");D[2]=rs.getString("c");
	    		 D[3]=rs.getString("d");D[4]=rs.getString("e");D[5]=rs.getString("f");
	    		 D[6]=rs.getString("g");D[7]=rs.getString("h");D[8]=rs.getString("i");
	    		 D[9]=rs.getString("j");D[10]=rs.getString("k");D[11]=rs.getString("l");
	    		 D[12]=rs.getString("m");D[13]=rs.getString("n");
	    		 return D;
	    	 }
	    	 return null;
	}
	
	public boolean releasebooking(String A[],String id,String year,String month,String day)
	{
		 char [] B=new char [14];
		 B[0]='a';B[1]='b';B[2]='c';B[3]='d';B[4]='e';B[5]='f';B[6]='g';B[7]='h';B[8]='i';
		 B[9]='j';B[10]='k';B[11]='l';B[12]='m';B[13]='n';
		releasebooking rb=new releasebooking();
		try{
			Connection conn=getConn();
			
			
			//String sql="replace into release booking(id,year,month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			String sql = "SELECT id, year, month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM releasebooking where id=? and year=? and month=? and day=?";
			java.sql.PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,id);ps.setString(2, year);
			ps.setString(3, month);ps.setString(4,day);
			ResultSet rs = ps.executeQuery();
			int i;
			if(rs.next())
			{
				for(i=0;i<14;i++)
				{
					if(A[i].equals("0")){
						
					}
					else {
							if(B[i]=='a')
							{
								String pan=rs.getString("a");
								if(pan=="0"){
								String qs="update release booking set a=? where id=? and year=? and month=? and day=?";
								java.sql.PreparedStatement pss=conn.prepareStatement(qs);
								pss.setString(1, "1");
								pss.setString(2, id);
								pss.setString(3, year);
								pss.setString(4, month);
								pss.setString(5, day);
								pss.executeUpdate();
								pss.close();
								}
								
							}
							else if(B[i]=='b')
							{
								String pan=rs.getString("b");
								if(pan=="0"){
								String qs="update release booking set b=? where id=? and year=? and month=? and day=?";
								java.sql.PreparedStatement pss=conn.prepareStatement(qs);
								pss.setString(1, "1");
								pss.setString(2, id);
								pss.setString(3, year);
								pss.setString(4, month);
								pss.setString(5, day);pss.executeUpdate();
								pss.close();
								}
							}

							else if(B[i]=='c')
							{
								String pan=rs.getString("c");
								if(pan=="0"){
								String qs="update release booking set c=? where id=? and year=? and month=? and day=?";
								java.sql.PreparedStatement pss=conn.prepareStatement(qs);
								pss.setString(1, "1");
								pss.setString(2, id);
								pss.setString(3, year);
								pss.setString(4, month);
								pss.setString(5, day);pss.executeUpdate();
								pss.close();
								}
							}
							else if(B[i]=='d')
							{
								String pan=rs.getString("d");
								if(pan=="0"){
								String qs="update release booking set d=? where id=? and year=? and month=? and day=?";
								java.sql.PreparedStatement pss=conn.prepareStatement(qs);
								pss.setString(1,"1");
								pss.setString(2, id);
								pss.setString(3, year);
								pss.setString(4, month);
								pss.setString(5, day);pss.executeUpdate();
								pss.close();
								}
							}
							else if(B[i]=='e')
							{
								String pan=rs.getString("e");
								if(pan=="0"){
								String qs="update release booking set e=? where id=? and year=? and month=? and day=?";
								java.sql.PreparedStatement pss=conn.prepareStatement(qs);
								pss.setString(1, "1");
								pss.setString(2, id);
								pss.setString(3, year);
								pss.setString(4, month);
								pss.setString(5, day);pss.executeUpdate();
								pss.close();
								}
							}
							else if(B[i]=='f')
							{
								String pan=rs.getString("f");
								if(pan=="0"){
								String qs="update release booking set f=? where id=? and year=? and month=? and day=?";
								java.sql.PreparedStatement pss=conn.prepareStatement(qs);
								pss.setString(1, "1");
								pss.setString(2, id);
								pss.setString(3, year);
								pss.setString(4, month);
								pss.setString(5, day);pss.executeUpdate();
								pss.close();
								}
							}
							else if(B[i]=='g')
							{
								String pan=rs.getString("g");
								if(pan=="0"){
								String qs="update release booking set g=? where id=? and year=? and month=? and day=?";
								java.sql.PreparedStatement pss=conn.prepareStatement(qs);
								pss.setString(1, "1");
								pss.setString(2, id);
								pss.setString(3, year);
								pss.setString(4, month);
								pss.setString(5, day);pss.executeUpdate();
								pss.close();
								}
							}
							else if(B[i]=='h')
							{
								String pan=rs.getString("h");
								if(pan=="0"){
								String qs="update release booking set h=? where id=? and year=? and month=? and day=?";
								java.sql.PreparedStatement pss=conn.prepareStatement(qs);
								pss.setString(1, "1");
								pss.setString(2, id);
								pss.setString(3, year);
								pss.setString(4, month);
								pss.setString(5, day);pss.executeUpdate();
								pss.close();
								}
							}
							else if(B[i]=='i')
							{
								String pan=rs.getString("i");
								if(pan=="0"){
								String qs="update release booking set i=? where id=? and year=? and month=? and day=?";
								java.sql.PreparedStatement pss=conn.prepareStatement(qs);
								pss.setString(1, "1");
								pss.setString(2, id);
								pss.setString(3, year);
								pss.setString(4, month);
								pss.setString(5, day);pss.executeUpdate();
								pss.close();
								}
							}
							else if(B[i]=='j')
							{
								String pan=rs.getString("j");
								if(pan=="0"){
								String qs="update release booking set j=? where id=? and year=? and month=? and day=?";
								java.sql.PreparedStatement pss=conn.prepareStatement(qs);
								pss.setString(1, "1");
								pss.setString(2, id);
								pss.setString(3, year);
								pss.setString(4, month);
								pss.setString(5, day);pss.executeUpdate();
								pss.close();
								}
							}
							else if(B[i]=='k')
							{
								String pan=rs.getString("k");
								if(pan=="0"){
								String qs="update release booking set k=? where id=? and year=? and month=? and day=?";
								java.sql.PreparedStatement pss=conn.prepareStatement(qs);
								pss.setString(1, "1");
								pss.setString(2, id);
								pss.setString(3, year);
								pss.setString(4, month);
								pss.setString(5, day);pss.executeUpdate();
								pss.close();
								}
							}
							else if(B[i]=='l')
							{
								String pan=rs.getString("l");
								if(pan=="0"){
								String qs="update release booking set l=? where id=? and year=? and month=? and day=?";
								java.sql.PreparedStatement pss=conn.prepareStatement(qs);
								pss.setString(1, "1");
								pss.setString(2, id);
								pss.setString(3, year);
								pss.setString(4, month);
								pss.setString(5, day);pss.executeUpdate();
								pss.close();
								}
							}
							else if(B[i]=='m')
							{
								String pan=rs.getString("m");
								if(pan=="0"){
								String qs="update release booking set m=? where id=? and year=? and month=? and day=?";
								java.sql.PreparedStatement pss=conn.prepareStatement(qs);
								pss.setString(1, "1");
								pss.setString(2, id);
								pss.setString(3, year);
								pss.setString(4, month);
								pss.setString(5, day);pss.executeUpdate();
								pss.close();
								}
							}
							else if(B[i]=='n')
							{
								String pan=rs.getString("n");
								if(pan=="0"){
								String qs="update release booking set n=? where id=? and year=? and month=? and day=?";
								java.sql.PreparedStatement pss=conn.prepareStatement(qs);
								pss.setString(1, "1");
								pss.setString(2, id);
								pss.setString(3, year);
								pss.setString(4, month);
								pss.setString(5, day);pss.executeUpdate();
								pss.close();
								}
							}
					}
				}
			}
			else{
				String sq="insert into releasebooking (id,year,month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				java.sql.PreparedStatement sp=conn.prepareStatement(sq);
				/*rb.setId(id);rb.setDay(day);
				rb.setYear(year);rb.setA(A[0]);
				rb.setMonth(month);rb.setB(A[1]);
				rb.setC(A[2]);rb.setD(A[3]);
				rb.setE(A[4]);rb.setF(A[5]);
				rb.setG(A[6]);rb.setH(A[7]);
				rb.setI(A[8]);rb.setJ(A[9]);
				rb.setK(A[10]);rb.setL(A[11]);
				rb.setM(A[12]);rb.setN(A[13]);
				sp.setString(1, rb.getId());sp.setString(3, rb.getMonth());
				sp.setString(2, rb.getYear());sp.setString(4, rb.getDay());
				sp.setString(5, rb.getA());sp.setString(6, rb.getB());
				sp.setString(7, rb.getC());sp.setString(8, rb.getD());
				sp.setString(9, rb.getE());sp.setString(10, rb.getF());
				sp.setString(11, rb.getG());sp.setString(12, rb.getH());
				sp.setString(13, rb.getI());sp.setString(14, rb.getJ());
				sp.setString(15, rb.getK());sp.setString(16, rb.getL());
				sp.setString(17, rb.getM());sp.setString(18, rb.getN());*/
				sp.setString(1, id);sp.setString(2, year);sp.setString(3, month);
				sp.setString(4,day);sp.setString(5,A[0]);sp.setString(6, A[1]);
				sp.setString(7, A[2]);sp.setString(8,A[2]);sp.setString(9, A[4]);
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
	
	
	
	public boolean appointment(String tid,String sid,String A[],String year,String month,String day)
	{
		 Connection conn = null;
	         
	        try{
	            
	            conn = getConn();
	            char [] B=new char [14];
	            B[0]='a';B[1]='b';B[2]='c';B[3]='d';B[4]='e';B[5]='f';B[6]='g';B[7]='h';B[8]='i';
	   		 	B[9]='j';B[10]='k';B[11]='l';B[12]='m';B[13]='n';
	   		 	int i;
	   		 	for(i=0;i<14;i++)
	   		 	{
	   		 		if(!A[i].equals("0"))
	   		 		{
	   		 		System.out.println("进入为1的");
		   		 		if(B[i]=='a')
						{
		   		 			String sql;
		   		 			java.sql.PreparedStatement pstmt = null;
		   		 			sql = "SELECT id, year, month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM releasebooking where id=? and year=? and month=? and day=? and a=?";
		   		 			pstmt = conn.prepareStatement(sql); 
		   		 			pstmt.setString(1, tid);pstmt.setString(2, year);
		   		 			pstmt.setString(3, month);pstmt.setString(4, day);pstmt.setString(5, "1");
		   		 			ResultSet rs = pstmt.executeQuery();
		   		 			if(rs.next())
		   		 			{
		   		 				String sq;java.sql.PreparedStatement pstm = null;
		   		 				sq="update releasebooking set a=? where id=? and year=? and month=? and day=?";
		   		 				pstm=conn.prepareStatement(sq);
		   		 				pstm.setString(1, "2");pstm.setString(2, tid);pstm.setString(3,year);
		   		 				pstm.setString(4, month);pstm.setString(5, day);
		   		 				pstm.executeUpdate();
		   		 				sq="insert into successbooking (teacherid, year,month,day,time,studentid)  values(?,?,?,?,?,?)";
		   		 				java.sql.PreparedStatement pstm1 = null;
		   		 				pstm1 = conn.prepareStatement(sq); 
		   		 				pstm1.setString(1, tid);
		   		 				pstm1.setString(2, year);
		   		 				pstm1.setString(3,month);pstm1.setString(4,day);
		   		 				pstm1.setString(5, "8:00-8:30");pstm1.setString(6, sid);
		   		 				pstm1.executeUpdate();
		   		 				pstm1.close();
		   		 				pstm.close();
		   		 				System.out.println("a成功写入");
		   		 			}
		   		 			else{rs.close();pstmt.close();}
		   		 		}
		   		 		else if(B[i]=='b')
		   		 		{
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
		   		 				sq="insert into successbooking (teacherid, year,month,day,time,studentid)  values(?,?,?,?,?,?)";
		   		 			java.sql.PreparedStatement pstm1 = null;
	   		 				pstm1 = conn.prepareStatement(sq); 
		   		 				pstm1.setString(1, tid);pstm1.setString(2, year);
		   		 				pstm1.setString(3,month);pstm1.setString(4,day);
		   		 				pstm1.setString(5, "8:30-9:00");pstm1.setString(6, sid);
		   		 				pstm1.executeUpdate();
		   		 				pstm1.close();
		   		 				pstm.close();
		   		 				System.out.println("b成功写入");
		   		 			}
		   		 			else{rs.close();pstmt.close();}
		   		 			}
			   		 	else if(B[i]=='c')
		   		 		{
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
		   		 				sq="insert into successbooking (teacherid, year,month,day,time,studentid)  values(?,?,?,?,?,?)";
		   		 			java.sql.PreparedStatement pstm1 = null;
	   		 				pstm1 = conn.prepareStatement(sq); 
		   		 				pstm1.setString(1, tid);pstm1.setString(2, year);
		   		 				pstm1.setString(3,month);pstm1.setString(4,day);
		   		 				pstm1.setString(5, "9:00-9:30");pstm1.setString(6, sid);
		   		 				pstm1.executeUpdate();
		   		 				pstm1.close();
		   		 				pstm.close();
		   		 			System.out.println("c成功写入");
		   		 			}
		   		 			else{rs.close();pstmt.close();}
		   		 			}
				   		 else if(B[i]=='d')
			   		 		{
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
			   		 				sq="insert into successbooking (teacherid, year,month,day,time,studentid)  values(?,?,?,?,?,?)";
			   		 			java.sql.PreparedStatement pstm1 = null;
		   		 				pstm1 = conn.prepareStatement(sq); 
			   		 				pstm1.setString(1, tid);pstm1.setString(2, year);
			   		 				pstm1.setString(3,month);pstm1.setString(4,day);
			   		 				pstm1.setString(5, "9:30-10:00");pstm1.setString(6, sid);
			   		 				pstm1.executeUpdate();
			   		 				pstm1.close();
			   		 				pstm.close();
			   		 			System.out.println("d成功写入");
			   		 			}
			   		 			else{rs.close();pstmt.close();}
			   		 			}
					   		else if(B[i]=='e')
			   		 		{
					   			System.out.println("e进入");
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
			   		 				sq="insert into successbooking (teacherid, year,month,day,time,studentid)  values(?,?,?,?,?,?)";
			   		 			java.sql.PreparedStatement pstm1 = null;
		   		 				pstm1 = conn.prepareStatement(sq); 
			   		 				pstm1.setString(1, tid);pstm1.setString(2, year);
			   		 				pstm1.setString(3,month);pstm1.setString(4,day);
			   		 				pstm1.setString(5, "10:00-10:30");pstm1.setString(6, sid);
			   		 				pstm1.executeUpdate();
			   		 				pstm1.close();
			   		 				pstm.close();
			   		 			System.out.println("e成功写入");
			   		 			}
			   		 			else{rs.close();pstmt.close();}
			   		 			}
					   		else if(B[i]=='f')
			   		 		{
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
			   		 				sq="insert into successbooking (teacherid, year,month,day,time,studentid)  values(?,?,?,?,?,?)";
			   		 			java.sql.PreparedStatement pstm1 = null;
		   		 				pstm1 = conn.prepareStatement(sq); 
			   		 				pstm1.setString(1, tid);pstm1.setString(2, year);
			   		 				pstm1.setString(3,month);pstm1.setString(4,day);
			   		 				pstm1.setString(5, "10:30-11:00");pstm1.setString(6, sid);
			   		 				pstm1.executeUpdate();
			   		 				pstm1.close();
			   		 				pstm.close();
			   		 			System.out.println("f成功写入");
			   		 			}
			   		 			else{rs.close();pstmt.close();}
			   		 			}
					   		else if(B[i]=='g')
			   		 		{
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
			   		 				sq="insert into successbooking (teacherid, year,month,day,time,studentid)  values(?,?,?,?,?,?)";
			   		 			java.sql.PreparedStatement pstm1 = null;
		   		 				pstm1 = conn.prepareStatement(sq); 
			   		 				pstm1.setString(1, tid);pstm1.setString(2, year);
			   		 				pstm1.setString(3,month);pstm1.setString(4,day);
			   		 				pstm1.setString(5, "10:30-11:00");pstm1.setString(6, sid);
			   		 				pstm1.executeUpdate();
			   		 				pstm1.close();
			   		 				pstm.close();
			   		 			System.out.println("g成功写入");
			   		 			}
			   		 			else{rs.close();pstmt.close();}
			   		 			}
					   		else if(B[i]=='h')
			   		 		{
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
			   		 				sq="insert into successbooking (teacherid, year,month,day,time,studentid)  values(?,?,?,?,?,?)";
			   		 			java.sql.PreparedStatement pstm1 = null;
		   		 				pstm1 = conn.prepareStatement(sq); 
			   		 				pstm1.setString(1, tid);pstm1.setString(2, year);
			   		 				pstm1.setString(3,month);pstm1.setString(4,day);
			   		 				pstm1.setString(5, "14:00-14:30");pstm1.setString(6, sid);
			   		 				pstm1.executeUpdate();
			   		 				pstm1.close();
			   		 				pstm.close();
			   		 			System.out.println("h成功写入");
			   		 			}
			   		 			else{rs.close();pstmt.close();}
			   		 			}
					   		else if(B[i]=='i')
			   		 		{
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
			   		 				sq="insert into successbooking (teacherid, year,month,day,time,studentid)  values(?,?,?,?,?,?)";
			   		 			java.sql.PreparedStatement pstm1 = null;
		   		 				pstm1 = conn.prepareStatement(sq); 
			   		 				pstm1.setString(1, tid);pstm1.setString(2, year);
			   		 				pstm1.setString(3,month);pstm1.setString(4,day);
			   		 				pstm1.setString(5, "14:30-15:00");pstm1.setString(6, sid);
			   		 				pstm1.executeUpdate();
			   		 				pstm1.close();
			   		 				pstm.close();
			   		 			System.out.println("i成功写入");
			   		 			}
			   		 			else{rs.close();pstmt.close();}
			   		 			}
					   		else if(B[i]=='j')
			   		 		{
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
			   		 				sq="insert into successbooking (teacherid, year,month,day,time,studentid)  values(?,?,?,?,?,?)";
			   		 			java.sql.PreparedStatement pstm1 = null;
		   		 				pstm1 = conn.prepareStatement(sq); 
			   		 				pstm1.setString(1, tid);pstm1.setString(2, year);
			   		 				pstm1.setString(3,month);pstm1.setString(4,day);
			   		 				pstm1.setString(5, "15:00-15:30");pstm1.setString(6, sid);
			   		 				pstm1.executeUpdate();
			   		 				pstm1.close();
			   		 				pstm.close();
			   		 			System.out.println("j成功写入");
			   		 			}
			   		 			else{rs.close();pstmt.close();}
			   		 			}
					   		else if(B[i]=='k')
			   		 		{
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
			   		 				sq="insert into successbooking (teacherid, year,month,day,time,studentid)  values(?,?,?,?,?,?)";
			   		 			java.sql.PreparedStatement pstm1 = null;
		   		 				pstm1 = conn.prepareStatement(sq); 
			   		 				pstm1.setString(1, tid);pstm1.setString(2, year);
			   		 				pstm1.setString(3,month);pstm1.setString(4,day);
			   		 				pstm1.setString(5, "15:30-16:00");pstm1.setString(6, sid);
			   		 				pstm1.executeUpdate();
			   		 				pstm1.close();
			   		 				pstm.close();
			   		 			System.out.println("k成功写入");
			   		 			}
			   		 			else{rs.close();pstmt.close();}
			   		 			}
					   		else if(B[i]=='l')
			   		 		{
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
			   		 				sq="insert into successbooking (teacherid, year,month,day,time,studentid)  values(?,?,?,?,?,?)";
			   		 			java.sql.PreparedStatement pstm1 = null;
		   		 				pstm1 = conn.prepareStatement(sq); 
			   		 				pstm1.setString(1, tid);pstm1.setString(2, year);
			   		 				pstm1.setString(3,month);pstm1.setString(4,day);
			   		 				pstm1.setString(5, "16:00-16:30");pstm1.setString(6, sid);
			   		 				pstm1.executeUpdate();
			   		 				pstm1.close();
			   		 				pstm.close();
			   		 			System.out.println("l成功写入");
			   		 			}
			   		 			else{rs.close();pstmt.close();}
			   		 			}
					   		else if(B[i]=='m')
			   		 		{
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
			   		 				sq="insert into successbooking (teacherid, year,month,day,time,studentid)  values(?,?,?,?,?,?)";
			   		 			java.sql.PreparedStatement pstm1 = null;
		   		 				pstm1 = conn.prepareStatement(sq); 
			   		 				pstm1.setString(1, tid);pstm1.setString(2, year);
			   		 				pstm1.setString(3,month);pstm1.setString(4,day);
			   		 				pstm1.setString(5, "16:30-17:00");pstm1.setString(6, sid);
			   		 				pstm1.executeUpdate();
			   		 				pstm1.close();
			   		 				pstm.close();
			   		 			System.out.println("m成功写入");
			   		 			}
			   		 			else{rs.close();pstmt.close();}
			   		 			}
					   		else if(B[i]=='n')
			   		 		{
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
			   		 				sq="insert into successbooking (teacherid, year,month,day,time,studentid)  values(?,?,?,?,?,?)";
			   		 			java.sql.PreparedStatement pstm1 = null;
		   		 				pstm1 = conn.prepareStatement(sq); 
			   		 				pstm1.setString(1, tid);pstm1.setString(2, year);
			   		 				pstm1.setString(3,month);pstm1.setString(4,day);
			   		 				pstm1.setString(5, "17:00-17:30");pstm1.setString(6, sid);
			   		 				pstm1.executeUpdate();
			   		 				pstm1.close();
			   		 				pstm.close();
			   		 			System.out.println("n成功写入");
			   		 			}
			   		 			else{rs.close();pstmt.close();}
			   		 			}
						}
	   		 		}
	   		 	}catch(Exception e){
	   				System.out.print("信息添加失败！");
	   				e.printStackTrace();
	   			}
		return false;
		
	}
	
	public List<releasebooking> Queryateacher(String id)
	{
        Connection conn = null;
        
        java.sql.PreparedStatement pstmt = null; 
        List<releasebooking> booking=new LinkedList<releasebooking>();
        try{
            
            conn = getConn();
        
            // 执行查询
            System.out.println(" 实例化Statement对...");
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
            	System.out.println("ISBN: " + book.month);
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
				   		 		}
			   		 		else if(B[i]=='b')
			   		 		{
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
			   		 			}
				   		 	else if(B[i]=='c')
			   		 		{
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
			   		 			}
					   		 else if(B[i]=='d')
				   		 		{
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
				   		 			}
						   		else if(B[i]=='e')
				   		 		{
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
				   		 			}
						   		else if(B[i]=='f')
				   		 		{
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
				   		 			}
						   		else if(B[i]=='g')
				   		 		{
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
				   		 				pstm1.setString(5, "10:30-11:00");pstm1.setString(6, sid);pstm1.setString(7, instruction);
				   		 				pstm1.executeUpdate(); rs.close();
				   		 				pstm1.close();
				   		 				pstm.close();
				   		 			}
				   		 			else{rs.close();pstmt.close();}
				   		 			}
						   		else if(B[i]=='h')
				   		 		{
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
				   		 			}
						   		else if(B[i]=='i')
				   		 		{
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
				   		 			}
						   		else if(B[i]=='j')
				   		 		{
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
				   		 			}
						   		else if(B[i]=='k')
				   		 		{
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
				   		 			}
						   		else if(B[i]=='l')
				   		 		{
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
				   		 			}
						   		else if(B[i]=='m')
				   		 		{
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
				   		 			}
						   		else if(B[i]=='n')
				   		 		{
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
				   		 			}
							}
		   		 		}
		   		 	}catch(Exception e){
		   				System.out.print("信息添加失败！");
		   				e.printStackTrace();
		   			}
			return false;
		
	}
	
	
	
	
}

	
	
	
	
	
