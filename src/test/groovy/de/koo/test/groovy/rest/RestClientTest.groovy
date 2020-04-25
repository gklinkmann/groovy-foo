package de.koo.test.groovy.rest

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import de.koo.groovy.rest.RestClient

class RestClientTest {
	RestClient client
	
	@BeforeEach
	void setUp() {
		client=new RestClient()
	}
	
	@Test
	void getItemScore() {
		def testData=[item:'ASDFASEACV235', score: 90786]
		def itemScore=client.getItemScore(testData.item, testData.score)
		assert itemScore.score == testData.score
	}

}
