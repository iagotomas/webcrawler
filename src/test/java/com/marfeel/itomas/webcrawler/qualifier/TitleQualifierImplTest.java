package com.marfeel.itomas.webcrawler.qualifier;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TitleQualifierImplTest {

	
	TitleQualifierImpl impl;
	@Before
	public void setUp(){
		impl = new TitleQualifierImpl();
	}
	@Test
	public void qualify_success() {
		boolean result = impl.qualify("<html><head><title>News</title></head></html>");
		Assert.assertTrue(result);
	}
	@Test
	public void qualify_failure() {
		boolean result = impl.qualify("<html><head><title>Another</title></head></html>");
		Assert.assertFalse(result);
	}
}
