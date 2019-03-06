import java.awt.*;
import java.awt.event.*;
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
		panel.setSize(frameWidth, frameHeight);
		
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
