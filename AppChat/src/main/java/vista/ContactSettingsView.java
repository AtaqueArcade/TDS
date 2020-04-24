package vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import controlador.Controlador;

public class ContactSettingsView {
	public ContactSettingsView() {
	}

	public void show() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<String> userlist = Controlador.getInstance().getUsernamesByFilter("");
		JFrame frame = new JFrame("Contact settings");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("New contact", null, panel, "Add new contatcs to your contact list");
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		panel.add(panel_1, BorderLayout.WEST);

		Component verticalStrut_3 = Box.createVerticalStrut(20);
		panel_1.add(verticalStrut_3);
		JTextField textField = new JTextField();

		textField.setPreferredSize(new Dimension(100, 20));
		textField.setMinimumSize(new Dimension(100, 20));
		textField.setMaximumSize(new Dimension(100, 20));
		panel_1.add(textField);
		textField.setColumns(10);

		JButton btnSearch = new JButton("Search");
		Component verticalStrut_4 = Box.createVerticalStrut(10);
		panel_1.add(verticalStrut_4);
		btnSearch.setPreferredSize(new Dimension(100, 30));
		btnSearch.setMinimumSize(new Dimension(100, 30));
		btnSearch.setMaximumSize(new Dimension(100, 30));
		btnSearch.setAlignmentX(0.5f);
		panel_1.add(btnSearch);

		Component horizontalStrut_3 = Box.createHorizontalStrut(120);
		panel_1.add(horizontalStrut_3);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		panel.add(panel_2, BorderLayout.EAST);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panel_2.add(verticalStrut_1);

		JButton btnAdd = new JButton("Add contacts");
		btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAdd.setPreferredSize(new Dimension(100, 30));
		btnAdd.setMinimumSize(new Dimension(100, 30));
		btnAdd.setMaximumSize(new Dimension(100, 30));
		panel_2.add(btnAdd);
		Component horizontalStrut_2 = Box.createHorizontalStrut(120);
		panel_2.add(horizontalStrut_2);

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		JList list = new JList();
		DefaultListModel listModel = new DefaultListModel();
		for (int i = 0; i < userlist.size(); i++)
			listModel.addElement(userlist.get(i));
		list.setModel(listModel);
		btnSearch.addActionListener(e -> {
			try {
				listModel.clear();
				List<String> newlist = Controlador.getInstance().getUsernamesByFilter(textField.getText().trim());
				for (int i = 0; i < newlist.size(); i++)
					listModel.addElement(newlist.get(i));
				list.setModel(listModel);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		textField.addActionListener(e -> {
			btnSearch.doClick();
		});
		btnAdd.addActionListener(e -> {
			List<String> selections = list.getSelectedValuesList();
			boolean noContactIsRepeated;
			try {
				noContactIsRepeated = Controlador.getInstance().checkContactList(selections);
				if (noContactIsRepeated) {
					for (String username : selections)
						Controlador.getInstance().addContact(username);
					JOptionPane.showMessageDialog(new JFrame(),
							"Selected contacts [" + String.join(",", selections) + "] added succesfully!\n",
							"New contacts", JOptionPane.INFORMATION_MESSAGE);
				} else
					JOptionPane.showMessageDialog(new JFrame(),
							"Conflicting contacts selected: Contact already in your contact list detected.", "Error",
							JOptionPane.ERROR_MESSAGE);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		});
		scrollPane.setViewportView(list);
		JPanel panel2 = new JPanel();
		tabbedPane.addTab("Contact manager", null, panel2, "Hide, show or delete contacts on your list");
		panel2.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		panel2.add(panel_3, BorderLayout.WEST);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));

		Component verticalStrut_2 = Box.createVerticalStrut(20);
		panel_3.add(verticalStrut_2);
		JButton btnHide = new JButton("Hide");
		panel_3.add(btnHide);
		btnHide.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnHide.setPreferredSize(new Dimension(100, 30));
		btnHide.setMinimumSize(new Dimension(100, 30));
		btnHide.setMaximumSize(new Dimension(100, 30));
		JButton btnShow = new JButton("Show");
		panel_3.add(btnShow);
		btnShow.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnShow.setPreferredSize(new Dimension(100, 30));
		btnShow.setMinimumSize(new Dimension(100, 30));
		btnShow.setMaximumSize(new Dimension(100, 30));
		JButton btnDelete = new JButton("Delete");
		panel_3.add(btnDelete);
		btnDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnDelete.setPreferredSize(new Dimension(100, 30));
		btnDelete.setMinimumSize(new Dimension(100, 30));
		btnDelete.setMaximumSize(new Dimension(100, 30));

		Component horizontalStrut_1 = Box.createHorizontalStrut(120);
		panel_3.add(horizontalStrut_1);
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));

		Component verticalStrut = Box.createVerticalStrut(20);
		panel_4.add(verticalStrut);
		JLabel label = new JLabel("[User info]");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_4.add(label);
		panel2.add(panel_4, BorderLayout.EAST);

		JLabel lblmoreUserInfo = new JLabel("[More user info]");
		lblmoreUserInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_4.add(lblmoreUserInfo);

		Component horizontalStrut = Box.createHorizontalStrut(120);
		panel_4.add(horizontalStrut);

		Component verticalStrut_long = Box.createVerticalStrut(250);
		panel_4.add(verticalStrut_long);

		JList list_1 = new JList();
		JScrollPane scrollPane_1 = new JScrollPane(list_1);

		panel2.add(scrollPane_1, BorderLayout.CENTER);
		frame.getContentPane().add(tabbedPane);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
