package podcast.RSSFORMATTER2;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class NewPodcast extends JFrame {

	private JPanel panel;
	private App app; 
	private JTextField podcastTitle;
	private JLabel lblAuthor;
	private JTextField author;
	private JTextField description;
	private JLabel lblDescription;
	private JLabel lblEmail;
	private JTextField email;
	private JLabel lblExplicit;
	private JRadioButton rdbtnYes;
	private JRadioButton rdBtnNo;
	private JLabel lblImage;
	private JTextField subdomain;
	private JLabel lblNetlifySubdomain;
	private JLabel lblLanguage;
	private JLabel lblCategories;
	private JButton btnBack;
	private JList<String> list;
	private JScrollPane pane;
	private String languageSelected;
	private JButton btnGenerate;
	private String explicit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewPodcast frame = new NewPodcast();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewPodcast() {
		
		app = new App();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 600);
		setResizable(false);
		
		panel = new JPanel();
		panel.setVisible(true);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblPodcastTitle = new JLabel("Podcast Title:");
		lblPodcastTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblPodcastTitle.setBounds(6, 6, 136, 49);
		panel.add(lblPodcastTitle);
		
		podcastTitle = new JTextField();
		podcastTitle.setFont(new Font("Dialog", Font.PLAIN, 20));
		podcastTitle.setColumns(10);
		podcastTitle.setBounds(154, 6, 220, 49);
		panel.add(podcastTitle);
		
		lblAuthor = new JLabel("Author(s):");
		lblAuthor.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblAuthor.setBounds(6, 67, 136, 49);
		panel.add(lblAuthor);
		
		author = new JTextField();
		author.setFont(new Font("Dialog", Font.PLAIN, 20));
		author.setColumns(10);
		author.setBounds(154, 67, 220, 49);
		panel.add(author);
		
		description = new JTextField();
		description.setFont(new Font("Dialog", Font.PLAIN, 20));
		description.setColumns(10);
		description.setBounds(154, 128, 220, 49);
		panel.add(description);
		
		lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblDescription.setBounds(6, 128, 136, 49);
		panel.add(lblDescription);
		
		lblEmail = new JLabel("e-mail:");
		lblEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblEmail.setBounds(6, 250, 136, 49);
		panel.add(lblEmail);
		
		email = new JTextField();
		email.setFont(new Font("Dialog", Font.PLAIN, 20));
		email.setColumns(10);
		email.setBounds(154, 250, 220, 49);
		panel.add(email);
		
		lblExplicit = new JLabel("Explicit:");
		lblExplicit.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblExplicit.setBounds(6, 189, 136, 49);
		panel.add(lblExplicit);
		
		lblImage = new JLabel("Drag Podcast Image Here");
		lblImage.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblImage.setBounds(417, 67, 247, 49);
		panel.add(lblImage);
		
		subdomain = new JTextField();
		subdomain.setFont(new Font("Dialog", Font.PLAIN, 20));
		subdomain.setColumns(10);
		subdomain.setBounds(638, 6, 220, 49);
		panel.add(subdomain);
		
		lblNetlifySubdomain = new JLabel("Netlify Subdomain:");
		lblNetlifySubdomain.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNetlifySubdomain.setBounds(417, 6, 184, 49);
		panel.add(lblNetlifySubdomain);
		
		lblLanguage = new JLabel("Language:");
		lblLanguage.setToolTipText("");
		lblLanguage.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblLanguage.setBounds(417, 128, 103, 49);
		panel.add(lblLanguage);
		
		lblCategories = new JLabel("Categories:");
		lblCategories.setToolTipText("");
		lblCategories.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblCategories.setBounds(417, 248, 116, 49);
		panel.add(lblCategories);
		
		String[] languages = {"English", "Spanish", "French", "Chinese", "Arabic", "Hindi", "Russian", "Japanese"};
		String[] languagesShort = {"en", "es", "fr", "zh" , "ar", "hi", "ru", "ja"};
		final DefaultComboBoxModel model = new DefaultComboBoxModel(languages);
		list = new JList(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //I added a bunch of stuff around here to make it prettier and to also make it work with the JComboBox as it seems more like an HTML form :D
		JComboBox comboBox = new JComboBox(model);

		comboBox.setBounds(600, 147, 100, 20);
		panel.add(comboBox);
		
		rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.setBounds(154, 204, 54, 23);
		panel.add(rdbtnYes);
		rdbtnYes.addActionListener(new ActionListener() {// when button is clicked, highlights "Yes" and deselects "No"			
			public void actionPerformed(ActionEvent e) { // MAKE THIS SET A STRING TO "yes"
				rdbtnYes.setSelected(true);
				rdBtnNo.setSelected(false);
			}
		});
		
		rdBtnNo = new JRadioButton("No");
		rdBtnNo.setBounds(220, 204, 54, 23);
		panel.add(rdBtnNo);
		rdBtnNo.addActionListener(new ActionListener() { // when button is clicked, highlights "No" and deselects "Yes"
			public void actionPerformed(ActionEvent e) { // MAKE THIS SET A STRING TO "no"
				rdBtnNo.setSelected(true);
				rdbtnYes.setSelected(false);
				
			}
		});
		
		btnBack = new JButton("Back");
		btnBack.setBounds(128, 311, 117, 29);
		panel.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				app.setLocation(getLocation());
				app.setVisible(true);
				setVisible(false);
				dispose();
				
			}
		});
		
		btnGenerate = new JButton("Generate");
		btnGenerate.setBounds(257, 311, 117, 29);
		panel.add(btnGenerate);
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rdBtnNo.isSelected() == false && rdbtnYes.isSelected() == false) {
						//DO SOMETHING!
						System.out.println("SKREET!");
					}
					else {
						if (rdbtnYes.isSelected()) {
							explicit = "yes";
						} 
						else {
							explicit = "no";
						}
						NewEpisodeFormatter.generate(podcastTitle.getText(), languagesShort[comboBox.getSelectedIndex()], author.getText(), email.getText(), subdomain.getText(), explicit);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
	}
}
