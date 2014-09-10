package dcc.gaa.mes.gitresearch.model;

import java.io.Serializable;
import java.util.Date;

import org.eclipse.egit.github.core.Milestone;
import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.util.DateUtils;

@SuppressWarnings("serial")
public class GitPullRequest implements Serializable {

	private boolean mergeable;

	private boolean merged;

	private Date closedAt;

	private Date mergedAt;

	private Date updatedAt;

	private Date createdAt;

	private long id;

	private int additions;

	private int changedFiles;

	private int comments;

	private int commits;

	private int deletions;

	private int number;

	private Milestone milestone;

	private GitPullRequestMarker base;

	private GitPullRequestMarker head;

	private String body;

	private String bodyHtml;

	private String bodyText;

	private String diffUrl;

	private String htmlUrl;

	private String issueUrl;

	private String patchUrl;

	private String state;

	private String title;

	private String url;

	private User assignee;

	private User mergedBy;

	private User user;

	/**
	 * @return mergeable
	 */
	public boolean isMergeable() {
		return mergeable;
	}

	/**
	 * @param mergeable
	 * @return this pull request
	 */
	public GitPullRequest setMergeable(boolean mergeable) {
		this.mergeable = mergeable;
		return this;
	}

	/**
	 * @return merged
	 */
	public boolean isMerged() {
		return merged;
	}

	/**
	 * @param merged
	 * @return this pull request
	 */
	public GitPullRequest setMerged(boolean merged) {
		this.merged = merged;
		return this;
	}

	/**
	 * @return closedAt
	 */
	public Date getClosedAt() {
		return DateUtils.clone(closedAt);
	}

	/**
	 * @param closedAt
	 * @return this pull request
	 */
	public GitPullRequest setClosedAt(Date closedAt) {
		this.closedAt = DateUtils.clone(closedAt);
		return this;
	}

	/**
	 * @return mergedAt
	 */
	public Date getMergedAt() {
		return DateUtils.clone(mergedAt);
	}

	/**
	 * @param mergedAt
	 * @return this pull request
	 */
	public GitPullRequest setMergedAt(Date mergedAt) {
		this.mergedAt = DateUtils.clone(mergedAt);
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
	 * @return this pull request
	 */
	public GitPullRequest setUpdatedAt(Date updatedAt) {
		this.updatedAt = DateUtils.clone(updatedAt);
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
	 * @return this pull request
	 */
	public GitPullRequest setCreatedAt(Date createdAt) {
		this.createdAt = DateUtils.clone(createdAt);
		return this;
	}

	/**
	 * @return additions
	 */
	public int getAdditions() {
		return additions;
	}

	/**
	 * @param additions
	 * @return this pull request
	 */
	public GitPullRequest setAdditions(int additions) {
		this.additions = additions;
		return this;
	}

	/**
	 * @return changedFiles
	 */
	public int getChangedFiles() {
		return changedFiles;
	}

	/**
	 * @param changedFiles
	 * @return this pull request
	 */
	public GitPullRequest setChangedFiles(int changedFiles) {
		this.changedFiles = changedFiles;
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
	 * @return this pull request
	 */
	public GitPullRequest setComments(int comments) {
		this.comments = comments;
		return this;
	}

	/**
	 * @return commits
	 */
	public int getCommits() {
		return commits;
	}

	/**
	 * @param commits
	 * @return this pull request
	 */
	public GitPullRequest setCommits(int commits) {
		this.commits = commits;
		return this;
	}

	/**
	 * @return deletions
	 */
	public int getDeletions() {
		return deletions;
	}

	/**
	 * @param deletions
	 * @return this pull request
	 */
	public GitPullRequest setDeletions(int deletions) {
		this.deletions = deletions;
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
	 * @return this pull request
	 */
	public GitPullRequest setNumber(int number) {
		this.number = number;
		return this;
	}

	/**
	 * @return base
	 */
	public GitPullRequestMarker getBase() {
		return base;
	}

	/**
	 * @param base
	 * @return this pull request
	 */
	public GitPullRequest setBase(GitPullRequestMarker base) {
		this.base = base;
		return this;
	}

	/**
	 * @return head
	 */
	public GitPullRequestMarker getHead() {
		return head;
	}

	/**
	 * @param head
	 * @return this pull request
	 */
	public GitPullRequest setHead(GitPullRequestMarker head) {
		this.head = head;
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
	 * @return this pull request
	 */
	public GitPullRequest setBody(String body) {
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
	 * @return this pull request
	 */
	public GitPullRequest setBodyHtml(String bodyHtml) {
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
	 * @return this pull request
	 */
	public GitPullRequest setBodyText(String bodyText) {
		this.bodyText = bodyText;
		return this;
	}

	/**
	 * @return diffUrl
	 */
	public String getDiffUrl() {
		return diffUrl;
	}

	/**
	 * @param diffUrl
	 * @return this pull request
	 */
	public GitPullRequest setDiffUrl(String diffUrl) {
		this.diffUrl = diffUrl;
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
	 * @return this pull request
	 */
	public GitPullRequest setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
		return this;
	}

	/**
	 * @return issueUrl
	 */
	public String getIssueUrl() {
		return issueUrl;
	}

	/**
	 * @param issueUrl
	 * @return this pull request
	 */
	public GitPullRequest setIssueUrl(String issueUrl) {
		this.issueUrl = issueUrl;
		return this;
	}

	/**
	 * @return patchUrl
	 */
	public String getPatchUrl() {
		return patchUrl;
	}

	/**
	 * @param patchUrl
	 * @return this pull request
	 */
	public GitPullRequest setPatchUrl(String patchUrl) {
		this.patchUrl = patchUrl;
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
	 * @return this pull request
	 */
	public GitPullRequest setState(String state) {
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
	 * @return this pull request
	 */
	public GitPullRequest setTitle(String title) {
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
	 * @return this pull request
	 */
	public GitPullRequest setUrl(String url) {
		this.url = url;
		return this;
	}

	/**
	 * @return mergedBy
	 */
	public User getMergedBy() {
		return mergedBy;
	}

	/**
	 * @param mergedBy
	 * @return this pull request
	 */
	public GitPullRequest setMergedBy(User mergedBy) {
		this.mergedBy = mergedBy;
		return this;
	}

	/**
	 * @return user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 * @return this pull request
	 */
	public GitPullRequest setUser(User user) {
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
	 * @return this pull request
	 */
	public GitPullRequest setId(long id) {
		this.id = id;
		return this;
	}

	/**
	 * @return milestone
	 */
	public Milestone getMilestone() {
		return milestone;
	}

	/**
	 * @param milestone
	 * @return this pull request
	 */
	public GitPullRequest setMilestone(Milestone milestone) {
		this.milestone = milestone;
		return this;
	}

	/**
	 * @return assignee
	 */
	public User getAssignee() {
		return assignee;
	}

	/**
	 * @param assignee
	 * @return this pull request
	 */
	public GitPullRequest setAssignee(User assignee) {
		this.assignee = assignee;
		return this;
	}

	@Override
	public String toString() {
		return "Pull Request " + number; //$NON-NLS-1$
	}
}
