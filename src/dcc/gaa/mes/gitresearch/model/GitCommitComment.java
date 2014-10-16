package dcc.gaa.mes.gitresearch.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.eclipse.egit.github.core.CommitComment;

@SuppressWarnings("serial")
@Entity 
@DiscriminatorValue("CommitComment")

public class GitCommitComment extends GitComment implements Serializable{

	private int line;

	private int position;
	
	@Id
	private String commitId;

	private String path;

	public GitCommitComment() {
	}

	public GitCommitComment(CommitComment commitComment) {
		if (commitComment != null) {
			// Superclass
			this.setBody(commitComment.getBody());
			this.setBodyHtml(commitComment.getBodyHtml());
			this.setBodyText(commitComment.getBodyText());
			this.setCreatedAt(commitComment.getCreatedAt());
			this.setId(commitComment.getId());
			this.setUpdatedAt(commitComment.getUpdatedAt());
			this.setUrl(commitComment.getUrl());
			if (commitComment.getUser() != null)
				this.setUser(new GitUser(commitComment.getUser()));
			// Subclass specific fields
			this.setLine(commitComment.getLine());
			this.setPosition(commitComment.getPosition());
			this.setCommitId(commitComment.getCommitId());
			this.setPath(commitComment.getPath());
			
		}
	}
	
	/**
	 * @return line
	 */
	public int getLine() {
		return line;
	}

	/**
	 * @param line
	 * @return this commit comment
	 */
	public GitCommitComment setLine(int line) {
		this.line = line;
		return this;
	}

	/**
	 * @return position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * @param position
	 * @return this commit comment
	 */
	public GitCommitComment setPosition(int position) {
		this.position = position;
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
	 * @return this commit comment
	 */
	public GitCommitComment setCommitId(String commitId) {
		this.commitId = commitId;
		return this;
	}

	/**
	 * @return path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 * @return this commit comment
	 */
	public GitCommitComment setPath(String path) {
		this.path = path;
		return this;
	}
}
