package dcc.gaa.mes.gitresearch.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.egit.github.core.CommitUser;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GitCommitUser [id=").append(id).append(", date=")
				.append(date).append(", email=").append(email)
				.append(", name=").append(name).append("]");
		return builder.toString();
	}

}
