package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

import modelo.Mensaje;
import controlador.Controlador;
import tds.BubbleText;

@SuppressWarnings("serial")
public class ChatView extends JPanel {
	private JTextField textField;
	private int emoji;

	public ChatView() {
		setLayout(new BorderLayout(0, 0));

		JPanel chat = new JPanel();
		chat.setLayout(new BoxLayout(chat, BoxLayout.Y_AXIS));
		chat.setSize(400, 700);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(chat);
		emoji = -1;
		/*
		 * BubbleText burbuja; burbuja = new BubbleText(chat, "Hola grupo!!",
		 * Color.GREEN, "J.Ramón", BubbleText.SENT); chat.add(burbuja);
		 * 
		 * BubbleText burbuja2; burbuja2 = new BubbleText(chat,
		 * "Hola, ¿Está seguro de que la burbuja usa varias lineas si es necesario?"
		 * , Color.LIGHT_GRAY, "Alumno", BubbleText.RECEIVED);
		 * chat.add(burbuja2);
		 * 
		 * BubbleText burbuja3; burbuja3 = new BubbleText(chat,
		 * "No estoy seguro", Color.GREEN, "J.Ramón", BubbleText.SENT, 24);
		 * chat.add(burbuja3);
		 * 
		 * BubbleText burbujaEmoji = new BubbleText(chat, 1, Color.GREEN,
		 * "J.Ramón", BubbleText.SENT, 18); chat.add(burbujaEmoji);
		 */
		JPanel contactsPanel = new JPanel();
		add(contactsPanel, BorderLayout.WEST);
		JPanel toolbarPanel = new JPanel();
		add(toolbarPanel, BorderLayout.NORTH);

		JPanel tray = new JPanel();
		add(tray, BorderLayout.SOUTH);
		JPanel trayPanel = new JPanel();
		tray.add(trayPanel);

		JButton btnEmoji = new JButton("Emoji");
		btnEmoji.addActionListener(ev -> {
			abrirSelectorEmojis();
		});
		trayPanel.add(btnEmoji);

		textField = new JTextField();
		trayPanel.add(textField);
		textField.setColumns(35);

		JPanel panel_1 = new JPanel();
		tray.add(panel_1);

		JButton btnSend = new JButton("Send");
		panel_1.add(btnSend);
		btnSend.addActionListener(e -> {
			// TODO añadir persistencia de mensajes
			Mensaje message = new Mensaje(textField.getText(), 0);
			BubbleText bubble;
			bubble = new BubbleText(chat, textField.getText(), Color.LIGHT_GRAY,
					Controlador.getInstance().getCurrentUser(), BubbleText.RECEIVED);
			chat.add(bubble);
		});
		add(scrollPane, BorderLayout.CENTER);
	}

	private void abrirSelectorEmojis() {
	}
}