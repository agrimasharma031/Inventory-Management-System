import java.sql.*;
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
	public static void InsertProductData() throws SQLException
	{
		space();
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
		System.out.println("\t\t\tData inserted");}
		else
		{space();
		System.out.println("\t\t\tNot inserted");}
		}
	public static void updateProductQty() throws SQLException
	{
		space();
		System.out.print("\t\t\tEnter product id");
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
		}
	public static void deletePro()
	{
	try
		{
		space();
		System.out.print("\t\t\tEnter product id");
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
	}
	public static void showPro()throws SQLException
	{
	PreparedStatement pst=con.prepareStatement("select * from product");
	ResultSet rs=pst.executeQuery();
	
	System.out.println("Product ID\tProduct Name\tPurchase Price\tSale Price\tQty");

	while(rs.next())
	{
	int pid=rs.getInt(1);
	String pname=rs.getString(2);
	float price=rs.getFloat(5);
	float sprice=rs.getFloat(3);
	int pqty=rs.getInt(4);
	
	System.out.println(pid+"\t\t"+pname+"\t"+price+"\t\t"+sprice+"\t\t"+pqty);
	}
	}




    public static void insertproductsaledetail()
      {
       try{
	space();
      System.out.print("\t\t\tEnter SALE ID");
      int id1=sc.nextInt();
      System.out.print("\t\t\tEnter PRODUCT ID");
      int id2=sc.nextInt();  
      System.out.print("\t\t\tEnter SALE PRICE");
      float ssp=sc.nextFloat();
      System.out.print("\t\t\tEnter sale QUANTITY ");
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
	
          System.out.println("\t\t\tData not Inserted");
       }   
            }
         
      catch(InputMismatchException e){System.out.println("e.getMessage");} 
      catch(SQLException e){System.out.println("e.getMessage");}
       }  




   
      public static void updateproductsaledetail()
   {    try{
             clearScreen();
	space();
       System.out.print("\t\t\tEnter PRODUCT ID");
      int id1=sc.nextInt();
      PreparedStatement pst=con.prepareStatement("select * from sale where productID=?");
      pst.setInt(1,id1); 
      ResultSet rs=pst.executeQuery(); 
      if(rs.next())
      {
             pst=con.prepareStatement("update sale set price=?,date=?,saleQty=? where productid=? ");
              space();
		System.out.print("\t\t\tEnter SALE PRICE:"); 
              float ssp=sc.nextFloat();
               System.out.print("\t\t\tEnter Product  QUANTITY: ");
               int qtys=sc.nextInt();
             System.out.print("\t\t\tEnter DATE As YY//MM//DD :");
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
                      System.out.println("\t\t\tRecord UPDATED!!!!!!!");
                    }
                else
                    {
                     System.out.println("\t\t\tRecord not UPDATED!!!!!!!");
                    }               
       }
          else
           { 
             System.out.println("\t\t\tRecord not Found !!!!!!!");
           } 
         } 
      
      catch(SQLException e){System.out.println(e);}
 
  }

      public static void viewproductsaledetail()
      {
            try{
           clearScreen();
	
		  System.out.println("Sale ID\tProduct ID\tSale Price\tSale Qty\tDate");
          PreparedStatement pst=con.prepareStatement("select * from sale ");
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {

          System.out.println(rs.getInt(1)+"\t"+rs.getInt(2)+"\t\t"+rs.getFloat(3)+"\t\t"+rs.getInt(5)+"\t\t"+rs.getDate(4));
            } 
          }
           catch(SQLException e){System.out.println("e.getMessage()");}
      }






         public static void profitdetail()
     { try{
	float prp=0; float slp=0;int slq=0;
	space();
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
	float grs=slp-prp;
        System.out.println("\t\t\tProfit per per product =" +grs);
        
	float pro=grs*slq;
        
         System.out.println("\t\t\tTotal profit ==" +pro);	}
       
           catch(SQLException e)
         {System.out.println(e);} 
     }
         public static void profitdatedetail()
     { try{
		int id=0,iD;
		space();
	System.out.print("\t\t\tEnter first date (YYYY//MM//DD): ");	
		String datef=sc.nextLine();
		java.util.Date d=new java.util.Date(datef);
		java.sql.Date d1=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
		sc.nextLine();
		System.out.print("\t\t\tEnter Last  date (YYYY//MM//DD): ");
		String datel=sc.nextLine();
		java.util.Date d2=new java.util.Date(datel);
		java.sql.Date d3=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
	float prp=0; float slp=0;int slq=0;
	
        PreparedStatement pst=con.prepareStatement("select * from sale where date>=?# and date<?# ");
		pst.setDate(1,d1);
		pst.setDate(1,d3);
	
	
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
     }
	public static void space(){
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
	}
	public static void main(String...arg){
	  String s1="user";
    String s2="pass";
	clearScreen();
		System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();
     System.out.println();
    System.out.println("   ------------------------------------------------------------------------------------------------------------------");
    System.out.println("   |                                                                                                                |");
    System.out.println("   |                                      WELCOME TO INVENTORY MANAGEMENT SYSTEM                                    |");                                       
    System.out.println("   |                                                                                                                |");
    System.out.println("   |                                                                                                                |");
    System.out.println("   |                                          PRESS  ENTER KEY TO LOGIN                                             |");
    System.out.println("   |                                                                                                                |");
    System.out.println("   |                                                                                                                |");
    System.out.println("   ------------------------------------------------------------------------------------------------------------------");
	sc.nextLine();
  clearScreen();
	space();
	System.out.print("\t\t\tUSERNAME: ");
	s1=sc.nextLine();
	System.out.print("\t\t\tPASSWORD: ");
	s2=sc.nextLine();
  
	if(s1.equals("user") && s2.equals("pass")){
		
		try{
			
			while(true){
			clearScreen();
			space();
			System.out.println("\t\t\t==================================================================");
			System.out.println("\t\t\t1.Manage Stock");
			System.out.println();
			System.out.println("\t\t\t2.Manage Sale");
			System.out.println();
			System.out.println("\t\t\t3.Manage Product");
			System.out.println();
			System.out.println("\t\t\t4.View Profit Details");
			System.out.println();
			System.out.println("\t\t\t5.Exit");
			System.out.println();

			System.out.println("\t\t\t===================================================================");
			System.out.print("\t\t\tEnter Your choice :");
			int ch=sc.nextInt();
			switch(ch){
				case 1:
					clearScreen();
					space();
					System.out.println("\t\t\t===================================================================");
					System.out.println("\t\t\t1.Update Product Quantity in Stock");
					System.out.println();
					System.out.println("\t\t\t2.View Stock");
					System.out.println();
					System.out.println("\t\t\t3.To Main Menu");
					
					System.out.println("\t\t\t===================================================================");

					System.out.print("\t\t\tEnter Your choice :");
					int ch1=sc.nextInt();
					switch(ch1)
					{
						case 1:	
							clearScreen();
							updateProductQty();sc.nextLine();
							System.out.println("Press Enter to continue....");
							sc.nextLine();continue;
						case 2:
							clearScreen();
							showPro();sc.nextLine();
							System.out.println("Press Enter to continue....");
							sc.nextLine();continue;
						case 3:continue;
						
					}
				case 2:
					clearScreen();
					space();
					System.out.println("\t\t\t===================================================================");
					System.out.println("\t\t\t1.Insert Product Sale Detail");

					System.out.println();
					System.out.println("\t\t\t2.Upadate Sale ");

					System.out.println();
					System.out.println("\t\t\t3.view Sale");
					System.out.println();
					System.out.println("\t\t\t4.To Main Menu");
					System.out.println("\t\t\t===================================================================");
					System.out.print("\t\t\tEnter your choice:");
					int ch3=sc.nextInt();
					switch(ch3)
					{
						case 1:	
							clearScreen();
							insertproductsaledetail();sc.nextLine();
							System.out.println("Press Enter to continue....");
							sc.nextLine();continue;
						case 2:
							clearScreen();
							updateproductsaledetail();sc.nextLine();
							System.out.println("Press Enter to continue....");
							sc.nextLine();continue;
						case 3:
							clearScreen();
							viewproductsaledetail();sc.nextLine();
							System.out.println("Press Enter to continue....");
							sc.nextLine();continue;	
						case 4:continue;
					}
				case 3:	
					clearScreen();
					space();
					System.out.println("\t\t\t===================================================================");
					System.out.println("\t\t\t1.Add New Product");
					System.out.println();
					System.out.println("\t\t\t2.View all Product ");
					System.out.println();
					System.out.println("\t\t\t3.Remove Product");
					System.out.println();
					System.out.println("\t\t\t4.To Main Menu");
					System.out.println("\t\t\t===================================================================");
					System.out.print("\t\t\tEnter your choice:");
					int ch2=sc.nextInt();
					switch(ch2)
					{
						case 1:
							clearScreen();
							InsertProductData();sc.nextLine();
							System.out.println("Press Enter to continue....");
							sc.nextLine();continue;
							
						case 2:
							clearScreen();
							showPro();sc.nextLine();
							System.out.println("Press Enter to continue....");
							sc.nextLine();continue;
							
						case 3:
							clearScreen();
							deletePro();sc.nextLine();
							System.out.println("Press Enter to continue....");
							sc.nextLine();continue;
						case 4:continue;
					}
				case 4:
					clearScreen();
					space();
					System.out.println("\t\t\t===================================================================");
					System.out.println("\t\t\t1.View profit details of a product");
					System.out.println();
					System.out.println("\t\t\t2.To Main Menu");
					System.out.println("\t\t\t===================================================================");
					System.out.print("\t\t\tEnter your choice:");
					int ch4=sc.nextInt();
					switch(ch4){

					case 1: clearScreen();
						profitdetail();
						sc.nextLine();
						System.out.println("Press Enter to continue....");
						sc.nextLine();continue;
					case 2:continue;
					}
					
		case 5:System.exit(0);
					
			
		}
		}	
		}
	
	
	
		catch(Exception e)
		{
			System.out.print(e);
		}	}
	
    	else{
		System.out.println();
		System.out.print("\t\t\t\t!!!!You are not authorised!!!!");}}}

