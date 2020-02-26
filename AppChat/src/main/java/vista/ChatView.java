package vista;

import java.awt.*;
import javax.swing.*;
import tds.BubbleText;

@SuppressWarnings("serial")
public class ChatView extends JPanel {
	private JTextField textField;

	public ChatView() {
		setLayout(new BorderLayout(0, 0));

		JPanel chat = new JPanel();
		chat.setLayout(new BoxLayout(chat, BoxLayout.Y_AXIS));
		chat.setSize(400, 700);
		chat.setMinimumSize(new Dimension(400, 700));
		chat.setMaximumSize(new Dimension(400, 700));
		chat.setPreferredSize(new Dimension(400, 418));
		JScrollPane scrollPane = new JScrollPane(chat);

		BubbleText burbuja;
		burbuja = new BubbleText(chat, "Hola grupo!!", Color.GREEN, "J.Ramón", BubbleText.SENT);
		chat.add(burbuja);

		BubbleText burbuja2;
		burbuja2 = new BubbleText(chat, "Hola, ¿Está seguro de que la burbuja usa varias lineas si es necesario?",
				Color.LIGHT_GRAY, "Alumno", BubbleText.RECEIVED);
		chat.add(burbuja2);

		BubbleText burbuja3;
		burbuja3 = new BubbleText(chat, "No estoy seguro", Color.GREEN, "J.Ramón", BubbleText.SENT, 24);
		chat.add(burbuja3);

		BubbleText burbujaEmoji = new BubbleText(chat, 1, Color.GREEN, "J.Ramón", BubbleText.SENT, 18);
		chat.add(burbujaEmoji);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.NORTH);

		JPanel tray = new JPanel();
		add(tray, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		tray.add(panel);
		
		JButton btnEmoji = new JButton("Emoji");
		panel.add(btnEmoji);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		tray.add(panel_1);
		
		JButton btnSend = new JButton("Send");
		panel_1.add(btnSend);

		add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		scrollPane.setRowHeaderView(panel_3);
	}
}