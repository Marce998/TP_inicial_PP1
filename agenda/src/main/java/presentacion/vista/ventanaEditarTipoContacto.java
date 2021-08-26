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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ventanaEditarTipoContacto extends JFrame{

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static ventanaEditarTipoContacto INSTANCE;
	private JComboBox cbxEditarTipo;
	private JButton btnEditarTipo;
	private JTextField txtEditarTipo;
	
	public static ventanaEditarTipoContacto getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new ventanaEditarTipoContacto(); 	
			return new ventanaEditarTipoContacto();
		}
		else
			return INSTANCE;
	}

	
	
	public ventanaEditarTipoContacto() {
		
		super();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				txtEditarTipo.setText(null);
			}
		});
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 465, 344);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 429, 283);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Seleccione el tipo de contacto a editar");
		lblTitulo.setBounds(0, 11, 429, 21);
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblTitulo);
		
		JLabel lblApliqueCambio = new JLabel("Aplique la modificaci\u00F3n");
		lblApliqueCambio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblApliqueCambio.setBounds(10, 152, 185, 14);
		panel.add(lblApliqueCambio);
		
		JLabel lblElijaUnTipo = new JLabel("Elija un tipo de contacto a editar");
		lblElijaUnTipo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblElijaUnTipo.setBounds(10, 73, 202, 14);
		panel.add(lblElijaUnTipo);
		
		cbxEditarTipo = new JComboBox();
		cbxEditarTipo.setBounds(222, 71, 197, 20);
		panel.add(cbxEditarTipo);
		
		tipoContacto tc= new tipoContacto();
		DefaultComboBoxModel modeloTiposContacto=  new DefaultComboBoxModel(tc.mostrarTiposContacto());
		cbxEditarTipo.setModel(modeloTiposContacto);
		
		txtEditarTipo = new JTextField();
		txtEditarTipo.setBounds(222, 150, 197, 20);
		panel.add(txtEditarTipo);
		txtEditarTipo.setColumns(10);
		
		btnEditarTipo = new JButton("Confirmar cambio");
		btnEditarTipo.setBounds(140, 213, 148, 23);
		panel.add(btnEditarTipo);
		
		
		
		this.setVisible(false);
	}
	
	public void mostrarVentana() {
		this.setVisible(true);
	}
	

	public JComboBox getCbxEditarTipo() {
		return cbxEditarTipo;
	}



	public JButton getBtnEditarTipo() {
		return btnEditarTipo;
	}



	public JTextField getTxtEditarTipo() {
		return txtEditarTipo;
	}

	

	public void cerrar() {
		this.txtEditarTipo.setText(null);
		this.dispose();
	}
}

