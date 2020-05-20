package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.io.File;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.IntStream;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;

import controlador.Controlador;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.JButton;

public class StatisticsView {
	private int totalAdded;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void show() throws InstantiationException, IllegalAccessException, ClassNotFoundException,
			UnsupportedLookAndFeelException {
		Font font = new Font("Open Sans", Font.PLAIN, 12);
		JFrame frame = new JFrame("Statistics");
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		int messagesSent = Controlador.getInstance().getDataMessagesSent();
		int messagesReceived = Controlador.getInstance().getDataMessagesReceived();
		int nContacts = Controlador.getInstance().getDataContactAmount();
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		frame.getContentPane().add(panel, BorderLayout.EAST);

		Component verticalStrut = Box.createVerticalStrut(20);
		panel.add(verticalStrut);

		JPanel panel_3 = new JPanel();
		panel_3.setMaximumSize(new Dimension(120, Integer.MAX_VALUE));

		panel_3.setBackground(Color.GRAY);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		panel.add(panel_3);

		Component verticalStrut_10 = Box.createVerticalStrut(20);
		panel_3.add(verticalStrut_10);

		JLabel label = new JLabel("AppChat scores:");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Dialog", Font.BOLD, 12));
		label.setAlignmentX(0.5f);
		panel_3.add(label);

		Component verticalStrut_1 = Box.createVerticalStrut(30);
		panel_3.add(verticalStrut_1);

		JLabel label_1 = new JLabel("Messages sent:");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_1.setAlignmentX(0.5f);
		panel_3.add(label_1);

		Component verticalStrut_2 = Box.createVerticalStrut(5);
		panel_3.add(verticalStrut_2);

		JLabel label_2 = new JLabel(Integer.toString(messagesSent));
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_2.setAlignmentX(0.5f);
		panel_3.add(label_2);

		Component verticalStrut_3 = Box.createVerticalStrut(30);
		panel_3.add(verticalStrut_3);

		JLabel label_3 = new JLabel("Messages received:");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_3.setAlignmentX(0.5f);
		panel_3.add(label_3);

		Component verticalStrut_4 = Box.createVerticalStrut(5);
		panel_3.add(verticalStrut_4);

		JLabel label_4 = new JLabel(Integer.toString(messagesReceived));
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_4.setAlignmentX(0.5f);
		panel_3.add(label_4);

		Component verticalStrut_5 = Box.createVerticalStrut(30);
		panel_3.add(verticalStrut_5);

		JLabel label_5 = new JLabel("Contacts added:");
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_5.setAlignmentX(0.5f);
		panel_3.add(label_5);

		Component verticalStrut_6 = Box.createVerticalStrut(5);
		panel_3.add(verticalStrut_6);

		JLabel label_6 = new JLabel(Integer.toString(nContacts));
		label_6.setForeground(Color.WHITE);
		label_6.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_6.setAlignmentX(0.5f);
		panel_3.add(label_6);

		Component verticalStrut_9 = Box.createVerticalStrut(10);
		panel.add(verticalStrut_9);
		JButton btnNewButton = new JButton("Export contacts");
		btnNewButton.setFont(font);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setOpaque(true);
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(btnNewButton);

		Component verticalStrut_8 = Box.createVerticalStrut(10);
		panel.add(verticalStrut_8);

		JButton btnExportDataAnalytics = new JButton("Export analytics");

		btnExportDataAnalytics.setFont(font);
		btnExportDataAnalytics.setContentAreaFilled(false);
		btnExportDataAnalytics.setOpaque(true);
		btnExportDataAnalytics.setBackground(SystemColor.textHighlight);
		btnExportDataAnalytics.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnExportDataAnalytics.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(btnExportDataAnalytics);

		Component verticalStrut_11 = Box.createVerticalStrut(10);
		panel.add(verticalStrut_11);

		Component horizontalStrut = Box.createHorizontalStrut(140);
		panel.add(horizontalStrut);

		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		panel_1.add(panel_2, BorderLayout.SOUTH);

		Map<String, Integer> bestContacts = Controlador.getInstance().getDataBestContacts();
		PieChart chartContacts = new PieChartBuilder().width(100).height(250).title("Biggest chats").build();
		Color[] slicesColors = new Color[] { new Color(224, 68, 14), new Color(230, 105, 62), new Color(236, 143, 110),
				new Color(243, 180, 159), new Color(246, 199, 182) };
		chartContacts.getStyler().setSeriesColors(slicesColors);
		chartContacts.getStyler().setChartBackgroundColor(Color.DARK_GRAY);
		chartContacts.getStyler().setBaseFont(font);
		chartContacts.getStyler().setChartTitleBoxVisible(true);
		chartContacts.getStyler().setLegendPosition(LegendPosition.InsideNE);
		chartContacts.getStyler().setChartTitleBoxBackgroundColor(Color.GRAY);
		totalAdded = 0;
		if (messagesSent + messagesReceived > 0)
			bestContacts.entrySet().stream().forEach(entry -> {
				totalAdded += entry.getValue();
				int percentage = (entry.getValue() * 100) / (messagesSent + messagesReceived);
				chartContacts.addSeries(entry.getKey(), percentage);
			});
		if (totalAdded < (messagesSent + messagesReceived))
			chartContacts.addSeries("other", (messagesSent + messagesReceived) - totalAdded);
		XChartPanel<PieChart> bestContactsPanel = new XChartPanel<PieChart>(chartContacts);
		XYChart chartUsage = new XYChartBuilder().width(100).height(150).title("App usage (last 30 days)")
				.xAxisTitle("Messages").yAxisTitle("Time").build();
		chartUsage.getStyler().setAxisTitlesVisible(false);
		chartUsage.getStyler().setChartBackgroundColor(Color.DARK_GRAY);
		chartUsage.getStyler().setBaseFont(font);
		chartUsage.getStyler().setChartTitleBoxVisible(true);
		chartUsage.getStyler().setChartTitleBoxBackgroundColor(Color.GRAY);
		chartUsage.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Area);
		chartUsage.getStyler().setLegendPosition(LegendPosition.InsideNE);
		chartUsage.addSeries("Messages sent",
				Arrays.stream(IntStream.range(1, 31).toArray()).asDoubleStream().toArray(),
				Arrays.stream(Controlador.getInstance().getDataMsgSentLast30Days()).asDoubleStream().toArray());
		chartUsage.addSeries("Messages received",
				Arrays.stream(IntStream.range(1, 31).toArray()).asDoubleStream().toArray(),
				Arrays.stream(Controlador.getInstance().getDataMsgReceivedLast30Days()).asDoubleStream().toArray());
		XChartPanel<XYChart> usagePanel = new XChartPanel<XYChart>(chartUsage);

