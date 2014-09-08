package dcc.gaa.mes.gitproject.model;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.eclipse.egit.github.core.RepositoryCommit;



@Entity
public class MyRepositoryCommit {
	
	@Id
    @GeneratedValue
//    @Column(name="rep_commit_id")
    private int id; 
	
	@OneToOne(cascade={CascadeType.ALL})
	
	private MyCommit commit;

	@OneToOne(cascade={CascadeType.ALL})
	private MyCommitStats stats;

	@OneToMany(cascade={CascadeType.ALL})
    private List<MyCommit> parents;
	
	@OneToMany(cascade={CascadeType.ALL})
    private List<MyCommitFile> files;

	private String sha;

	private String url;
	
	@OneToOne(cascade={CascadeType.ALL})
	private MyUser author;

	@OneToOne(cascade={CascadeType.ALL})
	private MyUser committer;
	
	public MyRepositoryCommit(RepositoryCommit repositoryCommit) {
		Method[] gettersAndSetters = repositoryCommit.getClass().getMethods();

        for (int i = 0; i < gettersAndSetters.length; i++) {
                String methodName = gettersAndSetters[i].getName();
                try{
                  if(methodName.startsWith("get")){
                     this.getClass().getMethod(methodName.replaceFirst("get", "set") , gettersAndSetters[i].getReturnType() ).invoke(this, gettersAndSetters[i].invoke(repositoryCommit, null));
                        }else if(methodName.startsWith("is") ){
                            this.getClass().getMethod(methodName.replaceFirst("is", "set") ,  gettersAndSetters[i].getReturnType()  ).invoke(this, gettersAndSetters[i].invoke(repositoryCommit, null));
                        }

                }catch (NoSuchMethodException e) {
                    // TODO: handle exception
                }catch (IllegalArgumentException e) {
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
	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return commit
	 */
	public MyCommit getCommit() {
		return commit;
	}

	/**
	 * @param commit
	 * @return this commit
	 */
	public MyRepositoryCommit setCommit(MyCommit commit) {
		this.commit = commit;
		return this;
	}

	/**
	 * @return stats
	 */
	public MyCommitStats getStats() {
		return stats;
	}

	/**
	 * @param stats
	 * @return this commit
	 */
	public MyRepositoryCommit setStats(MyCommitStats stats) {
		this.stats = stats;
		return this;
	}

	/**
	 * @return parents
	 */
	public List<MyCommit> getParents() {
		return parents;
	}

	/**
	 * @param parents
	 * @return this commit
	 */
	public MyRepositoryCommit setParents(List<MyCommit> parents) {
		this.parents = parents;
		return this;
	}

	/**
	 * @return files
	 */
	public List<MyCommitFile> getFiles() {
		return files;
	}

	/**
	 * @param files
	 * @return this commit
	 */
	public MyRepositoryCommit setFiles(List<MyCommitFile> files) {
		this.files = files;
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
	 * @return this commit
	 */
	public MyRepositoryCommit setSha(String sha) {
		this.sha = sha;
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
	 * @return this commit
	 */
	public MyRepositoryCommit setUrl(String url) {
		this.url = url;
		return this;
	}

	/**
	 * @return author
	 */
	public MyUser getAuthor() {
		return author;
	}

	/**
	 * @param author
	 * @return this commit
	 */
	public MyRepositoryCommit setAuthor(MyUser author) {
		this.author = author;
		return this;
	}

	/**
	 * @return committer
	 */
	public MyUser getCommitter() {
		return committer;
	}

	/**
	 * @param committer
	 * @return this commit
	 */
	public MyRepositoryCommit setCommitter(MyUser committer) {
		this.committer = committer;
		return this;
	}
}