import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class HistoryPage implements MouseListener{
	private ArrayList<Event> events = new ArrayList<Event>(); 
	private ArrayList<JLabel> labels = new ArrayList<JLabel>();
	private JScrollBar scrollBar = new JScrollBar();
	private JFrame frame= new JFrame("History Page");
	private JPanel contentPane = new JPanel();	
	private JScrollPane scroll = new JScrollPane(contentPane);
	private JPanel panel= new JPanel();
	private HistoryPage thisHistory;
	
	
	public HistoryPage(Task task) {
		thisHistory = this;
		events =task.getEvents();
		for(int i = 0; i<events.size();i++) {
			add(getEvent(i),new JLabel());
		}
		openHistoryPage();

	}
	//returns event at specific index
	public Event getEvent(int index) {
		return events.get(index);
	}
	//adds labels and adds listener to comment events
	public void add(final Event event,final JLabel j){
		j.setText(event.createSentence());
		labels.add(j);
		if(event.getType() == "Comment"){
			j.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if(e.getClickCount()==2){
						openComment((commentEvent)event);
						
						
					}

				}
			});
		}

	}
	//creates gui
	public void openHistoryPage() {

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	

		scroll = new JScrollPane(contentPane);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		scroll.setBounds(0, 0, 500, 400);
		scroll.add(scrollBar);
		scroll.validate();
	
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
//		contentPane.setBorder(BorderFactory.createEmptyBorder(5, 50, 5, 50));
		contentPane.setPreferredSize(new Dimension(500, 400));
	

		for(JLabel l : labels) {
			contentPane.add(l);

		}
		
		panel.add(scroll);
		frame.setContentPane(panel);
		
		scroll.revalidate();
		contentPane.repaint();
		
		frame.pack();
		frame.setVisible(true);

	}
	// opens the comment page
	public void openComment(commentEvent event){
		event.editComment(thisHistory);
	}
	
	public void ChangeLabel(Date eventDate, String newComment) {
		for(Event e : events) {
			if(e.getDate().equals(eventDate)) {
				e.setComment(newComment);
				labels.get(events.indexOf(e)).setText(e.createSentence());;
//				labels.get(events.indexOf(e)).setText(newComment);
			}
		}
	}
	

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub


	}
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	
	public static void main(String[] args) {
		
		Task task = new Task("Test Task");
		
		Event event01 = new commentEvent("oldString", "newString");
		Event event02 = new priorityEvent(new scheduledPriority("Eventual"), new scheduledPriority("Urgent"));
		task.addEventToHistory(event01);
		task.addEventToHistory(event02);
		
		HistoryPage history = new HistoryPage(task);
		
    }
	
}










