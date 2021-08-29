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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import persistencia.datosDesplegables.mysql.Localidad;
import persistencia.datosDesplegables.mysql.Pais;
import persistencia.datosDesplegables.mysql.Provincia;

public class ventanaBajaLocalidad extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static ventanaBajaLocalidad INSTANCE;
	private JComboBox txtPais;
	private JComboBox txtProvincia;
	private JComboBox txtLocalidad;
	private JButton btnBorrarLocalidad;
	
	public static ventanaBajaLocalidad getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new ventanaBajaLocalidad(); 	
			return new ventanaBajaLocalidad();
		}
		else
			return INSTANCE;
	}
	
	public ventanaBajaLocalidad() {
		
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 465, 282);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 429, 223);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione la localidad a borrar");
		lblNewLabel.setBounds(0, 11, 429, 21);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		txtPais = new JComboBox();
		txtPais.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Pais pais = (Pais) txtPais.getSelectedItem();
				Provincia provincia = new Provincia();
				DefaultComboBoxModel modeloProvincia = new DefaultComboBoxModel(provincia.mostrarProvincias(pais.getIdPais())); 
				txtProvincia.setModel(modeloProvincia);
				txtLocalidad.removeAllItems();
			}
		});
		txtPais.setBounds(132, 54, 162, 22);		
				
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
				txtLocalidad.setModel(modeloLocalidad);
			}
		});
		txtProvincia.setBounds(132, 87, 162, 22);
		panel.add(txtProvincia);
		
		txtLocalidad = new JComboBox();
		txtLocalidad.setBounds(132, 120, 162, 22);
		panel.add(txtLocalidad);
		
		btnBorrarLocalidad= new JButton("Borrar");
		btnBorrarLocalidad.setBounds(173, 182, 89, 23);
		panel.add(btnBorrarLocalidad);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana() {
		this.setVisible(true);
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
	public JButton getBtnBorrarLocalidad() {
		return btnBorrarLocalidad;
	}

	public void cerrar() {
		this.dispose();
	}
}