package mvc.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DBConnection {

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		String url = "jdbc:mysql://localhost/mandoo1021";
		String user = "mandoo1021";
		String password = "alsrnr12";
		
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url, user, password);
		
		return conn;
	}
}
