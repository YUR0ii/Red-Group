import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class historyPage{
	private ArrayList<Event> events = new ArrayList<Event>(); 
	private ArrayList<JLabel> labels = new ArrayList<JLabel>();
	private JScrollPane scroll;
	private JFrame frame;
	

	public historyPage(Task task) {
		events = new task.getEvents();
	}

	public Event getEvent(int index) {
		return events[index];
	}
	
	public void add(Event event, JLabel j){
		events.add(event);
		j.setText(event.createSentence());
		labels.add(j);
		
	}
	public void openHistoryPage() {
		frame = new JFrame("History Page");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.setPreferredSize(new Dimension(1600,860));
		
		scroll = new JScrollPane();
		
	}
}





