package com.slq.common;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Template {

	public Template() {
		super();
	}

	public Template(String name) {
		super();
		this.name = name;
	}

	private String name;
	
	private String value;
	
	private List<Template> children;

	public void setChildren(List<Template> children) {
		if(children==null||children.isEmpty()||children.size()==0) {
			children=null;
		}
		this.children = children;
	}
	public List<Template> getChildren() {
		if(children==null||children.isEmpty()||children.size()==0) {
			children=null;
		}
		return children;
	}
	public List<Template> initChildren() {
		if(this.children==null) {
			this.children=new ArrayList<Template>();
		}
		return children;
	}
}
