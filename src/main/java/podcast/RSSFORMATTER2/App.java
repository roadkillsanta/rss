package podcast.RSSFORMATTER2;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

@SuppressWarnings("serial")
public class App extends JFrame{

	private JPanel panel;
	private JLabel frameTitle;
	private JButton bNewPodcast;
	private JButton bAddEpisode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 900, 600);
//		System.out.println(getBounds());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		//DO NOT SET THE FRAME LAYOUT IF YOU'RE PUTTING EVERYTHING ON THE PANEL!!!!!!!
		//frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setVisible(true);
		panel.setLayout(null);
		getContentPane().add(panel);
		
		frameTitle = new JLabel("Podcast Generator");
		frameTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		frameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		frameTitle.setBounds(305, 6, 290, 50);
		panel.add(frameTitle);
		
		bAddEpisode = new JButton("Click to add episode");
		bAddEpisode.setBounds(167, 209, 193, 148);
		panel.add(bAddEpisode);
		bAddEpisode.setFocusable(false); // This fixes the highliting thing when the program launches, yuo should add an actionlistener on hover
										// to make it true
										// Note that this will remove the "tabbing" functionality of switching between components.
		bAddEpisode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddEpisode ae = new AddEpisode();
				ae.setLocation(getLocation());
				ae.setVisible(true);
				setVisible(false);

//				panel.removeAll();
//				TestPanel(this, panel);	
//				dispose();
				
			}
		});
		
		bNewPodcast = new JButton("Click for new podcast");
		bNewPodcast.setBounds(525, 209, 193, 148);
		panel.add(bNewPodcast);
		bNewPodcast.setFocusable(false); // see line 70
		bNewPodcast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewPodcast np = new NewPodcast();
				np.setLocation(getLocation());
				np.setVisible(true);
				setVisible(false);
			}
		});
		
	}
}
