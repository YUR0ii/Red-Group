import javax.swing.*;

import MainPage.taskContainer;
import MainPage.taskContainer.contextMenu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;

public class taskContainer extends JComponent implements Comparable
{
	Task task;
	JLabel name;
	JMenuItem complete=new JMenuItem("Complete the task"); 
	JMenuItem delete=new JMenuItem("Delete the task"); 
	JMenuItem edit=new JMenuItem("Edit the task"); 
	contextMenu menu;
	taskContainer(Task task)
	{
		this.task = task;
		name=new JLabel(task.getName());
		this.setLayout(new FlowLayout());
		this.add(name);

		this.addMouseListener(new MouseAdapter()
		{

			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(e.getButton() == e.BUTTON3)
				{
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
			Action complete = new AbstractAction()
			{

				@Override
				public void actionPerformed(ActionEvent e)
				{
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
			Action delete = new AbstractAction()
			{

				@Override
				public void actionPerformed(ActionEvent e)
				{
					JPopupMenu confirm = new JPopupMenu();
					
					JLabel text = new JLabel("Are you sure you want to delete " + task.getName() + "?");
					JButton del = new JButton("Delete");
					JButton cancel = new JButton("Cancel");
					
					confirm.setLayout(new FlowLayout());
					confirm.add(text);
					confirm.add(del);
					confirm.add(cancel);
					
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
			this.add(complete);
			this.add(delete);
			this.add(edit);
			this.createActionComponent(complete);
			
		}
		class contentMenuListener extends MouseAdapter{
			public void mouseClicked(MouseEvent e) {
				System.out.println("clicked");
				if (e.getComponent().equals(completeI)) {
					System.out.println("complete");
					task.setComplete(true);
					completeTasks.add(task);
					int index=completeTasks.indexOf(task);
					scrollPanel.remove(containers.get(index));
					containers.remove(index);
				} else if (e.getComponent().equals(deleteI)) {
					System.out.println("delete");
					task.delete();
					int index=incompleteTasks.indexOf(task);
					incompleteTasks.remove(task);
					containers.remove(containers.get(index));
				} else if (e.getComponent().equals(editI)) {
					System.out.println("edit");
					task.edit();
				}
				menu.setVisible(false);
			}
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