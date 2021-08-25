package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import modelo.Agenda;
import persistencia.datosDesplegables.mysql.tipoContacto;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaEditarPersona;
import presentacion.vista.VentanaPersona;
import presentacion.vista.Vista;
import presentacion.vista.ventanaABMtipoContacto;
import presentacion.vista.ventanaAltaTipoContacto;
import presentacion.vista.ventanaBajaTipoContacto;
import dto.PersonaDTO;

public class Controlador implements ActionListener
{
		private Vista vista;
		private List<PersonaDTO> personasEnTabla;
		private List<tipoContacto> tiposDeContacto;
		private VentanaPersona ventanaPersona;
		private VentanaEditarPersona ventanaEditarPersona;
		private ventanaABMtipoContacto ventanaABMtipo;
		private ventanaAltaTipoContacto ventanaAltaTipo;
		private ventanaBajaTipoContacto ventanaBajaTipo;
		private Agenda agenda;
		
		public Controlador(Vista vista, Agenda agenda)
		{
			this.vista = vista;
			this.vista.getBtnAgregar().addActionListener(a->ventanaAgregarPersona(a));
			this.vista.getBtnBorrar().addActionListener(s->borrarPersona(s));
			this.vista.getBtnReporte().addActionListener(r->mostrarReporte(r));
			this.vista.getBtnEditar().addActionListener(m->ventanaModificarPersona(m));
			this.vista.getBtnABMtipoContacto().addActionListener(abmTipo->mostrarVentanaABMtipo(abmTipo));
			
			this.ventanaPersona = VentanaPersona.getInstance();
			this.ventanaPersona.getBtnAgregarPersona().addActionListener(p->guardarPersona(p));
			
			this.ventanaABMtipo=ventanaABMtipoContacto.getInstance();
			this.ventanaABMtipo.getBtnAgregarTipo().addActionListener(a->ventanaAltaTipoContacto(a));
			this.ventanaABMtipo.getBtnEliminarTipo().addActionListener(e->ventanaBajaTipoContacto(e));
			
		
			this.ventanaAltaTipo=ventanaAltaTipoContacto.getInstance();
			this.ventanaAltaTipo.getBtnAgregarNuevoTipo().addActionListener(a->agregarNuevoTipoContacto(a));
			
			this.ventanaBajaTipo=ventanaBajaTipoContacto.getInstance();
			this.ventanaBajaTipo.getBtnBorrarTipo().addActionListener(b->borrarTipoContacto(b));
			
			this.ventanaEditarPersona=VentanaEditarPersona.getInstance();
			this.ventanaEditarPersona.getBtnAplicarCambios().addActionListener(e->aplicarCambiosPersona(e));
			
			this.agenda = agenda;	
		}
		

		private void ventanaAgregarPersona(ActionEvent a) {
			this.ventanaPersona.mostrarVentana();
		}
		
		private void mostrarVentanaABMtipo(ActionEvent abmTipo) {
			this.ventanaABMtipo.mostrarVentana();
		}
		
		private void ventanaAltaTipoContacto(ActionEvent a) {
			this.ventanaAltaTipo.mostrarVentana();
		}
		
		private void ventanaBajaTipoContacto(ActionEvent e) {
			this.ventanaBajaTipo.mostrarVentana();
		}
			
		private void ventanaModificarPersona(ActionEvent m) {
			this.ventanaEditarPersona.mostrarVentana();
			
			int filaSeleccionada = this.vista.getTablaPersonas().getSelectedRow();
			
			this.ventanaEditarPersona.getTxtNombre().setText(this.personasEnTabla.get(filaSeleccionada).getNombre());
			this.ventanaEditarPersona.getTxtTelefono().setText(this.personasEnTabla.get(filaSeleccionada).getTelefono());
			this.ventanaEditarPersona.getTxtEmail().setText(this.personasEnTabla.get(filaSeleccionada).getEmail());
			this.ventanaEditarPersona.getTxtFechaNac().setText(this.personasEnTabla.get(filaSeleccionada).getFechaNac().toString());
			this.ventanaEditarPersona.getTxtTipo().setSelectedItem(this.personasEnTabla.get(filaSeleccionada).getTipo());
			this.ventanaEditarPersona.getTxtPais().setSelectedItem(this.personasEnTabla.get(filaSeleccionada).getPais());
			this.ventanaEditarPersona.getTxtProvincia().setSelectedItem(this.personasEnTabla.get(filaSeleccionada).getProvincia());
			this.ventanaEditarPersona.getTxtLocalidad().setSelectedItem(this.personasEnTabla.get(filaSeleccionada).getLocalidad());
			
		}

