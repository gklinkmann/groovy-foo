package de.koo.groovy.json

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

import com.github.fge.jsonschema.main.JsonSchemaFactory

class JsonLoader {
	public static main(args){
		def loader=new JsonLoader()
		loader.validate("data/in/zoo_export.json"
			,"data/in/zoo_export_schema.json")
	}
	
	public validate(filename,schemaFilename){
		def zooSchema = com.github.fge.jackson.JsonLoader.fromFile(new File(schemaFilename))
        def json = com.github.fge.jackson.JsonLoader.fromFile(new File(filename))
		
		def factory = JsonSchemaFactory.byDefault()
		def schema = factory.getJsonSchema(zooSchema)
		
		schema.validate(json)
	}
	
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