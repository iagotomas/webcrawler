package com.marfeel.itomas.webcrawler;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="urls")
public class UriEntry {

	@Id
	private String id;
	private String uri;
	private String rank;
	private boolean marfeelizable;
	private boolean error;
	/**
	 * The uri as given by the client. Lacks of the protocol part.
	 * @return the uri of this entry
	 */
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	/**
	 * Uri's rank, a number 
	 * @return the rank of this uri
	 */
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	/**
	 * Whether the entry is marfeelizable or not
	 * @return true if it is marfeelizable
	 */
	public boolean isMarfeelizable() {
		return marfeelizable;
	}
	public void setMarfeelizable(boolean marfeelizable) {
		this.marfeelizable = marfeelizable;
	}
	public String getId() {
		return id;
	}
	/**
	 * Whether the url failed to be parsed
	 * @return true if the url coudldn't be parsed
	 */
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	
	
	
}
