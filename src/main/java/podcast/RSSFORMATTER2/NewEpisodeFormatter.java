package podcast.RSSFORMATTER2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class NewEpisodeFormatter {

	public static void generate (String title, String language, String author, String email, String subdomain, String explicit) throws IOException {
		File f = new File("//Users/s014894/Desktop/RSS.xml");
		System.out.println(f.createNewFile());
		FileWriter fw = new FileWriter(f);
		
		
		fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
				"<rss version=\"2.0\" xmlns:itunes=\"http://www.itunes.com/dtds/podcast-1.0.dtd\" xmlns:content=\"http://purl.org/rss/1.0/modules/content/\">\n" + 
				"<channel>\n" + 
				"<title>"+title+"</title>\n" + 
				"<link></link>\n" + 
				"<language>"+language+"</language>\n" + 
				"<itunes:subtitle></itunes:subtitle>\n" + 
				"<itunes:author>"+author+"</itunes:author>\n" + 
				"<itunes:summary>Are you the worst mom ever? You have competition. Let’s face it. We&apos;re all screwing it up. Join Carrie Hartman and Estie Miller as they discuss their frequent parenting gaffes and talk to other moms about their humorous fails with food, technology, behavior boundaries, healthcare and old standbys like homework and the tooth fairy. You&apos;ll hear stories of reassuring incompetence that are sure to remind you that there is always a worse mom than you.</itunes:summary>\n" + 
				"<description>Are you the worst mom ever? You have competition. Let’s face it. We&apos;re all screwing it up. Join Carrie Hartman and Estie Miller as they discuss their frequent parenting gaffes and talk to other moms about their humorous fails with food, technology, behavior boundaries, healthcare and old standbys like homework and the tooth fairy. You&apos;ll hear stories of reassuring incompetence that are sure to remind you that there is always a worse mom than you.</description>\n" + 
				"<itunes:type>serial</itunes:type>\n" + 
				"<itunes:owner>\n" + 
				"<itunes:name>"+author+"</itunes:name>\n" + 
				"<itunes:email>"+email+"</itunes:email>\n" + 
				"</itunes:owner>\n" + 
				"<itunes:image href=\"https://"+subdomain+".netlify.com/logo.png\"/>\n" + 
				"<itunes:category text=\"Comedy\">\n" + 
				"</itunes:category>\n" + 
				"<itunes:category text=\"Kids &amp; Family\">\n" + 
				"</itunes:category>\n" + 
				"<itunes:explicit>"+explicit+"</itunes:explicit>");
	}
}
