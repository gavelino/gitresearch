package dcc.gaa.mes.gitresearch.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.egit.github.core.IssueEvent;
import org.eclipse.egit.github.core.util.DateUtils;

@Entity
@SuppressWarnings("serial")
public class GitIssueEvent implements Serializable {

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@ManyToOne(cascade = { CascadeType.REFRESH })
	private GitIssue issue;

	@Id
	private long id;

	private String commitId;

	private String event;

	private String url;

	@ManyToOne(cascade = { CascadeType.REFRESH })
	private GitUser actor;

	public GitIssueEvent() {
	}
	
	public GitIssueEvent(IssueEvent issueEvent, GitIssue gitIssue) {
		if (issueEvent !=null) {
			this.setCommitId(issueEvent.getCommitId());
			this.setActor(new GitUser(issueEvent.getActor()));
			this.setCreatedAt(issueEvent.getCreatedAt());
			this.setEvent(issueEvent.getEvent());
			this.setId(issueEvent.getId());
			this.setIssue(gitIssue);
			this.setUrl(issueEvent.getUrl());		
			
			// Configura o usuario que fechou a issue, baseado no evento.
			if (this.getEvent().equals("closed")&&gitIssue.getState().equals("closed"))
				gitIssue.setClosedBy(this.getActor());
		}
	}
	/**
	 * @return createdAt
	 */
	public Date getCreatedAt() {
		return DateUtils.clone(createdAt);
	}

	/**
	 * @param createdAt
	 * @return this issue event
	 */
	public GitIssueEvent setCreatedAt(Date createdAt) {
		this.createdAt = DateUtils.clone(createdAt);
		return this;
	}

	/**
	 * @return issue
	 */
	public GitIssue getIssue() {
		return issue;
	}

	/**
	 * @param issue
	 * @return this issue event
	 */
	public GitIssueEvent setIssue(GitIssue issue) {
		this.issue = issue;
		return this;
	}

	/**
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 * @return this issue event
	 */
	public GitIssueEvent setId(long id) {
		this.id = id;
		return this;
	}

	/**
	 * @return commitId
	 */
	public String getCommitId() {
		return commitId;
	}

	/**
	 * @param commitId
	 * @return this issue event
	 */
	public GitIssueEvent setCommitId(String commitId) {
		this.commitId = commitId;
		return this;
	}

	/**
	 * @return event
	 */
	public String getEvent() {
		return event;
	}

	/**
	 * @param event
	 * @return this issue event
	 */
	public GitIssueEvent setEvent(String event) {
		this.event = event;
		return this;
	}

	/**
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 * @return this issue event
	 */
	public GitIssueEvent setUrl(String url) {
		this.url = url;
		return this;
	}

	/**
	 * @return actor
	 */
	public GitUser getActor() {
		return actor;
	}

	/**
	 * @param actor
	 * @return this issue event
	 */
	public GitIssueEvent setActor(GitUser actor) {
		this.actor = actor;
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GitIssueEvent [id=").append(id).append(", event=")
				.append(event).append("]");
		return builder.toString();
	}
}