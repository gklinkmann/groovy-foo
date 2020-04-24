package de.koo.groovy.text

class PasswordGenerator {
	public static void main(def args){
		def generator=new PasswordGenerator()
		generator.generate(8)
	}
	
	public generate(passwordLength){
		def availChars = []
		('A'..'Z').each { availChars << it.toString() }
		3.times { (0..9).each { availChars << it.toString()
			} }

		def generateRandomString = { length ->
			def max = availChars.size
			def rnd = new Random()
			def sb = new StringBuilder()
			length.times { sb.append(availChars[rnd.nextInt(max)]) }
			sb.toString()
		}

		3.times { println generateRandomString(passwordLength) }
	}
}
