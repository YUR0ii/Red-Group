import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;


public class taskContainer extends JComponent implements Comparable
{
	private Task task;
	private JLabel name;
	private Date date;
	private contextMenu menu;
	private Font urgent=new Font(Font.SERIF,Font.BOLD,14);
	private Font current=new Font(Font.SERIF,Font.PLAIN,14);
	private Font eventual=new Font(Font.SERIF,Font.ITALIC,14);
	private Font inactive=new Font(Font.SERIF,Font.ITALIC,14);
	taskContainer(Task task)
	{
		this.task = task;
		name=new JLabel(task.getName());
		this.setLayout(new FlowLayout());
		this.add(name);
		
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
					getCompleteTasks().add(task);
					int index=incompleteTasks.indexOf(task);
					incompleteTasks.remove(index);
					scrollPanel.remove(containers.get(index));
					containers.remove(index);
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
							scrollPanel.remove(containers.get(index));
							containers.remove(index);
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