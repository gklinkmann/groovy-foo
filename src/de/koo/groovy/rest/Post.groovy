package de.koo.groovy.rest

class Post {
	def title
	def url
	def type
	def added
	def isPublic
	def referer
	def description
	List packs
	List tags
	
	public String toString(){
		return this.getProperties().toString()
	}
}
