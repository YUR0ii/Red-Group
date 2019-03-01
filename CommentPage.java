//UNFINISHED!!!


import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CommentPage {

	private String currentText;
	
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JButton commit = new JButton("Commit");
	private JButton delete = new JButton("Delete");
	private JTextField textBox = new JTextField();
	
	CommentPage(String CT){
		currentText = CT;
		
		//frame = F;
		//panel = P;
		frame.add(panel);
		panel.add(textBox);
		textBox.setText(currentText);
		
		frame.setSize(750,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		frame.setVisible(true);
		initiateComponents();
		
	}
	
	private void initiateComponents() {
		
		textBox.addKeyListener(new KeyListener(){

			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}
			
			public void keyTyped(KeyEvent arg0) {
				
				
			}
		});
			
		commit.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  		
				
				frame.dispose();
			
			}  
		});
		
		delete.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  		
				
				currentText = "";
				frame.dispose();
				
			}  
		});

	}
	
	
	
}


