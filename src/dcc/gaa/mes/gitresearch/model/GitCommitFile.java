package dcc.gaa.mes.gitresearch.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.eclipse.egit.github.core.CommitFile;

@SuppressWarnings("serial")
@Entity
public class GitCommitFile implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private int additions;

	private int changes;

	private int deletions;

	private String blobUrl;

	private String filename;

	@Lob
	private String patch;

	private String rawUrl;

	private String sha;

	private String status;

	public GitCommitFile() {
		super();
	}

	public GitCommitFile(CommitFile commitFile) {
		if (commitFile != null) {
			this.additions = commitFile.getAdditions();
			this.changes = commitFile.getChanges();
			this.deletions = commitFile.getDeletions();
			this.blobUrl = commitFile.getBlobUrl();
			this.filename = commitFile.getFilename();
			this.patch = commitFile.getPatch();
			this.rawUrl = commitFile.getRawUrl();
			this.sha = commitFile.getSha();
			this.status = commitFile.getStatus();
		}
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
	public GitCommitFile setAdditions(int additions) {
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
	public GitCommitFile setChanges(int changes) {
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
	public GitCommitFile setDeletions(int deletions) {
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
	public GitCommitFile setBlobUrl(String blobUrl) {
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
	public GitCommitFile setFilename(String filename) {
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
	public GitCommitFile setPatch(String patch) {
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
	public GitCommitFile setRawUrl(String rawUrl) {
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
	public GitCommitFile setSha(String sha) {
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
	public GitCommitFile setStatus(String status) {
		this.status = status;
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GitCommitFile [id=").append(id).append(", filename=")
				.append(filename).append(", sha=").append(sha).append("]");
		return builder.toString();
	}
}
