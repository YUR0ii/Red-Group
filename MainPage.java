import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.util.*;
import javax.swing.*;


public class MainPage extends JPanel implements ActionListener {
	private JFrame mainFrame = new JFrame();
	private JPanel scrollPanel = new JPanel();
	private JScrollPane scroll = new JScrollPane(scrollPanel);
	private JMenuBar menuBar = new JMenuBar();
	private JMenu file = new JMenu("File");
	private JPopupMenu fileMenu = new JPopupMenu();
	private JMenuItem save = new JMenuItem("Save a backup");
	private JMenuItem restore = new JMenuItem("Restore a backup");
	private JMenuItem print = new JMenuItem("Print");
	private JMenuItem closed = new JMenuItem("Closed");
	private JMenuItem quit = new JMenuItem("Quit");
	private JTextField input = new JTextField();
	protected ArrayList<Task> incompleteTasks = new ArrayList<Task>();
	protected ArrayList<Task> completeTasks = new ArrayList<Task>();
	protected ArrayList<taskContainer> containers = new ArrayList<taskContainer>();

	MainPage() {
		file.addMouseListener(new menuListener());
		closed.addMouseListener(new menuListener());
		quit.addMouseListener(new menuListener());
		save.addMouseListener(new fileListener());
		restore.addMouseListener(new fileListener());
		print.addMouseListener(new fileListener());
		createGUI();
	}

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
		input.addActionListener(this);
		input.setEditable(true);
		input.setText("New Task");
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
		mainFrame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String eventName = e.getActionCommand();
		if (eventName.equals("Add a Task")) {
			Task temp = new Task(input.getText());
			incompleteTasks.add(temp);
			containers.add(new taskContainer(temp));
			int index = incompleteTasks.indexOf(temp);
			scrollPanel.add(containers.get(index));
			//scrollPanel.repaint();
		}
	}

	public void paintComponent(Graphics g) {
	}

	public class taskContainer extends JComponent implements Comparable{
		private Task task;
		private JLabel name;
		private JLabel date;
		private JFrame taskFrame=new JFrame();
		private taskMenuPane taskMenu=new taskMenuPane();
		taskContainer(Task otask) {
			task = otask;
			name=new JLabel(task.getName());
			this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			this.setSize(500,50);
			name.setSize(500,50);
			if(task.getPriorityLevel().contentEquals("inactive")) {
				date=new JLabel();
				String dateString=Calendar.getInstance().getTime().toString();
				date.setText(dateString);
				date.setSize(500,50);
				this.add(date);
			}
			this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
			this.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == e.BUTTON3) {
						System.out.println("test");
						taskMenu.createMenu();
						taskFrame.setVisible(true);
					}
				}
			});
			this.add(name);
			scrollPanel.repaint();
		}
		public class taskMenuPane extends JPanel{
			private JButton complete=new JButton("Complete the task");
			private JButton delete=new JButton("Delete the task");
			private JButton edit=new JButton("Edit the task");
			public void createMenu() {
				taskFrame.setContentPane(taskMenu);
				Action taskMenuListener=new AbstractAction(){
					public void actionPerformed(ActionEvent e) {
						if (e.getActionCommand().equals("complete")) {
							task.complete();
							completeTasks.add(task);
							int index=completeTasks.indexOf(task);
							scrollPanel.remove(containers.get(index));
							containers.remove(index);
							System.out.println("test55");
						} else if (e.getActionCommand().equals("delete")) {
							task.delete();
							int index=incompleteTasks.indexOf(task);
							incompleteTasks.remove(task);
							containers.remove(containers.get(index));
							System.out.println("test66");
						} else if (e.getActionCommand().equals("edit")) {
							task.edit();
							System.out.println("test77");
						}
						taskFrame.dispose();
						scrollPanel.repaint();
					}
				};
				complete.setAction(taskMenuListener);
				delete.setAction(taskMenuListener);
				edit.setAction(taskMenuListener);
				complete.setActionCommand("complete");
				delete.setActionCommand("delete");
				edit.setActionCommand("edit");
				complete.setText("Complete the task");
				delete.setText("Delete the task");
				edit.setText("Edit the task");
				this.add(complete);
				this.add(delete);
				this.add(edit);
				this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
				complete.setPreferredSize(new Dimension(110,20));
				delete.setPreferredSize(new Dimension(110,20));
				edit.setPreferredSize(new Dimension(110,20));
				this.setSize(300,300);
				taskFrame.setLocation(name.getX(),name.getY());
				taskFrame.pack();
			}
			
		}
		
		/*
		@Override
		public int compareTo(Object o) {
			return 0;
		}

		public int compareTo(taskContainer t) {
			if (task.getPriorityLevel().equals(t.task.getPriorityLevel())) {
				return task.getName().compareToIgnoreCase(t.task.getName());
			} else {
				// return Integer.compare(task.getPriorityLevel(), t.task.getPriorityLevel());
			}
		}*/
	}

	public static void main(String[] args) {
		MainPage page = new MainPage();
	}

	private class fileListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (e.getComponent().equals(save)) {

			} else if (e.getComponent().equals(restore)) {

			} else if (e.getComponent().equals(print)) {

			}
			fileMenu.setVisible(false);
		}
	}

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
