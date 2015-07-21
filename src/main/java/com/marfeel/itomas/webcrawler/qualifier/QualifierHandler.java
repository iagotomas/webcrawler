package com.marfeel.itomas.webcrawler.qualifier;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

public class QualifierHandler {

	private Map<String,Qualifier> qualifiers;
	private Qualifier defaultQualifier;
	@Autowired
	public void setQualifiers(Map<String, Qualifier> qualifiers) {
		this.qualifiers = qualifiers;
	}
	@Autowired
	public void setDefaultQualifier(Qualifier defaultQualifier) {
		this.defaultQualifier = defaultQualifier;
	}
	public Qualifier handle(String qualifier){
		if(qualifiers.containsKey(qualifier)){
			return qualifiers.get(qualifier);
		}
		return defaultQualifier;
	}
}
