import java.sql.*;
import java.io.*;
import java.util.*;


class IMS
{
	
	static Connection con;
	static Scanner sc=new Scanner(System.in);
	static 
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ims","root","root");
			if(con!=null)
			{
				System.out.println("Connection open....");
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		}
	public static void clearScreen()
	{
		try
		{
		new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
		}
		catch(Exception e){}
	}

	public static void colorChange1()
	{
		try
		{
		new ProcessBuilder("cmd","/c","color 4f").inheritIO().start().waitFor();
		}
		catch(Exception e){}
	}

	public static void colorChange2()
	{
		try
		{
		new ProcessBuilder("cmd","/c","color 70").inheritIO().start().waitFor();
		}
		catch(Exception e){}
	}

	public static void colorChange3()
	{
		try
		{
		new ProcessBuilder("cmd","/c","color ec").inheritIO().start().waitFor();
		}
		catch(Exception e){}
	}
	
	public static void space()
	{
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
	}		
	
	

	public static void InsertProductData() throws SQLException
	{
		space();
		colorChange3() ;
		System.out.print("\t\t\tEnter Product Id: ");
		int id=sc.nextInt();

		System.out.print("\t\t\tEnter Product Name: ");
		sc.nextLine();
		String name=sc.nextLine();

		System.out.print("\t\t\tEnter Purchase Price: ");
		float prcprice=sc.nextFloat();

		System.out.print("\t\t\tEnter Sale Price: ");
		float saleprice=sc.nextFloat();

		System.out.print("\t\t\tEnter Product Quantity: ");
		int pdQty=sc.nextInt();

	
		PreparedStatement pst=con.prepareStatement("insert into product values(?,?,?,?,?)");
		pst.setInt(1,id);
		pst.setString(2,name);
		pst.setFloat(5,prcprice);
		pst.setFloat(3,saleprice);
		pst.setInt(4,pdQty);
		if(pst.executeUpdate()>0)
		{
		space();
		System.out.println("\t\t\tData inserted.");}
		else
		{space();
		System.out.println("\t\t\tData Not inserted.");}
		colorChange2() ;
		}

	public static void updateProductQty() throws SQLException
	{
		space();
		colorChange3() ;
		System.out.print("\t\t\tEnter product id:");
		int pid=sc.nextInt();
		PreparedStatement pst=con.prepareStatement("select * from product where productId=?");
		pst.setInt(1,pid);
		ResultSet rs=pst.executeQuery();
		if(rs.next())
		{
		pst=con.prepareStatement("update  product set productQty=? where productId=?");
		pst.setInt(2,pid);
		space();
		System.out.print("\t\t\tEnter the product quantity:");
		int pdq=sc.nextInt();
		pst.setInt(1,pdq);}
		if(pst.executeUpdate()>0){
			space();
			System.out.println("\t\t\tRecord Upadated");
		}
		else{
			System.out.println("\t\t\tRecored not Updated");
		}
		colorChange2() ;	
	}
	public static void deletePro() throws Exception
	{
	try
		{
		space();
		colorChange3() ;
		System.out.print("\t\t\tEnter product id:");
		int pid=sc.nextInt();
		PreparedStatement pst=con.prepareStatement("select * from product where productId=?");
		pst.setInt(1,pid);
		ResultSet rs=pst.executeQuery();
		if(rs.next())
		{
		pst=con.prepareStatement("delete  from product  where productId=?");
		pst.setInt(1,pid);
		if(pst.executeUpdate()>0){
			System.out.println("\t\t\tRecord Deleted successfully");
		}
		else{
			System.out.println("\t\t\tRecored not Deleted");
		}
		}
	else
	{
	
	System.out.println("\t\t\tRecord not found");
	}
	}
	catch(Exception e){System.out.println(e);}
	colorChange2() ;
	}


