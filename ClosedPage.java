import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

public class ClosedPage extends MainPage {
	ClosedPage(){
		JFrame closeFrame=new JFrame();
		JPanel panel = new JPanel();
		JScrollPane scrollPanel = new JScrollPane(panel);
        for (int i = 0; i<completeTasks.size(); i++) {
        	containers.add(new taskContainer(completeTasks.get(i)));
            panel.add(containers.get(i));completeTasks.get(i);
        }
        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scrollPanel.setBounds(0, 0, 500, 400);
        panel.setBackground(new Color(247,232,210));
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500, 400));
        contentPane.add(scrollPanel);
        closeFrame.setContentPane(contentPane);
        closeFrame.pack();
        closeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        closeFrame.setVisible(true);
        scrollPanel.repaint();
	}
}