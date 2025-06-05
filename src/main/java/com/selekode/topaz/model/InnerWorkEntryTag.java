package com.selekode.topaz.model;

public class InnerWorkEntryTag {
	private int inner_work_entry_id;
	private int tag_id;
	public int getInner_work_entry_id() {
		return inner_work_entry_id;
	}
	public void setInner_work_entry_id(int inner_work_entry_id) {
		this.inner_work_entry_id = inner_work_entry_id;
	}
	public int getTag_id() {
		return tag_id;
	}
	public void setTag_id(int tag_id) {
		this.tag_id = tag_id;
	}
	public InnerWorkEntryTag(int inner_work_entry_id, int tag_id) {
		super();
		this.inner_work_entry_id = inner_work_entry_id;
		this.tag_id = tag_id;
	}
	
	
}
