package de.koo.groovy.io

class XmlLogParser extends LogParser {
	public getSection(section){
		def sectionText=""
		
		def startSection=false
		file.withReader{ r ->
			for (String line; (line = r.readLine()) != null; ) {
				if (line.contains("BEGIN_${section}")) {
					startSection=true
					continue
				}
				if (line.contains("END_${section}")) break
	
				if (startSection) {
					sectionText <<= line
				}
			}
		}

		return parseXml("<table>${sectionText.toString()}</table>")
	}
	
	private parseXml(xml){
		def sectionData=[]
		
		def table = new XmlSlurper().parseText(xml)
		table.tr.each{ row ->
			def tokens=[]
			row.td.each{ tokens.add(it.text()?.trim()) }
			
			sectionData.add(tokens)
		}
		
		return sectionData
	}
}