		private void guardarPersona(ActionEvent p) {
			String nombre = this.ventanaPersona.getTxtNombre().getText();
			String tel = ventanaPersona.getTxtTelefono().getText();
			String email= ventanaPersona.getTxtEmail().getText();
			java.sql.Date fechaNac= Date.valueOf(ventanaPersona.getTxtFechaNac().getText());
			String domicilio = ventanaPersona.getTxtCalle().getText() + ", " + ventanaPersona.getTxtAltura().getText() + 
					", Piso: " + ventanaPersona.getTxtPiso().getText() + ", Depto: " + ventanaPersona.getTxtDepto().getText();
			String tipo = ventanaPersona.getTxtTipo().getSelectedItem().toString();
			String pais = ventanaPersona.getTxtPais().getSelectedItem().toString();
			String provincia = ventanaPersona.getTxtProvincia().getSelectedItem().toString();
			String localidad = ventanaPersona.getTxtLocalidad().getSelectedItem().toString();
			PersonaDTO nuevaPersona = new PersonaDTO(0, nombre, tel,email,fechaNac,domicilio,tipo,pais,provincia,localidad);
			this.agenda.agregarPersona(nuevaPersona);
			this.refrescarTabla();
			this.ventanaPersona.cerrar();
		}
		
		private void aplicarCambiosPersona(ActionEvent e) {
			int filaSeleccionada = this.vista.getTablaPersonas().getSelectedRow();
			
			PersonaDTO persona= this.personasEnTabla.get(filaSeleccionada);
			
			persona.setNombre(this.ventanaEditarPersona.getTxtNombre().getText());
			persona.setTelefono(this.ventanaEditarPersona.getTxtTelefono().getText());
			persona.setEmail(this.ventanaEditarPersona.getTxtEmail().getText());
			persona.setFechaNac(Date.valueOf(this.ventanaEditarPersona.getTxtFechaNac().getText()));
			persona.setTipo(this.ventanaEditarPersona.getTxtTipo().getSelectedItem().toString());
			persona.setDomicilio(this.ventanaEditarPersona.getTxtCalle().getText() + "," + this.ventanaEditarPersona.getTxtAltura().getText() + 
			", Piso: " + this.ventanaEditarPersona.getTxtPiso().getText() + ", Depto: " + this.ventanaEditarPersona.getTxtDepto().getText());
			persona.setPais(this.ventanaEditarPersona.getTxtPais().getSelectedItem().toString());
			persona.setProvincia(this.ventanaEditarPersona.getTxtProvincia().getSelectedItem().toString());
			persona.setLocalidad(this.ventanaEditarPersona.getTxtLocalidad().getSelectedItem().toString());
			
			
			this.agenda.actualizarPersona(persona);
			
			this.refrescarTabla();
			
			this.ventanaEditarPersona.cerrar();
		}
		
		private void agregarNuevoTipoContacto(ActionEvent a) {
			String tipo= ventanaAltaTipo.getTxtNuevoTipo().getText();
			tipoContacto tipoNuevo= new tipoContacto();
			tipoNuevo.setIdTipoContacto(0);
			tipoNuevo.setTipo(tipo);
			tipoNuevo.insertToMySQL(tipoNuevo);
			
			this.refrescarTabla();
			this.refrescarTiposContacto();
			this.ventanaAltaTipo.cerrar();
		}
		
		private void borrarTipoContacto(ActionEvent b) {
			tipoContacto tc= new tipoContacto();
			tiposDeContacto= tc.mostrarTiposContacto();
			int indice=this.ventanaBajaTipo.getCbxBajaTipo().getSelectedIndex();
			tc.deleteFromMySQL(tiposDeContacto.get(indice));
			
			this.refrescarTabla();
			this.refrescarTiposContacto();
			this.ventanaBajaTipo.cerrar();
		}

		private void mostrarReporte(ActionEvent r) {
			ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
			reporte.mostrar();	
		}

		public void borrarPersona(ActionEvent s)
		{
			int[] filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
			for (int fila : filasSeleccionadas)
			{
				this.agenda.borrarPersona(this.personasEnTabla.get(fila));
			}
			this.refrescarTabla();
		}
		
		public void inicializar()
		{
			this.refrescarTabla();
			this.vista.show();
		}
		
		private void refrescarTabla()
		{
			this.personasEnTabla = agenda.obtenerPersonas();
			this.vista.llenarTabla(this.personasEnTabla);
		}
		
		private void refrescarTiposContacto(){
			
			tipoContacto tc= new tipoContacto();
			DefaultComboBoxModel modeloTiposContacto=  new DefaultComboBoxModel(tc.mostrarTiposContacto());
			this.ventanaPersona.getTxtTipo().setModel(modeloTiposContacto);
			this.ventanaBajaTipo.getCbxBajaTipo().setModel(modeloTiposContacto);
			this.ventanaEditarPersona.getTxtTipo().setModel(modeloTiposContacto);
			
		}

		@Override
		public void actionPerformed(ActionEvent e) { }
		
}
