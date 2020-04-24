package de.koo.groovy.json

import groovy.transform.ToString;

import java.util.List;

@ToString
class Bookmark {
	static enum BookmarkType { page, image }
	
	String title
	String url
	BookmarkType type
	Date added
	Boolean isPublic
	String referer
	String description
	List packs
	List tags
}
