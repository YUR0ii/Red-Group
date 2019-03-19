import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HistoryPage implements MouseListener{
	private ArrayList<Event> events = new ArrayList<Event>(); 
	private ArrayList<JLabel> labels = new ArrayList<JLabel>();
	private JScrollPane scroll = new JScrollPane();
	private JScrollBar scrollBar = new JScrollBar();
	private JFrame frame;
	private JPanel contentPane;
	private JPanel panel;
	public HistoryPage(Task task) {
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
	public void add(Event event, JLabel j){
		j.setText(event.createSentence());
		labels.add(j);
		if(event.getType() == "Comment"){
			j.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if(e.getClickCount()==2){
						openComment((commentEvent)event);
						j.setText(event.getNewComment());
						
					}

				}
			});
		}

	}
	//creates gui
	public void openHistoryPage() {
		frame = new JFrame("History Page");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	

		scroll = new JScrollPane(contentPane);
//		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
//		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
//		scroll.setBounds(0, 0, 500, 400);
		scroll.add(scrollBar);
		scroll.validate();
		contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
//		contentPane.setBorder(BorderFactory.createEmptyBorder(5, 50, 5, 50));
		contentPane.setPreferredSize(new Dimension(500, 400));
		contentPane.add(scroll);
		frame.setContentPane(contentPane);

		for(JLabel l : labels) {
//			contentPane.add(l);
			scroll.add(l);
		}
		
		frame.pack();
		frame.setVisible(true);

	}
	// opens the comment page
	public void openComment(commentEvent event){
		event.editComment();
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
}




