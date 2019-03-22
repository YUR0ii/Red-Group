
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class ClosedPage extends MainPage {

		ClosedPage(ArrayList<taskContainer> cl, ArrayList<taskContainer> inCl ){
			JFrame closeFrame=new JFrame();
			JTextArea textArea = new JTextArea(30, 30);
			textArea.setEditable(false);

			if(cl.size() > 0){
				textArea.append("Completed Tasks\n");
		        for (int i = 0; i<cl.size(); i++) {
		        	textArea.append("<"+cl.get(i).getTask().getName()+">\n");
		       }
			}
			if(inCl.size() > 0){
				ArrayList<taskContainer> inactive = new ArrayList<taskContainer>();
				ArrayList<taskContainer> current = new ArrayList<taskContainer>();
				ArrayList<taskContainer> eventual = new ArrayList<taskContainer>();
				ArrayList<taskContainer> urgent = new ArrayList<taskContainer>();

				for (int i = 0; i<inCl.size(); i++) {
					if("inactive".equals(inCl.get(i).getTask().getPriorityLevel() == "inactive"))
						inactive.add(inCl.get(i));
					else if("current".equals(inCl.get(i).getTask().getPriorityLevel()))
						current.add(inCl.get(i));
					else if("eventual".equalsIgnoreCase(inCl.get(i).getTask().getPriorityLevel()))
						eventual.add(inCl.get(i));
					else if( "urgent".equalsIgnoreCase(inCl.get(i).getTask().getPriorityLevel()))
						urgent.add(inCl.get(i));

				}
				if(current.size() > 0){
					textArea.append("A Current Task\n");
			        for (int i = 0; i<current.size(); i++) {
			        	textArea.append("<"+current.get(i).getTask().getName()+">\n");
			       }
				}
				if(urgent.size() > 0){
					textArea.append("An Urgent Task\n");
			        for (int i = 0; i<urgent.size(); i++) {
			        	textArea.append("<"+urgent.get(i).getTask().getName()+">\n");
			       }
				}
				if(eventual.size() > 0){
					textArea.append("An Eventual task\n");
			        for (int i = 0; i<eventual.size(); i++) {
			        	textArea.append("<"+eventual.get(i).getTask().getName()+">\n");
			       }
				}
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








