package presentacion.vista;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import persistencia.datosDesplegables.mysql.Localidad;
import persistencia.datosDesplegables.mysql.Pais;
import persistencia.datosDesplegables.mysql.Provincia;

public class ventanaAltaLocalidad extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static ventanaAltaLocalidad INSTANCE;
	private JTextField txtNuevaLocalidad;
	private JComboBox txtPais;
	private JComboBox txtProvincia;
	private JButton btnAgregarNuevaLocalidad;
	
	public static ventanaAltaLocalidad getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new ventanaAltaLocalidad(); 	
			return new ventanaAltaLocalidad();
		}
		else
			return INSTANCE;
	}
	
	public ventanaAltaLocalidad() {
		
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 465, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 429, 239);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Agregue la nueva localidad");
		lblNewLabel.setBounds(0, 11, 429, 21);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		txtNuevaLocalidad = new JTextField();
		txtNuevaLocalidad.setBounds(95, 163, 239, 20);
		panel.add(txtNuevaLocalidad);
		txtNuevaLocalidad.setColumns(10);
		
		txtPais = new JComboBox();
		txtPais.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Pais pais = (Pais) txtPais.getSelectedItem();
				Provincia provincia = new Provincia();
				DefaultComboBoxModel modeloProvincia = new DefaultComboBoxModel(provincia.mostrarProvincias(pais.getIdPais())); 
				txtProvincia.setModel(modeloProvincia);
			}
		});
		txtPais.setBounds(95, 43, 239, 20);
		Pais paises = new Pais();
		DefaultComboBoxModel modeloPaises = new DefaultComboBoxModel(paises.mostrarPaises());
		txtPais.setModel(modeloPaises);
		panel.add(txtPais);
		
		txtProvincia = new JComboBox();
		txtProvincia.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Provincia provincia = (Provincia) txtProvincia.getSelectedItem();
				Localidad localidad = new Localidad();
				DefaultComboBoxModel modeloLocalidad = new DefaultComboBoxModel(localidad.mostrarLocalidades(provincia.getIdProvincia())); 
			}
		});
		txtProvincia.setBounds(95, 90, 239, 20);
		Provincia provincias = new Provincia();
		DefaultComboBoxModel modeloProvincias = new DefaultComboBoxModel(provincias.mostrarProvincias(paises.getIdPais()));
		txtProvincia.setModel(modeloProvincias);
		panel.add(txtProvincia);
		
		btnAgregarNuevaLocalidad= new JButton("Agregar");
		btnAgregarNuevaLocalidad.setBounds(169, 205, 89, 23);
		panel.add(btnAgregarNuevaLocalidad);
		
		JLabel lblNewLabel_1 = new JLabel("Ingrese la Localidad:");
		lblNewLabel_1.setBounds(95, 138, 100, 14);
		panel.add(lblNewLabel_1);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana() {
		this.setVisible(true);
	}

	public JTextField getTxtNuevaLocalidad() {
		return txtNuevaLocalidad;
	}

	public JButton getBtnAgregarNuevaLocalidad() {
		return btnAgregarNuevaLocalidad;
	}
	
	public JComboBox getTxtPais() {
		return txtPais;
	}
	
	public JComboBox getTxtProvincia() {
		return txtProvincia;
	}


	public void cerrar() {
		this.txtNuevaLocalidad.setText(null);
		this.dispose();
	}
}