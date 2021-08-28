package persistencia.datosDesplegables.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import persistencia.conexion.Conexion;

public class Provincia {
	private int idProvincia;
	private String nombreProvincia;
	private int idPais;
	
	public int getIdProvincia() {
		return idProvincia;
	}
	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}
	public String getNombreProvincia() {
		return nombreProvincia;
	}
	public void setNombreProvincia(String nombreProvincia) {
		this.nombreProvincia= nombreProvincia;
	}
	
	public int getIdPais() {
		return this.idPais;
	}
	
	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}
	
	public String toString() {
		return this.nombreProvincia;
	}
	
	public Vector<Provincia> mostrarProvincias(int idPais){
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		Vector<Provincia> provincias= new Vector<Provincia>();
		Conexion conexion = Conexion.getConexion();
		
		try {
			String sql= "SELECT * FROM provincias WHERE id_pais=" + idPais;
			statement = conexion.getSQLConexion().prepareStatement(sql);
			resultSet = statement.executeQuery();
			
			Provincia provincia=new Provincia();
			provincia.setIdProvincia(0);
			provincia.setNombreProvincia("Selecciona Provincia");
			provincias.add(provincia);
			
			while(resultSet.next())
			{
				provincia= new Provincia();
				provincia.setIdProvincia(resultSet.getInt("id_provincia"));
				provincia.setNombreProvincia(resultSet.getString("nombre"));
				provincias.add(provincia);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return provincias;
	}
	
	public boolean insertToMySQL(Provincia provincia) {
		String sql= "INSERT INTO provincias(id_provincia,nombre,id_pais) VALUES (?,?,?)";
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(sql);
			statement.setInt(1, provincia.getIdProvincia());
			statement.setString(2, provincia.getNombreProvincia());
			statement.setInt(3,provincia.getIdPais());
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
	
	public boolean deleteFromMySql(int idProvincia) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean borradoExitoso=false;
		
		try {
			String sqlDelLocalidad = "DELETE FROM localidades WHERE id_provincia=" + idProvincia;
			statement = conexion.prepareStatement(sqlDelLocalidad);
			if(statement.executeUpdate()>0) {
				conexion.commit();
			};
			
			String sqlDelProvincia = "DELETE FROM provincias WHERE id_provincia=" + idProvincia;
			statement = conexion.prepareStatement(sqlDelProvincia);
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