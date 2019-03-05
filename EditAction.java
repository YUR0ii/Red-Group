import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

import javax.swing.*;

public class EditAction extends JPanel implements ActionListener{
	public void createAndShowGUI() {		
		// 2x jlabel 1x jtextpane 4x jradiobutton 3x jcheckbox 3x jspinner 3x jbutton
		final int frameWidth = 400;
		final int frameHeight = 600;
		
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
		JSpinner urgentDateSelect = new JSpinner();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		panel.add(setInactiveStatus, c);
		
		frame.add(panel);
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
