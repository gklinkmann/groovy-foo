package de.koo.groovy.io

class TextLogParser extends LogParser {

	public getSection(section){
		def sectionData=[]
		
		def startSection=false
		file.withReader{ r ->
			for (def line; (line = r.readLine()) != null; ) {
				if (line.startsWith("BEGIN_${section}")) {
					startSection=true
					continue
				}
				if (line.startsWith("END_${section}")) break
	
				if (startSection) {
					sectionData.add(line.trim().split())
				}
			}
		}
		
		return sectionData
	}
}
