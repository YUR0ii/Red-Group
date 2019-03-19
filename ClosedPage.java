import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class ClosedPage extends MainPage {

		ClosedPage(){
			JFrame closeFrame=new JFrame();
			JTextArea textArea = new JTextArea(30, 30);
			textArea.setEditable(false);

			if(completeContainers.size() > 0){
				textArea.append("Completed Tasks\n");
		        for (int i = 0; i<completeContainers.size(); i++) {
		        	textArea.append("<"+completeContainers.get(i).getTask().getName()+">\n");
		       }
			}
			if(incompleteContainers.size() > 0){
				ArrayList<taskContainer> inactive = new ArrayList<taskContainer>();
				ArrayList<taskContainer> current = new ArrayList<taskContainer>();
				ArrayList<taskContainer> eventual = new ArrayList<taskContainer>();
				ArrayList<taskContainer> urgent = new ArrayList<taskContainer>();

				for (int i = 0; i<incompleteContainers.size(); i++) {
					if("inactive".equals(incompleteContainers.get(i).getTask().getPriorityLevel() == "inactive"))
						inactive.add(incompleteContainers.get(i));
					else if("current".equals(incompleteContainers.get(i).getTask().getPriorityLevel()))
						current.add(incompleteContainers.get(i));
					else if("eventual".equalsIgnoreCase(incompleteContainers.get(i).getTask().getPriorityLevel()))
						eventual.add(incompleteContainers.get(i));
					else if( "urgent".equalsIgnoreCase(incompleteContainers.get(i).getTask().getPriorityLevel()))
						urgent.add(incompleteContainers.get(i));

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