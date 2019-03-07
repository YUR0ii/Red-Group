import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class MainPage extends JPanel implements ActionListener{
	private JFrame mainFrame=new JFrame();
	private JPanel scrollPanel=new JPanel();
	private JScrollPane scroll=new JScrollPane(scrollPanel);
	private JMenuBar menuBar=new JMenuBar();
	private JMenu file=new JMenu("File"); 
	private JPopupMenu fileMenu=new JPopupMenu();
	private JMenuItem save=new JMenuItem("Save a backup");
	private JMenuItem restore=new JMenuItem("Restore a backup");
	private JMenuItem print=new JMenuItem("Print");
	private JMenuItem closed=new JMenuItem("Closed");
	private JMenuItem quit=new JMenuItem("Quit");
	private JTextField input=new JTextField();
	private ArrayList<Task> incompleteTasks=new ArrayList<Task>();
	private ArrayList<Task> completeTasks=new ArrayList<Task>();
	private ArrayList<taskContainer> containers=new ArrayList<taskContainer>();
	MainPage(){		
		file.addMouseListener(new menuListener());
		closed.addMouseListener(new menuListener());
		quit.addMouseListener(new menuListener());
		save.addMouseListener(new fileListener());
		restore.addMouseListener(new fileListener());
		print.addMouseListener(new fileListener());
		createGUI();
	}
	public void createGUI() {
		mainFrame.setJMenuBar(menuBar);
		mainFrame.setContentPane(this);
		mainFrame.setBackground(new Color(247,232,210));
		menuBar.add(file);
		menuBar.add(closed);
		menuBar.add(quit);	
		file.add(fileMenu);
		file.setPreferredSize(new Dimension(200,20));
		fileMenu.add(save);
		fileMenu.add(restore);
		fileMenu.add(print);
		this.add(scroll);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		scroll.setPreferredSize(new Dimension(600,360));
		input.addActionListener(this);
		input.setEditable(true);
		input.setText("New Task");
		input.setVisible(true);
		input.setActionCommand("Add a Task");
		input.setPreferredSize(new Dimension(590,25));
		scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.PAGE_AXIS));
		scrollPanel.setBackground(new Color(247,232,210));
		scrollPanel.setBorder(BorderFactory.createEmptyBorder(5,50,5,50));
		this.setPreferredSize(new Dimension(600,400));
		this.add(input); 
		mainFrame.setLocation(250,100);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		String eventName=e.getActionCommand();
		if(eventName.equals("Add a Task")) {
			Task temp=new Task(input.getText());
			incompleteTasks.add(temp);
			containers.add(new taskContainer(temp));
			int index=incompleteTasks.indexOf(temp);
			scrollPanel.add(containers.get(index).getLabel());
			//repaint();
		}
	}
	public void paintComponent(Graphics g) {
		
		
	}
	public class taskContainer{
		private JLabel container;
		private String date;
		taskContainer(Task task){
			container=new JLabel(task.getName());
			System.out.println(task.getName());
		}
		public JLabel getLabel() {
			return container;
		}
	}
	public static void main(String[] args) {
		MainPage page=new MainPage();
	}
	class fileListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if(e.getComponent().equals(save)) {
				 
			}else if(e.getComponent().equals(restore)) {
				
			}else if(e.getComponent().equals(print)) {
				
			}
			fileMenu.setVisible(false);
		}
	}
	class menuListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			if(e.getComponent().equals(quit)) {
				 mainFrame.dispose();
			}else if(e.getComponent().equals(closed)) {
				ClosedPage complete=new ClosedPage();
			}else if(e.getComponent().equals(file)) {
				fileMenu.show(e.getComponent(),e.getX(),e.getY());
			}
		}
	}
}
