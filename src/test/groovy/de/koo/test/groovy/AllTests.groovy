package de.koo.test.groovy

import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

import de.koo.test.groovy.io.LogParserTest
import de.koo.test.groovy.json.JsonLoaderTest
import de.koo.test.groovy.rest.DeliciousBookmarkClientTest
import de.koo.test.groovy.text.PasswordGeneratorTest
import de.koo.test.groovy.xml.XmlReaderTest

@RunWith(Suite.class)
@SuiteClasses([LogParserTest.class
	,JsonLoaderTest.class
	,DeliciousBookmarkClientTest.class
	,PasswordGeneratorTest.class
	,XmlReaderTest.class
])
class AllTests {

}
