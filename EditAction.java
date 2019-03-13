import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

import javax.swing.*;

public class EditAction implements ActionListener {
	Task editingTask;
	JFrame frame = new JFrame();
	JPanel commentPanel = new JPanel();

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
		button4.setSelected(true);
		group.add(button1); group.add(button2); group.add(button3); group.add(button4);
		radioButtonPanel.add(button1); radioButtonPanel.add(button2); radioButtonPanel.add(button3); radioButtonPanel.add(button4);
		
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
		for (String s : checkbnames) {
			JCheckBox checkb = new JCheckBox(s);
			checkBoxPanel.add(checkb);
		}

		// create and add JSpinners (date selection)
		JPanel spinnerPanel = new JPanel();
		spinnerPanel.setLayout(new BoxLayout(spinnerPanel, BoxLayout.Y_AXIS));
		for (int i = 0; i < 4; i++) {
			SpinnerDateModel model = new SpinnerDateModel();
			JSpinner spinner = new JSpinner(model);
			spinnerPanel.add(spinner);
		}
		
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
		commentPanel.setLayout(new BoxLayout(commentPanel, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane(commentPanel);
		scrollPane.setPreferredSize(new Dimension(350, 200));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		// create JLabels for displaying comments
		for (String s:editingTask.getAllComments()) {
			JLabel newLabel = new JLabel(s);
			commentPanel.add(newLabel);
		}
		
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
				// set print operation later
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
		// create JLabels for displaying comments again
		for (String s:editingTask.getAllComments()) {
			JLabel newLabel = new JLabel(s);
			commentPanel.add(newLabel);
		}
		
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
		//testTask.addComment("blahblahblahblah blahblah blahblahblahblahblah blahblah blah");
		EditAction e = new EditAction(testTask);
		e.createAndShowGUI();
	}
}
