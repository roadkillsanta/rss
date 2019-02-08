package podcast.RSSFORMATTER2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class NewPodcastFormatter {

	public static void generate (String title, String language, String author, String email, String subdomain, String explicit, String imageName, String description, ArrayList categories) throws IOException {
		String desktop = System.getProperty("user.home")+"/Desktop/";
		File f = new File(desktop+"RSS.xml"); //user.home = universal desktop
		FileWriter fw = new FileWriter(f);
		String categorySection="";
		if(categories.size()>3) {
			for(int i=0; i<3; i++) {
			categorySection=categorySection+"<itunes:category text=\""+categories.get(i)+">\n" +  
			"</itunes:category>\n";
			}
		}
		else {
			for(int i=0; i<categories.size(); i++) {
				categorySection=categorySection+"<itunes:category text=\""+categories.get(i)+">\n" +  
				"</itunes:category>\n";
			}
		}
		
		String write="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
				"<rss version=\"2.0\" xmlns:itunes=\"http://www.itunes.com/dtds/podcast-1.0.dtd\" xmlns:content=\"http://purl.org/rss/1.0/modules/content/\">\n" + 
				"<channel>\n" + 
				"<title>"+title+"</title>\n" + 
				"<link></link>\n" + 
				"<language>"+language+"</language>\n" + 
				"<itunes:subtitle></itunes:subtitle>\n" + 
				"<itunes:author>"+author+"</itunes:author>\n" + 
				"<itunes:summary>"+description+"</itunes:summary>\n" + 
				"<description>"+description+"</description>\n" + 
				"<itunes:type>serial</itunes:type>\n" + 
				"<itunes:owner>\n" + 
				"<itunes:name>"+author+"</itunes:name>\n" + 
				"<itunes:email>"+email+"</itunes:email>\n" + 
				"</itunes:owner>\n" + 
				"<itunes:image href=\"https://"+subdomain+".netlify.com/"+imageName+"\"/>\n"+ 
				categorySection+
				"<itunes:explicit>"+explicit+"</itunes:explicit>\n\n" +
				"<!--Add new episodes here-->\n" +
				"</channel>\n"+
				"</rss>";
		// image must be between 1400x1400 and 3000x3000
		fw.write(write);
		fw.close();
	}
}
