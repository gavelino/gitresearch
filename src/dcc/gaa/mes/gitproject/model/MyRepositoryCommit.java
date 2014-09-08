package dcc.gaa.mes.gitproject.model;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;

import org.eclipse.egit.github.core.Commit;
import org.eclipse.egit.github.core.CommitFile;
import org.eclipse.egit.github.core.RepositoryCommit;



@Entity
public class MyRepositoryCommit  implements Serializable{
	
	@Id
    @GeneratedValue(strategy =GenerationType.AUTO)
//    @Column(name="rep_commit_id")
    private int id; 
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	private MySearchRepository searchRepository;
	
	@OneToOne(cascade={CascadeType.ALL}, mappedBy = "repositoryCommit")	
	private MyCommit commit;

	@OneToOne(cascade={CascadeType.ALL}, mappedBy = "repositoryCommit")
	private MyCommitStats stats;

	@OneToMany(cascade={CascadeType.ALL}, mappedBy = "repositoryCommit")	
    private List<MyCommit> parents;
	
	@OneToMany(cascade={CascadeType.ALL})
	private List<MyCommitFile> files;

	private String sha;

	private String url;
	
	@OneToOne(cascade={CascadeType.REFRESH}, mappedBy = "repositoryCommit")
	private MyUser author;

	@OneToOne(cascade={CascadeType.REFRESH}, mappedBy = "repositoryCommit")
	private MyUser committer;
	
	public MyRepositoryCommit(RepositoryCommit repositoryCommit) {
		if (repositoryCommit!=null) {
			this.setCommit(new MyCommit(repositoryCommit.getCommit()));
			this.setStats(new MyCommitStats(repositoryCommit.getStats()));
			this.parents = new ArrayList<MyCommit>();
			if (repositoryCommit.getParents() != null) {
				for (Commit parentCommit : repositoryCommit.getParents()) {
					parents.add(new MyCommit(parentCommit));
				}
			}
			this.files = new ArrayList<MyCommitFile>();
			if (repositoryCommit.getFiles() != null) {
				for (CommitFile commitFile : repositoryCommit.getFiles()) {
					files.add(new MyCommitFile(commitFile));
				}
			}
			this.setSha(commit.getSha());
			this.setUrl(commit.getUrl());
			this.setAuthor(new MyUser(repositoryCommit.getAuthor()));
			this.setCommitter(new MyUser(repositoryCommit.getCommitter()));
		}
		
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MySearchRepository getSearchRepository() {
		return searchRepository;
	}
	public void setSearchRepository(MySearchRepository searchRepository) {
		this.searchRepository = searchRepository;
	}
	
	/**
	 * @return commit
	 */
	public MyCommit getCommit() {
		return commit;
	}

	/**
	 * @param commit
	 * @return this commit
	 */
	public MyRepositoryCommit setCommit(MyCommit commit) {
		this.commit = commit;
		return this;
	}

	/**
	 * @return stats
	 */
	public MyCommitStats getStats() {
		return stats;
	}

	/**
	 * @param stats
	 * @return this commit
	 */
	public MyRepositoryCommit setStats(MyCommitStats stats) {
		this.stats = stats;
		return this;
	}

	/**
	 * @return parents
	 */
	public List<MyCommit> getParents() {
		return parents;
	}

	/**
	 * @param parents
	 * @return this commit
	 */
	public MyRepositoryCommit setParents(List<MyCommit> parents) {
		this.parents = parents;
		return this;
	}

	/**
	 * @return files
	 */
	public List<MyCommitFile> getFiles() {
		return files;
	}

	/**
	 * @param files
	 * @return this commit
	 */
	public void setFiles(List<MyCommitFile> files) {
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
	public MyUser getAuthor() {
		return author;
	}

	/**
	 * @param author
	 * @return this commit
	 */
	public MyRepositoryCommit setAuthor(MyUser author) {
		this.author = author;
		return this;
	}

	/**
	 * @return committer
	 */
	public MyUser getCommitter() {
		return committer;
	}

	/**
	 * @param committer
	 * @return this commit
	 */
	public MyRepositoryCommit setCommitter(MyUser committer) {
		this.committer = committer;
		return this;
	}
}