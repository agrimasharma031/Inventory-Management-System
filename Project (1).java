import java.util.Scanner;
import java.sql.*;
class Admin
{	
	int id;
	String Name;
	Admin(int sid,String n)
	{
		id=sid;
		n=Name;
	}
	void AdminMenu()
	{
		System.out.println("1,Manager ");
		System.out.println("2,Employee ");
		System.out.println("3,View project ");

	}	
}
class Manager
{	
	int id;
	String Name;
	Manager(int sid,String n)
	{
		id=sid;
		n=Name
	}
	void ManagerMenu()
	{
		System.out.println("Welcome*** "+Name+"***");
		System.out.println("1,Update profile ");
		System.out.println("2,Add Employee");
		System.out.println("3,Add Bug report");

	}	
}
}
class Login
{
	public static void main(String...arg)
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter Username : ");
		int uid=sc.nextInt();
		System.out.print("Enter Password : ");
		String pwd=new String(System.console().readPassword());
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/iit,"root","r1oo2t3");
		PreparedStatement pst=con.prepareStatement("select * from emp where emp_id=?'emp_pwd=?");
		pst.setInt(1,uid);
		pst.setString(2,pwd);
		ResultSet res=pst.executeQuery();
		if(res.next())
		{
			int id=res.getInt(1);
			String n=res.getString(2);
			String role=res.getString(6);
			if(role.equals("admin"))
			{
				Admin obj=new Admin(id,n);
				obj.AdminMenu();
				
			}
			else if(role.equals("manager"))
			{
				Manager ob=new Manager(id,n);
				ob.ManagerMenu();
			}
			
		}
		else
		{
			System.out.print("Invalid userid or password");
		}

	}
}