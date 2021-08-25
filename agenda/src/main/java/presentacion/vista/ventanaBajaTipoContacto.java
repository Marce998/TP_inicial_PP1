package presentacion.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import persistencia.datosDesplegables.mysql.tipoContacto;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class ventanaBajaTipoContacto extends JFrame{

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static ventanaBajaTipoContacto INSTANCE;
	private JLabel lblElijaUnTipo;
	private JComboBox cbxBajaTipo;
	private JButton btnBorrarTipo;
	
	public static ventanaBajaTipoContacto getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new ventanaBajaTipoContacto(); 	
			return new ventanaBajaTipoContacto();
		}
		else
			return INSTANCE;
	}

	
	
	public ventanaBajaTipoContacto() {
		
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 465, 298);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 429, 237);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione el tipo de contacto a eliminar");
		lblNewLabel.setBounds(0, 11, 429, 21);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		lblElijaUnTipo = new JLabel("Elija un tipo de contacto a borrar");
		lblElijaUnTipo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblElijaUnTipo.setBounds(10, 73, 202, 14);
		panel.add(lblElijaUnTipo);
		
		cbxBajaTipo = new JComboBox();
		cbxBajaTipo.setBounds(222, 71, 197, 20);
		panel.add(cbxBajaTipo);
		
		tipoContacto tc= new tipoContacto();
		DefaultComboBoxModel modeloTiposContacto=  new DefaultComboBoxModel(tc.mostrarTiposContacto());
		cbxBajaTipo.setModel(modeloTiposContacto);
		
		btnBorrarTipo = new JButton("Borrar tipo");
		btnBorrarTipo.setBounds(169, 168, 89, 23);
		panel.add(btnBorrarTipo);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana() {
		this.setVisible(true);
	}
	

	public JComboBox getCbxBajaTipo() {
		return cbxBajaTipo;
	}


	public JButton getBtnBorrarTipo() {
		return btnBorrarTipo;
	}


	public void cerrar() {
		this.dispose();
	}
}
