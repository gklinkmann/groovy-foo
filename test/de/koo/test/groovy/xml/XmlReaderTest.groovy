package de.koo.test.groovy.xml;

import static org.junit.Assert.*

import org.junit.Before
import org.junit.Test

import de.koo.groovy.xml.XmlReader

class XmlReaderTest {
	def reader
	@Before
	public void setUp (){
		reader=new XmlReader()
	}
	
	@Test
	public void read (){
		def cars=reader.read()
		assert cars.size()>0
		
		cars.each { println it }
	}
}
