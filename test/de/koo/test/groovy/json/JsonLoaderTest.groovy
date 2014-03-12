package de.koo.test.groovy.json;

import static org.junit.Assert.*

import org.junit.Before
import org.junit.Test

import de.koo.groovy.json.JsonLoader

class JsonLoaderTest {
	def loader
	
	@Before
	public void setUp() {
		loader=new JsonLoader()
	}

	@Test
	public void load() {
		def bookmarks=loader.load("data/in/zoo_export.json")
		assert 0 < bookmarks.size()
		
		bookmarks.each{println it}
	}
	
	@Test
	public void unload() {
		def bookmarks=loader.load("data/in/zoo_export.json")
		assert 0 < bookmarks.size()
		
		loader.unload(bookmarks,"data/out/bookmarks.json")
	}
}
