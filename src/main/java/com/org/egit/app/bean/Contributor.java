package com.org.egit.app.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement   
public class Contributor {
	private String name;
	private int id;
	private String type;
	private String url;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	

}
