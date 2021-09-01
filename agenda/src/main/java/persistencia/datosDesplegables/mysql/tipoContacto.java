package persistencia.datosDesplegables.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import dto.PersonaDTO;
import persistencia.conexion.Conexion;

public class tipoContacto {
	private int idTipoContacto;
	private String tipo;
	
	
	public boolean insertToMySQL(tipoContacto tipo) {
		String sql= "INSERT INTO tipocontacto(idTipoContacto,tipo) VALUES (?,?)";
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(sql);
			statement.setInt(1, tipo.getIdTipoContacto());
			statement.setString(2, tipo.getTipo());
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
	
	public boolean deleteFromMySQL(tipoContacto tipo_a_borrar)
	{
		String sql= "DELETE FROM tipocontacto WHERE idTipoContacto=?";
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(sql);
			statement.setString(1, Integer.toString(tipo_a_borrar.getIdTipoContacto()));
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isdeleteExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}
	
	public boolean updateToMySQL(tipoContacto tipo_a_actualizar) {
		String sql= "UPDATE tipocontacto SET tipo=? where idTipoContacto=?";
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try
		{
			statement = conexion.prepareStatement(sql);
			statement.setString(1, tipo_a_actualizar.getTipo());
			statement.setInt(2, tipo_a_actualizar.getIdTipoContacto()); 
			
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isUpdateExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return isUpdateExitoso;
		
	}
	
	
	public Vector<tipoContacto> mostrarTiposContacto(){
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		Vector<tipoContacto> tipos = new Vector<tipoContacto>();
		Conexion conexion = Conexion.getConexion();
		
		try {
			String sql= "SELECT * FROM tipocontacto";
			statement = conexion.getSQLConexion().prepareStatement(sql);
			resultSet = statement.executeQuery();
			
			tipoContacto tipo=null;
			
			while(resultSet.next())
			{
				tipo= new tipoContacto();
				tipo.setIdTipoContacto(resultSet.getInt("idTipoContacto"));
				tipo.setTipo(resultSet.getString("tipo"));
				tipos.add(tipo);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return tipos;
		
	}
	
	public int getIdTipoContacto() {
		return idTipoContacto;
	}
	public String getTipo() {
		return tipo;
	}
	
	public void setIdTipoContacto(int idTipoContacto) {
		this.idTipoContacto = idTipoContacto;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String toString() {
		return this.tipo;
	}	
}
