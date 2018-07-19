package com.yashi.rest.messanger.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {
	private long id;
	private Date created;
	private String message;
	private String author;
	private Map<Long,Comment> comments = new HashMap<Long, Comment>();
	private List<Link> links = new ArrayList<Link>();
	
	public Message(){
		
	}
	
	public Message(long id, String message, String author){
		this.id = id;
		this.message = message;
		this.author = author;
		this.created = new Date();
	}
	
	public void setId(long id){
		this.id = id;
	}
	public long getId(){
		return this.id;
	}
	public void setCreated(Date created){
		this.created = created;
	}
	public Date getCreated(){
		return this.created;
	}
	public void setMessage(String message){
		this.message = message;
	}
	public String getMessage(){
		return this.message;
	}
	public void setAuthor(String author){
		this.author = author;
	}
	public String getAuthor(){
		return this.author;
	}
	public void setComments(Map<Long,Comment> comments){
		this.comments = comments;
	}
	public Map<Long,Comment> getComments(){
		return this.comments;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
	public void addLinks(String link, String rel){
		Link link2 = new Link();
		link2.setLink(link);
		link2.setRel(rel);
		links.add(link2);
	}
}
