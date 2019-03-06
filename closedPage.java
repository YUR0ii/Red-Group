import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;


public class closedPage extends MainPage {
	closedPage(){
		JFrame closeFrame=new JFrame();
		JPanel panel = new JPanel();
		JScrollPane scrollPane = new JScrollPane(panel);

        for (int i = 0; i < 10; i++) {
            panel.add(new JButton("Hello-" + i));
        }

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scrollPane.setBounds(0, 0, 500, 400);
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500, 400));
        contentPane.add(scrollPane);
        closeFrame.setContentPane(contentPane);
        closeFrame.pack();
        closeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        closeFrame.setVisible(true);
	}
}