	public static void showPro()throws SQLException
	{

	colorChange3() ;
	PreparedStatement pst=con.prepareStatement("select * from product");
	ResultSet rs=pst.executeQuery();
	String x = String.format("%14s %18s %18s %14s %14s","Product ID","Product Name","Purchase Price","Sale Price","Qty") ;
	System.out.println(x);
	System.out.println("----------------------------------------------------------------------------------------");

	while(rs.next())
	{
	int pid=rs.getInt(1);
	String pname=rs.getString(2);
	float price=rs.getFloat(5);
	float sprice=rs.getFloat(3);
	int pqty=rs.getInt(4);
	String y = String.format("%14s %18s %18s %14s %14s",pid,pname,price,sprice,pqty) ;
	System.out.println(y);
	}
	colorChange2() ;
	}






    public static void insertproductsaledetail() throws Exception
      {
       try{
	space();
	colorChange3() ;
      System.out.print("\t\t\tEnter Sale Id:");
      int id1=sc.nextInt();
      System.out.print("\t\t\tEnter Product Id");
      int id2=sc.nextInt();  
      System.out.print("\t\t\tEnter Sale Price");
      float ssp=sc.nextFloat();
      System.out.print("\t\t\tEnter sale Quantity ");
      int qtys=sc.nextInt();
      System.out.print("\t\t\tEnter DATE As YY//MM/DD ");
	sc.nextLine();
      String date=sc.nextLine();
     
      PreparedStatement pst=con.prepareStatement("insert into sale values(?,?,?,?,?)");
      pst.setInt(1,id1);
      pst.setInt(2,id2);
      pst.setFloat(3,ssp);
      pst.setInt(5,qtys);
	java.util.Date d=new java.util.Date(date);
      java.sql.Date d1=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
      pst.setDate(4,d1);  //Parameterized query
  
      if(pst.executeUpdate()>0)
      {
	
          System.out.println("\t\t\tNew Sale Details Inserted");
      }    
       else
       {	
	
          System.out.println("\t\t\tSale Data not Inserted");
       }   
            }
         
      catch(InputMismatchException e){System.out.println("e.getMessage");} 
      catch(SQLException e){System.out.println("e.getMessage");}
	colorChange2() ;
       }  





   
     public static void updateproductsaledetail() throws Exception
   {    try{
             clearScreen();
	space();
	colorChange3() ;
       System.out.print("\t\t\tEnter Product Id:");
      int id1=sc.nextInt();
      PreparedStatement pst=con.prepareStatement("select * from sale where productID=?");
      pst.setInt(1,id1); 
      ResultSet rs=pst.executeQuery(); 
      if(rs.next())
      {
             pst=con.prepareStatement("update sale set price=?,date=?,saleQty=? where productid=? ");
              space();
		System.out.print("\t\t\tEnter Sale Price:"); 
              float ssp=sc.nextFloat();
               System.out.print("\t\t\tEnter Product  Quantity: ");
               int qtys=sc.nextInt();
             System.out.print("\t\t\tEnter Date As yy//mm//dd :");
	sc.nextLine();
             String dat=sc.nextLine();
             pst.setInt(4,id1);
		pst.setFloat(1,ssp);
             pst.setInt(3,qtys);
	     java.util.Date d=new java.util.Date(dat);
             java.sql.Date d1=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
             pst.setDate(2,d1);  //Parameterized query
  
   
                  if(pst.executeUpdate()>0)
                  {
                      System.out.println("\t\t\tRecord Updated.");
                    }
                else
                    {
                     System.out.println("\t\t\tRecord not Updated.");
                    }               
       }
          else
           { 
             System.out.println("\t\t\tSorry, Record not Found.");
           } 
         } 
      
      catch(SQLException e){System.out.println(e);}
	colorChange2() ;
 
  }


public static void viewproductsaledetail()
      {
            try{
           clearScreen();
	colorChange3() ;
	
	String u= String.format("%14s %18s %18s %14s %14s","Sale ID","Product ID","Sale Price","Sale Qty","Date");
		  System.out.println(u);
		System.out.println("--------------------------------------------------------------------------------------");
          PreparedStatement pst=con.prepareStatement("select * from sale ");
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
		String v= String.format("%14s %18s %18s %18s %14s",rs.getInt(1),rs.getInt(2),rs.getFloat(3),rs.getInt(5),rs.getDate(4));
		  System.out.println(v);
          
            } 
          }
           catch(SQLException e){System.out.println("e.getMessage()");}
	colorChange2() ;
      }



 public static void profitdetail()
     { try{
	float prp=0; float slp=0;int slq=0;
	space();
	colorChange3() ;
        System.out.print("\t\t\tEnter PRODUCT ID :");
        int id=sc.nextInt();
        PreparedStatement pst=con.prepareStatement("select * from product where productId=?");
	pst.setInt(1,id);
	
	ResultSet rs=pst.executeQuery();
	 while(rs.next()){
		 prp=rs.getFloat(5);
	}
	
	PreparedStatement pst1=con.prepareStatement("select * from sale where productID=?");
	int id2=id;
	pst1.setInt(1,id2);
        ResultSet rs1=pst1.executeQuery();
        while(rs1.next())
	{
	slp=rs1.getFloat(3);
	 slq=rs1.getInt(5);
	 
	}
	float grs=slp-prp ;
        System.out.println("\t\t\tProfit per product = " +grs);
        
	float pro=grs*slq;
        
         System.out.println("\t\t\tTotal profit = " +pro);	}
       
           catch(SQLException e)
         {System.out.println(e);} 
	colorChange2() ;
     }


        public static void profitdatedetail() throws Exception
     { try{
		int id=0,iD;
		space();
		colorChange3() ;
	System.out.print("\t\t\tEnter first date (YYYY//MM//DD): ");	
		String datef=sc.nextLine();
		java.util.Date d= new java.util.Date(datef);
		java.sql.Date d1= new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
		sc.nextLine();
		System.out.print("\t\t\tEnter Last  date (YYYY//MM//DD): ");
		String datel=sc.nextLine();
		java.util.Date d2=new java.util.Date(datel);
		java.sql.Date d3=new java.sql.Date(d2.getYear(),d2.getMonth(),d2.getDate());
	float prp=0; float slp=0;int slq=0;
	
        PreparedStatement pst=con.prepareStatement("select * from sale where date>=? and date<? ");
		pst.setDate(1,d1);
		pst.setDate(2,d3);
	
	
	ResultSet rs=pst.executeQuery();
     while(rs.next())
	{
	slp=rs.getFloat(3);
	 slq=rs.getInt(5);
	id=rs.getInt(2);
	}

	PreparedStatement pst1=con.prepareStatement("select * from product where productID=?  ");
	int id2=id;
	pst1.setInt(1,id2);
        ResultSet rs1=pst1.executeQuery();
	 while(rs.next()){
		 prp=rs.getFloat(5);
		
	}
   
	float grs=slp-prp;
        
	float pro=grs*slq;
        
         System.out.println("\t\t\tTotal profit ==" +pro); 	}
       
           catch(SQLException e)
         {System.out.println(e);} 
	colorChange2() ;
     }
		




