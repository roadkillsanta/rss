package podcast.RSSFORMATTER2;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AddEpisodeFormatter {

	public static void format (File mp3File, String fileDurationFinal, String fileSize, String episodeTitleFinal, String episodeNumberFinal, String episodeSeasonFinal, String episodeSummaryFinal, String xmlPath) throws UnsupportedAudioFileException, IOException {
		
		String date=new Date().toString();
		date = date.substring(0,3)+","+date.substring(3);
		date = date.substring(0,date.lastIndexOf(":")+3);
		
		String xmlFileText = readFile(xmlPath);
		System.out.println(xmlFileText);

		String netlifySubdomain = xmlFileText.substring(xmlFileText.indexOf("<itunes:image href=\"https://")+28); // gets Netlify subdomain from XML file
		netlifySubdomain = netlifySubdomain.substring(0, netlifySubdomain.indexOf("."));
		System.out.println("Netlify Subdomain: " + netlifySubdomain);
		
		String author = xmlFileText.substring(xmlFileText.indexOf("<itunes:author>")+15); // gets author(s) from XML file
		author = author.substring(0, author.indexOf("</itunes:author>"));
		System.out.println("Author(s): " + author);
		
		String explicit = xmlFileText.substring(xmlFileText.indexOf("<itunes:explicit>")+17); // gets whether the podcast is explicit or not
		explicit = explicit.substring(0, explicit.indexOf("</itunes:explicit>"));
		System.out.println("Explicit?: " + explicit);

		
		String output = "<item>\n" + 
				"<itunes:episodeType>full</itunes:episodeType>\n" + 
				"<itunes:title>"+episodeTitleFinal+"</itunes:title>\n" + 
				"<itunes:episode>"+episodeNumberFinal+"</itunes:episode>\n" + 
				"<itunes:season>"+episodeSeasonFinal+"</itunes:season>\n" + 
				"<title>"+episodeTitleFinal+"</title>\n" + 
				"<itunes:author>"+author+"</itunes:author>\n" + 
				"<itunes:subtitle>"+episodeSummaryFinal+"</itunes:subtitle>\n" + 
				"<itunes:summary>"+episodeSummaryFinal+"</itunes:summary>\n" + 
				"<description>"+episodeSummaryFinal+"</description>\n" + 
				"<content:encoded>"+episodeSummaryFinal+"</content:encoded>\n" + 
				"<enclosure length=\""+fileSize+"\" type=\"audio/mpeg\" url=\"https://"+netlifySubdomain+".netlify.com/"+mp3File.getName()+"\"/>\n" + 
				"<guid>https://"+netlifySubdomain+".netlify.com/"+mp3File.getName()+"</guid>\n" +
				"<pubDate>"+date+" +0000</pubDate>\n" + 
				"<itunes:duration>"+fileDurationFinal+"</itunes:duration>\n" + 
				"<itunes:explicit>"+explicit+"</itunes:explicit>\n" + 
				"</item>\n";
		
		xmlFileText = xmlFileText.substring(0, xmlFileText.indexOf("<!--Add new episodes here-->")) + output + "\n" + xmlFileText.substring(xmlFileText.indexOf("<!--Add new episodes here-->"));
		// this line gets the substring from the beginning of the base XML to to the comment, then adds the episode code, then subtrings from the comment to the end of the file
		
		try { // this code wrties the XML w/ the added episode to the file
			FileWriter fw = new FileWriter(xmlPath);
			fw.write(xmlFileText);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static String fileDuration(File file2) throws UnsupportedAudioFileException, IOException {
		String min, sec;
		
	    AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file2);
	    if (fileFormat instanceof AudioFileFormat) {
	        Map<?, ?> properties = ((AudioFileFormat) fileFormat).properties();
	        String key = "duration";
	        //link to all keys: https://docs.oracle.cm/javase/7/docs/api/javax/sound/sampled/AudioFileFormat.html
	        
	        Long microseconds = (Long) properties.get(key);
	        int mili = (int) (microseconds / 1000);
	        sec = Integer.toString(((mili / 1000) % 60));
	        min = Integer.toString((mili / 1000) / 60);
	        
	    } else {
	        throw new UnsupportedAudioFileException();
	    }
	    
	    return min+":"+sec;

	}
	
	
	public static String readFile(String pathname) throws IOException {

	    File file = new File(pathname);
	    StringBuilder fileContents = new StringBuilder((int)file.length());        

	    try (Scanner scanner = new Scanner(file)) {
	        while(scanner.hasNextLine()) {
	            fileContents.append(scanner.nextLine() + System.lineSeparator());
	        }
	        return fileContents.toString();
	    }
	}
}
