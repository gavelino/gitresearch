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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.Label;

@Entity
@SuppressWarnings("serial")
public class GitIssue implements Serializable {

	@Id
	private long id;

	@ManyToOne(cascade = { CascadeType.REFRESH })
	private GitRepository repository;

	@Temporal(TemporalType.TIMESTAMP)
	private Date closedAt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	private int comments;

	private int number;

	@ManyToMany(cascade = { CascadeType.REFRESH })
	private List<GitIssueEvent> events;

	@ManyToMany(cascade = { CascadeType.REFRESH })
	private List<GitLabel> labels;

	@ManyToOne(cascade = { CascadeType.REFRESH })
	private GitMilestone milestone;

	// TODO Revisar a necessidade de armazenar o objeto GitPullRequest em uma
	// Issue
	// private GitPullRequest pullRequest;
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

	@ManyToOne(cascade = { CascadeType.REFRESH })
	private GitUser closedBy;

	public GitIssue() {
		super();
	}

	public GitIssue(Issue issue, GitRepository gitRepository) {
		if (issue != null) {
			this.repository = gitRepository;

			if (issue.getAssignee() != null) {
				this.assignee = new GitUser(issue.getAssignee());
			}

			this.body = issue.getBody();
			this.bodyHtml = issue.getBodyHtml();
			this.bodyText = issue.getBodyText();
			this.closedAt = issue.getClosedAt();
			this.comments = issue.getComments();
			this.createdAt = issue.getCreatedAt();
			this.htmlUrl = issue.getHtmlUrl();
			this.id = issue.getId();

			List<GitLabel> labels = new ArrayList<GitLabel>();
			for (Label label : issue.getLabels()) {
				labels.add(new GitLabel(label));
			}

			this.labels = labels;

			if (issue.getMilestone() != null) {
				this.milestone = new GitMilestone(issue.getMilestone());
			}

			this.number = issue.getNumber();
			this.pullRequest = issue.getPullRequest().toString();
			this.state = issue.getState();
			this.updatedAt = issue.getUpdatedAt();
			this.title = issue.getTitle();
			this.url = issue.getUrl();

			if (issue.getUser() != null) {
				this.user = new GitUser(issue.getUser());
			}
		}

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public GitRepository getRepository() {
		return repository;
	}

	public void setRepository(GitRepository repository) {
		this.repository = repository;
	}

	public Date getClosedAt() {
		return closedAt;
	}

	public void setClosedAt(Date closedAt) {
		this.closedAt = closedAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<GitIssueEvent> getEvents() {
		return events;
	}

	public void setEvents(List<GitIssueEvent> events) {
		this.events = events;
	}

	public List<GitLabel> getLabels() {
		return labels;
	}

	public void setLabels(List<GitLabel> labels) {
		this.labels = labels;
	}

	public GitMilestone getMilestone() {
		return milestone;
	}

	public void setMilestone(GitMilestone milestone) {
		this.milestone = milestone;
	}

	public String getPullRequest() {
		return pullRequest;
	}

	public void setPullRequest(String pullRequest) {
		this.pullRequest = pullRequest;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getBodyHtml() {
		return bodyHtml;
	}

	public void setBodyHtml(String bodyHtml) {
		this.bodyHtml = bodyHtml;
	}

	public String getBodyText() {
		return bodyText;
	}

	public void setBodyText(String bodyText) {
		this.bodyText = bodyText;
	}

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public GitUser getAssignee() {
		return assignee;
	}

	public void setAssignee(GitUser assignee) {
		this.assignee = assignee;
	}

	public GitUser getUser() {
		return user;
	}

	public void setUser(GitUser user) {
		this.user = user;
	}

	public GitUser getClosedBy() {
		return closedBy;
	}

	public void setClosedBy(GitUser closedBy) {
		this.closedBy = closedBy;
	}

	@Override
	public String toString() {
		return "GitIssue [id=" + id + "]";
	}

}
