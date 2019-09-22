import java.util.Calendar;
import java.util.Random;
import java.util.stream.Collectors;
import java.sql.*;

public class PackageTracker {
	
	static class Package{
		private int weight;
		private String packaging;
		private String dimensions;	
		private String trackingNumber;
		private int lastLocation = 1;
		private String startingLocation;
		private String endLocation;
		Package(int w, String p, String d, String sl, String dl){
			this.weight = w;
			this.packaging = p;
			this.dimensions = d;
			this.startingLocation = sl;
			this.endLocation = dl;
			this.trackingNumber = generateTracking();
			System.out.print("Thanks for shipping your package with us! Your tracking number is: ");
			System.out.println(this.trackingNumber);
			this.sendPackage(startingLocation);
		}
		
		private String generateTracking() {
			int length = 20;
			String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
			return new Random().ints(length, 0, chars.length()).mapToObj(i -> "" + chars.charAt(i)).collect(Collectors.joining());
		}
		private void addPackage() {

			try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = 
			       DriverManager.getConnection("jdbc:mysql://localhost:3306/Assignment3?" +
                           "user=nash&password=githubsafepassword&serverTimezone=UTC");
			      Calendar calendar = Calendar.getInstance();
			      java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
			      String query = "INSERT INTO packages (vcTrackingNumber, vcDimensions, vcPackaging, intWeight)"
			        + " values (?, ?, ?, ?)";
			      PreparedStatement preparedStmt = con.prepareStatement(query);
			      preparedStmt.setString (1, trackingNumber);
			      preparedStmt.setString (2, dimensions);
			      preparedStmt.setString   (3, packaging);
			      preparedStmt.setInt(4, weight);
			      preparedStmt.execute();
			}
			catch(Exception e) {System.out.println(e);}
		}
		private void trackPackage() {
			try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = 
			       DriverManager.getConnection("jdbc:mysql://localhost:3306/Assignment3?" +
                           "user=nash&password=githubsafepassword&serverTimezone=UTC");
			      Calendar calendar = Calendar.getInstance();
			      java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
			      String query = "SELECT * FROM packageLocation WHERE vcTrackingNumber = ? ORDER BY intNodesTraveled ASC";
			      PreparedStatement preparedStmt = con.prepareStatement(query);
			      preparedStmt.setString (1, trackingNumber);
			      ResultSet rs = preparedStmt.executeQuery();
			      while ( rs.next() )
			      {
			        System.out.print("Stop Number: " + rs.getInt("intNodesTraveled"));
			        System.out.println(" Location: " + rs.getString("vcLocation"));
			      }
			      rs.close();
			}
			catch(Exception e) {System.out.println(e);}
		}
		private void sendPackage(String location) {
			try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = 
			       DriverManager.getConnection("jdbc:mysql://localhost:3306/Assignment3?" +
                           "user=nash&password=githubsafepassword&serverTimezone=UTC");
			      Calendar calendar = Calendar.getInstance();
			      java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
			      String query = "INSERT INTO packageLocation (vcTrackingNumber, intNodesTraveled, vcLocation) VALUES (?,?,?)";
			      PreparedStatement preparedStmt = con.prepareStatement(query);
			      preparedStmt.setString (1, trackingNumber);
			      preparedStmt.setInt (2, lastLocation);
			      preparedStmt.setString (3, location);
			      preparedStmt.execute();
			      lastLocation += 1;
			}
			catch(Exception e) {System.out.println(e); lastLocation -= 1;}
		}
	}
	
	public static void main(String[] args) {
		int[][] adjacencyMatrix = { 
				//1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25
				{ 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //1 Northborough, MA
				{ 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //2 Edison, NJ
				{ 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //3 Pittsburgh, PA
				{ 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //4 Allentown, PA
				{ 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //5 Martinsburg, WV
				{ 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //6 Charlotte, NC
				{ 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //7 Atlanta, GA
				{ 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0}, //8 Orlando, FL
				{ 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0}, //9 Memphis, TN
				{ 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //10 Grove City, OH
				{ 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //11 Indianapolis, IN
				{ 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //12 Detroit, MI
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //13 New Berlin, WI
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //14 Minneapolis, MN
				{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //15 St.Louis, MO
				{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0}, //16 Kansas, KS
				{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0}, //17 Dallas, TX
				{ 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, //18 Houston, TX
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0}, //19 Denver, CO
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0}, //20 Salt Lake City, UT
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0}, //21 Phoenix, AZ
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0}, //22 Los Angeles, CA
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0}, //23 Chino, CA
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0}, //24 Sacramento, CA
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0}  //25 Seattle, WA
                }; 
		Package primePackage = new Package(5, "Box", "5x5x5", "Durham, NC", "Seattle, WA");
		primePackage.addPackage();
		primePackage.sendPackage(primePackage.endLocation);
		
		primePackage.trackPackage();
		
	}
}
