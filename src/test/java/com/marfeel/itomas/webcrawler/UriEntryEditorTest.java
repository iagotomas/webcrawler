package com.marfeel.itomas.webcrawler;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UriEntryEditorTest {

	private UriEntryEditor mock;

	@Before
	public void setUp() {
		this.mock = new UriEntryEditor();
	}

	@Test
	public void setAsText() {
		mock.setAsText("[{\"url\":\"www.google.com\",\"rank\":8989889},{\"url\":\"www.yaho.com\",\"rank\":8989887}]");
		Assert.assertTrue(mock.getValue() instanceof List);
		List<UriEntry> result = (List<UriEntry>) mock.getValue();
		Assert.assertTrue(result.size()==2);
	}
}
