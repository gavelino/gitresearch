package dcc.gaa.mes.gitresearch.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityTransaction;

import dcc.gaa.mes.gitresearch.model.GitComment;
import dcc.gaa.mes.gitresearch.model.GitCommit;
import dcc.gaa.mes.gitresearch.model.GitCommitComment;
import dcc.gaa.mes.gitresearch.model.GitIssue;
import dcc.gaa.mes.gitresearch.model.GitIssueEvent;
import dcc.gaa.mes.gitresearch.model.GitLabel;
import dcc.gaa.mes.gitresearch.model.GitMilestone;
import dcc.gaa.mes.gitresearch.model.GitPullRequest;
import dcc.gaa.mes.gitresearch.model.GitRepository;
import dcc.gaa.mes.gitresearch.model.GitRepositoryCommit;
import dcc.gaa.mes.gitresearch.model.GitResearch;
import dcc.gaa.mes.gitresearch.model.GitUser;

public class ResearchDAO extends GenericDAO<GitResearch> {
	
	public void prePersist(GitResearch research) {
	    
	    Map<String, GitCommit> map = new HashMap<String, GitCommit>();
	    for (GitRepository gr : research.getRepositories()) {
	        for (GitRepositoryCommit rc : gr.getRepositoryCommits()) {
	            map.put(rc.getCommit().getSha(), rc.getCommit());
	        }
	    }
		
		for (GitRepository gr : research.getRepositories()) {
			gr.setGitResearch(research);
			
			for (GitRepositoryCommit rc : gr.getRepositoryCommits()) {
				rc.setRepository(gr);
				for (int i = 0; i < rc.getParents().size(); i++) {
                    GitCommit gc = rc.getParents().get(i);
                    if (map.containsKey(gc.getSha())) {
                        rc.getParents().set(i, map.get(gc.getSha()));
                    }
                }
				
				List<GitCommit> parents = rc.getCommit().getParents();
                for (int i = 0; i < parents.size(); i++) {
				    GitCommit gc = parents.get(i);
                    if (map.containsKey(gc.getSha())) {
                        parents.set(i, map.get(gc.getSha()));
                    }
				}
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
					if (user != null && this.em.find(GitUser.class, user.getId()) == null) {
						this.em.persist(user);
					}
					
					user = rc.getCommitter();
					if (user != null && this.em.find(GitUser.class, user.getId()) == null) {
						this.em.persist(user);
					}
					
				}
				
				for (GitIssue gi : gr.getRepositoryIssues()) {
					for (GitIssueEvent ie : gi.getEvents()) {
						this.em.persist(ie);
						
						GitIssue issue = ie.getIssue();
						if (issue != null && this.em.find(GitIssue.class, issue.getId()) == null) {
							this.em.persist(issue);
						}
						
						GitUser actor = ie.getActor();
						if (actor != null && actor.getId() !=null && this.em.find(GitUser.class, actor.getId()) == null) {
							this.em.persist(actor);
						}
						if (issue.getGitComments()!=null) {
							for (GitComment gcomm : issue.getGitComments()) {
								if (gcomm.getUser()!=null && this.em.find(GitUser.class, gcomm.getUser().getId()) == null){
									this.em.persist(gcomm.getUser());
								}
									
								if (this.em.find(GitComment.class, gcomm.getId()) == null) {
									this.em.persist(gcomm);
								}
							}
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
					
					GitPullRequest pull = gi.getPullRequest();
					if (pull != null && this.em.find(GitPullRequest.class, pull.getId()) == null) {
						
						GitUser pullUser = pull.getUser();
						if (pullUser != null && this.em.find(GitUser.class, pullUser.getId()) == null) {
							this.em.persist(pullUser);
						}
						GitUser pullAssignee = pull.getAssignee();
						if (pullAssignee != null && this.em.find(GitUser.class, pullAssignee.getId()) == null) {
							this.em.persist(pullAssignee);
						}
						GitUser pullMerged = pull.getMergedBy();
						if (pullMerged != null && this.em.find(GitUser.class, pullMerged.getId()) == null) {
							this.em.persist(pullMerged);
						}

						if (pull.getGitComments()!=null) {
							for (GitCommitComment gcomm : pull.getGitComments()) {
								if (this.em.find(GitCommitComment.class, gcomm.getId()) == null) {
									this.em.persist(gcomm);
								}
							}
						}
						
						this.em.persist(pull);
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
	}
	
	public List<GitResearch> getAll() {
		return this.em.createQuery("SELECT gr FROM GitResearch gr", GitResearch.class).getResultList();
	}
	
	@Override
	public GitResearch find(Object id) {
		return this.em.find(GitResearch.class, id);
	}

}
