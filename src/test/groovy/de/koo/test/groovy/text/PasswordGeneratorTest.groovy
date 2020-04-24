package de.koo.test.groovy.text;

import static org.junit.Assert.*

import org.junit.Before
import org.junit.Test

import de.koo.groovy.text.PasswordGenerator

class PasswordGeneratorTest {
	def generator
	
	@Before
	public void init() {
		generator=new PasswordGenerator()
	}
	
	@Test
	public void generate() {
		generator.generate(32)
	}
}
