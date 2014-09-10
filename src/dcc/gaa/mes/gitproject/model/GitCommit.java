package dcc.gaa.mes.gitproject.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.eclipse.egit.github.core.Commit;

@SuppressWarnings("serial")
@Entity
public class GitCommit implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(cascade = { CascadeType.ALL })
	private GitCommitUser author;

	@ManyToOne(cascade = { CascadeType.ALL })
	private GitCommitUser committer;

	private int commentCount;

	@OneToMany(cascade = { CascadeType.ALL })
	private List<GitCommit> parents;

	private String message;

	private String sha;

	private String url;

	// TODO Avaliar necessidade desse atributo
	// private Tree tree;

	public GitCommit() {
		super();
	}

	public GitCommit(Commit commit) {
		if (commit != null) {
			this.setAuthor(new GitCommitUser(commit.getAuthor()));
			this.setCommitter(new GitCommitUser(commit.getCommitter()));
			this.setCommentCount(commit.getCommentCount());
			this.parents = new ArrayList<GitCommit>();
			if (commit.getParents() != null) {
				for (Commit parentCommit : commit.getParents()) {
					parents.add(new GitCommit(parentCommit));
				}
			}
			this.setMessage(commit.getMessage());
			this.setSha(commit.getSha());
			this.setUrl(commit.getUrl());
		}
	}

	/**
	 * @return author
	 */
	public GitCommitUser getAuthor() {
		return author;
	}

	/**
	 * @param author
	 * @return this commit
	 */
	public GitCommit setAuthor(GitCommitUser author) {
		this.author = author;
		return this;
	}

	/**
	 * @return committer
	 */
	public GitCommitUser getCommitter() {
		return committer;
	}

	/**
	 * @param committer
	 * @return this commit
	 */
	public GitCommit setCommitter(GitCommitUser committer) {
		this.committer = committer;
		return this;
	}

	/**
	 * @return commentCount
	 */
	public int getCommentCount() {
		return commentCount;
	}

	/**
	 * @param commentCount
	 * @return this commit
	 */
	public GitCommit setCommentCount(int commentCount) {
		this.commentCount = commentCount;
		return this;
	}

	/**
	 * @return parents
	 */
	public List<GitCommit> getParents() {
		return parents;
	}

	/**
	 * @param parents
	 * @return this commit
	 */
	public GitCommit setParents(List<GitCommit> parents) {
		this.parents = parents;
		return this;
	}

	/**
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 * @return this commit
	 */
	public GitCommit setMessage(String message) {
		this.message = message;
		return this;
	}

	/**
	 * @return sha
	 */
	public String getSha() {
		return sha;
	}

	/**
	 * @param sha
	 * @return this commit
	 */
	public GitCommit setSha(String sha) {
		this.sha = sha;
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
	 * @return this commit
	 */
	public GitCommit setUrl(String url) {
		this.url = url;
		return this;
	}

	// /**
	// * @return tree
	// */
	// public Tree getTree() {
	// return tree;
	// }
	//
	// /**
	// * @param tree
	// * @return this commit
	// */
	// public MyCommit setTree(Tree tree) {
	// this.tree = tree;
	// return this;
	// }
}