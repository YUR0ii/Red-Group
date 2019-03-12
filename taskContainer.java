import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.util.*;

public class taskContainer extends JComponent implements Comparable
{
	Task task;
	JLabel name;
	Date date;
	JMenuItem complete=new JMenuItem("Complete the task"); 
	JMenuItem delete=new JMenuItem("Delete the task"); 
	JMenuItem edit=new JMenuItem("Edit the task"); 
	contextMenu menu;
	taskContainer(Task task)
	{
		this.task = task;
		name=new JLabel(task.getName());
		this.setLayout(new FlowLayout());
		//adds a date if it is inactive
		if(task.getPriorityLevel().equals("inactive")) {
			this.add(new JLabel(Calendar.getInstance().getTime().toString()));
		}
		this.add(name);
		scrollPanel.repaint();
		this.addMouseListener(new MouseAdapter()
		{

			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(e.getButton() == e.BUTTON3)
				{
					//creates menu when file button is clicked
					menu=new contextMenu();
					System.out.println("show");
					menu.show(e.getComponent(), e.getX(), e.getY());
				}
			};
		});
	}
	class contextMenu extends JPopupMenu
	{	
		contextMenu()
		{
			//creates an action to complete a task
			Action complete = new AbstractAction()
			{

				@Override
				public void actionPerformed(ActionEvent e)
				{
					//completes a task
					System.out.println("complete");
					task.setComplete(true);
					completeTasks.add(task);
					int index=incompleteTasks.indexOf(task);
					incompleteTasks.remove(index);
					scrollPanel.remove(containers.get(index));
					containers.remove(index);
					scrollPanel.repaint();
				}
				
				@Override
				public Object getValue(String key) {
					if(key.equals("NAME"))
						return "Mark Task as Completed";
					return null;
				}

			};
			//creates an action to edit a task
			Action edit = new AbstractAction()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					new EditAction(task).createAndShowGUI();
				}

				@Override
				public Object getValue(String key) {
					if(key.equals("NAME"))
						return "Edit Task";
					return null;
				}

			};
			//creates an action to delete a task
			Action delete = new AbstractAction()
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
							System.out.println("delete");
							task.delete();
							int index=incompleteTasks.indexOf(task);
							incompleteTasks.remove(task);
							scrollPanel.remove(containers.get(index));
							containers.remove(index);
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

				@Override
				public Object getValue(String key) {
					if(key.equals("NAME"))
						return "Delete Task";
					return null;
				}

			};
			//adds actions to menu in Action form
			this.add(complete);
			this.add(delete);
			this.add(edit);
			this.createActionComponent(complete);
			
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