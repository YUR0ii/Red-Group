import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class HistoryPage implements MouseListener{
	private ArrayList<Event> events = new ArrayList<Event>(); 
	private ArrayList<sentenceContainer> allLabels=new ArrayList<sentenceContainer>();
	private JFrame frame= new JFrame("History Page");
	private JPanel contentPane = new JPanel();	
	private JScrollPane scroll = new JScrollPane(contentPane);
	private JPanel panel= new JPanel();
	private HistoryPage thisHistory;
	
	
	public HistoryPage(Task task) {
		allLabels.clear();
		thisHistory = this;
		events =task.getEvents();
		for(int i = events.size()-1; i>-1;i--) {
			add(getEvent(i));
		}
		openHistoryPage();

	}
	//returns event at specific index
	public Event getEvent(int index) {
		return events.get(index);
	}
	//adds labels and adds listener to comment events
	public void add(final Event event){
		ArrayList<String> set=event.createSentence();
		sentenceContainer container=new sentenceContainer(set,event);		
		
		allLabels.add(container);
	}
	//creates gui
	public void openHistoryPage() {

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("History");

		scroll = new JScrollPane(contentPane);
		scroll.setPreferredSize(new Dimension(500, 400));
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
		contentPane.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));	
		contentPane.setBackground(new Color(247, 232, 210));
		contentPane.validate();
		

		for(sentenceContainer a : allLabels) {
			contentPane.add(a);
		}
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(scroll);
		frame.setContentPane(panel);
		panel.setBackground(new Color(247, 232, 210));
		
		scroll.validate();
		contentPane.repaint();
		
		frame.pack();
		frame.setVisible(true);

	}
	// opens the comment page
	public void openComment(Event event){
		event.editComment(thisHistory);
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
	
	public class sentenceContainer extends JComponent{
		private ArrayList<String> sentence;
		private ArrayList<JLabel> labelset=new ArrayList<JLabel>();
		sentenceContainer(ArrayList<String> sen, Event ev){
			sentence=sen;
			for(String s: sentence) {
				labelset.add(new JLabel(s));
			}
			if(ev.getType() == "Comment"){
				for(int i=0;i<labelset.size();i++) {
					if(ev.getStringType(i)==1) {
						labelset.get(i).setForeground(new Color(127, 126, 123));
						labelset.get(i).setFont(new Font(Font.SERIF,Font.ITALIC,14));
					}else {
						labelset.get(i).setFont(new Font(Font.SERIF,Font.PLAIN,14));
					}
				}
				this.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if(e.getClickCount()==2){
							openComment(ev);
//							System.out.println("this works");
						}

					}
				});
			}else {
				for(int i=0;i<labelset.size();i++) {
					if(ev.getStringType(i)==1) {
						if(sentence.get(i).equals("urgent")) {
							labelset.get(i).setForeground(Color.red);
							labelset.get(i).setFont(new Font(Font.SERIF,Font.BOLD,14));
						}else if(sentence.get(i).equals("current")) {
							labelset.get(i).setForeground(new Color(191, 121, 1));
							labelset.get(i).setFont(new Font(Font.SERIF,Font.PLAIN,14));
						}else if(sentence.get(i).equals("eventual")) {
							labelset.get(i).setForeground(Color.blue);
							labelset.get(i).setFont(new Font(Font.SERIF,Font.ITALIC,14));
						}else{
							labelset.get(i).setForeground(new Color(127, 126, 123));
							labelset.get(i).setFont(new Font(Font.SERIF,Font.ITALIC,14));
						}
					}else if(ev.getStringType(i)==2) {
						labelset.get(i).setForeground(new Color(127, 126, 123));
						labelset.get(i).setFont(new Font(Font.SERIF,Font.PLAIN,14));
					}else {
						labelset.get(i).setFont(new Font(Font.SERIF,Font.PLAIN,14));
					}
				}
			}
			this.setLayout(new FlowLayout());
			for(JLabel l: labelset) {
				this.add(l);
			}
		}
	}
	
	public static void main(String[] args) {
		
		Task task = new Task("Test Task");
		
		Event event01 = new commentEvent("oldString", "newString");
		Event event02 = new priorityEvent(new scheduledPriority("eventual"), new scheduledPriority("urgent"));
		task.addEventToHistory(event01);
		task.addEventToHistory(event02);
		task.addEventToHistory(event01);
		task.addEventToHistory(event02);
		task.addEventToHistory(event01);
		task.addEventToHistory(event02);
		task.addEventToHistory(event01);
		task.addEventToHistory(event02);
		task.addEventToHistory(event01);
		task.addEventToHistory(event02);
		task.addEventToHistory(event01);
		task.addEventToHistory(event02);
		task.addEventToHistory(event01);
		task.addEventToHistory(event02);
		task.addEventToHistory(event01);
		task.addEventToHistory(event02);
		task.addEventToHistory(event01);
		task.addEventToHistory(event02);
		task.addEventToHistory(event01);
		task.addEventToHistory(event02);
		task.addEventToHistory(event01);
		task.addEventToHistory(event02);
		task.addEventToHistory(event01);
		task.addEventToHistory(event02);
		task.addEventToHistory(event01);
		task.addEventToHistory(event02);
		task.addEventToHistory(event01);
		task.addEventToHistory(event02);
		task.addEventToHistory(event01);
		task.addEventToHistory(event02);
		task.addEventToHistory(event01);
		task.addEventToHistory(event02);
		task.addEventToHistory(event01);
		task.addEventToHistory(event02);
		task.addEventToHistory(event01);
		task.addEventToHistory(event02);
		HistoryPage history = new HistoryPage(task);
		
    }	
}