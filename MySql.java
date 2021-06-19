import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySql
{
private Connection conn;
private String url, userName, password;

/* Konstruktor klasy <code>MySql<code>
  */
public MySql()
{	
	url = "jdbc:mysql://127.0.0.1:3307/elotnisko";
	userName = "root";
	password = "darek123";
	//connect();
}

/* Metoda <code>connect</code> inicjuje po³¹czenie z baza
 * 
 */
public String connect()
{
	try {
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

	} 
	catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	return "error";
	} 
	catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	try {
		conn = DriverManager.getConnection(url, userName, password);
	} 
	catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	return e.toString();
	}
	return "ok";
}

/* Metoda <code>connect</code> 
 * 
 */
private void disconnect()
{
	try{
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.err.println(e.toString());
	}
}

public Connection getConnection()
{
	return conn;
}

/*public PrepareStatement createPrepareStatement(String query)
{
	return conn.createPrepareStatement(query);
}*/

public ArrayList<String> getTable(String input)
{
	connect();
	Statement statement;
	ResultSet rs;
	ArrayList<String> wynik1=new ArrayList<String>(); 

	try {
		statement = conn.createStatement();
		rs = statement.executeQuery("SELECT * FROM loty_");

		while(rs.next()) {
		wynik1.add(rs.getString(input));
		
		System.out.print(wynik1);
	//	conn.close();
		}
	} 
	catch (SQLException e) {
		// TODO Auto-generated catch block
		System.err.println(e.toString());
	}
	disconnect();
	return wynik1;
}

/* Metoda <code>getQuery</code> pobiera z bazy danych 
 *  SELECT
 */
public String executeQuery(String query)
{
	connect();
	Statement statement;
	ResultSet rs;
	String wynik="";

	try {
		statement = conn.createStatement();
		rs = statement.executeQuery(query);
		rs.next();
		wynik = rs.getString(1);
		System.out.print(wynik);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.err.println(e.toString());
					return e.toString();
	}
	disconnect();
	return wynik;
}

/* Metoda <code>executeUpdate</code> zmienia dane w bazie 
 *  INSERT, UPDATE or DELETE
 */
public String executeUpdate(String update)
{
	connect();
	Statement statement;
		  //	   String up = "Insert into .....";

	try {
		statement = conn.createStatement();
		statement.executeUpdate(update);

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.err.println(e.toString());
				   return e.toString();
	}

return "ok";
			}
}