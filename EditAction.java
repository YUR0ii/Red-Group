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

	private static EditAction singleton;

	public static EditAction getInstance()
	{
		return singleton;
	}
	
	public void createAndShowGUI() {
		// create JFrame
		singleton = this;
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
				update(editingTask);
			}
		});

		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				editingTask.setPriorityLevel("current");
				update(editingTask);
			}
		});

		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				editingTask.setPriorityLevel("eventual");
				update(editingTask);
			}
		});

		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				editingTask.setPriorityLevel("inactive");
				update(editingTask);
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
				update(editingTask);
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
				update(editingTask);
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
				update(editingTask);
			}
		});
		
		// add key listeners to jspinners
		urgentSpinner.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Date date = (Date) currentSpinner.getValue();
					editingTask.updatePriorityDate(date, 0);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		currentSpinner.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Date date = (Date) currentSpinner.getValue();
					editingTask.updatePriorityDate(date, 1);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		eventualSpinner.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Date date = (Date) currentSpinner.getValue();
					editingTask.updatePriorityDate(date, 2);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
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
				update(editingTask);
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
				editingTask.setName(displayItemName.getText());
				update(editingTask);
				frame.dispose();
			}
		});

		JButton printButton = new JButton("Print"); // set printer icon later
//		printButton.setIcon(new ImageIcon("lib/smallprintericon.png"));
//		printButton.setPreferredSize(new Dimension(50, 50));
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
	
	public static void update(String currentText) {
		singleton.updateComment(currentText);
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
	public CommentPage getInstance(Task t) {
		if (count < instLim) {
			CommentPage c = new CommentPage(t);
			count++;
			c.addWindowListener(new WindowAdapter()
	        {
	            @Override
	            public void windowClosing(WindowEvent e)
	            {
	                //System.out.println("Closed");
	            	instanceRemoved();
	                e.getWindow().dispose();
	            }
	        });
			return c;
		} else {
			return null;
		}
	}
	
	public void instanceRemoved() {
		count--;
	}
	
	public static void update(Task t) {
		MainPage.getInstance().updatePage(t);
		if(t.getComplete()) {
			MainPage.getClosedInstance().refreshGUI();			
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