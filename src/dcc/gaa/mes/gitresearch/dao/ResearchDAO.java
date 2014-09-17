package dcc.gaa.mes.gitresearch.dao;

import javax.persistence.EntityTransaction;

import dcc.gaa.mes.gitresearch.model.GitCommit;
import dcc.gaa.mes.gitresearch.model.GitIssue;
import dcc.gaa.mes.gitresearch.model.GitIssueEvent;
import dcc.gaa.mes.gitresearch.model.GitLabel;
import dcc.gaa.mes.gitresearch.model.GitMilestone;
import dcc.gaa.mes.gitresearch.model.GitRepository;
import dcc.gaa.mes.gitresearch.model.GitRepositoryCommit;
import dcc.gaa.mes.gitresearch.model.GitResearch;
import dcc.gaa.mes.gitresearch.model.GitUser;

public class ResearchDAO extends GenericDAO<GitResearch> {
	
	public void prePersist(GitResearch research) {
		
		for (GitRepository gr : research.getRepositories()) {
			gr.setGitResearch(research);
			
			for (GitRepositoryCommit rc : gr.getRepositoryCommits()) {
				rc.setRepository(gr);
			}
			
			for (GitIssue gi : gr.getRepositoryIssues()) {
				gi.setRepository(gr);

				for (GitIssueEvent ie : gi.getEvents()) {
					ie.setIssue(gi);
				}
			}
		}
	}
	
	@Override
	public void persist(GitResearch research) {
		this.prePersist(research);
		
		EntityTransaction tx = this.em.getTransaction();
		try {
			tx.begin();
			
			for (GitRepository gr : research.getRepositories()) {
				for (GitRepositoryCommit rc : gr.getRepositoryCommits()) {
					if (rc.getCommit().getId() == null) {
						this.em.persist(rc.getCommit());
					}
					
					for (GitCommit commit : rc.getParents()) {
						if (commit.getId() == null) {
							this.em.persist(commit);
						}
					}
					
					for (GitCommit commit : rc.getCommit().getParents()) {
						if (commit.getId() == null) {
							this.em.persist(commit);
						}
					}
					
					GitUser user = rc.getAuthor();
					if (this.em.find(GitUser.class, user.getId()) == null) {
						this.em.persist(user);
					}
					
					user = rc.getCommitter();
					if (this.em.find(GitUser.class, user.getId()) == null) {
						this.em.persist(user);
					}
					
				}
				
				for (GitIssue gi : gr.getRepositoryIssues()) {
					for (GitIssueEvent ie : gi.getEvents()) {
						this.em.persist(ie);
						
						GitIssue issue = ie.getIssue();
						if (this.em.find(GitIssue.class, issue.getId()) == null) {
							this.em.persist(issue);
						}
						
						GitUser actor = ie.getActor();
						if (this.em.find(GitUser.class, actor.getId()) == null) {
							this.em.persist(actor);
						}
					}
					
					for (GitLabel gl : gi.getLabels()) {
						if (gl != null && this.em.find(GitLabel.class, gl.getUrl()) == null) {
							this.em.persist(gl);
						}
					}
					
					GitMilestone milestone = gi.getMilestone();
					if (milestone != null && this.em.find(GitMilestone.class, milestone.getUrl()) == null) {
						GitUser creator = milestone.getCreator();
						if (this.em.find(GitUser.class, creator.getId()) == null) {
							this.em.persist(creator);
						}
						
						this.em.persist(milestone);
					}
					
					GitUser assignee = gi.getAssignee();
					if (assignee != null && this.em.find(GitUser.class, assignee.getId()) == null) {
						this.em.persist(assignee);
					}
					
					GitUser user = gi.getUser();
					if (user != null && this.em.find(GitUser.class, user.getId()) == null) {
						this.em.persist(user);
					}
					
					GitUser closedBy = gi.getClosedBy();
					if (closedBy != null && this.em.find(GitUser.class, closedBy.getId()) == null) {
						this.em.persist(closedBy);
					}
				}
			}
			
			this.em.persist(research);
			
			tx.commit();
		} catch (RuntimeException re) {
			if(tx != null && tx.isActive()) 
				tx.rollback();
			throw re;
		}
		
		
		
		this.prePersist(research);
		super.persist(research);
	}
	
	@Override
	public GitResearch find(Object id) {
		return this.em.find(GitResearch.class, id);
	}

}
