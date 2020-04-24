package de.koo.test.groovy.xml;

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import de.koo.groovy.xml.XmlReader

class XmlReaderTest {
	def reader

	@BeforeEach
	public void setUp (){
		reader=new XmlReader()
	}
	
	@Test
	public void read (){
		def cars=reader.read("data/in/cars.xml")
		assert cars.size()>0
		
		cars.each { println it }
	}
}
