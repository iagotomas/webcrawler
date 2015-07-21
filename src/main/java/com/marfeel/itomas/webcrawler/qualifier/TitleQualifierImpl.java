package com.marfeel.itomas.webcrawler.qualifier;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Qualifier implementation which looks up the title tag to determine whether the uri qualifies
 * @author iago
 *
 */
public class TitleQualifierImpl implements Qualifier {

	
	public boolean qualify(String html) {
		Document doc = Jsoup.parse(html);
		Elements titles = doc.getElementsByTag("title");
		// assume only 1 title per page
		Element title = titles.get(0);
		String value = title.text().toLowerCase();
		return value!=null&&(value.contains("news")||value.contains("noticias"));
	}

	

}
