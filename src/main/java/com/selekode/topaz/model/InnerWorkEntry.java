package com.selekode.topaz.model;

public class InnerWorkEntry {
	private int id;
	private String date;
	private Integer tagID;
	private String title;
	private String content;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getTagID() {
		return tagID;
	}
	public void setTagID(Integer tagID) {
		this.tagID = tagID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public InnerWorkEntry(int id, String date, Integer tagID, String title, String content) {
		super();
		this.id = id;
		this.date = date;
		this.tagID = tagID;
		this.title = title;
		this.content = content;
	}
	
	
}

