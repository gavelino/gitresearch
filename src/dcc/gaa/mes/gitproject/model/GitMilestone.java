package dcc.gaa.mes.gitproject.model;

import java.io.Serializable;
import java.util.Date;

import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.util.DateUtils;

@SuppressWarnings("serial")
public class GitMilestone implements Serializable {

	private Date createdAt;

	private Date dueOn;

	private int closedIssues;

	private int number;

	private int openIssues;

	private String description;

	private String state;

	private String title;

	private String url;

	private User creator;

	/**
	 * @return createdAt
	 */
	public Date getCreatedAt() {
		return DateUtils.clone(createdAt);
	}

	/**
	 * @param createdAt
	 * @return this milestone
	 */
	public GitMilestone setCreatedAt(Date createdAt) {
		this.createdAt = DateUtils.clone(createdAt);
		return this;
	}

	/**
	 * @return dueOn
	 */
	public Date getDueOn() {
		return DateUtils.clone(dueOn);
	}

	/**
	 * @param dueOn
	 * @return this milestone
	 */
	public GitMilestone setDueOn(Date dueOn) {
		this.dueOn = DateUtils.clone(dueOn);
		return this;
	}

	/**
	 * @return closedIssues
	 */
	public int getClosedIssues() {
		return closedIssues;
	}

	/**
	 * @param closedIssues
	 * @return this milestone
	 */
	public GitMilestone setClosedIssues(int closedIssues) {
		this.closedIssues = closedIssues;
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
	 * @return this milestone
	 */
	public GitMilestone setNumber(int number) {
		this.number = number;
		return this;
	}

	/**
	 * @return openIssues
	 */
	public int getOpenIssues() {
		return openIssues;
	}

	/**
	 * @param openIssues
	 * @return this milestone
	 */
	public GitMilestone setOpenIssues(int openIssues) {
		this.openIssues = openIssues;
		return this;
	}

	/**
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 * @return this milestone
	 */
	public GitMilestone setDescription(String description) {
		this.description = description;
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
	 * @return this milestone
	 */
	public GitMilestone setState(String state) {
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
	 * @return this milestone
	 */
	public GitMilestone setTitle(String title) {
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
	 * @return this milestone
	 */
	public GitMilestone setUrl(String url) {
		this.url = url;
		return this;
	}

	/**
	 * @return creator
	 */
	public User getCreator() {
		return creator;
	}

	/**
	 * @param creator
	 * @return this milestone
	 */
	public GitMilestone setCreator(User creator) {
		this.creator = creator;
		return this;
	}
}
