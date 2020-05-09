package vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import controlador.Controlador;
import modelo.Contacto;
import modelo.ContactoIndividual;
import modelo.Grupo;

public class GroupSettingsView {
	public GroupSettingsView() {
	}

	/**
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @wbp.parser.entryPoint
	 */
	public void show() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<Contacto> contacts = Controlador.getInstance().getCurrentContacts();
		JTextField textFieldGroupName;
		JFrame frame = new JFrame("Group settings");
		frame.setPreferredSize(new Dimension(400, 300));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("New group", null, panel, "Create a new group chat");
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_8 = new JPanel();
		panel.add(panel_8, BorderLayout.NORTH);

		Component horizontalStrut_6 = Box.createHorizontalStrut(25);
		panel_8.add(horizontalStrut_6);

		JLabel lblContacts = new JLabel("Contact list\r\n");
		lblContacts.setHorizontalAlignment(SwingConstants.LEFT);
		panel_8.add(lblContacts);

		Component horizontalStrut_7 = Box.createHorizontalStrut(45);
		panel_8.add(horizontalStrut_7);

		JLabel lblGroupName = new JLabel("Group name\r\n");
		lblGroupName.setHorizontalAlignment(SwingConstants.CENTER);
		panel_8.add(lblGroupName);

		Component horizontalStrut_8 = Box.createHorizontalStrut(27);
		panel_8.add(horizontalStrut_8);

		JLabel lblContactsToBe = new JLabel("Contacts to be added\r\n");
		lblContactsToBe.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_8.add(lblContactsToBe);

		Component horizontalStrut_9 = Box.createHorizontalStrut(20);
		panel_8.add(horizontalStrut_9);

		JList<String> list = new JList<String>();
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for (int i = 0; i < contacts.size(); i++) {
			if (contacts.get(i) instanceof ContactoIndividual)
				listModel.addElement(contacts.get(i).getName());
		}
		list.setModel(listModel);
		JPanel panel_5 = new JPanel();
		panel.add(panel_5, BorderLayout.WEST);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel_5.add(horizontalStrut);

		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setPreferredSize(new Dimension(100, 200));
		panel_5.add(scrollPane);

		JList<String> list_2 = new JList<String>();
		DefaultListModel<String> listModel_2 = new DefaultListModel<String>();
		list_2.setModel(listModel_2);
		JPanel scrollPane_2 = new JPanel();
		panel.add(scrollPane_2, BorderLayout.EAST);

		JScrollPane scrollPane_3 = new JScrollPane(list_2);
		scrollPane_3.setPreferredSize(new Dimension(100, 200));
		scrollPane_2.add(scrollPane_3);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		scrollPane_2.add(horizontalStrut_1);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		panel.add(panel_2, BorderLayout.CENTER);

