package persistencia.datosDesplegables.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import persistencia.conexion.Conexion;

public class Localidad {
	private int idLocalidad;
	private String nombreLocalidad;
	private int idProvincia;
	
	public int getIdLocalidad() {
		return idLocalidad;
	}
	public void setIdLocalidad(int idLocalidad) {
		this.idLocalidad= idLocalidad;
	}
	public String getNombreLocalidad() {
		return nombreLocalidad;
	}
	public void setNombreLocalidad(String nombreLocalidad) {
		this.nombreLocalidad = nombreLocalidad;
	}
	
	public int getIdProvincia() {
		return idProvincia;
	}
	
	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}
	public String toString() {
		return this.nombreLocalidad;
	}
	
	public Vector<Localidad> mostrarLocalidades(int idProvincia){
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		Vector<Localidad> localidades= new Vector<Localidad>();
		Conexion conexion = Conexion.getConexion();
		
		try {
			String sql= "SELECT * FROM localidades WHERE id_provincia =" + idProvincia;
			statement = conexion.getSQLConexion().prepareStatement(sql);
			resultSet = statement.executeQuery();
			
			Localidad localidad=null;
			
			while(resultSet.next())
			{
				localidad= new Localidad();
				localidad.setIdLocalidad(resultSet.getInt("id_localidad"));
				localidad.setNombreLocalidad(resultSet.getString("nombre"));
				localidades.add(localidad);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return localidades;
	}
	
	public boolean insertToMySQL(Localidad localidad) {
		String sql= "INSERT INTO localidades(id_localidad,nombre,id_provincia) VALUES (?,?,?)";
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(sql);
			statement.setInt(1, localidad.getIdLocalidad());
			statement.setString(2, localidad.getNombreLocalidad());
			statement.setInt(3,localidad.getIdProvincia());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		} return isInsertExitoso;
	}
	
	public boolean deleteFromMySql(int idLocalidad) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean borradoExitoso=false;
		
		try {
			String sqlDelLocalidad = "DELETE FROM localidades WHERE id_localidad=" + idLocalidad;
			System.out.println(sqlDelLocalidad);
			statement = conexion.prepareStatement(sqlDelLocalidad);
			if(statement.executeUpdate()>0) {
				conexion.commit();
				borradoExitoso=true;
			};
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		} return borradoExitoso;
	}
}
