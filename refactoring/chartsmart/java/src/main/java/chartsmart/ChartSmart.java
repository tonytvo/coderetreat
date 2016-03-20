package chartsmart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class ChartSmart extends JPanel {

	
	private JButton btnShowBarChart;
	private JButton btnShowPieOrBarChart;
	private JButton btnShowSingleOrComparechart;
	private JComboBox cboChartType;
	private JComboBox cboDisplayType;
	
	public ChartSmart() {
		initializeLayout();
	}

	private void showGui() {		
		JFrame frame = new JFrame("ChartSmart, the Smart way to do Charts.");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(this);
		Insets insets = frame.getInsets();
		
		frame.setSize(500 + insets.left + insets.right, 
				500 + insets.top + insets.bottom);
		frame.setLocation(100, 100);
		frame.setVisible(true);		
	}

	private void initializeLayout() {
		this.setPreferredSize(new Dimension(500, 500));
		this.setLayout(null);
		
		btnShowBarChart = new JButton("Show Chart");
		btnShowPieOrBarChart = new JButton("Show Pie or Bar");
		btnShowSingleOrComparechart = new JButton("Show Display Type");
		
		add(btnShowBarChart);
		add(btnShowPieOrBarChart);
		add(btnShowSingleOrComparechart);
		
		String[] chartTypes = {"Bar Chart", "Pie Chart"};
		cboChartType = new JComboBox(chartTypes);
		String[] displayTypes = {"Single", "Compare"};
		cboDisplayType = new JComboBox(displayTypes);
		
		add(cboChartType);
		add(cboDisplayType);
		
		ButtonListener buttonListener = new ButtonListener();
		btnShowBarChart.addActionListener(buttonListener);
		btnShowPieOrBarChart.addActionListener(buttonListener);
		btnShowSingleOrComparechart.addActionListener(buttonListener);
		
		Insets insets = getInsets();
		
		Dimension size = btnShowBarChart.getPreferredSize();
		btnShowBarChart.setBounds(25 + insets.left, 25 + insets.top, size.width, size.height);
		
		size = btnShowPieOrBarChart.getPreferredSize();
		btnShowPieOrBarChart.setBounds(25 + insets.left, 65 + insets.top, size.width, size.height);
		cboChartType.setBounds(200, 65 + insets.top, 200, size.height);
		
		size = btnShowSingleOrComparechart.getPreferredSize();
		btnShowSingleOrComparechart.setBounds(25 + insets.left, 105 + insets.top, size.width, size.height);
		cboDisplayType.setBounds(200, 105 + insets.top, 200, size.height);
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				ChartSmart chartSmart = new ChartSmart();
				chartSmart.showGui();
			}
		
		});	
	}
	

	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
						
			if (e.getSource() == btnShowBarChart) {
				openBarChart();
			} else if (e.getSource() == btnShowPieOrBarChart) {
				ChartWindow cw = new ChartWindow();
				cw.iniDS(getChartType(), "rpfll", true);
				display(cw);
			} else {
				ChartWindow cw = new ChartWindow();
				cw.iniDS(getChartType(), getDisplayType(), true);
				display(cw);
			}			
		}

		private void openBarChart() {
			ChartWindow cw = new ChartWindow();
			cw.iniDS(406, "rpfll", true);
			display(cw);
		}

		private void display(ChartWindow cw) {
			JFrame frame = new JFrame();
			frame.getContentPane().add(cw);
			frame.setLocation(150, 150);
			frame.setBackground(new Color(255, 239, 213));
			frame.pack();
			frame.setTitle(cw.getTitle());		
			frame.setVisible(true);
		}

		private String getDisplayType() {
			if ((String)cboDisplayType.getSelectedItem() == "Single") {
				return "rpfll";
			} else {
				return "shareddisplay";
			}
		}

		private int getChartType() {
			if ((String)cboChartType.getSelectedItem() == "Bar Chart") {
				return 406;
			} else {
				return 323;
			}				
		}

	}

	
}
