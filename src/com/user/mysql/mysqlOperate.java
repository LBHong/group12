package com.user.mysql;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import com.user.userteacher.teacher;
import com.user.userstudent.student;
import com.user.releasebooking.releasebooking;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
public class mysqlOperate {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://w.";
 
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "z5omyo340o";
    static final String PASS = "123456";
	public   Map<String,String> showstudent(String id)
	{
		Map<String,String> detailMap=new HashMap<String,String>();
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
	    		 return null;
	    	 }
	    	 else
	    	 {
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
	    		 return null;
	    	 }
	    	 else
	    	 {
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
	public boolean login(String password,String id) throws SQLException
	{
		Map<String,String> detailMap=new HashMap<String,String>();
	        if(showstudent(id)!=null)
	        {
	        	detailMap=showstudent(id);
	        	String key="密码";		
	        	String value=detailMap.get(key);
	            if(password==value)
	        	{
	        		return true;
	        	}
	            else{
	        			return false;
	        	}
	        			       	
	        }
	        else if(showteacher(id)!=null)
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
	        		return true;
	        	}
	        	else{
	        			return false;
	        	}
	        }
			return false;
	}

	public boolean releasebooking(String A[],String id,String year,String month,String day)
	{
		 char [] B=new char [14];
		 B[0]='a';B[1]='b';B[2]='c';B[3]='d';B[4]='e';B[5]='f';B[6]='g';B[7]='h';B[8]='i';
		 B[9]='j';B[10]='k';B[11]='l';B[12]='m';B[13]='n';
		releasebooking rb=new releasebooking();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://w.rdc.sae.sina.com.cn:3306/app_tianchen1016";
			String username="z5omyo340o";
			String password="j520jylh02zl30jxki4w0zj31k15xj023kj5534y";
			Connection conn=DriverManager.getConnection(url,username,password);
			//String sql="replace into release booking(id,year,month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			String sql = "SELECT id, year, month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n FROM release booking where id=? and year=? and month=? and day=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,id);ps.setString(2, year);
			ps.setString(3, month);ps.setString(4,day);
			ResultSet rs = ps.executeQuery();
			int i;
			if(rs.next())
			{
				for(i=0;i<14;i++)
				{
					if(A[i].equals("0")){}
					else{
							if(B[i]=='a')
							{
								String qs="update release booking set a=? where id=? and year=? and month=? and day=?";
								PreparedStatement pss=conn.prepareStatement(qs);
								pss.setString(1, A[0]);
								pss.setString(2, id);
								pss.setString(3, year);
								pss.setString(4, month);
								pss.setString(5, day);
								pss.executeUpdate();
								pss.close();
							}
							else if(B[i]=='b')
							{
								String qs="update release booking set b=? where id=? and year=? and month=? and day=?";
								PreparedStatement pss=conn.prepareStatement(qs);
								pss.setString(1, A[1]);
								pss.setString(2, id);
								pss.setString(3, year);
								pss.setString(4, month);
								pss.setString(5, day);pss.executeUpdate();
								pss.close();
							}
							else if(B[i]=='c')
							{
								String qs="update release booking set c=? where id=? and year=? and month=? and day=?";
								PreparedStatement pss=conn.prepareStatement(qs);
								pss.setString(1, A[2]);
								pss.setString(2, id);
								pss.setString(3, year);
								pss.setString(4, month);
								pss.setString(5, day);pss.executeUpdate();
								pss.close();
							}
							else if(B[i]=='d')
							{
								String qs="update release booking set d=? where id=? and year=? and month=? and day=?";
								PreparedStatement pss=conn.prepareStatement(qs);
								pss.setString(1, A[3]);
								pss.setString(2, id);
								pss.setString(3, year);
								pss.setString(4, month);
								pss.setString(5, day);pss.executeUpdate();
								pss.close();
							}
							else if(B[i]=='e')
							{
								String qs="update release booking set e=? where id=? and year=? and month=? and day=?";
								PreparedStatement pss=conn.prepareStatement(qs);
								pss.setString(1, A[4]);
								pss.setString(2, id);
								pss.setString(3, year);
								pss.setString(4, month);
								pss.setString(5, day);pss.executeUpdate();
								pss.close();
							}
							else if(B[i]=='f')
							{
								String qs="update release booking set f=? where id=? and year=? and month=? and day=?";
								PreparedStatement pss=conn.prepareStatement(qs);
								pss.setString(1, A[5]);
								pss.setString(2, id);
								pss.setString(3, year);
								pss.setString(4, month);
								pss.setString(5, day);pss.executeUpdate();
								pss.close();
							}
							else if(B[i]=='g')
							{
								String qs="update release booking set g=? where id=? and year=? and month=? and day=?";
								PreparedStatement pss=conn.prepareStatement(qs);
								pss.setString(1, A[6]);
								pss.setString(2, id);
								pss.setString(3, year);
								pss.setString(4, month);
								pss.setString(5, day);pss.executeUpdate();
								pss.close();
							}
							else if(B[i]=='h')
							{
								String qs="update release booking set h=? where id=? and year=? and month=? and day=?";
								PreparedStatement pss=conn.prepareStatement(qs);
								pss.setString(1, A[7]);
								pss.setString(2, id);
								pss.setString(3, year);
								pss.setString(4, month);
								pss.setString(5, day);pss.executeUpdate();
								pss.close();
							}
							else if(B[i]=='i')
							{
								String qs="update release booking set i=? where id=? and year=? and month=? and day=?";
								PreparedStatement pss=conn.prepareStatement(qs);
								pss.setString(1, A[8]);
								pss.setString(2, id);
								pss.setString(3, year);
								pss.setString(4, month);
								pss.setString(5, day);pss.executeUpdate();
								pss.close();
							}
							else if(B[i]=='j')
							{
								String qs="update release booking set j=? where id=? and year=? and month=? and day=?";
								PreparedStatement pss=conn.prepareStatement(qs);
								pss.setString(1, A[9]);
								pss.setString(2, id);
								pss.setString(3, year);
								pss.setString(4, month);
								pss.setString(5, day);pss.executeUpdate();
								pss.close();
							}
							else if(B[i]=='k')
							{
								String qs="update release booking set k=? where id=? and year=? and month=? and day=?";
								PreparedStatement pss=conn.prepareStatement(qs);
								pss.setString(1, A[10]);
								pss.setString(2, id);
								pss.setString(3, year);
								pss.setString(4, month);
								pss.setString(5, day);pss.executeUpdate();
								pss.close();
							}
							else if(B[i]=='l')
							{
								String qs="update release booking set l=? where id=? and year=? and month=? and day=?";
								PreparedStatement pss=conn.prepareStatement(qs);
								pss.setString(1, A[11]);
								pss.setString(2, id);
								pss.setString(3, year);
								pss.setString(4, month);
								pss.setString(5, day);pss.executeUpdate();
								pss.close();
							}
							else if(B[i]=='m')
							{
								String qs="update release booking set m=? where id=? and year=? and month=? and day=?";
								PreparedStatement pss=conn.prepareStatement(qs);
								pss.setString(1, A[12]);
								pss.setString(2, id);
								pss.setString(3, year);
								pss.setString(4, month);
								pss.setString(5, day);pss.executeUpdate();
								pss.close();
							}
							else if(B[i]=='n')
							{
								String qs="update release booking set n=? where id=? and year=? and month=? and day=?";
								PreparedStatement pss=conn.prepareStatement(qs);
								pss.setString(1, A[13]);
								pss.setString(2, id);
								pss.setString(3, year);
								pss.setString(4, month);
								pss.setString(5, day);pss.executeUpdate();
								pss.close();
							}
					}
				}
			}
			else{
				String sq="insert into release booking (id,year,month,day,a,b,c,d,e,f,g,h,i,j,k,l,m,n) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement sp=conn.prepareStatement(sq);
				rb.setId(id);rb.setDay(day);
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
				sp.setString(17, rb.getM());sp.setString(18, rb.getN());
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
			/*rb.setId(id);rb.setDay(day);
			rb.setYear(year);rb.setA(A[0]);
			rb.setMonth(month);rb.setB(A[1]);
			rb.setC(A[2]);rb.setD(A[3]);
			rb.setE(A[4]);rb.setF(A[5]);
			rb.setG(A[6]);rb.setH(A[7]);
			rb.setI(A[8]);rb.setJ(A[9]);
			rb.setK(A[10]);rb.setL(A[11]);
			rb.setM(A[12]);rb.setN(A[13]);
			ps.setString(1, rb.getId());ps.setInt(3, rb.getMonth());
			ps.setInt(2, rb.getYear());ps.setInt(4, rb.getDay());
			ps.setChar(5, rb.getA());*/
		return true;		
	}
}