package de.koo.groovy.rest

import static groovyx.net.http.HttpBuilder.configure
import static groovyx.net.http.ContentTypes.JSON
import groovyx.net.http.*

class RestClient {
	def http
	
	public getItemScore(item, score) {
		http = configure {
			request.uri = 'http://httpbin.org'
			request.contentType = JSON[0]
			response.parser(JSON[0]) { config, resp ->
				new ItemScore(NativeHandlers.Parsers.json(config, resp).json)
			}
		}
		
		ItemScore itemScore = http.post(ItemScore) {
		    request.uri.path = '/post'
		    request.body = new ItemScore(item, score)
		}
		
		return itemScore
	} 	
}
