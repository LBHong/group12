import java.sql.SQLException;
import java.util.List;
import com.user.userteacher.teacher;
import com.user.mysqloperate.Mysqloperate;

public class test {
	public static void main(String[] args) throws SQLException
	{
		/*System.out.println("begin");
		List<teacher> te;
		Mysqloperate q=new Mysqloperate();
		te=q.chaxunallteachers("1");
		System.out.println(te.size());
		for(int i = 0; i < te.size(); i++) { 

		    System.out.println(te.get(i).id); 

		}
		System.out.println("end");
		return;*/
		/*Mysqloperate q=new Mysqloperate();
		String[] a=new String[14];
		a=q.teachershow("11","1","1","1");
		for(int i = 0; i < a.length; i++) { 

		    System.out.println(a[i]); 

		}*/
		String [] C=new String [14];
		Mysqloperate sqlOprate=new Mysqloperate();
		
			C[0]="1";C[1]="0";C[2]="0";C[3]="0";C[4]="1";C[5]="1";C[6]="1";C[7]="1";C[8]="1";
			C[9]="1";C[10]="1";C[11]="1";C[12]="1";C[13]="1";
			boolean d;
		
			d=sqlOprate.appointment("111","000", C, "2017","02","20");
		

	}
}
