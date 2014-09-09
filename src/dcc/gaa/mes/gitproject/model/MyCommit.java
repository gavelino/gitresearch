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

@SuppressWarnings("serial")
@Entity
public class MyCommit implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@ManyToOne(cascade= {CascadeType.REFRESH})
	private MyRepositoryCommit repositoryCommit;
	
	@ManyToOne(cascade= {CascadeType.ALL})
	private MyCommitUser author;

	@ManyToOne(cascade= {CascadeType.ALL})
	private MyCommitUser committer;

	private int commentCount;

	@OneToMany(cascade={CascadeType.ALL})
    private List<MyCommit> parents;

	private String message;


	private String sha;

	private String url;
// TODO Avaliar necessidade desse atributo 
//	private Tree tree;
	
	public MyCommit() {
		super();
	}

	public MyCommit(org.eclipse.egit.github.core.Commit commit) {
		if (commit!=null) {
			this.setAuthor(new MyCommitUser(commit.getAuthor()));
			this.setCommitter(new MyCommitUser(commit.getCommitter()));
			this.setCommentCount(commit.getCommentCount());
			this.parents = new ArrayList<MyCommit>();
			if (commit.getParents() != null) {
				for (org.eclipse.egit.github.core.Commit parentCommit : commit.getParents()) {
					parents.add(new MyCommit(parentCommit));
				}
			}
			this.setMessage(commit.getMessage());
			this.setSha(commit.getSha());
			this.setUrl(commit.getUrl());
		}
	}
	
	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public MyRepositoryCommit getRepositoryCommit() {
		return repositoryCommit;
	}


	public void setRepositoryCommit(MyRepositoryCommit repositoryCommit) {
		this.repositoryCommit = repositoryCommit;
	}

	/**
	 * @return author
	 */
	public MyCommitUser getAuthor() {
		return author;
	}

	/**
	 * @param author
	 * @return this commit
	 */
	public MyCommit setAuthor(MyCommitUser author) {
		this.author = author;
		return this;
	}

	/**
	 * @return committer
	 */
	public MyCommitUser getCommitter() {
		return committer;
	}

	/**
	 * @param committer
	 * @return this commit
	 */
	public MyCommit setCommitter(MyCommitUser committer) {
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
	public MyCommit setCommentCount(int commentCount) {
		this.commentCount = commentCount;
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
	public MyCommit setParents(List<MyCommit> parents) {
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
	public MyCommit setMessage(String message) {
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
	public MyCommit setSha(String sha) {
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
	public MyCommit setUrl(String url) {
		this.url = url;
		return this;
	}

//	/**
//	 * @return tree
//	 */
//	public Tree getTree() {
//		return tree;
//	}
//
//	/**
//	 * @param tree
//	 * @return this commit
//	 */
//	public MyCommit setTree(Tree tree) {
//		this.tree = tree;
//		return this;
//	}
}