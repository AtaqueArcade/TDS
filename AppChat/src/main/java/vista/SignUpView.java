package vista;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.text.ParseException;

import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JDateChooser;
import controlador.Controlador;

@SuppressWarnings("serial")
public class SignUpView extends JPanel {

	private JTextField nameTextField;
	private JDateChooser birthdayDateChooser;
	private JTextField userTextField;
	private JTextField passwordTextField;
	private JTextField phoneTextField;
	private JLabel errorLabel;

	public SignUpView() {
		setBackground(Color.GRAY);
		setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		add(panel);

		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setPreferredSize(new Dimension(0, 40));
		panel.add(verticalStrut);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.GRAY);
		panel_2.add(panel_3, BorderLayout.NORTH);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panel_3.add(verticalStrut_1);
		verticalStrut_1.setPreferredSize(new Dimension(0, 40));

		JLabel lblBecomeAnAppchat = new JLabel("Become an Appchat member. Connect with people.");
		lblBecomeAnAppchat.setForeground(Color.WHITE);
		panel_3.add(lblBecomeAnAppchat);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.GRAY);
		panel_2.add(panel_4, BorderLayout.CENTER);

		Component verticalStrut_2 = Box.createVerticalStrut(20);
		verticalStrut_2.setPreferredSize(new Dimension(0, 40));
		panel_4.add(verticalStrut_2);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setForeground(Color.GRAY);
		splitPane.setBorder(new LineBorder(Color.GRAY, 1, true));
		splitPane.setBackground(Color.GRAY);
		splitPane.setEnabled(false);
		panel_4.add(splitPane);

		JPanel panel_5 = new JPanel();
		splitPane.setLeftComponent(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));

		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7, BorderLayout.SOUTH);
		panel_7.setLayout(new BorderLayout(0, 0));

		JPanel panel_15 = new JPanel();
		panel_15.setBackground(Color.GRAY);
		panel_7.add(panel_15, BorderLayout.NORTH);

		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setForeground(Color.BLACK);
		panel_15.add(lblPhone);

		MaskFormatter mf1 = null;
		try {
			mf1 = new MaskFormatter("#########");
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		phoneTextField = new JFormattedTextField(mf1);
		panel_15.add(phoneTextField);
		phoneTextField.setColumns(12);

		JPanel panel_11 = new JPanel();
		panel_11.setBackground(Color.GRAY);
		panel_5.add(panel_11, BorderLayout.NORTH);

		JLabel lblNewLabel_4 = new JLabel("Name\r\n");
		lblNewLabel_4.setForeground(Color.BLACK);
		panel_11.add(lblNewLabel_4);

		nameTextField = new JTextField();
		panel_11.add(nameTextField);
		nameTextField.setColumns(12);

		JPanel panel_12 = new JPanel();
		panel_12.setBackground(Color.GRAY);
		panel_5.add(panel_12, BorderLayout.CENTER);

		JLabel lblBirthday = new JLabel("Birthday");
		lblBirthday.setForeground(Color.BLACK);
		panel_12.add(lblBirthday);

		birthdayDateChooser = new JDateChooser();
		panel_12.add(birthdayDateChooser);

		JPanel panel_6 = new JPanel();
		splitPane.setRightComponent(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.GRAY);
		panel_6.add(panel_8, BorderLayout.NORTH);

		JLabel lblNewLabel_2 = new JLabel("User");
		lblNewLabel_2.setForeground(Color.BLACK);
		panel_8.add(lblNewLabel_2);

		userTextField = new JTextField();
		panel_8.add(userTextField);
		userTextField.setColumns(13);

		JPanel panel_9 = new JPanel();
		panel_6.add(panel_9, BorderLayout.SOUTH);
		panel_9.setLayout(new BorderLayout(0, 0));

		JPanel panel_14 = new JPanel();
		panel_14.setBackground(Color.GRAY);
		panel_9.add(panel_14, BorderLayout.NORTH);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e -> {
			Ventana.frame.getContentPane().removeAll();
			Ventana.frame.setContentPane(new LoginView());
			Ventana.frame.revalidate();
			Ventana.frame.repaint();
		});
		btnBack.setRequestFocusEnabled(false);
		btnBack.setRolloverEnabled(false);
		btnBack.setForeground(Color.DARK_GRAY);
		btnBack.setBackground(Color.LIGHT_GRAY);
		btnBack.setPreferredSize(new Dimension(80, 23));
		panel_14.add(btnBack);

		JButton btnSubmit = new JButton("Submit");

		btnSubmit.addActionListener(ev -> {
			if (fieldCheck()) {
				if (userTextField.getText().trim().length() < 6)
					JOptionPane.showMessageDialog(new JFrame(), "Username must be at least 6 characters long.",
							"Register", JOptionPane.ERROR_MESSAGE);
				else if (passwordTextField.getText().trim().length() < 6)
					JOptionPane.showMessageDialog(new JFrame(), "Password must be at least 6 characters long.",
							"Register", JOptionPane.ERROR_MESSAGE);
				else
					try {
						if (Controlador.getInstance().register(nameTextField.getText().trim(),
								birthdayDateChooser.getDate(), Integer.parseInt(phoneTextField.getText()),
								userTextField.getText().trim(), passwordTextField.getText().trim())) {
							JOptionPane.showMessageDialog(new JFrame(), "Registered succesfully!\n", "Sign up",
									JOptionPane.INFORMATION_MESSAGE);
							Ventana.frame.getContentPane().removeAll();
							Ventana.frame.setContentPane(new LoginView());
							Ventana.frame.revalidate();
							Ventana.frame.repaint();
						} else {
							JOptionPane.showMessageDialog(new JFrame(),
									"Error: username '" + userTextField.getText().trim() + "' is not available.\n",
									"Sign up", JOptionPane.ERROR_MESSAGE);
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});

		btnSubmit.setRolloverEnabled(false);
		btnSubmit.setRequestFocusEnabled(false);
		btnSubmit.setPreferredSize(new Dimension(80, 23));
		btnSubmit.setForeground(Color.DARK_GRAY);
		btnSubmit.setBackground(Color.LIGHT_GRAY);
		panel_14.add(btnSubmit);

		JPanel panel_10 = new JPanel();
		panel_10.setBackground(Color.GRAY);
		panel_6.add(panel_10, BorderLayout.CENTER);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setForeground(Color.BLACK);
		panel_10.add(lblNewLabel_1);

		passwordTextField = new JPasswordField();
		panel_10.add(passwordTextField);
		passwordTextField.setColumns(10);

		Component verticalStrut_3 = Box.createVerticalStrut(20);
		verticalStrut_3.setPreferredSize(new Dimension(0, 40));
		panel_4.add(verticalStrut_3);

		JPanel panel_13 = new JPanel();
		panel_13.setBackground(Color.GRAY);
		panel_1.add(panel_13, BorderLayout.SOUTH);
		panel_13.setLayout(new BorderLayout(0, 0));

		JPanel panel_16 = new JPanel();
		panel_16.setBackground(Color.GRAY);
		panel_1.add(panel_16, BorderLayout.CENTER);

		errorLabel = new JLabel("Please, complete all the fields to register.");
		errorLabel.setForeground(Color.RED);
		panel_16.add(errorLabel);
		errorLabel.setVisible(false);

		nameTextField.addActionListener(e -> {
			userTextField.requestFocusInWindow();
		});
		userTextField.addActionListener(e -> {
			passwordTextField.requestFocusInWindow();
		});
		passwordTextField.addActionListener(e -> {
			phoneTextField.requestFocusInWindow();
		});
		phoneTextField.addActionListener(e -> {
			btnSubmit.doClick();
		});
	}

	private boolean fieldCheck() {
		errorLabel.setVisible(false);
		if (nameTextField.getText().trim().isEmpty() || userTextField.getText().trim().isEmpty()
				|| passwordTextField.getText().trim().isEmpty() || phoneTextField.getText().trim().isEmpty()) {
			errorLabel.setVisible(true);
			return false;
		}
		return true;
	}
}
