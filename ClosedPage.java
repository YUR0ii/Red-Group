import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class ClosedPage extends MainPage {
	private ArrayList<taskContainer> closedContainers=new ArrayList<taskContainer>();

		ClosedPage(){
			JFrame closeFrame=new JFrame();
			JTextArea textArea = new JTextArea(30, 30);
			textArea.setEditable(false);
			JPanel test = new JPanel();
			Task task = new Task("norman testing");
			task.setComplete(true);
			completeTasks.add(task);
			textArea.append("Completed Tasks\n");
	        for (int i = 0; i<completeTasks.size(); i++) {
	        	textArea.append(completeTasks.get(i).getName()+"\n");
	       }
	        for (int i = 0; i<incompleteTasks.size(); i++) {
	        	textArea.append(incompleteTasks.get(i).getName()+"\n");
	       }

	    	JScrollPane scroll = new JScrollPane(textArea);

	        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	        scroll.setBounds(0, 0, 400, 400);

	        textArea.setBackground(new Color(247,232,210));
	        closeFrame.setContentPane(scroll);
	        closeFrame.setLocation(550, 250);
	        closeFrame.pack();
	        closeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        closeFrame.setVisible(true);
	        closeFrame.setResizable(false);

	}
}


