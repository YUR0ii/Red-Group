import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class MainPage extends JPanel implements ActionListener {
	private JFrame mainFrame = new JFrame();
	private JPanel scrollPanel = new JPanel();
	private JScrollPane scroll = new JScrollPane(scrollPanel);
	private JScrollBar scrollBar = new JScrollBar();
	private JMenuBar menuBar = new JMenuBar();
	private JMenu file = new JMenu("File");
	private JPopupMenu fileMenu = new JPopupMenu();
	private JMenuItem save = new JMenuItem("Save a backup");
	private JMenuItem restore = new JMenuItem("Restore a backup");
	private JMenuItem print = new JMenuItem("Print");
	private JMenuItem closed = new JMenuItem("Closed");
	private JMenuItem quit = new JMenuItem("Quit");
	private JTextField input = new JTextField();
	private ArrayList<Task> incompleteTasks = new ArrayList<Task>();// these 2 lists are used to access the containers
	private ArrayList<Task> completeTasks = new ArrayList<Task>();
	protected ArrayList<taskContainer> incompleteContainers = new ArrayList<taskContainer>();
	protected ArrayList<taskContainer> completeContainers = new ArrayList<taskContainer>();
	private ArrayList<String> dateStrings=new ArrayList<String>();
	
	MainPage() {
		file.addMouseListener(new menuListener());
		closed.addMouseListener(new menuListener());
		quit.addMouseListener(new menuListener());
		save.addMouseListener(new fileListener());
		restore.addMouseListener(new fileListener());
		print.addMouseListener(new fileListener());
		
		
		//createGUI();
	}
	//creates GUI components for main page
	public void createGUI() {
		mainFrame.setJMenuBar(menuBar);
		mainFrame.setContentPane(this);
		mainFrame.setBackground(new Color(247, 232, 210));
		menuBar.add(file);
		menuBar.add(closed);
		menuBar.add(quit);
		file.add(fileMenu);
		file.setPreferredSize(new Dimension(200, 20));
		fileMenu.add(save);
		fileMenu.add(restore);
		fileMenu.add(print);
		this.add(scroll);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setPreferredSize(new Dimension(600, 360));
		scroll.add(scrollBar);
		input.addActionListener(this);
		input.setEditable(true);
		input.setText("New Task");
		input.setVisible(true);
		input.setActionCommand("Add a Task");
		input.setPreferredSize(new Dimension(590, 25));
		scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.PAGE_AXIS));
		scrollPanel.setBackground(new Color(247, 232, 210));
		scrollPanel.setBorder(BorderFactory.createEmptyBorder(5, 50, 5, 50));
		this.setPreferredSize(new Dimension(600, 400));
		this.add(input);
		mainFrame.setLocation(250, 100);
		mainFrame.pack();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}
	//adds functionality to text field for adding tasks
	public void actionPerformed(ActionEvent e) {
		String eventName = e.getActionCommand();
		if (eventName.equals("Add a Task")) {
			taskContainer temp = new taskContainer(new Task(input.getText()));
			incompleteContainers.add(temp);
			int index = incompleteContainers.indexOf(temp);			
			incompleteTasks.add(temp.getTask());
//I think we need to add the dates separate from the taskContainer
			boolean check=true;
			for(String d: dateStrings) {
				if((d.equals(incompleteContainers.get(index).dateString))) {
					check=false;
				}
			}
			if(check) {
				dateStrings.add(incompleteContainers.get(index).dateString);
				scrollPanel.add(new JLabel(incompleteContainers.get(index).dateString));
			}
			scrollPanel.add(incompleteContainers.get(index));
			scrollPanel.validate();
			scrollPanel.repaint();
		}
	}
	
	public void paintComponent(Graphics g) {
	}
	
	public void updatePage(Task editedTask) {
		int index=incompleteTasks.indexOf(editedTask);
		incompleteContainers.get(index).update();
		//need to check list for changed boolean?
		scrollPanel.repaint();
		//check to see if the date needs to be removed
	}
	
	public class taskContainer extends JComponent implements Comparable
	{
		private Task task;
		private JLabel name;
		private Date date;
		private String dateString;
		private contextMenu menu;
		private Font urgent=new Font(Font.SERIF,Font.BOLD,14);
		private Font current=new Font(Font.SERIF,Font.PLAIN,14);
		private Font eventual=new Font(Font.SERIF,Font.ITALIC,14);
		private Font inactive=new Font(Font.SERIF,Font.ITALIC,14);
		taskContainer(Task task)
		{
			this.task = task;
			name=new JLabel(task.getName());
			date=Calendar.getInstance().getTime();
			dateString=getDateString();
			this.setLayout(new FlowLayout());
			this.add(name);
			name.setFont(inactive);
			this.addMouseListener(new MouseAdapter()
			{
				//creates menu when right clicking on a task
				@Override
				public void mouseClicked(MouseEvent e)
				{
					if(e.getButton() == e.BUTTON3)
					{
						menu=new contextMenu();
						menu.show(e.getComponent(), e.getX(), e.getY());
					}else if(e.getClickCount()==2){
						task.edit();
					}
				};
			});
			this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		}
		
		public Task getTask() {
			return task;
		}
		
		public void setTask(Task nTask) {
			task=nTask;
		}
		
		
		public Date getDate() {
			return date;
		}
		public String getDateString() {
			return dateString;
		}
		public void update() {
			//changes fonts depending on the priority
			if(task.getPriorityLevel().equals("urgent")){
				name.setFont(urgent);
			}else if(task.getPriorityLevel().equals("current")){
				name.setFont(current);
			}else if(task.getPriorityLevel().equals("eventual")){
				name.setFont(eventual);
			}else {
				name.setFont(inactive);
			}
		}
		class contextMenu extends JPopupMenu
		{	
			contextMenu()
			{
				//creates an action to complete a task
				//Action complete = new AbstractAction()
				JMenuItem complete = new JMenuItem("Mark as Complete");
				complete.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						//completes a task
						task.setComplete(true);
						completeTasks.add(task);
						int index=incompleteTasks.indexOf(task);
						incompleteTasks.remove(index);
						scrollPanel.remove(incompleteContainers.get(index));
						completeContainers.add(incompleteContainers.get(index));
						incompleteContainers.remove(index);
						scrollPanel.validate();
						scrollPanel.repaint();
					}
				});
				//creates an action to edit a task
				JMenuItem edit = new JMenuItem("Edit");
				edit.addActionListener(new ActionListener()
						{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						new EditAction(task).createAndShowGUI();
					}
						});
				//creates an action to delete a task
				JMenuItem delete = new JMenuItem("Delete");
				delete.addActionListener(new ActionListener()
						{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						//creates an "Are you sure?" popup
						JPopupMenu confirm = new JPopupMenu();
						
						JLabel text = new JLabel("Are you sure you want to delete " + task.getName() + "?");
						JButton del = new JButton("Delete");
						JButton cancel = new JButton("Cancel");
						
						confirm.setLayout(new FlowLayout());
						confirm.add(text);
						confirm.add(del);
						confirm.add(cancel);

						//adds deleting function to delete button
						del.addActionListener(new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent e)
							{
								task.delete();
								int index=incompleteTasks.indexOf(task);
								incompleteTasks.remove(task);
								scrollPanel.remove(incompleteContainers.get(index));
								incompleteContainers.remove(index);
								scrollPanel.validate();
								scrollPanel.repaint();
								confirm.setVisible(false);
							}
							
						});
						//adds cancelling function to cancel button
						cancel.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e)
							{
								confirm.setVisible(false);
							}
							
						});
						confirm.setVisible(true);
						confirm.setLocation(mainFrame.getX()+mainFrame.getWidth()/2-confirm.getWidth()/2,
											mainFrame.getY()+mainFrame.getHeight()/2-confirm.getHeight()/2);
					}
						});
				
				//adds actions to menu
				this.add(complete);
				this.add(delete);
				this.add(edit);
				
			}
		}
		

		@Override
		public int compareTo(Object o)
		{
			return 0;
		}
		
		public int compareTo(taskContainer t)
		{
			if(task.getPriorityLevel().equals(t.task.getPriorityLevel()))
			{
				return task.getName().compareToIgnoreCase(t.task.getName());
			}
			else
			{
				//			return Integer.compare(task.getPriorityLevel(), t.task.getPriorityLevel());
			}
		}
	}
	
	public static void main(String[] args) {
		MainPage page = new MainPage();
		page.createGUI();
	}
	//deals with functions in file menu
	private class fileListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (e.getComponent().equals(save)) {

			} else if (e.getComponent().equals(restore)) {

			} else if (e.getComponent().equals(print)) {

			}
			fileMenu.setVisible(false);
		}
	}
	//deals with functions in menu bar
	private class menuListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (e.getComponent().equals(quit)) {
				mainFrame.dispose();
			} else if (e.getComponent().equals(closed)) {
				ClosedPage complete = new ClosedPage();
			} else if (e.getComponent().equals(file)) {
				fileMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}
	
}
