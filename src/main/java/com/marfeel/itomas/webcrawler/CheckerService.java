package com.marfeel.itomas.webcrawler;

import com.marfeel.itomas.webcrawler.qualifier.Qualifier;

/**
 * The checker service is in charge of qualifying and persisting the urls provided.
 * @author iago
 *
 */
public interface CheckerService {

	public void check(UriEntry entry);
	public void setQualifier(Qualifier qualifier);

}