package de.koo.groovy.rest

import groovy.json.*

import javax.net.ssl.HttpsURLConnection

class DeliciousBookmarkClient {

	static main(args) {
		def dc=new DeliciousBookmarkClient()
		println dc.toHtml(dc.recent)
	}

	public DeliciousBookmarkClient () {
		Authenticator.setDefault(new Authenticator() {
			PasswordAuthentication getPasswordAuthentication() {
				def username = "username"
				def pass = 'password'
		
				return new PasswordAuthentication(username,pass.toCharArray())
			}
		})
	}

	public getRecent(){
		def url = new URL("https://username:password@api.delicious.com/v1/posts/recent")
		def conn = (HttpsURLConnection)url.openConnection()
		
		return conn.content.text
	}
	
	public toHtml(xml){
		def posts = new XmlSlurper().parseText(xml)

		// build the HTML representation
		def html="<dl>\n"
		posts.post.each{post->
		   def desc=post.@description.text()
		   def href=post.@href.text()
		   html <<= "   <dt><a href=\"${href}\">$desc</a></dt>\n"
		   html <<= "      <dd>${desc}</dd>\n"
		}
		html <<= "</dl>\n"
		
		return html.toString()
	}
	
	public loadJson (filename){
		def posts=[]
		
		JsonSlurper slurper = new JsonSlurper()
		def entries = slurper.parse(new FileReader(filename))
		entries.each{entry->
			def post=new Post(tags:entry.tags
				,title:entry.title
				,description:entry.description
				,referer:entry.referer
				,isPublic:entry.get("public")
				,type:entry.type
				,url:entry.url
				,packs:entry.packs
				)
			posts<<post
		}
		
		return posts
	}
	
	public add(filename){
		String.metaClass.encodeURL = {
			java.net.URLEncoder.encode(delegate,"US-ASCII")
		}
		
		def posts=this.loadJson(filename)
		def results=[]

		posts.each{post->
			def _url="https://username:password@api.delicious.com/v1/posts/add"
			_url <<= "?url=${post.url}"
			_url <<= "&description=${post.title.encodeURL()}"
			_url <<= "&tags=${post.tags.join(",").encodeURL()}"
			
			URL url = new URL(_url.toString())

			try {
				def conn = (HttpsURLConnection)url.openConnection()
				if (conn.responseCode==200) {
					def result = new XmlSlurper().parseText(conn.content.text)
					
					results << ["url":post.url, "result":result.@code.text()]					
				} 
			} catch (IOException e) {
				println "[ERROR] ${e.message}"
			}		
			
			// wait 1 second before you'll throttled
			Thread.sleep(1100)
		}
		
		return results
	}

}
