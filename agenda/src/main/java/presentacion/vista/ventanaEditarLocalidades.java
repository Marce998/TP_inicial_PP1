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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ventanaEditarLocalidades extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static ventanaEditarLocalidades INSTANCE;
	private JComboBox txtPais;
	private JComboBox txtProvincia;
	private JComboBox txtLocalidad;
	private JButton btnEditarPais;
	private JButton btnEditarProvincia;
	private JButton btnEditarLocalidad;
	private JTextField txtNuevoPais;
	private JTextField txtNuevaProvincia;
	private JTextField txtNuevaLocalidad;
	
	public static ventanaEditarLocalidades getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new ventanaEditarLocalidades(); 	
			return new ventanaEditarLocalidades();
		}
		else
			return INSTANCE;
	}
	
	public ventanaEditarLocalidades() {
		
		super();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				txtPais.setSelectedIndex(0);
			}
		});
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 564, 339);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Editar Localidades");
		lblNewLabel.setBounds(0, 11, 564, 21);
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
		txtPais.setBounds(75, 100, 162, 22);		
				
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
		txtProvincia.setBounds(75, 186, 162, 22);
		panel.add(txtProvincia);
		
		txtLocalidad = new JComboBox();
		txtLocalidad.setBounds(75, 275, 162, 22);
		panel.add(txtLocalidad);
		
		btnEditarPais = new JButton("Editar");
		btnEditarPais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditarPais.setBounds(257, 127, 89, 23);
		panel.add(btnEditarPais);
		
		btnEditarProvincia = new JButton("Editar");
		btnEditarPais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditarProvincia.setBounds(257, 216, 89, 23);
		panel.add(btnEditarProvincia);
		
		btnEditarLocalidad= new JButton("Editar");
		btnEditarPais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditarLocalidad.setBounds(257, 307, 89, 23);
		panel.add(btnEditarLocalidad);
		
		txtNuevoPais = new JTextField();
		txtNuevoPais.setBounds(75, 128, 162, 20);
		panel.add(txtNuevoPais);
		txtNuevoPais.setColumns(10);
		
		txtNuevaProvincia= new JTextField();
		txtNuevaProvincia.setBounds(75, 219, 162, 20);
		panel.add(txtNuevaProvincia);
		txtNuevaProvincia.setColumns(10);
		
		txtNuevaLocalidad= new JTextField();
		txtNuevaLocalidad.setBounds(75, 308, 162, 20);
		panel.add(txtNuevaLocalidad);
		txtNuevaLocalidad.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Editar Localidad");
		lblNewLabel_1.setBounds(75, 250, 162, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Editar Provincia");
		lblNewLabel_2.setBounds(75, 161, 162, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Editar Pais");
		lblNewLabel_3.setBounds(75, 76, 162, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Seleccione una opci\u00F3n, luego escriba el nuevo nombre y presione Editar");
		lblNewLabel_4.setBounds(75, 43, 470, 21);
		panel.add(lblNewLabel_4);
		
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
	
	public JTextField getTxtNuevoPais() {
		return txtNuevoPais;
	}
	
	public JTextField getTxtNuevaProvincia() {
		return txtNuevaProvincia;
	}
	
	public JTextField getTxtNuevaLocalidad() {
		return txtNuevaLocalidad;
	}
	
	public JButton getBtnEditarPais() {
		return btnEditarPais;
	}
	
	public JButton getBtnEditarProvincia() {
		return btnEditarProvincia;
	}
	
	public JButton getBtnEditarLocalidad() {
		return btnEditarLocalidad;
	}
	
	public void cerrar() {
		this.txtNuevoPais.setText(null);
		this.txtNuevaProvincia.setText(null);
		this.txtNuevaLocalidad.setText(null);
		this.dispose();
	}
}