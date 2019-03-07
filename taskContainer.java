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

	taskContainer(Task task)
	{
		this.task = task;
		name.setText(task.getName());
		this.setLayout(new FlowLayout());
		this.add(name);

		this.addMouseListener(new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(e.getButton() == e.BUTTON3)
				{
					new contextMenu();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
	}

	class contextMenu extends JMenu
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
