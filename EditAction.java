import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
import javax.swing.*;

public class EditAction implements ActionListener {
	Task editingTask;
	JFrame frame = new JFrame();
	JTextArea commentArea = new JTextArea();

	public EditAction(Task task) {
		editingTask = task;
	}

	public void createAndShowGUI() {
		// create JFrame
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(new Dimension(400, 500));
		frame.setTitle("Edit Item");

		// create and add radio buttons
		// has to be individual buttons or else can't set action listener
		JPanel radioButtonPanel = new JPanel(new FlowLayout());
		ButtonGroup group = new ButtonGroup();
		JRadioButton button1 = new JRadioButton("Urgent");
		JRadioButton button2 = new JRadioButton("Current");
		JRadioButton button3 = new JRadioButton("Eventual");
		JRadioButton button4 = new JRadioButton("Inactive");
		if (editingTask.getPriorityLevel().equals("urgent")) {
			button1.setSelected(true);
		} else if (editingTask.getPriorityLevel().equals("current")) {
			button2.setSelected(true);
		} else if (editingTask.getPriorityLevel().equals("eventual")) {
			button3.setSelected(true);
		} else {
			button4.setSelected(true);
		}
		group.add(button1);
		group.add(button2);
		group.add(button3);
		group.add(button4);
		radioButtonPanel.add(button1);
		radioButtonPanel.add(button2);
		radioButtonPanel.add(button3);
		radioButtonPanel.add(button4);

		// add action listener to radio buttons
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				editingTask.setPriorityLevel("Urgent");
			}
		});

		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				editingTask.setPriorityLevel("Current");
			}
		});

		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				editingTask.setPriorityLevel("Eventual");
			}
		});

		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				editingTask.setPriorityLevel("Inactive");
			}
		});

		// create and add checkboxes
		JPanel checkBoxPanel = new JPanel();
		checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
		JCheckBox urgentBox = new JCheckBox("Urgent");
		JCheckBox currentBox = new JCheckBox("Current");
		JCheckBox eventualBox = new JCheckBox("Eventual");
		checkBoxPanel.add(urgentBox);
		checkBoxPanel.add(currentBox);
		checkBoxPanel.add(eventualBox);

		// create and add JSpinners (date selection)
		JPanel spinnerPanel = new JPanel();
		spinnerPanel.setLayout(new BoxLayout(spinnerPanel, BoxLayout.Y_AXIS));
		SpinnerDateModel model = new SpinnerDateModel();
		JSpinner urgentSpinner = new JSpinner(model);
		JSpinner currentSpinner = new JSpinner(model);
		JSpinner eventualSpinner = new JSpinner(model);
		spinnerPanel.add(urgentSpinner);
		spinnerPanel.add(currentSpinner);
		spinnerPanel.add(eventualSpinner);

		// add action listeners to checkboxes
		urgentBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (urgentBox.isSelected()) {
					Date date = (Date) urgentSpinner.getValue();
					editingTask.updatePriorityDate(date, 0);
				}
				editingTask.getScheduledPriority(0).setActive(urgentBox.isSelected());
			}
		});
		currentBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (currentBox.isSelected()) {
					Date date = (Date) currentSpinner.getValue();
					editingTask.updatePriorityDate(date, 1);
				}
				editingTask.getScheduledPriority(1).setActive(currentBox.isSelected());
			}
		});
		eventualBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (eventualBox.isSelected()) {
					Date date = (Date) eventualSpinner.getValue();
					editingTask.updatePriorityDate(date, 2);
				}
				editingTask.getScheduledPriority(2).setActive(eventualBox.isSelected());
			}
		});

		// lazy coding
		JPanel checkBoxAndSpinnerPanel = new JPanel(new FlowLayout());
		checkBoxAndSpinnerPanel.add(checkBoxPanel);
		checkBoxAndSpinnerPanel.add(spinnerPanel);

		// create regular JLabels
		JLabel displayItemName = new JLabel("Edit " + editingTask.getName());
		Font itemNameFont = new Font("Arial", Font.PLAIN, 18);
		displayItemName.setFont(itemNameFont);
		JLabel displayCommentTitle = new JLabel("Comments");

		// create comment area and make scrollable
		commentArea.setEditable(false);
		commentArea.setLineWrap(true);
		commentArea.setWrapStyleWord(true);
		commentArea.setText(editingTask.getComment());
		commentArea.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.WHITE));
		JScrollPane scrollPane = new JScrollPane(commentArea);
		scrollPane.setPreferredSize(new Dimension(350, 200));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		// add action listener to comment area
		commentArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CommentPage c = new CommentPage(editingTask);
			}
		});

		// history, done, print buttons
		JButton historyButton = new JButton("History");
		historyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HistoryPage newHistory = new HistoryPage(editingTask);
				// newHistory.openHistoryPage();
			}
		});

		JButton doneButton = new JButton("Done");
		doneButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		JButton printButton = new JButton(); // set printer icon later
		printButton.setIcon(new ImageIcon("lib/smallprintericon.png"));
		printButton.setPreferredSize(new Dimension(50, 25));
		printButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Printer printer = new Printer();
				printer.printComponent(frame);
			}
		});

		// add buttons to a panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(historyButton);
		buttonPanel.add(doneButton);
		buttonPanel.add(printButton);

		// add components to JFrame
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.getContentPane().add(displayItemName);
		displayItemName.setAlignmentX((float) 0.5);
		frame.getContentPane().add(radioButtonPanel);
		frame.getContentPane().add(checkBoxAndSpinnerPanel);
		frame.getContentPane().add(displayCommentTitle);
		displayCommentTitle.setAlignmentX((float) 0.5);
		frame.getContentPane().add(scrollPane);
		scrollPane.setAlignmentX((float) 0.5);
		frame.getContentPane().add(buttonPanel);
		frame.setVisible(true);
	}

	public void updateComment(String s) {
		// update textarea
		commentArea.setText(s);

		// update frame
		frame.getContentPane().repaint();
		frame.getContentPane().revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	class CommentPage extends JFrame {
		Task task;
		private String currentText;

		private JSplitPane sp;
		private JPanel topPanel;
		private JPanel bottomPanel;
		private JScrollPane scroll;
		private JTextArea textArea;
		private JPanel inputPanel;
		private JButton commit;
		private JButton delete;
		private JLabel title;
		private Event recentEvent;
		private String ogComment;

		CommentPage(Task t) {
			task = t;
			ogComment = task.getComment();
			currentText = task.getComment();
			setLocation(500, 300);
			sp = new JSplitPane();
			topPanel = new JPanel();
			bottomPanel = new JPanel();
			textArea = new JTextArea();
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			textArea.setText(currentText);
			scroll = new JScrollPane(textArea);
			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			title = new JLabel("Edit Comment");
			topPanel.add(title);
			inputPanel = new JPanel();
			commit = new JButton("Commit");
			commit.setPreferredSize(new Dimension(199, 25));
			delete = new JButton("Delete");
			delete.setPreferredSize(new Dimension(199, 25));
			setPreferredSize(new Dimension(400, 400));
			getContentPane().add(sp);
			sp.setOrientation(JSplitPane.VERTICAL_SPLIT);
			sp.setTopComponent(topPanel);
			sp.setBottomComponent(bottomPanel);
			topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
			bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
			bottomPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			bottomPanel.add(scroll);
			bottomPanel.add(inputPanel);
			inputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));
			inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
			inputPanel.add(commit);
			inputPanel.add(delete);

			pack();
			initiateComponents();
			setVisible(true);
		}

		public String getText() {
			return currentText;
		}

		private void initiateComponents() {
			textArea.addKeyListener(new KeyListener() {
				public void keyPressed(KeyEvent e) {
				}
				public void keyReleased(KeyEvent e) {
				}
				public void keyTyped(KeyEvent arg0) {
					currentText = textArea.getText();
				}
			});
			
			commit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					currentText = textArea.getText();
					recentEvent = new commentEvent(ogComment, currentText);
					task.setComment(currentText);
					updateComment(currentText);
					dispose();
				}
			});
			
			delete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					recentEvent = new commentEvent(ogComment, "");
					currentText = "";
					dispose();
				}
			});
		}
		public Event getRecentEvent() {
			return recentEvent;
		}
	}

	public static void main(String[] args) {
		Task testTask = new Task("testing");
		testTask.setComment(
				"blahblahblahblah blahblah blahblahblahblahblah blahblah blahblahblahblahblah blahblah blahblahblahblahblah blahblah blahblahblahblahblah blahblah blahblahblahblahblah blahblah blah");
		EditAction e = new EditAction(testTask);
		e.createAndShowGUI();
	}
}