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
		radioButtonPanel.setBackground(new Color(247, 232, 210)); 
		ButtonGroup group = new ButtonGroup();
		JRadioButton button1 = new JRadioButton("Urgent");
		button1.setForeground(Color.red);
		button1.setBackground(new Color(247, 232, 210));
		JRadioButton button2 = new JRadioButton("Current");
		button2.setForeground(new Color(191, 121, 1));
		button2.setBackground(new Color(247, 232, 210));
		JRadioButton button3 = new JRadioButton("Eventual");
		button3.setForeground(Color.blue);
		button3.setBackground(new Color(247, 232, 210));
		JRadioButton button4 = new JRadioButton("Inactive");
		button4.setForeground(new Color(127, 126, 123));
		button4.setBackground(new Color(247, 232, 210));
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
				editingTask.setPriorityLevel("urgent");
			}
		});

		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				editingTask.setPriorityLevel("current");
			}
		});

		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				editingTask.setPriorityLevel("eventual");
			}
		});

		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				editingTask.setPriorityLevel("inactive");
			}
		});

		// create and add checkboxes
		JPanel checkBoxPanel = new JPanel();
		checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
		checkBoxPanel.setBackground(new Color(247, 232, 210)); 
		JCheckBox urgentBox = new JCheckBox("Urgent");
		urgentBox.setForeground(Color.red);
		urgentBox.setBackground(new Color(247, 232, 210));
		JCheckBox currentBox = new JCheckBox("Current");
		currentBox.setForeground(new Color(191, 121, 1));
		currentBox.setBackground(new Color(247, 232, 210));
		JCheckBox eventualBox = new JCheckBox("Eventual");
		eventualBox.setForeground(Color.blue);
		eventualBox.setBackground(new Color(247, 232, 210));
		if(editingTask.getScheduledPriority(0).getActive()) {
			urgentBox.setSelected(true);
		}else if(editingTask.getScheduledPriority(1).getActive()) {
			currentBox.setSelected(true);
		}else if(editingTask.getScheduledPriority(2).getActive()) {
			eventualBox.setSelected(true);
		}
		checkBoxPanel.add(urgentBox);
		checkBoxPanel.add(currentBox);
		checkBoxPanel.add(eventualBox);

		// create and add JSpinners (date selection)
		JPanel spinnerPanel = new JPanel();
		spinnerPanel.setLayout(new BoxLayout(spinnerPanel, BoxLayout.Y_AXIS));
		JSpinner urgentSpinner = new JSpinner(new SpinnerDateModel());
		JSpinner currentSpinner = new JSpinner(new SpinnerDateModel());
		JSpinner eventualSpinner = new JSpinner(new SpinnerDateModel());
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
		checkBoxAndSpinnerPanel.setBackground(new Color(247, 232, 210)); 
		checkBoxAndSpinnerPanel.add(checkBoxPanel);
		checkBoxAndSpinnerPanel.add(spinnerPanel);

		// display item name
		JTextField displayItemName = new JTextField(editingTask.getName());
		Font itemNameFont = new Font("Arial", Font.PLAIN, 16);
		displayItemName.setFont(itemNameFont);
		displayItemName.setHorizontalAlignment(SwingConstants.CENTER);
		displayItemName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				editingTask.setName(displayItemName.getText());
			}
		});
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
				getInstance(editingTask);
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
				update(editingTask);
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
		buttonPanel.setBackground(new Color(247, 232, 210));
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
		displayCommentTitle.setBackground(new Color(247, 232, 210));
		frame.getContentPane().add(scrollPane);
		scrollPane.setAlignmentX((float) 0.5);
		frame.getContentPane().add(buttonPanel);
		frame.setVisible(true);
	}

	public void updateComment(String s) {
		// update text area
		commentArea.setText(s);

		// update frame
		frame.getContentPane().repaint();
		frame.getContentPane().revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	
	private final int instLim = 1;
	private int count = 0;
	// limit instances of commentpage
	private CommentPage getInstance(Task t) {
		if (count < instLim) {
			CommentPage c = new CommentPage(t);
			count++;
			return c;
		} else {
			return null;
		}
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
			setLocation(400, 200);
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
			topPanel.setBackground(new Color(247, 232, 210)); 
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
	
	public static void update(Task t) {
		MainPage.getInstance().updatePage(t);
	}
	public static void main(String[] args) {
		Task testTask = new Task("testing");
		testTask.setComment(
				"blahblahblahblah blahblah blahblahblahblahblah blahblah blahblahblahblahblah blahblah blahblahblahblahblah blahblah blahblahblahblahblah blahblah blahblahblahblahblah blahblah blah");
		EditAction e = new EditAction(testTask);
		e.createAndShowGUI();
	}
}