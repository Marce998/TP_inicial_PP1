package persistencia.datosDesplegables.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import persistencia.conexion.Conexion;

public class tipoContacto {
	private int idTipoContacto;
	private String tipo;
	
	
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
	
	
	
	
}
