package com.selekode.topaz.model;

import java.time.LocalDate;

import com.selekode.topaz.converter.LocalDateUnixSecondsConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "inner_work_entry")
public class InnerWork {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// Stores as Unix seconds in DB, used as LocalDate in Java
	@Convert(converter = LocalDateUnixSecondsConverter.class)
	@Column(name = "date", nullable = false)
	private LocalDate date;
	private Long tagID;
	private String title;
	private String content;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Long getTagID() {
		return tagID;
	}

	public void setTagID(Long tagID) {
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

	public InnerWork() {
		
	}

}
