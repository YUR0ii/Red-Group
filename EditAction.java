import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

import javax.swing.*;

public class EditAction implements ActionListener{
	public void createAndShowGUI() {		
		String itemName = null;
		String commentText = null;
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(400,500));
		frame.setTitle("Edit Item");
		
		String[] radiobnames = new String[] {"Urgent", "Current", "Eventual", "Inactive"};
		JPanel radioButtonPanel = new JPanel(new FlowLayout());
		ButtonGroup group = new ButtonGroup();
		for (String s:radiobnames) {
			JRadioButton radiob = new JRadioButton(s);
			group.add(radiob);
			radioButtonPanel.add(radiob);
		}
		
		String[] checkbnames = new String[] {"Urgent", "Current", "Eventual"};
		JPanel checkBoxPanel = new JPanel();
		checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
		for(String s:checkbnames) {
			JCheckBox checkb = new JCheckBox(s);
			checkBoxPanel.add(checkb);
		}
		
		JPanel spinnerPanel = new JPanel();
		spinnerPanel.setLayout(new BoxLayout(spinnerPanel, BoxLayout.Y_AXIS));
		for (int i = 0; i < 4; i++) {
			
		}
		
		JLabel displayItemName = new JLabel("Edit " + itemName);
		Font itemNameFont = new Font("Arial", Font.PLAIN, 18);
		displayItemName.setFont(itemNameFont);
		JLabel displayCommentTitle = new JLabel("Comments");
		JTextField commentField = new JTextField(commentText);
		commentField.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(commentField);
		scrollPane.setPreferredSize(new Dimension(400, 200));
		
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.getContentPane().add(displayItemName);
		displayItemName.setAlignmentX((float) 0.5);
		frame.getContentPane().add(radioButtonPanel);
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
	
	//make constructor that accepts task as a parameter ahshsafsaf
	
	public static void main(String[] args) {
		EditAction e = new EditAction();
		e.createAndShowGUI();
	}
}
