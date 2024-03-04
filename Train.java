package train;
import java.sql.*;
import java.util.*;

class User{
	Scanner sc = new Scanner(System.in);
	private String name;
	private String pass;
	
	public String getUserName() {
		
		System.out.println("Ener user name : ");
		String uName = sc.nextLine();
		name = uName;
		return name;
		
	}
	
	public String getPass() {
		System.out.println("Enter password : ");
		String uPass = sc.nextLine();
		pass = uPass;
		return pass;
	}
	
}

class passengerDetail{

	Scanner sc = new Scanner(System.in);
	
	private int pnrNumber;
	private String name;
	private String trainNumber;
	private String classType;
	private String date;
	private String startStation;
	private String destinyStation;
	
	 public int getPnrNumber() {
		 int max,min;
		 max = 9999; min = 1000;
         Random random = new Random();
         pnrNumber = random.nextInt(max) + min;
         return pnrNumber;
     }

	
	public String getName() {
		System.out.println("Enter passenger name : ");
		String PName = sc.nextLine();
		name = PName;
		return name;
		
	}
	
	public String getTrainNumber() {
		System.out.println("Enter Train number : ");
		String number = sc.nextLine();
		trainNumber = number;
		return trainNumber;
	}
	
	public String getClassType() {
		
		System.out.println("Enter class type :  ");
		String type = sc.nextLine();
		classType = type;
		return classType;
		
		
	}
	
	public String getDate() {
		System.out.println("Enter the Journey date as 'YYYY-MM-DD' format");
		String Date = sc.nextLine();
		date = Date;
		return date;
	}
	
	public String getStartStation() {
		System.out.println("Enter starting place : ");
		String sPlace = sc.nextLine();
		startStation = sPlace;
		return startStation;
	}
	
	public String getDestinyStation() {
		System.out.println("Enter destiny station : ");
		String dPlace = sc.nextLine();
		destinyStation = dPlace;
		return destinyStation;
	}
	
}

public class myTrain {
	
public static void main(String[] args) {
	
	Scanner sc = new Scanner(System.in);
	User user1 = new User(); 
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String url = "jdbc:mysql://localhost:3306/myTrain"; 
		String user = user1.getUserName();
		String pass = user1.getPass();
		
		
		try(Connection con = DriverManager.getConnection(url,user,pass)){
			
			System.out.println("Login successfull...");
		
		while(true) {
			
			 System.out.println("Enter the choice : ");
             System.out.println("1. Insert Record :");
             System.out.println("2. Delete Record : ");
             System.out.println("3. Show All Records :");
             System.out.println("4. Exit.\n");
             
             int numb = sc.nextInt();
             
             if(numb == 1) {
            	
            	passengerDetail p = new passengerDetail();
            	 
            	int pnrNumber = p.getPnrNumber();
            	String name = p.getName();
            	String trainNumber = p.getTrainNumber();
            	String classType = p.getClassType();
            	String date = p.getDate();
                String startStation = p.getStartStation();
            	String destin = p.getDestinyStation();
            	 
            	 String q = "insert into train1 values(?, ?, ?, ?, ?, ?, ?)";
            	 
            	 PreparedStatement pstmt = con.prepareStatement(q);
            	 
            	 pstmt.setInt(1, pnrNumber);
            	 pstmt.setString(2, name);
            	 pstmt.setString(3, trainNumber);
            	 pstmt.setString(4, classType);
            	 pstmt.setString(5, date);
            	 pstmt.setString(6, startStation);
            	 pstmt.setString(7, destin);
            	 
            	 pstmt.executeUpdate();
            	 
            	 System.out.println("Date inserted Successfully...");
            	 
             }
             
             else if(numb == 2) {
            	 String q = "delete from train1 where pnrNumber = ?";
            	 System.out.println("Enter the PNR number to delete the record : ");
                 int pnrNumber = sc.nextInt();
                 
                 PreparedStatement pstmt = con.prepareStatement(q);
                 
                 pstmt.setInt(1, pnrNumber);
            	 
                 pstmt.executeUpdate();
            	 
                 System.out.println("Successfully delete data...");
                 
             }
             
             else if(numb == 3) {
            	 
            	String q = "select * from train1";
         	    PreparedStatement pstmt = con.prepareStatement(q);
         	    
         	    ResultSet result = pstmt.executeQuery();
         	    
         	    while(result.next()) {
         	    	
         	    	String pnrNumber = result.getString("pnrNumber");
                 	String name = result.getString("name");
                 	String trainNumber = result.getString("trainNumber");
                 	String classType = result.getString("classType");
                 	String date = result.getString("date");
                    String startStation = result.getString("startStation");
                 	String destinyStation = result.getString("destinyStation");
                 	
                     System.out.println("PNR Number: " + pnrNumber);
                     System.out.println("Passenger Name: " + name);
                     System.out.println("Train Number: " + trainNumber);
                     System.out.println("Class Type: " + classType);
                     System.out.println("Journey Date: " + date);
                     System.out.println("From Location: " + startStation);
                     System.out.println("To Location: " + destinyStation);
                     
                     System.out.println("\n");
             }
             }
             
             if(numb == 4) {
            	 System.out.println("Thanks for visit...");
            	 break;
            	 
             }
             
			
		}
				
	}
		
		catch(Exception e){
			System.out.println(e);
		}
		
	 }
	
	
	catch(Exception e) {
		System.out.println(e);
	}
}	

}
