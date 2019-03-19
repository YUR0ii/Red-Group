import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.util.Calendar;
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
		String[] checkbnames = new String[] { "Urgent", "Current", "Eventual" };
		JPanel checkBoxPanel = new JPanel();
		checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
		JCheckBox urgentBox = new JCheckBox();
		JCheckBox currentBox = new JCheckBox();
		JCheckBox eventualBox = new JCheckBox();
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
				Date date = (Date) urgentSpinner.getValue();
				editingTask.updatePriorityDate(date,0);
			}
		});
		currentBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Date date = (Date) currentSpinner.getValue();
				editingTask.updatePriorityDate(date,1);
			}
		});
		eventualBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Date date = (Date) eventualSpinner.getValue();
				editingTask.updatePriorityDate(date,2);
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
		commentArea.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CommentPage c = new CommentPage(editingTask.getComment());
				
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
		
		// history, done, print buttons
		JButton historyButton = new JButton("History");
		historyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HistoryPage newHistory = new HistoryPage(editingTask);
				newHistory.openHistoryPage();
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
		printButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Printer printer=new Printer();
				printer.printComponent(frame);
			}
		});
		
		// add buttons to a panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(historyButton); buttonPanel.add(doneButton); buttonPanel.add(printButton);

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
	
	public void updateCommentPanel() {
		// update textarea
		commentArea.setText(editingTask.getComment());
		
		// update frame
		frame.getContentPane().repaint();
		frame.getContentPane().revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	public static void main(String[] args) {
		Task testTask = new Task("testing");
		testTask.setComment("blahblahblahblah blahblah blahblahblahblahblah blahblah blahblahblahblahblah blahblah blahblahblahblahblah blahblah blahblahblahblahblah blahblah blahblahblahblahblah blahblah blah");
		EditAction e = new EditAction(testTask);
		e.createAndShowGUI();
	}
}
