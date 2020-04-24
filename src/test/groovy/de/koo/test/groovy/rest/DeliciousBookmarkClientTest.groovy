package de.koo.test.groovy.rest;

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import de.koo.groovy.rest.DeliciousBookmarkClient

class DeliciousBookmarkClientTest {
	DeliciousBookmarkClient client
	
	@BeforeEach
	public void setUp(){
		client=new DeliciousBookmarkClient()	
	} 

	@Test
	public void getRecent(){
		def xml=client.recent
		def posts = new XmlSlurper().parseText(xml)
		assert 0 < posts.post.size()
		
		println client.toHtml(xml)
	} 

	@Test
	public void loadJson(){
		def posts=client.loadJson("data/in/zoo_export.json")
		assert 1 == posts.size()	
	} 

	@Test
	public void add(){
		def filename="data/in/zoo_export.json"
		
		def results=client.add(filename)
		assert results.size()==client.loadJson(filename).size()
		
		results.each {println it}	
	} 
}
