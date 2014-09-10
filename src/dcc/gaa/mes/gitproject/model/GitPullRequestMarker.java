package dcc.gaa.mes.gitproject.model;

import java.io.Serializable;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.User;

@SuppressWarnings("serial")
public class GitPullRequestMarker implements Serializable {

	private Repository repo;

	private String label;

	private String ref;

	private String sha;

	private User user;

	/**
	 * @return repo
	 */
	public Repository getRepo() {
		return repo;
	}

	/**
	 * @param repo
	 * @return this marker
	 */
	public GitPullRequestMarker setRepo(Repository repo) {
		this.repo = repo;
		return this;
	}

	/**
	 * @return label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 * @return this marker
	 */
	public GitPullRequestMarker setLabel(String label) {
		this.label = label;
		return this;
	}

	/**
	 * @return ref
	 */
	public String getRef() {
		return ref;
	}

	/**
	 * @param ref
	 * @return this marker
	 */
	public GitPullRequestMarker setRef(String ref) {
		this.ref = ref;
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
	 * @return this marker
	 */
	public GitPullRequestMarker setSha(String sha) {
		this.sha = sha;
		return this;
	}

	/**
	 * @return user
	 */
	public User getUser() {
		return user;
	}

}
