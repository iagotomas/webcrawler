package com.marfeel.itomas.webcrawler;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.marfeel.itomas.webcrawler.qualifier.Qualifier;
import com.marfeel.itomas.webcrawler.qualifier.QualifierHandler;


@Controller
public class CrawlerController {

	private static final Logger LOG = Logger.getLogger(CrawlerController.class.getName());
	private static final String ENCODING = "UTF-8";

	private CheckerService checkerService;
	private QualifierHandler qualifierHandler;
	
	@Autowired
	public CrawlerController(QualifierHandler qualifierHandler,CheckerService checkerService) {
		this.qualifierHandler = qualifierHandler;
		this.checkerService = checkerService;
	}
	@RequestMapping(value="/crawler", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	@Async
	public void crawler(@RequestBody final String uris,@RequestParam(value="qualifier",required=false) String qualifier){
		final List<UriEntry> source = extract(uris);
		Qualifier qualifierImpl = getQualifierHandler().handle(qualifier);
		CheckerService checker = getCheckerService();
		checker.setQualifier(qualifierImpl);
		for(UriEntry entry:source){
			checker.check(entry);
		}
	}
	/**
	 * Extract a list of {@link UriEntry}'s from a string.  
	 * @param uris A string with the JSON representation to translate
	 * @return a list of {@link UriEntry}'s
	 */
	protected List<UriEntry> extract(final String uris)  {
		UriEntryEditor editor = getEntryEditor();
		List<UriEntry> result = new ArrayList<UriEntry>();
		try {
			editor.setAsText(URLDecoder.decode(uris,ENCODING));
			result = (List<UriEntry>) editor.getValue();
		} catch (IllegalArgumentException e) {
			LOG.severe("The input couldn't be parsed into a valid format. "+e.getMessage());
		} catch (UnsupportedEncodingException e) {
			LOG.severe("The input has an invalid encoding. "+e.getMessage());
		}
		return result;
	}
	public void setQualifierHandler(QualifierHandler qualifierHandler) {
		this.qualifierHandler = qualifierHandler;
	}
	protected QualifierHandler getQualifierHandler() {
		return qualifierHandler;
	}
	protected CheckerService getCheckerService() {
		return checkerService;
	}
	public void setCheckerService(CheckerService checkerService) {
		this.checkerService = checkerService;
	}
	protected UriEntryEditor getEntryEditor() {
		return new UriEntryEditor();
	}
	
	@ExceptionHandler
	@ResponseBody
	public String handleException(IllegalStateException ex) {
		LOG.severe(ex.getMessage());
		return "Exception raisen";
	}
}
