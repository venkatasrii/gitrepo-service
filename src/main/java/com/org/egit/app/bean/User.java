package com.org.egit.app.bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement   
public class User {
	
	private String userName;
	
	public User(String userName) {
		super();
		this.userName = userName;
	}



	public User() {
		super();
		// TODO Auto-generated constructor stub
	}



	public User(String userName, List<Repo> repos) {
		super();
		this.userName = userName;
		this.repos = repos;
	}



	private List<Repo> repos;
	
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public List<Repo> getRepos() {
		return repos;
	}


	public void setRepos(List<Repo> repos) {
		this.repos = repos;
	}



	public void addRepo(Repo repo) {
		if(repos ==null) {
			repos=new ArrayList<>();
		}
		repos.add(repo);
		
		}		
	}