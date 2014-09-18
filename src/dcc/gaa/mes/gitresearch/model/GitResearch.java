package dcc.gaa.mes.gitresearch.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyClass;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SuppressWarnings("serial")
public class GitResearch implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;

	@Temporal(TemporalType.TIMESTAMP)
	Date date;

	@OneToMany(cascade = { CascadeType.ALL })
	List<GitRepository> repositories;

	@ElementCollection(targetClass = java.lang.String.class)
	@MapKeyClass(java.lang.String.class)
	Map<String, String> searchParams;

	public GitResearch() {
		date = new Date();
	}

	public GitResearch(Map<String, String> searchParams,
			List<GitRepository> repositories) {
		date = new Date();
		this.searchParams = searchParams;
		this.repositories = repositories;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<GitRepository> getRepositories() {
		return repositories;
	}

	public void setRepositories(List<GitRepository> repositories) {
		this.repositories = repositories;
	}

	public Map<String, String> getSearchParams() {
		return searchParams;
	}

	public void setSearchParams(Map<String, String> searchParams) {
		this.searchParams = searchParams;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GitResearch [id=").append(id).append(", date=")
				.append(date).append(", repositories=").append(repositories.size())
				.append(", searchParams=").append(searchParams).append("]");
		return builder.toString();
	}
	
	

}
