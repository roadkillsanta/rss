package podcast.RSSFORMATTER2;

import java.awt.Font;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AddEpisode extends JFrame {
	
	private JPanel panel;
	private App app; 
	private static JTextField title;
	private static JTextField episodeN;
	private static JLabel lblEpisode;
	private static JTextField seasonN;
	private static JLabel lblSeason;
	private static JTextField summary;
	private static JLabel lblEpisodeSummary;
	private static JButton b;
	private static String MP3Path;
	private static JLabel lblTitle;
	private static JLabel lblMP3Path;
	private static JButton btnBack;
	private static JLabel lblXMLPath;
	private static String XMLPath;
	private static boolean validMP3;
	private static boolean validXML; // when both of these are true, the "Generate" button will enable

	public AddEpisode() {
		app = new App();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 600);
		setResizable(false);
		
		panel = new JPanel();
		panel.setVisible(true);
		panel.setLayout(null);
		getContentPane().add(panel);
		
		lblTitle = new JLabel("Episode Title:");
		lblTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblTitle.setBounds(6, 6, 136, 49);
		panel.add(lblTitle);
		
		title = new JTextField();
		title.setFont(new Font("Dialog", Font.PLAIN, 20));
		title.setBounds(154, 6, 220, 49);
		title.setColumns(10);
		panel.add(title);
		
		episodeN = new JTextField();
		episodeN.setFont(new Font("Dialog", Font.PLAIN, 20));
		episodeN.setColumns(10);
		episodeN.setBounds(154, 67, 220, 49);
		panel.add(episodeN);
		
		lblEpisode = new JLabel("Episode #:");
		lblEpisode.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblEpisode.setBounds(6, 67, 136, 49);
		panel.add(lblEpisode);
		
		seasonN = new JTextField();
		seasonN.setFont(new Font("Dialog", Font.PLAIN, 20));
		seasonN.setColumns(10);
		seasonN.setBounds(154, 128, 220, 49);
		panel.add(seasonN);
		
		lblSeason = new JLabel("Season #:");
		lblSeason.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblSeason.setBounds(6, 128, 136, 49);
		panel.add(lblSeason);
		
		summary = new JTextField();
		summary.setFont(new Font("Dialog", Font.PLAIN, 20));
		summary.setColumns(10);
		summary.setBounds(195, 189, 220, 49);
		panel.add(summary);
		
		lblEpisodeSummary = new JLabel("Episode Summary:");
		lblEpisodeSummary.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblEpisodeSummary.setBounds(6, 189, 178, 49);
		panel.add(lblEpisodeSummary);
		
		b = new JButton("Generate");
		b.setBounds(31, 372, 117, 29);
		b.setEnabled(false);
		panel.add(b);
		b.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){  
		    	try {
					AddEpisodeFormatter.format(new File(MP3Path), AddEpisodeFormatter.fileDuration(new File(MP3Path)), Long.toString(new File(MP3Path).length()), title.getText(), episodeN.getText(), seasonN.getText(), summary.getText(), XMLPath);
				} catch (Exception e1) { // Prior verification ensures that neither IOException nor AuidoFormatException will be thrown
					e1.printStackTrace();
				}
		           
		    }  
		    });
		
		btnBack = new JButton("Back");
		btnBack.setBounds(161, 372, 117, 29);
		panel.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				app.setLocation(getLocation());
				app.setVisible(true);
				setVisible(false);
				dispose();
				
			}
		});
		
		
		
		lblMP3Path = new JLabel("Drag MP3 File Here");
		lblMP3Path.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblMP3Path.setBounds(6, 250, 838, 49);
		panel.add(lblMP3Path);	
		lblMP3Path.setDropTarget(new DropTarget() {
	        public synchronized void drop(DropTargetDropEvent evt) {
	            try {
	                evt.acceptDrop(DnDConstants.ACTION_COPY);
	                @SuppressWarnings("unchecked")
					List<File> droppedFiles = (List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
	                for (File file : droppedFiles) {
	                    MP3Path = file.getAbsolutePath();  
	                    if (MP3Path.endsWith(".mp3")) { // if the added file is an MP3
	    	                lblMP3Path.setText("MP3 Added"); // change the label
	    	                validMP3 = true; // change the boolean to true
	    	                if (validMP3 = true && validXML == true) { // check if both valid MP3 and XML files have been added
								b.setEnabled(true); // if so, enable the "Generate" Button
							} // end of inner if
						} // end of outer if 
	                    else { // if not
	                    	lblMP3Path.setText("Please add an MP3 file"); // prompt the user to and an MP3 file
	                    	validMP3 = false; // change the boolean
	                    	b.setEnabled(false); // disable the "Generate" button
						} // end of else
	                } // end of for-loop
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	        }
	    });
		
		lblXMLPath = new JLabel("Drag XML File Here");
		lblXMLPath.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblXMLPath.setBounds(6, 311, 270, 49);
		panel.add(lblXMLPath);
		lblXMLPath.setDropTarget(new DropTarget() {
	        public synchronized void drop(DropTargetDropEvent evt) {
	            try {
	                evt.acceptDrop(DnDConstants.ACTION_COPY);
	                @SuppressWarnings("unchecked")
					List<File> droppedFiles = (List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
	                for (File file : droppedFiles) {
	                    XMLPath = file.getAbsolutePath();  
	                    if (XMLPath.endsWith(".xml")) {
	    	                lblXMLPath.setText("XML Added"); 
	    	                validXML = true;
	    	                if (validMP3 = true && validXML == true) {
								b.setEnabled(true);
							} // end of inner if
						} // end of outer if
	                    else {
	                    	lblXMLPath.setText("Please add an XML file");
	                    	validXML = false;
	                    	b.setEnabled(false);
						} // end of else
	                } // end of for-loop
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	        }
	    });
	}
	}


