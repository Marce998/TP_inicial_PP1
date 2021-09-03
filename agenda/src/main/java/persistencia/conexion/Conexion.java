package persistencia.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import org.apache.log4j.Logger;

public class Conexion 
{
	public static Conexion instancia;
	private Connection connection;
	private Logger log = Logger.getLogger(Conexion.class);
	private Map<String,String> users;
	
	public Conexion()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver"); // quitar si no es necesario
			String url = "jdbc:mysql://localhost:3306/agenda";//?useSSL=false
			String mode = "?useSSL=false";
			String user = "root";
			String pass = "root";
			this.connection = DriverManager.getConnection(url+mode,user,pass);
			this.connection.setAutoCommit(false);
			log.info("Conexi�n exitosa");
		}
		catch(Exception e)
		{
			log.error("Conexi�n fallida", e);
		}
	}
	
	
	public static Conexion getConexion()   
	{								
		if(instancia == null)
		{
			instancia = new Conexion();
		}
		return instancia;
	}

	public Connection getSQLConexion() 
	{
		return this.connection;
	}
	
	public void cerrarConexion()
	{
		try 
		{
			this.connection.close();
			log.info("Conexion cerrada");
		}
		catch (SQLException e) 
		{
			log.error("Error al cerrar la conexi�n!", e);
		}
		instancia = null;
	}
	
	//////////////gestion usuarios//////////////////
	
	public boolean addUser(String user, String pass) {
		boolean user_added = false;
		return user_added;
	}
}