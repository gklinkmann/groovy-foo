package de.koo.groovy.json

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

class JsonLoader {
	public load(filename){
		def bookmarks=[]
		
		JsonSlurper slurper = new JsonSlurper()
		def entries = slurper.parse(new FileReader(filename))
		entries.each{entry->
			def bookmark=new Bookmark(tags:entry.tags
				,title:entry.title
				,description:entry.description
				,referer:entry.referer
				,isPublic:entry.get("public")=="y"
				,type:entry.type
				,url:entry.url
				,packs:entry.packs
				)
			bookmarks << bookmark
		}

		return bookmarks
	}
	
	public unload(bookmarks,filename){
		JsonBuilder builder = new JsonBuilder(bookmarks)
		
		new File(filename).withWriter {out ->
			out.write(builder.toPrettyString())
		}
	}
}
