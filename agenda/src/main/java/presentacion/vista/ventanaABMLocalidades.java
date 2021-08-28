package presentacion.vista;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ventanaABMLocalidades extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnAgregarLocalidad;
	private JButton btnEliminarLocalidad;
	private JButton btnEditarLocalidad;
	private static ventanaABMLocalidades INSTANCE;
	
	public static ventanaABMLocalidades getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new ventanaABMLocalidades(); 	
			return new ventanaABMLocalidades();
		}
		else
			return INSTANCE;
	}
	
	public ventanaABMLocalidades() {
		
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
		
		JLabel lblNewLabel = new JLabel("\u00BFQu\u00E9 desea hacer con las localidades?");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 11, 429, 21);
		panel.add(lblNewLabel);
		
		btnAgregarLocalidad = new JButton("Agregar Localidad");
		btnAgregarLocalidad.setBounds(155, 59, 118, 23);
		panel.add(btnAgregarLocalidad);
		
		btnEliminarLocalidad = new JButton("Eliminar Localidad");
		btnEliminarLocalidad.setBounds(155, 104, 118, 23);
		panel.add(btnEliminarLocalidad);
		
		btnEditarLocalidad = new JButton("Editar Localidad");
		btnEditarLocalidad.setBounds(155, 148, 118, 23);
		panel.add(btnEditarLocalidad);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana() {
		this.setVisible(true);
	}

	public JButton getBtnAgregarLocalidad() {
		return btnAgregarLocalidad;
	}


	public JButton getBtnEliminarLocalidad() {
		return btnEliminarLocalidad;
	}

	public JButton getBtnEditarLocalidad() {
		return btnEditarLocalidad;
	}
	
	public void cerrar() {
		this.dispose();
	}

}