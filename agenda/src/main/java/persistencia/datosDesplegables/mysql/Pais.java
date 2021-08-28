package persistencia.datosDesplegables.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import persistencia.conexion.Conexion;

public class Pais {
	private int idPais;
	private String nombrePais;
	
	public int getIdPais() {
		return idPais;
	}
	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}
	public String getNombrePais() {
		return nombrePais;
	}
	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}
	
	public String toString() {
		return this.nombrePais;
	}
	
	public Vector<Pais> mostrarPaises(){
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		Vector<Pais> paises= new Vector<Pais>();
		Conexion conexion = Conexion.getConexion();
			
		try {
			String sql= "SELECT * FROM paises";
			statement = conexion.getSQLConexion().prepareStatement(sql);
			resultSet = statement.executeQuery();
			
			Pais pais=new Pais();
			pais.setIdPais(0);
			pais.setNombrePais("Selecciona País");
			paises.add(pais);
			
			while(resultSet.next())
			{
				pais= new Pais();
				pais.setIdPais(resultSet.getInt("id_pais"));
				pais.setNombrePais(resultSet.getString("nombre"));
				paises.add(pais);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return paises;
	}
	
	public boolean insertToMySQL(Pais pais) {
		String sql= "INSERT INTO paises(id_pais,nombre) VALUES (?,?)";
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(sql);
			statement.setInt(1, pais.getIdPais());
			statement.setString(2, pais.getNombrePais());
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
	
	public boolean deleteFromMySql(int idPais) {
		PreparedStatement statement;
		ResultSet resultSet;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean borradoExitoso=false;
		
		try {
			String sqlSelProvincia = "SELECT * FROM provincias WHERE id_pais=" + idPais;
			statement = conexion.prepareStatement(sqlSelProvincia);
			resultSet = statement.executeQuery();
			
			PreparedStatement statementDel;
			
			while(resultSet.next()) {
				int idProvincia = resultSet.getInt("id_provincia");
				
				String sqlDelLocalidad = "DELETE FROM localidades WHERE id_provincia=" + idProvincia;
				statementDel = conexion.prepareStatement(sqlDelLocalidad);
				if(statementDel.executeUpdate()>0) {
					conexion.commit();
				};
				
				String sqlDelProvincia = "DELETE FROM provincias WHERE id_provincia=" + idProvincia;
				statementDel = conexion.prepareStatement(sqlDelProvincia);
				if(statementDel.executeUpdate()>0) {
					conexion.commit();
				};
			}
			
			String sqlPais= "DELETE FROM paises WHERE id_pais=" + idPais;
			statement = conexion.prepareStatement(sqlPais);
			if(statement.executeUpdate()>0) {
				conexion.commit();
				borradoExitoso = true;
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
