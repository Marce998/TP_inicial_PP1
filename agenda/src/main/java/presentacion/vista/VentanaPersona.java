package presentacion.vista;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import persistencia.conexion.Conexion;
import persistencia.datosDesplegables.mysql.Localidad;
import persistencia.datosDesplegables.mysql.Pais;
import persistencia.datosDesplegables.mysql.Provincia;
import persistencia.datosDesplegables.mysql.tipoContacto;


public class VentanaPersona extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtFechaNac;
	private JTextField txtCalle;
	private JTextField txtAltura;
	private JTextField txtPiso;
	private JTextField txtDepto;
	private JComboBox txtPais;
	private JComboBox txtProvincia;
	private JComboBox txtLocalidad;
	private JComboBox txtTipo;
	private ArrayList<String> paises = new ArrayList<String>();
	private ArrayList<String> provincias = new ArrayList<String>();
	private ArrayList<String> localidades = new ArrayList<String>();
	private JButton btnAgregarPersona;
	private static VentanaPersona INSTANCE;
	
	public static VentanaPersona getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaPersona(); 	
			return new VentanaPersona();
		}
		else
			return INSTANCE;
	}

	private VentanaPersona() 
	{
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 454, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 418, 600);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(10, 11, 113, 14);
		panel.add(lblNombreYApellido);
		
		JLabel lblTelfono = new JLabel("Telefono");
		lblTelfono.setBounds(10, 52, 113, 14);
		panel.add(lblTelfono);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 89, 46, 14);
		panel.add(lblEmail);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento");
		lblFechaDeNacimiento.setBounds(10, 132, 113, 14);
		panel.add(lblFechaDeNacimiento);
		
		JLabel lblFormatoYyyymmdd = new JLabel("formato YYYY-MM-DD");
		lblFormatoYyyymmdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblFormatoYyyymmdd.setBounds(133, 117, 164, 14);
		panel.add(lblFormatoYyyymmdd);
		
		JLabel domicilio = new JLabel("Domicilio:");
		domicilio.setBounds(10, 200, 100, 14);
		panel.add(domicilio);
		
		JLabel calle = new JLabel("Calle");
		calle.setBounds(20, 230, 50, 14);
		panel.add(calle);
		
		JLabel altura = new JLabel("Altura");
		altura.setBounds(20, 260, 70, 14);
		panel.add(altura);
		
		JLabel piso = new JLabel("Piso");
		piso.setBounds(20, 290, 50, 14);
		panel.add(piso);
		
		JLabel depto = new JLabel("Depto.");
		depto.setBounds(20, 320, 50, 14);
		panel.add(depto);
		
		JLabel pais = new JLabel("País");
		pais.setBounds(10, 360, 50, 14);
		panel.add(pais);
		
		JLabel provincia = new JLabel("Provincia");
		provincia.setBounds(10, 400, 50, 14);
		panel.add(provincia);
		
		JLabel localidad = new JLabel("Localidad");
		localidad.setBounds(10, 440, 50, 14);
		panel.add(localidad);
		
		JLabel tipo = new JLabel("Tipo");
		tipo.setBounds(10, 157, 50, 14);
		panel.add(tipo);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(133, 8, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(133, 49, 164, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(133, 86, 164, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtFechaNac = new JTextField();
		txtFechaNac.setBounds(133, 129, 164, 20);
		panel.add(txtFechaNac);
		txtFechaNac.setColumns(10);
		
		txtCalle = new JTextField();
		txtCalle.setBounds(133, 230, 164, 20);
		panel.add(txtCalle);
		txtCalle.setColumns(10);
		
		txtAltura = new JTextField();
		txtAltura.setBounds(133, 260, 164, 20);
		panel.add(txtAltura);
		txtAltura.setColumns(10);
		
		txtPiso = new JTextField();
		txtPiso.setBounds(133, 290, 164, 20);
		panel.add(txtPiso);
		txtPiso.setColumns(10);
		
		txtDepto = new JTextField();
		txtDepto.setBounds(133, 320, 164, 20);
		panel.add(txtDepto);
		txtDepto.setColumns(10);
		
		
		txtTipo = new JComboBox();
		txtTipo.setBounds(132, 153, 165, 22);
		panel.add(txtTipo);
		
		tipoContacto tc= new tipoContacto();
		DefaultComboBoxModel modeloTiposContacto=  new DefaultComboBoxModel(tc.mostrarTiposContacto());
		txtTipo.setModel(modeloTiposContacto);
				
		txtPais = new JComboBox(paises.toArray());
		txtPais.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Pais pais = (Pais) txtPais.getSelectedItem();
				Provincia provincia = new Provincia();
				DefaultComboBoxModel modeloProvincia = new DefaultComboBoxModel(provincia.mostrarProvincias(pais.getIdPais())); 
				txtProvincia.setModel(modeloProvincia);
				txtLocalidad.removeAllItems();
			}
		});
		txtPais.setBounds(132, 360, 162, 22);		
				
		Pais paises = new Pais();
		DefaultComboBoxModel modeloPaises = new DefaultComboBoxModel(paises.mostrarPaises());
		txtPais.setModel(modeloPaises);
		panel.add(txtPais);
			
		txtProvincia = new JComboBox(provincias.toArray());
		txtProvincia.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Provincia provincia = (Provincia) txtProvincia.getSelectedItem();
				Localidad localidad = new Localidad();
				DefaultComboBoxModel modeloLocalidad = new DefaultComboBoxModel(localidad.mostrarLocalidades(provincia.getIdProvincia())); 
				txtLocalidad.setModel(modeloLocalidad);
			}
		});
		txtProvincia.setBounds(132, 400, 162, 22);
		panel.add(txtProvincia);
					
		txtLocalidad = new JComboBox(localidades.toArray());
		txtLocalidad.setBounds(132, 440, 162, 22);
		panel.add(txtLocalidad);
		
		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.setBounds(208, 480, 89, 23);
		panel.add(btnAgregarPersona);
						
		this.setVisible(false);
	}
	
	public void mostrarVentana()
	{
		this.setVisible(true);
	}
	
	public JTextField getTxtNombre() 
	{
		return txtNombre;
	}

	public JTextField getTxtTelefono() 
	{
		return txtTelefono;
	}
	
	public JTextField getTxtEmail() {
		return txtEmail;
	}
	
	public JTextField getTxtFechaNac() {
		return txtFechaNac;
	}
	
	public JTextField getTxtCalle() {
		return txtCalle;
	}
	
	public JTextField getTxtAltura() {
		return txtAltura;
	}
	
	public JTextField getTxtPiso() {
		return txtPiso;
	}
	
	public JTextField getTxtDepto() {
		return txtDepto;
	}
	
	public JComboBox getTxtTipo() {
		return txtTipo;
	}
	
	public JComboBox getTxtPais() {
		return txtPais;
	}
	
	public JComboBox getTxtProvincia() {
		return txtProvincia;
	}
	
	public JComboBox getTxtLocalidad() {
		return txtLocalidad;
	}
	
	public JButton getBtnAgregarPersona() 
	{
		return btnAgregarPersona;
	}
	
	public void cerrar()
	{
		this.txtNombre.setText(null);
		this.txtTelefono.setText(null);
		this.txtEmail.setText(null);
		this.txtFechaNac.setText(null);
		this.txtAltura.setText(null);
		this.txtCalle.setText(null);
		this.txtDepto.setText(null);
		this.dispose();
	}
}

