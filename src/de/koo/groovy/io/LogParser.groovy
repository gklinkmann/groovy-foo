package de.koo.groovy.io

abstract class LogParser {
	def file

	public parse(){
		return ["days":this.days, "mapping":this.mapping]
	}
	
	public getDays(){
		def days=[]
		getSection("DAY").each{tokens->
			days.add([date:tokens[0]
				,pages:tokens[1]
				,hits:tokens[2]
				,bandwith:tokens[3]
				,visits:tokens[4]])
		}
		
		return days
	}
		
	public getMapping(){
		def mapping=[:]
		getSection("MAP").each{tokens->
			mapping.put(tokens[0], tokens[1])
		}
		return mapping
	}
	
	public abstract getSection(section)
}
