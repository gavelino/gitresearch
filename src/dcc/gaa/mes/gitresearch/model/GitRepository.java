package dcc.gaa.mes.gitresearch.model;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.egit.github.core.SearchRepository;
import org.eclipse.egit.github.core.util.DateUtils;

@SuppressWarnings("serial")
@Entity
public class GitRepository implements Serializable {

	@Id
	private String id;

	private boolean fork;
	private boolean hasDownloads;
	private boolean hasIssues;
	private boolean hasWiki;

	private boolean isPrivate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date pushedAt;

	private String description;
	private String homepage;
	private String language;
	private String name;
	// TODO Avaliar uma forma de referenciar o GitUser
	private String owner;
	private String url;

	private int forks;
	private int openIssues;
	private int size;
	private int watchers;
	
	@ManyToOne(cascade = {CascadeType.REFRESH})
	private GitResearch gitResearch; 

	@OneToMany(cascade = { CascadeType.ALL })
	private List<GitRepositoryCommit> repositoryCommits;
	
	@OneToMany(cascade = { CascadeType.ALL })
	private List<GitIssue> repositoryIssues;

	public GitRepository() {
		super();
	}

	/**
	 * Create repository
	 * 
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public GitRepository(SearchRepository searchRepository) {
		if (searchRepository != null) {
			this.id = searchRepository.getId();
			
			this.fork = searchRepository.isFork();
			this.hasDownloads = searchRepository.isHasDownloads();
			this.hasIssues = searchRepository.isHasIssues();
			this.hasWiki = searchRepository.isHasWiki();
			
			this.isPrivate = searchRepository.isPrivate();
			
			this.createdAt = searchRepository.getCreatedAt();
			this.pushedAt = searchRepository.getPushedAt();
			
			this.description = searchRepository.getDescription();
			this.homepage = searchRepository.getHomepage();
			this.language = searchRepository.getLanguage();
			this.name = searchRepository.getName();
			this.owner = searchRepository.getOwner();
			this.url = searchRepository.getUrl();
			
			this.forks = searchRepository.getForks();
			this.openIssues = searchRepository.getOpenIssues();
			this.size = searchRepository.getSize();
			this.watchers = searchRepository.getWatchers();
			
			this.repositoryCommits = new LinkedList<GitRepositoryCommit>();
			this.repositoryIssues = new LinkedList<GitIssue>();
		}
	}

	/**
	 * Create repository with owner and name
	 *
	 * @param owner
	 * @param name
	 */
	public GitRepository(String owner, String name) {
		if (owner == null)
			throw new IllegalArgumentException("Owner cannot be null"); //$NON-NLS-1$
		if (owner.length() == 0)
			throw new IllegalArgumentException("Owner cannot be empty"); //$NON-NLS-1$
		if (name == null)
			throw new IllegalArgumentException("Name cannot be null"); //$NON-NLS-1$
		if (name.length() == 0)
			throw new IllegalArgumentException("Name cannot be empty"); //$NON-NLS-1$

		this.owner = owner;
		this.name = name;
	}

	
	
	public List<GitIssue> getRepositoryIssues() {
		return repositoryIssues;
	}

	public void setRepositoryIssues(List<GitIssue> repositoryIssues) {
		this.repositoryIssues = repositoryIssues;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		else if (obj instanceof SearchRepository)
			return getId().equals(((SearchRepository) obj).getId());
		else
			return false;
	}

	public String generateId() {
		final String owner = this.owner;
		if (owner == null || owner.length() == 0)
			return null;
		final String name = this.name;
		if (name == null || name.length() == 0)
			return null;
		return owner + "/" + name; //$NON-NLS-1$
	}

	/**
	 * @return createdAt
	 */
	public Date getCreatedAt() {
		return DateUtils.clone(createdAt);
	}

	/**
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return forks
	 */
	public int getForks() {
		return forks;
	}

	/**
	 * @return homepage
	 */
	public String getHomepage() {
		return homepage;
	}

	/**
	 * Get unique identifier for repository
	 *
	 * @return id
	 */
	public String getId() {
		return owner + '/' + name;
	}

	/**
	 * @return language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return openIssues
	 */
	public int getOpenIssues() {
		return openIssues;
	}

	/**
	 * @return owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @return pushedAt
	 */
	public Date getPushedAt() {
		return DateUtils.clone(pushedAt);
	}

	public List<GitRepositoryCommit> getRepositoryCommits() {
		return repositoryCommits;
	}

	/**
	 * @return size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return watchers
	 */
	public int getWatchers() {
		return watchers;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return getId().hashCode();
	}

	/**
	 * @return fork
	 */
	public boolean isFork() {
		return fork;
	}

	/**
	 * @return hasDownloads
	 */
	public boolean isHasDownloads() {
		return hasDownloads;
	}

	/**
	 * @return hasIssues
	 */
	public boolean isHasIssues() {
		return hasIssues;
	}

	/**
	 * @return hasWiki
	 */
	public boolean isHasWiki() {
		return hasWiki;
	}

	/**
	 * @return isPrivate
	 */
	public boolean isPrivate() {
		return isPrivate;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFork(boolean fork) {
		this.fork = fork;
	}

	public void setForks(int forks) {
		this.forks = forks;
	}

	public void setHasDownloads(boolean hasDownloads) {
		this.hasDownloads = hasDownloads;
	}

	public void setHasIssues(boolean hasIssues) {
		this.hasIssues = hasIssues;
	}

	public void setHasWiki(boolean hasWiki) {
		this.hasWiki = hasWiki;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOpenIssues(int openIssues) {
		this.openIssues = openIssues;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public void setPushedAt(Date pushedAt) {
		this.pushedAt = pushedAt;
	}

	public void setRepositoryCommits(List<GitRepositoryCommit> repositoryCommits) {
		this.repositoryCommits = repositoryCommits;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setWatchers(int watchers) {
		this.watchers = watchers;
	}

	@Override
	public String toString() {
		return "GitRepository [id=" + id + "]";
	}

	public GitResearch getGitResearch() {
		return gitResearch;
	}

	public void setGitResearch(GitResearch gitResearch) {
		this.gitResearch = gitResearch;
	}
}