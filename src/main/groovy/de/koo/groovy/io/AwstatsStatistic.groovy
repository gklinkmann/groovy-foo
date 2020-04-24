package de.koo.groovy.io

import groovy.io.FileType

class AwstatsStatistic {
	def parser

	public run(month, year){
		def domainData=[:]
		def dataDir=new File("data/in/awstats")
		
		// Unterverzeichnisse ermitteln
		if (dataDir.isDirectory()) {
			def pattern = "awstats${month}${year}.*\\.txt"
			dataDir.eachFileMatch(FileType.FILES, ~/${pattern}/) { dataFile ->
				def domain=getDomain(dataFile.name)
				
				def line
				// erste Zeile auslesen  
				dataFile.withReader { line = it.readLine() }  
				if(line =~ /xml/) {
					parser=new XmlLogParser("file":dataFile)
				} else {
					parser=new TextLogParser("file":dataFile)
				}
				
				if (domainData.keySet().contains(domain)) {
					domainData[domain]=domainData[domain]+this.statistic.klr
				} else {
					domainData[domain]=this.statistic.klr
				}
			}
			
			domainData.each { _domain,_klr -> 
				println "${_domain};${_klr}"
			}
		}
	}
	
	/**
	 * returns the hits and the bandwith (MB) per month
	 *
	 * @return
	 */
	private getStatistic(){
		def hits=0
		def bandwith=0
		
		parser.days.each{day ->
			hits+=new Long(day.hits)
			bandwith+=new Long(day.bandwith)
		}
		
		return ["klr": hits*Math.round(bandwith/1024/1024)]
	}
	
	/**
	 * returns the domain from filename
	 * @param filename
	 * @return
	 */
	private getDomain(filename){
		def matcher = (filename =~ /awstats\d{6}\.(.*)/)
		String domain
		
		if (matcher.matches()) {
			domain=matcher[0][1]
			domain=domain.replaceAll("(.txt|.443\\.txt)","")
			domain=domain.replaceAll("www1","www")
		} else {
			domain="unknown"
		}
		
		return domain
	}
		
}
