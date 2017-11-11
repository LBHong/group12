package main;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import com.user.userteacher.teacher;
import com.user.userstudent.student;
import com.user.releasebooking.releasebooking;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import com.user.mysql.mysqlOperate;
public class main1 {
	
	static String [] C=new String [14];
	private static mysqlOperate sqlOprate=new mysqlOperate();
	public static void main(String[] args)
	{
		C[0]="1";C[1]="1";C[2]="1";C[3]="1";C[4]="1";C[5]="1";C[6]="1";C[7]="1";C[8]="1";
		C[9]="1";C[10]="1";C[11]="1";C[12]="1";C[13]="0";
		boolean d;
	
		d=sqlOprate.releasebooking(C,"115031050","2018","12","12");
	}
}
