package de.koo.test.groovy.text;

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import de.koo.groovy.text.PasswordGenerator

class PasswordGeneratorTest {
	def generator
	
	@BeforeEach
	public void init() {
		generator=new PasswordGenerator()
	}
	
	@Test
	public void generate() {
		generator.generate(32)
	}
}
