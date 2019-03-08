import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.util.*;
import javax.swing.*;

import taskContainer.contextMenu;

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
	private ArrayList<Task> incompleteTasks = new ArrayList<Task>();
	private ArrayList<Task> completeTasks = new ArrayList<Task>();
	private ArrayList<taskContainer> containers = new ArrayList<taskContainer>();

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
			// repaint();
		}
	}

	public void paintComponent(Graphics g) {

	}

	public class taskContainer extends JComponent implements Comparable {
		private Task task;
		private JLabel name;
		taskContainer(Task task) {
			this.task = task;
			name=new JLabel(task.getName());
			this.setLayout(new FlowLayout());
			this.add(name);

			this.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == e.BUTTON3) {
						new contextMenu();
					}
				}
			});
		}
		class contextMenu extends JPopupMenu {
			contextMenu() {
				Action complete = new Action() {

					@Override
					public void actionPerformed(ActionEvent e) {
						task.complete();
					}

					@Override
					public void addPropertyChangeListener(PropertyChangeListener listener) {
						// TODO Auto-generated method stub

					}

					@Override
					public Object getValue(String key) {
						if (key.equals("NAME"))
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
				Action edit = new Action() {

					@Override
					public void actionPerformed(ActionEvent e) {
						new EditAction(task).createAndShowGUI();
					}

					@Override
					public void addPropertyChangeListener(PropertyChangeListener listener) {
						// TODO Auto-generated method stub

					}

					@Override
					public Object getValue(String key) {
						if (key.equals("NAME"))
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
				Action delete = new Action() {

					@Override
					public void actionPerformed(ActionEvent e) {
						JWindow confirm = new JWindow();
						JLabel text = new JLabel("Are you sure you want to delete " + task.getName() + "?");
						JButton del = new JButton("Delete");
						JButton cancel = new JButton("Cancel");

						confirm.setLayout(new FlowLayout());
						confirm.add(text);
						confirm.add(del);
						confirm.add(cancel);
						del.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								// delete task
							}
						});
						cancel.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
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
						if (key.equals("NAME"))
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
		public int compareTo(Object o) {
			return 0;
		}

		public int compareTo(taskContainer t) {
			if (task.getPriorityLevel().equals(t.task.getPriorityLevel())) {
				return task.getName().compareToIgnoreCase(t.task.getName());
			} else {
				// return Integer.compare(task.getPriorityLevel(), t.task.getPriorityLevel());
			}
		}
	}

	public static void main(String[] args) {
		MainPage page = new MainPage();
	}

	class fileListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (e.getComponent().equals(save)) {

			} else if (e.getComponent().equals(restore)) {

			} else if (e.getComponent().equals(print)) {

			}
			fileMenu.setVisible(false);
		}
	}

	class menuListener extends MouseAdapter {
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
