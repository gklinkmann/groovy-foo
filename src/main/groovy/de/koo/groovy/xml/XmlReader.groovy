package de.koo.groovy.xml

import java.util.List;

class XmlReader {

	public read(filename){
		def cars=[]
		def records=new XmlSlurper().parse(filename);

		records.car.each {car->
			cars << ["name":car.@name.text(),"country":car.country.text()]
		}
		
		return cars
	}
}
