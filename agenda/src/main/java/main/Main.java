package main;

import modelo.Agenda;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.vista.Vista;
import presentacion.vista.ventanaAutenticacion;


public class Main 
{

	public static void main(String[] args) 
	{
		Vista vista = new Vista();
		ventanaAutenticacion ventanaConexion= new ventanaAutenticacion();
		Agenda modelo = new Agenda(new DAOSQLFactory());
		Controlador controlador = new Controlador(vista,ventanaConexion,modelo);
		controlador.inicializar();
	}
}