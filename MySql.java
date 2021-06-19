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


public MySql()
{	
	url = "jdbc:mysql://127.0.0.1:3307/elotnisko";
	userName = "root";
	password = "darek123";
	//connect();
}

public String connect()
{
	try {
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	} 
	catch (InstantiationException e) {
		e.printStackTrace();
	return "error";
	} 
	catch (IllegalAccessException e) {
		e.printStackTrace();
	} 
	catch (ClassNotFoundException e) {
		e.printStackTrace();
	}

	try {
		conn = DriverManager.getConnection(url, userName, password);
	} 
	catch (SQLException e) {
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

public ArrayList<String> getTable(String input, String table)
{
	connect();
	Statement statement;
	ResultSet rs;
	ArrayList<String> wynik1=new ArrayList<String>(); 

	try {
		statement = conn.createStatement();
		rs = statement.executeQuery("SELECT * FROM "+ table);

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

public ArrayList<String> executeQuery1(String query)
{
	connect();
	Statement statement;
	ResultSet rs;
	ArrayList<String> wynik1=new ArrayList<String>(); 

	try {
		statement = conn.createStatement();
		rs = statement.executeQuery(query);

		while(rs.next()) {
		wynik1.add(rs.getString(2));
		
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