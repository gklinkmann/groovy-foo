package de.koo.groovy.xml

import java.util.List;

class XmlReader {

	public read(){
		def cars=[]
		def records=new XmlSlurper().parse("data/in/cars.xml");

		records.car.each {car->
			cars << ["name":car.@name.text(),"country":car.country.text()]
		}
		
		return cars
	}
}
