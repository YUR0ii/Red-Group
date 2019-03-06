import javax.swing.*;
import java.awt.*;

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
