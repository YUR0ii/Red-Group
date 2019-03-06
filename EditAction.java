import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

import javax.swing.*;

public class EditAction implements ActionListener{
	public void createAndShowGUI() {		
		final int frameWidth = 400;
		final int frameHeight = 600;
		String itemName = null;
		String commentText = null;
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(frameWidth, frameHeight);
		frame.setTitle("Edit Item");
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JRadioButton setUrgentStatus = new JRadioButton("Urgent");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		panel.add(setUrgentStatus, c);
		
		JRadioButton setCurrentStatus = new JRadioButton("Current");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		panel.add(setCurrentStatus, c);
		
		JRadioButton setEventualStatus = new JRadioButton("Eventual");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		panel.add(setEventualStatus, c);
		
		JRadioButton setInactiveStatus = new JRadioButton("Inactive");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		panel.add(setInactiveStatus, c);
		
		JCheckBox urgentDateBox = new JCheckBox("Urgent");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		panel.add(urgentDateBox, c);
		
		SpinnerDateModel spinnerModel = new SpinnerDateModel();
		JSpinner selectUrgentDate = new JSpinner(spinnerModel);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		panel.add(selectUrgentDate, c);
		
		JCheckBox currentDateBox = new JCheckBox("Current");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		panel.add(currentDateBox, c);
		
		JSpinner selectCurrentDate = new JSpinner(spinnerModel);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		panel.add(selectCurrentDate, c);
		
		JCheckBox eventualDateBox = new JCheckBox("Eventual");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		panel.add(eventualDateBox, c);
		
		JSpinner selectEventualDate = new JSpinner(spinnerModel);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		panel.add(selectEventualDate, c);
		
		JLabel displayItemName = new JLabel("Edit " + itemName);
		Font itemNameFont = new Font("Arial", Font.PLAIN, 18);
		displayItemName.setFont(itemNameFont);
		JLabel displayCommentTitle = new JLabel("Comments");
		
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(displayItemName, BorderLayout.PAGE_START);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		EditAction e = new EditAction();
		e.createAndShowGUI();
	}
}
