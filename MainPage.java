import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class MainPage extends JPanel implements ActionListener
{
	private JFrame mainFrame = new JFrame();
	private JPanel scrollPanel = new JPanel();
	private JScrollPane scroll = new JScrollPane(scrollPanel);
	private JMenuBar menuBar = new JMenuBar();
	private JMenu file = new JMenu("File");
	private JPopupMenu fileMenu = new JPopupMenu();
	private JMenuItem save = new JMenuItem("Save a backup");
	private JMenuItem restore = new JMenuItem("Restore a backup");
	private JMenuItem print = new JMenuItem("Print");
	private JMenuItem closed = new JMenuItem("Closed Action Items");
	private JMenuItem quit = new JMenuItem("Quit");
	private JTextField input = new JTextField();
	private Font dateFont=new Font(Font.SERIF,Font.PLAIN,16);
	private ArrayList<Task> incompleteTasks = new ArrayList<Task>();// these 2 lists are used to access the containers
	protected ArrayList<Task> completeTasks = new ArrayList<Task>();
	protected ArrayList<taskContainer> incompleteContainers = new ArrayList<taskContainer>();
	protected ArrayList<taskContainer> completeContainers = new ArrayList<taskContainer>();
	private ArrayList<String> dateStrings=new ArrayList<String>();

	private static ClosedPage completePage;

	public static ClosedPage getClosedInstance()
	{
		return completePage;
	}

	private static MainPage singleton;

	public static MainPage getInstance()
	{
		return singleton;
	}

	MainPage()
	{
		restoreTasks(true);
		//adds listeners
		file.addMouseListener(new menuListener());
		closed.addMouseListener(new menuListener());
		quit.addMouseListener(new menuListener());
		save.addMouseListener(new fileListener());
		restore.addMouseListener(new fileListener());
		print.addMouseListener(new fileListener());
		for(Task t: incompleteTasks) {
			for(scheduledPriority p: t.getScheduledPriorities()) {
				if(p.getActive()) {
					String d=Event.getDateString(p.getDate());
					if(d.equals(Event.getDateString(Calendar.getInstance().getTime()))) {
						t.setPriorityLevel(p.getLevel());
					}
				}
			}
		}
		singleton = this;
	}
	//creates GUI components for main page
	public void createGUI() {
		mainFrame.setJMenuBar(menuBar);
		mainFrame.setContentPane(this);
		mainFrame.setBackground(new Color(247, 232, 210));
		mainFrame.setTitle("To Do List");
		menuBar.add(file);
		menuBar.add(closed);
		menuBar.add(quit);
		menuBar.setBackground(new Color(189, 209, 237));
		menuBar.setPreferredSize(new Dimension(600, 40));
		closed.setBackground(new Color(189, 209, 237));
		quit.setBackground(new Color(189, 209, 237));
		file.add(fileMenu);
		file.setPreferredSize(new Dimension(200, 20));
		file.setBackground(new Color(173, 204, 247));
		fileMenu.add(save);
		fileMenu.add(restore);
		fileMenu.add(print);
		fileMenu.setBackground(new Color(173, 204, 247));
		restore.setBackground(new Color(205, 221, 242));
		save.setBackground(new Color(205, 221, 242));
		print.setBackground(new Color(205, 221, 242));
		this.add(scroll);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setPreferredSize(new Dimension(600, 500));
		scroll.validate();
		input.addActionListener(this);
		input.setEditable(true);
		input.setText("New Action Item");
		input.setVisible(true);
		input.setActionCommand("Add a Task");
		input.setPreferredSize(new Dimension(590, 30));
		scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.PAGE_AXIS));
		scrollPanel.setBackground(new Color(247, 232, 210));
		scrollPanel.setBorder(BorderFactory.createEmptyBorder(5, 50, 5, 50));
		this.setPreferredSize(new Dimension(600, 550));
		this.add(input);
		mainFrame.setLocation(300, 50);
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
		for(taskContainer t: incompleteContainers) {
			if(t.getTask().equals(editedTask)) {
				t.update();
			}
		}
		updateGUI();
		incompleteTasks.clear();
		for(taskContainer t : incompleteContainers)
		{
			incompleteTasks.add(t.getTask());
		}

	}

	public void updateGUI() {
		//still adds the date before all of the tasks, not just the inactive ones
		scrollPanel.removeAll();
		incompleteContainers.clear();
		dateStrings.clear();
		ArrayList[] tasks = {new ArrayList<Task>(), new ArrayList<Task>(), new ArrayList<Task>(), new ArrayList<Task>()};
		for(Task t: incompleteTasks)
		{
			switch(t.getPriorityLevel())
			{
			case "urgent":
				tasks[0].add(t);
				break;
			case "current":
				tasks[1].add(t);
				break;
			case "eventual":
				tasks[2].add(t);
				break;
			default:
				tasks[3].add(t);
				break;
			}
		}
		
		for(int i = 0; i < 3; i++)
			for(Task tt : (ArrayList<Task>) tasks[i])
				incompleteContainers.add(new taskContainer(tt));
		
		tasks[3].sort(null);
		
		for(Task t : (ArrayList<Task>) tasks[3])
		{
			System.out.println(t.getDateString());
			if(!(t.getDateString()==null)) {
				dateStrings.add(t.getDateString());
				JLabel tempL=new JLabel(t.getDateString());
				tempL.setFont(dateFont);
				scrollPanel.add(tempL);
			}			
			incompleteContainers.add(new taskContainer(t));
		}
		
		for(taskContainer t : incompleteContainers)
			scrollPanel.add(t);
		

		scrollPanel.validate();
		scroll.validate();
		scrollPanel.repaint();
	}

	public class taskContainer extends JComponent
	{
		private Task task;
		private JLabel name;
		private Date date;
		private String dateString="";
		private contextMenu menu;
		private Font urgent=new Font(Font.SERIF,Font.BOLD,20);
		private Font current=new Font(Font.SERIF,Font.PLAIN,20);
		private Font eventual=new Font(Font.SERIF,Font.ITALIC,20);
		private Font inactive=new Font(Font.SERIF,Font.ITALIC,20);
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

		public JLabel getLabel() {
			return name;
		}

		taskContainer(Task task)
		{
			this.task = task;
			name=new JLabel(task.getName());
			date=Calendar.getInstance().getTime();
			this.add(name);
			update();

			addMouseListener(new MouseAdapter()
			{
				//creates menu when right clicking on a task
				@Override
				public void mouseClicked(MouseEvent e)
				{
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
					if(e.getButton() == MouseEvent.BUTTON1)
						dragging = true;
					clickOffset = e.getPoint();
				}

				@Override
				public void mouseReleased(MouseEvent e)
				{
					if(dragging)
					{
						boolean h = true;
						int i = 0;
						for (i = incompleteContainers.size()-1; i >= 0 && h; i--)
						{
							if(getIndex()!= i && incompleteContainers.get(i).getLocationOnScreen().getY()< incompleteContainers.get(getIndex()).getLocationOnScreen().getY())
								h = false;
						}
						if(h)
						{
							//							System.out.println("give " + incompleteContainers.get(getIndex()).getName() + " urgent priority");
							//change priority to urgent
							task.setPriorityLevel(incompleteContainers.get(0).getTask().getPriorityLevel());
							incompleteTasks.remove(getIndex());
							incompleteTasks.add(0, task);
						}
						else
						{
							//System.out.println("give " + incompleteContainers.get(getIndex()).getName() + " priority of " + incompleteContainers.get(i+1).getName());
							//above higher change, below lower change

							int index = getIndex();
							incompleteTasks.add(i+2, task);
							if(i+1 > index)
							{
								task.setPriorityLevel(incompleteContainers.get(i+1).task.getPriorityLevel());
								incompleteTasks.remove(index);
							}
							else
							{
								task.setPriorityLevel(incompleteContainers.get(i+2).task.getPriorityLevel());
								incompleteTasks.remove(index+1);
							}
						}
						updatePage(task);
					}
				}
			});
			addMouseMotionListener(new MouseMotionListener()
			{

				@Override
				public void mouseDragged(MouseEvent e) 
				{
					if(dragging)
					{
						Point newP = e.getLocationOnScreen();
						newP.translate(-(int) (scrollPanel.getLocationOnScreen().getX() + clickOffset.getX()), -(int) (scrollPanel.getLocationOnScreen().getY() + clickOffset.getY()));
						setLocation(newP);
					}
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
				name.setForeground(Color.red);
			}else if(task.getPriorityLevel().equals("current")){
				name.setFont(current);
				name.setForeground(new Color(191, 121, 1));
			}else if(task.getPriorityLevel().equals("eventual")){
				name.setFont(eventual);
				name.setForeground(Color.blue);
			}else {
				name.setFont(inactive);
				name.setForeground(new Color(127, 126, 123));
			}
			dateString=task.getDateString();
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
	}

	public static void main(String[] args) {
		new MainPage().createGUI();
	}
	//deals with functions in file menu
	private class fileListener extends MouseAdapter
	{
		//need it to close when clicked on something else
		public void mouseClicked(MouseEvent e) {
			if (e.getComponent().equals(save)) {
				Backup.saveTasks(mainFrame, incompleteTasks, completeTasks);
			} else if (e.getComponent().equals(restore))
			{
				restoreTasks(false);
			} else if (e.getComponent().equals(print)) {
				Printer printer=new Printer();
				printer.printComponent(mainFrame);
			}
			fileMenu.setVisible(false);
		}
	}

	private void restoreTasks(boolean b)
	{
		ArrayList<Task>[] tasks = Backup.restoreTasks(mainFrame, b);
		incompleteTasks = tasks[0];
		completeTasks = tasks[1];
		updateGUI();

	}

	//deals with functions in menu bar
	private class menuListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (e.getComponent().equals(quit)) {
				Backup.saveTasks(mainFrame, incompleteTasks, completeTasks);
				mainFrame.dispose();
			} else if (e.getComponent().equals(closed)) {
				ClosedPage complete = new ClosedPage(completeTasks);
			} else if (e.getComponent().equals(file)) {
				fileMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}

}