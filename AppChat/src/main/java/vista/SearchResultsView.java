package vista;

import java.awt.Dimension;
import tds.BubbleText;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import controlador.Controlador;
import modelo.Mensaje;
import java.awt.BorderLayout;
import java.awt.Color;

public class SearchResultsView {
	public SearchResultsView(String search) {
		JFrame frame = new JFrame("Search results");
		frame.setPreferredSize(new Dimension(400, 300));
		frame.setMinimumSize(new Dimension(400, 300));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel chat = new JPanel();
		chat.setLayout(new BoxLayout(chat, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportView(chat);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		try {
			for (Mensaje m : Controlador.getInstance().getCurrentContact().getMensajes()) {
				if (m.getText().contains(search)) {
					BubbleText bubble = new BubbleText(chat, m.getText(), Color.LIGHT_GRAY, m.getSpeaker(),
							BubbleText.SENT);
					chat.add(bubble);
				}
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
