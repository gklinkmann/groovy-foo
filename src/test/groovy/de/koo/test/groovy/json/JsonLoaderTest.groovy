package de.koo.test.groovy.json;

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import de.koo.groovy.json.JsonLoader

class JsonLoaderTest {
	def loader
	
	@BeforeEach
	public void setUp() {
		loader=new JsonLoader()
	}

	@Test
	public void validate() {
		loader.validate("data/in/zoo_export.json","data/in/zoo_export_schema.json")
	}
	
	@Test
	public void load() {
		def bookmarks=loader.load("data/in/zoo_export.json")
		assert bookmarks.size()>0
		
		bookmarks.each{println it}
	}
	
	@Test
	public void unload() {
		def bookmarks=loader.load("data/in/zoo_export.json")
		assert 0 < bookmarks.size()
		
		loader.unload(bookmarks,"data/out/bookmarks.json")
	}
}
