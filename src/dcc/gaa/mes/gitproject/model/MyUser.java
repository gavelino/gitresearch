package dcc.gaa.mes.gitproject.model;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.util.DateUtils;

@SuppressWarnings("serial")
@Entity
public class MyUser  implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@OneToOne(cascade= {CascadeType.REFRESH})
	private MyRepositoryCommit repositoryCommit;
	
	/**
	 * TYPE_USER
	 */
	public static final String TYPE_USER = "User"; //$NON-NLS-1$

	/**
	 * TYPE_ORG
	 */
	public static final String TYPE_ORG = "Organization"; //$NON-NLS-1$

	private boolean hireable;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	private int collaborators;

	private int diskUsage;

	private int followers;

	private int following;


	private int ownedPrivateRepos;

	private int privateGists;

	private int publicGists;

	private int publicRepos;

	private int totalPrivateRepos;

	private String avatarUrl;

	private String blog;

	private String company;

	private String email;

	private String gravatarId;

	private String htmlUrl;

	private String location;

	private String login;

	private String name;

	private String type;

	private String url;
//	TODO Avaliar necessidade do campo plan
//	private UserPlan plan;
	
	public MyUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyUser(User user) {
		if (user!=null) {
			Method[] gettersAndSetters = user.getClass().getMethods();
			for (int i = 0; i < gettersAndSetters.length; i++) {
				String methodName = gettersAndSetters[i].getName();
				try {
					if (methodName.startsWith("get")
							&& !methodName.equalsIgnoreCase("getPlan")) {
						this.getClass()
								.getMethod(
										methodName.replaceFirst("get", "set"),
										gettersAndSetters[i].getReturnType())
								.invoke(this,
										gettersAndSetters[i].invoke(user, null));
					} else if (methodName.startsWith("is")) {
						this.getClass()
								.getMethod(
										methodName.replaceFirst("is", "set"),
										gettersAndSetters[i].getReturnType())
								.invoke(this,
										gettersAndSetters[i].invoke(user, null));
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
	
	
	public MyRepositoryCommit getRepositoryCommit() {
		return repositoryCommit;
	}


	public void setRepositoryCommit(MyRepositoryCommit repositoryCommit) {
		this.repositoryCommit = repositoryCommit;
	}


	/**
	 * @return hireable
	 */
	public boolean isHireable() {
		return hireable;
	}

	/**
	 * @param hireable
	 * @return this user
	 */
	public MyUser setHireable(boolean hireable) {
		this.hireable = hireable;
		return this;
	}

	/**
	 * @return createdAt
	 */
	public Date getCreatedAt() {
		return DateUtils.clone(createdAt);
	}

	/**
	 * @param createdAt
	 * @return this user
	 */
	public MyUser setCreatedAt(Date createdAt) {
		this.createdAt = DateUtils.clone(createdAt);
		return this;
	}

	/**
	 * @return collaborators
	 */
	public int getCollaborators() {
		return collaborators;
	}

	/**
	 * @param collaborators
	 * @return this user
	 */
	public MyUser setCollaborators(int collaborators) {
		this.collaborators = collaborators;
		return this;
	}

	/**
	 * @return diskUsage
	 */
	public int getDiskUsage() {
		return diskUsage;
	}

	/**
	 * @param diskUsage
	 * @return this user
	 */
	public MyUser setDiskUsage(int diskUsage) {
		this.diskUsage = diskUsage;
		return this;
	}

	/**
	 * @return followers
	 */
	public int getFollowers() {
		return followers;
	}

	/**
	 * @param followers
	 * @return this user
	 */
	public MyUser setFollowers(int followers) {
		this.followers = followers;
		return this;
	}

	/**
	 * @return following
	 */
	public int getFollowing() {
		return following;
	}

	/**
	 * @param following
	 * @return this user
	 */
	public MyUser setFollowing(int following) {
		this.following = following;
		return this;
	}

	/**
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 * @return this user
	 */
	public MyUser setId(Long id) {
		this.id = id;
		return this;
	}

	/**
	 * @return ownedPrivateRepos
	 */
	public int getOwnedPrivateRepos() {
		return ownedPrivateRepos;
	}

	/**
	 * @param ownedPrivateRepos
	 * @return this user
	 */
	public MyUser setOwnedPrivateRepos(int ownedPrivateRepos) {
		this.ownedPrivateRepos = ownedPrivateRepos;
		return this;
	}

	/**
	 * @return privateGists
	 */
	public int getPrivateGists() {
		return privateGists;
	}

	/**
	 * @param privateGists
	 * @return this user
	 */
	public MyUser setPrivateGists(int privateGists) {
		this.privateGists = privateGists;
		return this;
	}

	/**
	 * @return publicGists
	 */
	public int getPublicGists() {
		return publicGists;
	}

	/**
	 * @param publicGists
	 * @return this user
	 */
	public MyUser setPublicGists(int publicGists) {
		this.publicGists = publicGists;
		return this;
	}

	/**
	 * @return publicRepos
	 */
	public int getPublicRepos() {
		return publicRepos;
	}

	/**
	 * @param publicRepos
	 * @return this user
	 */
	public MyUser setPublicRepos(int publicRepos) {
		this.publicRepos = publicRepos;
		return this;
	}

	/**
	 * @return totalPrivateRepos
	 */
	public int getTotalPrivateRepos() {
		return totalPrivateRepos;
	}

	/**
	 * @param totalPrivateRepos
	 * @return this user
	 */
	public MyUser setTotalPrivateRepos(int totalPrivateRepos) {
		this.totalPrivateRepos = totalPrivateRepos;
		return this;
	}

	/**
	 * @return avatarUrl
	 */
	public String getAvatarUrl() {
		return avatarUrl;
	}

	/**
	 * @param avatarUrl
	 * @return this user
	 */
	public MyUser setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
		return this;
	}

	/**
	 * @return blog
	 */
	public String getBlog() {
		return blog;
	}

	/**
	 * @param blog
	 * @return this user
	 */
	public MyUser setBlog(String blog) {
		this.blog = blog;
		return this;
	}

	/**
	 * @return company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company
	 * @return this user
	 */
	public MyUser setCompany(String company) {
		this.company = company;
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
	 * @return this user
	 */
	public MyUser setEmail(String email) {
		this.email = email;
		return this;
	}

	/**
	 * @return gravatarId
	 */
	public String getGravatarId() {
		return gravatarId;
	}

	/**
	 * @param gravatarId
	 * @return this user
	 */
	public MyUser setGravatarId(String gravatarId) {
		this.gravatarId = gravatarId;
		return this;
	}

	/**
	 * @return htmlUrl
	 */
	public String getHtmlUrl() {
		return htmlUrl;
	}

	/**
	 * @param htmlUrl
	 * @return this user
	 */
	public MyUser setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
		return this;
	}

	/**
	 * @return location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 * @return this user
	 */
	public MyUser setLocation(String location) {
		this.location = location;
		return this;
	}

	/**
	 * @return login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 * @return this user
	 */
	public MyUser setLogin(String login) {
		this.login = login;
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
	 * @return this user
	 */
	public MyUser setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 * @return this user
	 */
	public MyUser setType(String type) {
		this.type = type;
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
	 * @return this user
	 */
	public MyUser setUrl(String url) {
		this.url = url;
		return this;
	}

//	/**
//	 * @return plan
//	 */
//	public UserPlan getPlan() {
//		return plan;
//	}
//
//	/**
//	 * @param plan
//	 * @return this user
//	 */
//	public MyUser setPlan(UserPlan plan) {
//		this.plan = plan;
//		return this;
//	}
}
