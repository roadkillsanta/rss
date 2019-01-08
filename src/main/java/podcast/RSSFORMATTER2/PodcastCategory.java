package podcast.RSSFORMATTER2;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class PodcastCategory {
	public JTextField instruction;
	public PodcastCategory(JFrame frame){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel.add(toolBar, BorderLayout.SOUTH);
		
		Component glue = Box.createGlue();
		toolBar.add(glue);
		
		JButton btnNext = new JButton("Next");
		toolBar.add(btnNext);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		toolBar.add(horizontalStrut_1);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        System.exit(1);
		    }
		});
		toolBar.add(btnCancel);
		instruction=new JTextField();
		instruction.setText("Select up to 3 categories for your Podcast");
		instruction.setHorizontalAlignment(JTextField.CENTER);
		panel.add(instruction);
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		toolBar.add(horizontalStrut_2);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		toolBar.add(horizontalStrut);
		
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.pack();
	}
	public static void main(String args[]) {
		JFrame frame=new JFrame();
		new PodcastCategory(frame);
		frame.setVisible(true);
	}
}
