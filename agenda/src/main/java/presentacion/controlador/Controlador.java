package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import modelo.Agenda;
import persistencia.datosDesplegables.mysql.Localidad;
import persistencia.datosDesplegables.mysql.Pais;
import persistencia.datosDesplegables.mysql.Provincia;
import persistencia.datosDesplegables.mysql.tipoContacto;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaEditarPersona;
import presentacion.vista.VentanaPersona;
import presentacion.vista.Vista;
import presentacion.vista.ventanaABMtipoContacto;
import presentacion.vista.ventanaAltaTipoContacto;
import presentacion.vista.ventanaBajaTipoContacto;
import presentacion.vista.ventanaEditarTipoContacto;
import presentacion.vista.ventanaABMLocalidades;
import presentacion.vista.ventanaAltaLocalidades;
import presentacion.vista.ventanaAltaPais;
import presentacion.vista.ventanaAltaProvincia;
import presentacion.vista.ventanaAltaLocalidad;
import presentacion.vista.ventanaBajaLocalidades;
import presentacion.vista.ventanaBajaPais;
import presentacion.vista.ventanaBajaProvincia;
import presentacion.vista.ventanaBajaLocalidad;
import presentacion.vista.ventanaEditarLocalidades;
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
		private ventanaABMLocalidades ventanaABMLocalidades;
		private ventanaAltaLocalidades ventanaAltaLocalidades;
		private ventanaAltaPais ventanaAltaPais;
		private ventanaAltaProvincia ventanaAltaProvincia;
		private ventanaAltaLocalidad ventanaAltaLocalidad;
		private ventanaBajaLocalidades ventanaBajaLocalidades;
		private ventanaBajaPais ventanaBajaPais;
		private ventanaBajaProvincia ventanaBajaProvincia;
		private ventanaBajaLocalidad ventanaBajaLocalidad;
		private ventanaEditarLocalidades ventanaEditarLocalidades;
		private Agenda agenda;
		
		public Controlador(Vista vista, Agenda agenda)
		{
			this.vista = vista;
			this.vista.getBtnAgregar().addActionListener(a->ventanaAgregarPersona(a));
			this.vista.getBtnBorrar().addActionListener(s->borrarPersona(s));
			this.vista.getBtnReporte().addActionListener(r->mostrarReporte(r));
			this.vista.getBtnEditar().addActionListener(m->ventanaModificarPersona(m));
			this.vista.getBtnABMtipoContacto().addActionListener(abmTipo->mostrarVentanaABMtipo(abmTipo));
			this.vista.getBtnABMLocalidades().addActionListener(abmLocalidades->mostrarVentanaABMLocalidades(abmLocalidades));
			
			
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
			
			this.ventanaABMLocalidades = ventanaABMLocalidades.getInstance();
			this.ventanaABMLocalidades.getBtnAgregarLocalidad().addActionListener(ls->ventanaAltaLocalidades(ls));
			this.ventanaABMLocalidades.getBtnEliminarLocalidad().addActionListener(ls->ventanaBajaLocalidades(ls));
			this.ventanaABMLocalidades.getBtnEditarLocalidad().addActionListener(ls->ventanaEditarLocalidades(ls));
			
			this.ventanaAltaLocalidades = ventanaAltaLocalidades.getInstance();
			this.ventanaAltaLocalidades.getBtnAgregarNuevoPais().addActionListener(pa->ventanaAltaPais(pa));
			this.ventanaAltaLocalidades.getBtnAgregarNuevaProvincia().addActionListener(pr->ventanaAltaProvincia(pr));
			this.ventanaAltaLocalidades.getBtnAgregarNuevaLocalidad().addActionListener(lo->ventanaAltaLocalidad(lo));
			
			this.ventanaAltaPais = ventanaAltaPais.getInstance();
			this.ventanaAltaPais.getBtnAgregarNuevoPais().addActionListener(a->agregarPais(a));
			
			this.ventanaAltaProvincia = ventanaAltaProvincia.getInstance();
			this.ventanaAltaProvincia.getBtnAgregarNuevaProvincia().addActionListener(a->agregarProvincia(a));
			
			this.ventanaAltaLocalidad = ventanaAltaLocalidad.getInstance();
			this.ventanaAltaLocalidad.getBtnAgregarNuevaLocalidad().addActionListener(a->agregarLocalidad(a));
			
			this.ventanaBajaLocalidades = ventanaBajaLocalidades.getInstance();
			this.ventanaBajaLocalidades.getBtnBorrarPais().addActionListener(b->ventanaBajaPais(b));
			this.ventanaBajaLocalidades.getBtnBorrarProvincia().addActionListener(b->ventanaBajaProvincia(b));
			this.ventanaBajaLocalidades.getBtnBorrarLocalidad().addActionListener(b->ventanaBajaLocalidad(b));
			
			this.ventanaBajaPais = ventanaBajaPais.getInstance();
			this.ventanaBajaPais.getBtnBorrarPais().addActionListener(b->borrarPais(b));
			
			this.ventanaBajaProvincia = ventanaBajaProvincia.getInstance();
			this.ventanaBajaProvincia.getBtnBorrarProvincia().addActionListener(b->borrarProvincia(b));
			
			this.ventanaBajaLocalidad = ventanaBajaLocalidad.getInstance();
			this.ventanaBajaLocalidad.getBtnBorrarLocalidad().addActionListener(b->borrarLocalidad(b));
			
			this.ventanaEditarLocalidades = ventanaEditarLocalidades.getInstance();
			this.ventanaEditarLocalidades.getBtnEditarPais().addActionListener(e->editarPais(e));
			this.ventanaEditarLocalidades.getBtnEditarProvincia().addActionListener(e->editarProvincia(e));
			this.ventanaEditarLocalidades.getBtnEditarLocalidad().addActionListener(e->editarLocalidad(e));
			
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
		
		private void mostrarVentanaABMLocalidades(ActionEvent abmLocalidades) {
			this.ventanaABMLocalidades.mostrarVentana();
		}
		
		private void ventanaAltaLocalidades(ActionEvent ls) {
			this.ventanaAltaLocalidades.mostrarVentana();
		}
		
		private void ventanaAltaPais(ActionEvent pa) {
			this.ventanaAltaPais.mostrarVentana();;
		}
		
		private void ventanaAltaProvincia(ActionEvent pr) {
			this.ventanaAltaProvincia.mostrarVentana();
		}
		
		private void ventanaAltaLocalidad(ActionEvent lo) {
			this.ventanaAltaLocalidad.mostrarVentana();
		}
		
		private void ventanaBajaLocalidades(ActionEvent e) {
			this.ventanaBajaLocalidades.mostrarVentana();
		}
		
		private void ventanaBajaPais(ActionEvent e) {
			this.ventanaBajaPais.mostrarVentana();
		}
		
		private void ventanaBajaProvincia(ActionEvent e) {
			this.ventanaBajaProvincia.mostrarVentana();
		}
		
		private void ventanaBajaLocalidad(ActionEvent e) {
			this.ventanaBajaLocalidad.mostrarVentana();
		}
		
		private void ventanaEditarLocalidades(ActionEvent e) {
			this.ventanaEditarLocalidades.mostrarVentana();
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
				
				Pais p= new Pais();
				List<Pais> paises= p.mostrarPaises();
				int indicePais=0;
				while(!paises.get(indicePais).getNombrePais().equals(this.personasEnTabla.get(filaSeleccionada).getPais())) {
					indicePais++;
				}
				
				Provincia prov= new Provincia();
				List<Provincia> provincias=prov.mostrarProvincias(paises.get(indicePais).getIdPais());
				int indiceProv=0;
				while(!provincias.get(indiceProv).getNombreProvincia().equals(this.personasEnTabla.get(filaSeleccionada).getProvincia())) {
					indiceProv++;
				}
				
				Localidad loc= new Localidad();
				List<Localidad> localidades = loc.mostrarLocalidades(provincias.get(indiceProv).getIdProvincia());
				int indiceLoc=0;
				while(!localidades.get(indiceLoc).getNombreLocalidad().equals(this.personasEnTabla.get(filaSeleccionada).getLocalidad())) {
					indiceLoc++;
				}
				
				
				// setea los campos para que aparezcan cargados de acuerdo a los datos de la persona
				this.ventanaEditarPersona.getTxtNombre().setText(this.personasEnTabla.get(filaSeleccionada).getNombre());
				this.ventanaEditarPersona.getTxtTelefono().setText(this.personasEnTabla.get(filaSeleccionada).getTelefono());
				this.ventanaEditarPersona.getTxtEmail().setText(this.personasEnTabla.get(filaSeleccionada).getEmail());
				this.ventanaEditarPersona.getTxtFechaNac().setText(this.personasEnTabla.get(filaSeleccionada).getFechaNac().toString());
				this.ventanaEditarPersona.getTxtTipo().setSelectedIndex(indice);
				this.ventanaEditarPersona.getTxtPais().setSelectedIndex(indicePais);
				this.ventanaEditarPersona.getTxtProvincia().setSelectedIndex(indiceProv);
				this.ventanaEditarPersona.getTxtLocalidad().setSelectedIndex(indiceLoc);
				//this.ventanaEditarPersona.getTxtPaisPref().setSelectedIndex(indicePaisPref);
				//this.ventanaEditarPersona.getTxtProvinciaPref().setSelectedIndex(indiceProvPref);
				//this.ventanaEditarPersona.getTxtLocalidadPref().setSelectedIndex(indiceLocPref);
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
			String ciudadPref = ventanaPersona.getTxtPaisPref().getSelectedItem().toString() + ", "
					+ ventanaPersona.getTxtProvinciaPref().getSelectedItem().toString() + ", " 
					+ ventanaPersona.getTxtLocalidadPref().getSelectedItem().toString();
			PersonaDTO nuevaPersona = new PersonaDTO(0, nombre, tel,email,fechaNac,domicilio,tipo,pais,provincia,localidad,ciudadPref);
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
			persona.setCiudadPref(this.ventanaEditarPersona.getTxtPaisPref().getSelectedItem().toString() + ", " + this.ventanaEditarPersona.getTxtProvinciaPref().getSelectedItem().toString() + ", " + this.ventanaEditarPersona.getTxtLocalidadPref().getSelectedItem().toString());
	
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
		
		private void agregarPais(ActionEvent a) {
			String stringPais = ventanaAltaPais.getTxtNuevoPais().getText();
			Pais nuevoPais = new Pais();
			nuevoPais.setIdPais(0);
			nuevoPais.setNombrePais(stringPais);
			
			
			if(stringPais.length()>0) {
				boolean esNuevo = true;
				List<Pais> listaPaises = nuevoPais.mostrarPaises();
				for(Pais pais : listaPaises) {
					if (nuevoPais.getNombrePais().toLowerCase().equals(pais.getNombrePais().toLowerCase())) {
						esNuevo = false;
					}
				}
				if(esNuevo) {
					nuevoPais.insertToMySQL(nuevoPais);
					this.refrescarTabla();
					this.refrescarPais();
					this.ventanaAltaPais.cerrar();
				}
				else {
					JOptionPane.showMessageDialog(null, "No puede agregar un país ya existente");
				}
				
				
			}
			else {
				JOptionPane.showMessageDialog(null,"No puede dejar el campo vacío, ingrese un valor válido");
			}		
		}
		
		private void agregarProvincia(ActionEvent a) {
			String stringProvincia = ventanaAltaProvincia.getTxtNuevaProvincia().getText();
			Provincia nuevaProvincia = new Provincia();
			nuevaProvincia.setIdProvincia(0);
			nuevaProvincia.setNombreProvincia(stringProvincia);
			Pais pais = new Pais();
			pais.setIdPais(ventanaAltaProvincia.getTxtPais().getSelectedIndex());
			
			boolean esValido = true;
			
			if(ventanaAltaProvincia.getTxtPais().getSelectedIndex() == 0) {
				esValido = false;
			}
			
			if(esValido) {
				if(stringProvincia.length()>0) {
					List<Provincia> listaProvincias = nuevaProvincia.mostrarProvincias(pais.getIdPais());
					for(Provincia provincia: listaProvincias) {
						if (nuevaProvincia.getNombreProvincia().toLowerCase().equals(provincia.getNombreProvincia().toLowerCase())) {
							esValido = false;
						}
					}
					if(esValido) {
						nuevaProvincia.setIdPais(pais.getIdPais());
						nuevaProvincia.insertToMySQL(nuevaProvincia);
						this.refrescarTabla();
						this.refrescarProvincia(pais.getIdPais());
						this.ventanaAltaProvincia.cerrar();
					}
					else {
						JOptionPane.showMessageDialog(null, "No puede agregar una provincia ya existente");
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null,"No puede dejar el campo vacío, ingrese un valor válido");
				}
			}
			else {
				JOptionPane.showMessageDialog(null,"Se debe seleccionar un país");
			}			
		}
		
		private void agregarLocalidad(ActionEvent a) {
			Pais pais = (Pais) ventanaAltaLocalidad.getTxtPais().getSelectedItem();
			Provincia provincia = (Provincia) ventanaAltaLocalidad.getTxtProvincia().getSelectedItem();
			
			String stringLocalidad = ventanaAltaLocalidad.getTxtNuevaLocalidad().getText();
			Localidad nuevaLocalidad= new Localidad();
			nuevaLocalidad.setIdLocalidad(0);
			nuevaLocalidad.setNombreLocalidad(stringLocalidad);
			
			boolean esValido = true;
			
			if(ventanaAltaLocalidad.getTxtPais().getSelectedIndex() == 0 || ventanaAltaLocalidad.getTxtProvincia().getSelectedIndex() == 0) {
				esValido = false;
			}
			
			if(esValido) {
				if(stringLocalidad.length()>0) {
					List<Localidad> listaLocalidades = nuevaLocalidad.mostrarLocalidades(provincia.getIdProvincia());
					for(Localidad localidad: listaLocalidades) {
						if (nuevaLocalidad.getNombreLocalidad().toLowerCase().equals(localidad.getNombreLocalidad().toLowerCase())) {
							esValido= false;
						}
					}
					if(esValido) {
						nuevaLocalidad.setIdProvincia(provincia.getIdProvincia());;
						nuevaLocalidad.insertToMySQL(nuevaLocalidad);
						this.refrescarTabla();
						this.refrescarLocalidad(provincia.getIdProvincia());
						this.ventanaAltaLocalidad.cerrar();
					}
					else {
						JOptionPane.showMessageDialog(null, "No puede agregar una localidad ya existente");
					}
				}
				else {
					JOptionPane.showMessageDialog(null,"No puede dejar el campo vacío, ingrese un valor válido");
				}
			}
			else {
				JOptionPane.showMessageDialog(null,"Se debe seleccionar un país y provincia");
			}	
					
		}
		
		private void borrarPais(ActionEvent b) {
			if(ventanaBajaPais.getTxtPais().getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(null,"Se debe seleccionar país");
			}
			else {
				Pais pais = (Pais) ventanaBajaPais.getTxtPais().getSelectedItem();
				
				pais.deleteFromMySql(pais.getIdPais());
				this.refrescarTabla();
				this.refrescarPais();
				this.ventanaBajaPais.cerrar();
			}		
		}
		
		private void borrarProvincia(ActionEvent b) {
			if(ventanaBajaProvincia.getTxtPais().getSelectedIndex() == 0 || ventanaBajaProvincia.getTxtProvincia().getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(null,"Se debe seleccionar país y provincia");
			}
			else {
				Pais pais = (Pais) ventanaBajaProvincia.getTxtPais().getSelectedItem();
				Provincia provincia= (Provincia) ventanaBajaProvincia.getTxtProvincia().getSelectedItem();
				
				provincia.deleteFromMySql(provincia.getIdProvincia());
				this.refrescarTabla();
				this.refrescarProvincia(pais.getIdPais());
				this.ventanaBajaProvincia.cerrar();
			}		
		}
		
		private void borrarLocalidad(ActionEvent b) {
			if(ventanaBajaLocalidad.getTxtPais().getSelectedIndex() == 0 || ventanaBajaLocalidad.getTxtProvincia().getSelectedIndex() == 0 || ventanaBajaLocalidad.getTxtLocalidad().getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(null,"Se debe seleccionar país, provincia y localidad");
			}
			else {
				Localidad localidad = (Localidad) ventanaBajaLocalidad.getTxtLocalidad().getSelectedItem();
				Provincia provincia = (Provincia) ventanaBajaLocalidad.getTxtProvincia().getSelectedItem();
				
				localidad.deleteFromMySql(localidad.getIdLocalidad());
				this.refrescarTabla();
				this.refrescarLocalidad(provincia.getIdProvincia());
				this.ventanaBajaLocalidad.cerrar();
			}
		}
		
		private void editarPais(ActionEvent e) {
			if(ventanaEditarLocalidades.getTxtPais().getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(null,"Se debe seleccionar país");
			}
			else if(ventanaEditarLocalidades.getTxtNuevoPais().getText().length() > 0){
				Pais pais = (Pais) ventanaEditarLocalidades.getTxtPais().getSelectedItem();
				String nuevoPais = ventanaEditarLocalidades.getTxtNuevoPais().getText();
				
				pais.updateToMySql(pais.getIdPais(), nuevoPais);
				this.refrescarTabla();
				this.refrescarPais();
				this.ventanaEditarLocalidades.cerrar();
			}
			else {
				JOptionPane.showMessageDialog(null,"No puede dejar el campo vacío, ingrese un valor válido");
			}				
		}
		
		private void editarProvincia(ActionEvent e) {
			if(ventanaEditarLocalidades.getTxtPais().getSelectedIndex() == 0 || ventanaEditarLocalidades.getTxtProvincia().getSelectedIndex() == 0 ) {
				JOptionPane.showMessageDialog(null,"Se debe seleccionar país y provincia");
			}
			else if(ventanaEditarLocalidades.getTxtNuevaProvincia().getText().length() > 0) {
				Pais pais = (Pais) ventanaEditarLocalidades.getTxtPais().getSelectedItem();
				Provincia provincia = (Provincia) ventanaEditarLocalidades.getTxtProvincia().getSelectedItem();
				String nuevaProvincia = ventanaEditarLocalidades.getTxtNuevaProvincia().getText();
				
				provincia.updateToMySql(provincia.getIdProvincia(), nuevaProvincia);
				this.refrescarTabla();
				this.refrescarProvincia(pais.getIdPais());
				this.ventanaEditarLocalidades.cerrar();
			}
			else {
				JOptionPane.showMessageDialog(null,"No puede dejar el campo vacío, ingrese un valor válido");
			}	
			
		}
		
		private void editarLocalidad(ActionEvent e) {
			if(ventanaEditarLocalidades.getTxtPais().getSelectedIndex() == 0 || ventanaEditarLocalidades.getTxtProvincia().getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(null,"Se debe seleccionar país, provincia y localidad");
			}
			else if(ventanaEditarLocalidades.getTxtNuevaLocalidad().getText().length() > 0) {
				Provincia provincia = (Provincia) ventanaEditarLocalidades.getTxtProvincia().getSelectedItem();
				Localidad localidad = (Localidad) ventanaEditarLocalidades.getTxtLocalidad().getSelectedItem();
				String nuevaLocalidad = ventanaEditarLocalidades.getTxtNuevaLocalidad().getText();
				
				localidad.updateToMySql(localidad.getIdLocalidad(), nuevaLocalidad);
				this.refrescarTabla();
				this.refrescarLocalidad(provincia.getIdProvincia());
				this.ventanaEditarLocalidades.cerrar();	
			}
			else {
				JOptionPane.showMessageDialog(null,"No puede dejar el campo vacío, ingrese un valor válido");
			}				
		}

		private void mostrarReporte(ActionEvent r) {
			ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonasPorTipo());
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
		
		private void refrescarPais() {
			Pais pais = new Pais();
			DefaultComboBoxModel modeloPaises = new DefaultComboBoxModel(pais.mostrarPaises());
			this.ventanaPersona.getTxtPais().setModel(modeloPaises);
			this.ventanaPersona.getTxtPaisPref().setModel(modeloPaises);
			this.ventanaEditarPersona.getTxtPais().setModel(modeloPaises);
			this.ventanaEditarPersona.getTxtPaisPref().setModel(modeloPaises);
			this.ventanaAltaProvincia.getTxtPais().setModel(modeloPaises);
			this.ventanaAltaLocalidad.getTxtPais().setModel(modeloPaises);
			this.ventanaBajaPais.getTxtPais().setModel(modeloPaises);
			this.ventanaBajaProvincia.getTxtPais().setModel(modeloPaises);
			this.ventanaBajaLocalidad.getTxtPais().setModel(modeloPaises);
			this.ventanaEditarLocalidades.getTxtPais().setModel(modeloPaises);
		}
		
		private void refrescarProvincia(int idPais) {
			Provincia provincia = new Provincia();
			DefaultComboBoxModel modeloProvincias = new DefaultComboBoxModel(provincia.mostrarProvincias(idPais));
			this.ventanaPersona.getTxtProvincia().setModel(modeloProvincias);
			this.ventanaPersona.getTxtProvinciaPref().setModel(modeloProvincias);
			this.ventanaEditarPersona.getTxtProvincia().setModel(modeloProvincias);
			this.ventanaEditarPersona.getTxtProvinciaPref().setModel(modeloProvincias);
			this.ventanaAltaLocalidad.getTxtProvincia().setModel(modeloProvincias);
			this.ventanaBajaProvincia.getTxtProvincia().setModel(modeloProvincias);
			this.ventanaBajaLocalidad.getTxtProvincia().setModel(modeloProvincias);
			this.ventanaEditarLocalidades.getTxtProvincia().setModel(modeloProvincias);
		}
		
		private void refrescarLocalidad(int idProvincia) {
			Localidad localidad = new Localidad();
			DefaultComboBoxModel modeloLocalidades = new DefaultComboBoxModel(localidad.mostrarLocalidades(idProvincia));
			this.ventanaPersona.getTxtLocalidad().setModel(modeloLocalidades);
			this.ventanaPersona.getTxtLocalidadPref().setModel(modeloLocalidades);
			this.ventanaEditarPersona.getTxtLocalidad().setModel(modeloLocalidades);
			this.ventanaEditarPersona.getTxtLocalidadPref().setModel(modeloLocalidades);
			this.ventanaBajaLocalidad.getTxtLocalidad().setModel(modeloLocalidades);
			this.ventanaEditarLocalidades.getTxtLocalidad().setModel(modeloLocalidades);
		}

		@Override
		public void actionPerformed(ActionEvent e) { }
		
}
