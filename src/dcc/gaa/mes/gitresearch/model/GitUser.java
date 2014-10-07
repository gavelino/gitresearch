package dcc.gaa.mes.gitresearch.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.util.DateUtils;

@SuppressWarnings("serial")
@Entity
public class GitUser implements Serializable {

	public static final String TYPE_USER = "User"; //$NON-NLS-1$
	public static final String TYPE_ORG = "Organization"; //$NON-NLS-1$

	@Id
	private Integer id;

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

	// TODO Avaliar necessidade do campo plan
	// private UserPlan plan;

	public GitUser() {
		super();
	}

	public GitUser(User user) {
		if (user != null ) {
			this.id = user.getId();
			this.hireable = user.isHireable();
			this.createdAt = user.getCreatedAt();
			this.collaborators = user.getCollaborators();
			this.diskUsage = user.getDiskUsage();
			this.followers = user.getFollowers();
			this.following = user.getFollowing();
			this.ownedPrivateRepos = user.getOwnedPrivateRepos();
			this.privateGists = user.getPrivateGists();
			this.publicGists = user.getPublicGists();
			this.publicRepos = user.getPublicRepos();
			this.totalPrivateRepos = user.getTotalPrivateRepos();
			this.avatarUrl = user.getAvatarUrl();
			this.blog = user.getBlog();
			this.company = user.getCompany();
			this.email = user.getEmail();
			this.gravatarId = user.getGravatarId();
			this.htmlUrl = user.getHtmlUrl();
			this.location = user.getLocation();
			this.login = user.getLogin();
			this.name = user.getName();
			this.type = user.getType();
			this.url = user.getUrl();
		}
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
	public GitUser setHireable(boolean hireable) {
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
	public GitUser setCreatedAt(Date createdAt) {
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
	public GitUser setCollaborators(int collaborators) {
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
	public GitUser setDiskUsage(int diskUsage) {
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
	public GitUser setFollowers(int followers) {
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
	public GitUser setFollowing(int following) {
		this.following = following;
		return this;
	}

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 * @return this user
	 */
	public GitUser setId(int id) {
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
	public GitUser setOwnedPrivateRepos(int ownedPrivateRepos) {
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
	public GitUser setPrivateGists(int privateGists) {
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
	public GitUser setPublicGists(int publicGists) {
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
	public GitUser setPublicRepos(int publicRepos) {
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
	public GitUser setTotalPrivateRepos(int totalPrivateRepos) {
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
	public GitUser setAvatarUrl(String avatarUrl) {
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
	public GitUser setBlog(String blog) {
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
	public GitUser setCompany(String company) {
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
	public GitUser setEmail(String email) {
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
	public GitUser setGravatarId(String gravatarId) {
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
	public GitUser setHtmlUrl(String htmlUrl) {
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
	public GitUser setLocation(String location) {
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
	public GitUser setLogin(String login) {
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
	public GitUser setName(String name) {
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
	public GitUser setType(String type) {
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
	public GitUser setUrl(String url) {
		this.url = url;
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GitUser [id=").append(id).append(", login=")
				.append(login).append("]");
		return builder.toString();
	}

	// /**
	// * @return plan
	// */
	// public UserPlan getPlan() {
	// return plan;
	// }
	//
	// /**
	// * @param plan
	// * @return this user
	// */
	// public MyUser setPlan(UserPlan plan) {
	// this.plan = plan;
	// return this;
	// }
}
