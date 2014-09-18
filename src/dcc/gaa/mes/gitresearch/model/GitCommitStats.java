package dcc.gaa.mes.gitresearch.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.eclipse.egit.github.core.CommitStats;

@SuppressWarnings("serial")
@Entity
public class GitCommitStats implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private int additions;

	private int deletions;

	private int total;

	public GitCommitStats() {
		super();
	}

	public GitCommitStats(CommitStats commitStats) {
		if (commitStats != null) {
			this.additions = commitStats.getAdditions();
			this.deletions = commitStats.getDeletions();
			this.total = commitStats.getTotal();
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
	 * @return this commit stats
	 */
	public GitCommitStats setAdditions(int additions) {
		this.additions = additions;
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
	 * @return this commit stats
	 */
	public GitCommitStats setDeletions(int deletions) {
		this.deletions = deletions;
		return this;
	}

	/**
	 * @return total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total
	 * @return this commit stats
	 */
	public GitCommitStats setTotal(int total) {
		this.total = total;
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GitCommitStats [id=").append(id).append(", total=")
				.append(total).append("]");
		return builder.toString();
	}
}
