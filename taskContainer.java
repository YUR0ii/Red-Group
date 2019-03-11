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
	taskContainer cont = this;

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
				new contextMenu(cont);
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

	class contextMenu extends JPopupMenu
	{	
		contextMenu(Component parent)
		{
			JMenuItem complete = new JMenuItem("Mark task as completed");
			JMenuItem edit = new JMenuItem("Edit task");
			JMenuItem delete = new JMenuItem("Delete task");
			this.add(complete);
			this.add(edit);
			this.add(delete);
			this.show(parent, parent.getMousePosition().x, parent.getMousePosition().y);

			complete.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					task.complete();
				}

			});
			edit.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					task.edit();
				}
			});
			delete.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					new confirm();
				}
			});
		}
	}

	class confirm extends JWindow
	{
		JLabel text = new JLabel("Delete " + task.getName() + "?");
		JButton y = new JButton("Yes");
		JButton n = new JButton("No");
		confirm()
		{
			add(text);
			add(y);
			add(n);
			setLayout(new FlowLayout());
			this.setVisible(true);

			n.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					dispose();
				}
			});

			y.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					task.delete();
					dispose();
				}
			});
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
