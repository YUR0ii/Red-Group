import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;


//The dragging capabilities and dates for inactive items need to be disabled
//The Mark as Complete option also needs to be deactivated

public class ClosedPage extends MainPage{
	private JFrame closeFrame=new JFrame();
	private JPanel panel=new JPanel();
	private static ArrayList<Task> closedTasks;
	private ArrayList<closedTaskContainer> closedContainers=new ArrayList<closedTaskContainer>();
	
	ClosedPage(ArrayList<Task> coTasks){
		closedTasks=coTasks;
		JScrollPane scroll = new JScrollPane(panel);
		this.add(scroll);
		this.setBackground(new Color(247, 232, 210));
	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    scroll.setPreferredSize(new Dimension(440,410));
	    scroll.validate();
	    
		panel.setPreferredSize(new Dimension(440, 410));
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setBackground(new Color(247, 232, 210));
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.setPreferredSize(new Dimension(450, 450));

		//sets up the frame
		closeFrame.setContentPane(this);
	    closeFrame.setLocation(450, 150);
		closeFrame.setBackground(new Color(247, 232, 210));
		closeFrame.setSize(new Dimension(450, 450));
	    closeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    closeFrame.setVisible(true);
	    closeFrame.setResizable(false);
		refreshGUI();
	}	
	
	//adds the components to the panel in reverse order
	public void refreshGUI() {
		for(closedTaskContainer t: closedContainers) {
			t.update();
		}
		panel.removeAll();
		closedContainers.clear();
		for(Task t: closedTasks) {
			System.out.println("testee");
			closedTaskContainer temp=new closedTaskContainer(t);
			closedContainers.add(temp);
		}
		for(int i=closedContainers.size()-1;i>-1;i--) {
			panel.add(closedContainers.get(i));
		}
		panel.validate();
		panel.repaint();
	}
	public void setClosedTasks(){
		closedTasks=completeTasks;
	}
	
	public class closedTaskContainer extends JComponent
	{
		private Task task;
		private JLabel name;
		private contextMenu menu;
		private Font urgent=new Font(Font.SERIF,Font.BOLD,20);
		private Font current=new Font(Font.SERIF,Font.PLAIN,20);
		private Font eventual=new Font(Font.SERIF,Font.ITALIC,20);
		private Font inactive=new Font(Font.SERIF,Font.ITALIC,20);

		closedTaskContainer(Task task)
		{
			this.task = task;
			name=new JLabel(task.getName());
			this.setLayout(new FlowLayout());
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
			});
			this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		}
		
		public int getIndex()
		{
			return closedContainers.indexOf(this);
		}
		
		public String getName()
		{
			return task.getName();
		}

		public JLabel getLabel() {
			return name;
		}
		
		public Task getTask() {
			return task;
		}

		public void setTask(Task nTask) {
			task=nTask;
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
		}
		
		class contextMenu extends JPopupMenu
		{	
			contextMenu()
			{
				//creates an action that uncompletes a task
				JMenuItem complete = new JMenuItem("Mark as Complete");
				complete.setFocusable(false);
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
								int index=closedTasks.indexOf(task);
								closedTasks.remove(task);
								panel.remove(closedContainers.get(index));
								closedContainers.remove(index);	
								refreshGUI();
								MainPage.getInstance().updatePage(task);
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
						confirm.setLocation(closeFrame.getX()+closeFrame.getWidth()/2-confirm.getWidth()/2,
								closeFrame.getY()+closeFrame.getHeight()/2-confirm.getHeight()/2);
					}
				});

				//adds actions to menu
				this.add(complete);
				this.add(delete);
				this.add(edit);

			}
		}
	}
}
