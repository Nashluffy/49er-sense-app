import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import java.util.stream.Collectors;
import java.sql.*;

public class PackageTracker implements Runnable{

	private static Vector<Integer> path = new Vector<Integer>();
	private String startingLocation;
	private String endLocation;
	public void run() {
        System.out.println("Please input starting location: ");
		Scanner in = new Scanner(System.in); 
        startingLocation = in.nextLine(); 
        System.out.println("Please input ending location: ");
        endLocation = in.nextLine();
		findPath(locationToNode(startingLocation), locationToNode(endLocation));
	}
	
	static class Package {
		private int weight;
		private String packaging;
		private String dimensions;	
		private String trackingNumber;
		private int lastLocation = 1;
		
		private String getTrackingNumber() {
			return this.trackingNumber;
		}
		
		Package(int w, String p, String d){
			this.weight = w;
			this.packaging = p;
			this.dimensions = d;
			this.trackingNumber = generateTracking();
			System.out.print("Thanks for shipping your package with us! Your tracking number is: ");
			System.out.println(this.trackingNumber);
			this.addPackage();
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
		private void sendSingleLocation(String location) {
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
	static int[][] adjacencyMatrix = { 
			//1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25
			{ 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //1 Northborough, MA
			{ 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //2 Edison, NJ
			{ 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //3 Pittsburgh, PA
			{ 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //4 Allentown, PA
			{ 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //5 Martinsburg, WV
			{ 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //6 Charlotte, NC
			{ 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //7 Atlanta, GA
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
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1}, //24 Sacramento, CA
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0}  //25 Seattle, WA
            }; 
	private static final int NO_PARENT = -1; 
    private static void findPath(int startVertex, int stopVertex) { 
        
    	int nVertices = adjacencyMatrix[0].length; 
        int[] shortestDistances = new int[nVertices]; 
        boolean[] added = new boolean[nVertices]; 
        for (int vertexIndex = 0; vertexIndex < nVertices;vertexIndex++){ 
            shortestDistances[vertexIndex] = Integer.MAX_VALUE; added[vertexIndex] = false; 
        } 
        shortestDistances[startVertex] = 0; 
        int[] parents = new int[nVertices]; 
        parents[startVertex] = NO_PARENT; 
        for (int i = 1; i < nVertices; i++) { 
            int nearestVertex = -1; 
            int shortestDistance = Integer.MAX_VALUE; 
            for (int vertexIndex = 0; vertexIndex < nVertices;  vertexIndex++) { 
                if (!added[vertexIndex] && shortestDistances[vertexIndex] <  shortestDistance){ 
                    nearestVertex = vertexIndex; 
                    shortestDistance = shortestDistances[vertexIndex]; 
                } 
            } 
            added[nearestVertex] = true; 
            for (int vertexIndex = 0; vertexIndex < nVertices;  vertexIndex++){ 
                int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex]; 
                if (edgeDistance > 0 && ((shortestDistance + edgeDistance) <  shortestDistances[vertexIndex])){ 
                    parents[vertexIndex] = nearestVertex; 
                    shortestDistances[vertexIndex] = shortestDistance + edgeDistance; 
                } 
            } 
        } 
        printSolution(startVertex, shortestDistances, parents, stopVertex); 
    } 
    private static void printSolution(int startVertex, int[] distances, int[] parents, int stopVertex) { 
        int nVertices = distances.length;           
        for (int vertexIndex = 0;  vertexIndex < nVertices;  vertexIndex++)  { 
            if (vertexIndex == stopVertex)  
            { 
                printPath(vertexIndex, parents); 
                
            } 
        } 
    } 
    private static void printPath(int currentVertex, int[] parents) { 
    	if (currentVertex == NO_PARENT) { 
        	return; 
        } 
        printPath(parents[currentVertex], parents); 
        path.add(currentVertex);
    } 	
	private static void shipPackage(Package p) {
		for (int k = 0; k < path.size(); k++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			p.sendSingleLocation(nodeToLocation(path.elementAt(k)));
		}
	}
	
	private static void trackPackage(String trackingNumber){
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
    
	private static String nodeToLocation(int n) {
		String result = "";
		switch(n + 1) {
		case 1:
			result = "Northborough, MA";
			break;
		case 2:
			result = "Edison, NJ";
			break;
		case 3:
			result = "Pittsburgh, PA";
			break;
		case 4:
			result = "Allentown, PA";
			break;
		case 5:
			result = "Martinsburg, WV";
			break;
		case 6:
			result = "Charlotte, NC";
			break;
		case 7:
			result = "Atlanta, GA";
			break;
		case 8:
			result = "Orlando, FL";
			break;
		case 9:
			result = "Memphis, TN";
			break;
		case 10:
			result = "Grove City, OH";
			break;
		case 11:
			result = "Indianapolis, IN";
			break;
		case 12:
			result = "Detroit, MI";
			break;
		case 13:
			result = "New Berlin, WI";
			break;
		case 14:
			result = "Minneapolis, MN";
			break;
		case 15:
			result = "St. Louis, MO";
			break;
		case 16:
			result = "Kansas, KS";
			break;
		case 17:
			result = "Dallas, TX";
			break;
		case 18:
			result = "Houston, TX";
			break;
		case 19:
			result = "Denver, CO";
			break;
		case 20:
			result = "Salt Lake City, UT";
			break;
		case 21:
			result = "Phoenix, AZ";
			break;
		case 22:
			result = "Los Angeles, CA";
			break;
		case 23:
			result = "Chino, CA";
			break;
		case 24:
			result = "Sacramento, CA";
			break;
		case 25:
			result = "Seattle, WA";
			break;
		}
		return result;
	}
    
	private static int locationToNode(String s) {
		int returnInt = 0;
		switch(s) {
		case "Northborough, MA":
			returnInt = 0;
			break;
		case "Edison, NJ":
			returnInt = 1;
			break;
		case "Pittsburgh, PA":
			returnInt = 2;
			break;
		case "Allentown, PA":
			returnInt = 3;
			break;
		case "Martinsburg, WV":
			returnInt = 4;
			break;
		case "Charlotte, NC":
			returnInt = 5;
			break;
		case "Atlanta, GA":
			returnInt = 6;
			break;
		case "Orlando, FL":
			returnInt = 7;
			break;
		case "Memphis, TN":
			returnInt = 8;
			break;
		case "Grove City, OH":
			returnInt = 9;
			break;
		case "Indianapolis, IN":
			returnInt = 10;
			break;
		case "Detroit, MI":
			returnInt = 11;
			break;
		case "New Berlin, WI":
			returnInt = 12;
			break;
		case "Minneapolis, MN":
			returnInt = 13;
			break;
		case "St. Louis, MO":
			returnInt = 14;
			break;
		case "Kansas, KS":
			returnInt = 15;
			break;
		case "Dallas, TX":
			returnInt = 16;
			break;
		case "Houston, TX":
			returnInt = 17;
			break;
		case "Denver, CO":
			returnInt = 18;
			break;
		case "Salt Lake City, UT":
			returnInt = 19;
			break;
		case "Phoenix, AZ":
			returnInt = 20;
			break;
		case "Los Angeles, CA":
			returnInt = 21;
			break;
		case "Chino, CA":
			returnInt = 22;
			break;
		case "Sacramento, CA":
			returnInt = 23;
			break;
		case "Seattle, WA":
			returnInt = 24;
			break;
		}
		return returnInt;
	}
	public static void main(String[] args) {
		//When we bring a package to the post office, we need to specify 1. Weight 2. Packaging type 3. Dimensions
		Package primePackage = new Package(5, "Box", "5x5x5");
		Package diffPackage = new Package(10, "Circle", "5x5x5");

		//Then, a new thread is created to calculate the fastest path to the destination
		Thread packageTrack1 = new Thread(new PackageTracker());
		Thread packageTrack2 = new Thread(new PackageTracker());
		packageTrack1.start();
		packageTrack2.start();

		
		
		//We join the thread to make main thread wait on the path tracker to finish
		try {
			packageTrack1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			packageTrack2.join();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//Then we ship the package based on the path calculated by the thread
		shipPackage(primePackage);
		
		//Track it once its at the destination
		trackPackage(primePackage.getTrackingNumber());
	}
}
