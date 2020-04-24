package de.koo.groovy.rest

import groovy.transform.ToString;

@ToString
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
}
