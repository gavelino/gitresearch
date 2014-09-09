package dcc.gaa.mes.gitproject.model;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.eclipse.egit.github.core.CommitFile;

@Entity
public class MyCommitFile  implements Serializable{
	@Id
    @GeneratedValue (strategy = GenerationType.AUTO)
//    @Column(name="commit_file_id")
    private Long id;
	
	@ManyToOne(cascade= {CascadeType.REFRESH})
	private MyRepositoryCommit repositoryCommit;
	
	private int additions;

	private int changes;

	private int deletions;

	private String blobUrl;

	private String filename;

	private String patch;

	private String rawUrl;

	private String sha;

	private String status;
	
	public MyCommitFile() {
		super();
	}

	public MyCommitFile(CommitFile commitFile) {
		if (commitFile!=null) {
			Method[] gettersAndSetters = commitFile.getClass().getMethods();
			for (int i = 0; i < gettersAndSetters.length; i++) {
				String methodName = gettersAndSetters[i].getName();
				try {
					if (methodName.startsWith("get")
							&& !methodName.equalsIgnoreCase("getClass")
							&& !methodName.equalsIgnoreCase("getTree")) {
						this.getClass()
								.getMethod(
										methodName.replaceFirst("get", "set"),
										gettersAndSetters[i].getReturnType())
								.invoke(this,
										gettersAndSetters[i].invoke(commitFile,
												null));
					} else if (methodName.startsWith("is")) {
						this.getClass()
								.getMethod(
										methodName.replaceFirst("is", "set"),
										gettersAndSetters[i].getReturnType())
								.invoke(this,
										gettersAndSetters[i].invoke(commitFile,
												null));
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	
	public MyRepositoryCommit getRepositoryCommit() {
		return repositoryCommit;
	}


	public void setRepositoryCommit(MyRepositoryCommit repositoryCommit) {
		this.repositoryCommit = repositoryCommit;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return additions
	 */
	public int getAdditions() {
		return additions;
	}

	/**
	 * @param additions
	 * @return this commit file
	 */
	public MyCommitFile setAdditions(int additions) {
		this.additions = additions;
		return this;
	}

	/**
	 * @return changes
	 */
	public int getChanges() {
		return changes;
	}

	/**
	 * @param changes
	 * @return this commit file
	 */
	public MyCommitFile setChanges(int changes) {
		this.changes = changes;
		return this;
	}

	/**
	 * @return deletions
	 */
	public int getDeletions() {
		return deletions;
	}

	/**
	 * @param deletions
	 * @return this commit file
	 */
	public MyCommitFile setDeletions(int deletions) {
		this.deletions = deletions;
		return this;
	}

	/**
	 * @return blobUrl
	 */
	public String getBlobUrl() {
		return blobUrl;
	}

	/**
	 * @param blobUrl
	 * @return this commit file
	 */
	public MyCommitFile setBlobUrl(String blobUrl) {
		this.blobUrl = blobUrl;
		return this;
	}

	/**
	 * @return filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename
	 * @return this commit file
	 */
	public MyCommitFile setFilename(String filename) {
		this.filename = filename;
		return this;
	}

	/**
	 * @return patch
	 */
	public String getPatch() {
		return patch;
	}

	/**
	 * @param patch
	 * @return this commit file
	 */
	public MyCommitFile setPatch(String patch) {
		this.patch = patch;
		return this;
	}

	/**
	 * @return rawUrl
	 */
	public String getRawUrl() {
		return rawUrl;
	}

	/**
	 * @param rawUrl
	 * @return this commit file
	 */
	public MyCommitFile setRawUrl(String rawUrl) {
		this.rawUrl = rawUrl;
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
	 * @return this commit file
	 */
	public MyCommitFile setSha(String sha) {
		this.sha = sha;
		return this;
	}

	/**
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 * @return this commit file
	 */
	public MyCommitFile setStatus(String status) {
		this.status = status;
		return this;
	}
}
