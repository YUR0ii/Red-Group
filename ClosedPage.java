import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;



public class ClosedPage extends MainPage {

	ClosedPage(){

		JFrame closeFrame=new JFrame();
		JPanel panel = new JPanel();
		String [] header={"Completed Tasks"};
		String [][] data={{"<02/09/2033 satusday>"},{"<02/09/2033 satusday>"}};


		DefaultTableModel model = new DefaultTableModel(data, header);

		JTable table = new JTable(model);
		JScrollPane tableContainer = new JScrollPane(table);
		tableContainer.setViewportView(table);
		tableContainer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tableContainer.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		tableContainer.setBounds(0, 0, 500, 400);

		panel.add(tableContainer, BorderLayout.CENTER);
		panel.setPreferredSize(new Dimension(500, 400));
		closeFrame.setContentPane(panel);
		closeFrame.setLocation(550, 250);
		closeFrame.pack();
		closeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		closeFrame.setVisible(true);
		closeFrame.setLocationRelativeTo( null );

	}
}



