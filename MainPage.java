import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class MainPage extends JPanel implements ActionListener{
	private JFrame mainFrame=new JFrame();
	private JScrollPane scroll=new JScrollPane();
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
		mainFrame.setJMenuBar(menuBar);
		mainFrame.add(scroll);
		scroll.add(this);
		menuBar.add(file);
		menuBar.add(closed);
		menuBar.add(quit);	
		file.add(fileMenu);
		fileMenu.add(save);
		fileMenu.add(restore);
		fileMenu.add(print);
		file.addMouseListener(new menuListener());
		closed.addMouseListener(new menuListener());
		quit.addMouseListener(new menuListener());
		save.addMouseListener(new fileListener());
		restore.addMouseListener(new fileListener());
		print.addMouseListener(new fileListener());
		this.add(input);
		mainFrame.setLocation(400,400);
		mainFrame.setSize(200,200);
		mainFrame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		
		
	}
	public void paintComponent(Graphics g) {
		
		
	}
	class taskContainer{
		private JLabel container;
		private String date;
		taskContainer(Task task){
			container=new JLabel(task.getName());
			if(true) {//if the task is inactive, get a date
				
			}
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
				
			}else if(e.getComponent().equals(file)) {
				fileMenu.show(e.getComponent(),e.getX(),e.getY());
			}
		}
	}
}
