<h1>webcrawler</h1>
A basic webcrawler
	<p>
		This project aims to be a web page scraper crawler service. It
		provides a REST interface to input a JSON formatted list of urls that
		will be qualified by the server and persisted in the repository. The
		endpoint is published at <b>/crawler</b> , it is the only accessible
		path and it expects an input as follows:
	</p>
	<code> [ { "url": "centrallecheraasturiana.es", "rank": 834987
		}, { "url": "guiafull.com", "rank": 571272 } ] </code>
	<p>The endpoint is asynchronous and should return an HTTP status
		200 immediately firing an asynchronous thread to process the urls.</p>
	<p>
		Once processed the urls are stored in mongodb in a database named <b>marfeel</b>,
		inside a collection named <b>urls</b> should be found after the first
		request. The document stored contains the following fields:<br /> <b>uri</b>:
		the url processed<br /> <b>rank</b>: the url's rank<br /> <b>marfeelizable</b>:
		whether the url is qualified or not<br /> <b>error</b>: whether there
		was an error while processing the url<br /> <br /> Mongodb settings
		can be found at <i>src/main/webapp/WEB-INF/dispatch-servler.xml</i>
	</p>
	<h3>Build</h3>
	<p>
		To build the project simply run
		<code>mvn install</code>
		inside the unzipped project's folder. If successful this should
		produce a war file inside the target folder which can be deployed in
		the server. 
	</p>
	<h3>Note</h3>
	<p>
		Integration tests have been disabled , to run them you must have a
		running mongodb instance and configure <i>src/main/webapp/WEB-INF/dispatch-servler.xml</i>
		to match it, to enable the tests you will have to delete the @Ignore
		annotation found in <i>src/main/test/com/itomas/webcrawler/CrawlerControllerTest.java</i>
		at line 26
	</p>
