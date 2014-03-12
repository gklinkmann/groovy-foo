package de.koo.test.groovy.io;

import static org.junit.Assert.*

import org.junit.Before
import org.junit.Test

import de.koo.groovy.io.AwstatsStatistic
import de.koo.groovy.io.LogParser
import de.koo.groovy.io.TextLogParser
import de.koo.groovy.io.XmlLogParser

class LogParserTest {
	LogParser parser
	
	@Before
	public void setup() {
	}

	@Test
	public void parseText() {
		parser=new TextLogParser(file:new File("data/in/awstats/awstats032014.allmedia.local.host.txt"))	
		parser.parse().each {println it}
	}

	@Test
	public void parseXml() {
		parser=new XmlLogParser(file:new File("data/in/awstats/awstats032014.allmedia2.local.host.txt"))	
		parser.parse().each {println it}
	}

	@Test
	public void parseAll() {
		def statistic=new AwstatsStatistic()
		statistic.run("03","2014")
	}
}
