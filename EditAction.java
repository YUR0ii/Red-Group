import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

import javax.swing.*;

public class EditAction implements ActionListener {
	Task editingTask;
	String commentText = "No comment. No comment. No comment. No comment. "
			+ "\n No comment. No comment. "
			+ "\n No comment. No comment. "
			+ "\n No comment. No comment. "
			+ "\n No comment. No comment. "
			+ "\n No comment. No comment. "
			+ "\n No comment. No comment. "
			+ "\n No comment. No comment. "
			+ "\n No comment. No comment. "
			+ "\n No comment. No comment. "
			+ "\n No comment. No comment. "
			+ "\n No comment. No comment. "
			+ "\n No comment. No comment. "
			+ "\n No comment. No comment. "
			+ "\n No comment. No comment. ";

	public EditAction(Task task) {
		editingTask = task;
	}
	
	public void chopStrings(String s) {
		StringBuilder sb = new StringBuilder(s);
		// make long strings fit inside text box
		commentText = sb.toString();
	}

	public void createAndShowGUI() {

		// create JFrame
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(400, 500));
		frame.setTitle("Edit Item");

		// create and add radio buttons
		String[] radiobnames = new String[] { "Urgent", "Current", "Eventual", "Inactive" };
		JPanel radioButtonPanel = new JPanel(new FlowLayout());
		ButtonGroup group = new ButtonGroup();
		for (String s : radiobnames) {
			JRadioButton radiob = new JRadioButton(s);
			group.add(radiob);
			radioButtonPanel.add(radiob);
		}

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

		// create JLabels and JScrollPane for comment display
		JLabel displayItemName = new JLabel("Edit " + editingTask.getName());
		Font itemNameFont = new Font("Arial", Font.PLAIN, 18);
		displayItemName.setFont(itemNameFont);
		JLabel displayCommentTitle = new JLabel("Comments");
		JTextArea commentField = new JTextArea(commentText);
		commentField.setPreferredSize(new Dimension(350, 200));
		commentField.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(commentField);
		scrollPane.setPreferredSize(new Dimension(350, 200));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

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
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	// make constructor that accepts task as a parameter
	public static void main(String[] args) {
		Task testTask = new Task("testing");
		EditAction e = new EditAction(testTask);
		e.createAndShowGUI();
	}
}
