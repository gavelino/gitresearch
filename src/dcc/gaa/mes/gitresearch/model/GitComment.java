package dcc.gaa.mes.gitresearch.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.egit.github.core.Comment;
import org.eclipse.egit.github.core.util.DateUtils;

@SuppressWarnings("serial")
@Entity
public class GitComment implements Serializable{
	
	@Id
	private long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	private String body;

	private String bodyHtml;

	private String bodyText;

	private String url;

	@ManyToOne(cascade = { CascadeType.REFRESH })
	private GitUser user;
	
	public GitComment() {
	}
	
	public GitComment(Comment comment) {
		if (comment != null) {
			this.setBody(comment.getBody());
			this.setBodyHtml(comment.getBodyHtml());
			this.setBodyText(comment.getBodyText());
			this.setCreatedAt(comment.getCreatedAt());
			this.setId(comment.getId());
			this.setUpdatedAt(comment.getUpdatedAt());
			this.setUrl(comment.getUrl());
			if (comment.getUser() != null)
				this.setUser(new GitUser(comment.getUser()));
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
	 * @return this comment
	 */
	public GitComment setCreatedAt(Date createdAt) {
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
	 * @return this comment
	 */
	public GitComment setUpdatedAt(Date updatedAt) {
		this.updatedAt = DateUtils.clone(updatedAt);
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
	 * @return this comment
	 */
	public GitComment setBody(String body) {
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
	 * @return this comment
	 */
	public GitComment setBodyHtml(String bodyHtml) {
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
	 * @return this comment
	 */
	public GitComment setBodyText(String bodyText) {
		this.bodyText = bodyText;
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
	 * @return this comment
	 */
	public GitComment setId(long id) {
		this.id = id;
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
	 * @return this comment
	 */
	public GitComment setUrl(String url) {
		this.url = url;
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
	 * @return this comment
	 */
	public GitComment setUser(GitUser user) {
		this.user = user;
		return this;
	}
}
