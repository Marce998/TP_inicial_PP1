package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import modelo.Agenda;
import persistencia.datosDesplegables.mysql.tipoContacto;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaEditarPersona;
import presentacion.vista.VentanaPersona;
import presentacion.vista.Vista;
import presentacion.vista.ventanaABMtipoContacto;
import presentacion.vista.ventanaAltaTipoContacto;
import presentacion.vista.ventanaBajaTipoContacto;
import presentacion.vista.ventanaEditarTipoContacto;
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
		private ventanaEditarTipoContacto ventanaEditarTipo;
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
			this.ventanaABMtipo.getBtnAgregarTipo().addActionListener(a->mostrarVentanaAltaTipo(a));
			this.ventanaABMtipo.getBtnEliminarTipo().addActionListener(e->mostrarVentanaBajaTipo(e));
			this.ventanaABMtipo.getBtnEditarTipo().addActionListener(e->mostrarVentanaEditarTipo(e));
			
		
			this.ventanaAltaTipo=ventanaAltaTipoContacto.getInstance();
			this.ventanaAltaTipo.getBtnAgregarNuevoTipo().addActionListener(a->agregarNuevoTipoContacto(a));
			
			this.ventanaBajaTipo=ventanaBajaTipoContacto.getInstance();
			this.ventanaBajaTipo.getBtnBorrarTipo().addActionListener(b->borrarTipoContacto(b));
			
			this.ventanaEditarTipo=ventanaEditarTipoContacto.getInstance();
			this.ventanaEditarTipo.getBtnEditarTipo().addActionListener(m->modificarTipoContacto(m));
			
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
		
		private void mostrarVentanaAltaTipo(ActionEvent a) {
			this.ventanaAltaTipo.mostrarVentana();
		}
		
		private void mostrarVentanaBajaTipo(ActionEvent e) {
			this.ventanaBajaTipo.mostrarVentana();
		}
		
		private void mostrarVentanaEditarTipo(ActionEvent e) {
			this.ventanaEditarTipo.mostrarVentana();
		}
		
		
			
		private void ventanaModificarPersona(ActionEvent m) {
			
			if(this.vista.getTablaPersonas().getSelectedRows().length>0) {
				
				this.ventanaEditarPersona.mostrarVentana();
				
				int filaSeleccionada = this.vista.getTablaPersonas().getSelectedRow();
				
				// para que la ventana ya tenga seleccionado el tipo de contacto que tiene la persona 
				tipoContacto tc= new tipoContacto();
				tiposDeContacto= tc.mostrarTiposContacto();
				int indice=0;
				while(!tiposDeContacto.get(indice).getTipo().equals(this.personasEnTabla.get(filaSeleccionada).getTipo())) {
					indice++;
				}
				
				// setea los campos para que aparezcan cargados de acuerdo a los datos de la persona
				this.ventanaEditarPersona.getTxtNombre().setText(this.personasEnTabla.get(filaSeleccionada).getNombre());
				this.ventanaEditarPersona.getTxtTelefono().setText(this.personasEnTabla.get(filaSeleccionada).getTelefono());
				this.ventanaEditarPersona.getTxtEmail().setText(this.personasEnTabla.get(filaSeleccionada).getEmail());
				this.ventanaEditarPersona.getTxtFechaNac().setText(this.personasEnTabla.get(filaSeleccionada).getFechaNac().toString());
				this.ventanaEditarPersona.getTxtTipo().setSelectedIndex(indice);
				this.ventanaEditarPersona.getTxtPais().setSelectedItem(this.personasEnTabla.get(filaSeleccionada).getPais());
				this.ventanaEditarPersona.getTxtProvincia().setSelectedItem(this.personasEnTabla.get(filaSeleccionada).getProvincia());
				this.ventanaEditarPersona.getTxtLocalidad().setSelectedItem(this.personasEnTabla.get(filaSeleccionada).getLocalidad());
			}
			else {
				JOptionPane.showMessageDialog(null, "Debe seleccionar un contacto de la agenda para editarlo");
			}
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
		
		public void borrarPersona(ActionEvent s)
		{
			int[] filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
			
			if(filasSeleccionadas.length>0) {
				for (int fila : filasSeleccionadas)
				{
					this.agenda.borrarPersona(this.personasEnTabla.get(fila));
				}
				this.refrescarTabla();
			}
			else {
				JOptionPane.showMessageDialog(null, "Debe seleccionar un contacto de la agenda para borrarlo");
			}
			
		}
		
		private void aplicarCambiosPersona(ActionEvent e) {
				
			int filaSeleccionada = this.vista.getTablaPersonas().getSelectedRow();
	
			PersonaDTO persona = this.personasEnTabla.get(filaSeleccionada);
	
			persona.setNombre(this.ventanaEditarPersona.getTxtNombre().getText());
			persona.setTelefono(this.ventanaEditarPersona.getTxtTelefono().getText());
			persona.setEmail(this.ventanaEditarPersona.getTxtEmail().getText());
			persona.setFechaNac(Date.valueOf(this.ventanaEditarPersona.getTxtFechaNac().getText()));
			persona.setTipo(this.ventanaEditarPersona.getTxtTipo().getSelectedItem().toString());
			persona.setDomicilio(this.ventanaEditarPersona.getTxtCalle().getText() + ","
					+ this.ventanaEditarPersona.getTxtAltura().getText() + ", Piso: "
					+ this.ventanaEditarPersona.getTxtPiso().getText() + ", Depto: "
					+ this.ventanaEditarPersona.getTxtDepto().getText());
			persona.setPais(this.ventanaEditarPersona.getTxtPais().getSelectedItem().toString());
			persona.setProvincia(this.ventanaEditarPersona.getTxtProvincia().getSelectedItem().toString());
			persona.setLocalidad(this.ventanaEditarPersona.getTxtLocalidad().getSelectedItem().toString());
	
			this.agenda.actualizarPersona(persona);
	
			this.refrescarTabla();
	
			this.ventanaEditarPersona.cerrar();

		}
		
		private void agregarNuevoTipoContacto(ActionEvent a) {
			String tipo= ventanaAltaTipo.getTxtNuevoTipo().getText();
			tipoContacto tc= new tipoContacto();
			tiposDeContacto=tc.mostrarTiposContacto();
			
			if(this.ventanaAltaTipo.getTxtNuevoTipo().getText().length()>0) {
				boolean esNuevo=true;
				for (tipoContacto t : tiposDeContacto) {
					if(t.getTipo().toLowerCase().equals(this.ventanaAltaTipo.getTxtNuevoTipo().getText().toLowerCase())) {
						esNuevo=false;
					}
				}
				if(esNuevo) {
					tc.setIdTipoContacto(0);
					tc.setTipo(tipo);
					tc.insertToMySQL(tc);
					
					this.refrescarTabla();
					this.refrescarTiposContacto();
					this.ventanaAltaTipo.cerrar();
				}
				else {
					JOptionPane.showMessageDialog(null,"No puede agregar un tipo de contacto que ya existe,por favor ingrese un nuevo tipo");
				}
			}
			else {
				JOptionPane.showMessageDialog(null,"No puede dejar el campo vacío, ingrese un valor válido");
			}		
			
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
		
		private void modificarTipoContacto(ActionEvent m) {
			tipoContacto tc= new tipoContacto();
			tiposDeContacto=tc.mostrarTiposContacto();
			int indice=this.ventanaEditarTipo.getCbxEditarTipo().getSelectedIndex();
			if(this.ventanaEditarTipo.getTxtEditarTipo().getText().length()>0) {
				boolean esNuevo=true;
				for (tipoContacto tipo : tiposDeContacto) {
					if(tipo.getTipo().toLowerCase().equals(this.ventanaEditarTipo.getTxtEditarTipo().getText().toLowerCase())) {
						esNuevo=false;
					}
				}
				if(esNuevo) {
				  tiposDeContacto.get(indice).setTipo(this.ventanaEditarTipo.getTxtEditarTipo().getText()); tc.updateToMySQL(tiposDeContacto.get(indice));
				  this.refrescarTabla();
				  this.refrescarTiposContacto();
				  this.ventanaEditarTipo.cerrar();
				}
				else {
					JOptionPane.showMessageDialog(null,"No puede cambiar el tipo de contacto por uno ya existente, por favor modifique por un nuevo tipo");
				}
			}
			else {
				JOptionPane.showMessageDialog(null,"No puede dejar el campo vacío, ingrese un valor válido");
			}		
		}

		private void mostrarReporte(ActionEvent r) {
			ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
			reporte.mostrar();	
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
			this.ventanaEditarTipo.getCbxEditarTipo().setModel(modeloTiposContacto);
			this.ventanaEditarPersona.getTxtTipo().setModel(modeloTiposContacto);
			
		}

		@Override
		public void actionPerformed(ActionEvent e) { }
		
}
