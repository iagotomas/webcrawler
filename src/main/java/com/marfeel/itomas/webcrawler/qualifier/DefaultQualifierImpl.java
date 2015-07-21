package com.marfeel.itomas.webcrawler.qualifier;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Default qualifier implementation. It is used as default implementation to qualify
 * @author iago
 *
 */
public class DefaultQualifierImpl implements Qualifier {

	
	/**
	 * for the purpose of the project and to avoid misunderstandings this method is 
	 * duplicated from {@link TitleQualifierImpl}
	 */
	public boolean qualify(String html) {
		Document doc = Jsoup.parse(html);
		Elements titles = doc.getElementsByTag("title");
		// assume only 1 title per page
		Element title = titles.get(0);
		String value = title.text().toLowerCase();
		return value!=null&&(value.contains("news")||value.contains("noticias"));
	}

	

}
