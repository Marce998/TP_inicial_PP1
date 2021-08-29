package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PersonaDAO;
import dto.PersonaDTO;

public class PersonaDAOSQL implements PersonaDAO
{
	private static final String insert = "INSERT INTO personas(idPersona, nombre, telefono,email,fechaNac,tipo,domicilio,pais,provincia,localidad,ciudadPreferida) VALUES(?, ?, ?,?,?,?,?,?,?,?,?)";
	private static final String delete = "DELETE FROM personas WHERE idPersona = ?";
	private static final String readall = "SELECT * FROM personas";
	private static final String orderByTipo = "SELECT * FROM personas ORDER BY tipo";
	private static final String update = "UPDATE personas SET nombre=?,telefono=?,email=?,fechaNac=?,domicilio=?,tipo=?,pais=?,provincia=?,localidad=?,ciudadPref=? WHERE idPersona=?";
		
	public boolean insert(PersonaDTO persona)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, persona.getIdPersona());
			statement.setString(2, persona.getNombre());
			statement.setString(3, persona.getTelefono());
			statement.setString(4,persona.getEmail());
			statement.setDate(5,persona.getFechaNac());
			statement.setString(6, persona.getTipo());
			statement.setString(7, persona.getDomicilio());
			statement.setString(8, persona.getPais());
			statement.setString(9, persona.getProvincia());
			statement.setString(10, persona.getLocalidad());
			statement.setString(11, persona.getCiudadPref());
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
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return isInsertExitoso;
	}
	
	public boolean delete(PersonaDTO persona_a_eliminar)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setString(1, Integer.toString(persona_a_eliminar.getIdPersona()));
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
	
	public boolean update(PersonaDTO persona_a_actualizar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try
		{
			statement = conexion.prepareStatement(update);
			statement.setString(1, persona_a_actualizar.getNombre());
			statement.setString(2, persona_a_actualizar.getTelefono());
			statement.setString(3,persona_a_actualizar.getEmail());
			statement.setDate(4,persona_a_actualizar.getFechaNac());
			statement.setString(5, persona_a_actualizar.getDomicilio());
			statement.setString(6, persona_a_actualizar.getTipo());
			statement.setString(7, persona_a_actualizar.getPais());
			statement.setString(8, persona_a_actualizar.getProvincia());
			statement.setString(9, persona_a_actualizar.getLocalidad());
			statement.setString(10, persona_a_actualizar.getCiudadPref());
			statement.setInt(11, persona_a_actualizar.getIdPersona());
			
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
	
	public List<PersonaDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				personas.add(getPersonaDTO(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return personas;
	}
	
	public List<PersonaDTO> readAllOrderByTipo()
	{
		PreparedStatement statement;
		ResultSet resultSet; 
		ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(orderByTipo);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				personas.add(getPersonaDTO(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return personas;
	}
	
	private PersonaDTO getPersonaDTO(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("idPersona");
		String nombre = resultSet.getString("Nombre");
		String tel = resultSet.getString("Telefono");
		String email= resultSet.getString("Email");
		java.sql.Date fechaNac= resultSet.getDate("fechaNac");
		String domicilio = resultSet.getString("Domicilio");
		String tipo = resultSet.getString("Tipo");
		String pais = resultSet.getString("Pais");
		String provincia = resultSet.getString("Provincia");
		String localidad = resultSet.getString("Localidad");
		String ciudadPref = resultSet.getString("ciudadPreferida");
		return new PersonaDTO(id, nombre, tel,email,fechaNac,domicilio,tipo,pais,provincia,localidad,ciudadPref);
	}
}
