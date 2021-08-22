package dto;



public class PersonaDTO 
{
	private int idPersona;
	private String nombre;
	private String telefono;
	private String email;
	private java.sql.Date fechaNac;
	private String domicilio;
	private String tipo;

	public PersonaDTO(int idPersona, String nombre, String telefono,String email,java.sql.Date fechaNac, String domicilio, String tipo)
	{
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.fechaNac = fechaNac;
		this.domicilio = domicilio;
		this.tipo = tipo;
	}
	
	public int getIdPersona() 
	{
		return this.idPersona;
	}

	public void setIdPersona(int idPersona) 
	{
		this.idPersona = idPersona;
	}

	public String getNombre() 
	{
		return this.nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public String getTelefono() 
	{
		return this.telefono;
	}

	public void setTelefono(String telefono) 
	{
		this.telefono = telefono;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email=email;
	}
	
	public java.sql.Date getFechaNac() {
		return this.fechaNac;
	}
	
	public void setFechaNac(java.sql.Date fecha) {
		this.fechaNac=fecha;
	}
	
	public String getDomicilio(){
		return this.domicilio;
	}
	
	public void setDomicilio(String domicilio){
		this.domicilio = domicilio;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
