import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

import javax.swing.*;

public class EditAction implements ActionListener {
	Task editingTask;

	public EditAction(Task task) {
		editingTask = task;
	}

	public void createAndShowGUI() {
		// create JFrame
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(new Dimension(400, 500));
		frame.setTitle("Edit Item");

		// create and add radio buttons
		// has to be individual buttons or else can't set action listener
		String[] radiobnames = new String[] { "Urgent", "Current", "Eventual", "Inactive" };
		JPanel radioButtonPanel = new JPanel(new FlowLayout());
		ButtonGroup group = new ButtonGroup();
		for (String s : radiobnames) {
			JRadioButton radiob = new JRadioButton(s);
			group.add(radiob);
			radioButtonPanel.add(radiob);
		}
		
		// add action listener to radio buttons
		

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
		JPanel commentField = new JPanel();
		JScrollPane scrollPane = new JScrollPane(commentField);
		scrollPane.setPreferredSize(new Dimension(350, 200));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		// create JLabels for displaying comments
		for (String s:editingTask.getAllComments()) {
			JLabel newLabel = new JLabel(s);
			commentField.add(newLabel);
		}
		
		// history, done, print buttons
		JButton historyButton = new JButton("History");
		historyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				historyPage newHistory = new historyPage(editingTask);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	public static void main(String[] args) {
		Task testTask = new Task("testing");
		testTask.addComment("blahblahblahblah blahblah blahblahblahblahblah blahblah blah");
		EditAction e = new EditAction(testTask);
		e.createAndShowGUI();
	}
}
