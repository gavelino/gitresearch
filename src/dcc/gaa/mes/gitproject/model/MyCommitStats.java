package dcc.gaa.mes.gitproject.model;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.eclipse.egit.github.core.CommitStats;

@Entity
public class MyCommitStats  implements Serializable{
	@Id
    @GeneratedValue(strategy =GenerationType.AUTO)
//    @Column(name="commit_stats_id")
    private Long id;
	
	@OneToOne(cascade= {CascadeType.REFRESH})
	private MyRepositoryCommit repositoryCommit;
	
	private int additions;

	private int deletions;

	private int total;
	
	public MyCommitStats() {
		super();
	}

	public MyCommitStats(CommitStats commitStats) {
		if (commitStats!=null) {
			Method[] gettersAndSetters = commitStats.getClass().getMethods();
			for (int i = 0; i < gettersAndSetters.length; i++) {
				String methodName = gettersAndSetters[i].getName();
				try {
					if (methodName.startsWith("get")
							&& !methodName.equalsIgnoreCase("getTree")) {
						this.getClass()
								.getMethod(
										methodName.replaceFirst("get", "set"),
										gettersAndSetters[i].getReturnType())
								.invoke(this,
										gettersAndSetters[i].invoke(
												commitStats, null));
					} else if (methodName.startsWith("is")) {
						this.getClass()
								.getMethod(
										methodName.replaceFirst("is", "set"),
										gettersAndSetters[i].getReturnType())
								.invoke(this,
										gettersAndSetters[i].invoke(
												commitStats, null));
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
	 * @return additions
	 */
	public int getAdditions() {
		return additions;
	}

	/**
	 * @param additions
	 * @return this commit stats
	 */
	public MyCommitStats setAdditions(int additions) {
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
	public MyCommitStats setDeletions(int deletions) {
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
	public MyCommitStats setTotal(int total) {
		this.total = total;
		return this;
	}
}
