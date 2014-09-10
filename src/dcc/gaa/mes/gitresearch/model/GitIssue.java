package dcc.gaa.mes.gitresearch.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.Label;
import org.eclipse.egit.github.core.util.DateUtils;
@Entity
@SuppressWarnings("serial")
public class GitIssue implements Serializable {

	@Id
	private long id;
	
	@ManyToOne(cascade = { CascadeType.REFRESH })
	private GitRepository repository;
	
	private Date closedAt;

	private Date createdAt;

	private Date updatedAt;

	private int comments;

	private int number;

	@ManyToMany(cascade = { CascadeType.ALL })
	private List<GitLabel> labels;
	
	@ManyToOne(cascade = { CascadeType.REFRESH })
	private GitMilestone milestone;
	
	//TODO Revisar a necessidade de armazenar o objeto GitPullRequest em uma Issue
//	private GitPullRequest pullRequest;
	private String pullRequest;
	
	private String body;

	private String bodyHtml;

	private String bodyText;

	private String htmlUrl;

	private String state;

	private String title;

	private String url;

	@ManyToOne(cascade = { CascadeType.REFRESH })
	private GitUser assignee;

	@ManyToOne(cascade = { CascadeType.REFRESH })
	private GitUser user;

	public GitIssue(Issue issue) {
		this.setAssignee(new GitUser(issue.getAssignee()));
		this.setBody(issue.getBody());
		this.setBodyHtml(issue.getBodyHtml());
		this.setBodyText(issue.getBodyText());
		this.setClosedAt(issue.getClosedAt());
		this.setComments(issue.getComments());
		this.setCreatedAt(issue.getCreatedAt());
		this.setHtmlUrl(issue.getHtmlUrl());
		this.setId(issue.getId());
		List<GitLabel> labels = new ArrayList<GitLabel>();
		for (Label label : issue.getLabels()) {
			labels.add(new GitLabel(label));
		}
		this.setLabels(labels);
		this.setMilestone(new GitMilestone(issue.getMilestone()));
		this.setNumber(issue.getNumber());
		this.setPullRequest(issue.getPullRequest().toString());
		
		this.setState(issue.getState());
		this.setUpdatedAt(issue.getUpdatedAt());
		this.setTitle(issue.getTitle());
		this.setUrl(issue.getUrl());
		this.setUser(new GitUser(issue.getUser()));
		
	}
	
	/**
	 * @return closedAt
	 */
	public Date getClosedAt() {
		return DateUtils.clone(closedAt);
	}

	/**
	 * @param closedAt
	 * @return this issue
	 */
	public GitIssue setClosedAt(Date closedAt) {
		this.closedAt = DateUtils.clone(closedAt);
		return this;
	}

	/**
	 * @return createdAt
	 */
	public Date getCreatedAt() {
		return DateUtils.clone(createdAt);
	}

	/**
	 * @param createdAt
	 * @return this issue
	 */
	public GitIssue setCreatedAt(Date createdAt) {
		this.createdAt = DateUtils.clone(createdAt);
		return this;
	}

	/**
	 * @return updatedAt
	 */
	public Date getUpdatedAt() {
		return DateUtils.clone(updatedAt);
	}

	/**
	 * @param updatedAt
	 * @return this issue
	 */
	public GitIssue setUpdatedAt(Date updatedAt) {
		this.updatedAt = DateUtils.clone(updatedAt);
		return this;
	}

	/**
	 * @return comments
	 */
	public int getComments() {
		return comments;
	}

	/**
	 * @param comments
	 * @return this issue
	 */
	public GitIssue setComments(int comments) {
		this.comments = comments;
		return this;
	}

	/**
	 * @return number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number
	 * @return this issue
	 */
	public GitIssue setNumber(int number) {
		this.number = number;
		return this;
	}

	/**
	 * @return labels
	 */
	public List<GitLabel> getLabels() {
		return labels;
	}

	/**
	 * @param labels
	 * @return this issue
	 */
	public GitIssue setLabels(List<GitLabel> labels) {
		this.labels = labels != null ? new ArrayList<GitLabel>(labels) : null;
		return this;
	}

	/**
	 * @return milestone
	 */
	public GitMilestone getMilestone() {
		return milestone;
	}

	/**
	 * @param milestone
	 * @return this issue
	 */
	public GitIssue setMilestone(GitMilestone milestone) {
		this.milestone = milestone;
		return this;
	}

	/**
	 * @return pullRequest
	 */
	public String getPullRequest() {
		return pullRequest;
	}

	/**
	 * @param pullRequest
	 * @return this issue
	 */
	public GitIssue setPullRequest(String pullRequest) {
		this.pullRequest = pullRequest;
		return this;
	}

	/**
	 * @return body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body
	 * @return this issue
	 */
	public GitIssue setBody(String body) {
		this.body = body;
		return this;
	}

	/**
	 * @return bodyHtml
	 */
	public String getBodyHtml() {
		return bodyHtml;
	}

	/**
	 * @param bodyHtml
	 * @return this issue
	 */
	public GitIssue setBodyHtml(String bodyHtml) {
		this.bodyHtml = bodyHtml;
		return this;
	}

	/**
	 * @return bodyText
	 */
	public String getBodyText() {
		return bodyText;
	}

	/**
	 * @param bodyText
	 * @return this issue
	 */
	public GitIssue setBodyText(String bodyText) {
		this.bodyText = bodyText;
		return this;
	}

	/**
	 * @return htmlUrl
	 */
	public String getHtmlUrl() {
		return htmlUrl;
	}

	/**
	 * @param htmlUrl
	 * @return this issue
	 */
	public GitIssue setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
		return this;
	}

	/**
	 * @return state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 * @return this issue
	 */
	public GitIssue setState(String state) {
		this.state = state;
		return this;
	}

	/**
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 * @return this issue
	 */
	public GitIssue setTitle(String title) {
		this.title = title;
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
	 * @return this issue
	 */
	public GitIssue setUrl(String url) {
		this.url = url;
		return this;
	}

	/**
	 * @return assignee
	 */
	public GitUser getAssignee() {
		return assignee;
	}

	/**
	 * @param assignee
	 * @return this issue
	 */
	public GitIssue setAssignee(GitUser assignee) {
		this.assignee = assignee;
		return this;
	}

	/**
	 * @return user
	 */
	public GitUser getUser() {
		return user;
	}

	/**
	 * @param user
	 * @return this issue
	 */
	public GitIssue setUser(GitUser user) {
		this.user = user;
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
	 * @return this issue
	 */
	public GitIssue setId(long id) {
		this.id = id;
		return this;
	}

	@Override
	public String toString() {
		return "Issue " + number; //$NON-NLS-1$
	}

	public GitRepository getRepository() {
		return repository;
	}

	public void setRepository(GitRepository repository) {
		this.repository = repository;
	}

}
