import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class MainPage extends JPanel implements ActionListener
{
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
	private ArrayList<Task> urgentTasks = new ArrayList<Task>();
	private ArrayList<Task> currentTasks = new ArrayList<Task>();
	private ArrayList<Task> eventualTasks = new ArrayList<Task>();
	private ArrayList<Task> inactiveTasks = new ArrayList<Task>();


	MainPage()
	{
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
		scroll.validate();
		input.addActionListener(this);
		input.setEditable(true);
		input.setText("New Action Item");
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
	public void actionPerformed(ActionEvent e)
	{
		String eventName = e.getActionCommand();
		if (eventName.equals("Add a Task")) {
			taskContainer temp = new taskContainer(new Task(input.getText()));
			incompleteContainers.add(temp);		
			incompleteTasks.add(temp.getTask());
			scrollPanel.add(temp);
			updateGUI();
		}
	}

	public void paintComponent(Graphics g) {
	}

	public void updatePage(Task editedTask) {
		int index=incompleteTasks.indexOf(editedTask);
		taskContainer temp=incompleteContainers.get(index);
		temp.update();
		if((checkRemoveDate(temp.getDateString()))) {
			int i=dateStrings.indexOf(temp.getDateString());
			dateStrings.remove(i);		
		}
		if((checkAddDate(temp.getDateString()))) {
			dateStrings.add(temp.getDateString());
		}
		//need a method for reordering the page properly
		updateGUI();
	}

	public boolean checkRemoveDate(String date) {
		for(taskContainer t : incompleteContainers) {
			if(t.getDateString().equals(date)) {
				if((t.getTask().getPriorityLevel().equals("inactive"))) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean checkAddDate(String date) {
		for(taskContainer t : incompleteContainers) {
			if(!(t.getDateString().equals(date))) {
				if((t.getTask().getPriorityLevel().equals("inactive"))) {
					return true;
				}
			}
		}
		return false;
	}

	public String checkAddDate(taskContainer t) {
		for(String s : dateStrings) {
			if((t.getDateString().equals(s))) {
				return s;
			}
		}
		return null;
	}

	public void updateGUI() {
		scrollPanel.removeAll();
		incompleteContainers.clear();
		urgentTasks.clear();
		currentTasks.clear();
		eventualTasks.clear();
		inactiveTasks.clear();
		for(Task t: incompleteTasks) {
			if(t.getPriorityLevel().equals("urgent")) {
				urgentTasks.add(t);
			}else if(t.getPriorityLevel().equals("current")) {
				currentTasks.add(t);
			}else if(t.getPriorityLevel().equals("eventual")) {
				eventualTasks.add(t);
			}else {
				inactiveTasks.add(t);
			}
		}
		for(Task t: urgentTasks) {
			taskContainer temp=new taskContainer(t);
			incompleteContainers.add(temp);
			scrollPanel.add(temp);
		}
		for(Task t: currentTasks) {
			taskContainer temp=new taskContainer(t);
			incompleteContainers.add(temp);
			scrollPanel.add(temp);
		}
		for(Task t: eventualTasks) {
			taskContainer temp=new taskContainer(t);
			incompleteContainers.add(temp);
			scrollPanel.add(temp);
		}
		for(Task t: inactiveTasks) {
			taskContainer temp=new taskContainer(t);
			incompleteContainers.add(temp);
			if(!(checkAddDate(temp)==null)) {
				scrollPanel.add(new JLabel(checkAddDate(temp)));
			}
			scrollPanel.add(temp);
		}
		scrollPanel.validate();
		scroll.validate();
		scrollPanel.repaint();
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
		boolean dragging = false;

		int getIndex()
		{
			return incompleteContainers.indexOf(this);
		}
		Point clickOffset = new Point(0,0);

		public String getName()
		{
			return task.getName();
		}

		taskContainer(Task task)
		{
			this.task = task;
			name=new JLabel(task.getName());
			date=Calendar.getInstance().getTime();
			dateString=task.getDateString();
			this.setLayout(new FlowLayout());
			this.add(name);
			name.setFont(urgent);

			addMouseListener(new MouseAdapter()
			{
				//creates menu when right clicking on a task
				@Override
				public void mouseClicked(MouseEvent e)
				{
					System.out.println("show");
					if(e.getButton() == MouseEvent.BUTTON3)
					{
						
						menu=new contextMenu();
						menu.show(e.getComponent(), e.getX(), e.getY());
					}else if(e.getClickCount()==2){
						task.edit();
					}
					//					setLocation(e.getPoint());
				}

				@Override
				public void mousePressed(MouseEvent e)
				{
						clickOffset = e.getPoint();
				}

				@Override
				public void mouseReleased(MouseEvent e)
				{
					System.out.println(dragging);
					if(dragging)
					{
						boolean h = true;
						int i = 0;
						for (i = incompleteContainers.size()-1; i >= 0 && h; i--)
						{
							if(getIndex()!= i && incompleteContainers.get(i).getLocationOnScreen().getY() < e.getYOnScreen())
								h = false;
						}
						if(h)
						{
							//							System.out.println("give " + incompleteContainers.get(getIndex()).getName() + " urgent priority");
							//change priority to urgent
							task.setPriorityLevel("urgent");
						}
						else
						{
							//							System.out.println("give " + incompleteContainers.get(getIndex()).getName() + " priority of " + incompleteContainers.get(i+1).getName());
							task.setPriorityLevel(incompleteContainers.get(i+1).task.getPriorityLevel());
							incompleteTasks.remove(getIndex());
							incompleteTasks.add(i, task);
						}
						updateGUI();
					}
					dragging = false;
				}
			});
			addMouseMotionListener(new MouseMotionListener()
			{

				@Override
				public void mouseDragged(MouseEvent e) 
				{
					if(e.getButton() == MouseEvent.BUTTON1)
					{
						dragging = true;
						Point newP = e.getLocationOnScreen();
						newP.translate(-(int) (scrollPanel.getLocationOnScreen().getX() + clickOffset.getX()), -(int) (scrollPanel.getLocationOnScreen().getY() + clickOffset.getY()));
						setLocation(newP);
					}
					else
						dragging = false;
				}

				@Override
				public void mouseMoved(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

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
						updateGUI();
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
								updateGUI();
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
	private class fileListener extends MouseAdapter
	{
		public void mouseClicked(MouseEvent e) {
			if (e.getComponent().equals(save)) {
				Backup.createFolder("Z:\\To Do List");
				Backup.saveFile("Z:\\To Do List\\Incompleted Tasks.ser", incompleteTasks);
				Backup.saveFile("Z:\\To Do List\\Completed Tasks.ser", completeTasks);
			} else if (e.getComponent().equals(restore)) {
				loadFiles();
				//create a method that updates the page
			} else if (e.getComponent().equals(print)) {
				Printer printer=new Printer();
				printer.printComponent(mainFrame);
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