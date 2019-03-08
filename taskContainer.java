import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

	taskContainer(Task task)
	{
		this.task = task;
		name.setText(task.getName());
		this.setLayout(new FlowLayout());
		this.add(name);

		this.addMouseListener(new MouseAdapter()
		{

			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(e.getButton() == e.BUTTON3)
				{
					new contextMenu();
				}
		};
	});
//possibly get rid of this?
	class contextMenu extends JPopupMenu
	{	
		contextMenu()
		{
			Action complete = new Action()
			{

				@Override
				public void actionPerformed(ActionEvent e)
				{
					task.complete();
				}

				@Override
				public void addPropertyChangeListener(PropertyChangeListener listener) {
					// TODO Auto-generated method stub

				}

				@Override
				public Object getValue(String key) {
					if(key.equals("NAME"))
						return "Mark Task as Completed";
					return null;
				}

				@Override
				public boolean isEnabled() {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public void putValue(String key, Object value) {
					// TODO Auto-generated method stub

				}

				@Override
				public void removePropertyChangeListener(PropertyChangeListener listener) {
					// TODO Auto-generated method stub

				}

				@Override
				public void setEnabled(boolean b) {
					// TODO Auto-generated method stub

				}

			};
			Action edit = new Action()
			{

				@Override
				public void actionPerformed(ActionEvent e)
				{
					new EditAction(task).createAndShowGUI();
				}

				@Override
				public void addPropertyChangeListener(PropertyChangeListener listener) {
					// TODO Auto-generated method stub

				}

				@Override
				public Object getValue(String key) {
					if(key.equals("NAME"))
						return "Edit Task";
					return null;
				}

				@Override
				public boolean isEnabled() {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public void putValue(String key, Object value) {
					// TODO Auto-generated method stub

				}

				@Override
				public void removePropertyChangeListener(PropertyChangeListener listener) {
					// TODO Auto-generated method stub

				}

				@Override
				public void setEnabled(boolean b) {
					// TODO Auto-generated method stub

				}

			};
			Action delete = new Action()
			{

				@Override
				public void actionPerformed(ActionEvent e)
				{
					JWindow confirm = new JWindow();
					
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
//							delete task
						}
						
					});
					cancel.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e)
						{
							confirm.dispose();
						}
						
					});
				}

				@Override
				public void addPropertyChangeListener(PropertyChangeListener listener) {
					// TODO Auto-generated method stub

				}

				@Override
				public Object getValue(String key) {
					if(key.equals("NAME"))
						return "Delete Task";
					return null;
				}

				@Override
				public boolean isEnabled() {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public void putValue(String key, Object value) {
					// TODO Auto-generated method stub

				}

				@Override
				public void removePropertyChangeListener(PropertyChangeListener listener) {
					// TODO Auto-generated method stub

				}

				@Override
				public void setEnabled(boolean b) {
					// TODO Auto-generated method stub

				}

			};
			this.add(complete);
			this.add(edit);
			this.add(delete);
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
