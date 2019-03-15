 import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CommentPage extends JFrame {
	Task task;
	private String currentText;

	private JSplitPane sp;
	private JPanel topPanel;
	private JPanel bottomPanel;
	private JScrollPane scroll;
	private JTextArea textArea;
	private JPanel inputPanel;
	private JButton commit;
	private JButton delete;
	private JLabel title;
	private Event recentEvent;
	private String ogComment;
	
	CommentPage(Task t){
		task = t;
		ogComment = task.getComment();
		currentText = task.getComment();
		setLocation(500,300);
		sp = new JSplitPane();
		topPanel = new JPanel();         
		bottomPanel = new JPanel();      
		textArea = new JTextArea(); 
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setText(currentText);
		scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		title = new JLabel("Edit Comment");
		topPanel.add(title);
		inputPanel = new JPanel();
		commit = new JButton("Commit");
		commit.setPreferredSize(new Dimension(199,25));
		delete = new JButton("Delete");
		delete.setPreferredSize(new Dimension(199,25));
		setPreferredSize(new Dimension(400, 400)); 
		getContentPane().add(sp); 
		sp.setOrientation(JSplitPane.VERTICAL_SPLIT);
		sp.setTopComponent(topPanel);
		sp.setBottomComponent(bottomPanel);
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
		bottomPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		bottomPanel.add(scroll);
		bottomPanel.add(inputPanel);
		inputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
		inputPanel.add(commit);
		inputPanel.add(delete);

		pack();
		initiateComponents();
		setVisible(true);

	}
	
	public String getText() {
		
		return currentText;
		
	}
	
	private void initiateComponents() {

		textArea.addKeyListener(new KeyListener(){

			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}

			public void keyTyped(KeyEvent arg0) {

				currentText = textArea.getText();

			}
		});

		commit.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  		
				
				currentText = textArea.getText();
				recentEvent = new commentEvent(ogComment, currentText);
				task.setComment(currentText);
				dispose();
				
			}  
		});

		delete.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  		

				recentEvent = new commentEvent(ogComment, "");
				currentText = "";
				dispose();
				
			}  
		});

	}
	
	public Event getRecentEvent() {
		return recentEvent;
	}
	
}
