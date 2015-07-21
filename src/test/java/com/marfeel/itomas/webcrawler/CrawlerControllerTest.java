package com.marfeel.itomas.webcrawler;

import java.util.List;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.marfeel.itomas.webcrawler.qualifier.QualifierHandler;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/dispatcher-servlet.xml"})
@WebAppConfiguration
public class CrawlerControllerTest {
	@Autowired
    private WebApplicationContext ctx;

	@Autowired
	private CheckerService checkerService;
	@Autowired
	private QualifierHandler qualifierHandler;
	
    private MockMvc mockMvc;
 
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }
 
    @Test
    public void crawler() throws Exception {
        String json = "[{\"url\":\"www.google.com\",\"rank\":8989889}]";
        mockMvc.perform(post("/crawler")
                .contentType("application/json")
                .content(json).accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }
    @Test
    public void extract(){
    	CrawlerController controller = new CrawlerController(qualifierHandler,checkerService);
        String json = "[{\"url\":\"www.google.com\",\"rank\":8989889}]";
    	List<UriEntry> result = controller.extract(json);
    	Assert.assertNotNull(result);
    	Assert.assertTrue(result.size()==1);
    	Assert.assertTrue("www.google.com".equals(result.get(0).getUri()));
    }
 
}
