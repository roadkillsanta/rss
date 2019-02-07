package podcast.RSSFORMATTER2;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

public class PodcastCategory {
	public static JTextField instruction;
	public static void getCategories(JFrame frame){
		String[] arts = {"Design","Fashion &amp; Beauty","Food","Literature","Performing Arts","Visual Arts"};
		String[] business={"Business News","Careers","Investing","Management &amp; Marketing","Shopping"};
		//Comedy
		String[] education={"Educational Technology","Higher Education","K-12","Language Courses","Training"};
		String[] gamesHobbies={"Automotive","Aviation","Hobbies","Other Games","Video Games"};
		String[] governmentOrganizations={"Local","National","Non-Profit","Regional"};
		String[] Health={"Alternative Health","Fitness &amp; Nutrition","Self-Help","Sexuality"};
		//Kids &amp; Family
		//Music
		//News &amp; Politics
		String[] religionSpirituality={"Buddhism","Christianity","Hinduism","Islam","Judaism","Other","Spirituality"};
		String[] scienceMedicine={"Medicine","Natural Sciences","Social Sciences"};
		String[] societyCulture={"History","Personal Journals","Philosophy","Places &amp; Travel"};
		String[] sportsRecreation={"Amateur","College &amp; High School","Outdoor","Professional"};
		String[] technology={"Gadgets","Tech News","Podcasting","Software How-To"};
		//	TV &amp; Film
		/*String[] display={
"Arts",
"Design",
"Fashion & Beauty",
"Food",
"Literature",
"Performing Arts",
"Visual Arts",
"\n",
"Business",
"Business News",
"Careers",
"Investing",
"Management & Marketing",
"Shopping",
"\n",
"Comedy",
"Education",
"Educational Technology",
"Higher Education",
"K-12",
"Language Courses",
"Training",
"\n",
"Games & Hobbies",
"Automotive",
"Aviation",
"Hobbies",
"Other Games",
"Video Games",
"\n",
"Government & Organizations",
"Local",
"National",
"Non-Profit",
"Regional",
"\n",
"Health",
"Alternative Health",
"Fitness & Nutrition",
"Self-Help",
"Sexuality",
"\n",
"Kids & Family",
"Music",
"News & Politics",
"Religion & Spirituality",
"Buddhism",
"Christianity",
"Hinduism",
"Islam",
"Judaism",
"Other",
"Spirituality",
"\n",
"Science & Medicine",
"Medicine",
"Natural Sciences",
"Social Sciences",
"\n",
"Society & Culture",
"History",
"Personal Journals",
"Philosophy",
"Places & Travel",
"\n",
"Sports & Recreation",
"Amateur",
"College & High School",
"Outdoor",
"Professional",
"\n",
"Technology",
"Gadgets",
"Tech News",
"Podcasting",
"Software How-To",
"\n",
"TV & Film"};*/
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel.add(toolBar, BorderLayout.SOUTH);
		
		Component glue = Box.createGlue();
		toolBar.add(glue);
		
		JButton btnNext = new JButton("Done");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
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
		panel.add(instruction, BorderLayout.NORTH);
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		toolBar.add(horizontalStrut_2);
		
		@SuppressWarnings("rawtypes")
		JList list = new JList(null);
	    list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	    list.setSelectedIndex(0);
	    list.setVisibleRowCount(88);
	    JScrollPane listScrollPane = new JScrollPane(list);
		
	    panel.add(listScrollPane);
	    
		Component horizontalStrut = Box.createHorizontalStrut(20);
		toolBar.add(horizontalStrut);
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.pack();
	}
	public static void main(String args[]) {
		JFrame frame=new JFrame();
		frame.setPreferredSize(new Dimension(1280,720));
		getCategories(frame);
		frame.setVisible(true);
	}
}