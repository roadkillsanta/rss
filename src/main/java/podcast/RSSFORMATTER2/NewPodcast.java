package podcast.RSSFORMATTER2;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class NewPodcast extends JFrame {

	private JPanel panel;
	private App app; 
	private JTextField podcastTitle;
	private JLabel lblAuthor;
	private JTextField author;
	private JTextField description; // ADD LOGIC TO REMOVE SPECIAL CHARACTERS SUCH AS " ' " and " & ", etc List here: http://xml.silmaril.ie/specials.html
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
	private String imageName;

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
		HashMap<String, String> categories=new HashMap<String, String>();
		categories.put("Arts","Arts");
		categories.put("Business","Business");
		categories.put("Comedy","Comedy");
		categories.put("Education","Education");
		categories.put("Games & Hobbies","Games &amp; Hobbies");
		categories.put("Government & Organizations","Government &amp; Organizations");
		categories.put("Health","Health");
		categories.put("Kids & Family","Kids &amp; Family");
		categories.put("Music","Music");
		categories.put("News & Politics","News &amp; Politics");
		categories.put("Religion & Spirituality","Religion &amp; Spirituality");
		categories.put("Science & Medicine","Science &amp; Medicine");
		categories.put("Society & Culture","Society &amp; Culture");
		categories.put("Sports & Recreation","Sports &amp; Recreation");
		categories.put("Technology","Technology");
		categories.put("TV & Film","TV &amp; Film");
		String[] display= {
				"Arts",
				"Business",
				"Comedy",
				"Education",
				"Games & Hobbies",
				"Government & Organizations",
				"Health",
				"Kids & Family",
				"Music",
				"News & Politics",
				"Religion & Spirituality",
				"Science & Medicine",
				"Society & Culture",
				"Sports & Recreation",
				"Technology",
				"TV & Film",
		};
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
		lblImage.setBounds(417, 67, 477, 49);
		panel.add(lblImage);
		lblImage.setDropTarget(new DropTarget() {
	        public synchronized void drop(DropTargetDropEvent evt) {
	            try {
	                evt.acceptDrop(DnDConstants.ACTION_COPY);
	                @SuppressWarnings("unchecked")
					List<File> droppedFiles = (List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
	                BufferedImage bi; // exists for size analysis of image
	               
	                for (File file : droppedFiles) { // the for-loop allows for the continual addition of images
	                    imageName = file.getName();   
	                    bi = ImageIO.read(file);
	                    if (bi.getWidth() >= 1400 & bi.getHeight() >= 1400 && bi.getWidth() <= 3000 && bi.getHeight() <= 3000) {
	                    	// if the image is greater than or equal to 1400x1400 and less than or equal to 3000x3000
	                    	lblImage.setFont(new Font("Lucida Grande", Font.PLAIN, 20)); // sets font size to 20 in case it was changed by else block
	    	                lblImage.setText("Image Added"); 
	    	                btnGenerate.setEnabled(true);
	                    } // end of if 
	                    else {
	                    	lblImage.setFont(new Font("Lucida Grande", Font.PLAIN, 16)); // reduces font size from 20 to 16 for long message
	                    	lblImage.setText("Your image must be between 1400x1400 and 3000x3000");
						} // end of else
	                } // end of for loop
	            } catch (Exception ex) {
	                lblImage.setText("That file is not a valid image");
	            }
	        }
	    }); // end of drop target
		
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
		
		String[] languages = {"English", "Spanish", "French", "Chinese", "Arabic", "Hindi", "Russian", "Japenese"};
		list = new JList<String>(languages);
		list.setBounds(600, 147, 100, 60);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.add(list);
		
		pane = new JScrollPane(list);
		pane.setBounds(600, 147, 100, 60);
		panel.add(pane);
		list.addListSelectionListener(new ListSelectionListener() {			
			public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    List<String> selectedValuesList = list.getSelectedValuesList();
//                    System.out.println(selectedValuesList.toString().replaceAll("\\[", "").replaceAll("\\]", ""));
                    languageSelected = selectedValuesList.toString().replaceAll("\\[", "").replaceAll("\\]", "");
                    
                    switch (languageSelected) {
					case "English":
						languageSelected = "en";
						break;						
					case "Spanish":
						languageSelected = "es";
						break;
					case "French":
						languageSelected = "fr";
						break;
					case "Chinese":
						languageSelected = "zh";
						break;
					case "Arabic":
						languageSelected = "ar";
						break;
					case "Hindi":
						languageSelected = "hi";
						break;
					case "Russian":
						languageSelected = "ru";
						break;
					case "Japanese":
						languageSelected = "ja";
						break;
						
					default:
						break;
					}
                }
            }
				
			});
		
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
		JTextField instruction=new JTextField();
		instruction.setText("Select up to 3 categories for your Podcast");

		instruction.setHorizontalAlignment(JTextField.CENTER);
		instruction.setBounds(530, 250, 320, 32);
		panel.add(instruction);
		
		JList list = new JList(display);
	    list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	    list.setSelectedIndex(0);
	    list.setVisibleRowCount(20);
	    JScrollPane listScrollPane = new JScrollPane(list);
		listScrollPane.setBounds(530, 300, 200, 256);
		
	    panel.add(listScrollPane);
		btnGenerate = new JButton("Generate");
		btnGenerate.setBounds(257, 311, 117, 29);
		btnGenerate.setEnabled(false);
		panel.add(btnGenerate);
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
						if (rdbtnYes.isSelected()) {
							explicit = "yes";
						} 
						else {
							explicit = "no";
						}
						ArrayList categori=new ArrayList();
						int[] selectedIx = list.getSelectedIndices();
					    // Get all the selected items using the indices
					    for (int i = 0; i < selectedIx.length; i++) {
					    	categori.add(categories.get(list.getModel().getElementAt(selectedIx[i])));
					    }
						NewPodcastFormatter.generate(podcastTitle.getText(), languageSelected, author.getText(), email.getText(), subdomain.getText(), explicit, imageName, description.getText(), categori);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
	}
}
