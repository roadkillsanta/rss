package podcast.RSSFORMATTER2;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JTextArea;

public class AddEpisodeFormatter {

	public static void exist (JTextArea output, File file, String fileDurationFinal, String fileSize, String episodeTitleFinal, String episodeNumberFinal, String episodeSeasonFinal, String episodeSummaryFinal) throws UnsupportedAudioFileException, IOException {
		
		String date=new Date().toString();
		date = date.substring(0,3)+","+date.substring(3);
		date = date.substring(0,date.lastIndexOf(":")+3);

		output.setText("<item>\n" + 
				"<itunes:episodeType>full</itunes:episodeType>\n" + 
				"<itunes:title>"+episodeTitleFinal+"</itunes:title>\n" + 
				"<itunes:episode>"+episodeNumberFinal+"</itunes:episode>\n" + 
				"<itunes:season>"+episodeSeasonFinal+"</itunes:season>\n" + 
				"<title>"+episodeTitleFinal+"</title>\n" + 
				"<itunes:author>Carrie Hartman &amp; Estie Miller</itunes:author>\n" + 
				"<itunes:subtitle>"+episodeSummaryFinal+"</itunes:subtitle>\n" + 
				"<itunes:summary>"+episodeSummaryFinal+"</itunes:summary>\n" + 
				"<description>"+episodeSummaryFinal+"</description>\n" + 
				"<content:encoded>"+episodeSummaryFinal+"</content:encoded>\n" + 
				"<enclosure length=\""+fileSize+"\" type=\"audio/mpeg\" url=\"https://worsemomthanyou.netlify.com/"+file.getName()+"\"/>\n" + 
				"<guid>https://worsemomthanyou.netlify.com/S1E2-snapchatJunkieMom.mp3</guid>\n" + 
				"<pubDate>"+date+" +0000</pubDate>\n" + 
				"<itunes:duration>"+fileDurationFinal+"</itunes:duration>\n" + 
				"<itunes:explicit>yes</itunes:explicit>\n" + 
				"</item>");
		

		
//		System.out.println(fileDuration(file));
//		System.out.println(file.length());
//		System.out.println(ld);
		
	}
	public static String fileDuration(File file2) throws UnsupportedAudioFileException, IOException {
		String min, sec;
		
	    AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file2);
	    if (fileFormat instanceof AudioFileFormat) {
	        Map<?, ?> properties = ((AudioFileFormat) fileFormat).properties();
	        String key = "duration";
	        //link to all keys: https://docs.oracle.com/javase/7/docs/api/javax/sound/sampled/AudioFileFormat.html
	        
	        Long microseconds = (Long) properties.get(key);
	        int mili = (int) (microseconds / 1000);
	        sec = Integer.toString(((mili / 1000) % 60));
	        min = Integer.toString((mili / 1000) / 60);
	        //System.out.println("time = " + min + ":" + sec);
	        
	    } else {
	        throw new UnsupportedAudioFileException();
	    }
	    
	    return min+":"+sec;

	}
}
