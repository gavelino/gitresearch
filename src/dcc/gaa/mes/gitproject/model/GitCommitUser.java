package dcc.gaa.mes.gitproject.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.egit.github.core.CommitUser;
import org.eclipse.egit.github.core.util.DateUtils;

/**
 * Commit user model class
 */
@SuppressWarnings("serial")
@Entity
public class GitCommitUser implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private String email;

	private String name;

	public GitCommitUser() {
		super();
	}

	public GitCommitUser(CommitUser commitUser) {
		if (commitUser != null) {
			this.date = commitUser.getDate();
			this.email = commitUser.getEmail();
			this.name = commitUser.getName();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return date
	 */
	public Date getDate() {
		return DateUtils.clone(date);
	}

	/**
	 * @param date
	 * @return this commit user
	 */
	public GitCommitUser setDate(Date date) {
		this.date = DateUtils.clone(date);
		return this;
	}

	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 * @return this commit user
	 */
	public GitCommitUser setEmail(String email) {
		this.email = email;
		return this;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 * @return this commit user
	 */
	public GitCommitUser setName(String name) {
		this.name = name;
		return this;
	}
}
