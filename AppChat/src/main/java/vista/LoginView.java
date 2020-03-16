package vista;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JTextField;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;

@SuppressWarnings("serial")
public class LoginView extends JPanel{

	private JTextField txtNombre;
	private JTextField txtPasswd;

	public LoginView(){
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		add(panel, BorderLayout.NORTH);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalStrut_1.setPreferredSize(new Dimension(0, 50));
		panel.add(verticalStrut_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		add(panel_1, BorderLayout.SOUTH);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setPreferredSize(new Dimension(0, 50));
		panel_1.add(verticalStrut);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.GRAY);
		panel_3.add(panel_4, BorderLayout.NORTH);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setForeground(Color.BLACK);
		panel_4.add(lblUser);
		
		txtNombre = new JTextField();
		txtNombre.setPreferredSize(new Dimension(10, 20));
		panel_4.add(txtNombre);
		txtNombre.setColumns(13);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.GRAY);
		panel_3.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.GRAY);
		panel_5.add(panel_6, BorderLayout.SOUTH);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(Color.DARK_GRAY);
		btnLogin.setBackground(Color.LIGHT_GRAY);
		btnLogin.setActionCommand("btnLogin");
		panel_6.add(btnLogin);
		btnLogin.addActionListener(e -> {
			/*String user = txtNombre.getText();
			String passwd = txtPasswd.getText();
				boolean login = Controlador.getUnicaInstancia().login(user,passwd);
				if (login){
					removeAll();
				}
				else {//Popup
			}*/
			JOptionPane.showMessageDialog(new JFrame(),"Logeado correctamente", "Login",JOptionPane.INFORMATION_MESSAGE);
			Ventana.frame.getContentPane().removeAll();
			Ventana.frame.setVisible(false);
			Ventana.frame.dispose();
			Ventana.loadAppView();
		});
		JButton btnRegister = new JButton("Register");
		btnRegister.setBackground(Color.LIGHT_GRAY);
		btnRegister.setForeground(Color.DARK_GRAY);
		panel_6.add(btnRegister);
		btnRegister.addActionListener(e -> {
				Ventana.frame.getContentPane().removeAll();
				Ventana.frame.setContentPane(new SignUpView());
				Ventana.frame.revalidate();
				Ventana.frame.repaint();
		});
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.GRAY);
		panel_5.add(panel_7, BorderLayout.NORTH);
		
		JLabel label = new JLabel("Password");
		label.setForeground(Color.BLACK);
		panel_7.add(label);
		
		txtPasswd = new JTextField();
		txtPasswd.setColumns(10);
		panel_7.add(txtPasswd);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.GRAY);
		panel_5.add(panel_8, BorderLayout.CENTER);
		
		JLabel lblPleaseCompleteAll = new JLabel("Please, complete all the fields to log in!");
		lblPleaseCompleteAll.setForeground(Color.RED);
		lblPleaseCompleteAll.setBackground(Color.GRAY);
		lblPleaseCompleteAll.setVisible(false);
		panel_8.add(lblPleaseCompleteAll);
	}
}
