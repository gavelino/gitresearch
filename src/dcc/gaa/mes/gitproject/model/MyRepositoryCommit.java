package dcc.gaa.mes.gitproject.model;

import java.io.Serializable;
import java.util.List;

import org.eclipse.egit.github.core.Commit;
import org.eclipse.egit.github.core.CommitFile;
import org.eclipse.egit.github.core.CommitStats;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.User;

public class MyRepositoryCommit {

	/** serialVersionUID */
	private static final long serialVersionUID = -8911733018395257250L;

	private Commit commit;

	private CommitStats stats;

	private List<Commit> parents;

	private List<CommitFile> files;

	private String sha;

	private String url;

	private User author;

	private User committer;

	/**
	 * @return commit
	 */
	public Commit getCommit() {
		return commit;
	}

	/**
	 * @param commit
	 * @return this commit
	 */
	public MyRepositoryCommit setCommit(Commit commit) {
		this.commit = commit;
		return this;
	}

	/**
	 * @return stats
	 */
	public CommitStats getStats() {
		return stats;
	}

	/**
	 * @param stats
	 * @return this commit
	 */
	public MyRepositoryCommit setStats(CommitStats stats) {
		this.stats = stats;
		return this;
	}

	/**
	 * @return parents
	 */
	public List<Commit> getParents() {
		return parents;
	}

	/**
	 * @param parents
	 * @return this commit
	 */
	public MyRepositoryCommit setParents(List<Commit> parents) {
		this.parents = parents;
		return this;
	}

	/**
	 * @return files
	 */
	public List<CommitFile> getFiles() {
		return files;
	}

	/**
	 * @param files
	 * @return this commit
	 */
	public MyRepositoryCommit setFiles(List<CommitFile> files) {
		this.files = files;
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
	public MyRepositoryCommit setSha(String sha) {
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
	public MyRepositoryCommit setUrl(String url) {
		this.url = url;
		return this;
	}

	/**
	 * @return author
	 */
	public User getAuthor() {
		return author;
	}

	/**
	 * @param author
	 * @return this commit
	 */
	public MyRepositoryCommit setAuthor(User author) {
		this.author = author;
		return this;
	}

	/**
	 * @return committer
	 */
	public User getCommitter() {
		return committer;
	}

	/**
	 * @param committer
	 * @return this commit
	 */
	public MyRepositoryCommit setCommitter(User committer) {
		this.committer = committer;
		return this;
	}
}