		JButton btnNewButton_2 = new JButton("-->");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<String> selections = list.getSelectedValuesList();
				for (String username : selections) {
					listModel.removeElement(username);
					listModel_2.addElement(username);
					list.setModel(listModel);
					list_2.setModel(listModel_2);
				}
			}
		});
		btnNewButton_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panel_2.add(verticalStrut_1);

		textFieldGroupName = new JTextField();
		textFieldGroupName.setToolTipText("Enter the group's name here");
		textFieldGroupName.setPreferredSize(new Dimension(100, 20));
		textFieldGroupName.setMinimumSize(new Dimension(100, 20));
		textFieldGroupName.setMaximumSize(new Dimension(100, 20));
		panel_2.add(textFieldGroupName);
		textFieldGroupName.setColumns(10);
		textFieldGroupName.setPreferredSize(new Dimension(100, 20));
		textFieldGroupName.setMinimumSize(new Dimension(100, 20));
		textFieldGroupName.setMaximumSize(new Dimension(100, 20));
		panel_2.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("<--");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<String> selections = list_2.getSelectedValuesList();
				for (String username : selections) {
					listModel_2.removeElement(username);
					listModel.addElement(username);
					list.setModel(listModel);
					list_2.setModel(listModel_2);
				}
			}
		});
		btnNewButton_3.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_2.add(btnNewButton_3);

		Component verticalStrut = Box.createVerticalStrut(57);
		panel_2.add(verticalStrut);

		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnConfirm.setPreferredSize(new Dimension(100, 20));
		btnConfirm.setMinimumSize(new Dimension(100, 20));
		btnConfirm.setMaximumSize(new Dimension(100, 20));
		panel_2.add(btnConfirm);

		Component verticalStrut_2 = Box.createVerticalStrut(5);
		panel_2.add(verticalStrut_2);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnCancel.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnCancel.setPreferredSize(new Dimension(100, 20));
		btnCancel.setMinimumSize(new Dimension(100, 20));
		btnCancel.setMaximumSize(new Dimension(100, 20));
		panel_2.add(btnCancel);
		btnCancel.addActionListener(e -> {
			frame.dispose();
		});
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<String> contactList = IntStream.range(0, listModel_2.size()).mapToObj(listModel_2::get)
						.map(element -> (String) element).collect(Collectors.toList());
				try {
					Controlador.getInstance().addContact(textFieldGroupName.getText().trim(), contactList);
					JOptionPane.showMessageDialog(new JFrame(),
							"Group [" + textFieldGroupName.getText().trim() + "] created succesfully!\n", "New group",
							JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		JPanel panel2 = new JPanel();
		tabbedPane.addTab("Group manager", null, panel2, "Edit or delete existing groups");
		panel2.setLayout(new BorderLayout(0, 0));

		JList<String> list2 = new JList<String>();
		DefaultListModel<String> listModel2 = new DefaultListModel<String>();
		for (int i = 0; i < contacts.size(); i++) {
			if (contacts.get(i) instanceof Grupo)
				listModel2.addElement(contacts.get(i).getName());
		}
		list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list2.setModel(listModel2);
		JPanel panel_6 = new JPanel();
		panel2.add(panel_6, BorderLayout.WEST);

		Component horizontalStrut1 = Box.createHorizontalStrut(20);
		panel_6.add(horizontalStrut1);

		JScrollPane scrollPane1 = new JScrollPane(list2);
		scrollPane1.setPreferredSize(new Dimension(100, 200));

		panel_6.add(scrollPane1);

		JList<String> list3 = new JList<String>();
		JPanel panel1 = new JPanel();
		panel2.add(panel1, BorderLayout.EAST);
		JScrollPane scrollPane_ = new JScrollPane(list3);
		scrollPane_.setPreferredSize(new Dimension(100, 200));
		panel1.add(scrollPane_);
		Component horizontalStrut_ = Box.createHorizontalStrut(20);
		panel1.add(horizontalStrut_);
		JPanel panel4 = new JPanel();
		panel2.add(panel4, BorderLayout.CENTER);
		panel4.setLayout(new BorderLayout(0, 0));
		JPanel panel_7 = new JPanel();
		panel4.add(panel_7, BorderLayout.CENTER);
		panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
		JList<String> contacts2 = new JList<String>();
		DefaultListModel<String> listModel3 = new DefaultListModel<String>();
		DefaultListModel<String> listModel_3 = new DefaultListModel<String>();
		list2.addListSelectionListener(e -> {
			List<String> contactList = null;
			try {
				contactList = Controlador.getInstance().getGroupComponents(list2.getSelectedValue());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			listModel3.clear();
			for (int i = 0; i < contactList.size(); i++) {
				listModel3.addElement(contactList.get(i));
			}
			list3.setModel(listModel3);
			listModel_3.clear();
			for (int i = 0; i < contacts.size(); i++) {
				if (contacts.get(i) instanceof ContactoIndividual && !contactList.contains(contacts.get(i).getName()))
					listModel_3.addElement(contacts.get(i).getName());
			}
			list.setModel(listModel);
			contacts2.setModel(listModel_3);
		});
		JScrollPane scrollPane_1 = new JScrollPane(contacts2);
		scrollPane_1.setPreferredSize(new Dimension(100, 80));
		panel_7.add(scrollPane_1);

		JButton button_1 = new JButton("<--");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<String> selections = list3.getSelectedValuesList();
				for (String username : selections) {
					listModel_3.addElement(username);
					listModel3.removeElement(username);
					contacts2.setModel(listModel_3);
					list3.setModel(listModel3);
				}
			}
		});
		button_1.setAlignmentX(0.5f);
		panel_7.add(button_1);

		JButton btnNewButton_1 = new JButton("Delete group\r\n");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<String> selections = list2.getSelectedValuesList();
				try {
					if (Controlador.getInstance().deleteGroups(selections)) {
						JOptionPane.showMessageDialog(new JFrame(), "Group deleted succesfully.", "Delete groups",
								JOptionPane.INFORMATION_MESSAGE);
						frame.dispose();
					} else
						JOptionPane.showMessageDialog(new JFrame(),
								"You don't have permision to delete the selected group.", "Delete groups",
								JOptionPane.INFORMATION_MESSAGE);
				} catch (HeadlessException | InstantiationException | IllegalAccessException
						| ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		JButton button = new JButton("-->");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<String> selections = contacts2.getSelectedValuesList();
				for (String username : selections) {
					listModel_3.removeElement(username);
					listModel3.addElement(username);
					contacts2.setModel(listModel_3);
					list3.setModel(listModel3);
				}
			}
		});
		button.setAlignmentX(0.5f);
		panel_7.add(button);
		btnNewButton_1.setPreferredSize(new Dimension(100, 20));
		btnNewButton_1.setMinimumSize(new Dimension(100, 20));
		btnNewButton_1.setMaximumSize(new Dimension(100, 20));
		panel_7.add(btnNewButton_1);

		JButton button_2 = new JButton("Confirm");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					List<String> contactList = IntStream.range(0, listModel3.size()).mapToObj(listModel3::get)
							.map(element -> (String) element).collect(Collectors.toList());
					if (Controlador.getInstance().editGroup(list2.getSelectedValuesList().get(0), contactList)) {
						JOptionPane.showMessageDialog(new JFrame(),
								"Group [" + list2.getSelectedValuesList().get(0) + "] edited succesfully!\n",
								"Edit group", JOptionPane.INFORMATION_MESSAGE);
					} else
						JOptionPane.showMessageDialog(new JFrame(),
								"Group [" + list2.getSelectedValuesList().get(0) + "] couldn't be edited.\n",
								"Edit group", JOptionPane.INFORMATION_MESSAGE);
				} catch (HeadlessException | InstantiationException | IllegalAccessException
						| ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.dispose();
			}
		});
		button_2.setPreferredSize(new Dimension(100, 20));
		button_2.setMinimumSize(new Dimension(100, 20));
		button_2.setMaximumSize(new Dimension(100, 20));
		button_2.setAlignmentX(0.5f);
		panel_7.add(button_2);

		JButton button_3 = new JButton("Cancel");
		button_3.addActionListener(e -> {
			frame.dispose();
		});
		button_3.setPreferredSize(new Dimension(100, 20));
		button_3.setMinimumSize(new Dimension(100, 20));
		button_3.setMaximumSize(new Dimension(100, 20));
		button_3.setAlignmentX(0.5f);
		panel_7.add(button_3);
		JPanel l3 = new JPanel();
		panel2.add(l3, BorderLayout.NORTH);

		Component horizontalStrut_2 = Box.createHorizontalStrut(25);
		l3.add(horizontalStrut_2);
		JLabel lblNewLabel = new JLabel("Select the group");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		l3.add(lblNewLabel);
		Component horizontalStrut_4 = Box.createHorizontalStrut(30);
		l3.add(horizontalStrut_4);
		JLabel lblNewLabel_1 = new JLabel("Your contacts");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		l3.add(lblNewLabel_1);
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		l3.add(horizontalStrut_5);
		JLabel lblNewLabel_2 = new JLabel("Contacts inside group");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		l3.add(lblNewLabel_2);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		l3.add(horizontalStrut_3);
		frame.getContentPane().add(tabbedPane);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
