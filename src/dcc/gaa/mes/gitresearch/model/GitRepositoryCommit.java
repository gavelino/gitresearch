package dcc.gaa.mes.gitresearch.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.eclipse.egit.github.core.Commit;
import org.eclipse.egit.github.core.CommitFile;
import org.eclipse.egit.github.core.RepositoryCommit;

@SuppressWarnings("serial")
@Entity
public class GitRepositoryCommit implements Serializable {
	
	@ManyToOne(cascade = {CascadeType.REFRESH})
	private GitRepository repository;

	@OneToOne(cascade = { CascadeType.ALL })
	private GitCommit commit;

	@OneToOne(cascade = { CascadeType.ALL })
	private GitCommitStats stats;

	@OneToMany(cascade = { CascadeType.REFRESH })
	private List<GitCommit> parents;

	@OneToMany(cascade = { CascadeType.ALL })
	private List<GitCommitFile> files;

	@Id
	private String sha;

	private String url;

	@ManyToOne(cascade = { CascadeType.REFRESH })
	private GitUser author;

	@ManyToOne(cascade = { CascadeType.REFRESH })
	private GitUser committer;

	public GitRepositoryCommit() {
		super();
	}

	public GitRepositoryCommit(RepositoryCommit repositoryCommit) {
		if (repositoryCommit != null) {
			this.setCommit(new GitCommit(repositoryCommit.getCommit()));
			this.setStats(new GitCommitStats(repositoryCommit.getStats()));
			this.parents = new ArrayList<GitCommit>();
			if (repositoryCommit.getParents() != null) {
				for (Commit parentCommit : repositoryCommit.getParents()) {
					parents.add(new GitCommit(parentCommit));
				}
			}
			this.files = new ArrayList<GitCommitFile>();
			if (repositoryCommit.getFiles() != null) {
				for (CommitFile commitFile : repositoryCommit.getFiles()) {
					files.add(new GitCommitFile(commitFile));
				}
			}
			this.setSha(repositoryCommit.getSha());
			this.commit.setSha(this.getSha());
			this.setUrl(repositoryCommit.getUrl());
			this.setAuthor(new GitUser(repositoryCommit.getAuthor()));
			this.setCommitter(new GitUser(repositoryCommit.getCommitter()));
		}

	}

	/**
	 * @return commit
	 */
	public GitCommit getCommit() {
		return commit;
	}

	/**
	 * @param commit
	 * @return this commit
	 */
	public GitRepositoryCommit setCommit(GitCommit commit) {
		this.commit = commit;
		return this;
	}

	/**
	 * @return stats
	 */
	public GitCommitStats getStats() {
		return stats;
	}

	/**
	 * @param stats
	 * @return this commit
	 */
	public GitRepositoryCommit setStats(GitCommitStats stats) {
		this.stats = stats;
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
	public GitRepositoryCommit setParents(List<GitCommit> parents) {
		this.parents = parents;
		return this;
	}

	/**
	 * @return files
	 */
	public List<GitCommitFile> getFiles() {
		return files;
	}

	/**
	 * @param files
	 * @return this commit
	 */
	public void setFiles(List<GitCommitFile> files) {
		this.files = files;
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
	public GitRepositoryCommit setSha(String sha) {
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
	public GitRepositoryCommit setUrl(String url) {
		this.url = url;
		return this;
	}

	/**
	 * @return author
	 */
	public GitUser getAuthor() {
		return author;
	}

	/**
	 * @param author
	 * @return this commit
	 */
	public GitRepositoryCommit setAuthor(GitUser author) {
		this.author = author;
		return this;
	}

	/**
	 * @return committer
	 */
	public GitUser getCommitter() {
		return committer;
	}

	/**
	 * @param committer
	 * @return this commit
	 */
	public GitRepositoryCommit setCommitter(GitUser committer) {
		this.committer = committer;
		return this;
	}

	public GitRepository getRepository() {
		return repository;
	}

	public void setRepository(GitRepository repository) {
		this.repository = repository;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GitRepositoryCommit [sha=").append(sha).append("]");
		return builder.toString();
	}
	
}