		CategoryChart chartPerDay = new CategoryChartBuilder().width(100).height(250).title("Messages per time of day")
				.build();
		chartPerDay.getStyler().setAxisTitlesVisible(false);
		chartPerDay.getStyler().setChartBackgroundColor(Color.DARK_GRAY);
		chartPerDay.getStyler().setBaseFont(font);
		chartPerDay.getStyler().setPlotGridHorizontalLinesVisible(false);
		chartPerDay.getStyler().setPlotGridVerticalLinesVisible(false);
		chartPerDay.getStyler().setChartTitleBoxVisible(true);
		chartPerDay.getStyler().setChartTitleBoxBackgroundColor(Color.GRAY);
		chartPerDay.getStyler().setLegendVisible(false);
		chartPerDay.addSeries("messages", IntStream.range(1, 25).toArray(),
				Controlador.getInstance().getDataMsgPerDay());
		XChartPanel<CategoryChart> perDayPanel = new XChartPanel<CategoryChart>(chartPerDay);
		panel_2.add(perDayPanel);
		panel_2.add(bestContactsPanel);
		panel_1.add(usagePanel, BorderLayout.CENTER);

		btnExportDataAnalytics.addActionListener(e -> {
			try {
				new File("./Statistics/" + LocalDate.now().toString()).mkdirs();
				BitmapEncoder.saveJPGWithQuality(chartUsage,
						"./Statistics/" + LocalDate.now().toString() + "/usage.jpg", 0.95f);
				BitmapEncoder.saveJPGWithQuality(chartContacts,
						"./Statistics/" + LocalDate.now().toString() + "/contacts.jpg", 0.95f);
				BitmapEncoder.saveJPGWithQuality(chartPerDay,
						"./Statistics/" + LocalDate.now().toString() + "/perDay.jpg", 0.95f);
				JOptionPane.showMessageDialog(new JFrame(), "Graphs exported succesfully.", "Statistics",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		btnNewButton.addActionListener(e -> {
		});
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1000, 600));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