public static void main(String args[])
		{
		String s1=" ";
		String s2=" ";
		clearScreen();
		colorChange1() ;
				System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();
	System.out.println();
	System.out.println();
System.out.println();
System.out.println();
System.out.println();
System.out.println();
System.out.println();
System.out.println();
	System.out.println();
	System.out.println();
    System.out.println();
     System.out.println();
    System.out.println("  		 	 ______________________________________________________________________________________________________________________________________________________________________");
    System.out.println(" 			|  																				       |");
    System.out.println("			|                                                                                                                                       			       |");
    System.out.println("   			|                                    				  WELCOME TO INVENTORY MANAGEMENT SYSTEM                                   		               |");                                       
    System.out.println("   			|                                 		       	      *---------------------------------------------*                              			       |");
    System.out.println("			|								 *---------------------------------------*							       |");
    System.out.println("   			|                              		   	      		    *---------------------------------* 	                              			       |");
    System.out.println("  			|                                          							                                 				       |");														 
    System.out.println("   			|                                                                                                                						       |");
    System.out.println("   			|                                			           >>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<                                        			       |");
    System.out.println("   			|                                			           	PRESS  ENTER KEY TO LOGIN                                           			       |");
    System.out.println("   			|                                			           >>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<                                        			       |");
    System.out.println("			|																				       |");
    System.out.println("   			|______________________________________________________________________________________________________________________________________________________________________|");
	
	sc.nextLine();
  clearScreen();
	space();
    System.out.println(" ");
    System.out.println(" ");
    System.out.println(" ");
	System.out.println(" ");
	System.out.println(" ");
	System.out.println(" ");
	System.out.println(" ");
System.out.println(" ");
System.out.println(" ");
System.out.println("\t\t\t\t\t\t\t\t\t\t--------------------------------------------------------------");
System.out.println("\t\t\t\t\t\t\t\t\t\t--------------------------------------------------------------");
    System.out.print("\t\t\t\t\t\t\t\t\t\t\t\tEnter your User Name : "); 
    s1=sc.nextLine() ;          
System.out.println(" ");
System.out.println(" ");
System.out.println(" ");
        Console console = System.console();
		s2=new String(console.readPassword("\t\t\t\t\t\t\t\t\t\t\t\tEnter your Password : \n\t\t\t\t\t\t\t\t\t\t--------------------------------------------------------------\n\t\t\t\t\t\t\t\t\t\t--------------------------------------------------------------"));

   
	
	if(s1.equals("Admin") && s2.equals("secure@123")){

		
		try{
			
			while(true){
			clearScreen();
			colorChange2() ;
			space();
		System.out.println(" \n\n\n \n\n                 \t 	                 		   	      			  Project:Inventry Managament System.                  ");
		System.out.println() ;
		System.out.println("		****************************************************************************************************************************************************************************");
		System.out.println("		                                		         			Welcome To Inventry Managament System.");
		System.out.println("		____________________________________________________________________________________________________________________________________________________________________________");
		System.out.println("			                       			         				      Module       				 ");
		System.out.println("		____________________________________________________________________________________________________________________________________________________________________________");
		System.out.println() ;
		System.out.println() ;			
		System.out.println("\t\t\t==================================================================");
			
		System.out.println("\t\n	   	        Press Numeric Key 1 To Manage Stock.");
		System.out.println("\t\n 			Press Numeric Key 2 To Manage Sales.");
		System.out.println("\t\n 			Press Numeric Key 3 To Manage Products.");
		System.out.println("\t\n 			Press Numeric Key 4 To View Profit Details.");
		System.out.println("\t\n 			Press Numeric Key 5 To Exit Inventry Managament System.");
		System.out.println("\t\t\t===================================================================");
		System.out.println() ;
		System.out.println() ;	
		
		System.out.print("\t\t\tEnter Your choice :");
		char ch=sc.nextLine().charAt(0);
		
		switch(ch){
				
				case '1':
					clearScreen();
					space();
					System.out.println("\t\t\t===================================================================");
					System.out.println();
					System.out.println("\t\t\tPress Numeric Key 1. To Update Product Quantity in Stock");
					System.out.println();
					System.out.println("\t\t\tPress Numeric Key 2. To View Stock");
					System.out.println();
					System.out.println("\t\t\tPress Numeric Key 3. To abort to Main Menu.");
					System.out.println();
					System.out.println("\t\t\t===================================================================");

					System.out.print("\t\t\tPlease, Enter Your choice from the displayed above module:");
					int ch1=sc.nextInt();
					switch(ch1)
					{
						case 1:	
							clearScreen();
							updateProductQty();sc.nextLine();
							System.out.println("Press Enter key to continue.");
							sc.nextLine();continue;
						case 2:
							clearScreen();
							showPro();sc.nextLine();
							System.out.println("Press Enter key to continue.");
							sc.nextLine();continue;
						case 3:continue;
						
					}
				
				case '2':
					clearScreen();
					space();
					System.out.println("\t\t\t===================================================================");
					System.out.println();
					System.out.println("\t\t\tPress Numeric Key 1. To Insert Product Sale Details.");

					System.out.println();
					System.out.println("\t\t\tPressNumeric Key  2. To Update Product Sale. ");

					System.out.println();
					System.out.println("\t\t\tPress Numeric Key 3. To View Sale.");
					System.out.println();
					System.out.println("\t\t\tPress Numeric Key 4. To abort to Main Menu.");
					System.out.println();

					System.out.println("\t\t\t===================================================================");
					System.out.print("\t\t\tPlease, Enter your choice from the displayed above Module:");
					int ch3=sc.nextInt();
					switch(ch3)
					{
						
						case 1:	
							clearScreen();
							insertproductsaledetail();sc.nextLine();
							System.out.println("Press Enter key to continue.");
							sc.nextLine();continue;
						case 2:
							clearScreen();
							updateproductsaledetail();sc.nextLine();
							System.out.println("Press Enter key to continue.");
							sc.nextLine();continue;
						case 3:
							clearScreen();
							viewproductsaledetail();sc.nextLine();
							System.out.println("Press Enter key to continue.");
							sc.nextLine();continue;	
						case 4:continue;
					}
					
				case '3':	
					clearScreen();
					space();
					System.out.println("\t\t\t===================================================================");
					System.out.println();
					System.out.println("\t\t\tPress Numeric Key 1. To Add New Products");
					System.out.println();
					System.out.println("\t\t\tPress Numeric Key 2. To View all Products ");
					System.out.println();
					System.out.println("\t\t\tPress Numeric Key 3. To Remove Products");
					System.out.println();
					System.out.println("\t\t\tPress Numeric Key 4. To abort to Main Menu.");
					System.out.println();

					System.out.println("\t\t\t===================================================================");
					System.out.print("\t\t\tEnter your choice from the displayed above:");
					int ch2=sc.nextInt();
					switch(ch2)
					{
						case 1:
							clearScreen();
							InsertProductData();sc.nextLine();
							System.out.println("Press Enter key to continue.");
							sc.nextLine();continue;
							
						case 2:
							clearScreen();
							showPro();sc.nextLine();
							System.out.println("Press Enter key to continue.");
							sc.nextLine();continue;
							
						case 3:
							clearScreen();
							deletePro();sc.nextLine();
							System.out.println("Press Enter key to continue.");
							sc.nextLine();continue;
						case 4: continue;
					}
					
				case '4':
					clearScreen();
					space();
					System.out.println("\t\t\t==============================================================================");
					System.out.println();
					System.out.println("\t\t\tPress Numeric Key 1. To View profit details of a product.");
					System.out.println();
                                        System.out.println("\t\t\tPress Numeric Key 2. To View profit details according to start and end date.");
					System.out.println();
					System.out.println("\t\t\tPress Numeric Key 3. To abort to Main Menu.");
					System.out.println();

					System.out.println("\t\t\t==============================================================================");
					System.out.print("\t\t\tPlease, Enter your choice from the dispalyed above module:");
					int ch4=sc.nextInt();
					switch(ch4){

					case 1: clearScreen();
						profitdetail();
						sc.nextLine();
						System.out.println("Press Enter to continue.");
						sc.nextLine();continue;
                                        case 2: clearScreen();
						sc.nextLine();
                                                try
                                                {
						profitdatedetail() ;
						sc.nextLine();
						System.out.println("Press Enter to continue.");
						sc.nextLine();
                                                }
                                                catch(Exception e)
                                                {System.out.print(e) ;} 
                                                continue;
                                       case 3:continue;
					}
					
				
				case '5':System.exit(0);
				break ;
				
				default :
							System.out.println("Enter correct choice ");
							continue ;
						
		
					
			
		}
			
                }
}
	
	
		catch(Exception e)
		{
			try
			{ 	
				System.out.print("\n\n\n\n\t\t\t\t\t\t\t\t\t **Please go through the Module first, you are not giving the desired input.**");
				
				
						int ch=sc.nextInt();
				
			}
			catch(Exception e1){
						System.out.println(e1) ;
						}
				
			
		}	
}
	
    	else{
		System.out.println();
		System.out.print("\tYou are not enrolled in our System. Sorry, please Contact our Administrator ~ Mr. Hicks @ 9456XXXXXX for further queries.");
		System.out.println("\n\tHave a Nice day: ");
		System.out.println("\tThank you;");
			}
		
}


}