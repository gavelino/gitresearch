package dcc.gaa.mes.gitproject.model;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.egit.github.core.SearchRepository;
import org.eclipse.egit.github.core.util.DateUtils;

@Entity
public class MySearchRepository implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 978627174722864632L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

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
	private String owner;
	private String url;

	private int forks;
	private int openIssues;
	private int size;
	private int watchers;

	private int commits;

	@OneToMany(cascade = { CascadeType.ALL })
	private List<MyRepositoryCommit> repositoryCommits;
	
	public MySearchRepository() {
		super();
	}

	/**
	 * Create repository
	 * 
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public MySearchRepository(SearchRepository searchRepository) {
		if (searchRepository != null) {
			Method[] gettersAndSetters = searchRepository.getClass()
					.getMethods();
			for (int i = 0; i < gettersAndSetters.length; i++) {
				String methodName = gettersAndSetters[i].getName();
				try {
					if (methodName.startsWith("get")) {
						this.getClass()
								.getMethod(
										methodName.replaceFirst("get", "set"),
										gettersAndSetters[i].getReturnType())
								.invoke(this,
										gettersAndSetters[i].invoke(
												searchRepository, null));
					} else if (methodName.startsWith("is")) {
						this.getClass()
								.getMethod(
										methodName.replaceFirst("is", "set"),
										gettersAndSetters[i].getReturnType())
								.invoke(this,
										gettersAndSetters[i].invoke(
												searchRepository, null));
					}

				} catch (NoSuchMethodException e) {
					// TODO: handle exception
				} catch (IllegalArgumentException e) {
					// TODO: handle exception
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	/**
	 * Create repository with owner and name
	 *
	 * @param owner
	 * @param name
	 */
	public MySearchRepository(String owner, String name) {
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

	public int getCommits() {
		return commits;
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

	public List<MyRepositoryCommit> getRepositoryCommits() {
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

	public void setCommits(int commits) {
		this.commits = commits;
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

	public void setId(Long id) {
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

	public void setRepositoryCommits(List<MyRepositoryCommit> repositoryCommits) {
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

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getId();
	}
}