package presentacion.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;

public class ventanaABMtipoContacto extends JFrame{

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnAgregarTipo;
	private JButton btnEliminarTipo;
	private JButton btnEditarTipo;
	private static ventanaABMtipoContacto INSTANCE;
	
	public static ventanaABMtipoContacto getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new ventanaABMtipoContacto(); 	
			return new ventanaABMtipoContacto();
		}
		else
			return INSTANCE;
	}

	
	
	public ventanaABMtipoContacto() {
		
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 465, 263);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 429, 202);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u00BFQu\u00E9 desea hacer con los tipos de contacto?");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 11, 429, 21);
		panel.add(lblNewLabel);
		
		btnAgregarTipo = new JButton("Agregar tipo");
		btnAgregarTipo.setBounds(155, 59, 118, 23);
		panel.add(btnAgregarTipo);
		
		btnEliminarTipo = new JButton("Eliminar tipo");
		btnEliminarTipo.setBounds(155, 104, 118, 23);
		panel.add(btnEliminarTipo);
		
		btnEditarTipo = new JButton("Editar tipo");
		btnEditarTipo.setBounds(155, 148, 118, 23);
		panel.add(btnEditarTipo);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana() {
		this.setVisible(true);
	}



	public JButton getBtnAgregarTipo() {
		return btnAgregarTipo;
	}



	public JButton getBtnEliminarTipo() {
		return btnEliminarTipo;
	}



	public JButton getBtnEditarTipo() {
		return btnEditarTipo;
	}
	
	public void cerrar() {
		this.dispose();
	}

}
