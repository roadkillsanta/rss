package podcast.RSSFORMATTER2;

import java.awt.Font;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
	private static String filePath;
	private static JTextArea output;
	private static JLabel lblTitle;
	private static JLabel lblFilePath;
	private static JScrollPane scrollPane;
	private static JButton btnBack;

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
		b.setBounds(55, 327, 117, 29);
		b.setEnabled(false);
		panel.add(b);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(184, 327, 117, 29);
		panel.add(btnBack);
		
		output = new JTextArea();
		output.setEditable(false);
		output.setBounds(6, 367, 476, 183);
		panel.add(output);
		
		lblFilePath = new JLabel("Drag MP3 File Here");
		lblFilePath.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblFilePath.setBounds(6, 250, 890, 49);
		panel.add(lblFilePath);
		
		scrollPane = new JScrollPane(output);
		scrollPane.setBounds(6, 367, 476, 183);
		panel.add(scrollPane);

		
		b.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){  
		    	try {
					AddEpisodeFormatter.exist(output, new File(filePath), AddEpisodeFormatter.fileDuration(new File(filePath)), Long.toString(new File(filePath).length()), title.getText(), episodeN.getText(), seasonN.getText(), summary.getText() );
				} catch (UnsupportedAudioFileException e1) {
					lblFilePath.setText("The file type you have added is not supported. Please add a different file and try again.");
					b.setEnabled(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		           
		    }  
		    });
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				app.setLocation(getLocation());
				app.setVisible(true);
				setVisible(false);
				dispose();
				
			}
		});
		
		lblFilePath.setDropTarget(new DropTarget() {
	        public synchronized void drop(DropTargetDropEvent evt) {
	            try {
	                evt.acceptDrop(DnDConstants.ACTION_COPY);
	                @SuppressWarnings("unchecked")
					List<File> droppedFiles = (List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
	                for (File file : droppedFiles) {
	                    filePath = file.getAbsolutePath();   
	                }
	                lblFilePath.setText("MP3 Added"); 
	                b.setEnabled(true);
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	        }
	    });
	}

	}


