package com.marfeel.itomas.webcrawler;

import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.mongodb.core.MongoOperations;

import com.marfeel.itomas.webcrawler.qualifier.Qualifier;

public class MongoCheckerServiceImpl implements CheckerService {

	static final Logger LOG = Logger.getLogger(MongoCheckerServiceImpl.class.getName());
	@Autowired
	TaskExecutor crawlerExecutor;
	@Autowired
	MongoOperations mongoTemplate;
	
	private Qualifier qualifier;

	protected TaskExecutor getTaskExecutor() {
		return crawlerExecutor;
	}
	protected void execute(CheckerTask task){
		getTaskExecutor().execute(task);
	}
	public void check(UriEntry entry) {
		execute(new CheckerTask(entry,qualifier,mongoTemplate));
	}

	public void setQualifier(Qualifier qualifier) {
		this.qualifier = qualifier;
	}
	
	public static final class CheckerTask implements Runnable{

		
		private static final String USER_AGENT = "Mozilla";
		private static final int CONNECTION_TIMEOUT = 10000;
		private UriEntry entry;
		private Qualifier qualifier;
		private MongoOperations mongoOperations;

		public CheckerTask(UriEntry entry,Qualifier qualifier,MongoOperations op) {
			this.entry = entry;
			this.qualifier = qualifier;
			this.mongoOperations = op;
		}

		public void run() {
			boolean value = false;
			try {
				entry.setError(false);
				Document doc = Jsoup.connect("http://"+entry.getUri()).userAgent(CheckerTask.USER_AGENT).timeout(CheckerTask.CONNECTION_TIMEOUT).get();
				if(doc!=null&&doc.html()!=null){
					value = qualifier.qualify(doc.html());
				}
			} catch (Exception e) {
				LOG.warning("Couldn't parse url ["+entry.getUri()+"]");
				entry.setError(true);
			}
			entry.setMarfeelizable(value);
			mongoOperations.save(entry);
		}
	}
}