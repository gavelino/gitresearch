package dcc.gaa.mes.gitproject.model;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.egit.github.core.CommitUser;
import org.eclipse.egit.github.core.util.DateUtils;

/**
 * Commit user model class
 */
@Entity
public class MyCommitUser implements Serializable {

	@Id
    @GeneratedValue(strategy =GenerationType.AUTO)
//    @Column(name="commit_user_id")
    private int id; 
	
	@OneToMany(cascade= {CascadeType.REFRESH}, mappedBy = "author")
	private List<MyCommit> commitsAuthor;
	
	@OneToMany(cascade= {CascadeType.REFRESH}, mappedBy = "committer")
	private List<MyCommit> commitsCommiter;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private String email;

	private String name;
	
	public MyCommitUser() {
		super();
	}

	public MyCommitUser(CommitUser commitUser) {
		if (commitUser!=null) {
			Method[] gettersAndSetters = commitUser.getClass().getMethods();
			for (int i = 0; i < gettersAndSetters.length; i++) {
				String methodName = gettersAndSetters[i].getName();
				try {
					if (methodName.startsWith("get")) {
						this.getClass()
								.getMethod(
										methodName.replaceFirst("get", "set"),
										gettersAndSetters[i].getReturnType())
								.invoke(this,
										gettersAndSetters[i].invoke(commitUser,
												null));
					} else if (methodName.startsWith("is")) {
						this.getClass()
								.getMethod(
										methodName.replaceFirst("is", "set"),
										gettersAndSetters[i].getReturnType())
								.invoke(this,
										gettersAndSetters[i].invoke(commitUser,
												null));
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	public MyCommitUser setDate(Date date) {
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
	public MyCommitUser setEmail(String email) {
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
	public MyCommitUser setName(String name) {
		this.name = name;
		return this;
	}
}

