package com.org.egit.app.service;

import org.apache.commons.codec.binary.Base64;
import org.eclipse.egit.github.core.Contributor;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.RepositoryContents;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.stereotype.Service;

import com.org.egit.app.bean.Repo;
import com.org.egit.app.bean.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GitHubService {

	public User getUserRepository(String user, String repo) {
		RepositoryService repositoryService = new RepositoryService();
		final CommitService commitService = new CommitService();
		ContentsService contentsService = new ContentsService();

		//
		User user_ = new User();
		List<Repo> repos=new ArrayList<>();
		List<com.org.egit.app.bean.Contributor> contributors_=new ArrayList<>();
		Repo repo_ = new Repo();
		com.org.egit.app.bean.Contributor contributor_;
		try {
			Repository r = repositoryService.getRepository(user,repo);
			user_.setUserName(user);
			
			System.out.println(
					" Description : " + r.getDescription() + "  ID: " + r.getId() + "  GitUrl : " + r.getGitUrl()
							+ "  Name : " + r.getName() + "  Url :  " + r.getUrl() + "  HtmlUrl : " + r.getHtmlUrl());

			List<Contributor> contributors = repositoryService.getContributors(r, false);
			System.out.println(" contributors size : " + contributors.size());
			for (Contributor contributor : contributors) {
				System.out.println(" contributors : " + contributor.getContributions());
				System.out.println(" Name : " + contributor.getName());
				System.out.println(" url : " + contributor.getUrl());
				System.out.println(" type : " + contributor.getType());
				System.out.println(" login : " + contributor.getLogin());
				
				
				contributor_ = new com.org.egit.app.bean.Contributor();
				contributor_.setId(contributor.getId());
				contributor_.setName(contributor.getName());
				contributor_.setType(contributor.getType());
				contributor_.setUrl(contributor.getUrl());
				contributors_.add(contributor_);
			}

			List<RepositoryCommit> repositoryCommits = commitService.getCommits(r);
			System.out.println(" repositoryCommits : " + repositoryCommits.size());
			repo_.setCommits(repositoryCommits.size());
		///	try {
				RepositoryContents repositoryContents = contentsService.getReadme(r);				
				String readme = new String(Base64.decodeBase64(repositoryContents.getContent().getBytes()));
				System.out.println(readme);
				repo_.setReadme(readme);

		/*	} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			repo_.setContributors(contributors_);
			repos.add(repo_);
			user_.setRepos(repos);
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
return user_;
	}

	public User getUserRepositories(String user) {
		RepositoryService repositoryService = new RepositoryService();

		User user_ = new User();
		List<Repo> repos=new ArrayList<>();
		Repo repo = null;
		try {
			List<Repository> repositories = repositoryService.getRepositories("ynvsreeni");
			user_.setUserName(user);
			for (Repository r : repositories) {

				System.out.println(" Description : " + r.getDescription() + "  ID: " + r.getId() + "  GitUrl : "
						+ r.getGitUrl() + "  Name : " + r.getName() + "  Url :  " + r.getUrl() + "  HtmlUrl : "
						+ r.getHtmlUrl());
				
				repo =new Repo();
				repo.setId(r.getId());
				repo.setTitle(r.getDescription());
				repo.setUrl(r.getGitUrl());
				repo.setName(r.getName());
				repos.add(repo);

			}
			user_.setRepos(repos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
return user_;
	}

}